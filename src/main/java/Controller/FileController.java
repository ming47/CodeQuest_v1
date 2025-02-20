package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import Common.ConvertURL;
import Common.FileUtil;

@MultipartConfig // 파일 업로드를 위한 어노테이션
@WebServlet("/file/*")
public class FileController extends HttpServlet {     
	Gson g = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/file/image/upload.do")) {	// 서머노트 에디터 이미지 들어올때 사용하는 요청
				String requestPath = request.getParameter("request");
				
				String uploadPath = FileUtil.getPath("image", requestPath);
		        Path uploadDir = Paths.get(uploadPath);
		        
		        System.out.println(uploadPath);
		        
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
