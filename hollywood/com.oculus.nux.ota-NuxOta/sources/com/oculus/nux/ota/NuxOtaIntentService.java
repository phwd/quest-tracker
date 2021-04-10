package com.oculus.nux.ota;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.util.Log;
import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthFbLoginResult;
import com.oculus.authapi.AuthLoginError;
import com.oculus.authapi.AuthLoginWithFbAuthError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.security.CallerInfoHelper;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NuxOtaIntentService extends IntentService {
    private static final String TAG = "NuxOtaIntentService";

    /* access modifiers changed from: private */
    public interface Resolver<T> {
        void reject(Throwable th);

        void resolve(T t);
    }

    /* access modifiers changed from: private */
    public static class SerializableResultReceiver<T> extends ResultReceiver {
        private final String mAction;
        private final T mDefaultValue;
        private final Resolver<T> mResolver;
        private final String mResultExtra;

        public SerializableResultReceiver(String str, T t, Resolver<T> resolver, String str2) {
            super(null);
            this.mAction = str;
            this.mDefaultValue = t;
            this.mResolver = resolver;
            this.mResultExtra = str2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: com.oculus.nux.ota.NuxOtaIntentService$Resolver<T> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            Object obj = bundle.get(this.mResultExtra);
            String str = NuxOtaIntentService.TAG;
            Log.d(str, "Received " + this.mAction + " result " + obj);
            if (obj == null) {
                this.mResolver.resolve(this.mDefaultValue);
            } else {
                this.mResolver.resolve(obj);
            }
        }

        public ResultReceiver serialized() {
            Parcel obtain = Parcel.obtain();
            writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            ResultReceiver resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
            obtain.recycle();
            return resultReceiver;
        }
    }

    private void sendBooleanResult(Intent intent, boolean z) {
        ResultReceiver resultReceiver;
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("RESULT", z);
            resultReceiver.send(0, bundle);
        }
    }

    private void sendFloatResult(Intent intent, float f) {
        ResultReceiver resultReceiver;
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putFloat("RESULT", f);
            resultReceiver.send(0, bundle);
        }
    }

    private void sendIntResult(Intent intent, int i) {
        ResultReceiver resultReceiver;
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("RESULT", i);
            resultReceiver.send(0, bundle);
        }
    }

    public NuxOtaIntentService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            String str = TAG;
            Log.d(str, "Received intent action: " + action);
            if ("nux.ota.GET_NUX_OTA_PROGRESS".equals(action)) {
                handleGetNuxOtaProgress(intent);
            } else if ("nux.ota.SET_OKAY_TO_REBOOT".equals(action)) {
                handleSetOkayToReboot(intent);
            } else if ("nux.ota.SKIP_HIGH_PRI_APPS".equals(action)) {
                handleSkipHighPriApps(intent);
            } else if ("nux.ota.SKIP_NUX".equals(action)) {
                handleSkipNux(intent);
            }
        }
    }

    private void handleGetNuxOtaProgress(Intent intent) {
        float f;
        NuxOta tryGetInstance = NuxOta.tryGetInstance();
        if (tryGetInstance != null) {
            f = tryGetInstance.getUpdateProgress();
            Log.d(TAG, "Current progress (%): " + f);
        } else {
            Telemetry telemetry = new Telemetry(this);
            NuxOtaState state = new NuxOtaSettings().getState();
            String str = "Attempting to get progress while NuxOta instance is null. Current state: " + state.toString();
            Log.e(TAG, str);
            telemetry.recordError(str);
            f = 0.0f;
        }
        sendFloatResult(intent, f);
    }

    private void handleSetOkayToReboot(Intent intent) {
        boolean z;
        NuxOta tryGetInstance = NuxOta.tryGetInstance();
        if (tryGetInstance != null) {
            tryGetInstance.setOkayToReboot();
            z = true;
        } else {
            z = false;
        }
        sendBooleanResult(intent, z);
    }

    private void handleSkipHighPriApps(Intent intent) {
        boolean z;
        NuxOta tryGetInstance = NuxOta.tryGetInstance();
        if (tryGetInstance != null) {
            Log.d(TAG, "Skipping high pri apps.");
            tryGetInstance.onHighPriAppsUpdateCompletedOrSkipped();
            z = true;
        } else {
            Log.e(TAG, "Unable to skip high pri apps -- NuxOta is not initialized.");
            z = false;
        }
        sendBooleanResult(intent, z);
    }

    private void handleSkipNux(Intent intent) {
        Log.d(TAG, "[Skip NUX] Skipping NUX.");
        int i = 1;
        try {
            optionallyProvisionWifi(intent);
            optinallyLoginWithEmailAndPassword(intent);
            setFirstTimeNuxFlags(intent);
            i = 0;
            sendIntResult(intent, 0);
            reboot();
        } catch (InterruptedException | RuntimeException | ExecutionException | TimeoutException e) {
            Log.e(TAG, "[Skip NUX] Error skipping NUX.", e);
            sendIntResult(intent, i);
        }
    }

    private void optionallyProvisionWifi(Intent intent) throws ExecutionException, InterruptedException, TimeoutException {
        if (!intent.hasExtra("WIFI_SSID")) {
            Log.d(TAG, "[Skip NUX] Wi-Fi information was not specified, skipping Wi-Fi provisioning.");
            return;
        }
        Log.d(TAG, "[Skip NUX] Provisioning Wi-Fi.");
        String stringExtra = intent.getStringExtra("WIFI_SSID");
        String stringExtra2 = intent.getStringExtra("WIFI_USER");
        String stringExtra3 = intent.getStringExtra("WIFI_PWD");
        String stringExtra4 = intent.getStringExtra("WIFI_AUTH");
        boolean booleanExtra = intent.getBooleanExtra("WIFI_HIDDEN", false);
        final Intent intent2 = new Intent();
        intent2.setAction("companion.SET_WIFI");
        intent2.setClassName("com.oculus.companion.server", "com.oculus.companion.server.CompanionService");
        intent2.putExtra("WIFI_SSID", stringExtra);
        intent2.putExtra("WIFI_USER", stringExtra2);
        intent2.putExtra("WIFI_PWD", stringExtra3);
        intent2.putExtra("WIFI_AUTH", stringExtra4);
        intent2.putExtra("WIFI_HIDDEN", booleanExtra);
        int intExtra = intent.getIntExtra("MAX_ATTEMPTS", 1);
        while (intExtra > 0) {
            try {
                final CompletableFuture completableFuture = new CompletableFuture();
                final AnonymousClass1 r1 = new Resolver<Boolean>() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass1 */

                    public void resolve(Boolean bool) {
                        if (bool.booleanValue()) {
                            completableFuture.complete(bool);
                        } else {
                            completableFuture.completeExceptionally(new Throwable("Could not connect to Wi-Fi"));
                        }
                    }

                    @Override // com.oculus.nux.ota.NuxOtaIntentService.Resolver
                    public void reject(Throwable th) {
                        completableFuture.completeExceptionally(th);
                    }
                };
                new Handler(getMainLooper()).post(new Runnable() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass2 */

                    public void run() {
                        NuxOtaIntentService.this.sendIntentForResult(this, intent2, "companion.SET_WIFI", r1, "CS_RESULT", false);
                    }
                });
                completableFuture.get(60, TimeUnit.SECONDS);
                return;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e(TAG, "[Skip NUX] Failed to connect to Wi-Fi.", e);
                intExtra--;
                if (intExtra > 0) {
                    Log.d(TAG, "[Skip NUX] Retrying.");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    throw e;
                }
            }
        }
    }

    private void optinallyLoginWithEmailAndPassword(Intent intent) throws ExecutionException, InterruptedException, RuntimeException, TimeoutException {
        if (intent.hasExtra("EMAIL") && intent.hasExtra("PWD")) {
            try {
                tryLoginWithEmailAndPassword(intent, intent.getBooleanExtra("FB", false));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                if (!intent.getBooleanExtra("FB", false)) {
                    tryLoginWithEmailAndPassword(intent, true);
                    return;
                }
                throw e;
            }
        } else if (intent.hasExtra("EMAIL") || intent.hasExtra("PWD")) {
            throw new RuntimeException("[Skip NUX] Incomplete login information.");
        } else {
            Log.d(TAG, "[Skip NUX] Login information was not specified, setting flags for default system user.");
        }
    }

    private void tryLoginWithEmailAndPassword(Intent intent, boolean z) throws ExecutionException, InterruptedException, TimeoutException {
        String str = TAG;
        Object[] objArr = new Object[1];
        objArr[0] = z ? "FB" : "OC";
        Log.d(str, String.format("[Skip NUX] Attempting to login with %s account.", objArr));
        OVRAuth oVRAuth = new OVRAuth(this, new OVRAuth.CallerInfoProvider() {
            /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass3 */

            @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
            public Intent attachCallerInfo(Intent intent) {
                try {
                    CallerInfoHelper.attachCallerInfo(intent, this, null);
                    return intent;
                } catch (Exception e) {
                    Log.e(NuxOtaIntentService.TAG, "[Skip NUX] Error attaching caller info.", e);
                    return intent;
                }
            }
        });
        String stringExtra = intent.getStringExtra("EMAIL");
        String stringExtra2 = intent.getStringExtra("PWD");
        String stringExtra3 = intent.getStringExtra("TWO_FAC_CODE");
        int intExtra = intent.getIntExtra("MAX_ATTEMPTS", 1);
        if (z) {
            tryLoginWithFb(this, oVRAuth, stringExtra, stringExtra2, stringExtra3, intExtra);
        } else {
            tryLoginWithOc(this, oVRAuth, stringExtra, stringExtra2, intExtra);
        }
    }

    private void tryLoginWithFb(Context context, final OVRAuth oVRAuth, final String str, final String str2, final String str3, int i) throws ExecutionException, InterruptedException, TimeoutException {
        Throwable e;
        int i2 = i;
        while (true) {
            try {
                final CompletableFuture completableFuture = new CompletableFuture();
                final AnonymousClass4 r6 = new AuthResultCallback<AuthFbLoginResult, AuthFbLoginError>() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass4 */

                    public void onResult(AuthFbLoginResult authFbLoginResult) {
                        completableFuture.complete(new SkipNuxFbLoginResult(authFbLoginResult));
                    }

                    public void onError(AuthFbLoginError authFbLoginError) {
                        completableFuture.complete(new SkipNuxFbLoginResult(authFbLoginError));
                    }
                };
                new Handler(getMainLooper()).post(new Runnable() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass5 */

                    public void run() {
                        oVRAuth.loginToFb(str, str2, r6);
                    }
                });
                SkipNuxFbLoginResult skipNuxFbLoginResult = (SkipNuxFbLoginResult) completableFuture.get(60, TimeUnit.SECONDS);
                AuthFbLoginResult result = skipNuxFbLoginResult.getResult();
                final AuthFbLoginError error = skipNuxFbLoginResult.getError();
                if (!(error == null || error.getErrorCode() != -8 || str3 == null)) {
                    final CompletableFuture completableFuture2 = new CompletableFuture();
                    final AnonymousClass6 r7 = new AuthResultCallback<AuthFbLoginResult, AuthFbLoginError>() {
                        /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass6 */

                        public void onResult(AuthFbLoginResult authFbLoginResult) {
                            completableFuture2.complete(authFbLoginResult);
                        }

                        public void onError(AuthFbLoginError authFbLoginError) {
                            completableFuture2.completeExceptionally(authFbLoginError);
                        }
                    };
                    new Handler(getMainLooper()).post(new Runnable() {
                        /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass7 */

                        public void run() {
                            oVRAuth.completeFbLoginWithTwoFactorCode(str, error.getUid(), error.getMachineId(), error.getLoginFirstFactor(), str3, r7);
                        }
                    });
                    result = (AuthFbLoginResult) completableFuture2.get(60, TimeUnit.SECONDS);
                }
                final CompletableFuture completableFuture3 = new CompletableFuture();
                final AnonymousClass8 r2 = new AuthResultCallback<Void, AuthLoginWithFbAuthError>() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass8 */

                    public void onResult(Void r1) {
                        completableFuture3.complete(r1);
                    }

                    public void onError(AuthLoginWithFbAuthError authLoginWithFbAuthError) {
                        completableFuture3.completeExceptionally(authLoginWithFbAuthError);
                    }
                };
                final String accessToken = result.getAccessToken();
                try {
                    new Handler(getMainLooper()).post(new Runnable() {
                        /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass9 */

                        public void run() {
                            oVRAuth.loginWithFbAuth(accessToken, r2);
                        }
                    });
                    completableFuture3.get(60, TimeUnit.SECONDS);
                    return;
                } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                    e = e2;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e3) {
                e = e3;
                Log.e(TAG, "[Skip NUX] Failed to login.", e);
                i2--;
                if (i2 > 0) {
                    Log.d(TAG, "[Skip NUX] Retrying.");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    throw e;
                }
            }
            Log.d(TAG, "[Skip NUX] Retrying.");
            TimeUnit.SECONDS.sleep(5);
        }
    }

    private void tryLoginWithOc(Context context, final OVRAuth oVRAuth, final String str, final String str2, int i) throws ExecutionException, InterruptedException, TimeoutException {
        while (true) {
            try {
                final CompletableFuture completableFuture = new CompletableFuture();
                final AnonymousClass10 r5 = new AuthResultCallback<Void, AuthLoginError>() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass10 */

                    public void onResult(Void r1) {
                        completableFuture.complete(r1);
                    }

                    public void onError(AuthLoginError authLoginError) {
                        completableFuture.completeExceptionally(authLoginError);
                    }
                };
                new Handler(getMainLooper()).post(new Runnable() {
                    /* class com.oculus.nux.ota.NuxOtaIntentService.AnonymousClass11 */

                    public void run() {
                        oVRAuth.loginWithEmailAndPassword(str, str2, r5);
                    }
                });
                completableFuture.get(60, TimeUnit.SECONDS);
                return;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e(TAG, "[Skip NUX] Failed to login.", e);
                i--;
                if (i > 0) {
                    Log.d(TAG, "[Skip NUX] Retrying.");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    throw e;
                }
            }
        }
    }

    private void setFirstTimeNuxFlags(Intent intent) {
        Log.d(TAG, "[Skip NUX] Setting flags.");
        int currentUser = ActivityManager.getCurrentUser();
        boolean booleanExtra = intent.getBooleanExtra("PRE_OTA_ONLY", false);
        FirstTimeNuxManager.setOtaComplete();
        FirstTimeNuxManager.setFirstTimeNuxPreOtaComplete(true);
        Settings.Secure.putInt(getContentResolver(), "user_setup_complete", 1);
        if (!booleanExtra) {
            FirstTimeNuxManager.setFirstTimeNuxFlow("full_vr");
            FirstTimeNuxManager.setFirstTimeNuxHealthSafetyComplete(true, currentUser);
            FirstTimeNuxManager.setFirstTimeNuxAllowGuardian(true, currentUser);
            FirstTimeNuxManager.setFirstTimeNuxGuardianSetupEntered(true, currentUser);
            FirstTimeNuxManager.setFirstTimeNuxGuardianSetupComplete(true, currentUser);
            FirstTimeNuxManager.setFirstTimeNuxComplete(true, currentUser);
            return;
        }
        Log.d(TAG, "[Skip NUX] Skipping only pre-OTA NUX.");
    }

    private void reboot() {
        Log.d(TAG, "[Skip NUX] Rebooting.");
        ((PowerManager) getSystemService("power")).reboot(null);
    }

    /* access modifiers changed from: protected */
    public <T> void sendIntentForResult(Context context, Intent intent, String str, Resolver<T> resolver, String str2, T t) {
        intent.putExtra("RESULT_RECEIVER", new SerializableResultReceiver(str, t, resolver, str2).serialized());
        try {
            context.startService(intent);
        } catch (Exception e) {
            resolver.reject(e);
        }
    }
}
