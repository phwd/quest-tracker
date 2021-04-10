package com.oculus.location.geocoder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.WorkSource;
import android.provider.Settings;
import android.util.Log;
import com.android.location.provider.LocationProviderBase;
import com.android.location.provider.ProviderPropertiesUnbundled;
import com.android.location.provider.ProviderRequestUnbundled;
import com.oculus.aidl.ILocationCallback;
import com.oculus.aidl.ILocationServiceInterface;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import oculus.internal.Gatekeeper;

/* access modifiers changed from: package-private */
public class NetworkLocationProvider extends LocationProviderBase {
    private static final boolean DEBUG = false;
    private static final int PRECISE_LOCATION_REQUEST_TYPE = 3;
    private static final ProviderPropertiesUnbundled PROPS = ProviderPropertiesUnbundled.create(true, (boolean) DEBUG, (boolean) DEBUG, (boolean) DEBUG, (boolean) DEBUG, (boolean) DEBUG, (boolean) DEBUG, 1, 1);
    private static final String TAG = NetworkLocationProvider.class.getSimpleName();
    private final int FASTEST_REFRESH_INTERVAL = 900000;
    private Context mContext;
    private Handler mHandler;
    private Gatekeeper mLocationGatekeeper;
    private final ThreadHelper mThreadHelper;

    public NetworkLocationProvider(Context context) {
        super(TAG, PROPS);
        this.mContext = context;
        this.mHandler = new Handler();
        this.mLocationGatekeeper = new Gatekeeper(2);
        this.mThreadHelper = new ThreadHelper(context);
        turnLocationOn(context);
    }

    private void turnLocationOn(Context context) {
        if (!Settings.Secure.isLocationProviderEnabled(context.getContentResolver(), "network")) {
            Settings.Secure.putInt(context.getContentResolver(), "location_mode", PRECISE_LOCATION_REQUEST_TYPE);
        }
    }

    public void onDump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public void onDisable() {
        this.mThreadHelper.disable();
    }

    public void onEnable() {
    }

    public int onGetStatus(Bundle extras) {
        return 2;
    }

    public long onGetStatusUpdateTime() {
        return 0;
    }

    public void onSetRequest(ProviderRequestUnbundled request, WorkSource source) {
        if (this.mLocationGatekeeper.isEnabled()) {
            long autoTime = Math.max(request.getInterval(), 900000L);
            if (request.getReportLocation()) {
                this.mThreadHelper.setTime(autoTime);
                this.mThreadHelper.enable();
                return;
            }
            this.mThreadHelper.disable();
            return;
        }
        this.mThreadHelper.disable();
    }

    private class ThreadHelper extends ILocationCallback.Stub implements Runnable, ServiceConnection {
        private static final String LOCATION_SERVICE_CLASS = "com.oculus.ocms.locationservices.LocationService";
        private static final int RECONNECT_TIME_DELAY = 5000;
        private boolean mBound = NetworkLocationProvider.DEBUG;
        private final Context mContext;
        private final AtomicBoolean mEnabled = new AtomicBoolean(NetworkLocationProvider.DEBUG);
        private ScheduledThreadPoolExecutor mExecutor;
        private boolean mGetLocationOnConnection = NetworkLocationProvider.DEBUG;
        private Handler mHandler = new Handler(Looper.getMainLooper());
        private ILocationServiceInterface mILocationServiceInterface;
        private long mTime = 900000;

        public ThreadHelper(Context context) {
            this.mContext = context;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            this.mBound = true;
            this.mILocationServiceInterface = ILocationServiceInterface.Stub.asInterface(service);
            if (this.mGetLocationOnConnection) {
                this.mGetLocationOnConnection = NetworkLocationProvider.DEBUG;
                run();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            this.mBound = NetworkLocationProvider.DEBUG;
            this.mGetLocationOnConnection = NetworkLocationProvider.DEBUG;
            reload();
        }

        @Override // com.oculus.aidl.ILocationCallback
        public void report(final Location location) {
            if (location != null) {
                this.mHandler.post(new Runnable() {
                    /* class com.oculus.location.geocoder.NetworkLocationProvider.ThreadHelper.AnonymousClass1 */

                    public void run() {
                        NetworkLocationProvider.this.reportLocation(location);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void connectToLocationService() {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.oculus.ocms", LOCATION_SERVICE_CLASS));
                this.mContext.bindService(intent, this, 1);
            } catch (Exception e) {
                String str = NetworkLocationProvider.TAG;
                Log.e(str, "Error: " + e.getLocalizedMessage());
            }
        }

        private void reload() {
            disable();
            enable();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void disable() {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mExecutor;
            if (scheduledThreadPoolExecutor != null) {
                scheduledThreadPoolExecutor.shutdownNow();
            }
            try {
                this.mContext.unbindService(this);
            } catch (Exception e) {
                String str = NetworkLocationProvider.TAG;
                Log.e(str, "Error: " + e.getLocalizedMessage());
            }
            this.mEnabled.set(NetworkLocationProvider.DEBUG);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTime(long time) {
            this.mTime = time;
        }

        private void reset() {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mExecutor;
            if (scheduledThreadPoolExecutor != null) {
                scheduledThreadPoolExecutor.shutdownNow();
                this.mExecutor = null;
            }
            this.mExecutor = new ScheduledThreadPoolExecutor(1);
            this.mExecutor.scheduleAtFixedRate(this, 0, this.mTime, TimeUnit.MILLISECONDS);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void enable() {
            if (this.mEnabled.compareAndSet(NetworkLocationProvider.DEBUG, true)) {
                connectToLocationService();
            }
            reset();
        }

        public void run() {
            if (this.mBound) {
                ILocationServiceInterface iLocationServiceInterface = this.mILocationServiceInterface;
                if (iLocationServiceInterface != null) {
                    try {
                        iLocationServiceInterface.getLocationByType(this, NetworkLocationProvider.PRECISE_LOCATION_REQUEST_TYPE);
                    } catch (Exception e) {
                        String str = NetworkLocationProvider.TAG;
                        Log.e(str, "onServiceConnected: Exception = " + e.getLocalizedMessage());
                    }
                }
            } else {
                this.mGetLocationOnConnection = true;
                this.mHandler.postDelayed(new Runnable() {
                    /* class com.oculus.location.geocoder.NetworkLocationProvider.ThreadHelper.AnonymousClass2 */

                    public void run() {
                        ThreadHelper.this.connectToLocationService();
                    }
                }, 5000);
            }
        }
    }
}
