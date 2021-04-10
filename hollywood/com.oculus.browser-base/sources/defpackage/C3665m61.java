package defpackage;

import android.text.TextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationEntry;

/* renamed from: m61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3665m61 implements Comparable {
    public final String F;
    public final String G;
    public final long H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final String f10398J;
    public final String K;
    public final boolean L;

    public C3665m61(int i, String str, String str2, String str3, String str4, long j, String str5, boolean z) {
        this.I = i;
        this.f10398J = str;
        this.F = str2;
        this.G = str4;
        this.H = j;
        this.K = str5;
        this.L = z;
    }

    public static C3665m61 a(Tab tab) {
        String str;
        NavigationEntry l;
        if (tab.l() == null || tab.l().f() == null || (l = tab.l().f().l(tab.l().f().m())) == null) {
            str = null;
        } else {
            str = l.e.h();
        }
        int id = tab.getId();
        String title = tab.getTitle();
        String s = tab.s();
        String n = tab.n();
        if (str == null) {
            str = "";
        }
        return new C3665m61(id, title, s, n, str, C5383wB.q(tab).S, tab.s(), tab.a());
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return Integer.compare(this.I, ((C3665m61) obj).I);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C3665m61)) {
            return false;
        }
        C3665m61 m61 = (C3665m61) obj;
        return this.I == m61.I && TextUtils.equals(this.F, m61.F);
    }

    public int hashCode() {
        int i = 527 + this.I;
        if (((i * 31) + this.F) == null) {
            return 0;
        }
        return this.F.hashCode();
    }
}
