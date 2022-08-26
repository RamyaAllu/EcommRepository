package com.ecom.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.beans.BilingDetails;
import com.ecom.beans.Category;
import com.ecom.beans.Customer;
import com.ecom.beans.CustomerAddress;
import com.ecom.beans.CustomerCart;
import com.ecom.beans.CustomerOrder;
import com.ecom.beans.DigitalProducts;
import com.ecom.beans.OrderByCustomer;
import com.ecom.beans.PhysicalCategory;
import com.ecom.beans.PhysicalProducts;
import com.ecom.beans.PhysicalReview;
import com.ecom.beans.PhysicalSubCategory;
import com.ecom.beans.ProductImage;
import com.ecom.beans.SubCategory;
import com.ecom.repository.BillingRepository;
import com.ecom.repository.CustomerAddressRepository;
import com.ecom.repository.CustomerCartRepository;
import com.ecom.repository.CustomerOrderRepository;
import com.ecom.repository.CustomerRepository;
import com.ecom.repository.DigitalProductsRepository;
import com.ecom.repository.OrderByCustomerRepository;
import com.ecom.repository.PhysicalCategoryRepository;
import com.ecom.repository.PhysicalProductRepository;
import com.ecom.repository.PhysicalReviewRepository;
import com.ecom.repository.PhysicalSubCategoryRepository;
import com.ecom.repository.SubCategoryRepository;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerAddressService;
import com.ecom.service.CustomerCartService;
import com.ecom.service.CustomerService;
import com.ecom.service.EmailSenderService;
import com.ecom.service.ImageService;
import com.ecom.service.PhysicalCategoryService;
import com.ecom.service.PhysicalProductService;
import com.ecom.service.PhysicalSubCategoryService;
import com.ecom.service.SubcategoryService;

@Controller
@RequestMapping("/")
public class CustomerController {
	@Autowired
	CustomerService customerservice;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerAddressService customeraddressservice;
	@Autowired
	PhysicalProductService physicalProductService;
	@Autowired
	PhysicalProductRepository physicalProductRepository;
	@Autowired
	ImageService imageService;
	@Autowired
	EmailSenderService emailSenderService;
	@Autowired
	CustomerAddressRepository customerAddressRepository;
	@Autowired
	CustomerOrderRepository customerOrderRepository;
	@Autowired
	CustomerCartRepository customerCartRepository;
	@Autowired
	OrderByCustomerRepository orderByCustomerRepository;
	@Autowired
	PhysicalCategoryService PhysicalCategoryService;

	@Autowired
	PhysicalSubCategoryService PhysicalSubCategoryService;

	@Autowired
	PhysicalSubCategoryRepository PhysicalSubCategoryRepository;

	@Autowired
	CategoryService CategoryService;
	@Autowired
	PhysicalCategoryRepository PhysicalCategoryRepository;
	@Autowired
	SubCategoryRepository SubCategoryRepository;

	@Autowired
	SubcategoryService SubCategoryService;
	@Autowired
	DigitalProductsRepository DigitalProductRepostory;

	@Autowired
	SubCategoryRepository SubcategoryRepository;
	@Autowired
	BillingRepository billingRepository;
	@Autowired
	DigitalProductsRepository digitalProductsRepository;
	@Autowired
	PhysicalReviewRepository physicalReviewRepository;
	@Autowired
	CustomerCartService customerCartService;

	@RequestMapping("/")
	public String getMethod() {
		return "index";
	}

	@RequestMapping("/front-end")

	public String getLogin(Model model) {

		List<PhysicalProducts> products = physicalProductRepository.findLatestProducts();

		model.addAttribute("product", products);

		List<PhysicalCategory> catagorylist = PhysicalCategoryService.getAllCategory();
		model.addAttribute("catagorylist", catagorylist);

		List<PhysicalSubCategory> subcatagorylist = PhysicalSubCategoryService.list();
		model.addAttribute("subcatagorylist", subcatagorylist);

		List<Category> Digitalcatagorylist = CategoryService.getAllCategory();
		model.addAttribute("Digitalcatagorylist", Digitalcatagorylist);
		List<DigitalProducts> digitalProductsList = digitalProductsRepository.findAll();
		model.addAttribute("digitalProductsList", digitalProductsList);

		return "front-end-index";
	}

