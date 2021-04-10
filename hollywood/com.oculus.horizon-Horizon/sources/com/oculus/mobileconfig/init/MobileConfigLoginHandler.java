package com.oculus.mobileconfig.init;

import X.AbstractC06600ny;
import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0RX;
import X.AnonymousClass1ar;
import X.C01010Iv;
import X.C01020Iw;
import X.C09311at;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID"})
public class MobileConfigLoginHandler implements LoginHandler {
    public static final int SYNC_UPDATE_WAIT_TIME_MS = 1000;
    public static String TAG;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    public final Provider<AbstractC06600ny> mMobileConfigFactoryProvider;
    @Inject
    public final Provider<C09311at> mMobileConfigManagerHolderProvider;
    @Inject
    public final Provider<OkTigonServiceHolder> mOkTigonServiceHolderProvider;

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        return AnonymousClass0DC.A07(new Callable<Void>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass2 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                C09311at r2 = MobileConfigLoginHandler.this.mMobileConfigManagerHolderProvider.get();
                MobileConfigLoginHandler mobileConfigLoginHandler = MobileConfigLoginHandler.this;
                MobileConfigInit mobileConfigInit = (MobileConfigInit) AnonymousClass0J2.A03(0, 487, mobileConfigLoginHandler._UL_mInjectionContext);
                Credentials credentials = mobileConfigLoginHandler.mCredentialsProvider.get();
                OkTigonServiceHolder okTigonServiceHolder = MobileConfigLoginHandler.this.mOkTigonServiceHolderProvider.get();
                synchronized (mobileConfigInit) {
                    if (credentials != null) {
                        AnonymousClass1ar r3 = (AnonymousClass1ar) mobileConfigInit.mMobileConfigFactoryProvider.get();
                        r3.A07();
                        AnonymousClass0RX r1 = r3.A06;
                        if (r1 instanceof C09311at) {
                            C09311at r12 = (C09311at) r1;
                            MobileConfigManagerHolderImpl A00 = MobileConfigInit.A00(mobileConfigInit, credentials);
                            r12.A01(A00, r3);
                            MobileConfigDependenciesInFBApps.setNetworkService(A00, okTigonServiceHolder, true);
                            r12.isValid();
                        }
                    }
                }
                AnonymousClass1ar.A03((AnonymousClass1ar) MobileConfigLoginHandler.this.mMobileConfigFactoryProvider.get());
                if (!((MobileConfigInitOptions) AnonymousClass0J2.A03(1, 589, MobileConfigLoginHandler.this._UL_mInjectionContext)).mShouldFetchOnLogin || r2.tryUpdateConfigsSynchronously(1000) || !r2.isFetchNeeded()) {
                    return null;
                }
                String format = String.format(Locale.US, "Unable to finish downloading config values after: %d ms", 1000);
                AnonymousClass0NO.A08(MobileConfigLoginHandler.TAG, format);
                throw new RuntimeException(format);
            }
        }, AnonymousClass0DC.A0C, null).A0A(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r5) throws Exception {
                if (!r5.A0K() || (r5.A0F() instanceof IOException)) {
                    return r5;
                }
                Exception A0F = r5.A0F();
                ((IErrorReporter) AnonymousClass0J2.A03(2, 428, MobileConfigLoginHandler.this._UL_mInjectionContext)).A99(MobileConfigLoginHandler.TAG, A0F.getMessage(), A0F);
                return null;
            }
        });
    }

    @Inject
    public MobileConfigLoginHandler(AbstractC06640p5 r4) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r4);
        this.mMobileConfigManagerHolderProvider = new C01020Iw(357, r4);
        this.mMobileConfigFactoryProvider = new C01020Iw(399, r4);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r4);
        this.mOkTigonServiceHolderProvider = new C01010Iv(107, r4);
        TAG = AnonymousClass006.A05(((MobileConfigInitOptions) AnonymousClass0J2.A03(1, 589, this._UL_mInjectionContext)).mTagPrefix, "MobileConfigLoginHandler");
    }
}
