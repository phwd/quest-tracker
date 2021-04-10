package defpackage;

import android.view.autofill.AutofillId;
import org.chromium.components.content_capture.ContentCaptureData;

/* renamed from: gG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2669gG0 extends AbstractC2248dq0 {
    public final ContentCaptureData l;

    public AbstractC2669gG0(HT ht, ContentCaptureData contentCaptureData, C4369qD0 qd0) {
        super(ht, qd0);
        this.l = contentCaptureData;
    }

    @Override // defpackage.AbstractC2248dq0
    public void t() {
        q("ProcessContentTaskBase.processContent");
        C4198pD0 m = m();
        if (m != null) {
            v(m, this.l);
        }
    }

    public abstract AutofillId u(C4198pD0 pd0, ContentCaptureData contentCaptureData);

    public final boolean v(C4198pD0 pd0, ContentCaptureData contentCaptureData) {
        C4198pD0 pd02;
        if (contentCaptureData == null) {
            return false;
        }
        if (!contentCaptureData.a()) {
            return u(pd0, contentCaptureData) != null;
        }
        if (contentCaptureData.b != null) {
            pd02 = n(pd0, contentCaptureData);
            if (pd02 == null) {
                return false;
            }
        } else {
            AutofillId u = u(pd0, contentCaptureData);
            if (u == null) {
                return false;
            }
            pd02 = new C4198pD0(pd0.f11057a, u);
        }
        for (ContentCaptureData contentCaptureData2 : contentCaptureData.d) {
            if (!v(pd02, contentCaptureData2)) {
                return false;
            }
        }
        return true;
    }
}