	@RequestMapping("/frontendproductdetails/{id}")
	public String getdetails(@PathVariable(value = "id") int productid, Model model) {
		PhysicalProducts product = physicalProductService.getProductById(productid);
		ArrayList<ProductImage> productImages = imageService.getPhysicalProductImages(product.getProductCode());
		model.addAttribute("productImages", productImages);
		model.addAttribute("product", product);
		List<PhysicalReview> list = physicalReviewRepository.getPhysicalReviewByProductId(productid);

		model.addAttribute("Review", list);
		return "front-end-product-detail";
	}

	@RequestMapping("/front-end-index/{id}")
	public String categoriesView(Model model, @PathVariable("id") int catagoryId) {

		PhysicalCategory physicalCategory = PhysicalCategoryRepository.getById(catagoryId);
		List<PhysicalCategory> catagorylist = PhysicalCategoryService.getAllCategory();
		model.addAttribute("catagorylist", catagorylist);
		List<PhysicalSubCategory> subcatagorylist = PhysicalSubCategoryService
				.findPhysicalSubCategoryByCategory(physicalCategory.getCategoryName());
		model.addAttribute("subcatagorylist", subcatagorylist);
		List<DigitalProducts> products = DigitalProductRepostory.findLatestProducts(LocalDate.now());
		model.addAttribute("product", products);

		return "front-ends-index";

	}

	@RequestMapping("/viewSubcategory/{id}")
	public String viewDigitalSubcategories(Model model, @PathVariable("id") int catagoryId) {
		List<SubCategory> Digitalsubcatagorylist = SubcategoryRepository.findSubCategoryByCategory(catagoryId);
		model.addAttribute("Digitalsubcatagorylist", Digitalsubcatagorylist);

		return "viewDigitalSubcategorylist";
	}

	@RequestMapping("/view/{id}")
	public String CategoryProducts(Model model, @PathVariable("id") int subcatagoryId) {
		PhysicalSubCategory physicalSubCategory = PhysicalSubCategoryRepository.getById(subcatagoryId);
		List<PhysicalProducts> products = physicalProductRepository
				.findProductsBySubcategories(physicalSubCategory.getSubCategoryName());
		model.addAttribute("products", products);
		return "categoryproducts";

	}

	@RequestMapping("/Digitalview/{id}")
	public String DigitalCategoryProducts(Model model, @PathVariable("id") int subcatagoryId) {
		SubCategory subCategory = SubCategoryRepository.getById(subcatagoryId);
		List<DigitalProducts> products = DigitalProductRepostory
				.findProductsBySubcategories(subCategory.getSubCategoryName());
		model.addAttribute("products", products);
		return "Digitalcategoryproducts";

	}

	@RequestMapping("/loginpage")
	public String addLogin(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			HttpServletRequest request, @RequestParam(required = false) String message) {
		if (!StringUtils.isEmpty(message)) {
			model.addAttribute("msg2", message);
		}
		model.addAttribute("customerObject", customerObject);

		return "front-end-login";
	}

