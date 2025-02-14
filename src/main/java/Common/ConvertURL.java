package Common;

import javax.servlet.http.HttpServletRequest;

public class ConvertURL {
   public static String of(HttpServletRequest request) {
       String requestURI = request.getRequestURI();
       String contextPath = request.getContextPath();
       String path = requestURI.substring(contextPath.length());
       
       if (!path.endsWith(".do")) {
          throw new IllegalStateException(path + "는 잘못된 요청입니다.");
       }
      
      return path;
   }
}