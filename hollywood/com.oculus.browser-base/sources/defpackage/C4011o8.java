package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;

/* renamed from: o8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4011o8 extends EditText {
    public final K7 F;
    public final M8 G;
    public final K8 H = new K8(this);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4011o8(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AbstractC0331Fi1.a(context);
        AbstractC1361Wg1.a(this, getContext());
        K7 k7 = new K7(this);
        this.F = k7;
        k7.d(attributeSet, i);
        M8 m8 = new M8(this);
        this.G = m8;
        m8.e(attributeSet, i);
        m8.b();
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        K7 k7 = this.F;
        if (k7 != null) {
            k7.a();
        }
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    public TextClassifier getTextClassifier() {
        K8 k8;
        if (Build.VERSION.SDK_INT >= 28 || (k8 = this.H) == null) {
            return super.getTextClassifier();
        }
        return k8.a();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AbstractC4182p8.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        K7 k7 = this.F;
        if (k7 != null) {
            k7.e();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        K7 k7 = this.F;
        if (k7 != null) {
            k7.f(i);
        }
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

    public void setTextClassifier(TextClassifier textClassifier) {
        K8 k8;
        if (Build.VERSION.SDK_INT >= 28 || (k8 = this.H) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            k8.b = textClassifier;
        }
    }

    @Override // android.widget.EditText, android.widget.EditText
    public Editable getText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return super.getText();
        }
        return super.getEditableText();
    }
}
