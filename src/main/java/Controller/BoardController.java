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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {			
			String cmd = ConvertURL.of(request);
			System.out.println(cmd);

			String ip = request.getRemoteAddr();
			System.out.println(ip);

			if(cmd.equals("/board/add.do")) {
				request.getRequestDispatcher("/write.jsp").forward(request, response);
			}else if(cmd.equals("/board/printout.do")) {
				
			} else if (cmd.equals("/board/list.do")) {//게시글 목록 출력 
				// 페이징 유효성 검증
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
				} 
				else if (cpage > pageTotalCount) {
					cpage = pageTotalCount;
				}
			
				int end = cpage * Statics.recordCountPerPage;
				int start = end - (Statics.recordCountPerPage - 1);

				
				List<BoardDTO> list = dao.selectAll();
				request.setAttribute("list", list);

				int startNavi = (cpage - 1) / Statics.naviCountPerPage * Statics.naviCountPerPage + 1;
				int endNavi = startNavi + Statics.naviCountPerPage - 1;

				if (endNavi > pageTotalCount) { 
					endNavi = pageTotalCount;
				}

				boolean needPrev = true;
				boolean needNext = true;
				if (startNavi == 1) {
					needPrev = false;
				}
				if (endNavi == pageTotalCount) {
					needNext = false;
				}

				// request.setAttribute("list", list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("startNavi", startNavi);
				request.setAttribute("endNavi", endNavi);
				request.setAttribute("needPrev", needPrev);
				request.setAttribute("needNext", needNext);
				request.getSession().getAttribute("dto");
				
				request.getRequestDispatcher("/board.jsp").forward(request, response);
			} else if (cmd.equals("/ajax_list.board")) {// 게시물 목록
					String scpage = (String) request.getParameter("cpage");
					if (scpage == null) {
						scpage = "1";
					}

					int cpage = Integer.parseInt(scpage);

					int recordTotalCount = dao.getSize();

					int pageTotalCount = 0;

					PageNavi pageNavi = new PageNavi(cpage, dao.getSize(), 10, 5);
					request.setAttribute("pageNavi", pageNavi);
					request.getRequestDispatcher("/list.jsp").forward(request, response);
			} else if (cmd.equals("/board/detail.do")) { // 상세게시물
				
			} else if(cmd.equals("/board/update.do")) {//게시글 수정 
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
					
				BoardDTO dto = new BoardDTO(title,contents,boardId);
						
				int result = dao.update(dto);
						
				response.sendRedirect("/list.board?cpage"+boardId);
			} else if(cmd.equals("/board/delete.do")) {//게시글 삭제
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				
				BoardDTO dto = dao.selectById(boardId);
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("dto");
				String user = member.getId();

				
				int result = dao.deleteById(boardId);
				
				response.sendRedirect("/list.board?cpage=1");
					
					
			} else if(cmd.equals("/board/mypage.do")) {
				
			}

		} catch(Exception e) {
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
				MemberDTO dto = (MemberDTO) request.getSession().getAttribute("dto");
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

				int seq = dao.getNextVal(); // 게시글을 작성시 Board 테이블의id값을 가져오는 메서드
				String writer = dto.getId();
				String title = multi.getParameter("title");
				String contents = multi.getParameter("contents");
				//dao.insert(new BoardDTO(seq, title, writer, contents));

				Enumeration<String> fileNames = multi.getFileNames(); // Enumeration => List와 같음

				while (fileNames.hasMoreElements()) { // hasMoreElements = boolean
					String name = fileNames.nextElement();
					String originName = multi.getOriginalFileName(name);

					if (originName == null) {
						continue;
					}
//
//			String sysName = multi.getFilesystemName(name);
//			fdao.insert(new FilesDTO(0, originName, sysName, seq));

				}
				response.sendRedirect("/list.board?cpage=1");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
