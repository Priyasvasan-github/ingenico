package com.ingenico.epayments.pageActions;

import com.ingenico.epayments.context.TestContext;
import com.ingenico.epayments.pageObjects.PaymentPage;
import net.thucydides.core.annotations.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by x000810 on 10/16/2018.
 */
public class PaymentPageActions {

    private PaymentPage paymentPage;
    Properties prop = new Properties();
    InputStream input = null;

    /**
     * This Method Launches web browser and navigates to payment URL
     */
    @Step
    public void launchWebBrowser(){
        paymentPage.openAt(TestContext.getCreateHostedCheckoutContext().getCheckoutUrl());
    }

    /**
     * This method returns if Payment page is loaded or not
     * @return
     */
    @Step
    public boolean isPaymentPageLoaded(){
       return paymentPage.isPaymentPageHeaderLoaded();
    }

    /**
     * This method select iDEAL as payment option from 20 options
     */
    @Step
    public void selectIdealPaymentOption(){
        paymentPage.clickIdealPaymentOption();
    }

    /**
     * This method selects RABO bank as payment option
     */
    @Step
    public void selectRABOBankOption(){
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
     * @return
     */
    @Step
    public String getPaymentStatusMessage(){
        return paymentPage.getStatusMessage();
    }
    /**
     * This method returns the input values provided in config value
     * @param propName
     * @return
     */
    public String getPropValue(String propName){
        try{
            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
            return prop.getProperty(propName);
        }catch (IOException exception){
            return null;
        }
    }

}