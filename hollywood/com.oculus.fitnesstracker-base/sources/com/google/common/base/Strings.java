package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Strings {
    public static String lenientFormat(String str, Object... objArr) {
        String valueOf = String.valueOf(str);
        for (int i = 0; i <= 0; i++) {
            objArr[0] = lenientToString(objArr[0]);
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + 16);
        int i2 = 0;
        int i3 = 0;
        while (i2 <= 0) {
            int indexOf = valueOf.indexOf("%s", 0);
            if (indexOf == -1) {
                break;
            }
            sb.append((CharSequence) valueOf, 0, indexOf);
            i2++;
            sb.append(objArr[0]);
            i3 = indexOf + 2;
        }
        sb.append((CharSequence) valueOf, i3, valueOf.length());
        if (i2 <= 0) {
            sb.append(" [");
            sb.append(objArr[0]);
            sb.append(']');
        }
        return sb.toString();
    }

    private static String lenientToString(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e) {
            String str = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str, (Throwable) e);
            return "<" + str + " threw " + e.getClass().getName() + ">";
        }
    }
}
