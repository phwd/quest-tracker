package defpackage;

import J.N;
import java.io.IOException;
import org.chromium.base.ContextUtils;
import org.chromium.components.gcm_driver.instance_id.InstanceIDBridge;

/* renamed from: G20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G20 extends K20 {
    public final /* synthetic */ String b;
    public final /* synthetic */ int c;
    public final /* synthetic */ String d;
    public final /* synthetic */ int e;
    public final /* synthetic */ InstanceIDBridge f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public G20(InstanceIDBridge instanceIDBridge, String str, int i, String str2, int i2) {
        super(instanceIDBridge, null);
        this.f = instanceIDBridge;
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = i2;
    }

    @Override // defpackage.K20
    public Object a() {
        try {
            String a2 = S70.a(this.f.b, this.b);
            boolean z = true;
            if ((this.c & 1) != 1) {
                z = false;
            }
            S70.f(a2, z);
            String a3 = AbstractC4681s31.a(this.f.b, this.b);
            int i = this.c;
            if (i == 0) {
                AbstractC4681s31.b(a3);
            } else {
                ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.subscription_flags", 0).edit().putInt(a3, i).apply();
            }
            return this.f.d.b(this.b, this.d);
        } catch (IOException unused) {
            return "";
        }
    }

    @Override // defpackage.K20
    public void c(Object obj) {
        InstanceIDBridge instanceIDBridge = this.f;
        N.MgpbhGOm(instanceIDBridge.c, instanceIDBridge, this.e, (String) obj);
    }
}
