package com.facebook.quicklog.identifiers;

public class DigitalContentPayments {
    public static final short MODULE_ID = 435;
    public static final int PURCHASE_FLOW = 28508161;
    public static final int PURCHASE_FLOW_BY_PRODUCT = 28508162;
    public static final int WALLET_BALANCE_QUERY_FLOW = 28508163;
    public static final int WALLET_SPEND_MUTATION_FLOW = 28508164;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "DIGITAL_CONTENT_PAYMENTS_WALLET_SPEND_MUTATION_FLOW" : "DIGITAL_CONTENT_PAYMENTS_WALLET_BALANCE_QUERY_FLOW" : "DIGITAL_CONTENT_PAYMENTS_PURCHASE_FLOW_BY_PRODUCT" : "DIGITAL_CONTENT_PAYMENTS_PURCHASE_FLOW";
    }
}
