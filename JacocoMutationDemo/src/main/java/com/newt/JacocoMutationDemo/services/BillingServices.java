package com.newt.JacocoMutationDemo.services;

import org.apache.log4j.Logger;

import com.newt.JacocoMutationDemo.bean.AccountProfile;
import com.newt.JacocoMutationDemo.bean.AccountSummary;
import com.newt.JacocoMutationDemo.constants.ApplicationConstants;
import com.newt.JacocoMutationDemo.logger.Log4jWrapper;
import com.newt.JacocoMutationDemo.test.TestAccountProfile;

/**
 * @date 02/01/2016
 * @author Harish Main
 * @description Class contains all Billing Services implementations methods
 */
public class BillingServices implements ApplicationConstants{
	
	private final static Logger logger = Logger.getLogger(BillingServices.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	AccountProfile accountprofilebean=null;
	AccountSummary accountsummarybean=null;
	
	public BillingServices(AccountProfile profilebean){
		this.accountprofilebean=profilebean;
	}
	
	public BillingServices(AccountProfile profilebean, AccountSummary summarybean){
		this.accountprofilebean=profilebean;
		this.accountsummarybean=summarybean;
	}

	/**
	 * @date 02/01/2016
	 * @author Harish Main
	 * @description Method used to authenticate user credentials
	 * @param userid
	 * @param password
	 * @return
	 */
	public boolean authenticateUserCredentials(String userid, String password){
		
		boolean isValidUser=false;
		logger.info("Inside BillingServices :: authenticateUserCredentials() invoke... userid:"+userid+", password"+password);
		
		try{
			
/*			if(userid.equals(accountprofilebean.getUserid()) && password.equals(accountprofilebean.getPassword())){
				isValidUser=true;
				logger.info("Inside BillingServices :: authenticateUserCredentials(), login success ");
			}else{
				logger.warn("Inside BillingServices :: authenticateUserCredentials(), login failed ");
			}*/
			
			if((userid!=null && userid.equals(accountprofilebean.getUserid())) && 
					(password!=null &&  password.equals(accountprofilebean.getPassword()))){
				isValidUser=true;
				logger.info("Inside BillingServices :: authenticateUserCredentials(), login success ");
			}else{
				logger.warn("Inside BillingServices :: authenticateUserCredentials(), login failed ");
			}
			
		}catch(Exception e){
			logger.error("Inside BillingServices :: authenticateUserCredentials(), Exception:"+e);
		}
		return isValidUser;		
		
	}
	
	public void getAccountProfile(){
		
		try{
			
			if(accountprofilebean!=null){
				logger.info("account holder name: "+accountprofilebean.getAccount_holder_name());
				logger.info("email address      : "+accountprofilebean.getEmail_address());
				logger.info("Phone No           : "+accountprofilebean.getPhone_no());
			}

			
		}catch(Exception e){
			logger.error("Inside BillingServices :: getAccountProfile(), Exception:"+e);
		}
		

		
	}
	
	/**
	 * @date 02/03/2016
	 * @author Harish Main
	 * @description Method to calculate Total Bill Amount
	 * @param previous_charges
	 * @param current_charges
	 * @return
	 */
	public double getTotalBillAmount(double previous_charges, double current_charges){
		
		double subtotal_bill_charges=0.00;
		double total_bill_charges=0.00;
		double sales_tax_amount=0.00;
		
		try{
			
			if(previous_charges>=0 && current_charges>0){
				
				// Calculate total bill charges
				subtotal_bill_charges=previous_charges + current_charges ;
				sales_tax_amount=getSalesTaxAmount(subtotal_bill_charges);
				
				// Calculate total bill charges
				total_bill_charges=subtotal_bill_charges+sales_tax_amount;
				
				logger.info("Inside BillingServices :: getTotalBillAmount(), total_bill_charges:"+total_bill_charges);
			}


			
		}catch(Exception e){
			logger.error("Inside BillingServices :: getTotalBillAmount(), Exception:"+e);
		}
		
		return total_bill_charges;
		
	}
	
	public double getSalesTaxAmount(double bill_total_amount){
		
		double sales_tax_amount=0.00;
		
		try{
			
			if(bill_total_amount>0){
				sales_tax_amount=(bill_total_amount*SALES_TAX_RATE)/100;
			}
			
			logger.debug("Inside BillingServices :: getSalesTaxAmount(), sales_tax_amount:"+sales_tax_amount);
			
		}catch(Exception e){
			logger.error("BillingServices :: getSalesTaxAmount(), Exception: "+e);
		}
		
		return sales_tax_amount;
		
	}		
	
	

}
