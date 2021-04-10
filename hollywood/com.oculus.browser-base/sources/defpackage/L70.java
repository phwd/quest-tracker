package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: L70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L70 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8406a;
    public LayoutInflater b;

    public L70(int i) {
        this.f8406a = i;
    }

    @Override // defpackage.AbstractC5105ub0
    public final View a(ViewGroup viewGroup) {
        if (this.b == null) {
            this.b = LayoutInflater.from(viewGroup.getContext());
        }
        return this.b.inflate(this.f8406a, viewGroup, false);
    }
}
