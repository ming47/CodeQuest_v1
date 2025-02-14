package Common;

import javax.servlet.http.HttpServletRequest;

public class ConvertURL {
   public static String convert(HttpServletRequest request) {
       String requestURI = request.getRequestURI();
       String contextPath = request.getContextPath();
       String path = requestURI.substring(contextPath.length());
       
       if (!path.endsWith(".do")) {
          throw new IllegalStateException(path + "는 잘못된 url입니다. .do로만 입력해 주세요.");
       }
      
      return path;
   }
}