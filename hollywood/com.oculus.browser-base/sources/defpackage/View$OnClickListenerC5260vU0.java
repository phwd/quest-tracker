package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: vU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5260vU0 implements View.OnClickListener {
    public final View$OnLayoutChangeListenerC5940zU0 F;
    public final C2189dU0 G;
    public final boolean H;

    public View$OnClickListenerC5260vU0(View$OnLayoutChangeListenerC5940zU0 zu0, C2189dU0 du0, boolean z) {
        this.F = zu0;
        this.G = du0;
        this.H = z;
    }

    public void onClick(View view) {
        View$OnLayoutChangeListenerC5940zU0 zu0 = this.F;
        C2189dU0 du0 = this.G;
        boolean z = this.H;
        Objects.requireNonNull(zu0);
        AbstractC3535lK0.a("SharingHubAndroid.MoreSelected");
        ((C5638xj) zu0.F).p(zu0.U, true, 0);
        LT0.j(du0, z);
    }
}
