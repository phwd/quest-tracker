package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;

/* renamed from: yR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5764yR0 {
    public static List a() {
        String[][] MvmUzLgj = N.MvmUzLgj();
        ArrayList arrayList = new ArrayList(MvmUzLgj.length);
        for (String[] strArr : MvmUzLgj) {
            arrayList.add(new C5594xR0(strArr[0], strArr[1], strArr[2]));
        }
        return arrayList;
    }
}
