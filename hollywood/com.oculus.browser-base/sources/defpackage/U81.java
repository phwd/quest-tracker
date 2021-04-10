package defpackage;

import android.view.ViewGroup;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: U81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class U81 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        ViewGroup viewGroup = (ViewGroup) obj2;
        KH0 kh0 = (KH0) obj3;
        if (kh0 == null) {
            E71.d((ViewLookupCachingFrameLayout) viewGroup, uh0, 1);
            return;
        }
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) viewGroup;
        E71.b(uh0, viewLookupCachingFrameLayout, kh0);
        E71.a(uh0, viewLookupCachingFrameLayout, kh0);
    }
}
