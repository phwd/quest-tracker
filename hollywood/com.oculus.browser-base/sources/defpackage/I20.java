package defpackage;

import J.N;
import java.io.IOException;
import java.util.Objects;
import org.chromium.components.gcm_driver.instance_id.InstanceIDBridge;

/* renamed from: I20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I20 extends K20 {
    public final /* synthetic */ int b;
    public final /* synthetic */ InstanceIDBridge c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public I20(InstanceIDBridge instanceIDBridge, int i) {
        super(instanceIDBridge, null);
        this.c = instanceIDBridge;
        this.b = i;
    }

    @Override // defpackage.K20
    public Object a() {
        try {
            M20 m20 = this.c.d;
            Objects.requireNonNull(m20);
            synchronized (M20.b) {
                M20.f8458a.remove(m20.c.h);
                m20.c.a();
            }
            return Boolean.TRUE;
        } catch (IOException unused) {
            return Boolean.FALSE;
        }
    }

    @Override // defpackage.K20
    public void c(Object obj) {
        InstanceIDBridge instanceIDBridge = this.c;
        N.MB$4Dsst(instanceIDBridge.c, instanceIDBridge, this.b, ((Boolean) obj).booleanValue());
    }
}
