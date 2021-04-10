package org.chromium.chrome.browser.omnibox;

import J.N;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.net.MalformedURLException;
import java.net.URL;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.ui.base.Clipboard;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UrlBarApi26 extends AbstractC0981Qc {
    public boolean O;
    public int P = 3;
    public Hq1 Q;
    public Jq1 R;
    public TextWatcher S;
    public Iq1 T;
    public Callback U;
    public final GestureDetector V;
    public final ViewTreeObserver$OnGlobalLayoutListenerC2639g60 W;
    public boolean a0;
    public boolean b0;
    public MotionEvent c0;
    public boolean d0 = true;
    public boolean e0;
    public int f0;
    public int g0;
    public String h0;
    public int i0;
    public int j0;
    public float k0;
    public boolean l0;
    public boolean m0;
    public final int[] n0 = new int[2];
    public float o0;
    public int p0;
    public int q0;
    public CharSequence r0;
    public boolean s0;

    public UrlBarApi26(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(false);
        setFocusableInTouchMode(false);
        String string = Settings.Secure.getString(getContext().getContentResolver(), "default_input_method");
        if (string != null && string.contains("com.htc.android.htcime")) {
            setInputType(getInputType() | 176);
        }
        GestureDetector gestureDetector = new GestureDetector(getContext(), new Dq1(this), ThreadUtils.b());
        this.V = gestureDetector;
        gestureDetector.setOnDoubleTapListener(null);
        this.W = new ViewTreeObserver$OnGlobalLayoutListenerC2639g60(this, new Eq1(this));
        if (Build.VERSION.SDK_INT >= 26) {
            C2641g7.b(this);
        }
    }

    public final void b() {
        if (this.a0 || length() == 0) {
            setTextDirection(0);
        } else {
            setTextDirection(3);
        }
        setTextAlignment(2);
    }

    @Override // defpackage.AbstractC0981Qc, defpackage.Os1
    public boolean bringPointIntoView(int i) {
        if (!this.a0) {
            return false;
        }
        return super.bringPointIntoView(i);
    }

    public final void c() {
        Gq1[] gq1Arr;
        int i = SysUtils.isLowEndDevice() ? 1000 : 4000;
        Editable text = getText();
        int length = text.length();
        if (length <= i) {
            if (this.m0 && (gq1Arr = (Gq1[]) text.getSpans(0, length, Gq1.class)) != null && gq1Arr.length > 0) {
                for (Gq1 gq1 : gq1Arr) {
                    text.removeSpan(gq1);
                }
            }
            this.m0 = false;
            return;
        }
        this.m0 = true;
        if (text.nextSpanTransition(0, length, Gq1.class) == length) {
            int i2 = i / 2;
            text.setSpan(Gq1.F, i2, length - i2, 17);
        }
    }

    public void d(boolean z) {
        String str;
        if (this.R != null) {
            if (z) {
                c();
            }
            AbstractC1220Ua0.f("UrlBar", "Text change observed, triggering autocomplete.", new Object[0]);
            Jq1 jq1 = this.R;
            AbstractC1286Vc vc = this.K;
            String str2 = "";
            if (vc == null) {
                str = str2;
            } else {
                str = vc.f();
            }
            AbstractC1286Vc vc2 = this.K;
            if (vc2 != null) {
                str2 = vc2.b();
            }
            jq1.b(str, str2);
        }
    }

    public final void e() {
        MotionEvent motionEvent = this.c0;
        if (motionEvent != null) {
            super.onTouchEvent(motionEvent);
            this.c0 = null;
        }
    }

    public final void f() {
        if (isLayoutRequested()) {
            this.e0 = this.q0 != 0;
        } else {
            g(this.q0);
        }
    }

    public View focusSearch(int i) {
        Hq1 hq1 = this.Q;
        if (hq1 == null || i != 1 || hq1.h() == null) {
            return super.focusSearch(i);
        }
        return this.Q.h();
    }

    public final void g(int i) {
        boolean z = false;
        this.e0 = false;
        if (!this.a0) {
            Editable text = getText();
            if (TextUtils.isEmpty(text)) {
                i = 2;
            }
            setSelection(0);
            float textSize = getTextSize();
            if (getLayoutDirection() == 1) {
                z = true;
            }
            int measuredWidth = getMeasuredWidth() - (getPaddingRight() + getPaddingLeft());
            if (i == this.g0 && TextUtils.equals(text, this.h0) && measuredWidth == this.i0 && textSize == this.k0 && z == this.l0) {
                scrollTo(this.j0, getScrollY());
                return;
            }
            if (i == 1) {
                i();
            } else if (i == 2) {
                h();
            } else {
                return;
            }
            this.g0 = i;
            this.h0 = text.toString();
            this.i0 = measuredWidth;
            this.k0 = textSize;
            this.j0 = getScrollX();
            this.l0 = z;
        }
    }

    public CharSequence getAccessibilityClassName() {
        if (isEnabled()) {
            return super.getAccessibilityClassName();
        }
        return TextView.class.getName();
    }

    public int getAutofillType() {
        if (Build.VERSION.SDK_INT >= 29) {
            return 0;
        }
        return super.getAutofillType();
    }

    public final void h() {
        Editable text = getText();
        float f = 0.0f;
        if (TextUtils.isEmpty(text)) {
            if (getLayoutDirection() == 1) {
                C4271ph c = C4271ph.c();
                CharSequence hint = getHint();
                if (c.h.b(hint, 0, hint.length())) {
                    f = (float) (((int) getLayout().getPrimaryHorizontal(0)) - getMeasuredWidth());
                }
            }
        } else if (C4271ph.c().h.b(text, 0, text.length())) {
            f = Math.max(0.0f, (getLayout().getPrimaryHorizontal(text.length()) - ((float) getMeasuredWidth())) + getLayout().getPaint().measureText(text.toString()));
        }
        scrollTo((int) f, getScrollY());
    }

    public final void i() {
        float f;
        float f2;
        int i;
        Editable text = getText();
        int measuredWidth = getMeasuredWidth() - (getPaddingRight() + getPaddingLeft());
        Layout layout = getLayout();
        int min = Math.min(this.p0, text.length());
        text.length();
        float primaryHorizontal = layout.getPrimaryHorizontal(min);
        if (text.length() == 1) {
            f = 0.0f;
        } else {
            f = layout.getPrimaryHorizontal(Math.max(0, min - 1));
        }
        if (f < primaryHorizontal) {
            f2 = Math.max(0.0f, primaryHorizontal - ((float) measuredWidth));
        } else {
            int i2 = min - 1;
            int i3 = min - 2;
            while (true) {
                i = i2;
                i2 = i3;
                if (i2 >= 0) {
                    if (layout.getPrimaryHorizontal(i2) <= primaryHorizontal) {
                        i = Math.max(0, i - 1);
                        break;
                    }
                    i3 = i2 - 1;
                } else {
                    break;
                }
            }
            float measureText = layout.getPaint().measureText(text.subSequence(i, min).toString());
            float f3 = (float) measuredWidth;
            f2 = measureText < f3 ? Math.max(0.0f, (primaryHorizontal + measureText) - f3) : primaryHorizontal + f3;
        }
        scrollTo((int) f2, getScrollY());
    }

    public void j(boolean z) {
        this.L = z;
        AbstractC1286Vc vc = this.K;
        if (vc != null) {
            vc.k(z);
        }
    }

    public final boolean k() {
        getLocationInWindow(this.n0);
        return this.o0 == ((float) this.n0[1]);
    }

    public final void l() {
        Layout layout = getLayout();
        if (layout != null) {
            int i = 1;
            if (length() == 0) {
                i = 3;
            } else if (layout.getParagraphDirection(0) == 1) {
                i = 0;
            }
            if (i != this.P) {
                this.P = i;
                Callback callback = this.U;
                if (callback != null) {
                    callback.onResult(Integer.valueOf(i));
                }
                f();
            }
        }
    }

    @Override // defpackage.AbstractC0981Qc, defpackage.C4011o8
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        Hq1 hq1 = this.Q;
        if (hq1 == null || !hq1.i()) {
            editorInfo.imeOptions |= 16777216;
        }
        return onCreateInputConnection;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.O) {
            this.O = true;
            setFocusable(this.d0);
            setFocusableInTouchMode(this.d0);
        }
        l();
    }

    @Override // defpackage.AbstractC0981Qc
    public void onFocusChanged(boolean z, int i, Rect rect) {
        this.a0 = z;
        super.onFocusChanged(z, i, rect);
        if (z) {
            this.e0 = false;
        }
        b();
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (4 == i && keyEvent.getAction() == 1) {
            ViewTreeObserver$OnGlobalLayoutListenerC2639g60 g60 = this.W;
            g60.b();
            if (g60.F.getResources().getConfiguration().keyboard != 2) {
                g60.F.getViewTreeObserver().addOnGlobalLayoutListener(g60);
                g60.K = true;
                g60.L = g60.a();
                g60.F.postDelayed(g60.H, 1000);
            }
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.e0) {
            g(this.q0);
            return;
        }
        int i5 = i3 - i;
        if (this.f0 != i5) {
            g(this.q0);
            this.f0 = i5;
        }
    }

    public void onProvideAutofillStructure(ViewStructure viewStructure, int i) {
        this.s0 = true;
        super.onProvideAutofillStructure(viewStructure, i);
        this.s0 = false;
    }

    @Override // defpackage.AbstractC0981Qc
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (Build.VERSION.SDK_INT >= 29) {
            C3837n7.g(this);
        }
    }

    public boolean onTextContextMenuItem(int i) {
        Iq1 iq1 = this.T;
        if (iq1 == null) {
            return super.onTextContextMenuItem(i);
        }
        String str = null;
        int i2 = 0;
        if (i == 16908322) {
            Tq1 tq1 = (Tq1) iq1;
            Context applicationContext = ContextUtils.getApplicationContext();
            ClipData primaryClip = ((ClipboardManager) applicationContext.getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip != null) {
                StringBuilder sb = new StringBuilder();
                for (int i3 = 0; i3 < primaryClip.getItemCount(); i3++) {
                    sb.append(primaryClip.getItemAt(i3).coerceToText(applicationContext));
                }
                str = N.M14CHwRm(sb.toString());
                boolean z = ((BrowserStartupControllerImpl) AbstractC4280pk.a()).f() && N.MOCmo$He(str) != null;
                long currentTimeMillis = System.currentTimeMillis();
                long j = Clipboard.getInstance().d;
                long j2 = 0;
                if (j != 0) {
                    j2 = N.MN49cYVC(j);
                }
                long j3 = currentTimeMillis - j2;
                AbstractC3364kK0.f("MobileOmnibox.LongPressPasteAge", j3, 1, 3600000, 100);
                if (z) {
                    AbstractC3364kK0.f("MobileOmnibox.LongPressPasteAge.URL", j3, 1, 3600000, 100);
                } else {
                    AbstractC3364kK0.f("MobileOmnibox.LongPressPasteAge.TEXT", j3, 1, 3600000, 100);
                }
            }
            if (str == null) {
                return true;
            }
            int length = getText().length();
            if (isFocused()) {
                int selectionStart = getSelectionStart();
                int selectionEnd = getSelectionEnd();
                int max = Math.max(0, Math.min(selectionStart, selectionEnd));
                length = Math.max(0, Math.max(selectionStart, selectionEnd));
                i2 = max;
            }
            Selection.setSelection(getText(), length);
            getText().replace(i2, length, str);
            this.M = true;
            AbstractC1286Vc vc = this.K;
            if (vc == null) {
                return true;
            }
            vc.d();
            return true;
        } else if (i == 16908320 || i == 16908321) {
            if (i == 16908320) {
                AbstractC3535lK0.a("Omnibox.LongPress.Cut");
            } else {
                AbstractC3535lK0.a("Omnibox.LongPress.Copy");
            }
            String obj = getText().toString();
            Iq1 iq12 = this.T;
            int selectionStart2 = getSelectionStart();
            int selectionEnd2 = getSelectionEnd();
            Tq1 tq12 = (Tq1) iq12;
            Pq1 pq1 = tq12.I;
            if (!(pq1 == null || pq1.d == null || selectionStart2 != 0)) {
                String substring = obj.substring(selectionStart2, selectionEnd2);
                try {
                    URL url = new URL(tq12.I.d);
                    Pq1 pq12 = tq12.I;
                    CharSequence charSequence = pq12.f;
                    if (charSequence == null) {
                        charSequence = pq12.e;
                    }
                    String a2 = Tq1.a(charSequence.toString(), url.getHost());
                    String a3 = Tq1.a(tq12.I.d, url.getHost());
                    if (substring.startsWith(a2) && selectionEnd2 >= a2.length()) {
                        StringBuilder i4 = AbstractC2531fV.i(a3);
                        i4.append(substring.substring(a2.length()));
                        str = i4.toString();
                    }
                } catch (MalformedURLException unused) {
                }
            }
            if (str == null) {
                return super.onTextContextMenuItem(i);
            }
            j(true);
            setText(str);
            setSelection(0, str.length());
            j(false);
            boolean onTextContextMenuItem = super.onTextContextMenuItem(i);
            if (TextUtils.equals(getText(), str)) {
                j(true);
                setText(obj);
                setSelection(getText().length());
                j(false);
            }
            return onTextContextMenuItem;
        } else {
            if (i == 16908341) {
                AbstractC3535lK0.a("Omnibox.LongPress.Share");
            }
            return super.onTextContextMenuItem(i);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            getLocationInWindow(this.n0);
            this.o0 = (float) this.n0[1];
            this.b0 = !this.a0;
        }
        if (!this.a0) {
            if (motionEvent.getActionMasked() == 0) {
                this.c0 = MotionEvent.obtain(motionEvent);
            }
            this.V.onTouchEvent(motionEvent);
            return true;
        }
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            this.c0 = null;
        }
        if (this.b0 && motionEvent.getActionMasked() == 2) {
            return true;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            AbstractC1220Ua0.f("UrlBar", "Ignoring IndexOutOfBoundsException in UrlBar#onTouchEvent.", e2);
            return true;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && isFocused()) {
            post(new Fq1(this));
        }
    }

    public boolean performLongClick() {
        if (!k()) {
            return false;
        }
        e();
        return super.performLongClick();
    }

    @Override // defpackage.AbstractC0981Qc, android.widget.TextView, android.widget.EditText
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        b();
    }

    @Override // defpackage.C4011o8, defpackage.C4011o8, android.widget.EditText, android.widget.EditText, defpackage.AbstractC1225Uc
    public Editable getText() {
        if (!this.s0) {
            return super.getText();
        }
        CharSequence charSequence = this.r0;
        if (charSequence == null) {
            charSequence = "";
        }
        return new SpannableStringBuilder(charSequence);
    }
}
