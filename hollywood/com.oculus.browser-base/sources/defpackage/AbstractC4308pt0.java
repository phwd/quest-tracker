package defpackage;

import android.graphics.Rect;
import android.view.View;

/* renamed from: pt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4308pt0 {

    /* renamed from: a  reason: collision with root package name */
    public final IK0 f11095a;
    public int b = Integer.MIN_VALUE;
    public final Rect c = new Rect();

    public AbstractC4308pt0(IK0 ik0, C3966nt0 nt0) {
        this.f11095a = ik0;
    }

    public static AbstractC4308pt0 a(IK0 ik0, int i) {
        if (i == 0) {
            return new C3966nt0(ik0);
        }
        if (i == 1) {
            return new C4137ot0(ik0);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public int m() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return l() - this.b;
    }

    public abstract int n(View view);

    public abstract int o(View view);

    public abstract void p(int i);
}
