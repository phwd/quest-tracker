package defpackage;

import android.view.View;
import com.google.android.material.internal.NavigationMenuItemView;

/* renamed from: Qm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1009Qm0 extends C5349w {
    public final /* synthetic */ NavigationMenuItemView d;

    public C1009Qm0(NavigationMenuItemView navigationMenuItemView) {
        this.d = navigationMenuItemView;
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        d2.b.setCheckable(this.d.f0);
    }
}
