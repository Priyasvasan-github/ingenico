package com.ingenico.epayments.steps

import com.ingenico.epayments.context.EndPointContext
import com.ingenico.epayments.context.TestContext
import com.ingenico.epayments.helper.RestClientFactory
import static com.ingenico.epayments.context.TestContext.getCreateHostedCheckoutContext

import groovy.json.JsonSlurper

import net.thucydides.core.annotations.Step
import net.thucydides.core.annotations.Steps

import groovy.util.logging.Slf4j

import javax.inject.Named

import static groovyx.net.http.ContentType.JSON

/**
 * Steps for interacting with CreateHostedCheckout API to get partialRedirectUrl
 */
@Slf4j
@Named
class CreateHostedCheckoutSteps {

    @Steps EndPointContext endPointContext = TestContext.getEndPointContext()
    @Steps AuthenticationSteps authenticationSteps
    @Steps RestClientFactory restClientFactory

    String httpMethod = "POST"
    String contentType = "application/json;charset=UTF-8"
    String merchantKey =  endPointContext.getMerchantKey()
    String customHeaderOne = "x-gcs-messageid:6480071e-039d-4dca-a966-4ce3c1bc201b"
    String customHeaderTwo = "x-gcs-requestid:1cc6daff-a305-4d7b-94b0-c580fd5ba6b4"
    String uri = "/v1/" + merchantKey + "/hostedcheckouts"

    Date date = new Date()
    String utcDateTime = date.format('EEE, dd MMM yyyy HH:mm:ss z', TimeZone.getTimeZone('GMT'))

    String message = httpMethod + "\n" + contentType + "\n" + utcDateTime + "\n" + customHeaderOne + "\n" + customHeaderTwo + "\n" + uri + "\n"

    /**
     * Post and get the response of CreateHostedCheckout API service
     *
     */
    @Step
    def postCreateHostedCheckout() {
        def createHostedCheckoutContext = createHostedCheckoutContext

        // Get the HMAC-SHA256 signature
        String signature = authenticationSteps.hmacSha256 (message)

        String authorization = 'GCS v1HMAC:' + endPointContext.getApiKey() + ':' + signature

        // Request body
        def body = [
                'order': [
                        'amountOfMoney': [
                                'currencyCode': createHostedCheckoutContext.currencyCode,
                                'amount': createHostedCheckoutContext.amount
                        ],
                        'customer': [
                                'merchantCustomerId': merchantKey,
                                'billingAddress': [
                                        'countryCode': createHostedCheckoutContext.countryCode
                                ]
                        ]
                ],
                'hostedCheckoutSpecificInput': [
                        'variant': createHostedCheckoutContext.variant,
                        'locale': createHostedCheckoutContext.locale
                ]
        ]

        // Form the POST request
        def request = [
                uri: endPointContext.getConnectApiBasePath() + "/hostedcheckouts",
                body: body,
                requestContentType: JSON,
                contentType: JSON,
                headers: [
                        'Date': utcDateTime,
                        'Authorization': authorization,
                        'Content-Type': contentType,
                        'X-GCS-MessageId': "6480071e-039d-4dca-a966-4ce3c1bc201b",
                        'X-GCS-RequestId': "1cc6daff-a305-4d7b-94b0-c580fd5ba6b4"
                ]
        ]

        // Call the RESTClient and extract the partialRedirectUrl
        restClientFactory.get().post(request){ resp, reader ->
            assert resp.statusLine.statusCode == 201 : "CreateHostedCheckOut API response does not have HTTP response status 201!"

            // Parse response to JSON
            def jsonResponse = new JsonSlurper().parseText(reader.text)
            assert jsonResponse.partialRedirectUrl : "No partialRedirectUrl in api response"

            // Extract the partialRedirect URL
            String partialRedirectUrl = jsonResponse.partialRedirectUrl

            // Concatenate the checkout URL
            createHostedCheckoutContext.checkoutUrl = "https://payment." + partialRedirectUrl
        }
    }

}