package com.ingenico.epayments.context
/**
 * The TestContext is a container to hold the context shared in a feature run
 */
public class TestContext {

//    private static ThreadLocal<CreateHostedCheckoutContext> createHostedCheckoutContextThreadLocal = new ThreadLocal<CreateHostedCheckoutContext>()
    private static CreateHostedCheckoutContext createHostedCheckoutContext= new CreateHostedCheckoutContext()

    private static EndPointContext endPointContext= new EndPointContext()

//    static def init() {createHostedCheckoutContextThreadLocal.set(new CreateHostedCheckoutContext())}
//
//    static def clear() {createHostedCheckoutContextThreadLocal.set(null)}
//
//    static CreateHostedCheckoutContext getCreateHostedCheckoutContext() {
//        return createHostedCheckoutContextThreadLocal.get()
//    }

    static CreateHostedCheckoutContext getCreateHostedCheckoutContext() {
        return createHostedCheckoutContext
    }

    static EndPointContext getEndPointContext() {
        return endPointContext
    }

}
