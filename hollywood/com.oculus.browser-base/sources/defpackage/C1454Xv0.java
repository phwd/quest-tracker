package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Xv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1454Xv0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1515Yv0 f9242a;
    public final Tab b;

    public C1454Xv0(C1515Yv0 yv0, Tab tab) {
        this.f9242a = yv0;
        this.b = tab;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1515Yv0 yv0 = this.f9242a;
        Tab tab = this.b;
        Objects.requireNonNull(yv0);
        if (!((Boolean) obj).booleanValue()) {
            long j = yv0.I.c;
            if (j != 0) {
                N.MO7GqHLu(j, tab.getId());
            }
        }
    }
}
