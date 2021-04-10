package com.oculus.alpenglow.database;

import X.AbstractC02990bJ;
import X.AnonymousClass0Fn;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0Qe;
import X.AnonymousClass0R7;
import X.C01230Fm;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.boot.UnlockListener;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.library.model.AppStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_alpenglow_database_MigrationCompleteListener_ULGT__ULSEP_BINDING_ID"})
@ApplicationScoped
public class ApplicationDatabase implements UnlockListener {
    public static final int CONTEXT_CREDENTIAL_PROTECTED_STORAGE = 16;
    public static final String DATABASE_NAME = "application_db";
    public static final String MIGRATION_COMPLETE = "migration_complete";
    public static final String TAG = "EnterpriseServer.ApplicationDatabase";
    public static volatile ApplicationDatabase _UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    public AbstractApplicationDatabase mAbstractApplicationDatabase;
    public ApplicationDao mApplicationDao;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public volatile boolean mMigrationToDeviceProtectedComplete = this.mSharedPreferences.getBoolean(MIGRATION_COMPLETE, false);
    public final SharedPreferences mSharedPreferences;

    @Nullable
    @SuppressLint({"WrongConstant"})
    private Context A00() {
        try {
            Context context = (Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext);
            return context.createPackageContext(context.getPackageName(), 16);
        } catch (PackageManager.NameNotFoundException e) {
            AnonymousClass0NK.A04(TAG, "Call to createPackageContext failed", e);
            return null;
        }
    }

    public static void A02(@Nullable ApplicationDatabase applicationDatabase, Context context) {
        String str;
        String str2;
        UserManager userManager = (UserManager) ((Context) AnonymousClass0Lh.A03(0, 4, applicationDatabase._UL_mInjectionContext)).getSystemService(UserManager.class);
        if (userManager != null && !userManager.isUserUnlocked()) {
            str = TAG;
            str2 = "Device locked, cannot migrate";
        } else if (context != null) {
            AnonymousClass0Fn A00 = C01230Fm.A00((Context) AnonymousClass0Lh.A03(0, 4, applicationDatabase._UL_mInjectionContext));
            A00.A01(Migrations.MIGRATION_1_2);
            AbstractApplicationDatabase abstractApplicationDatabase = (AbstractApplicationDatabase) A00.A00();
            for (Application application : applicationDatabase.mAbstractApplicationDatabase.A08().A2t()) {
                abstractApplicationDatabase.A08().A5I(application);
            }
            for (Asset asset : applicationDatabase.mAbstractApplicationDatabase.A09().A2t()) {
                abstractApplicationDatabase.A09().A5J(asset);
            }
            applicationDatabase.mAbstractApplicationDatabase = abstractApplicationDatabase;
            applicationDatabase.mApplicationDao = abstractApplicationDatabase.A08();
            applicationDatabase.mMigrationToDeviceProtectedComplete = true;
            applicationDatabase.mSharedPreferences.edit().putBoolean(MIGRATION_COMPLETE, applicationDatabase.mMigrationToDeviceProtectedComplete).apply();
            applicationDatabase.mHandler.post(new Runnable() {
                /* class com.oculus.alpenglow.database.$$Lambda$ApplicationDatabase$2J49AwzfvGt88Jub4uPNza7VF6w */

                public final void run() {
                    for (MigrationCompleteListener migrationCompleteListener : (Set) AnonymousClass0Lh.A03(1, 68, ApplicationDatabase.this._UL_mInjectionContext)) {
                        try {
                            migrationCompleteListener.A6G();
                        } catch (Exception unused) {
                        }
                    }
                }
            });
            return;
        } else {
            str = TAG;
            str2 = "credentialProtectedContext is null";
        }
        AnonymousClass0NK.A01(str, str2);
    }

