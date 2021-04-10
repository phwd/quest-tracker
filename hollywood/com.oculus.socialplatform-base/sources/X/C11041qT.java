package X;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1qT  reason: invalid class name and case insensitive filesystem */
public final class C11041qT extends CheckedTextView {
    public static final int[] A01 = {16843016};
    public final AnonymousClass1qE A00;

    public C11041qT(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass1RS.A00(context), attributeSet, 16843720);
        Context context2 = getContext();
        C10891q9.A03(this, context2);
        AnonymousClass1qE r0 = new AnonymousClass1qE(this);
        this.A00 = r0;
        r0.A0A(attributeSet, 16843720);
        this.A00.A04();
        getContext();
        C10901qA A002 = C10901qA.A00(context2, attributeSet, A01, 16843720, 0);
        setCheckMarkDrawable(A002.A02(0));
        A002.A04();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A04();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C11141qg.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(@DrawableRes int i) {
        setCheckMarkDrawable(AnonymousClass1pW.A00(getContext(), i));
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass08Y.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass1qE r0 = this.A00;
        if (r0 != null) {
            r0.A07(context, i);
        }
    }
}
