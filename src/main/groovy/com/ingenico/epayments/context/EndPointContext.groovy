package com.ingenico.epayments.context

import groovy.util.logging.Slf4j

/**
 * The EndPointContext is a container for end-point configuration
 */
@Slf4j
class EndPointContext {
    String apiKey = "c334d0b975538d97"
    String apiSecret = "Onbt+O3mMgMUN022iZ5L+yDZ/AIPHW4abFE5OajeguA="
    String merchantKey = "2777"
    String connectApiBasePath = "https://eu.sandbox.api-ingenico.com/v1/" + merchantKey

}