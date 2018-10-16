package com.ingenico.epayments.context

import groovy.util.logging.Slf4j

/**
 *  The CreateHostedCheckoutContext is a container for CreateHostedCheckout service specific configuration
 */
@Slf4j
class CreateHostedCheckoutContext {

    // Generic contextual attributes
    String currencyCode
    String amount
    String countryCode
    String variant
    String locale
    String checkoutUrl
}