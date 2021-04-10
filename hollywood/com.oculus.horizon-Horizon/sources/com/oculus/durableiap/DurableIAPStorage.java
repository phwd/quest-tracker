package com.oculus.durableiap;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0b8;
import X.AnonymousClass0b9;
import X.AnonymousClass117;
import X.C00910Hi;
import X.C02600ao;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.SQLException;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.common.init.INeedInit;
import com.oculus.durableiap.DurableIAPDBContract;
import com.oculus.durableiap.net.DurableIAPMethods;
import com.oculus.job.OculusJobHelper;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import java.util.concurrent.Callable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_durableiap_DurableIAPJobScheduler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_durableiap_DurableIAPJobScheduler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_durableiap_DurableIAPJobScheduler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_durableiap_DurableIAPDatabaseSupplier_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_durableiap_net_DurableIAPMethods_ULSEP_BINDING_ID"})
public class DurableIAPStorage implements INeedInit, LoginHandler, LogoutHandler {
    public static final String ACTION_IAP_RESULT = "com.oculus.iap_shell_result";
    public static final String EXTRA_KEY_SUCCESS = "is_successful";
    public static final String[] SELECT_COLUMNS = {DurableIAPDBContract.Columns.SKU.name, DurableIAPDBContract.Columns.PURCHASE_ID.name, DurableIAPDBContract.Columns.GRANT_TIME.name, DurableIAPDBContract.Columns.EXPIRATION_TIME.name};
    public static final String TAG = "DurableIAPStorage";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public final DurableIAPDatabaseSupplier mDurableIAPDatabaseSupplier;
    @Inject
    @Eager
    public final DurableIAPMethods mNetworkMethods;
    @Inject
    @Eager
    public final OVRLibrary mOVRLibrary;

    public class IAPReceiver extends C00910Hi {
        public IAPReceiver() {
            super("com.oculus.iap_shell_result", new AnonymousClass0b8() {
                /* class com.oculus.durableiap.DurableIAPStorage.IAPReceiver.AnonymousClass1 */

                @Override // X.AnonymousClass0b8
                public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r5) {
                    if (intent.getBooleanExtra("is_successful", false)) {
                        AnonymousClass0DC.A06(new Callable<Void>() {
                            /* class com.oculus.durableiap.DurableIAPStorage.AnonymousClass2 */

                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final java.lang.Void call() {
                                /*
                                // Method dump skipped, instructions count: 303
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.oculus.durableiap.DurableIAPStorage.AnonymousClass2.call():java.lang.Object");
                            }
                        });
                    }
                }
            });
        }
    }

    public final void A00() {
        try {
            this.mDurableIAPDatabaseSupplier.A03().delete("durable_iap", null, null);
        } catch (SQLException e) {
            AnonymousClass0NO.A0H(TAG, e, "Exception occurred when trying to clear table.");
        }
    }

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        ((OculusJobHelper) AnonymousClass0J2.A04(170, this._UL_mInjectionContext)).A00();
        AnonymousClass0DC.A06(new Callable<Void>() {
            /* class com.oculus.durableiap.DurableIAPStorage.AnonymousClass2 */

            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Void call() {
                /*
                // Method dump skipped, instructions count: 303
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.durableiap.DurableIAPStorage.AnonymousClass2.call():java.lang.Object");
            }
        });
        return AnonymousClass0DC.A04(null);
    }

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        ((OculusJobHelper) AnonymousClass0J2.A04(170, this._UL_mInjectionContext)).A01();
        A00();
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        ((OculusJobHelper) AnonymousClass0J2.A04(170, this._UL_mInjectionContext)).A00();
        C02600ao A00 = C02600ao.A00();
        IntentFilter intentFilter = new IntentFilter("com.oculus.iap_shell_result");
        IAPReceiver iAPReceiver = new IAPReceiver();
        iAPReceiver.mScope = C02600ao.A01(A00);
        ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).registerReceiver(iAPReceiver, intentFilter);
        this.mOVRLibrary.A07(new OVRLibrary.InstallListener() {
            /* class com.oculus.durableiap.DurableIAPStorage.AnonymousClass1 */

            @Override // com.oculus.libraryapi.OVRLibrary.InstallListener
            public final void A6E(InstallerResult installerResult) {
                AnonymousClass0DC.A06(new Callable<Void>() {
                    /* class com.oculus.durableiap.DurableIAPStorage.AnonymousClass2 */

                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Void call() {
                        /*
                        // Method dump skipped, instructions count: 303
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.durableiap.DurableIAPStorage.AnonymousClass2.call():java.lang.Object");
                    }
                });
            }
        });
    }

    @Inject
    public DurableIAPStorage(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mDurableIAPDatabaseSupplier = (DurableIAPDatabaseSupplier) AnonymousClass117.A00(564, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mOVRLibrary = OVRLibraryModule.A00(r3);
        this.mNetworkMethods = (DurableIAPMethods) AnonymousClass117.A00(169, r3);
    }
}
