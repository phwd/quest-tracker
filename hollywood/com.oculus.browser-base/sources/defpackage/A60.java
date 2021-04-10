package defpackage;

import J.N;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/* renamed from: A60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A60 implements AbstractC3087il0 {
    public C2746gl0 F;
    public HashSet G;
    public HashSet H;

    public final void a(int i) {
        AbstractC3364kK0.g("Translate.ExplicitLanguageAsk.Event", i, 2);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 1) {
            this.F.b(uh0, 2);
            return;
        }
        TreeSet treeSet = new TreeSet(this.G);
        treeSet.removeAll(this.H);
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            N.Me60Lv4_(str, true);
            N.MC39_Sil(str, true);
        }
        HashSet hashSet = new HashSet(this.H);
        hashSet.removeAll(this.G);
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            N.Me60Lv4_(str2, false);
            N.MC39_Sil(str2, false);
        }
        this.F.b(uh0, 1);
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i == 1) {
            a(1);
        } else {
            a(2);
        }
    }
}
