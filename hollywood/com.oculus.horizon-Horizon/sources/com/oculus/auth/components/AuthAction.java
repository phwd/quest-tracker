package com.oculus.auth.components;

import X.C02790bO;
import X.C02800bY;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.oculus.time.Clock;

public final class AuthAction {
    public static final String UNKNOWN = "unknown";
    public final String mCallingPackage;
    public boolean mIsQuiet;
    public final String mName;
    public final long mStartTime;

    @VisibleForTesting
    public static AuthAction newTestInstance(String str, String str2) {
        return new AuthAction(str, str2, 0);
    }

    public static AuthAction unknown() {
        return new AuthAction("unknown", "unknown", 0);
    }

    public AuthAction(String str, String str2, long j) {
        this.mName = str;
        this.mCallingPackage = str2;
        this.mStartTime = j;
    }

    public static String extractCallingPackage(Context context, Intent intent) {
        String A01;
        C02790bO A00 = C02800bY.A00(context, intent);
        if (A00 == null || (A01 = A00.A01()) == null) {
            return "unknown";
        }
        return A01;
    }

    public static String extractName(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return "unknown";
        }
        return action;
    }

    public String getCallingPackage() {
        return this.mCallingPackage;
    }

    public String getName() {
        return this.mName;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public boolean isQuiet() {
        return this.mIsQuiet;
    }

    public void setQuiet(boolean z) {
        this.mIsQuiet = z;
    }

    public static AuthAction newInstance(Context context, Intent intent, Clock clock) {
        return new AuthAction(extractName(intent), extractCallingPackage(context, intent), System.currentTimeMillis());
    }

    public static AuthAction newInstance(Intent intent, String str, Clock clock) {
        return new AuthAction(extractName(intent), str, System.currentTimeMillis());
    }

    public static AuthAction newInstance(String str, Clock clock) {
        return new AuthAction(str, "unknown", System.currentTimeMillis());
    }

    public static AuthAction newInstance(String str, String str2, Clock clock) {
        return new AuthAction(str, str2, System.currentTimeMillis());
    }
}
