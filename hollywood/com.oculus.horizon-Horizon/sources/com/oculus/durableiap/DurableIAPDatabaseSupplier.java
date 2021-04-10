package com.oculus.durableiap;

import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.database.supplier.AbstractDatabaseSupplier;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_durableiap_DurableIAPSchemaPart_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DurableIAPDatabaseSupplier extends AbstractDatabaseSupplier {
    public static final String DATABASE_NAME = "durable_iap_database";
    public static volatile DurableIAPDatabaseSupplier _UL__ULSEP_com_oculus_durableiap_DurableIAPDatabaseSupplier_ULSEP_INSTANCE;

    @Inject
    public DurableIAPDatabaseSupplier(@UnsafeContextInjection Context context, IErrorReporter iErrorReporter, DurableIAPSchemaPart durableIAPSchemaPart) {
        super(context, iErrorReporter, ImmutableList.A07(durableIAPSchemaPart), DATABASE_NAME);
    }
}
