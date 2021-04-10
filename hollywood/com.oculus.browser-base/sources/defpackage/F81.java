package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: F81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F81 implements View.OnLayoutChangeListener {
    public final /* synthetic */ View F;
    public final /* synthetic */ G81 G;

    public F81(G81 g81, View view) {
        this.G = g81;
        this.F = view;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.F.getVisibility() == 0) {
            Objects.requireNonNull(this.G);
        }
    }
}
