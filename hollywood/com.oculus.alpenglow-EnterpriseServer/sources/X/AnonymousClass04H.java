package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/* renamed from: X.04H  reason: invalid class name */
public final class AnonymousClass04H extends PopupWindow {
    private void A00(Context context, AttributeSet attributeSet, int i, int i2) {
        AnonymousClass05Y A00 = AnonymousClass05Y.A00(context, attributeSet, AnonymousClass18N.A0I, i, i2);
        TypedArray typedArray = A00.A02;
        if (typedArray.hasValue(2)) {
            setOverlapAnchor(typedArray.getBoolean(2, false));
        }
        setBackgroundDrawable(A00.A02(0));
        A00.A04();
    }

    public AnonymousClass04H(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        A00(context, attributeSet, i, i2);
    }

    @Override // android.widget.PopupWindow
    public final void update(View view, int i, int i2, int i3, int i4) {
        super.update(view, i, i2, i3, i4);
    }

    public final void showAsDropDown(View view, int i, int i2) {
        super.showAsDropDown(view, i, i2);
    }

    public final void showAsDropDown(View view, int i, int i2, int i3) {
        super.showAsDropDown(view, i, i2, i3);
    }
}
