package com.oculus.linefrequencyservice;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.oculus.aidl.ILocationCallback;
import com.oculus.aidl.ILocationServiceInterface;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.SettingsManager;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import oculus.internal.Constants;

public class LineFrequencyService extends IntentService {
    private static final String ACTION_PREFIX = "OC_LOCATION_";
    public static final String ACTION_UPDATE_LINE_FREQUENCY = "OC_LOCATION_UPDATE_LINE_FREQUENCY";
    public static final String ACTION_USER_SET_LINE_FREQUENCY = "OC_LOCATION_USER_SET_LINE_FREQUENCY";
    private static final String CAMERA_LINE_FREQUENCY_DETECTION = "oculus.software.camera.line_frequency_detection";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GRID_LINE_FREQUENCY = 60;
    private static final int GLOBAL_GRID_LINE_FREQUENCY_AUTO = -1;
    private static final long LOCATION_FETCH_TIMEOUT = 30;
    private static final String LOCATION_SERVICE_CLASS = "com.oculus.ocms.locationservices.LocationService";
    private static final long LOCATION_SERVICE_CONNECT_TIMEOUT = 15;
    private static final String TAG = LineFrequencyService.class.getSimpleName();
    private ILocationHandler mLineFrequencyLocationHandler = new ILocationHandler() {
        /* class com.oculus.linefrequencyservice.LineFrequencyService.AnonymousClass1 */

        @Override // com.oculus.linefrequencyservice.LineFrequencyService.ILocationHandler
        public void handle(Location location) {
            if (location != null) {
                Bundle extras = location.getExtras();
                String country = extras.getString("country", "UNKNOWN");
                int detectedLineFreqency = LineFrequencyService.this.getDetectedLineFrequencySetting();
                int gridLineFrequency = extras.getInt("gridLineFrequency", detectedLineFreqency);
                if (!(gridLineFrequency == 50 || gridLineFrequency == 60)) {
                    AnalyticsEvent event = new AnalyticsEvent("oculus_mobile_aloge");
                    event.setExtra("aloge_msg", "Location has invalid grid line frequency value");
                    event.setExtra("aloge_extra", String.format("country: %s, detectedLineFrequency: %d, gridLineFrequency: %d", country, Integer.valueOf(detectedLineFreqency), Integer.valueOf(gridLineFrequency)));
                    event.setExtra("prog_name", "com.oculus.linefrequencyservice");
                    event.setExtra("log_tag", LineFrequencyService.TAG);
                    UnifiedTelemetryLogger.getInstance(LineFrequencyService.this.getApplicationContext()).reportEvent(event, LineFrequencyService.DEBUG);
                    String str = LineFrequencyService.TAG;
                    Log.w(str, "Location has invalid grid line frequency value: " + gridLineFrequency + ", using default");
                    gridLineFrequency = 60;
                }
                LineFrequencyService.this.setDetectedLineFrequencySetting(gridLineFrequency);
            }
        }
    };
    private SettingsManager settingsManager = new SettingsManager();

    private interface ILocationHandler {
        void handle(Location location);
    }

    public LineFrequencyService() {
        super(LineFrequencyService.class.getSimpleName());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 149
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.linefrequencyservice.LineFrequencyService.onHandleIntent(android.content.Intent):void");
    }

    private int getLineFrequencyGlobalSetting() {
        return this.settingsManager.getInt(SettingsManager.ELECTRIC_GRID_LINE_FREQUENCY, -1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getDetectedLineFrequencySetting() {
        return this.settingsManager.getInt(SettingsManager.DETECTED_ELECTRIC_GRID_LINE_FREQUENCY, 60);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDetectedLineFrequencySetting(int lineFrequencyValue) {
        this.settingsManager.setInt(SettingsManager.DETECTED_ELECTRIC_GRID_LINE_FREQUENCY, lineFrequencyValue);
    }

    private class LocationHelper extends ILocationCallback.Stub implements ServiceConnection {
        private final Context mContext;
        private ILocationServiceInterface mILocationServiceInterface;
        private CountDownLatch mLatch;
        private List<ILocationHandler> mLocationHandlers;

        private LocationHelper(Context context, List<ILocationHandler> locationHandlers) {
            this.mContext = context;
            this.mLocationHandlers = locationHandlers;
            this.mLatch = new CountDownLatch(1);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void handleLocationUpdate() {
            if (this.mILocationServiceInterface == null) {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(Constants.OCMS_PACKAGE, LineFrequencyService.LOCATION_SERVICE_CLASS));
                    this.mContext.bindServiceAsUser(intent, this, 1, UserHandle.SYSTEM);
                    this.mLatch.await(LineFrequencyService.LOCATION_SERVICE_CONNECT_TIMEOUT, TimeUnit.SECONDS);
                } catch (Exception e) {
                    String str = LineFrequencyService.TAG;
                    Log.e(str, "Error: " + e.getLocalizedMessage());
                    return;
                }
            }
            ILocationServiceInterface iLocationServiceInterface = this.mILocationServiceInterface;
            if (iLocationServiceInterface != null) {
                try {
                    iLocationServiceInterface.getLocationUsingIPOnly(this);
                } catch (RemoteException e2) {
                    String str2 = LineFrequencyService.TAG;
                    Log.e(str2, "onServiceConnected: RemoteException = " + e2.getLocalizedMessage());
                }
                try {
                    this.mLatch.await(LineFrequencyService.LOCATION_FETCH_TIMEOUT, TimeUnit.SECONDS);
                } catch (InterruptedException e3) {
                    String str3 = LineFrequencyService.TAG;
                    Log.e(str3, "Error: " + e3.getLocalizedMessage());
                }
                this.mContext.unbindService(this);
                this.mILocationServiceInterface = null;
            }
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            this.mILocationServiceInterface = ILocationServiceInterface.Stub.asInterface(service);
            this.mLatch.countDown();
            this.mLatch = new CountDownLatch(1);
        }

        public void onServiceDisconnected(ComponentName name) {
            this.mILocationServiceInterface = null;
        }

        @Override // com.oculus.aidl.ILocationCallback
        public void report(Location location) {
            List<ILocationHandler> list = this.mLocationHandlers;
            if (list != null) {
                for (ILocationHandler locationHandler : list) {
                    if (locationHandler != null) {
                        locationHandler.handle(location);
                    }
                }
            }
            this.mLatch.countDown();
        }
    }
}
