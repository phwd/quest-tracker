package defpackage;

import android.text.SpannableString;
import java.util.Arrays;

/* renamed from: FY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class FY0 {
    public static SpannableString a(String str, EY0... ey0Arr) {
        Object[] objArr;
        c(str, ey0Arr);
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        for (EY0 ey0 : ey0Arr) {
            d(ey0, str, i);
            sb.append((CharSequence) str, i, ey0.I);
            int length = ey0.F.length() + ey0.I;
            ey0.I = sb.length();
            sb.append((CharSequence) str, length, ey0.f7968J);
            i = ey0.f7968J + ey0.G.length();
            ey0.f7968J = sb.length();
        }
        sb.append((CharSequence) str, i, str.length());
        SpannableString spannableString = new SpannableString(sb);
        for (EY0 ey02 : ey0Arr) {
            if (!(ey02.I == -1 || (objArr = ey02.H) == null || objArr.length == 0)) {
                for (Object obj : objArr) {
                    if (obj != null) {
                        spannableString.setSpan(obj, ey02.I, ey02.f7968J, 0);
                    }
                }
            }
        }
        return spannableString;
    }

    public static String b(String str, EY0... ey0Arr) {
        c(str, ey0Arr);
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        for (EY0 ey0 : ey0Arr) {
            d(ey0, str, i);
            sb.append((CharSequence) str, i, ey0.I);
            i = ey0.f7968J + ey0.G.length();
        }
        sb.append((CharSequence) str, i, str.length());
        return sb.toString();
    }

    public static void c(String str, EY0... ey0Arr) {
        for (EY0 ey0 : ey0Arr) {
            int indexOf = str.indexOf(ey0.F);
            ey0.I = indexOf;
            ey0.f7968J = str.indexOf(ey0.G, ey0.F.length() + indexOf);
        }
        Arrays.sort(ey0Arr);
    }

    public static void d(EY0 ey0, String str, int i) {
        int i2 = ey0.I;
        if (i2 == -1 || ey0.f7968J == -1 || i2 < i) {
            ey0.I = -1;
            throw new IllegalArgumentException(String.format("Input string is missing tags %s%s: %s", ey0.F, ey0.G, str));
        }
    }
}
