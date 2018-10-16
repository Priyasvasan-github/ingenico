package com.ingenico.epayments.steps

import com.ingenico.epayments.context.EndPointContext
import com.ingenico.epayments.context.TestContext
import com.ingenico.epayments.helper.RestClientFactory
import com.ingenico.epayments.steps.AuthenticationSteps

import groovy.util.logging.Slf4j
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import net.thucydides.core.annotations.Steps

import javax.inject.Named
import javax.inject.Inject

import static com.ingenico.epayments.context.TestContext.getCreateHostedCheckoutContext
import static com.ingenico.epayments.context.TestContext.getCreateHostedCheckoutContext

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT

@Slf4j
@Named
public class CreateHostedCheckoutSteps {

    @Steps EndPointContext endPointContext = TestContext.getEndPointContext()
    @Steps AuthenticationSteps authenticationSteps
    @Steps RestClientFactory restClientFactory

    String httpMethod = "POST"
    String contentType = "application/json;charset=utf8"
    String merchantKey =  endPointContext.getMerchantKey()

    String uri = "/v1/" + merchantKey + "/hostedcheckouts"

    Date date = new Date()
    String utcDateTime = date.format('EEE, dd MMM yyyy HH:mm:ss z', TimeZone.getTimeZone('GMT'))

    String message = httpMethod + "\n" + contentType + "\n" + utcDateTime + "\n" + uri + "\n"

    /**
     * Get the reservation (containing passenger-, connection- and segment details) from the resource store
     *
     */
    def postCreateHostedCheckout() {
        def createHostedCheckoutContext = createHostedCheckoutContext

        String signature = authenticationSteps.hmacSha256 (message)

        String authorization = 'GCS v1HMAC:' + endPointContext.getApiKey() + ':' + signature

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

        def request = [
                uri: endPointContext.getConnectApiBasePath(),
                path: "/hostedcheckouts",
                headers: [
                        "Date": utcDateTime,
                        "Authorization": authorization,
                ],
                contentType: contentType,
                requestContentType: JSON,
                body: body
        ]

        restClientFactory.get().post(request) { resp, reader ->
            def response = reader

            String partialRedirectUrl = response.partialRedirectUrl
            System.out.println(partialRedirectUrl)
        }
    }
}
