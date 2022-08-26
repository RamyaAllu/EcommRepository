package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.CustomerCart;
@Repository
public interface CustomerCartRepository extends JpaRepository<CustomerCart, Integer>{
 @Query("select al from CustomerCart al where lower(al.isActive)='y' or lower(al.isActive)='Y'")
 List<CustomerCart> getActiveList();
 
 @Query("select sum(productPrice)  from CustomerCart al where al.customerId=?1 and (al.isActive='N' or al.isActive='Y')")
 String getcarttotal(int customerId);
 @Query("select al from CustomerCart al where al.isActive='Y' and al.customerId=?1")
 List<CustomerCart> getCartActiveList(int id);
 
 @Query("select al from CustomerCart al where  al.isActive='O' and al.customerId=?1")
 List<CustomerCart> getCartOrderList(int id);
 @Query("select al from CustomerCart al where (al.isActive='Y' or al.isActive='N') and al.customerId=?1")
 List<CustomerCart> getAllCartList(int cid);
 @Query("select al from CustomerCart al where al.customerId=?1 and al.productModelNumber=?2 and al.isActive='Y'")
 CustomerCart addQuantityofProduct(int cid,String pid);
 @Query("select al from CustomerCart al where al.customerId=?1 and al.productModelNumber=?2 and al.isActive='N'")
 List<CustomerCart> DeleteQuantityofProduct(int cid,String modelNumber);
 
 @Query("select al from CustomerCart al where  al.isActive='B' and al.customerId=?1")
 List<CustomerCart> getBillOrderList(int id);
  
 @Query("select sum(productPrice)  from CustomerCart al where al.customerId=?1 and al.isActive='B' ")
 String getBilltotal(int customerId);
 @Query("select al from CustomerCart al where (al.isActive='Y' or al.isActive='N') and al.customerId=?1 and al.productModelNumber=?2 ")
 List<CustomerCart> getAllcartListforproduct(int cid,String productModelNumber);
}
