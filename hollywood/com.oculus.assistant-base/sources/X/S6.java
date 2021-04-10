package X;

import android.os.Build;
import android.util.Log;

public final class S6 {
    public static Boolean A00;

    public static boolean A00() {
        if (Build.VERSION.SDK_INT >= 24) {
            return true;
        }
        return false;
    }

    public static boolean A01() {
        if (Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        return false;
    }

    public static boolean A02() {
        Boolean bool;
        int i = Build.VERSION.SDK_INT;
        boolean z = false;
        if (i >= 29) {
            z = true;
        }
        boolean z2 = false;
        if (z) {
            if (i >= 30 && Build.VERSION.CODENAME.equals("REL")) {
                return true;
            }
            String str = Build.VERSION.CODENAME;
            if (str.length() == 1 && str.charAt(0) >= 'R' && str.charAt(0) <= 'Z') {
                Boolean bool2 = A00;
                if (bool2 == null) {
                    try {
                        if ("google".equals(Build.BRAND)) {
                            String str2 = Build.ID;
                            if (!str2.startsWith("RPP1") && !str2.startsWith("RPP2") && Integer.parseInt(Build.VERSION.INCREMENTAL) >= 6301457) {
                                z2 = true;
                            }
                        }
                        bool = Boolean.valueOf(z2);
                        A00 = bool;
                    } catch (NumberFormatException unused) {
                        bool = true;
                        A00 = bool;
                    }
                    if (!bool.booleanValue()) {
                        Log.w("PlatformVersion", "Build version must be at least 6301457 to support R in gmscore");
                    }
                    bool2 = A00;
                }
                return bool2.booleanValue();
            }
        }
        return false;
    }
}
