package defpackage;

import android.os.SystemClock;
import java.util.Objects;

/* renamed from: Px1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Px1 {

    /* renamed from: a  reason: collision with root package name */
    public static Px1 f8724a;
    public final Nx1 b;
    public final Ox1 c;
    public long d = -1;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;

    public Px1(Nx1 nx1, Ox1 ox1) {
        this.b = nx1;
        this.c = ox1;
    }

    public static Px1 a() {
        if (f8724a == null) {
            f8724a = new Px1(new Nx1(), new Ox1());
        }
        return f8724a;
    }

    public final void b(String str) {
        String str2 = this.e ? "ActionButton." : "Body.";
        Objects.requireNonNull(this.c);
        AbstractC3535lK0.a("Notifications.WebPlatform." + str2 + str);
    }

    public final void c(String str) {
        String str2 = this.e ? "ActionButton." : "Body.";
        Objects.requireNonNull(this.b);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Objects.requireNonNull(this.c);
        AbstractC3364kK0.k(AbstractC2531fV.g("Notifications.WebPlatform.", str2, str), elapsedRealtime - this.d);
    }

    public final boolean d() {
        if (this.d == -1) {
            return true;
        }
        Objects.requireNonNull(this.b);
        if (SystemClock.elapsedRealtime() - this.d > 5000) {
            return true;
        }
        return false;
    }
}
