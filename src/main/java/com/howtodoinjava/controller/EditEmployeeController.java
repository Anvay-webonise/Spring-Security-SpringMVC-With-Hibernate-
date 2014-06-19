package com.howtodoinjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import com.howtodoinjava.entity.EmployeeEntity;
import com.howtodoinjava.service.EmployeeManager;

@Controller
public class EditEmployeeController {

	@Autowired
	private EmployeeManager employeeManager;
	
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		System.out.println("IN /");
		return "redirect:/list";
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEmployees(ModelMap map, HttpSession session) {
		System.out.println("IN /list");
		System.out.println("session id is " + session.getId());
		
		map.addAttribute("employee", new EmployeeEntity());
		map.addAttribute("employeeList", employeeManager.getAllEmployees());

		return "editEmployeeList";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String superAdmin(ModelMap map) {
		System.out.println("IN /admin");
			return "superAdmin";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEmployee(
			@ModelAttribute(value = "employee") EmployeeEntity employee,
			BindingResult result,HttpSession session) {
		System.out.println("IN /add");
		System.out.println("session id is " + session.getId());
		
		employeeManager.addEmployee(employee);
		return "redirect:/list";
	}

	@RequestMapping("/delete/{employeeId}")
	public String deleteEmplyee(@PathVariable("employeeId") Integer employeeId) {
		System.out.println("IN /delete");
		employeeManager.deleteEmployee(employeeId);
		return "redirect:/list";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		System.out.println("IN /login");
		return "login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		System.out.println("IN /accessdenied");
		model.addAttribute("error", "true");
		return "denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		System.out.println("IN /logout");
		System.out.println("session id is " + session.getId());
		return "logout";
	}
}
