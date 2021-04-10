package org.chromium.content.browser.input;

import J.N;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.SuggestionSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ImeAdapterImpl extends Vy1 implements Qr1, Q10 {
    public long F;
    public S10 G;
    public Abstractinputmethod.InputConnectionC4139ou H;
    public C0145Ch1 I;

    /* renamed from: J  reason: collision with root package name */
    public ShowKeyboardResultReceiver f10927J;
    public final WebContentsImpl K;
    public ViewAndroidDelegate L;
    public CB M;
    public final List N = new ArrayList();
    public int O = 0;
    public int P;
    public int Q = 0;
    public int R = 0;
    public boolean S;
    public boolean T;
    public final Rect U = new Rect();
    public Configuration V;
    public int W;
    public int X;
    public String Y;
    public int Z;
    public int a0;
    public boolean b0;
    public boolean c0;
    public boolean d0;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ShowKeyboardResultReceiver extends ResultReceiver {
        public final WeakReference F;

        public ShowKeyboardResultReceiver(ImeAdapterImpl imeAdapterImpl, Handler handler) {
            super(handler);
            this.F = new WeakReference(imeAdapterImpl);
        }

        public void onReceiveResult(int i, Bundle bundle) {
            ImeAdapterImpl imeAdapterImpl = (ImeAdapterImpl) this.F.get();
            if (imeAdapterImpl != null) {
                View t0 = imeAdapterImpl.t0();
                if (i == 2) {
                    t0.getWindowVisibleDisplayFrame(imeAdapterImpl.U);
                } else if (AbstractC4656rv1.e(t0) && i == 0) {
                    imeAdapterImpl.K.y0();
                }
            }
        }
    }

    public ImeAdapterImpl(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.K = webContentsImpl;
        this.L = webContentsImpl.F();
        S10 s10 = new S10(ContextUtils.getApplicationContext(), webContentsImpl.I(), this);
        this.V = new Configuration(t0().getResources().getConfiguration());
        this.M = new CB(s10, new TZ(this), new BB());
        this.G = s10;
        this.F = N.MhbsQh1H(this, webContentsImpl);
        Zy1.t0(webContentsImpl).r0(this);
    }

    public static ImeAdapterImpl s0(WebContents webContents) {
        return (ImeAdapterImpl) ((WebContentsImpl) webContents).v0(ImeAdapterImpl.class, VZ.f9091a);
    }

    public boolean A0(CharSequence charSequence, int i, boolean z, int i2) {
        if (!v0()) {
            return false;
        }
        w0();
        long uptimeMillis = SystemClock.uptimeMillis();
        N.M1qwlrOP(this.F, this, null, 7, 0, uptimeMillis, 229, 0, false, i2);
        if (z) {
            N.Mb6t43di(this.F, this, charSequence, charSequence.toString(), i);
        } else {
            N.Mlslst_P(this.F, this, charSequence, charSequence.toString(), i);
        }
        N.M1qwlrOP(this.F, this, null, 9, 0, uptimeMillis, 229, 0, false, i2);
        return true;
    }

    public boolean B0(KeyEvent keyEvent) {
        int i;
        int i2 = 0;
        if (!v0()) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action == 0) {
            i = 8;
        } else if (action != 1) {
            return false;
        } else {
            i = 9;
        }
        for (WZ wz : this.N) {
            wz.d(keyEvent);
        }
        w0();
        long j = this.F;
        int metaState = keyEvent.getMetaState();
        if ((metaState & 1) != 0) {
            i2 = 1;
        }
        if ((metaState & 2) != 0) {
            i2 |= 4;
        }
        if ((metaState & 4096) != 0) {
            i2 |= 2;
        }
        if ((1048576 & metaState) != 0) {
            i2 |= 512;
        }
        return N.M1qwlrOP(j, this, keyEvent, i, (metaState & 2097152) != 0 ? i2 | 1024 : i2, keyEvent.getEventTime(), keyEvent.getKeyCode(), keyEvent.getScanCode(), false, keyEvent.getUnicodeChar());
    }

    public void C0(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        B0(new KeyEvent(uptimeMillis, uptimeMillis, 0, i, 0, 0, -1, 0, i2));
        B0(new KeyEvent(uptimeMillis, uptimeMillis, 1, i, 0, 0, -1, 0, i2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void D0() {
        /*
        // Method dump skipped, instructions count: 145
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.ImeAdapterImpl.D0():void");
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void S(boolean z, boolean z2) {
        C0023Ah1 ah1;
        if (!z && z2) {
            y0();
        }
        C0145Ch1 ch1 = this.I;
        if (ch1 != null) {
            if (!z && (ah1 = ch1.d) != null) {
                ah1.a();
            }
            C0267Eh1 eh1 = ch1.b;
            if (eh1 != null) {
                eh1.H.set(z);
            }
            if (ch1.g != 1) {
                ch1.g = 0;
            } else if (z) {
                ch1.g = 2;
            }
        }
    }

    public final void cancelComposition() {
        if (this.H != null) {
            z0();
        }
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    public final void focusedNodeChanged(boolean z) {
        CB cb = this.M;
        if (cb != null) {
            cb.f7792a = z;
            cb.d = null;
            cb.e = false;
            cb.n = null;
        }
        if (this.O != 0 && this.H != null && z) {
            this.b0 = true;
        }
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onAttachedToWindow() {
        C0267Eh1 eh1;
        C0145Ch1 ch1 = this.I;
        if (ch1 != null && (eh1 = ch1.b) != null) {
            eh1.f7975J.set(eh1.G.getWindowToken());
            eh1.K.set(eh1.G.getRootView());
        }
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onConfigurationChanged(Configuration configuration) {
        if (v0()) {
            Configuration configuration2 = this.V;
            if (configuration2.keyboard != configuration.keyboard || configuration2.keyboardHidden != configuration.keyboardHidden || configuration2.hardKeyboardHidden != configuration.hardKeyboardHidden) {
                this.V = new Configuration(configuration);
                boolean z = false;
                if ((this.O == 0 || this.Q == 1) ? false : true) {
                    z0();
                    D0();
                } else if (r0()) {
                    z0();
                    if (this.V.keyboard != 1) {
                        z = true;
                    }
                    if (!z) {
                        u0();
                    } else {
                        D0();
                    }
                }
            }
        }
    }

    public final void onConnectedToRenderProcess() {
        this.c0 = true;
        if (this.I == null) {
            this.I = new C0145Ch1(this.G);
        }
        y0();
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onDetachedFromWindow() {
        y0();
        C0145Ch1 ch1 = this.I;
        if (ch1 != null) {
            C0023Ah1 ah1 = ch1.d;
            if (ah1 != null) {
                ah1.a();
            }
            C0267Eh1 eh1 = ch1.b;
            if (eh1 != null) {
                eh1.f7975J.set(null);
                eh1.K.set(null);
            }
            ch1.c = null;
        }
    }

    public final void onNativeDestroyed() {
        y0();
        this.F = 0;
        this.c0 = false;
        CB cb = this.M;
        if (cb != null) {
            cb.f7792a = false;
            cb.d = null;
            cb.e = false;
            cb.n = null;
        }
    }

    public final void onResizeScrollableViewport(boolean z) {
        if (!z) {
            this.U.setEmpty();
        } else if (!this.U.isEmpty()) {
            Rect rect = new Rect();
            t0().getWindowVisibleDisplayFrame(rect);
            if (!rect.equals(this.U)) {
                if (rect.width() == this.U.width()) {
                    this.K.y0();
                }
                this.U.setEmpty();
            }
        }
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onWindowFocusChanged(boolean z) {
        C0023Ah1 ah1;
        C0145Ch1 ch1 = this.I;
        if (ch1 != null) {
            if (!z && (ah1 = ch1.d) != null) {
                ah1.a();
            }
            C0267Eh1 eh1 = ch1.b;
            if (eh1 != null) {
                eh1.I.set(z);
            }
            if (!z) {
                ch1.g = 1;
            } else if (!z || ch1.g != 2) {
                ch1.g = 0;
            } else {
                ch1.g = 3;
            }
        }
    }

    public final void populateImeTextSpansFromJava(CharSequence charSequence, long j) {
        String[] strArr;
        if (charSequence instanceof SpannableString) {
            SpannableString spannableString = (SpannableString) charSequence;
            CharacterStyle[] characterStyleArr = (CharacterStyle[]) spannableString.getSpans(0, charSequence.length(), CharacterStyle.class);
            for (CharacterStyle characterStyle : characterStyleArr) {
                int spanFlags = spannableString.getSpanFlags(characterStyle);
                if (characterStyle instanceof BackgroundColorSpan) {
                    N.MqqhDONa(j, spannableString.getSpanStart(characterStyle), spannableString.getSpanEnd(characterStyle), ((BackgroundColorSpan) characterStyle).getBackgroundColor());
                } else if (characterStyle instanceof UnderlineSpan) {
                    N.MFfRzF$Z(j, spannableString.getSpanStart(characterStyle), spannableString.getSpanEnd(characterStyle));
                } else if (characterStyle instanceof SuggestionSpan) {
                    SuggestionSpan suggestionSpan = (SuggestionSpan) characterStyle;
                    boolean z = (spanFlags & 256) != 0;
                    boolean z2 = (suggestionSpan.getFlags() & 1) != 0;
                    boolean z3 = (suggestionSpan.getFlags() & 2) != 0;
                    boolean z4 = (suggestionSpan.getFlags() & 4) != 0;
                    if (z2 || z3 || z4) {
                        int i = -2000107320;
                        try {
                            i = ((Integer) SuggestionSpan.class.getMethod("getUnderlineColor", new Class[0]).invoke(suggestionSpan, new Object[0])).intValue();
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                        }
                        int alpha = (16777215 & i) + (((int) (((float) Color.alpha(i)) * 0.4f)) << 24);
                        int spanStart = spannableString.getSpanStart(suggestionSpan);
                        int spanEnd = spannableString.getSpanEnd(suggestionSpan);
                        boolean z5 = z3 || z4;
                        if (z4) {
                            strArr = new String[0];
                        } else {
                            strArr = suggestionSpan.getSuggestions();
                        }
                        N.M$b45Vvn(j, spanStart, spanEnd, z5, z, i, alpha, strArr);
                    }
                }
            }
        }
    }

    public final boolean r0() {
        return this.O != 0;
    }

    public final void setCharacterBounds(float[] fArr) {
        CB cb = this.M;
        if (cb != null) {
            View t0 = t0();
            if (cb.f7792a && !Arrays.equals(fArr, cb.d)) {
                cb.n = null;
                cb.d = fArr;
                if (cb.e) {
                    cb.b(t0);
                }
            }
        }
    }

    public final View t0() {
        return this.L.getContainerView();
    }

    public final void u0() {
        Abstractinputmethod.InputConnectionC4139ou ouVar;
        if (v0()) {
            View containerView = this.L.getContainerView();
            if (this.G.c(containerView)) {
                S10 s10 = this.G;
                IBinder windowToken = containerView.getWindowToken();
                s10.d = null;
                StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                try {
                    InputMethodManager b = s10.b();
                    if (b != null) {
                        b.hideSoftInputFromWindow(windowToken, 0, null);
                    }
                } finally {
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                }
            }
            if (!r0() && (ouVar = this.H) != null) {
                z0();
                ((C5464wh1) ouVar).h();
            }
        }
    }

    public final void updateFrameInfo(float f, float f2, boolean z, boolean z2, float f3, float f4, float f5) {
        CB cb = this.M;
        if (cb != null) {
            View t0 = t0();
            if (cb.f7792a) {
                BB bb = cb.t;
                int[] iArr = cb.p;
                Objects.requireNonNull(bb);
                t0.getLocationOnScreen(iArr);
                int[] iArr2 = cb.p;
                float f6 = (float) iArr2[0];
                float f7 = ((float) iArr2[1]) + f2;
                if (!(cb.e && f == cb.f && f6 == cb.g && f7 == cb.h && z == cb.i && z2 == cb.j && f3 == cb.k && f4 == cb.l && f5 == cb.m)) {
                    cb.n = null;
                    cb.e = true;
                    cb.f = f;
                    cb.g = f6;
                    cb.h = f7;
                    cb.i = z;
                    cb.j = z2;
                    cb.k = f3;
                    cb.l = f4;
                    cb.m = f5;
                }
                if (cb.b || (cb.c && cb.n == null)) {
                    cb.b(t0);
                }
            }
        }
    }

    public final void updateOnTouchDown() {
        this.U.setEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        if (r19 == 0) goto L_0x0055;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005a A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0064 A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0066 A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007b A[Catch:{ all -> 0x0131 }, LOOP:0: B:39:0x0075->B:41:0x007b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ae A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00af A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00bf A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00cc A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00f0 A[Catch:{ all -> 0x0131 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateState(int r19, int r20, int r21, int r22, boolean r23, boolean r24, java.lang.String r25, int r26, int r27, int r28, int r29, boolean r30, int r31, int r32, boolean r33) {
        /*
        // Method dump skipped, instructions count: 310
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.ImeAdapterImpl.updateState(int, int, int, int, boolean, boolean, java.lang.String, int, int, int, int, boolean, int, int, boolean):void");
    }

    public final boolean v0() {
        return this.F != 0 && this.c0;
    }

    public final void w0() {
        for (WZ wz : this.N) {
            wz.a();
        }
        if (this.S && this.K.s() != null) {
            RenderWidgetHostViewImpl w0 = this.K.s();
            if (!w0.a()) {
                N.MQWja$xA(w0.f10916a, w0);
            }
        }
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void x(WindowAndroid windowAndroid) {
        S10 s10 = this.G;
        if (s10 != null) {
            s10.b = windowAndroid;
        }
    }

    public boolean x0(int i) {
        if (!v0()) {
            return false;
        }
        if (this.R == 0) {
            if (i == 5) {
                long j = this.F;
                if (j != 0) {
                    N.Mm_z91JF(j, this, 1);
                }
                return true;
            } else if (i == 7) {
                long j2 = this.F;
                if (j2 != 0) {
                    N.Mm_z91JF(j2, this, 2);
                }
                return true;
            }
        }
        C0(66, 22);
        return true;
    }

    public void y0() {
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.b0 = false;
        u0();
    }

    public void z0() {
        if (v0()) {
            S10 s10 = this.G;
            View t0 = t0();
            InputMethodManager b = s10.b();
            if (b != null) {
                b.restartInput(t0);
            }
            Abstractinputmethod.InputConnectionC4139ou ouVar = this.H;
            if (ouVar != null) {
                Objects.requireNonNull((C5464wh1) ouVar);
            }
        }
    }
}
