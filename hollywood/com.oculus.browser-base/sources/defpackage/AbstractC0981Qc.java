package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

/* renamed from: Qc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0981Qc extends Os1 implements AbstractC1225Uc {

    /* renamed from: J  reason: collision with root package name */
    public final AccessibilityManager f8772J;
    public AbstractC1286Vc K;
    public boolean L = true;
    public boolean M;
    public boolean N;

    public AbstractC0981Qc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8772J = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // defpackage.Os1
    public boolean bringPointIntoView(int i) {
        if (this.N) {
            return false;
        }
        return super.bringPointIntoView(i);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        AbstractC1286Vc vc = this.K;
        if (vc == null) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return vc.dispatchKeyEvent(keyEvent);
    }

    @Override // defpackage.C4011o8
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection == null && this.K == null) {
            return null;
        }
        if (this.K == null) {
            if (!AbstractC4226pO.a() || N.M09VlOh_("SpannableInlineAutocomplete")) {
                AbstractC1220Ua0.f("AutocompleteEdit", "Using spannable model...", new Object[0]);
                this.K = new IY0(this);
            } else {
                AbstractC1220Ua0.f("AutocompleteEdit", "Using non-spannable model...", new Object[0]);
                this.K = new C1164Tc(this);
            }
            this.K.k(true);
            this.K.i(hasFocus());
            this.K.j(getText());
            this.K.onTextChanged(getText(), 0, 0, getText().length());
            this.K.a(getSelectionStart(), getSelectionEnd());
            if (this.M) {
                this.K.d();
            }
            this.K.k(false);
            this.K.k(this.L);
        }
        return this.K.e(onCreateInputConnection);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        AbstractC1286Vc vc = this.K;
        if (vc != null) {
            vc.i(z);
        }
        super.onFocusChanged(z, i, rect);
        if (!z) {
            setCursorVisible(false);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        P21 f0 = P21.f0();
        try {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean onPreDraw() {
        boolean onPreDraw = super.onPreDraw();
        if (!this.N) {
            return onPreDraw;
        }
        this.N = false;
        bringPointIntoView(getSelectionStart());
        return true;
    }

    public void onSelectionChanged(int i, int i2) {
        AbstractC1286Vc vc = this.K;
        if (vc != null) {
            vc.a(i, i2);
        }
        super.onSelectionChanged(i, i2);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        this.M = false;
        AbstractC1286Vc vc = this.K;
        if (vc != null) {
            vc.onTextChanged(charSequence, i, i2, i3);
        }
    }

    @Override // defpackage.AbstractC1225Uc
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        AbstractC1286Vc vc;
        if (!((this.L || ((vc = this.K) != null && vc.g())) && (accessibilityEvent.getEventType() == 8192 || accessibilityEvent.getEventType() == 16))) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    @Override // android.widget.TextView, android.widget.EditText
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.N = false;
        P21 f0 = P21.f0();
        try {
            super.setText(charSequence, bufferType);
            f0.close();
            AbstractC1286Vc vc = this.K;
            if (vc != null) {
                vc.j(charSequence);
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
