package defpackage;

import J.N;
import android.view.ViewStub;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;

/* renamed from: e50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2294e50 {

    /* renamed from: a  reason: collision with root package name */
    public final C4686s50 f9829a;
    public final P50 b;
    public final YH0 c;
    public final UH0 d;
    public KeyboardAccessoryView e;

    public C2294e50(AbstractC2124d50 d50, ViewStub viewStub) {
        YH0 yh0;
        P50 p50 = new P50();
        C2491fE fEVar = new C2491fE(viewStub);
        this.b = p50;
        MH0 mh0 = I50.i;
        OH0 oh0 = I50.f8198a;
        QH0 qh0 = I50.b;
        QH0 qh02 = I50.c;
        QH0 qh03 = I50.f;
        QH0 qh04 = I50.k;
        Map c2 = UH0.c(new KH0[]{mh0, oh0, qh0, qh02, I50.d, I50.g, qh03, I50.e, I50.h, I50.j, qh04});
        C1794b90 b90 = new C1794b90();
        LH0 lh0 = new LH0(null);
        lh0.f8415a = b90;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(oh0, lh0);
        GH0 gh0 = new GH0(null);
        gh0.f8081a = false;
        hashMap.put(qh0, gh0);
        GH0 gh02 = new GH0(null);
        gh02.f8081a = false;
        hashMap.put(qh02, gh02);
        GH0 gh03 = new GH0(null);
        gh03.f8081a = false;
        hashMap.put(qh03, gh03);
        GH0 gh04 = new GH0(null);
        gh04.f8081a = false;
        hashMap.put(mh0, gh04);
        GH0 gh05 = new GH0(null);
        gh05.f8081a = false;
        hashMap.put(qh04, gh05);
        UH0 uh0 = new UH0(c2, null);
        this.d = uh0;
        C4686s50 s50 = new C4686s50(uh0, d50, p50.b, p50.d);
        this.f9829a = s50;
        if (!N.M09VlOh_("AutofillKeyboardAccessory")) {
            fEVar.a(new T40(this));
        }
        fEVar.a(new U40(this));
        p50.b.G = s50;
        if (N.M09VlOh_("AutofillKeyboardAccessory")) {
            yh0 = new V40();
        } else {
            yh0 = new W40();
        }
        this.c = yh0;
        P70.a(uh0, qh0, fEVar, yh0);
        C4856t50 t50 = new C4856t50(uh0, p50.b);
        uh0.f9530a.b(t50);
        ((C1794b90) uh0.g(oh0)).F.b(t50);
    }

    public void a() {
        UH0 uh0 = this.f9829a.F;
        QH0 qh0 = I50.c;
        uh0.j(qh0, true);
        KeyboardAccessoryView keyboardAccessoryView = this.e;
        if (keyboardAccessoryView != null) {
            this.c.a(this.d, keyboardAccessoryView, qh0);
        }
    }
}
