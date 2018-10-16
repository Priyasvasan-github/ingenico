package com.ingenico.epayments.glue;

import com.ingenico.epayments.pageActions.PaymentPageActions;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import static org.assertj.core.api.Assertions.assertThat;

public class ThenSteps {

    @Steps
    PaymentPageActions paymentPageSteps;

    @Then("the payment is successful")
    public void verifyPaymentIsSuccessful () {
        assertThat(paymentPageSteps.getPaymentStatusMessage()).isEqualToIgnoringCase("Your payment is successful.");
    }
}
