package com.newt.JacocoMutationDemo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.newt.JacocoMutationDemo.bean.AccountProfile;
import com.newt.JacocoMutationDemo.bean.AccountSummary;
import com.newt.JacocoMutationDemo.dao.AccountProfileDaoImpl;
import com.newt.JacocoMutationDemo.dao.AccountSummaryDaoImpl;
import com.newt.JacocoMutationDemo.services.BillingServices;

public class BillingServicesTest {

	int account_no=0;
	String userid=null;
	String password=null;
	
	double previous_balance=0.00;
	double current_balance=0.00;
	double sales_tax=0.00;
	double subtotal_bill_amount=0.00;
	double total_bill_amount=0.00;
			
	AccountProfileDaoImpl profiledao = new AccountProfileDaoImpl();
	AccountProfile profileBean = new AccountProfile();
	
	AccountSummaryDaoImpl summarydao = new AccountSummaryDaoImpl();
	AccountSummary summaryBean = new AccountSummary();
	
	public void setAccountNo(int account_no){
		this.account_no=account_no;
		setProfileBean(account_no);
	}
	
	public void setAccountCredentials(int account_no, String userid, String password){
		this.account_no=account_no;
		this.userid=userid;
		this.password=password;
		
		setProfileBean(account_no);
		setSummaryBean(account_no);
	}
	
	
	
	public void setProfileBean(int account_no){
		this.profileBean=profiledao.getAccountProfileBean(account_no);
	}
	
	public void setSummaryBean(int account_no){
		this.summaryBean=summarydao.getAccountSummaryBean(account_no);
	}
	
	BillingServices billingservicesObj = null;
	
	@Test
	public void testBillingServices() {	
		
		this.account_no=2000001;
		setAccountNo(2000001);
		
		billingservicesObj = new BillingServices(profileBean);
		
		try {
			assertEquals("tc1 :: account number matching with billing services bean", this.account_no, profileBean.getAccount_no());	
		} catch (Exception ex) {
			System.out.println("Exception: "+ex);
			fail("testBillingServices :: FAILED :: account number not found in billingservicesObj"); 
		}
	}

	@Test
	public void testAuthenticateUserCredentials() {
		
		this.userid="user1";
		this.password="1234";
		
		setAccountCredentials(2000001, this.userid, this.password );
		billingservicesObj = new BillingServices(profileBean);
		
		boolean loginstatus=false;
		
		try{
			loginstatus=billingservicesObj.authenticateUserCredentials(this.userid, this.password);
			assertEquals("tc2 :: authentication success", true, loginstatus);
		}catch (Exception ex) {
			System.out.println("Exception: "+ex);
			fail("testAuthenticateUserCredentials :: FAILED :: Invalid credentials "); 
		}
		
	}
	
	@Test
	public void testgetAccountProfile(){
		
		this.userid="user1";
		this.password="1234";
		
		setAccountCredentials(2000001, this.userid, this.password );
		billingservicesObj = new BillingServices(profileBean);
		
		try{
			
			billingservicesObj.getAccountProfile();
			
		}catch (Exception ex) {
			System.out.println("Exception: "+ex);
			fail("testgetAccountProfile :: FAILED :: Account Profile"); 
		}
		
	}
	
	@Test
	public void testgetSalesTaxAmount(){
		
		this.userid="user1";
		this.password="1234";
		double actual_sales_tax=0.00;
		double expected_sales_tax=11.8125;			//11.8125
		double actual_subtotal_bill_amount=0.00;
		double actual_previous_balance=0.00;
		double actual_current_balance=0.00;
		
		setAccountCredentials(2000001, this.userid, this.password );
		billingservicesObj = new BillingServices(profileBean, summaryBean);
		
		try{
			
			actual_previous_balance=summaryBean.getPrevios_balance();
			actual_current_balance=summaryBean.getCurrent_balance();
			
			actual_subtotal_bill_amount=actual_previous_balance+actual_current_balance;
			
			actual_sales_tax=billingservicesObj.getSalesTaxAmount(actual_subtotal_bill_amount);
			
			assertEquals(expected_sales_tax, actual_sales_tax, 0.0);
			
		}catch(Exception e){
			System.out.println("testgetSalesTaxAmount, Exception: "+e);
			fail("testgetSalesTaxAmount :: FAILED :: Sales Tax Amount not matching");
		}
		
	}
	
	@Test
	public void testgetTotalBillAmount(){
		
		this.userid="user1";
		this.password="1234";
		double actual_total_bill_amount=0.00;
		double expected_bill_amount=200.8125;
		
		setAccountCredentials(2000001, this.userid, this.password );
		billingservicesObj = new BillingServices(profileBean, summaryBean);
				
		try{
			
			previous_balance=summaryBean.getPrevios_balance();
			current_balance=summaryBean.getCurrent_balance();
			
			actual_total_bill_amount=billingservicesObj.getTotalBillAmount(previous_balance, current_balance);

			assertEquals(expected_bill_amount, actual_total_bill_amount, 0.0);
			
		}catch(Exception e){
			System.out.println("testgetTotalBillAmount, Exception: "+e);
			fail("testgetTotalBillAmount :: FAILED :: Total Bill Amount not matching");
		}
		
		
	}

}
