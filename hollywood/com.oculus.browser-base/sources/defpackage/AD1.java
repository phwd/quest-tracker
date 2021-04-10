package defpackage;

import java.util.logging.Level;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: AD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AD1 {
    public static String a(@NullableDecl String str, @NullableDecl Object... objArr) {
        int indexOf;
        String str2;
        String valueOf = String.valueOf(str);
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (obj == null) {
                str2 = "null";
            } else {
                try {
                    str2 = obj.toString();
                } catch (Exception unused) {
                    String name = obj.getClass().getName();
                    String hexString = Integer.toHexString(System.identityHashCode(obj));
                    StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + name.length() + 1);
                    sb.append(name);
                    sb.append('@');
                    sb.append(hexString);
                    String sb2 = sb.toString();
                    Level level = Level.WARNING;
                    String valueOf2 = String.valueOf(sb2);
                    if (valueOf2.length() != 0) {
                        "Exception during lenientFormat for ".concat(valueOf2);
                        throw null;
                    }
                    new String("Exception during lenientFormat for ");
                    throw null;
                }
            }
            objArr[i2] = str2;
        }
        StringBuilder sb3 = new StringBuilder((objArr.length * 16) + valueOf.length());
        int i3 = 0;
        while (i < objArr.length && (indexOf = valueOf.indexOf("%s", i3)) != -1) {
            sb3.append((CharSequence) valueOf, i3, indexOf);
            sb3.append(objArr[i]);
            i3 = indexOf + 2;
            i++;
        }
        sb3.append((CharSequence) valueOf, i3, valueOf.length());
        if (i < objArr.length) {
            sb3.append(" [");
            sb3.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb3.append(", ");
                sb3.append(objArr[i4]);
            }
            sb3.append(']');
        }
        return sb3.toString();
    }
}
