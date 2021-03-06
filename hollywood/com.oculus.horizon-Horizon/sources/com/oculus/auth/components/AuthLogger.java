package com.oculus.auth.components;

import X.AbstractC06640p5;
import X.AbstractC07380s1;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0Pi;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C01010Iv;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
@ApplicationScoped
@Immutable
public class AuthLogger {
    public static final String COMPONENT_ACTIVATE_HMD = "activate_hmd";
    public static final String COMPONENT_CLAIM_ACTIVATION_BUNDLE = "claim_activation_bundle";
    public static final String COMPONENT_CLAIM_DEVICE = "claim_device";
    public static final String COMPONENT_FETCH_DEVICE_AUTH_TOKEN = "fetch_device_auth_token";
    public static final String COMPONENT_FETCH_FB_INFO_FOR_ACCOUNT_LINKING = "fetch_fb_info_for_account_linking";
    public static final String COMPONENT_LOGIN_WITH_FB_AUTH = "login_with_fb_auth";
    public static final String COMPONENT_OBTAIN_DSAT = "obtain_dsat";
    public static final String COMPONENT_OBTAIN_HORIZON_DSAT = "obtain_horizon_dsat";
    public static final String COMPONENT_REGISTER_HMD = "register_hmd";
    public static final String COMPONENT_REGISTER_LOGIN = "register_login";
    public static final String COMPONENT_SET_DEVICE_OWNERSHIP = "set_device_ownership";
    public static final String EVENT_ACTION = "oculus_mobile_auth_action";
    public static final String EVENT_COMPONENT = "oculus_mobile_auth_component";
    public static final String EXTRA_ACTION = "action";
    public static final String EXTRA_CALLING_PACKAGE = "calling_package";
    public static final String EXTRA_COMPONENT = "component";
    public static final String EXTRA_DURATION_MS = "duration_ms";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_ERROR_MESSAGE = "error_message";
    public static final String EXTRA_ERROR_TITLE = "error_title";
    public static final String EXTRA_IS_RELOGIN = "is_relogin";
    public static final String EXTRA_LOGGED_IN = "logged_in";
    public static final String EXTRA_OPTIONS = "options";
    public static final String EXTRA_OUTCOME = "outcome";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_THROWABLE_CLASS = "throwable_class";
    public static final String EXTRA_THROWABLE_MESSAGE = "throwable_message";
    public static final String EXTRA_WITH_ACCESS_TOKEN = "with_access_token";
    public static final String STATUS_FAILURE = "failure";
    public static final String STATUS_SUCCESS = "success";
    public static final String TAG = "AuthLogger";
    public static volatile AuthLogger _UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_components_AuthLogger_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(456, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AuthLogger _UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (AuthLogger) AnonymousClass117.A00(456, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final AuthLogger _UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_FACTORY_METHOD(AbstractC06640p5 r4) {
        if (_UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_INSTANCE == null) {
            synchronized (AuthLogger.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_INSTANCE = new AuthLogger(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_auth_components_AuthLogger_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_components_AuthLogger_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(456, r2);
    }

    private Event createEvent(String str, AuthAction authAction, @Nullable Throwable th, ImmutableMap<String, ?> immutableMap) {
        String str2;
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(str);
        A22.A15("action", authAction.mName);
        A22.A15("calling_package", authAction.mCallingPackage);
        if (th == null) {
            str2 = "success";
        } else {
            str2 = "failure";
        }
        A22.A15("status", str2);
        if (th != null) {
            A22.A15(EXTRA_THROWABLE_CLASS, th.getClass().getName());
            A22.A15(EXTRA_THROWABLE_MESSAGE, th.getMessage());
        }
        addExtras(A22, immutableMap);
        return A22;
    }

    public void reportAction(AuthAction authAction, @Nullable Throwable th, ImmutableMap<String, ?> immutableMap) {
        boolean containsKey = immutableMap.containsKey("error_code");
        long currentTimeMillis = System.currentTimeMillis() - authAction.mStartTime;
        if (!authAction.mIsQuiet) {
            Event createEvent = createEvent("oculus_mobile_auth_action", authAction, th, immutableMap);
            createEvent.A14("duration_ms", currentTimeMillis);
            createEvent.A5L();
        }
        int i = 3;
        if (th != null || containsKey) {
            AnonymousClass0NO.A0K(TAG, th, "Action failed: %s, callingPackage: %s, extras: %s, duration: %dms", authAction.mName, authAction.mCallingPackage, immutableMap, Long.valueOf(currentTimeMillis));
            return;
        }
        if (!authAction.mIsQuiet) {
            i = 4;
        }
        if (AnonymousClass0NO.A01.A54(i)) {
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("Action succeeded: %s, callingPackage: %s, extras: %s, duration: %dms", authAction.mName, authAction.mCallingPackage, immutableMap, Long.valueOf(currentTimeMillis));
            if (AnonymousClass0NO.A01.A54(i)) {
                AnonymousClass0NO.A01.A5H(i, TAG, formatStrLocaleSafe);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.oculus.auth.components.AuthLogger */
    /* JADX WARN: Multi-variable type inference failed */
    public void reportComponent(String str, AuthAction authAction, @Nullable Throwable th) {
        Event createEvent = createEvent(EVENT_COMPONENT, authAction, th, RegularImmutableMap.A03);
        createEvent.A15(EXTRA_COMPONENT, str);
        createEvent.A5L();
        if (th != null) {
            AnonymousClass0NO.A0K(TAG, th, "Component failed: %s, action: %s, callingPackage: %s", str, authAction.mName, authAction.mCallingPackage);
        }
    }

    @Inject
    public AuthLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    public static Event addExtras(Event event, ImmutableMap<String, ?> immutableMap) {
        AbstractC07380s1<Map.Entry<String, ?>> A0K = immutableMap.entrySet().iterator();
        while (A0K.hasNext()) {
            Map.Entry<String, ?> next = A0K.next();
            String key = next.getKey();
            Object value = next.getValue();
            if (value instanceof Integer) {
                event.A13(key, ((Number) value).intValue());
            } else if (value instanceof Long) {
                event.A14(key, ((Number) value).longValue());
            } else if (value instanceof Double) {
                event.A12(key, ((Number) value).doubleValue());
            } else if (value instanceof Boolean) {
                event.A16(key, ((Boolean) value).booleanValue());
            } else {
                event.A15(key, value.toString());
            }
        }
        return event;
    }
}
