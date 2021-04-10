package X;

import android.app.ActivityThread;
import android.text.TextUtils;
import javax.annotation.Nullable;

/* renamed from: X.0Kw  reason: invalid class name and case insensitive filesystem */
public final class C01160Kw {
    public static volatile C01160Kw A02;
    @Nullable
    public final AnonymousClass0Kv A00;
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
        String str2 = ((C01160Kw) obj).A01;
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static C01160Kw A00() {
        C01160Kw r0 = A02;
        if (r0 == null) {
            ActivityThread activityThread = AnonymousClass0J1.A00;
            if (activityThread == null) {
                activityThread = ActivityThread.currentActivityThread();
                AnonymousClass0J1.A00 = activityThread;
            }
            r0 = A01(activityThread.getProcessName());
            A02 = r0;
            if (TextUtils.isEmpty(r0.A01)) {
                String[] strArr = {null};
                AnonymousClass0L0.A00.A7x("/proc/self/cmdline", AnonymousClass0L0.A01, strArr, null, null);
                String str = strArr[0];
                if (TextUtils.isEmpty(str) || (r0 = A01(str)) == null) {
                    return A02;
                }
                A02 = r0;
            }
        }
        return r0;
    }

    public static C01160Kw A01(@Nullable String str) {
        String str2;
        AnonymousClass0Kv r1;
        if (str == null) {
            return new C01160Kw(null, null);
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
            r1 = AnonymousClass0Kv.A01;
        } else {
            r1 = new AnonymousClass0Kv(str2);
        }
        return new C01160Kw(str, r1);
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

    public C01160Kw() {
        this(null, null);
    }

    public C01160Kw(@Nullable String str, @Nullable AnonymousClass0Kv r2) {
        this.A01 = str;
        this.A00 = r2;
    }
}
