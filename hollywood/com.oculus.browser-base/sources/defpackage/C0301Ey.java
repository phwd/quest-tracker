package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import android.view.ViewStructure;
import org.chromium.components.content_capture.ContentCaptureController;
import org.chromium.components.content_capture.ContentCaptureData;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Ey  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0301Ey extends AbstractC0240Dy {
    public C4369qD0 b;
    public final View c;

    public C0301Ey(View view, ViewStructure viewStructure, WebContents webContents) {
        super(webContents);
        this.c = view;
        if (viewStructure != null) {
            this.b = new C4369qD0(view.getContentCaptureSession(), viewStructure.getAutofillId());
        }
    }

    public static AbstractC0240Dy g(Context context, View view, ViewStructure viewStructure, WebContents webContents) {
        if (ContentCaptureController.f10830a == null) {
            C0362Fy.d(context.getApplicationContext());
        }
        if (!ContentCaptureController.f10830a.c()) {
            return null;
        }
        return new C0301Ey(view, null, webContents);
    }

    public static AbstractC0240Dy h(Context context, View view, WebContents webContents) {
        return g(context, view, null, webContents);
    }

    @Override // defpackage.AbstractC0240Dy
    public void a(HT ht, ContentCaptureData contentCaptureData) {
        if (this.b == null) {
            C4369qD0 a2 = C4369qD0.a(this.c);
            this.b = a2;
            if (a2 == null) {
                return;
            }
        }
        new C0545Iy(ht, contentCaptureData, this.b).d(AbstractC2032cb.b);
    }

    @Override // defpackage.AbstractC0240Dy
    public void b(HT ht, long[] jArr) {
        if (!ht.isEmpty() && this.b != null) {
            new C0910Oy(ht, jArr, this.b).d(AbstractC2032cb.b);
        }
    }

    @Override // defpackage.AbstractC0240Dy
    public void c(HT ht, ContentCaptureData contentCaptureData) {
        if (this.b != null) {
            new C1398Wy(ht, contentCaptureData, this.b).d(AbstractC2032cb.b);
        }
    }

    @Override // defpackage.AbstractC0240Dy
    public void d(HT ht) {
        if (!ht.isEmpty() && this.b != null) {
            new VS0(ht, this.b).d(AbstractC2032cb.b);
        }
    }

    @Override // defpackage.AbstractC0240Dy
    public boolean f(String[] strArr) {
        if (!N.MxGt0EOk()) {
            return true;
        }
        ContentCaptureController contentCaptureController = ContentCaptureController.f10830a;
        if (contentCaptureController == null) {
            return false;
        }
        return N.MntScnJN(contentCaptureController.b, contentCaptureController, strArr);
    }
}
