@hosted_checkout @ideal @test
Feature: hosted_checkout_ideal
  As a merchant, I want to be able to retrieve the partial redirect url from the CreateHostedCheckout API
  and make payment in the hosted checkout web page using iDeal payment method, so I can complete my payment transactions.

Scenario: complete a transaction using iDeal payment
  Given I create a transaction using CreateHostedCheckout API as
    | currencyCode  | amount  | countryCode | variant | locale |
    | EUR           | 100	  | NL          | test    | en_GB  |
  And I have partialRedirectUrl from API response
  When I navigate to hostedCheckOut page
  And I use iDeal payment method
  Then the payment is successful

#end