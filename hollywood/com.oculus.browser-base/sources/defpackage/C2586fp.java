package defpackage;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip;

/* renamed from: fp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2586fp extends ViewOutlineProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Chip f9956a;

    public C2586fp(Chip chip) {
        this.f9956a = chip;
    }

    public void getOutline(View view, Outline outline) {
        C3098ip ipVar = this.f9956a.L;
        if (ipVar != null) {
            ipVar.getOutline(outline);
        } else {
            outline.setAlpha(0.0f);
        }
    }
}
