package defpackage;

import android.text.TextUtils;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;

/* renamed from: Ee1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0258Ee1 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f7970a = AbstractC5686xz.c(7);
    public final boolean b;

    public C0258Ee1(ContextualSearchContext contextualSearchContext) {
        String str = contextualSearchContext.j;
        int i = contextualSearchContext.l;
        boolean z = false;
        if (!TextUtils.isEmpty(str) && i != -1) {
            boolean z2 = ((double) i) / ((double) str.length()) < 0.25d;
            boolean z3 = ((double) (str.length() - i)) / ((double) str.length()) < 0.25d;
            if (str.length() >= 4 && (z2 || z3)) {
                z = true;
            }
        }
        this.b = z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.f7970a && this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean b() {
        return this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(15, Boolean.valueOf(this.b));
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            boolean z3 = !this.b;
            Pattern pattern = AA.f7657a;
            if (z3) {
                AbstractC3364kK0.g("Search.ContextualSearchTapOnWordMiddleSeen", !z ? 1 : 0, 2);
            }
        }
    }

    @Override // defpackage.AbstractC5856yz
    public boolean h() {
        return true;
    }
}
