package com.ingenico.epayments.pageActions;

import com.ingenico.epayments.context.TestContext;
import com.ingenico.epayments.pageObjects.PaymentPage;
import net.thucydides.core.annotations.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The PaymentPageActions class implements steps to use a browser to redirect to Ingenico hosted checkout page
 */
public class PaymentPageActions {

    private PaymentPage paymentPage;
    Properties properties = new Properties();
    InputStream input = null;

    /**
     * This Method Launches web browser and navigates to payment URL
     */
    @Step
    public void launchWebBrowser(){
        paymentPage.openAt(TestContext.getCreateHostedCheckoutContext().getCheckoutUrl());
    }

    /**
     * This method returns if payment page is loaded or not
     * @return the paymentPage
     */
    @Step
    public boolean checkPaymentPageLoaded(){
       return paymentPage.isPaymentPageHeaderLoaded();
    }

    /**
     * This method select iDEAL as payment option from 20 options
     */
    @Step
    public void selectPaymentOption(String paymentMethod){
        if (paymentMethod.equals("iDeal")) {
            paymentPage.clickIdealPaymentOption();
        }
    }

    /**
     * This method selects RABO bank as payment option
     */
    @Step
    public void selectIssuer(){
        paymentPage.selectValue(getPropValue("paymentBankValue"));
        paymentPage.clickPayButton();
    }

    /**
     * This method confirms the Payment
     */
    @Step
    public void confirmPayment(){
        paymentPage.clickConfirmTransactionButton();
    }

    /**
     * This method returns payment staus message
     * @return the paymentPage
     */
    @Step
    public String getPaymentStatusMessage(){
        return paymentPage.getStatusMessage();
    }
    /**
     * This method returns the input values provided in config value
     * @param propName
     * @return the properties
     */
    public String getPropValue(String propName){
        try{
            input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            return properties.getProperty(propName);
        }catch (IOException exception){
            return null;
        }
    }

}