	@RequestMapping("/registration")
	public String addRegistration(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			@RequestParam(required = false) String message, @RequestParam(required = false) Customer customer) {
		if (!StringUtils.isEmpty(message)) {
			model.addAttribute("message", message);
		}
		if (!ObjectUtils.isEmpty(customer)) {
			model.addAttribute("customerObject", customer);
		} else {
			model.addAttribute("customerObject", customerObject);
		}
		return "front-end-register";
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public String saveRegistration(Model model, HttpServletRequest request,
			@ModelAttribute(value = "customerObject") Customer customerObject, RedirectAttributes redirectAttributes) {
		System.out.println("I am Save User");

		if (customerObject.getConfirmPassword().equals(customerObject.getPassword())) {
			Customer customer = customerRepository.findByEmail(customerObject.getEmail());
			if (customer == null) {
				customerObject.setIsActive('Y');
				Customer object = customerservice.addCustomer(customerObject);
				HttpSession session = request.getSession();
				session.setAttribute("customerid", object.getCustomerId());
				session.setAttribute("fullname", object.getUserName());
				session.setAttribute("email", object.getEmail());
				System.out.println(customerObject.getUserName());
				return "front-end-login";
			} else {
				redirectAttributes.addFlashAttribute("message", "User already exists");
				return "redirect:/loginpage";
			}

		} else {
			redirectAttributes.addFlashAttribute("message", "password and confirm password should be same ");
			redirectAttributes.addFlashAttribute(customerObject);
			return "redirect:/registration";
		}
	}

	@GetMapping("/customerlogin")
	public String loginValidation(Model model, Customer customerObject, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		System.out.println(customerObject.getEmail());
		System.out.println(customerObject.getPassword());
		System.out.println(customerObject.getCustomerId());
		Customer signinObj = customerservice.getCustomer(customerObject.getEmail(), customerObject.getPassword());
		if (signinObj != null) {
			if (signinObj.getIsActive() == 'Y') {
				Customer object = customerservice.addCustomer(signinObj);
				Customer customer = customerservice.getCustomerById(object.getCustomerId());
				model.addAttribute("customer", customer);
				model.addAttribute("msg1", "Successfully login to " + customer.getUserName());
				return "front-end-dashboard";

			} else {
				redirectAttributes.addFlashAttribute("message",
						"Your account has been deleted try to consult company or try to use another email for registration");
				return "redirect:/loginpage";
			}
		} else {
			Customer customerobject = new Customer();
			model.addAttribute("customerObject", customerobject);

			redirectAttributes.addFlashAttribute("message",
					"The entered details are wrong.\t Please check your Email and password");

			return "redirect:/loginpage";

		}
	}

	@RequestMapping("/customerDashboard/{cid}")
	public String customerDashboard(Model model, @PathVariable("cid") int cid) {
		Customer customer = customerservice.getCustomerById(cid);
		model.addAttribute("customer", customer);
		return "front-end-dashboard";
	}

	@GetMapping("/customerprofile/{id}")
	public String addAddress(Model model, @PathVariable("id") int customerid) {
		Customer customerobject = customerservice.getCustomerById(customerid);
		CustomerAddress customeraddress = new CustomerAddress();
		System.out.println("santosh");

		model.addAttribute("customerobject", customerobject);
		model.addAttribute("customerid", customerobject.getCustomerId());
		model.addAttribute("password", customerobject.getPassword());
		model.addAttribute("Confirm_password", customerobject.getConfirmPassword());
		model.addAttribute("username", customerobject.getUserName());
		model.addAttribute("customeraddressobject", customeraddress);

		return "front-end-profile";
	}

	@GetMapping("/logout")
	public String Logout(HttpSession session) {

		return "redirect:/front-end";
	}

	@RequestMapping("/saveaddressandupdate")
	public String addDetails(Model model, Customer customerObject, CustomerAddress customeraddressObject,
			HttpServletRequest request) {
		customerObject.setIsActive('Y');
		customerservice.addCustomer(customerObject);
		customeraddressObject.setIsActive('Y');
		CustomerAddress addressobject = customeraddressservice.addcustomeraddress(customeraddressObject);
		model.addAttribute("address", addressobject);
		HttpSession session = request.getSession();
		session.setAttribute("addressid", addressobject.getAddressId());
		return "front-end-dashboard";
	}

	@GetMapping("/deleteprofile/{id}")
	public String deleteProfile(Model model, @PathVariable("id") int customerid,
			@ModelAttribute(value = "customerObject") Customer customerObject) {
		customerservice.deleteBydataId(customerid);
		return "redirect:/registration";
	}

	@RequestMapping("/editpassword/{id}")
	public String changepassword(Model model, @PathVariable("id") int customerid, HttpServletRequest request) {
		Customer customerobject = customerservice.getCustomerById(customerid);
		HttpSession session = request.getSession();
		session.setAttribute("id", customerobject.getCustomerId());
		model.addAttribute("customerobject", customerobject);
		model.addAttribute("customerid", customerobject.getCustomerId());
		model.addAttribute("password", customerobject.getPassword());
		model.addAttribute("ConfirmPassword", customerobject.getConfirmPassword());
		model.addAttribute("FullName", customerobject.getUserName());
		model.addAttribute("Email", customerobject.getEmail());
		model.addAttribute("MobileNumber", customerobject.getMobileNumber());
		model.addAttribute("FirstName", customerobject.getFirstName());
		model.addAttribute("LastName", customerobject.getLastName());
		customerobject.setIsActive('y');

		return "forget-password";
	}

	@RequestMapping("/savepassword")
	public String addDetails(Model model, @ModelAttribute(value = "customerobject") Customer customerobject,
			HttpServletRequest request) {

		String ConfirmPassword = customerobject.getConfirmPassword();
		HttpSession session = request.getSession();
		if (customerobject.getPassword().equals(ConfirmPassword)) {
			customerobject.setIsActive('Y');
			Customer object = customerservice.addCustomer(customerobject);

			session.setAttribute("customerid", object.getCustomerId());
			return "redirect:/loginpage";
		} else {
			model.addAttribute("customerobject", customerobject);
			String str = "password and confirm password should be same ";
			model.addAttribute("msg", str);
			int id = (int) session.getAttribute("id");
			if (id == 0) {
			}
			return "redirect:/editpassword/" + id;
		}
	}

	@RequestMapping("/editprofile/{cid}")

	public String editprofile(Model model, @PathVariable("cid") int cid) {
		Customer customerdetailsbyid = customerservice.getCustomerById(cid);
		model.addAttribute("customer", customerdetailsbyid);
		return "edit-customer-details";
	}

	@RequestMapping("/updatecontactdetails")
	public String updatedetails(Model model, Customer customer) {

		Customer customerObject = customerRepository.findByEmail(customer.getEmail());
		customerObject.setFirstName(customer.getFirstName());
		customerObject.setLastName(customer.getLastName());
		customerObject.setIsActive('Y');
		customerObject.setMobileNumber(customer.getMobileNumber());
		Customer customer2 = customerservice.addCustomer(customerObject);

		return "redirect:/customerDashboard/" + customer2.getCustomerId();
	}

	@RequestMapping("/editaddress/{id}")
	public String editaddress(Model model, @PathVariable(value = "id") int id) {
		CustomerAddress addressobject = customeraddressservice.getcustomeraddressById(id);
		model.addAttribute("addressobject", addressobject);
		return "edit-customer-address";
	}

	@RequestMapping("/frontendproductdetails/{pid}/{cid}")
	public String getdetailsreview(@PathVariable(value = "pid") int productid, @PathVariable(value = "cid") int cid,
			Model model) {
		PhysicalProducts product = physicalProductService.getProductById(productid);
		Customer customer = customerservice.getCustomerById(cid);

		List<ProductImage> productImages = imageService.getPhysicalProductImages(product.getProductCode());
		model.addAttribute("productImages", productImages);
		model.addAttribute("product", product);
		model.addAttribute("customer", customer);
		System.out.println(product.getProductId());

		return "front-end-product-reviewdetail";
	}

	@RequestMapping("orderlist/{cid}")
	public String getOrderListByCid(Model model, @PathVariable("cid") int cid) {

		List<CustomerOrder> orderList = customerOrderRepository.getData(cid);
		Customer customer = customerservice.getCustomerById(cid);
		model.addAttribute("orderList", orderList);
		model.addAttribute("customer", customer);

		return "orderlist";
	}

	@RequestMapping("/invoice/{cid}/{oid}/{bid}/{caid}")
	public String getInvoice(Model model, @PathVariable("cid") int cid, @PathVariable("oid") int oid,
			@PathVariable("bid") int bid, @PathVariable("caid") int caid) {

		List<OrderByCustomer> orderDownload = orderByCustomerRepository.orderedProduct(oid, cid);
		CustomerAddress customerAddress = customerAddressRepository.getById(caid);
		Customer customer = customerservice.getCustomerById(cid);
		BilingDetails billingAddress = billingRepository.getById(bid);
		CustomerOrder orderData = customerOrderRepository.getById(oid);
		model.addAttribute("customer", customer);
		model.addAttribute("customerOrder", orderDownload);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("customerAddress", customerAddress);
		model.addAttribute("orderData", orderData);

		return "invoice";
	}

	@RequestMapping("/frontendReview/{pid}")
	public String addReview(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			@PathVariable(value = "pid") int pid) {
		System.out.println("l");
		PhysicalProducts Productobject = physicalProductService.getProductById(pid);
		model.addAttribute("customerObject", customerObject);
		model.addAttribute("Productobject", Productobject);
		return "Reviewlogin";
	}

	@RequestMapping("/reviewloginpage")
	public String addReviewLogin(Model model, @ModelAttribute(value = "customerObject") Customer customerObject) {
		model.addAttribute("customerObject", customerObject);
		return "front-end-Reviewlogin";
	}

	@RequestMapping("/Reviewregistration")
	public String addReviewRegistration(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			@RequestParam(required = false) String msg) {
		if (!StringUtils.isEmpty(msg)) {
			model.addAttribute("msg", msg);
		}
		System.out.println("santhosh");
		model.addAttribute("customerObject", customerObject);
		return "front-end-Reviewregister";
	}

	@RequestMapping(value = "/savereviewCustomer", method = RequestMethod.POST)
	public String saveReviewRegistration(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			RedirectAttributes redirectAttributes) {
		System.out.println("I am Save User");
		// String str1=(customerObject.getDOB())

		if (customerObject.getConfirmPassword().equals(customerObject.getPassword())) {
			customerObject.setIsActive('y');
			customerservice.addCustomer(customerObject);
			System.out.println(customerObject.getUserName());
			return "Reviewlogin";
		} else {
			redirectAttributes.addFlashAttribute("msg", "password and confirm password should be same ");
			return "redirect:/Reviewregistration";
		}
	}

	@GetMapping("/customerReviewlogin/{pid}")
	public String loginValidationReview(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			HttpServletRequest request, @PathVariable(value = "pid") int pid) {
		PhysicalProducts Productobject = physicalProductService.getProductById(pid);
		System.out.println(customerObject.getEmail());
		System.out.println(customerObject.getPassword());
		System.out.println(customerObject.getCustomerId());
		Customer signinObj = customerservice.getCustomer(customerObject.getEmail(), customerObject.getPassword());
		System.out.println(signinObj);
		if (signinObj != null) {
			HttpSession session = request.getSession();
			session.setAttribute("customerid", customerObject.getCustomerId());
			session.setAttribute("fullname", customerObject.getUserName());
			session.setAttribute("email", customerObject.getEmail());
			signinObj.setCreated(LocalDate.now());
			signinObj.setUpdated(LocalDate.now());
			customerservice.addCustomer(signinObj);
			return "redirect:/frontendproductdetails/" + Productobject.getProductId() + "/" + signinObj.getCustomerId();

		} else {
			Customer cObject = new Customer();
			model.addAttribute("customerObject", cObject);

			model.addAttribute("msg", "The entered details are wrong.\t Please check your Email and password");

			return "redirect:/frontendReview/" + pid;

		}

	}

	@RequestMapping("/Digitalproductdetails/{id}")
	public String getDigitaldetails(@PathVariable(value = "id") int productid, Model model) {
		DigitalProducts products = DigitalProductRepostory.getById(productid);

		// List<DigitalProducts> products =
		// DigitalProductsService.getAllDigitalProductsData();
		model.addAttribute("product", products);
		// System.out.println(products.getProductId());

		return "DigitalProduct-details";

	}

	@RequestMapping("/pdflink/{id}")
	public String CustomerViewPdf(Model model, @PathVariable(value = "id") int id) {

		DigitalProducts products = DigitalProductRepostory.getById(id);
		model.addAttribute("products", products);
		return "pdf-link";
	}

	@RequestMapping("/Digitaldetails/{id}/{cid}")
	public String getDigitalproductdetails(@PathVariable(value = "id") int productid,
			@PathVariable(value = "cid") int cid, Model model) {
		DigitalProducts digitalProducts = DigitalProductRepostory.getById(productid);
		Customer customer = customerservice.getCustomerById(cid);

		model.addAttribute("digitalProducts", digitalProducts);
		model.addAttribute("customer", customer);

		return "Digitaldetails";
	}

	@GetMapping("/customerDigitalReviewlogin/{pid}")
	public String loginDigitalValidationReview(Model model,
			@ModelAttribute(value = "customerObject") Customer customerObject, HttpServletRequest request,
			@PathVariable(value = "pid") int pid) {
		DigitalProducts productObject = DigitalProductRepostory.getById(pid);
		System.out.println(customerObject.getEmail());
		System.out.println(customerObject.getPassword());
		System.out.println(customerObject.getCustomerId());
		Customer signinObj = customerservice.getCustomer(customerObject.getEmail(), customerObject.getPassword());
		System.out.println(signinObj);
		if (signinObj != null) {
			HttpSession session = request.getSession();
			session.setAttribute("customerid", customerObject.getCustomerId());
			session.setAttribute("fullname", customerObject.getUserName());
			session.setAttribute("email", customerObject.getEmail());
			signinObj.setCreated(LocalDate.now());
			signinObj.setUpdated(LocalDate.now());
			return "redirect:/Digitaldetails/" + productObject.getProductId() + "/" + signinObj.getCustomerId();

		} else {
			Customer cObject = new Customer();
			model.addAttribute("customerObject", cObject);

			model.addAttribute("msg", "The entered details are wrong.\t Please check your Email and password");
			DigitalProducts Product = DigitalProductRepostory.getById(pid);
			model.addAttribute("customerObject", customerObject);
			model.addAttribute("Productobject", Product);
			return "ReviewDigitallogin";

		}

	}

	@RequestMapping("/frontendDigitalReview/{pid}")
	public String addDigitalReview(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			@PathVariable(value = "pid") int pid) {
		System.out.println("l");
		DigitalProducts Productobject = DigitalProductRepostory.getById(pid);
		model.addAttribute("customerObject", customerObject);
		model.addAttribute("Productobject", Productobject);
		return "ReviewDigitallogin";
	}

	@RequestMapping("/categoryview")
	public String PhysicalCategoryProducts(Model model, @RequestParam("categoryName") String categoryName) {
		 
		List<PhysicalProducts> products = physicalProductRepository.findProductsBycategories(categoryName);
		model.addAttribute("products", products);
		List<PhysicalCategory> catagorylist = PhysicalCategoryService.getAllCategory();
		model.addAttribute("catagorylist", catagorylist);

		return "categoryviewproducts";
	}

	@RequestMapping("/otplogin")
	public String otpLogin(Model model, @ModelAttribute(value = "customerObject") Customer customerObject,
			RedirectAttributes redirectAttributes) {
		Customer signinObj = customerservice.getCustomer(customerObject.getEmail(), customerObject.getPassword());
		if (signinObj != null) {
			if (signinObj.getIsActive() == 'Y') {
				Random random = new Random();
				int otp = random.nextInt(1000000);
				if (otp < 100000) {
					otp = otp + 100000;
				}
				String subject = "Multikart Account - " + otp + " is your verification code for secure access ";
				String body = "Hi, " + " Greetings! " + " You are just a step away from accessing your MULTIKART account"
						+ " We are sharing a verification code to access your account " + " Your OTP : " + otp
						+ " Best Regards , Team Multikart.";
				emailSenderService.sendSimpleEmail(signinObj.getEmail(), body, subject);
				return "redirect:/otpsender/" + signinObj.getCustomerId()+"/"+otp;
			} else {
				redirectAttributes.addFlashAttribute("message",
						"Your account has been deleted try to consult company or try to use another email for registration");
				return "redirect:/loginpage";
			}

		} else {
			redirectAttributes.addFlashAttribute("message",
					"The entered details are wrong.\t Please check your Email and password");

			return "redirect:/loginpage";

		}
	}

	@RequestMapping("/customerDashboard/{cid}/{otp}")
	public String customerDashboardWithOtp(Model model, @PathVariable("cid") int cid, @PathVariable("otp") int otp,
			@RequestParam("OTP") int OTP, RedirectAttributes redirectAttributes) {
		Customer customer = customerservice.getCustomerById(cid);

		if (otp == OTP) {
			model.addAttribute("customer", customer);
			return "front-end-dashboard";
		} else {
			redirectAttributes.addFlashAttribute("message", "Invalid OTP!");
			redirectAttributes.addFlashAttribute("previousotp", otp);
			return "redirect:/otpsender/"+customer.getCustomerId()+"/"+otp;
		}

	}
	

	@RequestMapping("/otpsender/{cid}/{otp}")
	public String otpSender(Model model, @PathVariable("cid") int cid,@RequestParam(required=false)String message,@PathVariable("otp")int otp) {
		
		if(!StringUtils.isEmpty(message)) {
			model.addAttribute("message", message);
		}
		Customer customer = customerRepository.getById(cid);
		model.addAttribute("otp", otp);
		model.addAttribute("customer", customer);
		return "front-end-otp-login";
	}
	@RequestMapping("/otpResender/{cid}")
	public String otpresender(@PathVariable("cid")int cid) {
		Customer customer = customerRepository.getById(cid);
		Random random = new Random();
		int otp = random.nextInt(1000000);
		if (otp < 100000) {
			otp = otp + 100000;
		}
		String subject = "Multikart Account - " + otp + " is your verification code for secure access ";
		String body = "Hi, " + " Greetings! " + " You are just a step away from accessing your MULTIKART account"
				+ " We are sharing a verification code to access your account " + " Your OTP : " + otp
				+ " Best Regards , Team Multikart.";
		emailSenderService.sendSimpleEmail(customer.getEmail(), body, subject);
		return "redirect:/otpsender/"+cid+"/"+otp;
	}
	@RequestMapping("/productinvoice/{cid}/{obcid}/{bid}/{caid}")
	public String productinvoice(Model model, @PathVariable("cid") int cid, @PathVariable("obcid") int obcid,
			@PathVariable("bid") int bid,@PathVariable("caid")int caid) {
		Customer customer = customerservice.getCustomerById(cid);
		BilingDetails billingAddress = billingRepository.getById(bid);
		OrderByCustomer orderByCustomer=orderByCustomerRepository.getById(obcid);
		CustomerAddress customerAddress=customerAddressRepository.getById(caid);
		CustomerOrder customerOrder=customerOrderRepository.getById(orderByCustomer.getOrderId());
		model.addAttribute("customerAddress", customerAddress);
		model.addAttribute("customer", customer);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("orderByCustomer", orderByCustomer);
		model.addAttribute("customerOrder", customerOrder);
	
		return "product-invoice";
	}
}