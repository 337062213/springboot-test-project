package com.springboot.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.model.Data;
import com.springboot.test.model.User;

@RestController
public class HelloWorldController {
    
    @RequestMapping(path= {"/hello"},method=RequestMethod.POST,produces= {"application/json; charset=UTF-8"})
    @ResponseBody
    public HashMap<String, String> sayHello( @RequestBody @Valid User user, HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        String name= user.getName();
        map.put("name",name);
        map.put("value", "Hello! "+name);
        map.put("secret", "I love you");
        return map;
    }
    
    @RequestMapping(path= {"/getUser"},method=RequestMethod.POST,produces= {"application/json; charset=UTF-8"})
    @ResponseBody
    public CompletableFuture<String> getUser( @RequestBody @Valid List<User> user, HttpServletRequest request) {
    	
    	
    	CompletableFuture<String> future=CompletableFuture.supplyAsync(()->{
    		
    		Data.que.offer("");
    		  return "ok";
    	});
    	
    	future.whenComplete((result,exception)->{
    		if(exception==null) {
    			future.complete(result);
    			return;
    		}
    		future.completeExceptionally(exception);
    	});
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i<user.size();i++) {
        	String name = user.get(i).getName();
        	list.add(name);
        }
        
        return future;
    }
}
