package defpackage;

import android.view.View;
import android.widget.Button;

/* renamed from: k51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3320k51 implements AbstractC3491l51 {

    /* renamed from: a  reason: collision with root package name */
    public final int f10261a;
    public final View.OnClickListener b;

    public C3320k51(int i, View.OnClickListener onClickListener) {
        this.f10261a = i;
        this.b = onClickListener;
    }

    @Override // defpackage.AbstractC3491l51
    public void a(Button button) {
        button.setVisibility(0);
        button.setText(this.f10261a);
        button.setOnClickListener(this.b);
    }
}
