package defpackage;

import android.content.LocusId;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureContext;
import android.view.contentcapture.ContentCaptureSession;

/* renamed from: kD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3343kD0 extends AbstractC3172jD0 {
    @Override // defpackage.AbstractC3172jD0
    public ContentCaptureSession a(ContentCaptureSession contentCaptureSession, String str) {
        return contentCaptureSession.createContentCaptureSession(new ContentCaptureContext.Builder(new LocusId(str)).build());
    }

    @Override // defpackage.AbstractC3172jD0
    public void b(ContentCaptureSession contentCaptureSession) {
        contentCaptureSession.destroy();
    }

    @Override // defpackage.AbstractC3172jD0
    public AutofillId d(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
        return contentCaptureSession.newAutofillId(autofillId, j);
    }

    @Override // defpackage.AbstractC3172jD0
    public ViewStructure e(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
        return contentCaptureSession.newVirtualViewStructure(autofillId, j);
    }

    @Override // defpackage.AbstractC3172jD0
    public void f(ContentCaptureSession contentCaptureSession, ViewStructure viewStructure) {
        contentCaptureSession.notifyViewAppeared(viewStructure);
    }

    @Override // defpackage.AbstractC3172jD0
    public void g(ContentCaptureSession contentCaptureSession, AutofillId autofillId) {
        contentCaptureSession.notifyViewDisappeared(autofillId);
    }

    @Override // defpackage.AbstractC3172jD0
    public void h(ContentCaptureSession contentCaptureSession, AutofillId autofillId, String str) {
        contentCaptureSession.notifyViewTextChanged(autofillId, str);
    }

    @Override // defpackage.AbstractC3172jD0
    public void i(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long[] jArr) {
        contentCaptureSession.notifyViewsDisappeared(autofillId, jArr);
    }
}
