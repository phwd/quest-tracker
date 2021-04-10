package X;

import android.app.ActivityThread;
import android.text.TextUtils;
import javax.annotation.Nullable;

/* renamed from: X.Kf  reason: case insensitive filesystem */
public final class C0066Kf {
    public static volatile C0066Kf A02;
    @Nullable
    public final C0065Ke A00;
    @Nullable
    public final String A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.A01;
        String str2 = ((C0066Kf) obj).A01;
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static C0066Kf A00() {
        C0066Kf kf = A02;
        if (kf == null) {
            ActivityThread activityThread = IV.A00;
            if (activityThread == null) {
                activityThread = ActivityThread.currentActivityThread();
                IV.A00 = activityThread;
            }
            kf = A01(activityThread.getProcessName());
            A02 = kf;
            if (TextUtils.isEmpty(kf.A01)) {
                String[] strArr = {null};
                C0070Kj.A00.A37("/proc/self/cmdline", C0070Kj.A01, strArr, null, null);
                String str = strArr[0];
                if (TextUtils.isEmpty(str) || (kf = A01(str)) == null) {
                    return A02;
                }
                A02 = kf;
            }
        }
        return kf;
    }

    public static C0066Kf A01(@Nullable String str) {
        String str2;
        C0065Ke ke;
        if (str == null) {
            return new C0066Kf(null, null);
        }
        String[] split = str.split(":");
        if (split.length > 1) {
            str2 = split[1];
            if (str2 == null) {
                throw new IllegalArgumentException("Invalid name");
            }
        } else {
            str2 = "";
        }
        if ("".equals(str2)) {
            ke = C0065Ke.A01;
        } else {
            ke = new C0065Ke(str2);
        }
        return new C0066Kf(str, ke);
    }

    public final int hashCode() {
        String str = this.A01;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public final String toString() {
        String str = this.A01;
        if (str == null) {
            return "<unknown>";
        }
        return str;
    }

    public C0066Kf() {
        this(null, null);
    }

    public C0066Kf(@Nullable String str, @Nullable C0065Ke ke) {
        this.A01 = str;
        this.A00 = ke;
    }
}
