package com.ingenico.epayments.context

import groovy.util.logging.Slf4j

/**
 * CreateHostedCheckoutContext context factory
 */
@Slf4j
class CreateHostedCheckoutContext {

    // Generic contextual attributes
    String currencyCode
    String amount
    String countryCode
    String variant
    String locale
    String partialRedirectUrl = "https://payment.pay1.sandbox.secured-by-ingenico.com/checkout/2777-dbb4b09692034f6a805ed0f73d54e460:7e758881-2814-4201-b6c3-96c3649ac57a:4341498fdb774101a04fdb3408e6aec5";

}
