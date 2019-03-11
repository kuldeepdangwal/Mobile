package com.cg.billing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.billing.beans.Customer;
import com.cg.billing.beans.PostpaidAccount;
@Controller
public class URIController {
    private Customer customer;
    private PostpaidAccount postpaidAccount;
    
    @RequestMapping("/")
    public String getIndexPage() {
        return "indexPage";
    }
    
    @RequestMapping("/registration")
    public String getRegistrationPage() {
        return "registrationPage";
    }
    @RequestMapping("/openPostpaidAccount")
    public String getPostpaidAccountPage() {
        return "postpaidAccountPage";
    }
    @RequestMapping("/addPlan")
    public String getaddPlanPage() {
        return "addPlanPage";
    }
    @RequestMapping("/billgeneration")
    public String getgenerateBillPage() {
        return "generateBillPage";
    }
    @RequestMapping("/getCustomerDetails")
    public String getCustomerDetails() {
        return "customerDetailsPage";
    }
    @RequestMapping("/getpostpaidAccountDetails")
    public String getpostpaidAccountDetails() {
        return "postpaidAccountDetailsPage";
    }
    @RequestMapping("/getMonthlyBill")
    public String getMonthlyBillPage() {
        return "monthlyBillPage";
    }
    @RequestMapping("/changePlan")
    public String getchangePlanPage() {
        return "changePlanPage";
    }
    @RequestMapping("/closeAccount")
    public String getCloseAccountPage() {
        return "closeAccountPage";
    }
    @RequestMapping("/deleteCustomerDetails")
    public String getdeleteCustomerPage() {
        return "deleteCustomerPage";
    }
    @ModelAttribute
    public Customer getCustomer() {
    customer=new Customer();
    return customer;
    }
    @ModelAttribute
    public PostpaidAccount getPostpaidAccount() {
    postpaidAccount=new PostpaidAccount();
    return postpaidAccount;
    }
    
}
