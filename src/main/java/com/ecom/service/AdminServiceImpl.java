package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.Admin;
import com.ecom.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public Admin addEmployeeDetails(Admin details) {
		// TODO Auto-generated method stub
		return adminRepository.save(details);
	}

	@Override
	public void updateEmployeeDetails(Admin details) {
		// TODO Auto-generated method stub
		adminRepository.save(details);
	}

	@Override
	public List<Admin> getAllEmployeeDetails() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
	}

	@Override
	public void deleteEmployeeDetailsById(int id) {
		// TODO Auto-generated method stub
		adminRepository.deleteById(id);
	}

	@Override
	public Admin getEmployeeDetailsById(int id) {
		// TODO Auto-generated method stub
		return adminRepository.getById(id);
	}

	@Override
	public Admin getEmployeeDetails(String employeeMail, String employeePassword) {
		// TODO Auto-generated method stub
		return adminRepository.findByUsernameIgnoreCaseAndPassword(employeeMail, employeePassword);
	}

	@Override
	public Admin findByEmailId(String employeeMail) {
		// TODO Auto-generated method stub
		return adminRepository.findByEmailId(employeeMail);
	}

	@Override
	public Admin validateEmail(String employeeMail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAllDesigners() {
		// TODO Auto-generated method stub
		return adminRepository.findByDesigner();
	}

	@Override
	public List<Admin> getAllRoles() {
		// TODO Auto-generated method stub
		return adminRepository.findAllRoles();
	}

	
	
	
	

}
