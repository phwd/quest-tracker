package com.oculus.durableiap;

import com.oculus.downloader.extras.contract.ExtrasDatabase;
import com.oculus.platform.OVRServiceManager;

public abstract class DurableIAPDBContract {
    public static final String TABLE_NAME = "durable_iap";

    public static class Column {
        public final String name;
        public final String type = ExtrasDatabase.ColumnsTypes.TEXT;

        public Column(String str) {
            this.name = str;
        }
    }

    public static abstract class Columns {
        public static Column APPLICATION_GROUPING_ID = new Column("application_grouping_id");
        public static Column EXPIRATION_TIME = new Column("expiration_time");
        public static Column GRANT_TIME = new Column("grant_time");
        public static Column PURCHASE_ID = new Column("purchase_id");
        public static Column SKU = new Column(OVRServiceManager.IAP_SKU_KEY);
        public static Column USER_ID = new Column("user_id");
    }
}
