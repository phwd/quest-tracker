package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.oculus.os.Version;
import java.lang.ref.WeakReference;

/* renamed from: YN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YN extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f9270a;

    public YN(ZN zn) {
        super(Looper.getMainLooper());
        this.f9270a = new WeakReference(zn);
    }

    public void a(int i) {
        ZN zn = (ZN) this.f9270a.get();
        if (zn != null) {
            switch (i) {
                case 1:
                    zn.j("Making app update available.");
                    zn.d = true;
                    zn.e = 10000;
                    return;
                case 2:
                    zn.j("User accepts update.");
                    if (zn.g || zn.h) {
                        zn.g = false;
                        zn.c = 1;
                        Integer num = 0;
                        if (num.equals(zn.i)) {
                            zn.g();
                            return;
                        }
                        return;
                    }
                    return;
                case 3:
                    zn.j("User rejects update.");
                    if (zn.g || zn.h) {
                        zn.g = false;
                        zn.h = false;
                        zn.i = null;
                        zn.c = 0;
                        return;
                    }
                    return;
                case 4:
                    zn.j("Triggering download.");
                    zn.h(5);
                    int i2 = zn.j;
                    if (i2 == 5) {
                        zn.i(6);
                        return;
                    } else if (i2 == 6) {
                        zn.i(7);
                        return;
                    } else {
                        zn.i(8);
                        return;
                    }
                case 5:
                    zn.j("Download has started.");
                    if (zn.c == 1) {
                        zn.c = 2;
                        Integer num2 = 0;
                        if (num2.equals(zn.i)) {
                            zn.g();
                            return;
                        }
                        return;
                    }
                    return;
                case 6:
                    zn.j("Triggering download failure.");
                    int i3 = zn.c;
                    if (i3 == 1 || i3 == 2) {
                        zn.c = 5;
                        Integer num3 = 0;
                        if (num3.equals(zn.i)) {
                            zn.g();
                        }
                        zn.i = null;
                        zn.h = false;
                        zn.c = 0;
                        return;
                    }
                    return;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    zn.j("Triggering cancellation of download.");
                    int i4 = zn.c;
                    if (i4 == 1 || i4 == 2) {
                        zn.c = 6;
                        Integer num4 = 0;
                        if (num4.equals(zn.i)) {
                            zn.g();
                        }
                        zn.i = null;
                        zn.h = false;
                        zn.c = 0;
                        return;
                    }
                    return;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    zn.j("Download completes.");
                    int i5 = zn.c;
                    if (i5 == 2 || i5 == 1) {
                        zn.c = 11;
                        Integer num5 = 0;
                        if (num5.equals(zn.i)) {
                            zn.g();
                            return;
                        }
                        Integer num6 = 1;
                        if (num6.equals(zn.i)) {
                            zn.a();
                            return;
                        }
                        return;
                    }
                    return;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    zn.j("Triggering install failure.");
                    if (zn.c == 3) {
                        zn.c = 5;
                        Integer num7 = 0;
                        if (num7.equals(zn.i)) {
                            zn.g();
                        }
                        zn.i = null;
                        zn.h = false;
                        zn.c = 0;
                        return;
                    }
                    return;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    zn.j("Triggering install completion.");
                    if (zn.c == 3) {
                        zn.c = 4;
                        zn.d = false;
                        zn.e = 0;
                        zn.f = -1;
                        zn.h = false;
                        Integer num8 = 0;
                        if (num8.equals(zn.i)) {
                            zn.g();
                        }
                        zn.i = null;
                        zn.c = 0;
                        return;
                    }
                    return;
                default:
                    zn.j("Unknown event.");
                    return;
            }
        }
    }

    public void handleMessage(Message message) {
        a(message.what);
    }
}
