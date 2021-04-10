package defpackage;

import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: xb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5616xb1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0249Eb1 f11619a;
    public final int b;

    public C5616xb1(AbstractC0249Eb1 eb1, int i) {
        this.f11619a = eb1;
        this.b = i;
    }

    public void a(List list, AbstractC0124Ca1 ca1) {
        int i = this.b;
        if (i == 1) {
            int i2 = -1;
            for (int i3 = 0; i3 < list.size(); i3++) {
                i2 = Math.max(AbstractC1160Ta1.e(((AbstractC0246Ea1) ca1).i(), ((Tab) list.get(i3)).getId()), i2);
            }
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
            ((I71) ea1.c.d()).Y(list, ea1.i().getTabAt(i2), false, true);
            ((C0676Lb1) this.f11619a).b();
            AbstractC3535lK0.a("TabMultiSelect.Done");
            AbstractC3535lK0.a("TabGroup.Created.TabMultiSelect");
        } else if (i == 2) {
            I71 i71 = (I71) ((AbstractC0246Ea1) ca1).c.d();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                i71.Z(((Tab) it.next()).getId());
            }
            ((C0676Lb1) this.f11619a).b();
            AbstractC3535lK0.a("TabGridDialog.RemoveFromGroup.TabMultiSelect");
        } else if (i == 3) {
            ((AbstractC0246Ea1) ca1).i().l(list, true);
            ((C0676Lb1) this.f11619a).b();
        }
    }
}
