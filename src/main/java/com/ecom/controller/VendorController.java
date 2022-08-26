package com.ecom.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.beans.Admin;
import com.ecom.beans.PhysicalProducts;
import com.ecom.beans.ProductImage;
import com.ecom.beans.Vendor;
import com.ecom.beans.VendorDataList;
import com.ecom.repository.AdminRepository;
import com.ecom.repository.PhysicalProductRepository;
import com.ecom.repository.VendorDataListRepository;
import com.ecom.repository.VendorRepository;
import com.ecom.service.AdminService;
import com.ecom.service.VendorService;
import com.google.zxing.WriterException;

@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;
	@Autowired
	PhysicalProductRepository physicalproductrepository;

	@Autowired
	AdminService adminService;

	@Autowired
	VendorRepository vendorRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	VendorDataListRepository vendorDataListRepository;

	@RequestMapping(value = "/create-vendor/{eid}")
	public String getVendors(Model model, HttpSession session, @PathVariable("eid") int eid) {
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);

		Vendor vendor = new Vendor();

		model.addAttribute("vendor", vendor);
		return "vendor-Register";

	}

	@RequestMapping(value = "/saveVendor/{eid}", method = RequestMethod.POST)
	public String saveVendor(Model model, @ModelAttribute(value = "vendorObj") Vendor vendor, HttpSession session,
			@PathVariable("eid") int eid) {

		Vendor adminObj = vendorService.findByEmailId(vendor.getEmail());

		if (adminObj != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			model.addAttribute("vendor", vendor);
			model.addAttribute("msg", "User is already exists");

			return "vendor-Register";
		} else {

			vendor.setIsActive('y');
			vendor.setCreated(LocalDate.now());
			vendor.setUpdated(LocalDate.now());

			vendorService.addVendorDetails(vendor);
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			List<Vendor> list = vendorService.getAllVendors();
			model.addAttribute("vendorlist", list);
			return "vendor-list";

		}

	}

	@GetMapping(value = "/vendor-list/{eid}")
	public String getAllVendors(Model model, HttpSession session, @PathVariable("eid") int eid) {
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);
		List<Vendor> list = vendorService.getAllVendors();

		model.addAttribute("vendorlist", list);

		return "vendor-list";
	}

	@GetMapping("/delete-vendor/{id}/{eid}")
	public String deleteVendor(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid) {
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);

		Vendor vendor = vendorService.getVendorById(id);
		vendor.setIsActive('N');
		vendorService.addVendor(vendor);
		return "redirect:/vendor/vendor-list/" + eid;

	}

	@GetMapping("/edit-vendor/{id}/{eid}")
	public String getVendorById(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid) {
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);
		Vendor vendorObj = vendorService.getVendorById(id);
		model.addAttribute("vendor", vendorObj);
		return "edit-vendor";
	}

	@RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
	public ModelAndView addEmployee(Model model, Vendor vendor, HttpSession session) {

		Vendor adminObj = vendorService.findByEmailId(vendor.getEmail());

		if (adminObj != null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/vendor/vendor-Register");
			String errorMessage = "User Already Exists";
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.addObject("employee", vendor);

			return modelAndView;
		} else {

			vendor.setIsActive('y');
			vendor.setCreated(LocalDate.now());
			vendor.setUpdated(LocalDate.now());
			ModelAndView modelAndView = new ModelAndView("Vendor-Register");
			Vendor adminobject = vendorService.addVendorDetails(vendor);
			session.setAttribute("name", vendor.getCompanyName());
			session.setAttribute("role", vendor.getVendorType());

			model.addAttribute("admin", adminobject);
			return modelAndView;

		}
	}

	@RequestMapping(value = "/vendor-login")
	public String getVendorIndex(Model model, Vendor vendorobj,@RequestParam(required = false) String errorMessage) {
		if(!StringUtils.isEmpty(errorMessage)) {
	        model.addAttribute("errorMessage", errorMessage);
	    }

		model.addAttribute("vendorobj", vendorobj);
		return "vendor-login";

	}

	@RequestMapping(value = "/newvendorlogin", method = RequestMethod.POST)
	
	public String newvendorlogin(Model model, Vendor vendor, HttpServletRequest request, HttpSession session,RedirectAttributes redirectAttributes) {

		Vendor vendorobject = vendorService.getVendorDetails(vendor.getEmail(), vendor.getPassword());
		if (vendorobject != null) {

			model.addAttribute("vendor", vendorobject);

			return "redirect:/vendor/vendordata/" + vendorobject.getVendorId();
		} else {

			String errorMessage = "Invalid Credentials!";
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

			return "redirect:/vendor/vendor-login";
		}
	}

	@RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public String uploadExcelsheet(Model model, @PathVariable(value = "id") int id, Vendor vendorobj,
			@RequestParam("files") MultipartFile vendor, HttpSession session) throws IOException, WriterException {
		StringBuilder filejoin = new StringBuilder();

		Vendor obj = vendorService.getVendorById(id);
		String uploadDir = "C:\\Users\\Aakash\\Desktop\\santosh_job_practice\\Ecommerces\\src\\main\\resources\\static\\PhysicalProductFiles\\"
				+ id + "\\";

		filejoin.append(vendor.getOriginalFilename() + ",");
		String fileName = StringUtils.cleanPath(vendor.getOriginalFilename());
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputstream = vendor.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputstream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}

		VendorDataList vendorDataObject = new VendorDataList();
		vendorDataObject.setVendorDataId((int) Math.random());
		vendorDataObject.setVendorId(id);
		vendorDataObject.setFile(vendor.getOriginalFilename());
		vendorDataListRepository.save(vendorDataObject);
		obj.setCreated(LocalDate.now());
		obj.setUpdated(LocalDate.now());

		Vendor physicalproduct = vendorRepository.save(obj);
		model.addAttribute("vendorobj", physicalproduct);

		return "redirect:/vendor/vendordata/" + physicalproduct.getVendorId();
	}

	@RequestMapping("/login/{id}")
	public String add(@PathVariable("id") int id, Model model, HttpSession session) {

		Vendor vendorobject = vendorService.getVendorById(id);
		model.addAttribute("vendor", vendorobject);
		return "excelsheet-upload";
	}

	@RequestMapping("/vendordata/{vid}")
	public String venderData(@PathVariable("vid") int vid, Model model) {
		List<VendorDataList> datalist = vendorDataListRepository.vendordatalist(vid);
		model.addAttribute("vendordatalist", datalist);
		model.addAttribute("vendor", vendorRepository.getById(vid));
		return "VendorDataList";
	}

	@RequestMapping("/vendorExcelupload/{id}")
	public String VendorUplodingForm(Model model, @PathVariable(value = "id") int id) {
		Vendor vendorobj = vendorService.getVendorById(id);
		model.addAttribute("vendorobj", vendorobj);
		return "excelUpload";
	}

	@RequestMapping("/delete-file/{vid}/{vdid}")
	public String vendorDeleteFile(@PathVariable("vid") int vid, @PathVariable("vdid") int vdid) {
		vendorDataListRepository.deleteById(vdid);
		return "redirect:/vendor/vendordata/" + vid;
	}

	@RequestMapping("/preview-file/{vid}/{vdid}")
	public String vendorPreviewFile(@PathVariable("vid") int vid, @PathVariable("vdid") int vdid) throws IOException {

		VendorDataList vendorData = vendorDataListRepository.getById(vdid);
		String uploadDir = "C:\\Users\\Aakash\\Desktop\\santosh_job_practice\\MergeEcomm\\src\\main\\resources\\static\\PhysicalProductFiles\\"
				+ vdid + "\\";
		File excel = new File(uploadDir + vendorData.getFile());
		System.out.println(excel.toString());

		try {
			Runtime.getRuntime().exec("cmd /c start " + excel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/vendor/vendordata/" + vid;
	}
	@RequestMapping("/view-upload-files/{vid}/{eid}")
	public String viewUploadFiles(@PathVariable("vid")int vid,@PathVariable("eid")int eid,Model model) {
		List<VendorDataList> datalist = vendorDataListRepository.vendordatalist(vid);
		model.addAttribute("vendorlist", datalist);
		model.addAttribute("vendor", vendorRepository.getById(vid));
		Admin admin=adminRepository.getById(eid);
		model.addAttribute("admin", admin);
		return "view-upload-files";
	}
	@RequestMapping("/add-product-list/{vid}/{vdid}/{eid}")
	public String addProductList(@PathVariable("vid")int vid,@PathVariable("vdid")int vdid,@PathVariable("eid")int eid) throws Exception{
		VendorDataList vendorData = vendorDataListRepository.getById(vdid);
		String uploadDir = "C:\\Users\\Aakash\\Desktop\\santosh_job_practice\\MergeEcomm\\src\\main\\resources\\static\\PhysicalProductFiles\\"
				+ vdid + "\\";
		FileInputStream excelFile = new FileInputStream(uploadDir + vendorData.getFile());
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = workbook.getSheet("Users");
			int rows = sheet.getLastRowNum();
			for(int r = 1; r <= rows; r++) {
				XSSFRow row = sheet.getRow(r);
				PhysicalProducts physicalProducts=new PhysicalProducts();
				physicalProducts.setIsactive('Y');
				physicalProducts.setProductCategory(row.getCell(1).getStringCellValue());
				physicalProducts.setProductCode(row.getCell(2).getStringCellValue());
				physicalProducts.setProductDescription(row.getCell(3).getStringCellValue());
				physicalProducts.setProductDetails(row.getCell(4).getStringCellValue());
				physicalProducts.setProductDiscountPrice(Double.valueOf(row.getCell(5).getStringCellValue()));
				physicalProducts.setProductId((int)Math.random());
				physicalProducts.setProductImage(row.getCell(7).getStringCellValue());
				physicalProducts.setProductModelNumber(row.getCell(8).getStringCellValue());
				physicalProducts.setProductMRPPrice(Double.valueOf(row.getCell(9).getStringCellValue()));
				physicalProducts.setProductName(row.getCell(10).getStringCellValue());
				physicalProducts.setProductShippingInformation(row.getCell(11).getStringCellValue());
				physicalProducts.setProductSize(row.getCell(12).getStringCellValue());
				physicalProducts.setProductSpecification(row.getCell(13).getStringCellValue());
				physicalProducts.setProductSubCategory(row.getCell(14).getStringCellValue());
				physicalProducts.setProductVideo(row.getCell(15).getStringCellValue());
				physicalProducts.setQRcode(row.getCell(16).getStringCellValue());
				physicalProducts.setProductCompany(row.getCell(17).getStringCellValue());
				physicalProducts.setProductRating(row.getCell(18).getStringCellValue());
				physicalProducts.setCreated(LocalDate.now());
				physicalProducts.setCreatedby(eid);
				physicalProducts.setUpdated(LocalDate.now());
				physicalProducts.setUpdatedby(eid);
				
				physicalproductrepository.save(physicalProducts);
				for(int i=0;i<row.getCell(19).getNumericCellValue()-1;i++) {
					PhysicalProducts physicalProducts1=new PhysicalProducts();
					physicalProducts1.setIsactive('N');
					physicalProducts1.setProductCategory(row.getCell(1).getStringCellValue());
					physicalProducts1.setProductCode(row.getCell(2).getStringCellValue());
					physicalProducts1.setProductDescription(row.getCell(3).getStringCellValue());
					physicalProducts1.setProductDetails(row.getCell(4).getStringCellValue());
					physicalProducts1.setProductDiscountPrice(Double.valueOf(row.getCell(5).getStringCellValue()));
					physicalProducts1.setProductId((int)Math.random());
					physicalProducts1.setProductImage(row.getCell(7).getStringCellValue());
					physicalProducts1.setProductModelNumber(row.getCell(8).getStringCellValue());
					physicalProducts1.setProductMRPPrice(Double.valueOf(row.getCell(9).getStringCellValue()));
					physicalProducts1.setProductName(row.getCell(10).getStringCellValue());
					physicalProducts1.setProductShippingInformation(row.getCell(11).getStringCellValue());
					physicalProducts1.setProductSize(row.getCell(12).getStringCellValue());
					physicalProducts1.setProductSpecification(row.getCell(13).getStringCellValue());
					physicalProducts1.setProductSubCategory(row.getCell(14).getStringCellValue());
					physicalProducts1.setProductVideo(row.getCell(15).getStringCellValue());
					physicalProducts1.setQRcode(row.getCell(16).getStringCellValue());
					physicalProducts1.setProductCompany(row.getCell(17).getStringCellValue());
					physicalProducts1.setProductRating(row.getCell(18).getStringCellValue());
					physicalProducts1.setCreated(LocalDate.now());
					physicalProducts1.setCreatedby(eid);
					physicalProducts1.setUpdated(LocalDate.now());
					physicalProducts1.setUpdatedby(eid);
					physicalproductrepository.save(physicalProducts1);
				}
			}
			
		
		
		return "redirect:/product/productlist/" + eid;
	}
	@RequestMapping("/preview-file-admin/{vid}/{vdid}/{eid}")
	public String vendorPreviewFileFromAdmin(@PathVariable("vid") int vid, @PathVariable("vdid") int vdid,@PathVariable("eid")int eid) throws IOException {

		VendorDataList vendorData = vendorDataListRepository.getById(vdid);
		String uploadDir = "C:\\Users\\Aakash\\Desktop\\santosh_job_practice\\MergeEcomm\\src\\main\\resources\\static\\PhysicalProductFiles\\"
				+ vdid + "\\";
		File excel = new File(uploadDir + vendorData.getFile());
		System.out.println(excel.toString());

		try {
			Runtime.getRuntime().exec("cmd /c start " + excel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/vendor/view-upload-files/" + vid+"/"+eid;
	}

}
