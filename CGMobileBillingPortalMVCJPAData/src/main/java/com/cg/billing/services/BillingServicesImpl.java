package com.cg.billing.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.daoservices.BillDaoServices;
import com.cg.billing.daoservices.CustomerDaoServices;
import com.cg.billing.daoservices.PlanDaoServices;
import com.cg.billing.daoservices.PostpaidAccountDaoServices;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
	@Autowired
	CustomerDaoServices customerServicesImpl;
	@Autowired
	PlanDaoServices planServicesImpl;
    @Autowired
	PostpaidAccountDaoServices accountServicesImpl;
	@Autowired
	BillDaoServices billServicesImpl;
	@Override
	public List<Plan> getPlanAllDetails() {
		return planServicesImpl.findAll();
	}
	@Override
	public Customer acceptCustomerDetails(Customer customer) {
		customer=customerServicesImpl.save(customer);
		return customer;
	}
	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		Customer customer=getCustomerDetails(customerID);
		Plan plan=planServicesImpl.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("No such plan available"));
		PostpaidAccount postpaidAccount=new PostpaidAccount(plan, customer);
		postpaidAccount=accountServicesImpl.save(postpaidAccount);
		return postpaidAccount.getMobileNo();
	}
	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
					throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount = getPostPaidAccountDetails(customerID, mobileNo);
		Plan plan=postpaidAccount.getPlan();
		if(plan==null)
			throw new PlanDetailsNotFoundException("Plan details not found.");
		int localSMSAmount=0,stdSMSAmount=0,localCallAmount=0,stdCallAmount=0,internetDataUsageAmount=0,monthlyRental=0,billAmount=0,stateGST=0,centralGST=0,totalBillAmount=0;
		if(noOfLocalSMS>plan.getFreeLocalSMS())
			localSMSAmount = (int) ((noOfLocalSMS-plan.getFreeLocalSMS())*plan.getLocalSMSRate());
		if(noOfLocalCalls>plan.getFreeLocalCalls())
			localCallAmount=(int) ((noOfLocalCalls-plan.getFreeLocalCalls())*plan.getLocalCallRate());
		if(noOfStdSMS>plan.getFreeStdSMS())
			stdSMSAmount=(int) ((noOfStdSMS-plan.getFreeStdSMS())*plan.getStdSMSRate());
		if(noOfStdCalls>plan.getFreeStdCalls())
			stdCallAmount=(int) ((noOfStdCalls-plan.getFreeStdCalls())*plan.getStdCallRate());
		if(internetDataUsageUnits>plan.getFreeInternetDataUsageUnits())
			internetDataUsageAmount=(int) ((internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate());
		monthlyRental= plan.getMonthlyRental();
		billAmount=monthlyRental+localSMSAmount+stdSMSAmount+localCallAmount+stdCallAmount+internetDataUsageAmount;
		stateGST=(int)billAmount*10/100;
		centralGST=(int)billAmount*5/100;
		totalBillAmount = billAmount+stateGST+centralGST;
		Bill bill=new Bill(postpaidAccount, noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth, totalBillAmount, localSMSAmount, stdSMSAmount, localCallAmount, stdCallAmount, internetDataUsageAmount, stateGST, centralGST);
		bill=billServicesImpl.save(bill);
		return totalBillAmount;
	}
	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		Customer customer=customerServicesImpl.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not found"));
		return customer;
	}
	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerServicesImpl.findAll();
	}
	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		PostpaidAccount postpaidAccount = null;
		if(getCustomerDetails(customerID).getPostpaidAccounts().containsKey(mobileNo))		
		postpaidAccount= accountServicesImpl.findById(mobileNo).orElseThrow(()->new PostpaidAccountNotFoundException("Postpaid Account not found"));
		return postpaidAccount;
		

	}
	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		Customer customer=getCustomerDetails(customerID);
		List<PostpaidAccount> postpaidAccountList=new ArrayList<PostpaidAccount>(customer.getPostpaidAccounts().values());
		return postpaidAccountList;
	}
	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
		Bill bill;
		getPostPaidAccountDetails(customerID, mobileNo);
		if(billMonth.equalsIgnoreCase("January")||billMonth.equalsIgnoreCase("February")||
				billMonth.equalsIgnoreCase("march")||billMonth.equalsIgnoreCase("april")||
				billMonth.equalsIgnoreCase("June")||billMonth.equalsIgnoreCase("may")||
				billMonth.equalsIgnoreCase("July")||billMonth.equalsIgnoreCase("august")||
				billMonth.equalsIgnoreCase("september")||billMonth.equalsIgnoreCase("october")||
				billMonth.equalsIgnoreCase("november")||billMonth.equalsIgnoreCase("december"))
			bill= billServicesImpl.getMonthlyMobileBill( mobileNo, billMonth);
		else
			throw new InvalidBillMonthException("Enter valid month");
		if(bill==null) {
			throw new BillDetailsNotFoundException("No bill found for this Id");
		}
		return bill;
	}
	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		PostpaidAccount postpaidAccount=getPostPaidAccountDetails(customerID, mobileNo);
		List<Bill> bills=new ArrayList<Bill>(postpaidAccount.getBills().values());
		return bills;
	}
	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount=getPostPaidAccountDetails(customerID, mobileNo);
		//postpaidAccount.getPlan().setPlanID(planID);
		Plan plan=planServicesImpl.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("No such plan available"));
		postpaidAccount=new PostpaidAccount(mobileNo,plan,getCustomerDetails(customerID));
		accountServicesImpl.save(postpaidAccount);
		return true;
	}
	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		getPostPaidAccountDetails(customerID, mobileNo);
		accountServicesImpl.deleteById(mobileNo);;
		return true;
	}
	@Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		Customer customer=getCustomerDetails(customerID);
		customerServicesImpl.delete(customer);
		return true;
	}
	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount=getPostPaidAccountDetails(customerID, mobileNo);
		return postpaidAccount.getPlan();
	}
	@Override
	public Plan acceptPlanDetails(Plan plan) {
		return null;
	}
}
