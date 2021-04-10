package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: w91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5379w91 implements D91 {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f11525a;
    public final D91 b;

    public C5379w91(Tab tab, D91 d91) {
        this.f11525a = tab;
        this.b = d91;
    }

    @Override // defpackage.D91
    public void a(int i) {
        Tab tab = this.f11525a;
        D91 d91 = this.b;
        if (tab != null) {
            AbstractC3535lK0.a("TabGrid.TabSearchChipTapped");
            d91.a(i);
            AbstractC5889z91.a(tab);
        }
    }
}
