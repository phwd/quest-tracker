package defpackage;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/* renamed from: Fx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0361Fx0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0483Hx0 f8054a;
    public final TextView b;

    public C0361Fx0(C0483Hx0 hx0, TextView textView) {
        this.f8054a = hx0;
        this.b = textView;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0483Hx0 hx0 = this.f8054a;
        TextView textView = this.b;
        Drawable drawable = (Drawable) obj;
        if (drawable != null) {
            int i = hx0.a0;
            drawable.setBounds(0, 0, i, i);
        }
        textView.setCompoundDrawablePadding(hx0.Z);
        textView.setCompoundDrawablesRelative(drawable, null, null, null);
    }
}
