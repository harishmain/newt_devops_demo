package com.newt.JacocoMutationDemo.test;

import org.apache.log4j.Logger;

import com.newt.JacocoMutationDemo.dao.AccountProfileDaoImpl;
import com.newt.JacocoMutationDemo.dao.AccountSummaryDaoImpl;
import com.newt.JacocoMutationDemo.logger.Log4jWrapper;

public class TestAccountProfile {

	private final static Logger logger = Logger.getLogger(TestAccountProfile.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	public static void main(String[] args) {
		
		logger.info("TestAccountProfile :: main(), invoked...");
		
		TestAccountProfile obj = new TestAccountProfile();
		
		obj.testFindAccount();

	}

	
	public void testFindAccount(){
		
		logger.info("TestAccountProfile :: testFindAccount(), invoked...");
		//int account_no=2016001;
		
		int[] account_nos={2000001}; //{2000001, 2000002, 2000003};
		
		AccountProfileDaoImpl acdao=new AccountProfileDaoImpl();
		AccountSummaryDaoImpl asdao=new AccountSummaryDaoImpl();
		
		try{
			for(int account_no : account_nos){
				acdao.findAccount(account_no);
				asdao.findSummaryAccount(account_no);
			}
			
			
		}catch(Exception e){
			logger.error("TestAccountProfile :: testFindAccount(), Exception:"+e);			
		}
		
		
	}
	
}
