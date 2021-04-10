package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* renamed from: O7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O7 extends CheckedTextView {
    public static final int[] F = {16843016};
    public final M8 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public O7(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16843720);
        AbstractC0331Fi1.a(context);
        AbstractC1361Wg1.a(this, getContext());
        M8 m8 = new M8(this);
        this.G = m8;
        m8.e(attributeSet, 16843720);
        m8.b();
        C0514Ii1 q = C0514Ii1.q(getContext(), attributeSet, F, 16843720, 0);
        setCheckMarkDrawable(q.g(0));
        q.b.recycle();
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AbstractC4182p8.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(AbstractC5544x8.a(getContext(), i));
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AbstractC0751Mg1.d(this, callback));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.f(context, i);
        }
    }
}
