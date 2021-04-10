package com.google.common.base;

import X.AnonymousClass06;
import com.google.common.annotations.GwtCompatible;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Strings {
    public static String lenientFormat(@NullableDecl String str, @NullableDecl Object... objArr) {
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
                String A00 = AnonymousClass06.A00(obj.getClass().getName(), '@', Integer.toHexString(System.identityHashCode(obj)));
                Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, AnonymousClass06.A03("Exception during lenientFormat for ", A00), (Throwable) e);
                str2 = AnonymousClass06.A06("<", A00, " threw ", e.getClass().getName(), ">");
            }
            objArr[i2] = str2;
            i2++;
        }
        int length2 = valueOf.length();
        StringBuilder sb = new StringBuilder((length << 4) + length2);
        int i3 = 0;
        while (i < length) {
            int indexOf = valueOf.indexOf("%s", i3);
            if (indexOf == -1) {
                break;
            }
            sb.append((CharSequence) valueOf, i3, indexOf);
            sb.append(objArr[i]);
            i3 = indexOf + 2;
            i++;
        }
        sb.append((CharSequence) valueOf, i3, length2);
        if (i < length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i4 = i + 1; i4 < length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
