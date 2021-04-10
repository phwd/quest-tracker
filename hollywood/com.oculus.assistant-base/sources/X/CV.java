package X;

import android.app.ActivityThread;
import android.text.TextUtils;
import com.facebook.assistant.oacr.OacrConstants;

public final class CV {
    public static volatile CV A02;
    public final CU A00;
    public final String A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.A01;
        String str2 = ((CV) obj).A01;
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static CV A00() {
        CV cv = A02;
        if (cv == null) {
            ActivityThread activityThread = Ba.A00;
            if (activityThread == null) {
                activityThread = ActivityThread.currentActivityThread();
                Ba.A00 = activityThread;
            }
            cv = A01(activityThread.getProcessName());
            A02 = cv;
            if (TextUtils.isEmpty(cv.A01)) {
                String[] strArr = {null};
                CY.A00.A4i("/proc/self/cmdline", CY.A01, strArr, null, null);
                String str = strArr[0];
                if (TextUtils.isEmpty(str) || (cv = A01(str)) == null) {
                    return A02;
                }
                A02 = cv;
            }
        }
        return cv;
    }

    public static CV A01(String str) {
        String str2;
        CU cu;
        if (str == null) {
            return new CV(null, null);
        }
        String[] split = str.split(":");
        if (split.length > 1) {
            str2 = split[1];
            if (str2 == null) {
                throw new IllegalArgumentException("Invalid name");
            }
        } else {
            str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        if (OacrConstants.AUTO_SPEECH_DOMAIN.equals(str2)) {
            cu = CU.A01;
        } else {
            cu = new CU(str2);
        }
        return new CV(str, cu);
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

    public CV() {
        this(null, null);
    }

    public CV(String str, CU cu) {
        this.A01 = str;
        this.A00 = cu;
    }
}
