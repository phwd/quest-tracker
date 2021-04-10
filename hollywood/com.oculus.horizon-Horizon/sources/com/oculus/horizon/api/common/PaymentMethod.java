package com.oculus.horizon.api.common;

public class PaymentMethod {
    public static final String CREDIT_CARD_TYPE_NAME = "ExternalCreditCard";
    public static final String PAYPAL_TYPE_NAME = "PaymentPaypalBillingAgreement";
    public static final String STORE_CREDIT_TYPE_NAME = "StoredCredit";
    public String __typename;
    public Balance balance;
    public String card_type;
    public String display_name;
    public String email;
    public String id;
    public String last4;
    public String paypal_context;

    public static class Balance {
        public int amount_in_hundredths;
        public String currency;
        public String formatted_amount;
    }
}
