package com.newt.JacocoMutationDemo.dao;

import java.util.List;

import com.newt.JacocoMutationDemo.bean.AccountProfile;

public interface AccountProfileDao {

	List<AccountProfile> getAccountProfileList(int account_no);

	List<AccountProfile> getAllAccountProfiles();
	
	void findAccount(int account_no);
	
	void displayAccountProfile(List<AccountProfile> profileList);
	
}
