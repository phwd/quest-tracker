package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryModernView;

/* renamed from: z50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5876z50 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ KeyboardAccessoryModernView f11720a;

    public C5876z50(KeyboardAccessoryModernView keyboardAccessoryModernView) {
        this.f11720a = keyboardAccessoryModernView;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        if (i != 0) {
            KeyboardAccessoryModernView keyboardAccessoryModernView = this.f11720a;
            keyboardAccessoryModernView.G.k0(keyboardAccessoryModernView.P);
            Tm1 b = AbstractC3832n50.b();
            if (b != null) {
                b.notifyEvent("keyboard_accessory_bar_swiped");
            }
        }
    }
}
