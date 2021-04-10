package defpackage;

import org.chromium.components.content_capture.ContentCaptureData;

/* renamed from: VS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VS0 extends AbstractC2248dq0 {
    public VS0(HT ht, C4369qD0 qd0) {
        super(ht, qd0);
    }

    @Override // defpackage.AbstractC2248dq0
    public void t() {
        q("SessionRemovedTask.removeSession");
        C4198pD0 pd0 = (C4198pD0) this.k.b().remove(Long.valueOf(((ContentCaptureData) this.j.get(0)).f10831a));
        if (pd0 != null) {
            AbstractC3172jD0.c().b(pd0.f11057a);
            C4198pD0 c = this.k.c();
            if (this.j.size() > 2) {
                c = (C4198pD0) this.k.b().get(Long.valueOf(((ContentCaptureData) this.j.get(1)).f10831a));
            }
            if (c != null) {
                AbstractC3172jD0.c().g(c.f11057a, pd0.b);
            }
        }
    }
}
