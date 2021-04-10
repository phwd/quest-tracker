package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* renamed from: c60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1956c60 extends AbstractC2127d60 {
    public C1956c60(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    @Override // defpackage.AbstractC2127d60
    public void x(G50 g50, View view) {
        TextView textView = (TextView) view;
        C2636g50 g502 = g50.b;
        textView.setText(g502.f9975a);
        textView.setOnClickListener(new View$OnClickListenerC1785b60(g502));
    }
}
