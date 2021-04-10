package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: dY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2197dY0 {

    /* renamed from: a  reason: collision with root package name */
    public C4406qS0 f9789a;
    public WindowAndroid b;
    public C2026cY0 c;
    public Handler d;
    public Runnable e;

    public C2197dY0(C4406qS0 qs0, WebContents webContents) {
        this.f9789a = qs0;
        this.b = webContents.I();
        Zy1 t0 = Zy1.t0(webContents);
        if (t0 != null) {
            t0.F.b(new C1675aY0(this));
        }
        this.d = new Handler();
        this.e = new RunnableC1855bY0(this);
    }

    public final void a(int i, CharSequence charSequence, int i2, int i3) {
        TextClassifier textClassifier;
        Context context = (Context) this.b.f11022J.get();
        if (context == null) {
            textClassifier = null;
        } else {
            textClassifier = ((TextClassificationManager) context.getSystemService("textclassification")).getTextClassifier();
        }
        if (textClassifier == null || textClassifier == TextClassifier.NO_OP) {
            this.d.post(this.e);
            return;
        }
        C2026cY0 cy0 = this.c;
        if (cy0 != null) {
            cy0.b(false);
            this.c = null;
        }
        C2026cY0 cy02 = new C2026cY0(this, textClassifier, i, charSequence, i2, i3, (Context) this.b.f11022J.get());
        this.c = cy02;
        cy02.d(AbstractC2032cb.b);
    }
}
