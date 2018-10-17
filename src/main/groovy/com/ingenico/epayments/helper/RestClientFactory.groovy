package com.ingenico.epayments.helper

import groovy.test.GroovyAssert

import javax.inject.Named

import groovy.json.JsonOutput
import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient

import static com.ingenico.epayments.context.TestContext.getEndPointContext

/**
 * The RestClientFactory implements a factory for RESTClients.
 */
@Slf4j
@Named
class RestClientFactory {
    def anonymousRestClient

    /**
     * Get the RESTClient
     *
     * @return RESTClient
     */
    RESTClient get() {
        anonymousRestClient = getRestClient()
        return anonymousRestClient
    }

    /**
     * Create a pre-configured RESTClient for accessing the Connect API.
     *
     * @return RESTClient
     */
    RESTClient getRestClient() {

        // initialize the RESTClient
        def rest = new RESTClient(endPointContext.connectApiBasePath)

        // add default handler
        rest.handler.failure = { resp, reader ->
            def httpRequest = resp.context["http.request"]
            def requestLine = JsonOutput.toJson(httpRequest.requestLine)
            def allHeaders = JsonOutput.toJson(httpRequest.allHeaders)
            def error = "Unexpected failure: ${resp.statusLine}\nResponse: $reader\nRequest: ${JsonOutput.prettyPrint(requestLine)}\nHeaders: ${JsonOutput.prettyPrint(allHeaders)}"
            GroovyAssert.fail(error)
        }

        // link json to text type, so it is parsed by the default JSON parser
        rest.parser.'application/json' = rest.parser.'text/plain'

        return rest
    }

}