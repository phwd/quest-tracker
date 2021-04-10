package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;

/* renamed from: Kp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0650Kp0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8391a = new Object();
    public static Set b = new HashSet();
    public static final Object c = new Object();
    public final Context d;
    public final NotificationManager e;

    public C0650Kp0(Context context) {
        this.d = context;
        this.e = (NotificationManager) context.getSystemService("notification");
    }

    public boolean a() {
        return this.e.areNotificationsEnabled();
    }
}
