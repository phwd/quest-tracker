package X;

import android.app.ActivityThread;
import android.text.TextUtils;
import javax.annotation.Nullable;

public final class KO {
    public static volatile KO A02;
    @Nullable
    public final KN A00;
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
        String str2 = ((KO) obj).A01;
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static KO A00() {
        KO ko = A02;
        if (ko == null) {
            ActivityThread activityThread = IA.A00;
            if (activityThread == null) {
                activityThread = ActivityThread.currentActivityThread();
                IA.A00 = activityThread;
            }
            ko = A01(activityThread.getProcessName());
            A02 = ko;
            if (TextUtils.isEmpty(ko.A01)) {
                String[] strArr = {null};
                KS.A00.A4Y("/proc/self/cmdline", KS.A01, strArr, null, null);
                String str = strArr[0];
                if (TextUtils.isEmpty(str) || (ko = A01(str)) == null) {
                    return A02;
                }
                A02 = ko;
            }
        }
        return ko;
    }

    public static KO A01(@Nullable String str) {
        String str2;
        KN kn;
        if (str == null) {
            return new KO(null, null);
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
            kn = KN.A01;
        } else {
            kn = new KN(str2);
        }
        return new KO(str, kn);
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

    public KO() {
        this(null, null);
    }

    public KO(@Nullable String str, @Nullable KN kn) {
        this.A01 = str;
        this.A00 = kn;
    }
}
