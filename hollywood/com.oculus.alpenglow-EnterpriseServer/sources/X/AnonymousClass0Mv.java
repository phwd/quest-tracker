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
import com.oculus.alpenglow.R;

/* renamed from: X.0Mv  reason: invalid class name */
public final class AnonymousClass0Mv extends AbstractC04220eS implements AbstractC000603b, PopupWindow.OnDismissListener, View.OnKeyListener, AdapterView.OnItemClickListener {
    public int A00;
    public int A01 = 0;
    public View A02;
    public View A03;
    public ViewTreeObserver A04;
    public boolean A05;
    public boolean A06;
    public boolean A07;
    public PopupWindow.OnDismissListener A08;
    public AbstractC000503a A09;
    public final int A0A;
    public final Context A0B;
    public final View.OnAttachStateChangeListener A0C = new View$OnAttachStateChangeListenerC001103g(this);
    public final ViewTreeObserver.OnGlobalLayoutListener A0D = new ViewTreeObserver$OnGlobalLayoutListenerC001003f(this);
    public final AnonymousClass03U A0E;
    public final C04280eZ A0F;
    public final C01840Md A0G;
    public final int A0H;
    public final int A0I;
    public final boolean A0J;

    @Override // X.AbstractC04220eS
    public final void A07(C04280eZ r1) {
    }

    @Override // X.AbstractC000603b
    public final boolean A2p() {
        return false;
    }

    @Override // X.AbstractC000603b
    public final boolean A6e(SubMenuC01890Mu r14) {
        if (r14.hasVisibleItems()) {
            C04210eR r6 = new C04210eR(this.A0B, r14, this.A03, this.A0J, this.A0H, this.A0I);
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
            AbstractC04220eS r0 = r6.A03;
            if (r0 != null) {
                r0.A08(z);
            }
            r6.A02 = this.A08;
            this.A08 = null;
            this.A0F.A0F(false);
            C01840Md r02 = this.A0G;
            int A3g = r02.A3g();
            int A4r = r02.A4r();
            if ((Gravity.getAbsoluteGravity(this.A01, this.A02.getLayoutDirection()) & 7) == 5) {
                A3g += this.A02.getWidth();
            }
            if (!r6.A05()) {
                if (r6.A01 != null) {
                    C04210eR.A00(r6, A3g, A4r, true, true);
                }
            }
            AbstractC000503a r03 = this.A09;
            if (r03 != null) {
                r03.A6L(r14);
            }
            return true;
        }
        return false;
    }

    @Override // X.AbstractC000603b
    public final void A8k(boolean z) {
        this.A05 = false;
        AnonymousClass03U r0 = this.A0E;
        if (r0 != null) {
            r0.notifyDataSetChanged();
        }
    }

    public final void onDismiss() {
        this.A07 = true;
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
        PopupWindow.OnDismissListener onDismissListener = this.A08;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // X.AbstractC04220eS
    public final void A03(int i) {
        this.A0G.A7u(i);
    }

    @Override // X.AbstractC04220eS
    public final void A04(int i) {
        this.A0G.A8E(i);
    }

    @Override // X.AbstractC04220eS
    public final void A08(boolean z) {
        this.A0E.A01 = z;
    }

    @Override // X.AbstractC000903e
    public final ListView A3u() {
        return this.A0G.A3u();
    }

    @Override // X.AbstractC000903e
    public final boolean A5a() {
        if (this.A07 || !this.A0G.A5a()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC000603b
    public final void A5x(C04280eZ r2, boolean z) {
        if (r2 == this.A0F) {
            dismiss();
            AbstractC000503a r0 = this.A09;
            if (r0 != null) {
                r0.A5x(r2, z);
            }
        }
    }

    public AnonymousClass0Mv(Context context, C04280eZ r6, View view, int i, int i2, boolean z) {
        this.A0B = context;
        this.A0F = r6;
        this.A0J = z;
        this.A0E = new AnonymousClass03U(r6, LayoutInflater.from(context), this.A0J, R.layout.abc_popup_menu_item_layout);
        this.A0H = i;
        this.A0I = i2;
        Resources resources = context.getResources();
        this.A0A = Math.max(resources.getDisplayMetrics().widthPixels >> 1, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.A02 = view;
        this.A0G = new C01840Md(this.A0B, this.A0H, this.A0I);
        r6.A0E(this, context);
    }

    @Override // X.AbstractC000903e
    public final void A8P() {
        View view;
        Rect rect;
        if (A5a()) {
            return;
        }
        if (this.A07 || (view = this.A02) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.A03 = view;
        C01840Md r6 = this.A0G;
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
        if (!this.A05) {
            this.A00 = AbstractC04220eS.A01(this.A0E, this.A0B, this.A0A);
            this.A05 = true;
        }
        r6.A01(this.A00);
        r6.A0A.setInputMethodMode(2);
        Rect rect2 = super.A00;
        if (rect2 != null) {
            rect = new Rect(rect2);
        } else {
            rect = null;
        }
        r6.A06 = rect;
        r6.A8P();
        ListView A3u = r6.A3u();
        A3u.setOnKeyListener(this);
        if (this.A06) {
            C04280eZ r3 = this.A0F;
            if (r3.A05 != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.A0B).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) A3u, false);
                TextView textView = (TextView) frameLayout.findViewById(16908310);
                if (textView != null) {
                    textView.setText(r3.A05);
                }
                frameLayout.setEnabled(false);
                A3u.addHeaderView(frameLayout, null, false);
            }
        }
        r6.A7j(this.A0E);
        r6.A8P();
    }

    @Override // X.AbstractC000903e
    public final void dismiss() {
        if (A5a()) {
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

    @Override // X.AbstractC04220eS
    public final void A02(int i) {
        this.A01 = i;
    }

    @Override // X.AbstractC04220eS
    public final void A05(View view) {
        this.A02 = view;
    }

    @Override // X.AbstractC04220eS
    public final void A06(PopupWindow.OnDismissListener onDismissListener) {
        this.A08 = onDismissListener;
    }

    @Override // X.AbstractC04220eS
    public final void A09(boolean z) {
        this.A06 = z;
    }

    @Override // X.AbstractC000603b
    public final void A7m(AbstractC000503a r1) {
        this.A09 = r1;
    }
}
