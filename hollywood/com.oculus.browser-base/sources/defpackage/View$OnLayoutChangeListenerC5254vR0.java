package defpackage;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

/* renamed from: vR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC5254vR0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ C5084uR0 F;
    public final /* synthetic */ TextView G;
    public final /* synthetic */ Context H;

    public View$OnLayoutChangeListenerC5254vR0(C5084uR0 ur0, TextView textView, Context context) {
        this.F = ur0;
        this.G = textView;
        this.H = context;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.F.d() != null) {
            this.G.removeOnLayoutChangeListener(this);
            return;
        }
        Layout layout = this.G.getLayout();
        if (layout.getEllipsisCount(0) > 0) {
            this.G.setText(AbstractC5424wR0.a(this.H, this.F, layout, this.G.getPaint()));
        }
    }
}
