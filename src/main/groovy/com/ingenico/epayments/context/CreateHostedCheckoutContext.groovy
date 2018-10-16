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

}
