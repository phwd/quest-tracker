package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;

/* renamed from: X.0e9  reason: invalid class name and case insensitive filesystem */
public final class C04160e9 extends MultiAutoCompleteTextView {
    public static final int[] A02 = {16843126};
    public final AnonymousClass04A A00;
    public final AnonymousClass04U A01;

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            return r0.A03();
        }
        return null;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A06(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A07(mode);
        }
    }

    public C04160e9(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass17C.A00(context), attributeSet, R.attr.autoCompleteTextViewStyle);
        Context context2 = getContext();
        AnonymousClass05V.A03(this, context2);
        getContext();
        AnonymousClass05Y A002 = AnonymousClass05Y.A00(context2, attributeSet, A02, R.attr.autoCompleteTextViewStyle, 0);
        if (A002.A02.hasValue(0)) {
            setDropDownBackgroundDrawable(A002.A02(0));
        }
        A002.A04();
        AnonymousClass04A r0 = new AnonymousClass04A(this);
        this.A00 = r0;
        r0.A08(attributeSet, R.attr.autoCompleteTextViewStyle);
        AnonymousClass04U r02 = new AnonymousClass04U(this);
        this.A01 = r02;
        r02.A0C(attributeSet, R.attr.autoCompleteTextViewStyle);
        this.A01.A06();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A04();
        }
        AnonymousClass04U r02 = this.A01;
        if (r02 != null) {
            r02.A06();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AnonymousClass04F.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AnonymousClass04A r1 = this.A00;
        if (r1 != null) {
            AnonymousClass04A.A00(r1, null);
            r1.A04();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A05(i);
        }
    }

    public void setDropDownBackgroundResource(@DrawableRes int i) {
        setDropDownBackgroundDrawable(AnonymousClass17E.A00(getContext(), i));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            r0.A09(context, i);
        }
    }
}
