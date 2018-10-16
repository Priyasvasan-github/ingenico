package com.ingenico.epayments.glue;

import com.ingenico.epayments.context.TestContext;
import com.ingenico.epayments.pageActions.PaymentPageActions;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WhenSteps {

    @Steps
    PaymentPageActions paymentPageSteps;


    @When("I navigate to hostedCheckOut page")
    public void navigateToHostedUrl () {
        paymentPageSteps.launchWebBrowser();
        paymentPageSteps.isPaymentPageLoaded();
    }

    @When("I use iDeal payment method")
    public void payWithIdeal () {
        paymentPageSteps.selectIdealPaymentOption();
        paymentPageSteps.selectRABOBankOption();
        paymentPageSteps.confirmPayment();
    }
}
