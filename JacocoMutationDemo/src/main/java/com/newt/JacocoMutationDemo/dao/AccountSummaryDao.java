package com.newt.JacocoMutationDemo.dao;

import java.util.List;

import com.newt.JacocoMutationDemo.bean.AccountProfile;
import com.newt.JacocoMutationDemo.bean.AccountSummary;

public interface AccountSummaryDao {
	
	List<AccountSummary> getAccountSummaryList(int account_no);
	
	List<AccountSummary> getAllAccountSummary();
	
	void findSummaryAccount(int account_no);
	
	void displayAccountSummary(List<AccountSummary> summaryList);

}
