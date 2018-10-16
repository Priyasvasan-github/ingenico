package com.ingenico.epayments.glue;

import com.ingenico.epayments.pageActions.PaymentPageActions;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import javax.inject.Named;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Then definitions
 */
@SuppressWarnings("squid:UndocumentedApi")
@Named
public class ThenSteps {

    @Steps PaymentPageActions paymentPageSteps;

    /**
     * Verify if the transaction is successfully completed
     */
    @Then("^the payment is successful$")
    public void verifyPaymentIsSuccessful () {
        assertThat(paymentPageSteps.getPaymentStatusMessage()).isEqualToIgnoringCase("Your payment is successful.");
    }

}