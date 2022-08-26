package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.ProductWishlist;

@Repository
public interface ProductWishListRepository extends JpaRepository<ProductWishlist, Integer>{
    @Query("select w from ProductWishlist w where w.customerId=?1")
	List<ProductWishlist> getWishlistByCustomerId(int cid);
    @Query("select w from ProductWishlist w where w.productId=?1 and w.customerId=?2")
    public ProductWishlist findByProductId(int id,int cid);
}
