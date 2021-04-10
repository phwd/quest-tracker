package defpackage;

import com.google.android.material.tabs.TabLayout;
import org.chromium.chrome.browser.keyboard_accessory.tab_layout_component.KeyboardAccessoryTabLayoutView;

/* renamed from: O50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O50 {

    /* renamed from: a  reason: collision with root package name */
    public ZH0 f8598a;
    public E81 b;
    public final /* synthetic */ P50 c;

    public O50(P50 p50, TabLayout tabLayout) {
        this.c = p50;
        this.f8598a = ZH0.a(p50.f8665a, (KeyboardAccessoryTabLayoutView) tabLayout, new N50());
        E81 e81 = new E81(tabLayout);
        this.b = e81;
        p50.b.H.add(e81);
    }
}
