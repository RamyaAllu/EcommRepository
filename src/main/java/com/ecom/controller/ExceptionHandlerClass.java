package com.ecom.controller;

import java.nio.file.AccessDeniedException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import javax.persistence.NonUniqueResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jmx.MBeanServerNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class ExceptionHandlerClass {

//	   @ExceptionHandler(NonUniqueResultException.class)
//	   public String handleNonUniqueResultException(NonUniqueResultException nonUniqueResultException){
//	   return "exceptionhandle";
//	   }
//
//	       @ExceptionHandler(ClassNotFoundException.class)
//	       public String handelException(ClassNotFoundException classNotFoundException) {
//	           return"exceptionhandle";
//	       }
//
//	       @ExceptionHandler(IllegalAccessException.class)
//	       public String handelIllegalAccessException(IllegalAccessException illegalAccessException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(NoSuchFieldException.class)
//	       public String handelNoSuchFieldException(NoSuchFieldException noSuchFieldException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(NoSuchMethodException.class)
//	       public String handelNoSuchMethodException(NoSuchMethodException noSuchFieldException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(InterruptedException.class)
//	       public String handelInterruptedException(InterruptedException interruptedException) {
//	           return"exceptionhandle";
//	       }
//
//	       @ExceptionHandler(IndexOutOfBoundsException.class)
//	       public String handelIndexOutOfBoundsException(IndexOutOfBoundsException indexOutOfBoundsException) {
//	           return"exceptionhandle";
//	       }
//
//	       @ExceptionHandler(IllegalThreadStateException.class)
//	       public String handelIllegalThreadStateException(IllegalThreadStateException illegalThreadStateException) {
//	           return"exceptionhandle";
//	       }
//
//	       @ExceptionHandler(InputMismatchException.class)
//	       public String handelInputMismatchException(InputMismatchException inputMismatchException) {
//	           return"exceptionhandle";
//	       }
//
//	       @ExceptionHandler(NumberFormatException.class)
//	       public String handelNumberFormatException(NumberFormatException numberFormatException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(ArithmeticException.class)
//	       public String handelArithmeticException(ArithmeticException ArithmeticException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(NoSuchElementException.class)
//	       public String handelNoSuchElementException(NoSuchElementException NoSuchElementException) {
//	           return"exceptionhandle";
//	       }
//
//	       @ExceptionHandler(AccessDeniedException.class)
//	       public String handelAccessDeniedException(AccessDeniedException AccessDeniedException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(EmptyResultDataAccessException.class)
//	       public String handelAccessDeniedException(EmptyResultDataAccessException emptyResultDataAccessException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(NullPointerException.class)
//	       public String handelNullPointerException(NullPointerException nullPointerException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(MBeanServerNotFoundException.class)
//	       public String handelMBeanServerNotFoundException(MBeanServerNotFoundException MBeanServerNotFoundException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(TemplateInputException.class)
//	       public String handelTemplateInputException(TemplateInputException TemplateInputException) {
//	           return"exceptionhandle";
//	       }
//	       @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	       public String handelHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException TemplateInputException) {
//	           return"exceptionhandle";
//	       }

}
