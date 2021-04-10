package com.oculus.logging.analytics2;

import X.AbstractC0247Xu;
import X.C0260Yj;
import X.DB;
import X.Fg;
import X.H0;
import X.eJ;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsChangedHandler;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.horizon.foreground.ApplicationForegroundListener;
import java.util.concurrent.Callable;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID"})
@ApplicationScoped
public class SessionManagerImpl extends H0 implements Fg, ApplicationForegroundListener, CredentialsChangedHandler, LogoutHandler {
    public static volatile SessionManagerImpl _UL__ULSEP_com_oculus_logging_analytics2_SessionManagerImpl_ULSEP_INSTANCE;
    @Inject
    public final eJ<Credentials> mCredentialsProvider;
    public boolean mIsBackground = true;

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public final void A3a(long j) {
        this.mIsBackground = true;
        synchronized (this) {
            this.mSessionManagerCallbackObservable.A00();
        }
    }

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public final void A3b() {
        this.mIsBackground = false;
        synchronized (this) {
            this.mSessionManagerCallbackObservable.A01();
        }
    }

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        synchronized (this) {
            this.mSessionManagerCallbackObservable.A02();
        }
    }

    @Override // com.oculus.auth.credentials.CredentialsChangedHandler
    public final void onDeviceScopedCredentialsChanged() {
    }

    @Override // X.H0
    public final C0260Yj A00() {
        Credentials credentials = this.mCredentialsProvider.get();
        if (credentials != null) {
            return OculusPigeonIdentity.A00(credentials.mUserId, credentials.mAccessToken);
        }
        return OculusPigeonIdentity.A00(null, null);
    }

    @Override // com.oculus.auth.credentials.CredentialsChangedHandler
    public final void onCredentialsChanged() {
        DB.A00(new Callable<Void>() {
            /* class com.oculus.logging.analytics2.SessionManagerImpl.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                OculusPigeonIdentity A00;
                Credentials credentials = SessionManagerImpl.this.mCredentialsProvider.get();
                if (credentials != null) {
                    A00 = OculusPigeonIdentity.A00(credentials.mUserId, credentials.mAccessToken);
                } else {
                    A00 = OculusPigeonIdentity.A00(null, null);
                }
                SessionManagerImpl sessionManagerImpl = SessionManagerImpl.this;
                synchronized (sessionManagerImpl) {
                    sessionManagerImpl.mPigeonIdentityKnown = true;
                    sessionManagerImpl.mPigeonIdentity = A00;
                    sessionManagerImpl.mSessionManagerCallbackObservable.A03(A00);
                }
                return null;
            }
        }, DB.A0A);
    }

    @Inject
    public SessionManagerImpl(AbstractC0247Xu xu) {
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(xu);
    }

    @Override // X.Fg
    public final boolean A2G() {
        return this.mIsBackground;
    }
}
