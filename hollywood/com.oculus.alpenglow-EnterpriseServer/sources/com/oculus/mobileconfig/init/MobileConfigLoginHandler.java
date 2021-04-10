package com.oculus.mobileconfig.init;

import X.AbstractC02890az;
import X.AbstractC02990bJ;
import X.AbstractC07240oz;
import X.AnonymousClass006;
import X.AnonymousClass0Hn;
import X.AnonymousClass0Hv;
import X.AnonymousClass0Ld;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass0ST;
import X.AnonymousClass12o;
import X.AnonymousClass12r;
import X.C01520Hw;
import X.C01730Le;
import X.C03300bz;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID"})
public class MobileConfigLoginHandler implements LoginHandler {
    public static final int SYNC_UPDATE_WAIT_TIME_MS = 1000;
    public static String TAG;
    public AnonymousClass0R7 _UL_mInjectionContext;
    @Inject
    public final AbstractC07240oz<Credentials> mCredentialsProvider;
    @Inject
    public final AbstractC07240oz<AbstractC02890az> mMobileConfigFactoryProvider;
    @Inject
    public final AbstractC07240oz<AnonymousClass12r> mMobileConfigManagerHolderProvider;
    @Inject
    public final AbstractC07240oz<OkTigonServiceHolder> mOkTigonServiceHolderProvider;

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0Hv<Void> afterLoginAsync() {
        boolean z;
        AnonymousClass0Hv A00 = AnonymousClass0Hv.A00(new Callable<Void>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass2 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                AnonymousClass12r r3 = MobileConfigLoginHandler.this.mMobileConfigManagerHolderProvider.get();
                MobileConfigLoginHandler mobileConfigLoginHandler = MobileConfigLoginHandler.this;
                MobileConfigInit mobileConfigInit = (MobileConfigInit) AnonymousClass0Lh.A03(0, 32, mobileConfigLoginHandler._UL_mInjectionContext);
                Credentials credentials = mobileConfigLoginHandler.mCredentialsProvider.get();
                OkTigonServiceHolder okTigonServiceHolder = MobileConfigLoginHandler.this.mOkTigonServiceHolderProvider.get();
                synchronized (mobileConfigInit) {
                    if (credentials != null) {
                        AnonymousClass12o r2 = (AnonymousClass12o) mobileConfigInit.mMobileConfigFactoryProvider.get();
                        r2.A04();
                        AnonymousClass0ST r1 = r2.A06;
                        if (r1 instanceof AnonymousClass12r) {
                            AnonymousClass12r r12 = (AnonymousClass12r) r1;
                            MobileConfigManagerHolderImpl A00 = MobileConfigInit.A00(mobileConfigInit, credentials);
                            r12.A01(A00, r2);
                            MobileConfigDependenciesInFBApps.setNetworkService(A00, okTigonServiceHolder, true);
                            r12.isValid();
                        }
                    }
                }
                AnonymousClass12o.A01((AnonymousClass12o) MobileConfigLoginHandler.this.mMobileConfigFactoryProvider.get());
                if (!((MobileConfigInitOptions) AnonymousClass0Lh.A03(1, 126, MobileConfigLoginHandler.this._UL_mInjectionContext)).mShouldFetchOnLogin || r3.tryUpdateConfigsSynchronously(1000) || !r3.isFetchNeeded()) {
                    return null;
                }
                String format = String.format(Locale.US, "Unable to finish downloading config values after: %d ms", 1000);
                AnonymousClass0NK.A01(MobileConfigLoginHandler.TAG, format);
                throw new RuntimeException(format);
            }
        }, AnonymousClass0Hv.A0C);
        AnonymousClass1 r6 = new AnonymousClass0Hn<Void, AnonymousClass0Hv<Void>>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0Hv] */
            @Override // X.AnonymousClass0Hn
            public final AnonymousClass0Hv<Void> A8Z(AnonymousClass0Hv<Void> r2) throws Exception {
                if (!r2.A05() || (r2.A03() instanceof IOException)) {
                    return r2;
                }
                r2.A03().getMessage();
                return null;
            }
        };
        Executor executor = AnonymousClass0Hv.A0A;
        C01520Hw r4 = new C01520Hw();
        synchronized (A00.A05) {
            z = A00.A04;
            if (!z) {
                A00.A02.add(new C03300bz(A00, r4, r6, executor));
            }
        }
        if (z) {
            AnonymousClass0Hv.A02(r4, r6, A00, executor);
        }
        return r4.A00;
    }

    @Inject
    public MobileConfigLoginHandler(AbstractC02990bJ r4) {
        this._UL_mInjectionContext = new AnonymousClass0R7(3, r4);
        this.mMobileConfigManagerHolderProvider = new C01730Le(55, r4);
        this.mMobileConfigFactoryProvider = new C01730Le(116, r4);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r4);
        this.mOkTigonServiceHolderProvider = new AnonymousClass0Ld(114, r4);
        TAG = AnonymousClass006.A05(((MobileConfigInitOptions) AnonymousClass0Lh.A03(1, 126, this._UL_mInjectionContext)).mTagPrefix, "MobileConfigLoginHandler");
    }
}
