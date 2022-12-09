package com.bank.testsuite;



import com.bank.pages.*;
import com.bank.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankTest extends BaseTest {
    HomePage homePage = new HomePage();
    BankManagerLoginPage bankManagerLoginPage = new BankManagerLoginPage();
    AddCustomerPage addCustomerPage = new AddCustomerPage();
    CustomersPage customersPage = new CustomersPage();
    OpenAccountPage openAccountPage = new OpenAccountPage();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage();
    AccountPage accountPage = new AccountPage();

    @Test
    public void bankManagerShouldAddCustomerSuccessfully() {
        //click On "Bank Manager Login" Tab
        homePage.clickOnBankManagerLoginTab();
        //click On "Add Customer" Tab
        bankManagerLoginPage.clickOnAddCustomerTab();
        //enter FirstName
        addCustomerPage.enterFirstName("Abc");
        //enter LastName
        addCustomerPage.enterLastName("Xyz");
        //enter PostCode
        addCustomerPage.enterPostcode("EC1B 2JL");
        //click On "Add Customer" Button
        addCustomerPage.clickOnAddCustomerButton();
        //popup display
        //verify message "Customer added successfully"
        Assert.assertEquals(bankManagerLoginPage.getPopupMsg(),
                "Customer added successfully with customer id :6",
                "Message not found");
        //click on "ok" button on popup
        bankManagerLoginPage.clickOKOnPopup();
    }

    @Test
    public void bankManagerShouldOpenAccountSuccessfully() {
        //click On "Bank Manager Login" Tab
        homePage.clickOnBankManagerLoginTab();
        //click On "Open Account" Tab
        bankManagerLoginPage.clickOnOpenAccountTab();
        //Search customer that created in first test
        openAccountPage.searchAndSelectCreatedCustomer();
        //Select currency "Pound"
        openAccountPage.selectPoundInCurrency();
        //click on "process" button
        openAccountPage.clickOnProcessButton();
        //popup displayed
        //verify message "Account created successfully"
        Assert.assertEquals(bankManagerLoginPage.getPopupMsg(), "Account created successfully with account Number :1016", "Message not found");
        //click on "ok" button on popup
        bankManagerLoginPage.clickOKOnPopup();
    }

    @Test
    public void customerShouldLoginAndLogoutSuccessfully() {
        //click on "Customer Login" Tab
        homePage.clickOnCustomerLoginTab();
        //search customer that you created.
        customersPage.searchAndSelectNameFormDropdown("Harry Potter");
        //click on "Login" Button
        customerLoginPage.clickOnLoginButton();
        //verify "Logout" Tab displayed
        //Assert.assertEquals(customerLoginPage.getLogoutText(),"Logout","Text not found");
        //click on "Logout"
        customerLoginPage.clickOnLogoutButton();
        //verify "Your Name" text displayed
        Assert.assertEquals(customerLoginPage.getNameTextAfterLogout(), "Your Name :", "Text not found");
    }

    @Test
    public void customerShouldDepositMoneySuccessfully() {
        //click on "Customer Login" Tab
        homePage.clickOnCustomerLoginTab();
        //search customer that you created.
        customersPage.searchAndSelectNameFormDropdown("Harry Potter");
        //click on "Login" Button
        customerLoginPage.clickOnLoginButton();
        //click on "Deposit" Tab
        accountPage.clickOnDepositTab();
        //Enter amount 100
        accountPage.enterAmountToDeposit("1000");
        //click on "Deposit" Button
        accountPage.clickOnDepositButton();
        //verify message "Deposit Successful"
        Assert.assertEquals(accountPage.getDepositMsg(),"Deposit Successful","Message not found");
    }

    @Test
    public void customerShouldWithdrawMoneySuccessfully() throws InterruptedException {
        //click on "Customer Login" Tab
        homePage.clickOnCustomerLoginTab();
        //search customer that you created
        customersPage.searchAndSelectNameFormDropdown("Harry Potter");
        //click on "Login" Button
        customerLoginPage.clickOnLoginButton();
        //click on "Withdrawl" Tab
        accountPage.clickOnWithdrawTab();
        //Enter amount 50
        accountPage.enterAmountToWithdraw("50");
        //click on "Withdraw" Button
        //Thread.sleep(1000);
        accountPage.clickOnWithdrawButton();
        //verify message "Transaction Successful"
        //Thread.sleep(1000);
        Assert.assertEquals(accountPage.getWithdrawMsg(),"Transaction Successful","Message not found");
    }
}