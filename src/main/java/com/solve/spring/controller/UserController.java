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
import com.solve.spring.model.SearchRank;

import com.solve.spring.service.SearchRankService;

@Controller
public class UserController {
 
	@Autowired
	SearchRankService searchRankService;
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "입력한 아이디와 비밀번호가 일치하지 않습니다.");

        if (logout != null)
            model.addAttribute("message", "로그아웃 하였습니다.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	
    		List<SearchRank> searchRank = searchRankService.findAllByOrderCountDesc();
    		
    		model.addAttribute("searchRank", searchRank);

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
