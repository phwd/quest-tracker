package defpackage;

import android.graphics.Typeface;
import com.google.android.material.chip.Chip;

/* renamed from: ep  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2415ep extends AbstractC0931Pf1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Chip f9883a;

    public C2415ep(Chip chip) {
        this.f9883a = chip;
    }

    @Override // defpackage.AbstractC0931Pf1
    public void a(int i) {
    }

    @Override // defpackage.AbstractC0931Pf1
    public void b(Typeface typeface, boolean z) {
        CharSequence charSequence;
        Chip chip = this.f9883a;
        C3098ip ipVar = chip.L;
        if (ipVar.i1) {
            charSequence = ipVar.m0;
        } else {
            charSequence = chip.getText();
        }
        chip.setText(charSequence);
        this.f9883a.requestLayout();
        this.f9883a.invalidate();
    }
}
