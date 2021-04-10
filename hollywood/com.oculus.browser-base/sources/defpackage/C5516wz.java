package defpackage;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;

/* renamed from: wz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5516wz extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public static final HashSet f11580a = AbstractC0417Gv.d("es", "en", "pt", "ru", "fr", "it");
    public final boolean b = AbstractC5686xz.c(10);
    public final boolean c;
    public final boolean d;
    public final boolean e;

    public C5516wz(ContextualSearchContext contextualSearchContext) {
        boolean z;
        boolean contains = f11580a.contains(contextualSearchContext.c());
        this.d = contains;
        boolean z2 = false;
        if (contains) {
            String str = contextualSearchContext.j;
            if (TextUtils.isEmpty(str) || !j(str)) {
                z = false;
            } else {
                z = k(contextualSearchContext, str, contextualSearchContext.k, contextualSearchContext.o, contextualSearchContext.p) ? true : k(contextualSearchContext, contextualSearchContext.m, contextualSearchContext.n, str, contextualSearchContext.k);
            }
            if (z) {
                z2 = true;
            }
        }
        this.e = z2;
        this.c = !z2;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.b && this.c;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        C4017oA oAVar = (C4017oA) hz;
        oAVar.b(16, Boolean.valueOf(this.e));
        oAVar.b(21, Boolean.valueOf(this.d));
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            boolean z3 = !this.c;
            Pattern pattern = AA.f7657a;
            if (z3) {
                AbstractC3364kK0.g("Search.ContextualSearchEntitySeen", !z ? 1 : 0, 2);
            }
        }
    }

    public final boolean j(String str) {
        return !TextUtils.isEmpty(str) && str.length() > 1 && Character.isUpperCase(Character.valueOf(str.charAt(0)).charValue()) && !str.toUpperCase(Locale.getDefault()).equals(str);
    }

    public final boolean k(ContextualSearchContext contextualSearchContext, String str, int i, String str2, int i2) {
        boolean z;
        if (!j(str) || !j(str2)) {
            return false;
        }
        int i3 = i - 1;
        while (i3 > 0 && l(contextualSearchContext, i3)) {
            i3--;
        }
        if (i3 > 0) {
            char charAt = contextualSearchContext.c.charAt(i3);
            if (!(charAt == '.' || charAt == '?' || charAt == '!' || charAt == ':')) {
                z = true;
                if (!z && str.length() + i + 1 == i2) {
                    return l(contextualSearchContext, i2 - 1);
                }
                return false;
            }
        }
        z = false;
        if (!z) {
            return false;
        }
        return l(contextualSearchContext, i2 - 1);
    }

    public final boolean l(ContextualSearchContext contextualSearchContext, int i) {
        if (i == -1) {
            return false;
        }
        return Character.isWhitespace(Character.valueOf(contextualSearchContext.c.charAt(i)).charValue());
    }
}
