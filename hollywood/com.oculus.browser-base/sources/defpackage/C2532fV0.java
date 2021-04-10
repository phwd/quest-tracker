package defpackage;

import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;

/* renamed from: fV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2532fV0 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f9924a = AbstractC5686xz.c(12);
    public final boolean b;
    public final int c;

    public C2532fV0(ContextualSearchContext contextualSearchContext, int i) {
        String str = contextualSearchContext.j;
        boolean z = true;
        int max = (str == null || i == 0) ? 0 : Math.max(1, Math.min(10, (int) ((((float) str.length()) / ((float) i)) * 10.0f)));
        this.c = max;
        this.b = max < 3 ? false : z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.f9924a && this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean b() {
        return this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(23, Integer.valueOf(this.c));
    }

    @Override // defpackage.AbstractC5856yz
    public boolean h() {
        return true;
    }
}
