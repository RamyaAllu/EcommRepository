package com.ecom.service;

import java.util.List;

import com.ecom.beans.Admin;

public interface AdminService {

	Admin addEmployeeDetails(Admin details);

	void updateEmployeeDetails(Admin details);

	List<Admin> getAllEmployeeDetails();

	void deleteEmployeeDetailsById(int id);

	Admin getEmployeeDetailsById(int id);

	Admin getEmployeeDetails(String employeeMail, String employeePassword);

	Admin findByEmailId(String employeeMail);

	Admin validateEmail(String employeeMail);

	List<Admin> getAllDesigners();

	List<Admin> getAllRoles();

}
