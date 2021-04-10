package com.oculus.ocms.locationservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.aidl.ILocationCallback;
import com.oculus.aidl.ILocationServiceInterface;
import com.oculus.common.build.BuildConstants;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class LocationRequestHelper implements ServiceConnection {
    private static final long LOCATION_FETCH_TIMEOUT = 30;
    private static final String LOCATION_SERVICE_CLASS = "com.oculus.ocms.locationservices.LocationService";
    private static final long LOCATION_SERVICE_CONNECT_TIMEOUT = 15;
    private static final String OCMS_PACKAGE = "com.oculus.ocms";
    private static final String TAG = "LocationRequestHelper";
    private final Context mAppContext;
    @Nullable
    private CountDownLatch mConnectionLatch;
    @Nullable
    private ILocationServiceInterface mILocationServiceInterface;
    private String mOutput = "";
    @Nullable
    private CountDownLatch mResultLatch;

    public LocationRequestHelper(Context context) {
        this.mAppContext = context;
    }

    public Result getLocation() {
        if (this.mILocationServiceInterface == null) {
            try {
                this.mConnectionLatch = new CountDownLatch(1);
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.oculus.ocms", LOCATION_SERVICE_CLASS));
                this.mAppContext.bindService(intent, this, 1);
                this.mConnectionLatch.await(LOCATION_SERVICE_CONNECT_TIMEOUT, TimeUnit.SECONDS);
            } catch (InterruptedException | SecurityException e) {
                return createError("Unhandled exception while trying connect to location service: " + e.toString());
            }
        }
        if (this.mILocationServiceInterface == null) {
            return createError("Failed to connect to LocationService");
        }
        if (BuildConstants.DEBUG) {
            BLog.d(TAG, "Connected to LocationService");
        }
        this.mResultLatch = new CountDownLatch(1);
        try {
            this.mILocationServiceInterface.getLocationWithTimezoneUsingIPOnly(new LocationCallback());
            try {
                this.mResultLatch.await(30, TimeUnit.SECONDS);
                this.mAppContext.unbindService(this);
                this.mILocationServiceInterface = null;
                return createSuccess(this.mOutput);
            } catch (InterruptedException e2) {
                return createError("Unhandled exception while waiting for location result: " + e2.toString());
            }
        } catch (RemoteException e3) {
            return createError("Unhandled exception while trying to get location: " + e3.toString());
        }
    }

    public void onServiceConnected(@Nullable ComponentName componentName, @Nullable IBinder iBinder) {
        if (iBinder != null) {
            this.mILocationServiceInterface = ILocationServiceInterface.Stub.asInterface(iBinder);
        }
        CountDownLatch countDownLatch = this.mConnectionLatch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void onServiceDisconnected(@Nullable ComponentName componentName) {
        this.mILocationServiceInterface = null;
    }

    public static class Result {
        public final String error;
        public final String output;
        public final boolean success;

        private Result(boolean z, String str, String str2) {
            this.success = z;
            this.output = str;
            this.error = str2;
        }
    }

    private static Result createSuccess(String str) {
        return new Result(true, str, "");
    }

    private static Result createError(String str) {
        return new Result(false, "", str);
    }

    /* access modifiers changed from: private */
    public class LocationCallback extends ILocationCallback.Stub {
        private LocationCallback() {
        }

        @Override // com.oculus.aidl.ILocationCallback
        public void report(Location location) {
            if (BuildConstants.DEBUG) {
                BLog.d(LocationRequestHelper.TAG, "report: location = %s", location);
            }
            LocationRequestHelper.this.mOutput = String.valueOf(location);
            if (LocationRequestHelper.this.mResultLatch != null) {
                LocationRequestHelper.this.mResultLatch.countDown();
            }
        }
    }
}
