package com.oculus.config.updater;

import X.AbstractC06640p5;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.oculus.auth.handler.LoginHandler;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_config_updater_ConfigUpdaterJobScheduler_ULSEP_BINDING_ID"})
public class ConfigUpdaterLoginHandler implements LoginHandler {
    public final ConfigUpdater mConfigUpdater;
    public final AnonymousClass0p1<ConfigUpdaterJobScheduler> mJobSchedulerLazy;

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_updater_ConfigUpdaterLoginHandler_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(542, r2);
    }

    @AutoGeneratedAccessMethod
    public static final ConfigUpdaterLoginHandler _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterLoginHandler_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (ConfigUpdaterLoginHandler) AnonymousClass117.A00(542, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_updater_ConfigUpdaterLoginHandler_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(542, r2);
    }

    @Override // com.oculus.auth.handler.LoginHandler
    public AnonymousClass0DC<Void> afterLoginAsync() {
        return this.mConfigUpdater.fetchAsync().A09(new AnonymousClass0D4<Void, Void>() {
            /* class com.oculus.config.updater.ConfigUpdaterLoginHandler.AnonymousClass1 */

            @Override // X.AnonymousClass0D4
            public Void then(AnonymousClass0DC<Void> r2) throws Exception {
                ConfigUpdaterLoginHandler.this.mJobSchedulerLazy.get().schedule();
                return null;
            }
        });
    }

    @Inject
    public ConfigUpdaterLoginHandler(ConfigUpdater configUpdater, AnonymousClass0p1<ConfigUpdaterJobScheduler> r2) {
        this.mConfigUpdater = configUpdater;
        this.mJobSchedulerLazy = r2;
    }

    @AutoGeneratedFactoryMethod
    public static final ConfigUpdaterLoginHandler _UL__ULSEP_com_oculus_config_updater_ConfigUpdaterLoginHandler_ULSEP_FACTORY_METHOD(AbstractC06640p5 r3) {
        return new ConfigUpdaterLoginHandler(ConfigUpdater._UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_ACCESS_METHOD(r3), ConfigUpdaterJobScheduler._UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_updater_ConfigUpdaterJobScheduler_ULGT__ULSEP_ACCESS_METHOD(r3));
    }
}
