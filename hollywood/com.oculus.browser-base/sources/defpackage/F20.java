package defpackage;

import J.N;
import org.chromium.components.gcm_driver.instance_id.InstanceIDBridge;

/* renamed from: F20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F20 extends K20 {
    public final /* synthetic */ int b;
    public final /* synthetic */ InstanceIDBridge c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public F20(InstanceIDBridge instanceIDBridge, int i) {
        super(instanceIDBridge, null);
        this.c = instanceIDBridge;
        this.b = i;
    }

    @Override // defpackage.K20
    public Object a() {
        return Long.valueOf(D20.d.d(this.c.d.c.h).b);
    }

    @Override // defpackage.K20
    public void c(Object obj) {
        InstanceIDBridge instanceIDBridge = this.c;
        N.M1_H2CVT(instanceIDBridge.c, instanceIDBridge, this.b, ((Long) obj).longValue());
    }
}
