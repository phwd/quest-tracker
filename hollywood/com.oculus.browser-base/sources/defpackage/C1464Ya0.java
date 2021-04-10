package defpackage;

import android.util.Log;
import java.util.Locale;

/* renamed from: Ya0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1464Ya0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9281a;
    public final String b;

    public C1464Ya0(String str, String... strArr) {
        String str2;
        if (strArr.length == 0) {
            str2 = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (String str3 : strArr) {
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append(str3);
            }
            sb.append(']');
            sb.append(' ');
            str2 = sb.toString();
        }
        this.b = str2;
        this.f9281a = str;
        new OV(str);
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.f9281a, i)) {
            i++;
        }
    }

    public void a(String str, Object... objArr) {
        String str2 = this.f9281a;
        if (objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        Log.w(str2, this.b.concat(str));
    }
}
