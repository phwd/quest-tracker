package com.oculus.horizon.api.common;

public class PurchaseDetails {
    public PriceLabel exemptions;
    public String id;
    public PriceLabel price;
    public String purchase_time;
    public boolean show_store_credit_warning;
    public PriceLabel tax;
    public String terms_of_service;
    public PriceLabel total;
    public PriceLabel usable_store_credit;

    public static class PriceLabel {
        public String formatted;
        public String offset_amount;
    }
}
