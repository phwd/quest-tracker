package defpackage;

import android.net.TrafficStats;
import java.lang.reflect.Method;

/* renamed from: ah1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1701ah1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Method f9443a;
    public static final Method b;

    static {
        try {
            f9443a = TrafficStats.class.getMethod("setThreadStatsUid", Integer.TYPE);
            b = TrafficStats.class.getMethod("clearThreadStatsUid", new Class[0]);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("Unable to get TrafficStats methods", e);
        }
    }
}
