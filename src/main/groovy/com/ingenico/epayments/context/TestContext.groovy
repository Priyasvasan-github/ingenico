package com.ingenico.epayments.context

import groovy.util.logging.Slf4j

/**
 * The TestContext is a container to hold the context shared in a feature run
 */
@Slf4j
public class TestContext {

    private static CreateHostedCheckoutContext createHostedCheckoutContext= new CreateHostedCheckoutContext()

    private static EndPointContext endPointContext= new EndPointContext()

    static CreateHostedCheckoutContext getCreateHostedCheckoutContext() {
        return createHostedCheckoutContext
    }

    static EndPointContext getEndPointContext() {
        return endPointContext
    }

}