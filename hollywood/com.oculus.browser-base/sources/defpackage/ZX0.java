package defpackage;

import android.content.Context;
import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: ZX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZX0 {

    /* renamed from: a  reason: collision with root package name */
    public WindowAndroid f9349a;
    public TextClassifier b;
    public C3380kS0 c;

    public ZX0(WebContents webContents) {
        this.f9349a = webContents.I();
        Zy1 t0 = Zy1.t0(webContents);
        if (t0 != null) {
            t0.F.b(new YX0(this));
        }
    }

    public static ZX0 b(WebContents webContents) {
        if (webContents.I().f11022J.get() == null) {
            return null;
        }
        return new ZX0(webContents);
    }

    public final TextClassifier c(Context context, boolean z) {
        return ((TextClassificationManager) context.getSystemService("textclassification")).createTextClassificationSession(new TextClassificationContext.Builder(context.getPackageName(), z ? "edit-webview" : "webview").build());
    }

    public final void d() {
        TextClassifier textClassifier = this.b;
        if (textClassifier != null && !textClassifier.isDestroyed()) {
            this.b.destroy();
            this.b = null;
        }
    }

    public void e(SelectionEvent selectionEvent) {
        this.b.onSelectionEvent(selectionEvent);
    }

    public void f(String str, int i, int i2, C2355eS0 es0) {
        TextClassification textClassification;
        if (this.b != null) {
            if (!this.c.e(str, i)) {
                d();
                return;
            }
            int[] iArr = new int[2];
            if (!this.c.c(i, str.length() + i, iArr)) {
                d();
                return;
            }
            if (es0 == null || (textClassification = es0.g) == null) {
                e(SelectionEvent.createSelectionActionEvent(iArr[0], iArr[1], i2));
            } else {
                e(SelectionEvent.createSelectionActionEvent(iArr[0], iArr[1], i2, textClassification));
            }
            if (SelectionEvent.isTerminal(i2)) {
                d();
            }
        }
    }

    public void g(String str, int i, C2355eS0 es0) {
        TextClassification textClassification;
        TextSelection textSelection;
        if (this.b != null) {
            if (!this.c.e(str, i)) {
                d();
                return;
            }
            int[] iArr = new int[2];
            if (!this.c.c(i, str.length() + i, iArr)) {
                d();
            } else if (es0 != null && (textSelection = es0.h) != null) {
                e(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1], textSelection));
            } else if (es0 == null || (textClassification = es0.g) == null) {
                e(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1]));
            } else {
                e(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1], textClassification));
            }
        }
    }

    public void h(String str, int i, boolean z) {
        Context context;
        WindowAndroid windowAndroid = this.f9349a;
        if (windowAndroid != null && (context = (Context) windowAndroid.f11022J.get()) != null) {
            this.b = c(context, z);
            C3380kS0 ks0 = new C3380kS0();
            this.c = ks0;
            ks0.e(str, i);
            this.c.f = i;
            e(SelectionEvent.createSelectionStartedEvent(1, 0));
        }
    }
}
