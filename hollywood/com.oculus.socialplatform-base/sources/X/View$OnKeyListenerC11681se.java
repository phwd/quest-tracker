package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.socialplatform.R;

/* renamed from: X.1se  reason: invalid class name and case insensitive filesystem */
public final class View$OnKeyListenerC11681se extends AnonymousClass1sY implements AnonymousClass1t6, PopupWindow.OnDismissListener, View.OnKeyListener, AdapterView.OnItemClickListener {
    public int A00;
    public int A01 = 0;
    public View A02;
    public View A03;
    public ViewTreeObserver A04;
    public PopupWindow.OnDismissListener A05;
    public boolean A06;
    public boolean A07;
    public boolean A08;
    public AbstractC11941tc A09;
    public final int A0A;
    public final Context A0B;
    public final View.OnAttachStateChangeListener A0C = new AnonymousClass1tJ(this);
    public final ViewTreeObserver.OnGlobalLayoutListener A0D = new ViewTreeObserver$OnGlobalLayoutListenerC11881tE(this);
    public final C11661sc A0E;
    public final C11581sN A0F;
    public final C11651sb A0G;
    public final int A0H;
    public final int A0I;
    public final boolean A0J;

    @Override // X.AnonymousClass1t6
    public final boolean A3F() {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final boolean A89(SubMenuC11621sV r14) {
        if (r14.hasVisibleItems()) {
            AnonymousClass1sZ r6 = new AnonymousClass1sZ(this.A0B, r14, this.A03, this.A0J, this.A0H, this.A0I);
            r6.A04(this.A09);
            int size = r14.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                MenuItem item = r14.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
                i++;
            }
            r6.A05 = z;
            AnonymousClass1sY r0 = r6.A03;
            if (r0 != null) {
                r0.A02(z);
            }
            r6.A02 = this.A05;
            this.A05 = null;
            this.A0F.A0F(false);
            C11651sb r02 = this.A0G;
            int A47 = r02.A47();
            int A5H = r02.A5H();
            if ((Gravity.getAbsoluteGravity(this.A01, this.A02.getLayoutDirection()) & 7) == 5) {
                A47 += this.A02.getWidth();
            }
            if (!r6.A05()) {
                if (r6.A01 != null) {
                    AnonymousClass1sZ.A00(r6, A47, A5H, true, true);
                }
            }
            AbstractC11941tc r03 = this.A09;
            if (r03 != null) {
                r03.A7T(r14);
            }
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final void AAw(boolean z) {
        this.A06 = false;
        C11661sc r0 = this.A0E;
        if (r0 != null) {
            r0.notifyDataSetChanged();
        }
    }

    public final void onDismiss() {
        this.A08 = true;
        this.A0F.close();
        ViewTreeObserver viewTreeObserver = this.A04;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.A04 = this.A03.getViewTreeObserver();
            }
            this.A04.removeGlobalOnLayoutListener(this.A0D);
            this.A04 = null;
        }
        this.A03.removeOnAttachStateChangeListener(this.A0C);
        PopupWindow.OnDismissListener onDismissListener = this.A05;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // X.AnonymousClass1FA
    public final ListView A4G() {
        return this.A0G.A4G();
    }

    @Override // X.AnonymousClass1FA
    public final boolean A6B() {
        if (this.A08 || !this.A0G.A6B()) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void A6r(C11581sN r2, boolean z) {
        if (r2 == this.A0F) {
            dismiss();
            AbstractC11941tc r0 = this.A09;
            if (r0 != null) {
                r0.A6r(r2, z);
            }
        }
    }

    public View$OnKeyListenerC11681se(Context context, C11581sN r6, View view, int i, int i2, boolean z) {
        this.A0B = context;
        this.A0F = r6;
        this.A0J = z;
        this.A0E = new C11661sc(r6, LayoutInflater.from(context), this.A0J, R.layout.abc_popup_menu_item_layout);
        this.A0H = i;
        this.A0I = i2;
        Resources resources = context.getResources();
        this.A0A = Math.max(resources.getDisplayMetrics().widthPixels >> 1, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.A02 = view;
        this.A0G = new C11651sb(this.A0B, this.A0H, this.A0I);
        r6.A0E(this, context);
    }

    @Override // X.AnonymousClass1FA
    public final void AAO() {
        View view;
        Rect rect;
        if (A6B()) {
            return;
        }
        if (this.A08 || (view = this.A02) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.A03 = view;
        C11651sb r6 = this.A0G;
        r6.A0A.setOnDismissListener(this);
        r6.A08 = this;
        r6.A0E = true;
        r6.A0A.setFocusable(true);
        View view2 = this.A03;
        boolean z = false;
        if (this.A04 == null) {
            z = true;
        }
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.A04 = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.A0D);
        }
        view2.addOnAttachStateChangeListener(this.A0C);
        r6.A07 = view2;
        r6.A01 = this.A01;
        if (!this.A06) {
            this.A00 = AnonymousClass1sY.A00(this.A0E, this.A0B, this.A0A);
            this.A06 = true;
        }
        r6.A00(this.A00);
        r6.A0A.setInputMethodMode(2);
        Rect rect2 = super.A00;
        if (rect2 != null) {
            rect = new Rect(rect2);
        } else {
            rect = null;
        }
        r6.A06 = rect;
        r6.AAO();
        ListView A4G = r6.A4G();
        A4G.setOnKeyListener(this);
        if (this.A07) {
            C11581sN r3 = this.A0F;
            if (r3.A05 != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.A0B).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) A4G, false);
                TextView textView = (TextView) frameLayout.findViewById(16908310);
                if (textView != null) {
                    textView.setText(r3.A05);
                }
                frameLayout.setEnabled(false);
                A4G.addHeaderView(frameLayout, null, false);
            }
        }
        r6.A9e(this.A0E);
        r6.AAO();
    }

    @Override // X.AnonymousClass1FA
    public final void dismiss() {
        if (A6B()) {
            this.A0G.dismiss();
        }
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void A9h(AbstractC11941tc r1) {
        this.A09 = r1;
    }
}
