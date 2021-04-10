package com.oculus.mobileconfig.init;

import X.AbstractC0096Hu;
import X.AbstractC0234Xa;
import X.AbstractC0247Xu;
import X.AnonymousClass06;
import X.C0088Gy;
import X.D3;
import X.DB;
import X.GA;
import X.Gt;
import X.Mu;
import X.QC;
import X.RU;
import X.XX;
import X.eJ;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID"})
public class MobileConfigLoginHandler implements LoginHandler {
    public static final int SYNC_UPDATE_WAIT_TIME_MS = 1000;
    public static String TAG;
    public QC _UL_mInjectionContext;
    @Inject
    public final eJ<Credentials> mCredentialsProvider;
    @Inject
    public final eJ<AbstractC0234Xa> mMobileConfigFactoryProvider;
    @Inject
    public final eJ<XX> mMobileConfigManagerHolderProvider;
    @Inject
    public final eJ<OkTigonServiceHolder> mOkTigonServiceHolderProvider;

    @AutoGeneratedFactoryMethod
    public static final MobileConfigLoginHandler A00(AbstractC0247Xu xu) {
        return new MobileConfigLoginHandler(xu);
    }

    @Override // com.oculus.auth.handler.LoginHandler
    public final DB<Void> afterLoginAsync() {
        return DB.A00(new Callable<Void>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass2 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                XX xx = MobileConfigLoginHandler.this.mMobileConfigManagerHolderProvider.get();
                MobileConfigLoginHandler mobileConfigLoginHandler = MobileConfigLoginHandler.this;
                MobileConfigInit mobileConfigInit = (MobileConfigInit) AbstractC0096Hu.A03(0, 41, mobileConfigLoginHandler._UL_mInjectionContext);
                Credentials credentials = mobileConfigLoginHandler.mCredentialsProvider.get();
                OkTigonServiceHolder okTigonServiceHolder = MobileConfigLoginHandler.this.mOkTigonServiceHolderProvider.get();
                synchronized (mobileConfigInit) {
                    if (credentials != null) {
                        GA ga = (GA) mobileConfigInit.mMobileConfigFactoryProvider.get();
                        ga.A06();
                        RU ru = ga.A06;
                        if (ru instanceof XX) {
                            XX xx2 = (XX) ru;
                            MobileConfigManagerHolderImpl A00 = MobileConfigInit.A00(mobileConfigInit, credentials);
                            xx2.A01(A00, ga);
                            MobileConfigDependenciesInFBApps.setNetworkService(A00, okTigonServiceHolder, true);
                            xx2.isValid();
                        }
                    }
                }
                GA.A03((GA) MobileConfigLoginHandler.this.mMobileConfigFactoryProvider.get());
                if (!((MobileConfigInitOptions) AbstractC0096Hu.A03(1, 131, MobileConfigLoginHandler.this._UL_mInjectionContext)).mShouldFetchOnLogin || xx.tryUpdateConfigsSynchronously(1000) || !xx.isFetchNeeded()) {
                    return null;
                }
                String format = String.format(Locale.US, "Unable to finish downloading config values after: %d ms", 1000);
                Mu.A00(MobileConfigLoginHandler.TAG, format);
                throw new RuntimeException(format);
            }
        }, DB.A0C).A02(new D3<Void, DB<Void>>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.DB] */
            @Override // X.D3
            public final DB<Void> A5U(DB<Void> db) throws Exception {
                if (!db.A06() || (db.A03() instanceof IOException)) {
                    return db;
                }
                Exception A03 = db.A03();
                ((IErrorReporter) AbstractC0096Hu.A03(2, 135, MobileConfigLoginHandler.this._UL_mInjectionContext)).A5J(MobileConfigLoginHandler.TAG, A03.getMessage(), A03);
                return null;
            }
        });
    }

    @Inject
    public MobileConfigLoginHandler(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(3, xu);
        this.mMobileConfigManagerHolderProvider = new C0088Gy(64, xu);
        this.mMobileConfigFactoryProvider = new C0088Gy(115, xu);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(xu);
        this.mOkTigonServiceHolderProvider = new Gt(113, xu);
        TAG = AnonymousClass06.A04(((MobileConfigInitOptions) AbstractC0096Hu.A03(1, 131, this._UL_mInjectionContext)).mTagPrefix, "MobileConfigLoginHandler");
    }
}
