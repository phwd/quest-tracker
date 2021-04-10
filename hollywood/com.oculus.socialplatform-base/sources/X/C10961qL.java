package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* renamed from: X.1qL  reason: invalid class name and case insensitive filesystem */
public class C10961qL extends AutoCompleteTextView {
    public static final int[] A02 = {16843126};
    public final C10991qO A00;
    public final AnonymousClass1qE A01;

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            return r0.A01();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A05(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A06(mode);
        }
    }

    public C10961qL(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(AnonymousClass1RS.A00(context), attributeSet, i);
        Context context2 = getContext();
        C10891q9.A03(this, context2);
        getContext();
        C10901qA A002 = C10901qA.A00(context2, attributeSet, A02, i, 0);
        if (A002.A02.hasValue(0)) {
            setDropDownBackgroundDrawable(A002.A02(0));
        }
        A002.A04();
        C10991qO r0 = new C10991qO(this);
        this.A00 = r0;
        r0.A07(attributeSet, i);
        AnonymousClass1qE r02 = new AnonymousClass1qE(this);
        this.A01 = r02;
        r02.A0A(attributeSet, i);
        this.A01.A04();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A03();
        }
        AnonymousClass1qE r02 = this.A01;
        if (r02 != null) {
            r02.A04();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C11141qg.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C10991qO r1 = this.A00;
        if (r1 != null) {
            C10991qO.A00(r1, null);
            r1.A03();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A04(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass08Y.A00(this, callback));
    }

    public void setDropDownBackgroundResource(@DrawableRes int i) {
        setDropDownBackgroundDrawable(AnonymousClass1pW.A00(getContext(), i));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            r0.A07(context, i);
        }
    }
}
