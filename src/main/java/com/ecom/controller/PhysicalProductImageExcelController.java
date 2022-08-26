package com.ecom.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom.beans.PhysicalProducts;
import com.ecom.beans.ProductImage;
import com.ecom.repository.ImageRepository;
import com.ecom.repository.PhysicalProductRepository;

@Controller
@RequestMapping("/excelsheet")
public class PhysicalProductImageExcelController {

	@Autowired
	private PhysicalProductRepository physicalProductRepository;
	
	
	

	@RequestMapping("/dataFilling")
	@ResponseBody
	public String exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<PhysicalProducts> productList = physicalProductRepository.getActivePhysicalProducts();
         
        ProductExcelExporter excelExporter = new ProductExcelExporter(productList);
         
        excelExporter.export(response);   
        return "santhosh";
    }
}

class ProductExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<PhysicalProducts> physicalProductslist;

	public ProductExcelExporter(List<PhysicalProducts> physicalProductslist) {
		this.physicalProductslist = physicalProductslist;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "isactive", style);
		createCell(row, 1, "productCategory", style);
		createCell(row, 2, "productCode", style);
		createCell(row, 3, "productDescription", style);
		createCell(row, 4, "productDetails", style);
		createCell(row, 5, "productDiscountPrice", style);
		createCell(row, 6, "productId", style);
		createCell(row, 7, "productImage", style);
		createCell(row, 8, "productModelNumber", style);
		createCell(row, 9, "productMRPPrice", style);
		createCell(row, 10, "productName", style);
		createCell(row, 11, "productShippingInformation", style);
		createCell(row, 12, "productSize", style);
		createCell(row, 13, "productSpecification", style);
		createCell(row, 14, "productSubCategory", style);
		createCell(row, 15, "productVideo", style);
		createCell(row, 16, "QRcode", style);
		createCell(row, 17, "vendorName", style);
		createCell(row, 18, "productRating", style);
		createCell(row,19,"productQuantity",style);
		
		
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	@Autowired
    ImageRepository imageRepository;
	private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (PhysicalProducts physicalProducts : physicalProductslist) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, "Y", style);
            createCell(row, columnCount++, physicalProducts.getProductCategory(), style);
            createCell(row, columnCount++, physicalProducts.getProductCode(), style);
            createCell(row, columnCount++, physicalProducts.getProductDescription(), style);
            createCell(row, columnCount++, physicalProducts.getProductDetails(), style);
            createCell(row, columnCount++,String.valueOf(physicalProducts.getProductDiscountPrice()), style);
            createCell(row, columnCount++, physicalProducts.getProductId(), style);
            createCell(row, columnCount++, physicalProducts.getProductImage(), style);
            createCell(row, columnCount++, physicalProducts.getProductModelNumber(), style);
            createCell(row, columnCount++, String.valueOf(physicalProducts.getProductMRPPrice()), style);
            createCell(row, columnCount++, physicalProducts.getProductName(), style);
            createCell(row, columnCount++, physicalProducts.getProductShippingInformation(), style);
            createCell(row, columnCount++, physicalProducts.getProductSize(), style);
            createCell(row, columnCount++, physicalProducts.getProductSpecification(), style);
            createCell(row, columnCount++, physicalProducts.getProductSubCategory(), style);
            createCell(row, columnCount++, "could not find video", style);
            createCell(row, columnCount++,"could not find video", style);
            createCell(row, columnCount++, physicalProducts.getProductCompany(), style);
            createCell(row, columnCount++, "5", style);
            createCell(row,columnCount++,20,style);
            
            
            
             
        }
    }
	public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
