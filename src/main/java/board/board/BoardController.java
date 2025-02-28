package board.board;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.file.FilesDAO;
import board.file.FilesDAOImpl;
import board.file.FilesDTO;
import board.viewcount.ViewCountDAO;
import board.viewcount.ViewCountDAOImpl;
import board.viewcount.ViewCountDTO;
import member.member.MemberDTO;
import utils.ConvertURL;
import utils.PageNavi;
import utils.Statics;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardDAO dao = BoardDAOImpl.INSTANCE;
	FilesDAO fdao = FilesDAOImpl.INSTANCE;
	ViewCountDAO vdao = ViewCountDAOImpl.INSTANCE;

	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cmd = ConvertURL.of(request);
			if (cmd.equals("/board/addform.do")) {
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				if (member == null) { // 회원만 글쓰기 가능
					response.sendRedirect("/");
					return;
				} else if(member.getIsbanned()) { // 밴 유저는 글쓰기 불가
					response.sendRedirect("/");
					return;
				}
				request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);
			} else if (cmd.equals("/board/list.do")) {// 게시글 목록 출력
				String scpage = (String) request.getParameter("cpage");

				if (scpage == null || scpage.equals("null")) {
					scpage = "1";
				}
				int cpage = Integer.parseInt(scpage);

				int recordTotalCount = dao.getSize();

				int pageTotalCount = 0;
				if (recordTotalCount % Statics.recordCountPerPage > 0) {
					pageTotalCount = recordTotalCount / Statics.recordCountPerPage + 1;
				} else {
					pageTotalCount = recordTotalCount / Statics.recordCountPerPage;
				}

				if (cpage < 1) {
					cpage = 1;
				} else if (cpage > pageTotalCount) {
					cpage = pageTotalCount;
				}
				request.setAttribute("cpage", cpage);

				List<BoardDTO> noticeList = dao.selectRecentNotice();
				request.setAttribute("noticeList", noticeList);

				List<BoardDTO> list = dao.selectAll(cpage);
				request.setAttribute("list", list);

				PageNavi pageNavi = new PageNavi(cpage, dao.getSize());
				request.setAttribute("page", pageNavi.generate());
				
				request.setAttribute("pageUrl", "/board/list.do?cpage=");

				request.getRequestDispatcher("/WEB-INF/views/board/board.jsp").forward(request, response);
			} else if (cmd.equals("/board/mainlist.do")) {// 게시글 목록 출력
				List<BoardDTO> list = dao.selectTop5Boardlist();
				response.getWriter().append(g.toJson(list));
			} else if (cmd.equals("/board/search.do")) { // 검색 게시물 

				String scpage = (String) request.getParameter("cpage");
				String searchField = "";
				String searchText = "";

				searchField = (String)request.getParameter("searchField");
				searchText = (String)request.getParameter("searchText");

				if (scpage == null || scpage.equals("null")) {
					scpage = "1";
				}
				int cpage = Integer.parseInt(scpage);

				int recordTotalCount = dao.getSize();

				int pageTotalCount = 0;
				if (recordTotalCount % Statics.recordCountPerPage > 0) {
					pageTotalCount = recordTotalCount / Statics.recordCountPerPage + 1;
				} else {
					pageTotalCount = recordTotalCount / Statics.recordCountPerPage;
				}

				if (cpage < 1) {
					cpage = 1;
				} else if (cpage > pageTotalCount) {
					cpage = pageTotalCount;
				}
				request.setAttribute("cpage", cpage);

				List<BoardDTO>searchResultList = dao.selectBoardList(searchField, searchText,cpage);
				request.setAttribute("list",searchResultList);

				PageNavi pageNavi = new PageNavi(cpage, dao.getSearchListSize(searchField,searchText));
				request.setAttribute("page", pageNavi.generate());
				
				request.setAttribute("pageUrl", String.format("/board/search.do?searchField=%s&searchText=%s&cpage=", searchField, searchText));

				request.getRequestDispatcher("/WEB-INF/views/board/board.jsp").forward(request, response);
			} else if (cmd.equals("/board/detail.do")) {
				int boardId = Integer.parseInt(request.getParameter("id"));

				dao.increaseViewCount(boardId);

				MemberDTO dto = (MemberDTO) request.getSession().getAttribute("member");
				
				if(dto != null) {				
					vdao.insertMember(new ViewCountDTO(boardId, dto.getMemberId()));
				} else {
					vdao.insert(new ViewCountDTO(boardId));
				}	
				request.setAttribute("member", dto);
				request.setAttribute("dto", dao.selectById(boardId));

				int target = Integer.parseInt(request.getParameter("id"));
				List<FilesDTO> fdto = (List<FilesDTO>) fdao.selectByBoardId(target);

				request.setAttribute("filelist", fdto);
				request.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(request, response);
			} else if (cmd.equals("/board/mypage.do")) {

			}  else if (cmd.equals("/board/delete.do")) {// 게시글 삭제
				int boardId = Integer.parseInt(request.getParameter("id"));

				dao.deleteById(boardId);
				response.sendRedirect("/board/list.do?cpage=1");				
			} else if (cmd.equals("/board/hotweek/list.do")) {
				response.getWriter().append(g.toJson(dao.selectTop5WeekendBoardList()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/board/add.do")) {
				MemberDTO dto = (MemberDTO) request.getSession().getAttribute("member");

				if (dto == null) {
					response.sendRedirect("/");
					return;
				} 

				int maxSize = 1024 * 1024 * 10; 
				String savePath = request.getServletContext().getRealPath("upload");
				File filePath = new File(savePath);
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "utf8",
						new DefaultFileRenamePolicy());

				int boardId = dao.getNextVal(); 
				int memberId = dto.getMemberId();
				String title = multi.getParameter("title");
				String contents = multi.getParameter("contents");
				dao.insert(new BoardDTO(boardId, title, memberId, contents));

				Enumeration<String> fileNames = multi.getFileNames();

				while (fileNames.hasMoreElements()) {
					String name = fileNames.nextElement();
					String oriName = multi.getOriginalFileName(name);

					if (oriName == null) {
						continue;
					}
					String sysName = multi.getFilesystemName(name);

					fdao.insert(new FilesDTO(0, boardId, oriName, sysName));
				}
				
				response.sendRedirect("/board/list.do");
			} else if (cmd.equals("/board/update.do")) {
				int boardId = Integer.parseInt(request.getParameter("id"));
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");

				BoardDTO dto = new BoardDTO(title, contents, boardId);

				dao.update(dto);
				response.sendRedirect("/board/detail.do?id=" + boardId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
