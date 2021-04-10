package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.modules.codegen.FirstTimeNuxOtaModule;
import com.oculus.modules.codegen.Resolver;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class FirstTimeNuxOtaModuleImpl extends FirstTimeNuxOtaModule {
    private static final String ACTION_BASE = "nux.ota.";
    private static final String ACTION_GET_NUX_OTA_PROGRESS = "nux.ota.GET_NUX_OTA_PROGRESS";
    private static final String ACTION_NUX_OTA_PROGRESS_UPDATE = "com.oculus.nux.ota.NUX_OTA_PROGRESS_UPDATE";
    private static final String ACTION_SET_OKAY_TO_REBOOT = "nux.ota.SET_OKAY_TO_REBOOT";
    private static final String ACTION_SKIP_NUX = "nux.ota.SKIP_NUX";
    private static final String EXTRA_NUX_OTA_PROGRESS = "PROGRESS";
    private static final String EXTRA_RESULT = "RESULT";
    private static final String EXTRA_RESULT_RECEIVER = "RESULT_RECEIVER";
    private static final String FIRST_TIME_NUX_PRE_OTA_COMPLETE_KEY = "first_time_nux_pre_ota_complete";
    private static final String NUX_OTA_INTENT_SERVICE = "com.oculus.nux.ota.NuxOtaIntentService";
    private static final String NUX_OTA_PACKAGE = "com.oculus.nux.ota";
    private static final long PROGRESS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(1);
    private static final String TAG = FirstTimeNuxOtaModuleImpl.class.getSimpleName();
    private final Context mContext;
    private Timer mProgressCheckTimer;

    public FirstTimeNuxOtaModuleImpl(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_NUX_OTA_PROGRESS_UPDATE);
        return filter;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        String action = intent.getAction();
        char c = 65535;
        switch (action.hashCode()) {
            case -1421962557:
                if (action.equals(ACTION_NUX_OTA_PROGRESS_UPDATE)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                handleNuxOtaProgressUpdate(intent);
                return;
            default:
                Log.e(TAG, String.format("Unhandled action: %s", action));
                return;
        }
    }

    private void handleNuxOtaProgressUpdate(Intent intent) {
        float progress = intent.getFloatExtra(EXTRA_NUX_OTA_PROGRESS, 0.0f);
        updateOtaProgress(progress);
        if (progress < 100.0f) {
            restartProgressCheckTimerIfStarted();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateOtaProgress(float progress) {
        Log.d(TAG, String.format("NUX OTA progress updated to %.2f%%.", Float.valueOf(progress)));
        FirstTimeNuxOtaModule.OTAProgressUpdate update = new FirstTimeNuxOtaModule.OTAProgressUpdate();
        update.progress = (double) (progress / 100.0f);
        emitOnOTAProgressUpdated(update);
        if (progress >= 100.0f) {
            stopProgressCheckTimerIfStarted();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxOtaModule
    public void fetchOtaProgressImpl(Resolver<Double> resolver) {
        requestValue(ACTION_GET_NUX_OTA_PROGRESS, Double.valueOf(0.0d), resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxOtaModule
    public void setOkayToRebootImpl(Resolver<Boolean> resolver) {
        requestValue(ACTION_SET_OKAY_TO_REBOOT, Boolean.FALSE, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxOtaModule
    public void skipNuxImpl(Resolver<Boolean> resolver) {
        requestValue(ACTION_SKIP_NUX, Boolean.FALSE, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxOtaModule
    public void startProgressChecksImpl(Resolver<Boolean> resolver) {
        resolver.resolve(Boolean.valueOf(startProgressChecksIfNotStarted()));
    }

    private boolean startProgressChecksIfNotStarted() {
        if (this.mProgressCheckTimer == null) {
            this.mProgressCheckTimer = new Timer("Progress Check Timer");
            this.mProgressCheckTimer.schedule(new TimerTask() {
                /* class com.oculus.modules.FirstTimeNuxOtaModuleImpl.AnonymousClass1 */

                public void run() {
                    FirstTimeNuxOtaModuleImpl.this.checkProgress();
                }
            }, new Date(), PROGRESS_CHECK_INTERVAL_MS);
            return true;
        }
        Log.w(TAG, "Already checking for progress.");
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkProgress() {
        Log.d(TAG, "Performing a manual progress check.");
        requestValue(ACTION_GET_NUX_OTA_PROGRESS, Float.valueOf(0.0f), new Resolver<Float>() {
            /* class com.oculus.modules.FirstTimeNuxOtaModuleImpl.AnonymousClass2 */

            public void resolve(Float progress) {
                if (progress != null) {
                    FirstTimeNuxOtaModuleImpl.this.updateOtaProgress(progress.floatValue());
                } else {
                    Log.e(FirstTimeNuxOtaModuleImpl.TAG, "Received null for progress.");
                }
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                Log.e(FirstTimeNuxOtaModuleImpl.TAG, error.getMessage());
            }
        });
    }

    private void restartProgressCheckTimerIfStarted() {
        if (this.mProgressCheckTimer != null) {
            stopProgressCheckTimerIfStarted();
            startProgressChecksIfNotStarted();
        }
    }

    private void stopProgressCheckTimerIfStarted() {
        if (this.mProgressCheckTimer != null) {
            this.mProgressCheckTimer.cancel();
            this.mProgressCheckTimer = null;
        }
    }

    /* access modifiers changed from: protected */
    public <T> void requestValue(String action, T defaultValue, Resolver<T> resolver) {
        SerializableResultReceiver<T> resultReceiver = new SerializableResultReceiver<>(action, defaultValue, resolver);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClassName(NUX_OTA_PACKAGE, NUX_OTA_INTENT_SERVICE);
        intent.putExtra(EXTRA_RESULT_RECEIVER, resultReceiver.serialized());
        try {
            Log.d(TAG, "Sending a request: " + action);
            this.mContext.startService(intent);
        } catch (Exception exception) {
            Log.e(TAG, "Failed to start intent service.");
            resolver.reject(exception);
        }
    }

    /* access modifiers changed from: private */
    public static class SerializableResultReceiver<T> extends ResultReceiver {
        private final String mAction;
        private final T mDefaultValue;
        private final Resolver<T> mResolver;

        public SerializableResultReceiver(String action, T defaultValue, Resolver<T> resolver) {
            super(null);
            this.mAction = action;
            this.mDefaultValue = defaultValue;
            this.mResolver = resolver;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: com.oculus.modules.codegen.Resolver<T> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: protected */
        public void onReceiveResult(int resultCode, Bundle resultData) {
            Object obj = resultData.get(FirstTimeNuxOtaModuleImpl.EXTRA_RESULT);
            Log.d(FirstTimeNuxOtaModuleImpl.TAG, "Received " + this.mAction + " result " + obj);
            if (obj == null) {
                this.mResolver.resolve(this.mDefaultValue);
            } else {
                this.mResolver.resolve(obj);
            }
        }

        public ResultReceiver serialized() {
            Parcel parcel = Parcel.obtain();
            writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            ResultReceiver serializedReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return serializedReceiver;
        }
    }
}
