package com.ingenico.epayments.glue;

import com.ingenico.epayments.pageActions.PaymentPageActions;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import javax.inject.Named;

/**
 * When definitions
 */
@SuppressWarnings("squid:UndocumentedApi")
@Named
public class WhenSteps {

    @Steps PaymentPageActions paymentPageSteps;

    /**
     * Navigate to the hosted checkout page using web driver
     */
    @When("^I navigate to hostedCheckOut page$")
    public void navigateToHostedUrl () {
        paymentPageSteps.launchWebBrowser();
        paymentPageSteps.checkPaymentPageLoaded();
    }

    /**
     * Make payment using iDeal payment method
     */
    @When("^I use (.*) payment method$")
    public void payWithIdeal (String paymentMethod) {
        paymentPageSteps.selectPaymentOption(paymentMethod);
        paymentPageSteps.selectIssuer();
        paymentPageSteps.confirmPayment();
    }

}