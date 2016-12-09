package com.java.springMVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.springMVCBoard.command.BCommand;
import com.java.springMVCBoard.command.BContentCommand;
import com.java.springMVCBoard.command.BDeleteCommand;
import com.java.springMVCBoard.command.BListCommand;
import com.java.springMVCBoard.command.BModifyCommand;
import com.java.springMVCBoard.command.BReplyCommand;
import com.java.springMVCBoard.command.BReplyViewCommand;
import com.java.springMVCBoard.command.BWriteCommand;

@Controller
public class BController {

	
	BCommand command;
	
	//리스트를 본다.
	@RequestMapping("/list")
	public String list(Model model){
		
		System.out.println("list() is started");
		
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	//글을 작성하는 곳.
	@RequestMapping("/write_view")
	public String write_view(Model model){
		
		System.out.println("write_view() is started");
		
		return "write_view";
	}
	
	//글을 작성한다.
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		System.out.println("write() is started");
		
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//작성한 글의 내용을 본다.
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		
		System.out.println("content_view() is started");
		
		model.addAttribute("request",request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	//글을 수정한다.
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		System.out.println("modify() is started");
		
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//답변한 글을 본다.
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model){
		
		System.out.println("reply_view() is started");
		
		model.addAttribute("request",request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "";
	}
	
	//답변을 작성한다.
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model){
		
		System.out.println("reply() is started");
		
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//삭제한다.
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		System.out.println("delete() is started");
		
		model.addAttribute("request",request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	
}
