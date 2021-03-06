package com.oculus.config.duc;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.inject.name.Named;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_config_duc_DUCHelper_GkWrapper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DUCHelper {
    public static final List<String> ALL_FEATURES = buildAllFeatures();
    public static final String DEFAULT_USERID = "0";
    public static final String DEFAULT_USERIMAGE = "";
    public static final String DEFAULT_USERNAME = "";
    public static final String EVENT_DUC_CLIENT_GATING = "oculus_duc_client_gating";
    public static final String EXTRA_KEY_ENFORCED = "duc_enforced";
    public static final String EXTRA_KEY_FEATURE = "duc_feature";
    public static final String EXTRA_KEY_GATING_GK = "duc_gating_gk";
    public static final String EXTRA_KEY_PACKAGE = "duc_package";
    public static final String TAG = "DUCHelper";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Dependencies({"_UL__ULSEP_java_lang_Boolean_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_BINDING_ID"})
    public static final class GkWrapper {
        @Inject
        @Named(DUCModule.ENABLE_CACHE_GATING)
        public final Provider<Boolean> mEnableCacheGating;

        @AutoGeneratedAccessMethod
        public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_duc_DUCHelper_GkWrapper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
            return new C003008y(233, r2);
        }

        @AutoGeneratedAccessMethod
        public static final GkWrapper _UL__ULSEP_com_oculus_config_duc_DUCHelper_GkWrapper_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
            return (GkWrapper) AnonymousClass117.A00(233, r1);
        }

        @AutoGeneratedFactoryMethod
        public static final GkWrapper _UL__ULSEP_com_oculus_config_duc_DUCHelper_GkWrapper_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
            return new GkWrapper(r1);
        }

        @AutoGeneratedAccessMethod
        public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_duc_DUCHelper_GkWrapper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
            return new C01020Iw(233, r2);
        }

        public boolean isGatingEnabled() {
            return this.mEnableCacheGating.get().booleanValue();
        }

        @Inject
        public GkWrapper(AbstractC06640p5 r2) {
            this.mEnableCacheGating = DUCModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(r2);
        }
    }

    public enum Key {
        USER_ID,
        USER_PROFILE,
        IAP,
        AVATARS,
        DEEP_LINKING,
        FRIENDS,
        INVITES,
        MATCHMAKING,
        PARTIES,
        ROOMS,
        CHALLENGES,
        TEST
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_duc_DUCHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(239, r2);
    }

    @AutoGeneratedAccessMethod
    public static final DUCHelper _UL__ULSEP_com_oculus_config_duc_DUCHelper_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (DUCHelper) AnonymousClass117.A00(239, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final DUCHelper _UL__ULSEP_com_oculus_config_duc_DUCHelper_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new DUCHelper(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_duc_DUCHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(239, r2);
    }

    public boolean hasAccess(List<String> list, Key key) {
        if (!((GkWrapper) AnonymousClass0J2.A03(0, 233, this._UL_mInjectionContext)).isGatingEnabled() || (list != null && list.contains(key.name()))) {
            return true;
        }
        return false;
    }

    public void reportGatingEnforced(String str, Key key) {
        String str2 = TAG;
        String name = key.name();
        AnonymousClass0NO.A0F(str2, "Enforcing %s gating for calling package %s", name, str);
        Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(EVENT_DUC_CLIENT_GATING);
        A22.A15(EXTRA_KEY_PACKAGE, str);
        A22.A15(EXTRA_KEY_FEATURE, name);
        A22.A16(EXTRA_KEY_GATING_GK, ((GkWrapper) AnonymousClass0J2.A03(0, 233, this._UL_mInjectionContext)).isGatingEnabled());
        A22.A16(EXTRA_KEY_ENFORCED, true);
        A22.A5L();
    }

    @Inject
    public DUCHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    public static List<String> buildAllFeatures() {
        Key[] values = Key.values();
        ArrayList arrayList = new ArrayList();
        for (Key key : values) {
            arrayList.add(key.name());
        }
        return arrayList;
    }
}
