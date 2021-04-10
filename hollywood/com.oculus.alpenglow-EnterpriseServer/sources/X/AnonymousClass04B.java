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

/* renamed from: X.04B  reason: invalid class name */
public final class AnonymousClass04B extends CheckedTextView {
    public static final int[] A01 = {16843016};
    public final AnonymousClass04U A00;

    public AnonymousClass04B(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass17C.A00(context), attributeSet, 16843720);
        Context context2 = getContext();
        AnonymousClass05V.A03(this, context2);
        AnonymousClass04U r0 = new AnonymousClass04U(this);
        this.A00 = r0;
        r0.A0C(attributeSet, 16843720);
        this.A00.A06();
        getContext();
        AnonymousClass05Y A002 = AnonymousClass05Y.A00(context2, attributeSet, A01, 16843720, 0);
        setCheckMarkDrawable(A002.A02(0));
        A002.A04();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04U r0 = this.A00;
        if (r0 != null) {
            r0.A06();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AnonymousClass04F.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(@DrawableRes int i) {
        setCheckMarkDrawable(AnonymousClass17E.A00(getContext(), i));
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass0Bq.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass04U r0 = this.A00;
        if (r0 != null) {
            r0.A09(context, i);
        }
    }
}
