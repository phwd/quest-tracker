package X;

import android.app.ActivityThread;
import android.text.TextUtils;
import javax.annotation.Nullable;

/* renamed from: X.0Jl  reason: invalid class name */
public final class AnonymousClass0Jl {
    public static volatile AnonymousClass0Jl A02;
    @Nullable
    public final AnonymousClass0Jk A00;
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
        String str2 = ((AnonymousClass0Jl) obj).A01;
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static AnonymousClass0Jl A00() {
        AnonymousClass0Jl r0 = A02;
        if (r0 == null) {
            ActivityThread activityThread = AnonymousClass0H9.A00;
            if (activityThread == null) {
                activityThread = ActivityThread.currentActivityThread();
                AnonymousClass0H9.A00 = activityThread;
            }
            r0 = A01(activityThread.getProcessName());
            A02 = r0;
            if (TextUtils.isEmpty(r0.A01)) {
                String[] strArr = {null};
                AnonymousClass0Jp.A00.A8t("/proc/self/cmdline", AnonymousClass0Jp.A01, strArr, null, null);
                String str = strArr[0];
                if (TextUtils.isEmpty(str) || (r0 = A01(str)) == null) {
                    return A02;
                }
                A02 = r0;
            }
        }
        return r0;
    }

    public static AnonymousClass0Jl A01(@Nullable String str) {
        String str2;
        AnonymousClass0Jk r1;
        if (str == null) {
            return new AnonymousClass0Jl(null, null);
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
            r1 = AnonymousClass0Jk.A01;
        } else {
            r1 = new AnonymousClass0Jk(str2);
        }
        return new AnonymousClass0Jl(str, r1);
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

    public AnonymousClass0Jl() {
        this(null, null);
    }

    public AnonymousClass0Jl(@Nullable String str, @Nullable AnonymousClass0Jk r2) {
        this.A01 = str;
        this.A00 = r2;
    }
}
