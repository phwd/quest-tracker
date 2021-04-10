package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.browser.ShortcutHelper;

/* renamed from: Kw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Kw1 {
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01db A[SYNTHETIC, Splitter:B:104:0x01db] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x026f A[SYNTHETIC, Splitter:B:138:0x026f] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02d8  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0360  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0363  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0379  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0380  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0305 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.AbstractC3767mk a(android.content.Intent r51, java.lang.String r52, java.lang.String r53, int r54, boolean r55, boolean r56, defpackage.C5938zT0 r57, java.lang.String r58) {
        /*
        // Method dump skipped, instructions count: 1106
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Kw1.a(android.content.Intent, java.lang.String, java.lang.String, int, boolean, boolean, zT0, java.lang.String):mk");
    }

    public static AbstractC3767mk b(Intent intent, String str, String str2, Zx1 zx1, Zx1 zx12, String str3, String str4, int i, int i2, int i3, long j, long j2, int i4, boolean z, boolean z2, String str5, int i5, String str6, String str7, int i6, Map map, C1758ax1 ax1, boolean z3, boolean z4, C5938zT0 zt0, List list, int i7) {
        Integer num = null;
        if (str7 == null || str5 == null) {
            AbstractC1220Ua0.a("WebApkInfo", "Incomplete data provided: " + str7 + ", " + str5, new Object[0]);
            return null;
        }
        String str8 = TextUtils.isEmpty(str) ? str7 : str;
        String d = TextUtils.isEmpty(str2) ? ShortcutHelper.d(str7) : str2;
        String a2 = AbstractC2103cy1.a(str5);
        if (AbstractC2103cy1.b(j2)) {
            num = Integer.valueOf((int) j2);
        }
        Yx1 yx1 = new Yx1(a2, str8, d, zx1, str3, str4, i, i2, i3, num, i4, false, z, z3);
        Ew1 ew1 = new Ew1(str5, zx12, z2, i5, str6, str7, i6, map, ax1, z4, list, i7);
        boolean b = AbstractC2103cy1.b(j);
        return new C1932by1(intent, b ? (int) j : -1, b, zt0, yx1, ew1);
    }
}
