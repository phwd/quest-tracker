package com.oculus.appmanager.info.database;

import X.AbstractC06640p5;
import X.AnonymousClass0Pi;
import X.AnonymousClass117;
import X.C003108z;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.schema.ApkUpdateSchemaPart;
import com.oculus.database.supplier.AbstractDatabaseSupplier;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateSchemaPart_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ApkUpdateDatabaseSupplier extends AbstractDatabaseSupplier {
    public static final String DATABASE_NAME = "apk_update_service";
    public static volatile ApkUpdateDatabaseSupplier _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE;

    @AutoGeneratedAccessMethod
    public static final ApkUpdateDatabaseSupplier A00(AbstractC06640p5 r1) {
        return (ApkUpdateDatabaseSupplier) AnonymousClass117.A00(336, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final ApkUpdateDatabaseSupplier A01(AbstractC06640p5 r6) {
        if (_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE == null) {
            synchronized (ApkUpdateDatabaseSupplier.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE, r6);
                if (A00 != null) {
                    try {
                        AbstractC06640p5 applicationInjector = r6.getApplicationInjector();
                        _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE = new ApkUpdateDatabaseSupplier(C003108z.A02(applicationInjector), InterfacesModule.A00(applicationInjector), ApkUpdateSchemaPart.A00(applicationInjector));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE;
    }

    @Inject
    public ApkUpdateDatabaseSupplier(@UnsafeContextInjection Context context, IErrorReporter iErrorReporter, ApkUpdateSchemaPart apkUpdateSchemaPart) {
        super(context, iErrorReporter, ImmutableList.A07(apkUpdateSchemaPart), DATABASE_NAME);
    }
}