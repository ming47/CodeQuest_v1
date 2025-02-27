package utils;

import javax.servlet.http.HttpServletRequest;

public class ConvertURL {
   public static String of(HttpServletRequest request) {
       String requestURI = request.getRequestURI();
       String contextPath = request.getContextPath();
       String path = requestURI.substring(contextPath.length());
       
       if (path.endsWith(".exe") || path.endsWith(".msi")) {
          throw new IllegalStateException(path + " 이상한거 넣지 마라.");
       }
      
      return path;
   }
}