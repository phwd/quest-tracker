package com.facebook.analytics2.logger;

import X.AnonymousClass7u;
import X.C0139Dd;
import X.f3;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.proxygen.HTTPTransportCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class GooglePlayServicesFactory {
    public static final String TAG = "GooglePlayServicesFactory";

    public static boolean canLoadUploaderService() {
        try {
            Class.forName("com.facebook.analytics2.logger.GooglePlayUploadService");
            return true;
        } catch (ClassNotFoundException e) {
            if (!C0139Dd.A01.A3Y(4)) {
                return false;
            }
            C0139Dd.A01.A3D(TAG, "Can't class load GooglePlayUploadService", e);
            return false;
        }
    }

    public static boolean canUseGooglePlayServices(Context context) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), HTTPTransportCallback.BODY_BYTES_RECEIVED);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                return false;
            }
            return bundle.containsKey("com.google.android.gms.version");
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static AnonymousClass7u createUploadSchedulerImpl(Context context) {
        String str;
        if (!canLoadUploaderService()) {
            str = "Not using Google Play services: failed to load GooglePlayUploadService.";
        } else if (!canUseGooglePlayServices(context)) {
            str = "Not using Google Play services: missing required manifest metadata.";
        } else {
            int isGooglePlayServicesAvailable = GoogleApiAvailability.A00.isGooglePlayServicesAvailable(context);
            if (isGooglePlayServicesAvailable != 0) {
                C0139Dd.A0G(TAG, "Failed to connect to Google Play services: %s", ConnectionResult.A00(isGooglePlayServicesAvailable));
                return null;
            }
            C0139Dd.A09(TAG, "GMS found, scheduling with GcmNetworkManager");
            GooglePlayUploadService.A01(context);
            return new f3(context);
        }
        C0139Dd.A0B(TAG, str);
        return null;
    }
}
