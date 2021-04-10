package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Process;

/* renamed from: kV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3385kV {

    /* renamed from: a  reason: collision with root package name */
    public static C3214jV f10283a;

    public static Location a(Location location, Location location2) {
        if (location2 == null) {
            return location;
        }
        if (location == null) {
            return location2;
        }
        return location.getTime() > location2.getTime() ? location : location2;
    }

    public static long b(Location location) {
        long currentTimeMillis = System.currentTimeMillis() - location.getTime();
        if (currentTimeMillis >= 0) {
            return currentTimeMillis;
        }
        return Long.MAX_VALUE;
    }

    public static boolean c(Context context, String str) {
        return AbstractC3153j7.a(context, str, Process.myPid(), Process.myUid()) == 0;
    }
}
