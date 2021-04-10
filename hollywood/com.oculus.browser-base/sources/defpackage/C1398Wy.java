package defpackage;

import android.view.autofill.AutofillId;
import org.chromium.components.content_capture.ContentCaptureData;

/* renamed from: Wy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1398Wy extends AbstractC2669gG0 {
    public C1398Wy(HT ht, ContentCaptureData contentCaptureData, C4369qD0 qd0) {
        super(ht, contentCaptureData, qd0);
    }

    @Override // defpackage.AbstractC2669gG0
    public AutofillId u(C4198pD0 pd0, ContentCaptureData contentCaptureData) {
        AutofillId d = AbstractC3172jD0.c().d(pd0.f11057a, this.k.c().b, contentCaptureData.f10831a);
        AbstractC3172jD0.c().h(pd0.f11057a, d, contentCaptureData.b);
        return d;
    }
}
