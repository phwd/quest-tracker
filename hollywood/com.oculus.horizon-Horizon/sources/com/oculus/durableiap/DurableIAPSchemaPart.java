package com.oculus.durableiap;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.database.supplier.TablesDbSchemaPart;

@Dependencies({"_UL__ULSEP_com_oculus_durableiap_DurableIAPTable_ULSEP_BINDING_ID"})
public class DurableIAPSchemaPart extends TablesDbSchemaPart {
    public static final String PART_NAME = "durable_iap";
    public static final int VERSION = 1;

    @Inject
    public DurableIAPSchemaPart(DurableIAPTable durableIAPTable) {
        super("durable_iap", 1, ImmutableList.A07(durableIAPTable));
    }
}
