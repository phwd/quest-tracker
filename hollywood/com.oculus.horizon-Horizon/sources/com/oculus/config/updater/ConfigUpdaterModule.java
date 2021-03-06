package com.oculus.config.updater;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass0Pp;
import X.AnonymousClass0dM;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.common.init.INeedInit;
import com.oculus.common.init.NeedsHighPriorityInit;
import com.oculus.config.annotations.ConfigRefreshInterval;
import com.oculus.horizon.vr_lifecycle.MountStatusPoller;
import javax.inject.Provider;

@InjectorModule
public abstract class ConfigUpdaterModule extends AnonymousClass0J5 {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterDumperPlugin_ULSEP_BINDING_ID = MountStatusPoller.POLL_INTERVAL_MS;
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterInit_ULSEP_BINDING_ID = 312;
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterJobScheduler_ULSEP_BINDING_ID = 264;
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterLoginHandler_ULSEP_BINDING_ID = 542;
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterLogoutHandler_ULSEP_BINDING_ID = 311;
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterMethods_ULSEP_BINDING_ID = 131;
        public static final int _UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_BINDING_ID = 157;
        public static final int _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_config_annotations_ConfigRefreshInterval_ULSEP_BINDING_ID = 246;
    }

    @AddToMultiBind
    @NeedsHighPriorityInit
    public abstract INeedInit addConfigInit(ConfigUpdaterInit configUpdaterInit);

    @AddToMultiBind
    public abstract AnonymousClass0dM addConfigUpdaterDumperPlugin(ConfigUpdaterDumperPlugin configUpdaterDumperPlugin);

    @AddToMultiBind
    public abstract LoginHandler addConfigUpdaterLoginHandler(ConfigUpdaterLoginHandler configUpdaterLoginHandler);

    @AddToMultiBind
    public abstract LogoutHandler addConfigUpdaterLogoutHandler(ConfigUpdaterLogoutHandler configUpdaterLogoutHandler);

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_Long_ULGT__ULSEP_com_oculus_config_annotations_ConfigRefreshInterval_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(246, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Long _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_config_annotations_ConfigRefreshInterval_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (Long) AnonymousClass117.A00(246, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Long_ULGT__ULSEP_com_oculus_config_annotations_ConfigRefreshInterval_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(246, r2);
    }

    @AutoGeneratedFactoryMethod
    public static final Long _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_config_annotations_ConfigRefreshInterval_ULSEP_FACTORY_METHOD(AbstractC06640p5 r0) {
        return provideGatekeeperRefreshInterval();
    }

    @ConfigRefreshInterval
    @ProviderMethod
    public static Long provideGatekeeperRefreshInterval() {
        return 28800000L;
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForConfigUpdaterModule {
        public static void bind(AnonymousClass0Pp r0) {
        }
    }
}
