package defpackage;

import J.N;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputMethodManager;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: wh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5464wh1 extends BaseInputConnection implements Abstractinputmethod.InputConnectionC4139ou {

    /* renamed from: a  reason: collision with root package name */
    public static final C0325Fg1 f11563a = new C3931nh1("", new SJ0(0, 0), new SJ0(-1, -1), false, false);
    public final Runnable b = new RunnableC4102oh1(this);
    public final Runnable c = new RunnableC4273ph1(this);
    public final Runnable d = new RunnableC4444qh1(this);
    public final Runnable e = new RunnableC4614rh1(this);
    public final ImeAdapterImpl f;
    public final Handler g;
    public int h;
    public final BlockingQueue i = new LinkedBlockingQueue();
    public int j;
    public C0325Fg1 k;
    public int l;
    public boolean m;

    public C5464wh1(View view, ImeAdapterImpl imeAdapterImpl, Handler handler) {
        super(view, true);
        XZ.a();
        this.f = imeAdapterImpl;
        this.g = handler;
    }

    public static void a(C5464wh1 wh1) {
        ImeAdapterImpl imeAdapterImpl = wh1.f;
        if (imeAdapterImpl.v0()) {
            N.M_V5g5ie(imeAdapterImpl.F, imeAdapterImpl);
        }
    }

    public final void b(C0325Fg1 fg1) {
        XZ.a();
        try {
            this.i.put(fg1);
        } catch (InterruptedException e2) {
            AbstractC1220Ua0.a("Ime", "addToQueueOnUiThread interrupted", e2);
        }
    }

    public boolean beginBatchEdit() {
        c();
        c();
        this.h++;
        return true;
    }

    public final void c() {
        if (!(this.g.getLooper() == Looper.myLooper())) {
            throw new AssertionError();
        }
    }

    public boolean clearMetaKeyStates(int i2) {
        return false;
    }

    public void closeConnection() {
    }

    public boolean commitCompletion(CompletionInfo completionInfo) {
        return false;
    }

    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        return false;
    }

    public boolean commitText(CharSequence charSequence, int i2) {
        if (charSequence == null) {
            return false;
        }
        if (TextUtils.equals(charSequence, "\n")) {
            beginBatchEdit();
            commitText("", 1);
            PostTask.b(Zo1.f9374a, new RunnableC5294vh1(this), 0);
            endBatchEdit();
            return true;
        }
        PostTask.b(Zo1.f9374a, new RunnableC2223dh1(this, charSequence, i2), 0);
        f();
        return true;
    }

    public final void d(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.appendCodePoint(i2);
        this.f.A0(sb.toString(), 1, true, 0);
        this.j = i3;
    }

    public boolean deleteSurroundingText(int i2, int i3) {
        PostTask.b(Zo1.f9374a, new RunnableC2735gh1(this, i2, i3), 0);
        return true;
    }

    public boolean deleteSurroundingTextInCodePoints(int i2, int i3) {
        PostTask.b(Zo1.f9374a, new RunnableC2906hh1(this, i2, i3), 0);
        return true;
    }

    public final ExtractedText e(C0325Fg1 fg1) {
        if (fg1 == null) {
            return null;
        }
        ExtractedText extractedText = new ExtractedText();
        CharSequence charSequence = fg1.f8031a;
        extractedText.text = charSequence;
        extractedText.partialEndOffset = charSequence.length();
        extractedText.partialStartOffset = -1;
        SJ0 sj0 = fg1.b;
        extractedText.selectionStart = sj0.f8889a;
        extractedText.selectionEnd = sj0.b;
        extractedText.flags = fg1.d ? 1 : 0;
        return extractedText;
    }

    public boolean endBatchEdit() {
        c();
        int i2 = this.h;
        if (i2 == 0) {
            return false;
        }
        int i3 = i2 - 1;
        this.h = i3;
        if (i3 == 0) {
            i(g());
        }
        return this.h != 0;
    }

    public final void f() {
        PostTask.b(Zo1.f9374a, this.d, 0);
    }

    public boolean finishComposingText() {
        PostTask.b(Zo1.f9374a, this.e, 0);
        return true;
    }

    public final C0325Fg1 g() {
        boolean z = false;
        if (ThreadUtils.i()) {
            AbstractC1220Ua0.f("Ime", "InputConnection API is not called on IME thread. Returning cached result.", new Object[0]);
            return this.k;
        }
        c();
        PostTask.b(Zo1.f9374a, this.c, 0);
        c();
        while (true) {
            try {
                C0325Fg1 fg1 = (C0325Fg1) this.i.take();
                Objects.requireNonNull(fg1);
                if (fg1 instanceof C3931nh1) {
                    return null;
                }
                if (!fg1.e) {
                    z = true;
                } else if (!z) {
                    return fg1;
                } else {
                    i(fg1);
                    return fg1;
                }
            } catch (InterruptedException e2) {
                AbstractC0754Mh1.f8495a.b(e2);
                throw new AssertionError();
            }
        }
    }

    public int getCursorCapsMode(int i2) {
        C0325Fg1 g2 = g();
        if (g2 != null) {
            return TextUtils.getCapsMode(g2.f8031a, g2.b.f8889a, i2);
        }
        return 0;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i2) {
        c();
        boolean z = true;
        int i3 = 0;
        if ((i2 & 1) <= 0) {
            z = false;
        }
        this.m = z;
        if (z) {
            if (extractedTextRequest != null) {
                i3 = extractedTextRequest.token;
            }
            this.l = i3;
        }
        return e(g());
    }

    public Handler getHandler() {
        return this.g;
    }

    public CharSequence getSelectedText(int i2) {
        SJ0 sj0;
        int i3;
        int i4;
        C0325Fg1 g2 = g();
        if (g2 == null || (i3 = (sj0 = g2.b).f8889a) == (i4 = sj0.b)) {
            return null;
        }
        return TextUtils.substring(g2.f8031a, i3, i4);
    }

    public CharSequence getTextAfterCursor(int i2, int i3) {
        C0325Fg1 g2 = g();
        if (g2 == null) {
            return null;
        }
        int max = Math.max(0, Math.min(i2, g2.f8031a.length() - g2.b.b));
        CharSequence charSequence = g2.f8031a;
        return TextUtils.substring(charSequence, g2.b.b, Math.min(charSequence.length(), g2.b.b + max));
    }

    public CharSequence getTextBeforeCursor(int i2, int i3) {
        C0325Fg1 g2 = g();
        if (g2 == null) {
            return null;
        }
        return TextUtils.substring(g2.f8031a, Math.max(0, g2.b.f8889a - Math.max(0, Math.min(i2, g2.b.f8889a))), g2.b.f8889a);
    }

    public void h() {
        XZ.a();
        b(f11563a);
        this.g.post(this.b);
    }

    public final void i(C0325Fg1 fg1) {
        if (fg1 != null) {
            c();
            if (this.h == 0) {
                SJ0 sj0 = fg1.b;
                SJ0 sj02 = fg1.c;
                if (this.m) {
                    ExtractedText e2 = e(fg1);
                    ImeAdapterImpl imeAdapterImpl = this.f;
                    int i2 = this.l;
                    S10 s10 = imeAdapterImpl.G;
                    View t0 = imeAdapterImpl.t0();
                    InputMethodManager b2 = s10.b();
                    if (b2 != null) {
                        b2.updateExtractedText(t0, i2, e2);
                    }
                }
                PostTask.b(Zo1.f9374a, new RunnableC2052ch1(this, sj0, sj02), 0);
            }
        }
    }

    public boolean performContextMenuAction(int i2) {
        PostTask.b(Zo1.f9374a, new RunnableC2564fh1(this, i2), 0);
        return true;
    }

    public boolean performEditorAction(int i2) {
        PostTask.b(Zo1.f9374a, new RunnableC2393eh1(this, i2), 0);
        return true;
    }

    public boolean performPrivateCommand(String str, Bundle bundle) {
        PostTask.b(Zo1.f9374a, new RunnableC3589lh1(this, str, bundle), 0);
        return true;
    }

    public boolean reportFullscreenMode(boolean z) {
        return false;
    }

    public boolean requestCursorUpdates(int i2) {
        PostTask.b(Zo1.f9374a, new RunnableC3760mh1(this, i2), 0);
        return true;
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        PostTask.b(Zo1.f9374a, new RunnableC3076ih1(this, keyEvent), 0);
        f();
        return true;
    }

    public boolean setComposingRegion(int i2, int i3) {
        PostTask.b(Zo1.f9374a, new RunnableC3418kh1(this, i2, i3), 0);
        return true;
    }

    public boolean setComposingText(CharSequence charSequence, int i2) {
        if (charSequence == null) {
            return false;
        }
        PostTask.b(Zo1.f9374a, new RunnableC5124uh1(this, charSequence, i2, false), 0);
        f();
        return true;
    }

    public boolean setSelection(int i2, int i3) {
        PostTask.b(Zo1.f9374a, new RunnableC3247jh1(this, i2, i3), 0);
        return true;
    }
}
