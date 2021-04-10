package defpackage;

import java.util.Comparator;
import java.util.Locale;

/* renamed from: o4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3999o4 implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        String str;
        String str2;
        C2824hB hBVar = (C2824hB) obj;
        C2824hB hBVar2 = (C2824hB) obj2;
        if (hBVar.e) {
            str = hBVar.f.toLowerCase(Locale.ENGLISH);
        } else {
            str = AbstractC5154ur1.b(hBVar.d, false);
        }
        if (hBVar2.e) {
            str2 = hBVar2.f.toLowerCase(Locale.ENGLISH);
        } else {
            str2 = AbstractC5154ur1.b(hBVar2.d, false);
        }
        return str.compareTo(str2);
    }
}
