package com.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom.beans.Admin;
import com.ecom.beans.PhysicalCategory;
import com.ecom.beans.PhysicalSubCategory;
import com.ecom.repository.AdminRepository;
import com.ecom.service.PhysicalCategoryService;
import com.ecom.service.PhysicalSubCategoryService;

@Controller
@RequestMapping("/PhysicalSubCategory")
public class PhysicalSubCategoryController {
	@Autowired
	PhysicalCategoryService PhysicalCategoryServices;
	@Autowired
	PhysicalSubCategoryService PhysicalSubCategoryServices;
	@Autowired
	AdminRepository adminRepository;

	@RequestMapping("/addsubcatagory/{id}/{eid}")
	public String addSubCatagory(@PathVariable(value = "id") int id, Model model, PhysicalSubCategory subcatagory,
			@PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			System.out.println("test");

			PhysicalCategory catagory = PhysicalCategoryServices.getCategoryById(id);
			subcatagory.setCategoryId(catagory.getCategoryId());
			subcatagory.setCategoryName(catagory.getCategoryName());

			PhysicalSubCategory subcatagoryobject = PhysicalSubCategoryServices.addPhysicalSubCategory(subcatagory);
			model.addAttribute("catagory", catagory);
			model.addAttribute("subcatagoryobject", subcatagoryobject);
			return "Physicalcategory-sub";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/savesubcatagory/{id}/{eid}")

	public String saveSubCatagory(PhysicalSubCategory subcatagory, @PathVariable(value = "id") int id, Model model,
			@PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalCategory catagory = PhysicalCategoryServices.getCategoryById(id);
			subcatagory.setCategoryId(catagory.getCategoryId());
			subcatagory.setCategoryName(catagory.getCategoryName());
			PhysicalSubCategoryServices.addPhysicalSubCategory(subcatagory);

			return "redirect:/PhysicalSubCategory/subcatagory-list/" + eid;
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
			List<PhysicalSubCategory> subcatagorylist = PhysicalSubCategoryServices.list();
			model.addAttribute("subcatagorylist", subcatagorylist);
			return "Physicalcatagory-sub-list";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@GetMapping(value = "/getSubCatagory", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PhysicalSubCategory> getSubCatagory(@RequestParam String catagoryName) {

		List<PhysicalSubCategory> list = PhysicalSubCategoryServices.findPhysicalSubCategoryByCategory(catagoryName);

		System.out.println("catagoryId   " + catagoryName);

		return list;
	}

	@GetMapping("/deletesubCategory/{id}/{eid}")
	public String deleteCategory(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			System.out.println("ssss");
			PhysicalSubCategoryServices.deletePhysicalSubCategoryById(id);

			return "redirect:/PhysicalSubCategory/subcatagory-list/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@GetMapping("/editsubcategory/{id}/{eid}")
	public String EditBycategoryid(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalSubCategory obj = PhysicalSubCategoryServices.getPhysicalSubCategoryById(id);
			PhysicalCategory physicalCategory = PhysicalCategoryServices.getCategoryById(obj.getCategoryId());
			model.addAttribute("physicalSubCategory", obj);
			model.addAttribute("physicalCategory", physicalCategory);
			return "EditPhysicalSubCategory";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/santhosh")
	public String santhosh() {
		return "front-end-index";
	}

}
