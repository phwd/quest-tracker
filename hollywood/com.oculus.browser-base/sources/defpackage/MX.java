package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.base.Callback;

/* renamed from: MX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MX extends FrameLayout implements ViewGroup.OnHierarchyChangeListener {
    public final AbstractC0705Lm0 F;
    public final Q31 G;
    public final Callback H;
    public C4241pV0 I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC0705Lm0 f8479J;
    public Q31 K;
    public Runnable L;
    public Runnable M;

    public MX(Context context, Q31 q31, AbstractC0705Lm0 lm0, Q31 q312, Callback callback) {
        super(context);
        this.G = q31;
        this.F = lm0;
        this.K = q312;
        this.H = callback;
        setOnHierarchyChangeListener(this);
        setVisibility(4);
    }

    public void a() {
        Runnable runnable = this.L;
        if (runnable != null) {
            this.I.removeCallbacks(runnable);
            this.L = null;
        }
    }

    public final AbstractC0705Lm0 b() {
        if (!((Boolean) this.G.get()).booleanValue()) {
            return this.F;
        }
        if (this.f8479J == null) {
            this.f8479J = new B6(this);
        }
        return this.f8479J;
    }

    public void onChildViewAdded(View view, View view2) {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void onChildViewRemoved(View view, View view2) {
        if (getChildCount() == 0) {
            setVisibility(4);
        }
    }
}
