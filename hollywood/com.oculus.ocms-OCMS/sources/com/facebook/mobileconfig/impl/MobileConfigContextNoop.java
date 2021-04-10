package com.facebook.mobileconfig.impl;

import androidx.annotation.GuardedBy;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.mobileconfig.factory.ExposureType;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ThreadSafe
public class MobileConfigContextNoop extends MobileConfigContextBase {
    @GuardedBy("sLock")
    private static MobileConfigContextNoop sInstance;
    private static final Object sLock = new Object();

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean getBooleanFromStorage(long j, boolean z, boolean z2) {
        return z;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public double getDoubleFromStorage(long j, double d, boolean z) {
        return d;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    @Nullable
    public String getLoggingId(long j) {
        return null;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public int getLoggingMode(long j) {
        return 0;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public int getLoggingModeWithoutCaptureStack(long j) {
        return 0;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public long getLongFromStorage(long j, long j2, boolean z) {
        return j2;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public String getStringFromStorage(long j, String str, boolean z) {
        return str;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean hasServerValueFromStorage(long j) {
        return false;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean hasValueFromStorage(long j) {
        return false;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean isValid() {
        return true;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public void logExposure(long j, ExposureType exposureType) {
    }

    public static synchronized MobileConfigContextNoop getInstance() {
        MobileConfigContextNoop mobileConfigContextNoop;
        synchronized (MobileConfigContextNoop.class) {
            if (sInstance != null) {
                return sInstance;
            }
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new MobileConfigContextNoop();
                }
                mobileConfigContextNoop = sInstance;
            }
            return mobileConfigContextNoop;
        }
    }

    private MobileConfigContextNoop() {
        super(null);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public Map<Integer, Integer> getEmergencyPushInfo() {
        return new HashMap();
    }
}
