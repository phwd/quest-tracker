package com.oculus.horizon.linkedaccounts;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.horizon.accountlinking.dropbox.DropboxAPIHelper;
import com.oculus.horizon.accountlinking.google.GoogleAPIHelper;
import com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_accountlinking_google_GoogleAPIHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_accountlinking_dropbox_DropboxAPIHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_linkedaccounts_database_LinkedAccountsSQLiteHelper_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LinkedAccountsManager {
    public static final String ACTION_COMPLETE_ACCOUNT_LINKING = "com.oculus.vrshell.home.COMPLETE_ACCOUNT_LINKING";
    public static final String KEY_SERVICE_PROVIDER = "service_provider";
    public static final String TAG = "LinkedAccountsManager";
    public static volatile LinkedAccountsManager _UL__ULSEP_com_oculus_horizon_linkedaccounts_LinkedAccountsManager_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final CredentialsManager mCredentialsManager;
    @Inject
    @Eager
    public final DropboxAPIHelper mDropboxAPIHelper;
    @Inject
    @Eager
    public final GoogleAPIHelper mGoogleAPIHelper;
    @Inject
    @Eager
    public final LinkedAccountsSQLiteHelper mLinkedAccountsSQLiteHelper;

    /* renamed from: com.oculus.horizon.linkedaccounts.LinkedAccountsManager$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$linkedaccounts$model$ServiceProvider;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.horizon.linkedaccounts.model.ServiceProvider[] r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.linkedaccounts.LinkedAccountsManager.AnonymousClass5.$SwitchMap$com$oculus$horizon$linkedaccounts$model$ServiceProvider = r2
                com.oculus.horizon.linkedaccounts.model.ServiceProvider r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.DROPBOX     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.linkedaccounts.model.ServiceProvider r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.GOOGLE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizon.linkedaccounts.model.ServiceProvider r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.FACEBOOK     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.horizon.linkedaccounts.model.ServiceProvider r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.INSTAGRAM     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.horizon.linkedaccounts.model.ServiceProvider r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.REMOTE_MEDIA     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.AnonymousClass5.<clinit>():void");
        }
    }

    @Inject
    public LinkedAccountsManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mGoogleAPIHelper = (GoogleAPIHelper) AnonymousClass117.A00(69, r3);
        this.mDropboxAPIHelper = (DropboxAPIHelper) AnonymousClass117.A00(90, r3);
        this.mCredentialsManager = CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(r3);
        this.mLinkedAccountsSQLiteHelper = (LinkedAccountsSQLiteHelper) AnonymousClass117.A00(293, r3);
    }
}
