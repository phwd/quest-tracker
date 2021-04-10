package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import com.oculus.alpenglow.R;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0dq  reason: invalid class name and case insensitive filesystem */
public final class C04030dq implements AbstractC002604c {
    public Window.Callback A00;
    public boolean A01;
    public int A02 = 0;
    public int A03;
    public Drawable A04;
    public Drawable A05;
    public Drawable A06;
    public View A07;
    public Toolbar A08;
    public CharSequence A09;
    public CharSequence A0A;
    public CharSequence A0B;
    public boolean A0C;
    public Drawable A0D;
    public View A0E;
    public AnonymousClass0Mm A0F;

    @Override // X.AbstractC002604c
    public final void A81() {
        this.A01 = true;
    }

    public C04030dq(Toolbar toolbar, boolean z) {
        String string;
        Drawable drawable;
        Toolbar toolbar2;
        this.A08 = toolbar;
        CharSequence charSequence = toolbar.A0E;
        this.A0B = charSequence;
        this.A0A = toolbar.A0D;
        this.A0C = charSequence != null;
        this.A06 = toolbar.getNavigationIcon();
        AnonymousClass05Y A002 = AnonymousClass05Y.A00(toolbar.getContext(), null, AnonymousClass18N.A00, R.attr.actionBarStyle, 0);
        this.A04 = A002.A02(15);
        if (z) {
            TypedArray typedArray = A002.A02;
            CharSequence text = typedArray.getText(27);
            if (!TextUtils.isEmpty(text)) {
                this.A0C = true;
                this.A0B = text;
                if ((this.A03 & 8) != 0) {
                    this.A08.setTitle(text);
                }
            }
            CharSequence text2 = typedArray.getText(25);
            if (!TextUtils.isEmpty(text2)) {
                this.A0A = text2;
                if ((this.A03 & 8) != 0) {
                    this.A08.setSubtitle(text2);
                }
            }
            Drawable A022 = A002.A02(20);
            if (A022 != null) {
                this.A05 = A022;
                A01();
            }
            Drawable A023 = A002.A02(17);
            if (A023 != null) {
                A7x(A023);
            }
            if (this.A06 == null && (drawable = this.A04) != null) {
                this.A06 = drawable;
                if ((this.A03 & 4) != 0) {
                    toolbar2 = this.A08;
                } else {
                    toolbar2 = this.A08;
                    drawable = null;
                }
                toolbar2.setNavigationIcon(drawable);
            }
            A7s(typedArray.getInt(10, 0));
            int resourceId = typedArray.getResourceId(9, 0);
            if (resourceId != 0) {
                View inflate = LayoutInflater.from(this.A08.getContext()).inflate(resourceId, (ViewGroup) this.A08, false);
                View view = this.A07;
                if (!(view == null || (this.A03 & 16) == 0)) {
                    this.A08.removeView(view);
                }
                this.A07 = inflate;
                if (!(inflate == null || (this.A03 & 16) == 0)) {
                    this.A08.addView(inflate);
                }
                A7s(this.A03 | 16);
            }
            int layoutDimension = typedArray.getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = this.A08.getLayoutParams();
                layoutParams.height = layoutDimension;
                this.A08.setLayoutParams(layoutParams);
            }
            int dimensionPixelOffset = typedArray.getDimensionPixelOffset(7, -1);
            int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(3, -1);
            if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
                Toolbar toolbar3 = this.A08;
                int max = Math.max(dimensionPixelOffset, 0);
                int max2 = Math.max(dimensionPixelOffset2, 0);
                AnonymousClass053 r0 = toolbar3.A0A;
                if (r0 == null) {
                    r0 = new AnonymousClass053();
                    toolbar3.A0A = r0;
                }
                r0.A00(max, max2);
            }
            int resourceId2 = typedArray.getResourceId(28, 0);
            if (resourceId2 != 0) {
                Toolbar toolbar4 = this.A08;
                Context context = toolbar4.getContext();
                toolbar4.A05 = resourceId2;
                TextView textView = toolbar4.A08;
                if (textView != null) {
                    textView.setTextAppearance(context, resourceId2);
                }
            }
            int resourceId3 = typedArray.getResourceId(26, 0);
            if (resourceId3 != 0) {
                Toolbar toolbar5 = this.A08;
                Context context2 = toolbar5.getContext();
                toolbar5.A04 = resourceId3;
                TextView textView2 = toolbar5.A07;
                if (textView2 != null) {
                    textView2.setTextAppearance(context2, resourceId3);
                }
            }
            int resourceId4 = typedArray.getResourceId(22, 0);
            if (resourceId4 != 0) {
                this.A08.setPopupTheme(resourceId4);
            }
        } else {
            int i = 11;
            if (this.A08.getNavigationIcon() != null) {
                i = 15;
                this.A04 = this.A08.getNavigationIcon();
            }
            this.A03 = i;
        }
        A002.A04();
        if (R.string.abc_action_bar_up_description != this.A02) {
            this.A02 = R.string.abc_action_bar_up_description;
            if (TextUtils.isEmpty(this.A08.getNavigationContentDescription())) {
                int i2 = this.A02;
                if (i2 == 0) {
                    string = null;
                } else {
                    string = A3F().getString(i2);
                }
                this.A09 = string;
                A00();
            }
        }
        this.A09 = this.A08.getNavigationContentDescription();
        this.A08.setNavigationOnClickListener(new View$OnClickListenerC004705e(this));
    }

    private void A00() {
        if ((this.A03 & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.A09)) {
            this.A08.setNavigationContentDescription(this.A02);
        } else {
            this.A08.setNavigationContentDescription(this.A09);
        }
    }

    private void A01() {
        Drawable drawable;
        int i = this.A03;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.A05) == null) {
            drawable = this.A0D;
        }
        this.A08.setLogo(drawable);
    }

    @Override // X.AbstractC002604c
    public final boolean A1d() {
        ActionMenuView actionMenuView;
        Toolbar toolbar = this.A08;
        if (toolbar.getVisibility() != 0 || (actionMenuView = toolbar.A09) == null || !actionMenuView.A06) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC002604c
    public final void A1j() {
        C04250eW r0;
        C04050dt r02 = this.A08.A0B;
        if (r02 != null && (r0 = r02.A01) != null) {
            r0.collapseActionView();
        }
    }

    @Override // X.AbstractC002604c
    public final void A28() {
        AnonymousClass0Mm r0;
        ActionMenuView actionMenuView = this.A08.A09;
        if (actionMenuView != null && (r0 = actionMenuView.A04) != null) {
            r0.A05();
            C01880Ms r02 = r0.A03;
            if (r02 != null) {
                r02.A03();
            }
        }
    }

    @Override // X.AbstractC002604c
    public final Context A3F() {
        return this.A08.getContext();
    }

    @Override // X.AbstractC002604c
    public final int A3S() {
        return this.A03;
    }

    @Override // X.AbstractC002604c
    public final Menu A42() {
        return this.A08.getMenu();
    }

    @Override // X.AbstractC002604c
    public final int A49() {
        return 0;
    }

    @Override // X.AbstractC002604c
    public final ViewGroup A4s() {
        return this.A08;
    }

    @Override // X.AbstractC002604c
    public final boolean A50() {
        C04050dt r0 = this.A08.A0B;
        if (r0 == null || r0.A01 == null) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC002604c
    public final boolean A54() {
        AnonymousClass0Mm r0;
        ActionMenuView actionMenuView = this.A08.A09;
        if (actionMenuView == null || (r0 = actionMenuView.A04) == null || !r0.A05()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC002604c
    public final boolean A5W() {
        AnonymousClass0Mm r1;
        ActionMenuView actionMenuView = this.A08.A09;
        if (actionMenuView == null || (r1 = actionMenuView.A04) == null) {
            return false;
        }
        if (r1.A04 != null || r1.A06()) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC002604c
    public final boolean A5X() {
        AnonymousClass0Mm r0;
        ActionMenuView actionMenuView = this.A08.A09;
        if (actionMenuView == null || (r0 = actionMenuView.A04) == null || !r0.A06()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC002604c
    public final void A7o(boolean z) {
        this.A08.setCollapsible(z);
    }

    @Override // X.AbstractC002604c
    public final void A7s(int i) {
        View view;
        CharSequence charSequence;
        Toolbar toolbar;
        Toolbar toolbar2;
        Drawable drawable;
        int i2 = this.A03 ^ i;
        this.A03 = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    A00();
                }
                if ((this.A03 & 4) != 0) {
                    toolbar2 = this.A08;
                    drawable = this.A06;
                    if (drawable == null) {
                        drawable = this.A04;
                    }
                } else {
                    toolbar2 = this.A08;
                    drawable = null;
                }
                toolbar2.setNavigationIcon(drawable);
            }
            if ((i2 & 3) != 0) {
                A01();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.A08.setTitle(this.A0B);
                    toolbar = this.A08;
                    charSequence = this.A0A;
                } else {
                    charSequence = null;
                    this.A08.setTitle((CharSequence) null);
                    toolbar = this.A08;
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i2 & 16) != 0 && (view = this.A07) != null) {
                if ((i & 16) != 0) {
                    this.A08.addView(view);
                } else {
                    this.A08.removeView(view);
                }
            }
        }
    }

    @Override // X.AbstractC002604c
    public final void A7t(AnonymousClass058 r3) {
        Toolbar toolbar;
        View view = this.A0E;
        if (view != null && view.getParent() == (toolbar = this.A08)) {
            toolbar.removeView(this.A0E);
        }
        this.A0E = r3;
    }

    @Override // X.AbstractC002604c
    public final void A7w(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = AnonymousClass17E.A00(A3F(), i);
        } else {
            drawable = null;
        }
        A7x(drawable);
    }

    @Override // X.AbstractC002604c
    public final void A7x(Drawable drawable) {
        this.A0D = drawable;
        A01();
    }

    @Override // X.AbstractC002604c
    public final void A7z(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = AnonymousClass17E.A00(A3F(), i);
        } else {
            drawable = null;
        }
        this.A05 = drawable;
        A01();
    }

    @Override // X.AbstractC002604c
    public final void A80(AbstractC000503a r2, AnonymousClass03V r3) {
        this.A08.setMenuCallbacks(r2, r3);
    }

    @Override // X.AbstractC002604c
    public final void A8F(int i) {
        this.A08.setVisibility(i);
    }

    @Override // X.AbstractC002604c
    public final AnonymousClass0B0 A8J(int i, long j) {
        AnonymousClass0B0 A002 = AnonymousClass0Aw.A00(this.A08);
        float f = 0.0f;
        if (i == 0) {
            f = 1.0f;
        }
        A002.A01(f);
        A002.A03(j);
        A002.A04(new C01830Mb(this, i));
        return A002;
    }

    @Override // X.AbstractC002604c
    public final boolean A8S() {
        AnonymousClass0Mm r0;
        ActionMenuView actionMenuView = this.A08.A09;
        if (actionMenuView == null || (r0 = actionMenuView.A04) == null || !r0.A07()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC002604c
    public final CharSequence getTitle() {
        return this.A08.A0E;
    }

    @Override // X.AbstractC002604c
    public final void setMenu(Menu menu, AbstractC000503a r4) {
        AnonymousClass0Mm r1 = this.A0F;
        if (r1 == null) {
            r1 = new AnonymousClass0Mm(this.A08.getContext());
            this.A0F = r1;
        }
        r1.A7m(r4);
        this.A08.setMenu((C04280eZ) menu, r1);
    }

    @Override // X.AbstractC002604c
    public final void setWindowTitle(CharSequence charSequence) {
        if (!this.A0C) {
            this.A0B = charSequence;
            if ((this.A03 & 8) != 0) {
                this.A08.setTitle(charSequence);
            }
        }
    }

    @Override // X.AbstractC002604c
    public final void setWindowCallback(Window.Callback callback) {
        this.A00 = callback;
    }
}
