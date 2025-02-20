package Controller;

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

import Common.ConvertURL;
import Common.PageNavi;
import Common.Statics;
import DAOImpl.BoardDAOImpl;
import DAOImpl.FilesDAOImpl;
import DAOImpl.ReplyDAOImpl;
import DTO.BoardDTO;
import DTO.FilesDTO;
import DTO.MemberDTO;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardDAOImpl dao = BoardDAOImpl.INSTANCE;
	FilesDAOImpl fdao = FilesDAOImpl.INSTANCE;
	ReplyDAOImpl rdao = ReplyDAOImpl.INSTANCE;

	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			String cmd = ConvertURL.of(request);
			System.out.println(cmd);

			String ip = request.getRemoteAddr();
			System.out.println(ip);

			if (cmd.equals("/board/addform.do")) {
				request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);

			}
			
			else if (cmd.equals("/board/printout.do")) {

			
				
			} else if (cmd.equals("/board/list.do")) {// 게시글 목록 출력
	            String scpage = (String) request.getParameter("cpage");

	            if (scpage == null) {
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

	            List<BoardDTO> list = dao.selectAll(cpage);
	            request.setAttribute("list", list);

	            request.setAttribute("cpage", cpage);
	            PageNavi pageNavi = new PageNavi(cpage, dao.getSize());
	            request.setAttribute("page", pageNavi.generate());
	            
	            request.getSession().getAttribute("dto");
	            
	            request.getRequestDispatcher("/WEB-INF/views/board/board.jsp").forward(request, response);

			} 
			
//			
//			else if (cmd.equals("/ajax_list.board")) {// 게시물 목록
//				String cpage = (String) request.getParameter("page");
//				if (cpage == null) {
//					cpage = "1";
//				}
//
//				int page = Integer.parseInt(cpage);
//
//				int recordTotalCount = dao.getSize();
//
//				int pageTotalCount = 0;
//
//				PageNavi pageNavi = new PageNavi(cpage, dao.getSize(), 10, 5);
//				request.setAttribute("pageNavi", pageNavi);
//				request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
//
//			} 
			

			else if (cmd.equals("/board/detail.do")) { // 상세게시물

				int boardId = Integer.parseInt(request.getParameter("id"));// jsp에서 url 뒤에 붙는 id

				dao.viewCount(boardId);
				
				MemberDTO dto = (MemberDTO)request.getSession().getAttribute("loginId");
			
//				int target = Integer.parseInt(request.getParameter("getId"));// 게시물id 가져옴
				
				request.setAttribute("loginID", dto);
				request.setAttribute("dto", dao.selectById(boardId));// 세션에서 아이디값 가져옴

				request.setAttribute("reply", rdao.selectById(boardId));

				List<FilesDTO> fdto = (List<FilesDTO>) fdao.selectById(boardId);// 파일을 업로드할 게시물 찾음

			


				request.setAttribute("filelist", fdto);// jsp에 filelist 쓸수있게 속성 부여 ${filelist} 이렇게 써야됨

				request.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(request, response);

			}

			else if (cmd.equals("/board/update.do")) {// 게시글 수정
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");

				BoardDTO dto = new BoardDTO(title, contents, boardId);

				int result = dao.update(dto);

				response.sendRedirect("/WEB-INF/views/board/list.board?cpage" + boardId);

			}

			else if (cmd.equals("/board/delete.do")) {// 게시글 삭제
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				int result = dao.deleteById(boardId);

				if (result == 0) {
					System.out.println("삭제 실패");
				} else if (result > 0) {

					System.out.println(result + "개 삭제 성공");
				}

				request.getRequestDispatcher("/board/list.do").forward(request, response);


			} else if (cmd.equals("/board/delete.do")) {// 게시글 삭제
				int boardId = Integer.parseInt(request.getParameter("boardId"));

				BoardDTO dto = dao.selectById(boardId);

				MemberDTO member = (MemberDTO) request.getSession().getAttribute("dto");

				int result = dao.deleteById(boardId);

				response.sendRedirect("/WEB-INF/views/board/list.board?cpage=1");

			} else if (cmd.equals("/board/mypage.do")) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf8");

		String cmd = request.getRequestURI();

		Gson g = new Gson();
		String ip = request.getRemoteAddr();
		System.out.println(ip);

		try {

			if (cmd.equals("/board/add.do")) {// 게시글 추가

				// 로그인 검증
				MemberDTO dto =(MemberDTO) request.getSession().getAttribute("member");

				if (dto == null) {
					response.sendRedirect("/");
					return;
				} // dto값이 없을 경우 페이지 이동 x

				int maxSize = 1024 * 1024 * 10; // 파일 업로드 최대 사이즈(10mb)
				String savePath = request.getServletContext().getRealPath("upload"); // 파일 업로드 경로
				File filePath = new File(savePath);
				if (!filePath.exists()) {// 해당 경로가 아닌 경우
					filePath.mkdir();// filrPath 가 말하는 경로가 없는 경우 디렉터리를 생성
				}

				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "utf8",
						new DefaultFileRenamePolicy());

				// 파일 업로드를 처리하는 MultipartRequest 객체를 생성하는 코드입니다.
				// 사용자가 파일을 업로드하면, 이 객체가 해당 파일을 서버의 특정 경로에 저장해줍니다.

				int boardId = dao.getNextVal(); // 게시글을 작성시 Board 테이블의 id값을 가져오는 메서드
				int memberId = dto.getMemberId();
				String title = multi.getParameter("title");
				String contents = multi.getParameter("contents");
				dao.insert(new BoardDTO(boardId, title, memberId, contents));
			
				Enumeration<String> fileNames = multi.getFileNames(); // Enumeration => List와 같음

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
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
