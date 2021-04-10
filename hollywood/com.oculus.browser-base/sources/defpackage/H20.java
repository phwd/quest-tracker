package defpackage;

import J.N;
import java.io.IOException;
import org.chromium.components.gcm_driver.instance_id.InstanceIDBridge;

/* renamed from: H20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H20 extends K20 {
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ int d;
    public final /* synthetic */ InstanceIDBridge e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public H20(InstanceIDBridge instanceIDBridge, String str, String str2, int i) {
        super(instanceIDBridge, null);
        this.e = instanceIDBridge;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    @Override // defpackage.K20
    public Object a() {
        try {
            M20 m20 = this.e.d;
            m20.c.e(this.b, this.c, null);
            String a2 = S70.a(this.e.b, this.b);
            if (S70.d(a2)) {
                S70.b(a2);
            }
            AbstractC4681s31.b(AbstractC4681s31.a(this.e.b, this.b));
            return Boolean.TRUE;
        } catch (IOException unused) {
            return Boolean.FALSE;
        }
    }

    @Override // defpackage.K20
    public void c(Object obj) {
        InstanceIDBridge instanceIDBridge = this.e;
        N.MWbugtkA(instanceIDBridge.c, instanceIDBridge, this.d, ((Boolean) obj).booleanValue());
    }
}
