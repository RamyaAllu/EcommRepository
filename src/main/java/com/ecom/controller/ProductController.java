package com.ecom.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.beans.Admin;
import com.ecom.beans.PhysicalCategory;
import com.ecom.beans.PhysicalProducts;
import com.ecom.beans.PhysicalSubCategory;
import com.ecom.beans.ProductImage;
import com.ecom.beans.Store;
import com.ecom.beans.Vendor;
import com.ecom.repository.AdminRepository;
import com.ecom.repository.ImageRepository;
import com.ecom.repository.PhysicalProductRepository;
import com.ecom.repository.PhysicalSubCategoryRepository;
import com.ecom.repository.StoreRepository;
import com.ecom.repository.VendorRepository;
import com.ecom.service.AdminService;
import com.ecom.service.ImageService;
import com.ecom.service.PhysicalCategoryService;
import com.ecom.service.PhysicalProductService;
import com.ecom.service.PhysicalSubCategoryService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	PhysicalSubCategoryRepository physicalSubCategoryRepository;
	@Autowired
	PhysicalProductRepository physicalproductrepository;
	@Autowired
	PhysicalProductService physicalproductservice;
	@Autowired
	ImageService imageService;
	@Autowired
	PhysicalCategoryService PhysicalCategoryServices;
	@Autowired
	PhysicalSubCategoryService PhysicalSubCategoryServices;
	@Autowired
	AdminService adminService;
	@Autowired
	VendorRepository vendorRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	StoreRepository storeRepository;

	@RequestMapping("/addproduct/{eid}")
	public String ProductDetailsCreation(Model model, PhysicalProducts productobject, @PathVariable("eid") int eid,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("employeeId", eid);
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);
		List<PhysicalCategory> categoryList = PhysicalCategoryServices.getAllCategory();
		List<Store> stores=storeRepository.findAll();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("productobject", productobject);
		model.addAttribute("stores", stores);
		return "add-product";
	}

	@RequestMapping("/productsphotos")
	public String photomethod(Model model, @ModelAttribute(value = "productobject") PhysicalProducts productobject,
			final HttpServletRequest request, @RequestParam("quatity") int quatity,
			@RequestParam("productModelNumber") String modelNumber) throws Exception {
		HttpSession session = request.getSession();
		int eid = (int) session.getAttribute("employeeId");
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);

		List<PhysicalCategory> categoryList = PhysicalCategoryServices.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		String getQry = request.getParameter("getQry");
		String catagoryName = request.getParameter("catagoryName");

		if (getQry != null && getQry.equals("getSubsCatagory") && catagoryName != null && catagoryName != "") {

			List<PhysicalSubCategory> subCatagoryList = physicalSubCategoryRepository
					.findPhysicalSubCategoryByCategory(catagoryName);

			model.addAttribute("subCatagoryList", subCatagoryList);
			
			List<Store> stores=storeRepository.findAll();
			List<Vendor> vendors = vendorRepository.findAll();
			model.addAttribute("list", vendors);
			model.addAttribute("stores", stores);
			return "add-product";

		}

		List<PhysicalSubCategory> subCatagoryList = PhysicalSubCategoryServices
				.findPhysicalSubCategoryByCategory(productobject.getProductCategory());
		List<PhysicalProducts> physicalProducts = physicalproductrepository.getPhysicalProductsForadding(modelNumber);
		if (physicalProducts.isEmpty()) {
			productobject.setIsactive('Y');
			byte[] array1 = new byte[7];
			new Random().nextBytes(array1);
			String generatedString = new String(array1);
			productobject.setProductId((int) Math.random());
			productobject.setProductCode(generatedString);
			productobject.setProductModelNumber(modelNumber);
			productobject.setIsactive('Y');
			productobject.setCreated(LocalDate.now());
			productobject.setCreatedby(eid);
			productobject.setUpdated(LocalDate.now());
			productobject.setUpdatedby(eid);
			physicalproductservice.addProduct(productobject);
		}
		List<PhysicalProducts> physicalProduct = physicalproductrepository.getPhysicalProductsForadding(modelNumber);
		if (physicalProduct.size() != 0) {
			for (int i = 0; i < quatity - 1; i++) {
				for (PhysicalProducts products : physicalProduct) {

					PhysicalProducts object1 = physicalproductrepository.getById(products.getProductId());
					PhysicalProducts productobject1 = new PhysicalProducts();
					productobject1.setProductModelNumber(object1.getProductModelNumber());
					productobject1.setProductName(object1.getProductName());
					productobject1.setProductCategory(object1.getProductCategory());
					productobject1.setProductSubCategory(object1.getProductSubCategory());
					productobject1.setProductCompany(object1.getProductCompany());
					productobject1.setProductDescription(object1.getProductDescription());
					productobject1.setProductDiscountPrice(object1.getProductDiscountPrice());
					productobject1.setProductMRPPrice(object1.getProductMRPPrice());
					productobject1.setProductId((int) Math.random() * 90);
					productobject1.setIsactive('N');
					byte[] array = new byte[7];
					new Random().nextBytes(array);
					String generatedStrings = new String(array);
					productobject1.setProductCode(generatedStrings);
					productobject1.setCreated(LocalDate.now());
					productobject1.setCreatedby(eid);
					productobject1.setUpdated(LocalDate.now());
					productobject1.setUpdatedby(eid);
					productobject1.setStoreName(object1.getStoreName());

					PhysicalProducts object = physicalproductservice.addProduct(productobject1);
					model.addAttribute("productobject", object);
				}
			}
		}

		model.addAttribute("subCatagoryList", subCatagoryList);
		model.addAttribute("physicalproductid", productobject.getProductId());
		System.out.println(productobject.getProductId());
		return "upload-view";
	}

	@RequestMapping(value = "/productimage/{id}/{eid}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(Model model, @RequestParam("files") MultipartFile file, HttpSession session,
			@PathVariable(value = "id") int productid, PhysicalProducts productobject,
			@RequestParam("videoFile") MultipartFile videoFile, @PathVariable("eid") int eid,
			HttpServletRequest request) throws Exception {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts object = physicalproductservice.getProductById(productid);
			List<PhysicalProducts> physicalproductmodel = physicalproductrepository
					.getPhysicalProductsByModelNumberForadding(object.getProductModelNumber());
			for (PhysicalProducts product : physicalproductmodel) {
				try {
					product.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
					product.setProductVideo(Base64.getEncoder().encodeToString(videoFile.getBytes()));
				} catch (Exception e) {
					e.printStackTrace();

				}

				product.setCreated(LocalDate.now());
				product.setUpdated(LocalDate.now());

				PhysicalProducts physicalproduct = physicalproductrepository.save(product);
				model.addAttribute("physicalproduct", physicalproduct);
			}
			return "add-mutliple-physical-product-images";
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("uploadphysicalproductimages/{id}/{eid}")

	public String uploadMultipleImages(Model model, @PathVariable(value = "id") int id,
			@RequestParam("files") MultipartFile[] file, HttpServletResponse response, @PathVariable("eid") int eid,
			HttpServletRequest request) throws Exception {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts physicalProducts = physicalproductservice.getProductById(id);
			PhysicalProducts physicalProducts2 = physicalproductrepository
					.getPhysicalProductsForaddingMultipleImages(physicalProducts.getProductModelNumber());
			for (MultipartFile f : file) {
				ProductImage image = new ProductImage();
				image.setProductId(physicalProducts2.getProductId());
				image.setProductCode(physicalProducts2.getProductCode());
				int Random = (int) (Math.random() * 90);
				image.setImageId(Random);
				try {
					image.setImage(Base64.getEncoder().encodeToString(f.getBytes()));
				} catch (Exception e) {
					e.printStackTrace();
				}

				imageService.addImage(image);
			}

			return "redirect:/product/productlist/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/productlist/{eid}")
	public String method(Model model, @PathVariable("eid") int eid) {
		
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			List<PhysicalProducts> object = physicalproductrepository.getActivePhysicalProducts();
			model.addAttribute("productlist", object);
			return "product-list";
		
	}

	@RequestMapping("/deletePhysicalProduct/{id}/{eid}")
	public String deletePhysicalProducts(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);

			PhysicalProducts physicalProducts = physicalproductrepository.getById(id);
			List<PhysicalProducts> physicalProductslist = physicalproductrepository
					.getPhysicalProductsByModelNumber(physicalProducts.getProductModelNumber());
			if (physicalProductslist.size() != 0) {
				for (PhysicalProducts products : physicalProductslist) {
					physicalproductrepository.deleteById(products.getProductId());
					break;
				}
			} else {
				physicalproductservice.deleteBydataId(id);
			}
			return "redirect:/product/productlist/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("/editPhysicalproducts/{id}/{eid}")
	public String EditPhysicalByProductId(Model model, @PathVariable("id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			HttpSession session = request.getSession();
			session.setAttribute("employeeId", eid);
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts products = physicalproductservice.getProductById(id);
			List<PhysicalCategory> categoryList = PhysicalCategoryServices.getAllCategory();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("products", products);
			return "edit-physical-product";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping(value = "/editimage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String editProductImage(Model model, @ModelAttribute(value = "products") PhysicalProducts physicalProducts,
			final HttpServletRequest request, @RequestParam("quatity") int quatity, @RequestParam("Size") String Size,
			@RequestParam("productModelNumber") String modelNumber) throws Exception {
		HttpSession session = request.getSession();
		int eid = (int) session.getAttribute("employeeId");
		Admin admin = adminRepository.getById(eid);
		model.addAttribute("admin", admin);

		List<PhysicalCategory> categoryList = PhysicalCategoryServices.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		String getQry = request.getParameter("getQry");
		String catagoryName = request.getParameter("catagoryName");

		if (getQry != null && getQry.equals("getSubsCatagory") && catagoryName != null && catagoryName != "") {

			List<PhysicalSubCategory> subCatagoryList = PhysicalSubCategoryServices
					.findPhysicalSubCategoryByCategory(catagoryName);
			model.addAttribute("subCatagoryList", subCatagoryList);
			List<Vendor> vendors = vendorRepository.findAll();
			model.addAttribute("list", vendors);
			return "edit-physical-product";

		}

		physicalProducts.setIsactive('Y');
		PhysicalProducts product = physicalproductservice.addProduct(physicalProducts);
		model.addAttribute("product", product);
		return "edit-physical-product-image";

	}

	@RequestMapping(value = "/edit/{id}/{eid}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updateimage(Model model, @RequestParam("files") MultipartFile file, HttpSession session,
			@PathVariable(value = "id") int productid, PhysicalProducts productobject, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts object = physicalproductservice.getProductById(productid);
			System.out.println(object);
			if (file.getOriginalFilename() == "") {
				object.setProductImage(object.getProductImage());
			} else {
				try {
					object.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
				} catch (Exception e) {
					e.printStackTrace();

				}

			}
			object.setCreated(LocalDate.now());
			object.setUpdated(LocalDate.now());
			PhysicalProducts physicalproduct = physicalproductrepository.save(object);
			model.addAttribute("physicalproduct", physicalproduct);
			ArrayList<ProductImage> productimage = imageService
					.getPhysicalProductImages(physicalproduct.getProductCode());
			model.addAttribute("productimage", productimage);

			return "redirect:/product/productlist/" + eid;
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("/addmultipleimages/{id}/{eid}")
	public String addMultipleImages(Model model, @PathVariable(value = "id") int id, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts physicalProducts = physicalproductservice.getProductById(id);
			model.addAttribute("physicalProducts", physicalProducts);

			return "add-mutliple-physical-product-images";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/editmultipleimages/{id}/{eid}")
	public String editMultipleImage(Model model, @RequestParam("files") MultipartFile file, HttpSession session,
			@PathVariable(value = "id") int productid, PhysicalProducts productobject, @PathVariable("eid") int eid,
			HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts object = physicalproductservice.getProductById(productid);

			try {
				object.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();

			}

			object.setCreated(LocalDate.now());
			object.setUpdated(LocalDate.now());
			PhysicalProducts physicalproduct = physicalproductrepository.save(object);
			model.addAttribute("physicalproduct", physicalproduct);
			List<ProductImage> productimage = imageRepository
					.getphysicalproductimagesbyId(physicalproduct.getProductCode());
			model.addAttribute("productimage", productimage);
			return "edit-multiple-images";
		} else {
			return "redirect:/emp/back-end";
		}

	}

	@RequestMapping("/physicalproductdetails/{id}/{eid}")
	public String getdetails(@PathVariable(value = "id") int productid, Model model, HttpSession session,
			@PathVariable("eid") int eid, HttpServletRequest request) {
		HttpSession adminSession = request.getSession();
		if (adminSession.getAttribute("adminSession") != null) {
			Admin admin = adminRepository.getById(eid);
			model.addAttribute("admin", admin);
			PhysicalProducts product = physicalproductservice.getProductById(productid);
			List<ProductImage> productimage = imageRepository.getphysicalproductimagesbyId(product.getProductCode());
			model.addAttribute("productImages", productimage);
			model.addAttribute("product", product);
			List<PhysicalProducts> physicalProductsbymodelNumber = physicalproductrepository
					.getPhysicalProductsByModelNumberForQuantity(product.getProductModelNumber());
			System.out.println(product.getProductId());

			model.addAttribute("quantity", physicalProductsbymodelNumber.size());
			return "product-detail";
		} else {
			return "redirect:/emp/back-end";
		}
	}

	@RequestMapping("/compare-product/{id}")
	public String compareProductById(Model model, @PathVariable("id") int id) {

		PhysicalProducts physicalProducts = physicalproductservice.getProductById(id);

		model.addAttribute("physicalProducts", physicalProducts);

		List<PhysicalProducts> physicalProducts2 = physicalproductrepository.compareProducts(
				physicalProducts.getProductId(), physicalProducts.getProductCompany(),
				physicalProducts.getProductMRPPrice(),physicalProducts.getProductSubCategory());
		model.addAttribute("physicalProducts2", physicalProducts2);

		return "compare-product";
	}
}
