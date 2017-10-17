package com.illuxplain.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.illuxplain.models.Comments;
import com.illuxplain.repository.MySqlCommentPresistence;
import com.illuxplain.repository.PresistenceRepository;


@Controller
public class MachineLearning {
	
	@Autowired
	@Qualifier("mysqlCommentRepo")
	PresistenceRepository<Comments> commentRepo;
	
	
	@RequestMapping(value="machine-learning")
	public ModelAndView machineLearning(){
		Object[] params = { "machine-1"};
		try {
			List<Comments> listOfComments = commentRepo.readforList(createQueryForAllComments(), params);
			ModelAndView modelAndView = new ModelAndView("machine-learning");
			modelAndView.addObject("commentList",listOfComments);
			return modelAndView;	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	private String createQueryForAllComments(){
		return "SELECT * FROM comments WHERE page_id = ?";
	}
}
