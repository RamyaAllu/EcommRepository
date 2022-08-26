package com.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom.beans.Admin;
import com.ecom.beans.Category;
import com.ecom.beans.SubCategory;
import com.ecom.repository.AdminRepository;
import com.ecom.service.CategoryService;
import com.ecom.service.SubcategoryService;

@Controller
@RequestMapping("/subcatagory")
public class SubCatagoryController {
	@Autowired
	CategoryService catagoryservice;
	@Autowired
	SubcategoryService subcatagoryservice;
	@Autowired
	AdminRepository adminRepository;

	@RequestMapping("/add-subcatagory/{id}/{eid}")
	public String addSubCatagory(@PathVariable(value = "id") int id, Model model, SubCategory subcatagory,
			@PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			Category catagory = catagoryservice.getCategoryById(id);
			subcatagory.setCategoryId(catagory.getCategoryId());
			subcatagory.setCategoryName(catagory.getCategoryName());

			SubCategory subcatagoryobject = subcatagoryservice.addSubcategory(subcatagory);
			model.addAttribute("catagory", catagory);
			model.addAttribute("subcatagoryobject", subcatagoryobject);

			return "category-sub";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/savesubcatagory/{id}/{eid}")

	public String saveSubCatagory(SubCategory subcatagory, @PathVariable(value = "id") int id, Model model,
			@PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			Category catagory = catagoryservice.getCategoryById(id);
			subcatagory.setCategoryId(catagory.getCategoryId());
			subcatagory.setCategoryName(catagory.getCategoryName());
			subcatagoryservice.addSubcategory(subcatagory);

			return "redirect:/subcatagory/subcatagory-list/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@GetMapping(value = "/subcatagory-list/{eid}")

//@RequestMapping("/subcatagory-list")
	public String subCatagoryList(Model model, @PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			List<SubCategory> subcatagorylist = subcatagoryservice.list();
			model.addAttribute("subcatagorylist", subcatagorylist);
			return "catagory-sub-list";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("countries", catagoryservice.findAll());
		return "add-digital-product";
	}

	@GetMapping(value = "/getSubCatagory", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SubCategory> getSubCatagory(@RequestParam Integer catagoryId) {

		List<SubCategory> list = subcatagoryservice.findSubCategoryByCategory(catagoryId);

		System.out.println("catagoryId   " + catagoryId);

		return list;
	}

	@GetMapping("/deleteSubCategory/{id}/{eid}")
	public String deleteSubCategory(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			subcatagoryservice.deleteSubCategoryById(id);

			return "redirect:/subcatagory/subcatagory-list/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@GetMapping("/editSubcategory/{id}/{eid}")
	public String EditBycategoryid(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			SubCategory obj = subcatagoryservice.getSubCategoryById(id);
			model.addAttribute("categorieslist", obj);

			return "EditSubCategory";
		} else {
			return "redirect:/emp/back-end";
		}
	}
}