package com.google.common.base;

import X.AnonymousClass08;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Strings {
    public static String lenientFormat(String str, Object... objArr) {
        int length;
        String str2;
        String valueOf = String.valueOf(str);
        int i = 0;
        int i2 = 0;
        while (true) {
            length = objArr.length;
            if (i2 >= length) {
                break;
            }
            Object obj = objArr[i2];
            try {
                str2 = String.valueOf(obj);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append(obj.getClass().getName());
                sb.append('@');
                sb.append(Integer.toHexString(System.identityHashCode(obj)));
                String obj2 = sb.toString();
                Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, AnonymousClass08.A04("Exception during lenientFormat for ", obj2), (Throwable) e);
                str2 = AnonymousClass08.A07("<", obj2, " threw ", e.getClass().getName(), ">");
            }
            objArr[i2] = str2;
            i2++;
        }
        int length2 = valueOf.length();
        StringBuilder sb2 = new StringBuilder((length << 4) + length2);
        int i3 = 0;
        while (i < length) {
            int indexOf = valueOf.indexOf("%s", i3);
            if (indexOf == -1) {
                break;
            }
            sb2.append((CharSequence) valueOf, i3, indexOf);
            sb2.append(objArr[i]);
            i3 = indexOf + 2;
            i++;
        }
        sb2.append((CharSequence) valueOf, i3, length2);
        if (i < length) {
            sb2.append(" [");
            sb2.append(objArr[i]);
            for (int i4 = i + 1; i4 < length; i4++) {
                sb2.append(", ");
                sb2.append(objArr[i4]);
            }
            sb2.append(']');
        }
        return sb2.toString();
    }
}
