package defpackage;

/* renamed from: oX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4073oX {
    public static String a(int i, C4300pq1 pq1) {
        StringBuilder i2 = AbstractC2531fV.i("GoogleUpdate.Result.");
        int i3 = 1;
        String str = "Unknown";
        i2.append(i != 0 ? i != 1 ? str : "TimeWindow" : "Session");
        String sb = i2.toString();
        if (pq1 == null) {
            return sb;
        }
        StringBuilder j = AbstractC2531fV.j(sb, ".");
        int g = AbstractC5580xK0.g(pq1.j);
        if (g == 0) {
            g = 1;
        }
        j.append(g == 2 ? "Intent" : g == 3 ? "Inline" : str);
        StringBuilder j2 = AbstractC2531fV.j(j.toString(), ".");
        int f = AbstractC5580xK0.f(pq1.k);
        if (f != 0) {
            i3 = f;
        }
        if (i3 == 2) {
            str = "Menu";
        } else if (i3 == 3) {
            str = "Infobar";
        } else if (i3 == 4) {
            str = "Notification";
        }
        j2.append(str);
        return j2.toString();
    }
}
