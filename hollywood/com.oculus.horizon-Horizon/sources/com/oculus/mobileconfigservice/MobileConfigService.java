package com.oculus.mobileconfigservice;

import X.AnonymousClass006;
import X.AnonymousClass0CC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0bU;
import X.C003108z;
import X.C02780bN;
import X.C02790bO;
import X.C02870bf;
import X.C02880bg;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.aidl.IMobileConfigService;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.config.updater.logging.ConfigUpdaterLogger;
import com.oculus.util.thread.ThreadUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.annotation.Nullable;

public class MobileConfigService extends Service {
    public static final String TAG = "MobileConfigService";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final IMobileConfigService.Stub mBinder = new IMobileConfigService.Stub() {
        /* class com.oculus.mobileconfigservice.MobileConfigService.AnonymousClass1 */

        @Nullable
        private C02790bO A00() {
            C02790bO A01 = C02870bf.A01(MobileConfigService.this.mContext);
            MobileConfigService mobileConfigService = MobileConfigService.this;
            if (!mobileConfigService.mTrustedApp.A06(mobileConfigService.mContext)) {
                Context context = MobileConfigService.this.mContext;
                if (!C02870bf.A03(AnonymousClass0bU.A03(context, context.getPackageName())) || A01.A01() == null || !C02870bf.A03(AnonymousClass0bU.A03(MobileConfigService.this.mContext, A01.A01()))) {
                    return null;
                }
            }
            return A01;
        }

        @Override // com.oculus.aidl.IMobileConfigService
        public final void logExposure(String str, String str2, String str3) {
            ConfigUpdaterLogger configUpdaterLogger;
            String str4;
            ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_ipc_horizon_log_exposure_reached", AnonymousClass006.A09(str, ":", str2, ":", str3));
            C02790bO A00 = A00();
            if (A00 == null) {
                AnonymousClass0NO.A08(MobileConfigService.TAG, "MobileConfig log exposure from third party app");
                configUpdaterLogger = (ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext);
                str4 = "mc_fetch_failed";
            } else if (A00.A01() == null) {
                AnonymousClass0NO.A08(MobileConfigService.TAG, "MobileConfig no package name for log exposure");
                configUpdaterLogger = (ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext);
                str4 = "mc_log_exposure_failed";
            } else {
                ThreadUtils.A03("must not be run on the ui thread");
                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_ipc_horizon_log_exposure_filters_passed", AnonymousClass006.A09(str, ":", str2, ":", str3));
                if (!"".equals(str3)) {
                    MobileConfigService mobileConfigService = MobileConfigService.this;
                    ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, mobileConfigService._UL_mInjectionContext)).logMobileConfigExposure(str3, mobileConfigService.mCredentials.mUserId);
                    return;
                }
                return;
            }
            configUpdaterLogger.logMobileConfigStatus(str4, "signature_check_failed");
        }

        @Override // com.oculus.aidl.IMobileConfigService
        @Nullable
        public final ParcelFileDescriptor updateMCs(String str, ParcelFileDescriptor parcelFileDescriptor, int i) {
            String str2;
            ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_config_fetch_ipc_reached_horizon", "");
            C02790bO A00 = A00();
            if (A00 == null) {
                AnonymousClass0NO.A08(MobileConfigService.TAG, "MobileConfig fetch from third party app");
                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", "signature_check_failed_app_identity_null");
                return null;
            }
            String A01 = A00.A01();
            if (A01 == null) {
                AnonymousClass0NO.A08(MobileConfigService.TAG, "MobileConfig no package name for log exposure");
                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_log_exposure_failed", "signature_check_failed_package_name_null");
                return null;
            }
            ThreadUtils.A03("must not be run on the ui thread");
            ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_config_fetch_ipc_validation_passed", "");
            try {
                ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
                byte[] bArr = new byte[autoCloseInputStream.available()];
                autoCloseInputStream.read(bArr);
                autoCloseInputStream.close();
                String str3 = new String(bArr, "UTF-8");
                MobileConfigUpdater mobileConfigUpdater = (MobileConfigUpdater) AnonymousClass0J2.A03(2, 590, MobileConfigService.this._UL_mInjectionContext);
                HandlerThread handlerThread = new HandlerThread("WriteConfigDefinitionToDisk");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).post(new Runnable(str3, A01) {
                    /* class com.oculus.mobileconfigservice.MobileConfigUpdater.AnonymousClass2 */
                    public final /* synthetic */ String val$configJsonString;
                    public final /* synthetic */ String val$packageName;

                    {
                        this.val$configJsonString = r2;
                        this.val$packageName = r3;
                    }

                    public final void run() {
                        ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigUpdater.this._UL_mInjectionContext)).logMobileConfigStatus("mc_write_file_defintion_start", this.val$configJsonString);
                        synchronized (MobileConfigUpdater.A01(this.val$packageName)) {
                            try {
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(((Context) AnonymousClass0J2.A03(0, 294, MobileConfigUpdater.this._UL_mInjectionContext)).openFileOutput(AnonymousClass006.A05(this.val$packageName, "_config_definition.txt"), 0));
                                outputStreamWriter.write(this.val$configJsonString);
                                outputStreamWriter.close();
                                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigUpdater.this._UL_mInjectionContext)).logMobileConfigStatus("mc_write_file_defintion_succeeded", AnonymousClass006.A09("config definition - ", this.val$configJsonString, "; file name - ", this.val$packageName, "_config_definition.txt"));
                            } catch (IOException e) {
                                AnonymousClass0NO.A0B(MobileConfigUpdater.TAG, "failed to write config definition ", e);
                                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigUpdater.this._UL_mInjectionContext)).logMobileConfigStatus("mc_write_file_defintion_failed", e.getMessage());
                            }
                        }
                    }
                });
                MobileConfigUpdater.A00(mobileConfigUpdater, A01, str3);
                try {
                    synchronized (MobileConfigUpdater.A01(A01)) {
                        str2 = MobileConfigUpdater.A02(((Context) AnonymousClass0J2.A03(0, 294, mobileConfigUpdater._UL_mInjectionContext)).openFileInput(AnonymousClass006.A05(A01, "_config_values.txt")));
                        ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, mobileConfigUpdater._UL_mInjectionContext)).logMobileConfigStatus("mc_config_fetch_ipc_return_from_horizon", AnonymousClass006.A0B("config definition - ", str3, "; config value - ", str2, "; file name - ", A01, "_config_definition.txt"));
                    }
                } catch (FileNotFoundException e) {
                    ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, mobileConfigUpdater._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", AnonymousClass006.A05("config values file not found ", e.getMessage()));
                    str2 = "";
                }
                ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                ParcelFileDescriptor parcelFileDescriptor2 = createPipe[0];
                new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]).write(str2.getBytes("UTF-8"));
                return parcelFileDescriptor2;
            } catch (IOException e2) {
                AnonymousClass0NO.A0B(MobileConfigService.TAG, "error fetching configs", e2);
                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", AnonymousClass006.A05("io_exception ", e2.getMessage()));
                return null;
            } catch (NullPointerException e3) {
                AnonymousClass0NO.A0B(MobileConfigService.TAG, "error fetching configs", e3);
                ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigService.this._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", AnonymousClass006.A05("npe_during_fetch ", e3.getMessage()));
                return null;
            }
        }
    };
    @Inject
    @Eager
    @ForAppContext
    public Context mContext;
    @Inject
    @Eager
    public Credentials mCredentials;
    public C02870bf mTrustedApp;

    public final int onStartCommand(Intent intent, int i, int i2) {
        return 3;
    }

    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r2 = AnonymousClass0J2.get(this);
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r2);
        this.mContext = C003108z.A00(r2);
        this.mCredentials = CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_ACCESS_METHOD(r2);
        AnonymousClass0CC r1 = new AnonymousClass0CC();
        r1.A04(C02780bN.A0L);
        r1.A04(C02780bN.A0P);
        this.mTrustedApp = C02880bg.A01(r1.build());
    }

    public final IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
