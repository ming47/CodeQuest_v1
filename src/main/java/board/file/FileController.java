package board.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import utils.ConvertURL;
import utils.FileUtil;

@MultipartConfig // 파일 업로드를 위한 어노테이션
@WebServlet("/file/*")
public class FileController extends HttpServlet {     
	Gson g = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/file/download.do")) {
				System.out.println(request.getServletContext().getRealPath("upload"));

				// 권한 확인하기..
				// 기록 남기기
				// 등등..
				String file_Name = request.getParameter("filename");
				String oriName = request.getParameter("oriname");
				String path = request.getServletContext().getRealPath("upload");
	            //다운로드에 필요한 정보 취합 
				File target = new File(path + "/" + file_Name);
				byte[] fileContents = new byte[(int) target.length()];
				// 다운로드 할 대상 파일의 내용을 byte[]에 로딩하기 위해 저장소 준비

				oriName = new String(oriName.getBytes("utf8"),"ISO-8859-1");
				//오리지널 파일 이름 인코딩 처리 
				
				response.reset();
				response.setHeader("Content-Disposition", "attachment; filename=" + oriName);
				// response 에 지금 보내는 값은 소스코드가 아닌 파일 다운로드임 이라는 정보를 탑재

				try(DataInputStream dis = new DataInputStream(new FileInputStream(target));
						ServletOutputStream sos = response.getOutputStream();){
			
			
					dis.readFully(fileContents);//파일 내용을 RAM에 전부 로딩 
					sos.write(fileContents);//response 의 stream을 통해 byte[] 출력
					sos.flush();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/file/image/upload.do")) {	// 서머노트 에디터 이미지 들어올때 사용하는 요청
				String requestPath = request.getParameter("request");
				
				String uploadPath = FileUtil.getPath("image", requestPath);
		        Path uploadDir = Paths.get(uploadPath);
		        		        
		        if (!Files.exists(uploadDir)) {
		            Files.createDirectories(uploadDir);
		        }
				Part file = request.getPart("file");
				
				String fileName = 
						FileUtil.generateSysFileName(Paths.get(file.getSubmittedFileName()).getFileName().toString());
				
				try (InputStream fileContent = file.getInputStream()) {
		            Path filePath = uploadDir.resolve(fileName);
		            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
		            
		            Map<String, Object> json = new HashMap<>();
		            json.put("path", FileUtil.getPath(filePath.toString()));
		            
		            System.out.println(FileUtil.getPath(filePath.toString()));
		            response.setContentType("application/json");
		            response.getWriter().append(g.toJson(json));
		        }
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
