package defpackage;

import J.N;
import java.util.ArrayList;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: aH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1638aH0 extends T1 {
    public static C1638aH0 F;
    public final ArrayList G = new ArrayList();
    public final ArrayList H = new ArrayList();
    public final ArrayList I = new ArrayList();

    @Override // defpackage.U1, defpackage.T1
    public void E() {
        this.G.clear();
        this.H.clear();
        this.I.clear();
    }

    @Override // defpackage.U1
    public void v() {
        for (int size = this.H.size(); size > 0; size--) {
            N.MSj9Fi5N((Profile) this.G.get(0), (String) this.H.get(0), ((Integer) this.I.get(0)).intValue());
            this.G.remove(0);
            this.H.remove(0);
            this.I.remove(0);
        }
    }
}
