package com.oculus.appmanager.info.database;

import android.content.Context;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.schema.ApkUpdateSchemaPart;
import com.oculus.database.supplier.AbstractDatabaseSupplier;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateSchemaPart_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ApkUpdateDatabaseSupplier extends AbstractDatabaseSupplier {
    private static final String DATABASE_NAME = "apk_update_service";
    private static volatile ApkUpdateDatabaseSupplier _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE;

    @AutoGeneratedAccessMethod
    public static final ApkUpdateDatabaseSupplier _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ApkUpdateDatabaseSupplier) UL.factorymap.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ApkUpdateDatabaseSupplier _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE == null) {
            synchronized (ApkUpdateDatabaseSupplier.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        InjectorLike applicationInjector = injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE = new ApkUpdateDatabaseSupplier(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(applicationInjector), InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(applicationInjector), ApkUpdateSchemaPart._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateSchemaPart_ULSEP_ACCESS_METHOD(applicationInjector));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ApkUpdateDatabaseSupplier(@UnsafeContextInjection Context context, IErrorReporter iErrorReporter, ApkUpdateSchemaPart apkUpdateSchemaPart) {
        super(context, iErrorReporter, ImmutableList.of(apkUpdateSchemaPart), DATABASE_NAME);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_BINDING_ID, injectorLike);
    }
}