package com.ecom.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.PhysicalProducts;
@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProducts, Integer> {
	
	@Query("Select p from PhysicalProducts p where p.created=current_date and (lower(p.isactive)='y' or lower(p.isactive)='Y')")
	List<PhysicalProducts> findLatestProducts();
	@Query("select al from PhysicalProducts al where al.productModelNumber=?1 and  al.isactive='N'")
	List<PhysicalProducts> getPhysicalProductsByModelNumber(String model);
	@Query("select al from PhysicalProducts al where al.productModelNumber=?1 and  (al.isactive='N' or al.isactive='Y')")
	List<PhysicalProducts> getPhysicalProductsByModelNumberForQuantity(String model);
	@Query("select al from PhysicalProducts al where lower(al.isactive)='y' or lower(al.isactive)='Y'")
	List<PhysicalProducts> getActivePhysicalProducts();
	
	@Query("select al from PhysicalProducts al where al.productId!=?1 and (al.productCompany=?2 and al.productMRPPrice=?3) and al.ProductSubCategory=?4 and al.isactive='Y' ")
	List<PhysicalProducts> compareProducts(int id,String brand,double price,String sub);
	@Query("Select isactive from PhysicalProducts p where lower(p.isactive)=lower(?1)")
	List<Character> getlist(char isactive);
	@Query("select al from PhysicalProducts al where lower(al.isactive)='Y' or lower(al.isactive)='N'")
	List<PhysicalProducts> leftProducts();
	@Query("select al from PhysicalProducts al where lower(al.productModelNumber)=lower(?1)")
	List<PhysicalProducts> getPhysicalProductsByModelNumberForadding(String model);
	@Query("select al from PhysicalProducts al where lower(al.productModelNumber)=lower(?1) and al.isactive='Y'")
	List<PhysicalProducts> getPhysicalProductsForadding(String model);
	@Query("select sum(productMRPPrice) from PhysicalProducts al where al.isactive='S'")
	public String findAllorderproductsPrice();
	@Query("Select p from PhysicalProducts p where p.created=?1 and p.isactive='Y'")
	List<PhysicalProducts> findLatestProducts(LocalDate current_date);
	
	@Query("select al from PhysicalProducts al where al.ProductSubCategory=?1 and al.isactive='Y'")
	List<PhysicalProducts> findProductsBySubcategories(String id);
	@Query("select al from PhysicalProducts al where lower(al.productModelNumber)=lower(?1) and al.isactive='Y'")
	PhysicalProducts getPhysicalProductsForaddingMultipleImages(String model);
	
	
	@Query("select al from PhysicalProducts al where  al.productCategory=?1 and al.isactive='Y'")
	List<PhysicalProducts> findProductsBycategories(String categoryName);
	
}
