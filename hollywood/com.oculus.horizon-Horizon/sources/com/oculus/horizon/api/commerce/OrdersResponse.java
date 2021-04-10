package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.Item;
import com.oculus.horizon.api.common.PaymentMethod;
import com.oculus.horizon.api.common.PurchaseDetails;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.List;

@SingleEntryMapResponse
public class OrdersResponse implements ValidatableApiResponse {
    public PaymentAccount payment_account;

    public static class PaymentAccount {
        public DigitalOrders digital_orders;

        public static class DigitalOrder {
            public Item app_store_item;
            public PaymentMethod payment_method;
            public PurchaseDetails purchase;
            public Error refund_not_available_error;
            public String refund_status;
        }

        public static class DigitalOrders {
            public List<DigitalOrder> nodes;
        }

        public static class Error {
            public String description;
        }
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
    }
}
