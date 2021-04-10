package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: tB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4874tB0 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AB0 f11327a;

    public C4874tB0(AB0 ab0) {
        this.f11327a = ab0;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        if (tab == null || tab.getId() != i2) {
            C0289Es es = (C0289Es) this.f11327a.m;
            es.e.a(0);
            es.d("Tab switch dismissed Payment Request UI.");
        }
    }
}
