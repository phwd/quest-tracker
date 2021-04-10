package defpackage;

import android.widget.CompoundButton;
import java.util.Objects;

/* renamed from: lK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3534lK implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ C4729sK F;

    public C3534lK(View$OnClickListenerC3876nK nKVar, C4729sK sKVar) {
        this.F = sKVar;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        C4729sK sKVar = this.F;
        Objects.requireNonNull(sKVar);
        NU0.f8549a.m(sKVar.s.toString(), z);
        int i = View$OnClickListenerC3876nK.F;
    }
}
