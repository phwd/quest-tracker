package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import com.oculus.browser.R;

/* renamed from: s8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4694s8 extends MultiAutoCompleteTextView {
    public static final int[] F = {16843126};
    public final K7 G;
    public final M8 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4694s8(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f1830_resource_name_obfuscated_RES_2130968629);
        AbstractC0331Fi1.a(context);
        AbstractC1361Wg1.a(this, getContext());
        C0514Ii1 q = C0514Ii1.q(getContext(), attributeSet, F, R.attr.f1830_resource_name_obfuscated_RES_2130968629, 0);
        if (q.o(0)) {
            setDropDownBackgroundDrawable(q.g(0));
        }
        q.b.recycle();
        K7 k7 = new K7(this);
        this.G = k7;
        k7.d(attributeSet, R.attr.f1830_resource_name_obfuscated_RES_2130968629);
        M8 m8 = new M8(this);
        this.H = m8;
        m8.e(attributeSet, R.attr.f1830_resource_name_obfuscated_RES_2130968629);
        m8.b();
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        K7 k7 = this.G;
        if (k7 != null) {
            k7.a();
        }
        M8 m8 = this.H;
        if (m8 != null) {
            m8.b();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AbstractC4182p8.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        K7 k7 = this.G;
        if (k7 != null) {
            k7.e();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        K7 k7 = this.G;
        if (k7 != null) {
            k7.f(i);
        }
    }

    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(AbstractC5544x8.a(getContext(), i));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        M8 m8 = this.H;
        if (m8 != null) {
            m8.f(context, i);
        }
    }
}
