package com.ingenico.epayments.steps

import com.ingenico.epayments.context.TestContext

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.InvalidKeyException

import groovy.util.logging.Slf4j

/**
 * Form signature using HMAC-SHA256 algorithm to authenticate the API calls
 */
@Slf4j
public class AuthenticationSteps {

    String secretKey = TestContext.getEndPointContext().getApiSecret()

    def hmacSha256(String message) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256")
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")
            mac.init(secretKeySpec)
            byte[] digest = mac.doFinal(message.getBytes())
            def encodedData = digest.encodeBase64().toString()
            return encodedData
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key exception while converting to HMac SHA256")
        }
    }

}