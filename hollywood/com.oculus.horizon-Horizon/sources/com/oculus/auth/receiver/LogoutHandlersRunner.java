package com.oculus.auth.receiver;

import X.AbstractC06640p5;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import android.os.SystemClock;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.handler.AuthHandlerModule;
import com.oculus.auth.handler.LogoutHandler;
import java.util.Set;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID"})
public class LogoutHandlersRunner {
    public static final String TAG = "LogoutHandlersRunner";
    @Inject
    @Eager
    public final AnonymousClass0p1<Set<LogoutHandler>> mLogoutHandlersLazy;

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_receiver_LogoutHandlersRunner_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(308, r2);
    }

    @AutoGeneratedAccessMethod
    public static final LogoutHandlersRunner _UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (LogoutHandlersRunner) AnonymousClass117.A00(308, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final LogoutHandlersRunner _UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new LogoutHandlersRunner(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_receiver_LogoutHandlersRunner_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(308, r2);
    }

    public void runLogoutHandlers() {
        Set<LogoutHandler> set = this.mLogoutHandlersLazy.get();
        SystemClock.uptimeMillis();
        set.size();
        for (LogoutHandler logoutHandler : set) {
            logoutHandler.beforeLogout();
        }
        SystemClock.uptimeMillis();
    }

    @Inject
    public LogoutHandlersRunner(AbstractC06640p5 r2) {
        this.mLogoutHandlersLazy = AuthHandlerModule._UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULGT__ULSEP_ACCESS_METHOD(r2);
    }
}
