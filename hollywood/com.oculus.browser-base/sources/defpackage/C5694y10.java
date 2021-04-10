package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.components.infobars.InfoBar;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* renamed from: y10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5694y10 extends OptimizedFrameLayout {
    public final int G;
    public final ArrayList H = new ArrayList();
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC2282e10 f11655J;
    public AbstractC5354w10 K;
    public C4333q10 L;
    public Runnable M;

    public C5694y10(Context context, Runnable runnable, AbstractC2282e10 e10) {
        super(context, null);
        this.G = context.getResources().getDimensionPixelSize(R.dimen.f19990_resource_name_obfuscated_RES_2131165618);
        this.L = new C4333q10(this);
        this.f11655J = e10;
        this.M = runnable;
    }

    public static void d(C5694y10 y10, D10 d10) {
        Objects.requireNonNull(y10);
        y10.addView(d10, 0, new FrameLayout.LayoutParams(-1, -2));
        y10.I.add(d10);
        y10.g();
    }

    public void announceForAccessibility(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            super.announceForAccessibility(charSequence);
        }
    }

    public final void e() {
        AbstractC5354w10 w10;
        if (this.K == null) {
            int size = this.I.size() - 1;
            while (true) {
                C10 c10 = null;
                if (size >= 0) {
                    if (this.H.contains(((D10) this.I.get(size)).F)) {
                        size--;
                    } else if (size != 0 || this.I.size() < 2) {
                        D10 d10 = (D10) this.I.get(size);
                        if (size != this.I.size() - 1) {
                            removeView(d10);
                            this.I.remove(d10);
                            g();
                            addView(d10, 0, new FrameLayout.LayoutParams(-1, -2));
                            this.I.add(d10);
                            g();
                        }
                        f(new C5524x10(this, null));
                        return;
                    } else {
                        f(new C4674s10(this, null));
                        return;
                    }
                } else {
                    if (!this.I.isEmpty()) {
                        C10 c102 = ((D10) this.I.get(0)).F;
                        View childAt = ((D10) this.I.get(0)).getChildAt(0);
                        Objects.requireNonNull((InfoBar) c102);
                        if (childAt != null) {
                            f(new C4844t10(this, null));
                            return;
                        }
                    }
                    if (!this.I.isEmpty()) {
                        C10 c103 = ((D10) this.I.get(0)).F;
                        C10 c104 = null;
                        int i = 0;
                        while (i < this.H.size() && this.H.get(i) != c103) {
                            c104 = (C10) this.H.get(i);
                            i++;
                        }
                        if (c104 != null) {
                            f(new C4503r10(this, c104));
                            return;
                        }
                    }
                    if (this.I.size() < Math.min(this.H.size(), 3)) {
                        C10 c105 = (C10) this.H.get(this.I.size());
                        if (this.I.isEmpty()) {
                            w10 = new C4162p10(this, c105);
                        } else {
                            w10 = new C3991o10(this, c105);
                        }
                        f(w10);
                        return;
                    }
                    if (this.I.size() > 0) {
                        c10 = ((D10) this.I.get(0)).F;
                    }
                    this.f11655J.d(c10);
                    return;
                }
            }
        }
    }

    public final void f(AbstractC5354w10 w10) {
        this.K = w10;
        w10.e();
        if (!isLayoutRequested()) {
            AbstractC5354w10 w102 = this.K;
            Objects.requireNonNull(w102);
            C5014u10 u10 = new C5014u10(w102);
            Animator a2 = w102.a();
            w102.f11513a = a2;
            a2.addListener(u10);
            w102.f11513a.start();
        }
    }

    public final void g() {
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.I.get(i);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = ((size - 1) - i) * this.G;
            view.setLayoutParams(layoutParams);
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (super.onInterceptTouchEvent(motionEvent) || this.K != null || (!this.I.isEmpty() && !((D10) this.I.get(0)).F.d())) {
            return true;
        }
        return false;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.L.a();
        AbstractC5354w10 w10 = this.K;
        if (w10 != null) {
            if (!(w10.f11513a != null)) {
                C5014u10 u10 = new C5014u10(w10);
                Animator a2 = w10.a();
                w10.f11513a = a2;
                a2.addListener(u10);
                w10.f11513a.start();
            }
        }
    }

    @Override // org.chromium.ui.widget.OptimizedFrameLayout
    public void onMeasure(int i, int i2) {
        C4333q10 q10 = this.L;
        Objects.requireNonNull(q10);
        int size = View.MeasureSpec.getSize(i);
        boolean z = size > q10.b;
        if (z != q10.d) {
            q10.d = z;
            if (z) {
                if (q10.e == null) {
                    View view = new View(q10.f11109a.getContext());
                    q10.e = view;
                    view.setBackgroundResource(R.drawable.f33390_resource_name_obfuscated_RES_2131231379);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, 0, 3);
                    layoutParams.leftMargin = -q10.c;
                    q10.e.setLayoutParams(layoutParams);
                    View view2 = new View(q10.f11109a.getContext());
                    q10.f = view2;
                    view2.setBackgroundResource(R.drawable.f33390_resource_name_obfuscated_RES_2131231379);
                    FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(0, 0, 5);
                    layoutParams2.rightMargin = -q10.c;
                    q10.f.setScaleX(-1.0f);
                    q10.f.setLayoutParams(layoutParams2);
                }
                FrameLayout frameLayout = q10.f11109a;
                int i3 = q10.c;
                frameLayout.setPadding(i3, 0, i3, 0);
                q10.f11109a.setClipToPadding(false);
                q10.f11109a.addView(q10.e);
                q10.f11109a.addView(q10.f);
            } else {
                q10.f11109a.setPadding(0, 0, 0, 0);
                q10.f11109a.removeView(q10.e);
                q10.f11109a.removeView(q10.f);
            }
        }
        if (z) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(size, (q10.c * 2) + q10.b), View.MeasureSpec.getMode(i));
        }
        super.onMeasure(i, i2);
        C4333q10 q102 = this.L;
        int measuredHeight = getMeasuredHeight();
        if (q102.d) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(q102.c, 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
            q102.e.measure(makeMeasureSpec, makeMeasureSpec2);
            q102.f.measure(makeMeasureSpec, makeMeasureSpec2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }
}
