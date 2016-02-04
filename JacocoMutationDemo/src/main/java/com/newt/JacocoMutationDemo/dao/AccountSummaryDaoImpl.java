package com.newt.JacocoMutationDemo.dao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.newt.JacocoMutationDemo.bean.AccountProfile;
import com.newt.JacocoMutationDemo.bean.AccountSummary;
import com.newt.JacocoMutationDemo.constants.ApplicationConstants;
import com.newt.JacocoMutationDemo.logger.Log4jWrapper;

public class AccountSummaryDaoImpl implements AccountSummaryDao, ApplicationConstants {
	
	private final static Logger logger = Logger.getLogger(AccountSummaryDaoImpl.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	/**
	 * @date 02/01/2016
	 * @author Harish Main
	 * @description Method used to find and display the Account number passed.
	 */
	public void findSummaryAccount(int account_no) {
		
		logger.debug("AccountSummaryDaoImpl :: findSummaryAccount(), invoked... account_no:"+account_no);
		
		List<AccountSummary> summaryList = new ArrayList<AccountSummary>();
		
		try{
			//get account profile for given account number
			summaryList=getAccountSummaryList(account_no);			
			displayAccountSummary(summaryList);
			
		}catch(Exception e){
			logger.error("AccountProfileDaoImpl :: findSummaryAccount(), Exception: "+e);
		}
		
	}
	
	
	/**
	 * 
	 */
	public List<AccountSummary> getAccountSummaryList(int account_no) {

		logger.debug("AccountSummaryDaoImpl :: getAccountSummaryList(), invoked...");
		
		List<AccountSummary> tempList = new ArrayList<AccountSummary>();
		List<AccountSummary> accountsummaryList = new ArrayList<AccountSummary>();
		AccountSummary bean = null;
		try{
			
			tempList=getAllAccountSummary();
			
			Iterator<AccountSummary> itr = tempList.iterator();
			
			while(itr.hasNext()){
				bean = new AccountSummary();
				bean=itr.next();
				
				if(bean.getAccount_no()==account_no){
					accountsummaryList.add(bean);
					break;
				}
				
				
			}
			
			
		}catch(Exception e){
			logger.error("AccountSummaryDaoImpl :: getAccountSummaryList(), Exception: "+e);
		}
		
		return accountsummaryList;
	
		
	}
	
	/**
	 * 
	 */
	public AccountSummary getAccountSummaryBean(int account_no) {

		logger.debug("AccountSummaryDaoImpl :: getAccountSummaryBean(), invoked...");
		
		AccountSummary bean = new AccountSummary();;
		List<AccountSummary> accountsummaryList = new ArrayList<AccountSummary>();
		
		try{
			
			accountsummaryList=getAllAccountSummary();
			
			Iterator<AccountSummary> itr = accountsummaryList.iterator();
			
			while(itr.hasNext()){
				bean = new AccountSummary();
				bean=itr.next();
				
				if(bean.getAccount_no()==account_no){
					break;
				}
				
				
			}
			
			
		}catch(Exception e){
			logger.error("AccountSummaryDaoImpl :: getAccountSummaryBean(), Exception: "+e);
		}
		
		return bean;
	
		
	}	

	public List<AccountSummary> getAllAccountSummary() {

		logger.debug("AccountSummaryDaoImpl :: getAllAccountSummary(), invoked...");
		List<AccountSummary> accountsummaryList = new ArrayList<AccountSummary>();
		AccountSummary bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy");
		Date billdate = null;
		double prev_balance=0.00;
		double curr_balance=0.00;
		
		try{
			
			//Hardcoding backend data
			//Record-01
			bean = new AccountSummary();
			bean.setAccount_no(2000001);
			billdate = sdf.parse(BILL_DATE);
			bean.setBill_date(billdate);
			
			prev_balance=0.00;
			curr_balance=189.00;			
			bean.setPrevios_balance(prev_balance);
			bean.setCurrent_balance(curr_balance);
			accountsummaryList.add(bean);
			
			//Record-02
			bean = new AccountSummary();
			bean.setAccount_no(2000002);
			billdate = sdf.parse(BILL_DATE);
			bean.setBill_date(billdate);
			
			prev_balance=0.00;
			curr_balance=144.15;			
			bean.setPrevios_balance(prev_balance);
			bean.setCurrent_balance(curr_balance);
			accountsummaryList.add(bean);	
			
			//Record-03
			bean = new AccountSummary();
			bean.setAccount_no(2000003);
			billdate = sdf.parse(BILL_DATE);
			bean.setBill_date(billdate);
			
			prev_balance=64.45;
			curr_balance=201.99;			
			bean.setPrevios_balance(prev_balance);
			bean.setCurrent_balance(curr_balance);
			accountsummaryList.add(bean);

			
		}catch(Exception e){
			logger.error("AccountSummaryDaoImpl :: getAllAccountSummary(), Exception: "+e);
		}
		
		return accountsummaryList;
	
		
	
	}



	public void displayAccountSummary(List<AccountSummary> summaryList) {
		
		logger.debug("AccountSummaryDaoImpl :: displayAccountSummary(), invoked...");
		double subtotal_bill_amount=0.00;
		double total_bill_amount=0.00;
		double sales_tax_amount=0.00;
		DecimalFormat salestax_amt_formatted = new DecimalFormat("#0.00");
		try{
			
			if(summaryList!=null && summaryList.size()>0){
				
				logger.info("--------------------------------------------------");
				logger.info("       A C C O U N T   S U M M A R Y              ");
				logger.info("--------------------------------------------------");
				logger.info("");
				for(AccountSummary bean : summaryList){
					// Get Bill Total Amount					
					subtotal_bill_amount=getBillSubTotalAmount(bean);
					
					// Calculate Sales Tax Amount
					sales_tax_amount=getSalesTaxAmount(subtotal_bill_amount);
					total_bill_amount=subtotal_bill_amount+sales_tax_amount;
					
					logger.info("Bill Date          : "+bean.getBill_date());
					logger.info("Previous balance   : "+bean.getPrevios_balance());
					logger.info("Current balance    : "+bean.getCurrent_balance());
					//logger.info("Sales Tax          : "+sales_tax_amount+"%");
					logger.info("Sales Tax          : "+salestax_amt_formatted.format(sales_tax_amount)+"%");
					logger.info("--------------------------------------------------");
					logger.info("Total Bill Amount  : "+total_bill_amount);
					logger.info("--------------------------------------------------");
					logger.info("");
				}
				
			}else{
				logger.warn("");
				logger.warn("*** No Account Summary found ***");
				logger.warn("please check your account number and try again...");
				logger.warn("");
			}
			
			
		}catch(Exception e){
			logger.error("AccountSummaryDaoImpl :: displayAccountSummary(), Exception: "+e);
		}
		
	}
	
	public double getBillSubTotalAmount(AccountSummary bean){
		
		double prev_balance=0.00;
		double current_balance=0.00;
		double bill_total_amount=0.00;
		
		try{
			
			if(bean!=null){
				prev_balance=bean.getPrevios_balance();
				current_balance=bean.getCurrent_balance();	
				bill_total_amount=prev_balance+current_balance;				
			}
			
		}catch(Exception e){
			logger.error("AccountSummaryDaoImpl :: getBillSubTotalAmount(), Exception: "+e);
		}
		
		return bill_total_amount;
		
	}
	
	public double getSalesTaxAmount(double bill_total_amount){
		
		double sales_tax_amount =0.00;
		
		try{
			
			if(bill_total_amount>0){
				sales_tax_amount=(bill_total_amount*SALES_TAX_RATE)/100;
			}
			
			
		}catch(Exception e){
			logger.error("AccountSummaryDaoImpl :: getBillTotalAmount(), Exception: "+e);
		}
		
		return sales_tax_amount;
		
	}	
	

	
}
