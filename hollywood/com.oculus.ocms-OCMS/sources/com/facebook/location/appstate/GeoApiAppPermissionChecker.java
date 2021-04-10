package com.facebook.location.appstate;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.secure.trustedapp.AllFamilyPackageNames;
import java.util.Set;
import java.util.TreeSet;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class GeoApiAppPermissionChecker {
    private static final String[] LOCATION_PERMISSIONS = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private static final Set<String> sBgBlockedModules = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    @Nullable
    private static GeoApiAppPermissionChecker sInstance;
    protected final String TAG = GeoApiAppPermissionChecker.class.getSimpleName();
    private final boolean backgroundAllowed;
    private final Context mContext;

    static {
        sBgBlockedModules.add(AllFamilyPackageNames.INSTAGRAM);
    }

    private GeoApiAppPermissionChecker(Context context) {
        this.backgroundAllowed = !sBgBlockedModules.contains(context.getApplicationContext().getPackageName());
        this.mContext = context.getApplicationContext();
    }

    public static synchronized GeoApiAppPermissionChecker getInstance(Context context) {
        GeoApiAppPermissionChecker geoApiAppPermissionChecker;
        synchronized (GeoApiAppPermissionChecker.class) {
            if (sInstance == null) {
                sInstance = new GeoApiAppPermissionChecker(context);
            }
            geoApiAppPermissionChecker = sInstance;
        }
        return geoApiAppPermissionChecker;
    }

    public boolean isBackgroundAllowed() {
        return this.backgroundAllowed;
    }

    public boolean isTargetAndroidQorGreater() {
        return getTargetSDKVersion() >= 29;
    }

    public boolean hasAnyLocationPermission() {
        return hasAny(LOCATION_PERMISSIONS);
    }

    public boolean hasFineLocationPermission() {
        return hasPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean hasAny(String[] strArr) {
        for (String str : strArr) {
            if (hasPermission(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermission(String str) {
        try {
            return this.mContext.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable th) {
            BLog.e(this.TAG, "Runtime exception in accessing OS permissions [%s]", th);
            return false;
        }
    }

    private int getTargetSDKVersion() {
        return this.mContext.getApplicationInfo().targetSdkVersion;
    }
}
