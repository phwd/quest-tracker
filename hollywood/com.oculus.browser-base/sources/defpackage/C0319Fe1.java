package defpackage;

import android.text.TextUtils;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;

/* renamed from: Fe1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0319Fe1 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8028a = AbstractC5686xz.c(8);
    public final boolean b = AbstractC5686xz.c(9);
    public final boolean c;
    public final boolean d;
    public final String e;

    public C0319Fe1(ContextualSearchContext contextualSearchContext) {
        String str = contextualSearchContext.j;
        this.e = str;
        boolean z = true;
        this.c = !TextUtils.isEmpty(str) && str.length() <= 3;
        this.d = (TextUtils.isEmpty(str) || str.length() < 10) ? false : z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return (this.f8028a && this.c) || (this.b && !this.d);
    }

    @Override // defpackage.AbstractC5856yz
    public boolean b() {
        return this.c;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        C4017oA oAVar = (C4017oA) hz;
        oAVar.b(13, Boolean.valueOf(this.c));
        oAVar.b(14, Boolean.valueOf(this.d));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            boolean z3 = this.c;
            Pattern pattern = AA.f7657a;
            if (z3) {
                AbstractC3364kK0.g("Search.ContextualSearchTapShortWordSeen", !z, 2);
            }
            if (this.d) {
                AbstractC3364kK0.g("Search.ContextualSearchTapLongWordSeen", !z ? 1 : 0, 2);
            }
        }
    }

    @Override // defpackage.AbstractC5856yz
    public boolean h() {
        return true;
    }
}
