package com.springboot.test.controller;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller  
public class GlobalErrorController {      
  
  @RequestMapping("/admin/error")  
  public String error(HttpRequest request, Model model) {  
    return "screen/error";  
  }
  
  @RequestMapping("/404")  
  @ResponseStatus(value=HttpStatus.NOT_FOUND)  
  public String notFoundError() {  
      return "error/404";  
  }  
    
  @RequestMapping("/500")  
  @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)  
  public String interdError() {  
      return "error/500";  
  } 
  
}  
