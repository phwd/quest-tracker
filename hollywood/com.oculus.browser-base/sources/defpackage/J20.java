package defpackage;

import org.chromium.components.gcm_driver.instance_id.InstanceIDBridge;

/* renamed from: J20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J20 extends AbstractC2032cb {
    public final /* synthetic */ K20 i;

    public J20(K20 k20) {
        this.i = k20;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        synchronized (this.i.f8338a) {
            InstanceIDBridge instanceIDBridge = this.i.f8338a;
            if (instanceIDBridge.d == null) {
                instanceIDBridge.d = M20.a(instanceIDBridge.b);
            }
        }
        return this.i.a();
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        if (!InstanceIDBridge.f10848a) {
            K20 k20 = this.i;
            if (k20.f8338a.c != 0) {
                k20.c(obj);
            }
        }
    }
}