    @AutoGeneratedFactoryMethod
    public static final ApplicationDatabase A01(AbstractC02990bJ r4) {
        if (_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_INSTANCE = new ApplicationDatabase(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_INSTANCE;
    }

    public final List<Application> A03() {
        List<Application> A2t = this.mApplicationDao.A2t();
        if (A2t == null) {
            return new ArrayList();
        }
        return A2t;
    }

    public final List<String> A04(String str) {
        List<Application> A2y = this.mApplicationDao.A2y(str);
        ArrayList arrayList = new ArrayList();
        for (Application application : A2y) {
            arrayList.add(application.appId);
        }
        return arrayList;
    }

    public final void A05(String str) {
        List<AssetsForApplication> A2z = this.mApplicationDao.A2z(str);
        if (A2z != null && !A2z.isEmpty()) {
            AssetsForApplication assetsForApplication = A2z.get(0);
            this.mApplicationDao.A7M(assetsForApplication.application, assetsForApplication.assets);
        }
        this.mApplicationDao.A2w(str);
    }

    public final void A06(String str, AppStatus appStatus) {
        this.mApplicationDao.A8l(str, appStatus.ordinal());
    }

    @Override // com.oculus.alpenglow.boot.UnlockListener
    public final void A6h(Context context) {
        if (!this.mMigrationToDeviceProtectedComplete) {
            OculusThreadExecutor.A00().execute(new Runnable(A00()) {
                /* class com.oculus.alpenglow.database.$$Lambda$ApplicationDatabase$TVdeDyNMClBbbTP7ExVpokOCCDQ */
                public final /* synthetic */ Context f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ApplicationDatabase.A02(ApplicationDatabase.this, this.f$1);
                }
            });
        }
    }

    @Inject
    public ApplicationDatabase(AbstractC02990bJ r5) {
        Context A00;
        AnonymousClass0R7 r1 = new AnonymousClass0R7(2, r5);
        this._UL_mInjectionContext = r1;
        this.mSharedPreferences = ((Context) AnonymousClass0Lh.A03(0, 4, r1)).getSharedPreferences(DATABASE_NAME, 0);
        if (this.mMigrationToDeviceProtectedComplete || (A00 = A00()) == null) {
            AnonymousClass0Fn A002 = C01230Fm.A00((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext));
            A002.A01(Migrations.MIGRATION_1_2);
            AbstractApplicationDatabase abstractApplicationDatabase = (AbstractApplicationDatabase) A002.A00();
            this.mAbstractApplicationDatabase = abstractApplicationDatabase;
            this.mApplicationDao = abstractApplicationDatabase.A08();
            return;
        }
        AnonymousClass0Fn A003 = C01230Fm.A00(A00);
        A003.A01(Migrations.MIGRATION_1_2);
        AbstractApplicationDatabase abstractApplicationDatabase2 = (AbstractApplicationDatabase) A003.A00();
        this.mAbstractApplicationDatabase = abstractApplicationDatabase2;
        this.mApplicationDao = abstractApplicationDatabase2.A08();
        OculusThreadExecutor.A00().execute(new Runnable(A00) {
            /* class com.oculus.alpenglow.database.$$Lambda$ApplicationDatabase$Vx9F3W5AVCK4Aj4IU8YcoWZUKeY */
            public final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ApplicationDatabase applicationDatabase = ApplicationDatabase.this;
                Context context = this.f$1;
                AnonymousClass0Fn A00 = C01230Fm.A00((Context) AnonymousClass0Lh.A03(0, 4, applicationDatabase._UL_mInjectionContext));
                A00.A01(Migrations.MIGRATION_1_2);
                if (!((AbstractApplicationDatabase) A00.A00()).A08().A2t().isEmpty()) {
                    applicationDatabase.mMigrationToDeviceProtectedComplete = true;
                    applicationDatabase.mSharedPreferences.edit().putBoolean(ApplicationDatabase.MIGRATION_COMPLETE, applicationDatabase.mMigrationToDeviceProtectedComplete).apply();
                    return;
                }
                ApplicationDatabase.A02(applicationDatabase, context);
            }
        });
    }
}
