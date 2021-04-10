package com.oculus.horizon.api.common;

import com.oculus.horizon.api.common.PaymentMethod;
import java.util.List;

public class PaymentAccount {
    public StoredCredit stored_credit;

    public static class StoredCredit {
        public PaymentMethod.Balance balance;
        public String id;
        public Subcredits subcredits;

        public static class Subcredits {
            public List<Subcredit> nodes;

            public static class Subcredit {
                public static final String ACTIVE = "ACTIVE";
                public static final String EXPIRED = "EXPIRED";
                public PaymentMethod.Balance balance;
                public String expiration_time;
                public String subcredit_status;
            }
        }

        public boolean isValid() {
            Subcredits subcredits2;
            return (this.id == null || this.balance == null || (subcredits2 = this.subcredits) == null || subcredits2.nodes == null) ? false : true;
        }
    }
}
