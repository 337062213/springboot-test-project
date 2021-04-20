 package com.springboot.test.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;

public class PathUtils {
     
     public static String getPathByDefaultClassLoader() {
         return ClassUtils.getDefaultClassLoader().getResource("").getPath().replaceFirst("/", "");
     }
     
     public static String getPathByClass() {
         return Class.class.getClass().getResource("/").getPath().replaceFirst("/", "");
     }
     
     public static String getPathByProperty() {
         return System.getProperty("user.dir").replace('\\', '/');
     }
     
     public static String getPathByRequest(HttpServletRequest request){
         return request.getServletContext().getRealPath("/").replace('\\', '/');
     }
     
     public static void main(String[] args) {
         System.out.println(PathUtils.getPathByClass());
         System.out.println(PathUtils.getPathByDefaultClassLoader());
         System.out.println(PathUtils.getPathByProperty());
     }
}
