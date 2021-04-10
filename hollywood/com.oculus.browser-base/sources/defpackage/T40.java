package defpackage;

import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;
import org.chromium.chrome.browser.keyboard_accessory.tab_layout_component.KeyboardAccessoryTabLayoutView;

/* renamed from: T40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T40 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2294e50 f8935a;

    public T40(C2294e50 e50) {
        this.f8935a = e50;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        KeyboardAccessoryView keyboardAccessoryView = (KeyboardAccessoryView) obj;
        P50 p50 = this.f8935a.b;
        if (keyboardAccessoryView.H == null) {
            keyboardAccessoryView.H = (TabLayout) keyboardAccessoryView.findViewById(R.id.tabs);
        }
        TabLayout tabLayout = keyboardAccessoryView.H;
        R50 r50 = p50.b;
        r50.H.add(new E81(tabLayout));
        ZH0.a(p50.f8665a, (KeyboardAccessoryTabLayoutView) tabLayout, new K50());
    }
}
