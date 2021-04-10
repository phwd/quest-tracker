package defpackage;

import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;

/* renamed from: jD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3172jD0 {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC3172jD0 f10195a;

    public static AbstractC3172jD0 c() {
        if (f10195a == null) {
            f10195a = new C3343kD0();
        }
        return f10195a;
    }

    public abstract ContentCaptureSession a(ContentCaptureSession contentCaptureSession, String str);

    public abstract void b(ContentCaptureSession contentCaptureSession);

    public abstract AutofillId d(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j);

    public abstract ViewStructure e(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j);

    public abstract void f(ContentCaptureSession contentCaptureSession, ViewStructure viewStructure);

    public abstract void g(ContentCaptureSession contentCaptureSession, AutofillId autofillId);

    public abstract void h(ContentCaptureSession contentCaptureSession, AutofillId autofillId, String str);

    public abstract void i(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long[] jArr);
}
