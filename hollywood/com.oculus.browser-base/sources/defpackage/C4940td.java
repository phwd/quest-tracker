package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: td  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4940td implements AbstractC5135ul0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Tab f11353a;
    public final /* synthetic */ Callback b;
    public final /* synthetic */ boolean c;

    public C4940td(Tab tab, Callback callback, boolean z) {
        this.f11353a = tab;
        this.b = callback;
        this.c = z;
    }

    @Override // defpackage.AbstractC5135ul0
    public void a(boolean z) {
        if (z) {
            C5110ud.a(this.f11353a, this.b, this.c);
            return;
        }
        AbstractC4259pd.a(2);
        this.b.onResult(null);
    }
}
