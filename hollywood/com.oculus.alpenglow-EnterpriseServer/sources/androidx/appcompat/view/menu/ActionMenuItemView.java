package androidx.appcompat.view.menu;

import X.AbstractC000703c;
import X.AbstractC002203u;
import X.AbstractView$OnAttachStateChangeListenerC003504l;
import X.AnonymousClass02D;
import X.AnonymousClass03M;
import X.AnonymousClass03W;
import X.AnonymousClass18N;
import X.C004805f;
import X.C04090e2;
import X.C04250eW;
import X.C04280eZ;
import X.C04320ef;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public class ActionMenuItemView extends C04090e2 implements AbstractC000703c, AbstractC002203u, View.OnClickListener {
    public C04250eW A00;
    public AnonymousClass03M A01;
    public AnonymousClass03W A02;
    public int A03;
    public int A04;
    public int A05;
    public Drawable A06;
    public AbstractView$OnAttachStateChangeListenerC003504l A07;
    public CharSequence A08;
    public boolean A09;
    public boolean A0A;

    @Override // X.AbstractC000703c
    public final boolean A6s() {
        return true;
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    private void A00() {
        CharSequence charSequence;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.A08);
        if (this.A06 != null && ((this.A00.A05 & 4) != 4 || (!this.A09 && !this.A0A))) {
            z = false;
        }
        boolean z3 = z2 & z;
        CharSequence charSequence2 = null;
        if (z3) {
            charSequence = this.A08;
        } else {
            charSequence = null;
        }
        setText(charSequence);
        CharSequence contentDescription = this.A00.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            if (z3) {
                contentDescription = null;
            } else {
                contentDescription = this.A00.getTitle();
            }
        }
        setContentDescription(contentDescription);
        CharSequence tooltipText = this.A00.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (!z3) {
                charSequence2 = this.A00.getTitle();
            }
            C004805f.A00(this, charSequence2);
            return;
        }
        C004805f.A00(this, tooltipText);
    }

    @Override // X.AbstractC000703c
    public final void A5G(C04250eW r3, int i) {
        CharSequence title;
        this.A00 = r3;
        setIcon(r3.getIcon());
        if (A6s()) {
            title = r3.getTitleCondensed();
        } else {
            title = r3.getTitle();
        }
        setTitle(title);
        setId(r3.getItemId());
        int i2 = 8;
        if (r3.isVisible()) {
            i2 = 0;
        }
        setVisibility(i2);
        setEnabled(r3.isEnabled());
        if (r3.hasSubMenu() && this.A07 == null) {
            this.A07 = new C04320ef(this);
        }
    }

    @Override // X.AbstractC000703c
    public C04250eW getItemData() {
        return this.A00;
    }

    public final void onClick(View view) {
        AnonymousClass03W r1 = this.A02;
        if (r1 != null) {
            r1.A5L(this.A00);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractView$OnAttachStateChangeListenerC003504l r0;
        if (!this.A00.hasSubMenu() || (r0 = this.A07) == null || !r0.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setExpandedFormat(boolean z) {
        if (this.A0A != z) {
            this.A0A = z;
            C04250eW r0 = this.A00;
            if (r0 != null) {
                C04280eZ r1 = r0.A0B;
                r1.A0A = true;
                r1.A0G(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.A06 = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.A03;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i) / ((float) intrinsicWidth)));
                intrinsicWidth = i;
            }
            if (intrinsicHeight > i) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i) / ((float) intrinsicHeight)));
                intrinsicHeight = i;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        A00();
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        this.A05 = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setTitle(CharSequence charSequence) {
        this.A08 = charSequence;
        A00();
    }

    private boolean A01() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (i >= 480 || ((i >= 640 && i2 >= 480) || configuration.orientation == 2)) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC002203u
    public final boolean A5j() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // X.AbstractC002203u
    public final boolean A5k() {
        if (!(!TextUtils.isEmpty(getText())) || this.A00.getIcon() != null) {
            return false;
        }
        return true;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.A09 = A01();
        A00();
    }

    @Override // X.C04090e2
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        boolean z = !TextUtils.isEmpty(getText());
        if (z && (i5 = this.A05) >= 0) {
            super.setPadding(i5, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            i4 = this.A04;
            i3 = Math.min(size, i4);
        } else {
            i3 = this.A04;
            i4 = i3;
        }
        if (mode != 1073741824 && i4 > 0 && measuredWidth < i3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        }
        if (!z && this.A06 != null) {
            super.setPadding((getMeasuredWidth() - this.A06.getBounds().width()) >> 1, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void setItemInvoker(AnonymousClass03W r1) {
        this.A02 = r1;
    }

    public void setPopupCallback(AnonymousClass03M r1) {
        this.A01 = r1;
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.A09 = A01();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass18N.A02, i, 0);
        this.A04 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        this.A03 = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.A05 = -1;
        setSaveEnabled(false);
    }
}
