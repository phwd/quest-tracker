package defpackage;

import android.content.Context;
import android.view.View;
import java.util.Objects;
import org.chromium.ui.widget.ChipView;

/* renamed from: GM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GM0 implements View.OnClickListener {
    public View F;
    public ChipView G;
    public O4 H;
    public Context I;

    public GM0(Context context, View view) {
        this.I = context;
        this.F = view;
    }

    public void a() {
        O4 o4 = this.H;
        if (o4 != null && o4.c()) {
            this.H.K.dismiss();
        }
    }

    public final void b(int i) {
        AbstractC3364kK0.g("ContextMenu.LensChip.Event", i, 3);
    }

    public void onClick(View view) {
        if (view == this.G) {
            b(1);
            Objects.requireNonNull(null);
            throw null;
        }
    }
}
