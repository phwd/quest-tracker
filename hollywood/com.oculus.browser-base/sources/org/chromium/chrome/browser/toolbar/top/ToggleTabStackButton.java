package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.TraceEvent;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ToggleTabStackButton extends ListMenuButton implements AbstractC5710y61, View.OnClickListener, View.OnLongClickListener {
    public C1410Xc1 P;
    public C1410Xc1 Q;
    public C5880z61 R;
    public View.OnClickListener S;
    public View.OnLongClickListener T;
    public HI0 U;
    public Drawable V;

    public ToggleTabStackButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractC5710y61
    public void b(int i, boolean z) {
        setEnabled(i >= 1);
        setContentDescription(getResources().getQuantityString(R.plurals.f42640_resource_name_obfuscated_RES_2131820556, i, Integer.valueOf(i)));
        this.Q.g(i, z);
        this.P.g(i, z);
    }

    public void h(boolean z) {
        if (this.V == null) {
            this.V = getBackground();
        }
        if (z) {
            if (this.U == null) {
                HI0 a2 = HI0.a(getContext());
                this.U = a2;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                a2.H.set(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
                if (!a2.I.isEmpty()) {
                    a2.setBounds(a2.I);
                }
            }
            this.U.d(getContext().getResources(), getDrawable() == this.Q);
            setBackground(this.U);
            this.U.start();
            return;
        }
        setBackground(this.V);
    }

    public void i(boolean z) {
        setImageDrawable(z ? this.Q : this.P);
    }

    public void onClick(View view) {
        if (this.S != null && isClickable()) {
            this.S.onClick(this);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.listmenu.ListMenuButton
    public void onFinishInflate() {
        super.onFinishInflate();
        this.P = C1410Xc1.e(getContext(), false);
        this.Q = C1410Xc1.e(getContext(), true);
        setImageDrawable(this.P);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent j0 = TraceEvent.j0("ToggleTabStackButton.onLayout");
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public boolean onLongClick(View view) {
        if (this.T != null && isLongClickable()) {
            return this.T.onLongClick(view);
        }
        return C1184Ti1.c(getContext(), view, getResources().getString(R.string.f56740_resource_name_obfuscated_RES_2131952991));
    }

    public void onMeasure(int i, int i2) {
        TraceEvent j0 = TraceEvent.j0("ToggleTabStackButton.onMeasure");
        try {
            super.onMeasure(i, i2);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
