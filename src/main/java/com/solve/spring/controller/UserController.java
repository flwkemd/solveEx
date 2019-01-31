package com.solve.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solve.spring.enums.EnumCategoryGroup;
import com.solve.spring.model.PlaceRank;
import com.solve.spring.service.PlaceRankService;


@Controller
public class UserController {

	@Autowired
	PlaceRankService placeRankService;
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	
    	List<PlaceRank> placeRank = placeRankService.findAllByOrdercntDesc();
    	
    	
    	model.addAttribute("EnumCategory", EnumCategoryGroup.values());
    	
        return "welcome";
    }
    
	@RequestMapping("/detail")
	public String detail(HttpServletRequest req, HttpServletResponse res, Model model,
			@RequestParam("y") String y,
			@RequestParam("x") String x) {
		try {
			model.addAttribute("y", y);
			model.addAttribute("x", x);
			return "/detail";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
}
