package com.newt.JacocoMutationDemo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.newt.JacocoMutationDemo.bean.AccountProfile;
import com.newt.JacocoMutationDemo.logger.Log4jWrapper;
import com.newt.JacocoMutationDemo.util.PropertyLoader;

/**
 * @date 02/01/2016
 * @author Harish Main
 * @description Class contains Account Profile methods
 *
 */
public class AccountProfileDaoImpl implements AccountProfileDao {
	
	private final static Logger logger = Logger.getLogger(AccountProfileDaoImpl.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	PropertyLoader propertyloader = new PropertyLoader();

	/**
	 * @date 02/01/2016
	 * @author Harish Main
	 * @description Method used to find and display the Account number passed.
	 */
	public void findAccount(int account_no) {
		
		logger.debug("AccountProfileDaoImpl :: findAccount(), invoked...");
		
		List<AccountProfile> profileList = new ArrayList<AccountProfile>();
		
		try{
			//get account profile for given account number
			profileList=getAccountProfileList(account_no);
			
			displayAccountProfile(profileList);
			
		}catch(Exception e){
			logger.error("AccountProfileDaoImpl :: findAccount(), Exception: "+e);
		}
	}
	
	public List<AccountProfile> getAccountProfileList(int account_no) {
		
		logger.debug("AccountProfileDaoImpl :: getAccountProfileist(), invoked...");
		List<AccountProfile> tempList = new ArrayList<AccountProfile>();
		List<AccountProfile> profileList = new ArrayList<AccountProfile>();
		AccountProfile bean = null;
		
		try{
			
			tempList=getAllAccountProfiles();
			
			Iterator<AccountProfile> itr = tempList.iterator();
			
			while(itr.hasNext()){
				bean = new AccountProfile();
				bean=itr.next();
				
				if(bean.getAccount_no()==account_no){
					profileList.add(bean);
					break;
				}
				
				
			}

			
		}catch(Exception e){
			logger.error("AccountProfileDaoImpl :: getAccountProfileist(), Exception: "+e);
		}
		
		return profileList;
	}
	
	public AccountProfile getAccountProfileBean(int account_no) {
		
		logger.debug("AccountProfileDaoImpl :: getAccountProfileBean(), invoked...");

		List<AccountProfile> profileList = new ArrayList<AccountProfile>();
		AccountProfile bean = null;
		
		try{
			
			profileList=getAllAccountProfiles();
			
			Iterator<AccountProfile> itr = profileList.iterator();
			
			while(itr.hasNext()){
				bean = new AccountProfile();
				bean=itr.next();
				
				if(bean.getAccount_no()==account_no){
					break;
				}
				
				
			}

			
		}catch(Exception e){
			logger.error("AccountProfileDaoImpl :: getAccountProfileBean(), Exception: "+e);
		}
		
		return bean;
	}	

	/**
	 * @date 02/01/2016
	 * @author Harish Main
	 * @description Method used to get the all Account Profiles from backend
	 */
	public List<AccountProfile> getAllAccountProfiles() {
		
		logger.debug("AccountProfileDaoImpl :: getAllAccountProfiles(), invoked...");
		
		List<AccountProfile> profileList = new ArrayList<AccountProfile>();
		AccountProfile bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy");
		Date date = null;
		String account_holder_name=null;
		String userid=null;
		String password=null;
		try{
			//Hardcoding backend data
			//Record-01
			bean = new AccountProfile();
			bean.setAccount_no(2000001);
			account_holder_name="Blake Johnson";
			userid=propertyloader.getPropertyValue("userid1");	//"user1";
			password=propertyloader.getPropertyValue("password1");	//"1234";
			bean.setAccount_holder_name(account_holder_name);
			bean.setUserid(userid);
			bean.setPassword(password);
			bean.setAccount_name("Internet and TV Account");
			bean.setAccount_nick_name("John");		
			bean.setAddress("1300 W Walnut Hill Lanes, Irving, TX 75038");
			bean.setEmail_address("blake.johnson@mobile.com");
			bean.setPhone_no("(469) 456-3355");
			date = sdf.parse("01-26-2015");
			bean.setAccount_start_date(date);
			profileList.add(bean);
			
			//Record-02
			bean = new AccountProfile();
			bean.setAccount_no(2000002);	
			account_holder_name="Tina Page";
			userid=propertyloader.getPropertyValue("userid2");	//"user2";
			password=propertyloader.getPropertyValue("password2");	//"1234";
			bean.setAccount_holder_name(account_holder_name);
			bean.setUserid(userid);
			bean.setPassword(password);
			bean.setAccount_name("Internet Account");
			bean.setAccount_nick_name("Tina");		
			bean.setAddress("4907 N Glen Dr, Irving 75063");
			bean.setEmail_address("tina.page@mobile.com");
			bean.setPhone_no("(972) 210-4256");
			date = sdf.parse("11-02-2014");
			bean.setAccount_start_date(date);
			profileList.add(bean);
			
			//Record-02
			bean = new AccountProfile();
			bean.setAccount_no(2000003);	
			account_holder_name="Trey Henderson";
			userid=propertyloader.getPropertyValue("userid3");	//"user3";
			password=propertyloader.getPropertyValue("password3");	//"1234";
			bean.setAccount_holder_name(account_holder_name);
			bean.setUserid(userid);
			bean.setPassword(password);
			bean.setAccount_name("TV Account");
			bean.setAccount_nick_name("Troy");		
			bean.setAddress("1200 Grey Sparrow Dr, Katy, TX 77402");
			bean.setEmail_address("trey.henderson@mobile.com");
			bean.setPhone_no("(214) 7454-0792");
			date = sdf.parse("10-15-2013");
			bean.setAccount_start_date(date);
			profileList.add(bean);
			
		}catch(Exception e){
			logger.error("AccountProfileDaoImpl :: getAllAccountProfiles(), Exception: "+e);
		}
		
		return profileList;
	
		
	}
	

	

	public void displayAccountProfile(List<AccountProfile> profileList) {
		
		logger.debug("AccountProfileDaoImpl :: displayAccountProfile(), invoked...");
		
		//AccountProfile bean = new AccountProfile();
		try{
			
			if(profileList!=null && profileList.size()>0){
				logger.info("--------------------------------------------------");
				logger.info("       MONTHLY ACCOUNT STATEMENT                  ");
				logger.info("--------------------------------------------------");
				logger.info("");
				for(AccountProfile bean : profileList){
					logger.info("Account            : "+bean.getAccount_no());
					logger.info("Username           : "+bean.getUserid());
					logger.info("Account Holder Name: "+bean.getAccount_holder_name());
					logger.info("Account Name       : "+bean.getAccount_name());
					logger.info("Phone No           : "+bean.getPhone_no());
					logger.info("Email Address      : "+bean.getEmail_address());
				}
				
			}else{
				logger.warn("");
				logger.warn("*** No Account found ***");
				logger.warn("please check your account number and try again...");
				logger.warn("");
			}
			
			
		}catch(Exception e){
			logger.error("AccountProfileDaoImpl :: displayAccountProfile(), Exception: "+e);
		}
		
	}



	
	
}
