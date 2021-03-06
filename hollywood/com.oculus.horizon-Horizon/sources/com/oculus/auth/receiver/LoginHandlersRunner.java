package com.oculus.auth.receiver;

import X.AbstractC06640p5;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0Pi;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C01010Iv;
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.handler.LoginHandler;
import java.util.ArrayList;
import java.util.Set;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LoginHandlersRunner {
    public static final String TAG = "LoginHandlersRunner";
    public static volatile LoginHandlersRunner _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public LoginHandlerCompletionListener mListener;

    public interface LoginHandlerCompletionListener {
        void onLoginInternalCompleted();
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_receiver_LoginHandlersRunner_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(329, r2);
    }

    @AutoGeneratedAccessMethod
    public static final LoginHandlersRunner _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (LoginHandlersRunner) AnonymousClass117.A00(329, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final LoginHandlersRunner _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_FACTORY_METHOD(AbstractC06640p5 r4) {
        if (_UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_INSTANCE == null) {
            synchronized (LoginHandlersRunner.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_INSTANCE = new LoginHandlersRunner(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_receiver_LoginHandlersRunner_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(329, r2);
    }

    @Inject
    public LoginHandlersRunner(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public void runLoginHandlers() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        Set<LoginHandler> set = (Set) AnonymousClass0J2.A03(0, 60, this._UL_mInjectionContext);
        ArrayList arrayList = new ArrayList(set.size());
        set.size();
        for (LoginHandler loginHandler : set) {
            final String simpleName = loginHandler.getClass().getSimpleName();
            arrayList.add(loginHandler.afterLoginAsync().A0A(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
                /* class com.oculus.auth.receiver.LoginHandlersRunner.AnonymousClass1 */

                @Override // X.AnonymousClass0D4
                public AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r1) {
                    SystemClock.uptimeMillis();
                    return r1;
                }
            }));
        }
        AnonymousClass0DC.A02(AnonymousClass0DC.A05(arrayList), new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
            /* class com.oculus.auth.receiver.LoginHandlersRunner.AnonymousClass2 */

            @Override // X.AnonymousClass0D4
            public AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r2) {
                SystemClock.uptimeMillis();
                LoginHandlerCompletionListener loginHandlerCompletionListener = LoginHandlersRunner.this.mListener;
                if (loginHandlerCompletionListener == null) {
                    return null;
                }
                loginHandlerCompletionListener.onLoginInternalCompleted();
                return null;
            }
        }, AnonymousClass0DC.A0B);
    }

    public void setListener(LoginHandlerCompletionListener loginHandlerCompletionListener) {
        this.mListener = loginHandlerCompletionListener;
    }
}
