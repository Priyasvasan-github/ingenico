package com.ingenico.epayments.glue;

import com.ingenico.epayments.context.TestContext;
import com.ingenico.epayments.steps.CreateHostedCheckoutSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Given definitions
 */
@SuppressWarnings("squid:UndocumentedApi")
@Named
public class GivenSteps {

    @Steps CreateHostedCheckoutSteps createHostedCheckoutSteps;

    /**
     * Use the GET orders API to retrieve the root resource of the Order API.
     */
    @Given("^I create a transaction using CreateHostedCheckout API as$")
    public void SetCreateHostedCheckoutContext(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        TestContext.getCreateHostedCheckoutContext().setCurrencyCode(list.get(0).get("currencyCode"));
        TestContext.getCreateHostedCheckoutContext().setAmount(list.get(0).get("amount"));
        TestContext.getCreateHostedCheckoutContext().setCountryCode(list.get(0).get("countryCode"));
        TestContext.getCreateHostedCheckoutContext().setVariant(list.get(0).get("variant"));
        TestContext.getCreateHostedCheckoutContext().setLocale(list.get(0).get("locale"));
    }

    /**
     * Use the GET orders API to retrieve the root resource of the Order API.
     */
    @Given("^I have partialRedirectUrl from API response$")
    public void GetPartialRedirectUrl () {
        createHostedCheckoutSteps.postCreateHostedCheckout();
    }
}
