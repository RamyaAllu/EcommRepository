package com.ecom.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.beans.Admin;
import com.ecom.beans.Category;
import com.ecom.beans.DigitalProducts;
import com.ecom.beans.PhysicalProducts;
import com.ecom.beans.SubCategory;
import com.ecom.beans.Vendor;
import com.ecom.repository.AdminRepository;
import com.ecom.repository.DigitalProductsRepository;
import com.ecom.repository.SubCategoryRepository;
import com.ecom.repository.VendorRepository;
import com.ecom.service.CategoryService;
import com.ecom.service.DigitalProductsService;
import com.ecom.service.ImageService;
import com.ecom.service.SubcategoryService;

@Controller
@RequestMapping("/Dgproducts")
public class DigitalProductController {
	@Autowired
	DigitalProductsService digitalproductservice;

	@Autowired
	DigitalProductsRepository digirepository;
	@Autowired
	VendorRepository VendorRepository;
	@Autowired
	CategoryService CategoryService;

	@Autowired
	SubcategoryService SubcatagoryService;

	@Autowired
	ImageService ImageService;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	SubCategoryRepository subCategoryRepository;

	@GetMapping(value = "/create-product/{eid}")

	public String getProducts(Model model, @PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			HttpSession session = request.getSession();
			session.setAttribute("employeeId", admin.getEmployeeId());

			model.addAttribute("objDigProduct", new DigitalProducts());
			List<Category> categoryList = CategoryService.getAllCategory();
			model.addAttribute("categoryList", categoryList);
			// model.addAttribute("digitalproductid", objDigProduct.getProductId());

			List<Vendor> list = VendorRepository.finddigitalproductNames();
			model.addAttribute("list", list);
			return "add-digital-product";
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("/savedigitalproducts")
	public String saveDigitalProducts(Model model,
			@ModelAttribute(value = "objDigProduct") DigitalProducts objDigProduct, final HttpServletRequest request)
			throws Exception {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			HttpSession session = request.getSession();
			int eid = (int) session.getAttribute("employeeId");
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			List<Category> categoryList = CategoryService.getAllCategory();
			model.addAttribute("categoryList", categoryList);
			String getQry = request.getParameter("getQry");
			String categoryName = request.getParameter("categoryName");
			System.out.println("getQry::::" + getQry);
			System.out.println("catagoryId::::" + categoryName);
			if (getQry != null && getQry.equals("getSubsCatagory") && categoryName != null && categoryName != "") {
				List<SubCategory> subCatagoryList = subCategoryRepository.findSubCategoryByCategory(categoryName);
				model.addAttribute("subCatagoryList", subCatagoryList);
				List<Vendor> list = VendorRepository.finddigitalproductNames();
				model.addAttribute("list", list);
				return "add-digital-product";

			}

			List<SubCategory> subCatagoryList = subCategoryRepository
					.findSubCategoryByCategory(objDigProduct.getProductCategory());

			objDigProduct.setIsActive('Y');

			byte[] array1 = new byte[7];
			new Random().nextBytes(array1);
			String generatedString = new String(array1);
			objDigProduct.setProductCode(generatedString);
			DigitalProducts digitalobject = digitalproductservice.addProduct(objDigProduct);
			model.addAttribute("digitalproduct", digitalobject);
			model.addAttribute("successMsg", "Product added successfully");
			return "digital-File";
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping(value = "/productimage/{id}/{eid}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(Model model, @RequestParam("files") MultipartFile santhosh, HttpSession session,
			@PathVariable(value = "id") int productid, PhysicalProducts productobject, @PathVariable("eid") int eid,
			HttpServletRequest request) throws Exception {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			DigitalProducts object = digitalproductservice.getProductById(productid);
			System.out.println(object);
			StringBuilder filejoin = new StringBuilder();

			String uploadDir = "C:\\Users\\Aakash\\Desktop\\santosh_job_practice\\Ecommerces\\src\\main\\resources\\static\\Digitaluploads\\"
					+ productid + "\\";
			filejoin.append(santhosh.getOriginalFilename() + ",");
			String fileName = StringUtils.cleanPath(santhosh.getOriginalFilename());
			
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputstream = santhosh.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputstream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ioe) {
				throw new IOException("Could not save image file: " + fileName, ioe);
			}
			object.setFile(santhosh.getBytes());
			object.setFileName(santhosh.getOriginalFilename());
			object.setCreated(LocalDate.now());
			object.setUpdated(LocalDate.now());
			object.setIsActive('Y');

			DigitalProducts physicalproduct = digirepository.save(object);
			model.addAttribute("digitalproduct", physicalproduct);

			return "upload-digital-pics";
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("uploadDigitalproductimages/{id}/{eid}")

	public String uploadMultipleImages(Model model, @PathVariable(value = "id") int id,
			@RequestParam("files") MultipartFile file, DigitalProducts digitalProducts, HttpServletResponse response,
			@PathVariable("eid") int eid, HttpServletRequest request) throws Exception {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			DigitalProducts dProducts = digitalproductservice.getProductById(id);

			dProducts.setIsActive('Y');

			try {
				dProducts.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			digitalproductservice.addProduct(dProducts);

			return "redirect:/Dgproducts/showlist/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@GetMapping("/showlist/{eid}")
	public String ShowDigitalproductList(Model model, @PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			List<DigitalProducts> digitalproductlist = digirepository.findAll();
			model.addAttribute("digitalproductlist", digitalproductlist);

			return "digital-product-list";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/search/{eid}")
	public String categoryAdmin(DigitalProducts digitalProducts, Model model, String keyword,
			@PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			if (keyword != null) {

				List<DigitalProducts> digitalproductlist = digitalproductservice.getDigitalByName(keyword);
				model.addAttribute("digitalproductlist", digitalproductlist);

				return "digital-product-list";
			} else {
				List<DigitalProducts> digitalproductlist = digitalproductservice.getAllDigitalProductsData();
				model.addAttribute("digitalproductlist", digitalproductlist);
				return "digital-product-list";
			}
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@GetMapping("/deleteDigitalProduct/{id}/{eid}")
	public String deleteDigitalProducts(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			digitalproductservice.deleteBydataId(id);

			return "redirect:/Dgproducts/showlist/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@GetMapping("/editproducts/{id}/{eid}")
	public String EditByProductId(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			HttpSession session = request.getSession();
			session.setAttribute("employeeId", admin.getEmployeeId());
			DigitalProducts digitalproductbyid = digitalproductservice.getDetails(id);
			model.addAttribute("digitalproductbyid", digitalproductbyid);
			List<Category> categoryList = CategoryService.getAllCategory();
			model.addAttribute("categoryList", categoryList);

			List<Vendor> list = VendorRepository.finddigitalproductNames();
			model.addAttribute("list", list);

			return "edit-digital-product";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/saveEditDigitalProduct")
	public String saveEditDigitalProduct(Model model,
			@ModelAttribute("digitalproductbyid") DigitalProducts objDigProduct, final HttpServletRequest request)
			throws Exception {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			HttpSession session = request.getSession();
			int eid = (int) session.getAttribute("employeeId");
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			List<Category> categoryList = CategoryService.getAllCategory();
			model.addAttribute("categoryList", categoryList);
			String getQry = request.getParameter("getQry");
			String categoryName = request.getParameter("categoryName");
			System.out.println("getQry::::" + getQry);
			System.out.println("catagoryId::::" + categoryName);
			if (getQry != null && getQry.equals("getSubsCatagory") && categoryName != null && categoryName != "") {
				List<SubCategory> subCatagoryList = subCategoryRepository.findSubCategoryByCategory(categoryName);
				model.addAttribute("subCatagoryList", subCatagoryList);
				List<Vendor> list = VendorRepository.finddigitalproductNames();
				model.addAttribute("list", list);
				return "edit-digital-product";

			}

			List<SubCategory> subCatagoryList = subCategoryRepository
					.findSubCategoryByCategory(objDigProduct.getProductCategory());
			model.addAttribute("subCatagoryList", subCatagoryList);

			model.addAttribute("digitalproductid", objDigProduct.getProductId());

			objDigProduct.setIsActive('Y');

			byte[] array1 = new byte[7];
			new Random().nextBytes(array1);
			String generatedString = new String(array1);
			objDigProduct.setProductCode(generatedString);
			DigitalProducts digitalobject = digitalproductservice.addProduct(objDigProduct);
			model.addAttribute("digitalproduct", digitalobject);
			model.addAttribute("successMsg", "Product added successfully");
			return "digital-File";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@GetMapping(value = "/getSubCatagory", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SubCategory> getSubCatagory(@RequestParam String catagoryName) {

		List<SubCategory> list = subCategoryRepository.findSubCategoryByCategory(catagoryName);

		System.out.println("catagoryId " + catagoryName);

		return list;
	}

	@GetMapping("/getimages/{pid}/{eid}")
	public String Image(Model model, @PathVariable("pid") int pid, DigitalProducts obj, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {

			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			System.out.println("Hiiii" + pid);
			DigitalProducts product = digitalproductservice.getProductById(pid);
			model.addAttribute("product", product.getProductId());

			return "viewpdf";
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("/previewDigitalFile/{id}/{eid}")
	public ResponseEntity<byte[]> previewDigitalFile(@PathVariable("id")int id,@PathVariable("eid")int eid) { 
		DigitalProducts digitalProducts=digirepository.getById(id);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("content-disposition", "inline;filename=" + digitalProducts.getFileName());
		headers.setContentDispositionFormData(digitalProducts.getFileName(), digitalProducts.getFileName());
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(digitalProducts.getFile(), headers, HttpStatus.OK);
		 return response;
	}
	@RequestMapping("/downloadFile/{id}/{eid}")
	public void downloadFile(@PathVariable("id")int id,@PathVariable("eid")int eid,HttpServletResponse response) throws Exception {
		DigitalProducts digitalProducts=digirepository.getById(id);
		response.setContentType("application/octet");
		response.setHeader("content-disposition", "attachment; filename=" +digitalProducts.getFileName());
		ServletOutputStream outputStream =response.getOutputStream();
		outputStream.write(digitalProducts.getFile());
		outputStream.close();
		
	}
	
}
