package com.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.beans.Admin;
import com.ecom.beans.Category;
import com.ecom.beans.PhysicalCategory;
import com.ecom.repository.AdminRepository;
import com.ecom.service.PhysicalCategoryService;
import com.ecom.service.SubcategoryService;

@Controller
@RequestMapping("/PhysicalCatagory")
public class PhysicalCatagoryController {

	@Autowired
	PhysicalCategoryService PhysicalCategoryServices;
	@Autowired
	SubcategoryService subcatagoryservice;
	@Autowired
	AdminRepository adminRepository;

	@RequestMapping("/catagorylist/{eid}")
	public String addCatagory(Model model, Category catagory, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			List<PhysicalCategory> catagorylist = PhysicalCategoryServices.getAllCategory();
			model.addAttribute("catagorylist", catagorylist);
			model.addAttribute("catagory", catagory);
			return "Physicalcategory";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/savecatagory/{eid}")
	public String saveCatagory(PhysicalCategory catagory, @PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			PhysicalCategoryServices.addCategory(catagory);
			return "redirect:/PhysicalCatagory/catagorylist/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@GetMapping("/deleteCategory/{id}/{eid}")
	public String deleteCategory(@PathVariable("id") int id, @PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);

			System.out.println("ssss");
			PhysicalCategoryServices.deleteCategoryById(id);

			return "redirect:/PhysicalCatagory/catagorylist/" + admin.getEmployeeId();
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@GetMapping("/editcategory/{id}/{eid}")
	public String EditBycategoryid(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalCategory obj = PhysicalCategoryServices.getCategoryById(id);
			// List<Category> catagory=service.getAllCategoryDetails();
			model.addAttribute("categorieslist", obj);
			// model.addAttribute("catagory", catagory);
			return "EditPhysicalCategory";

		} else {
			return "redirect:/emp/back-end";
		}

	}
}
