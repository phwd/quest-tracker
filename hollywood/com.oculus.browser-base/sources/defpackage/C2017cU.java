package defpackage;

import J.N;
import android.os.Bundle;
import java.io.IOException;
import java.util.Objects;
import org.chromium.components.gcm_driver.GCMDriver;

/* renamed from: cU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2017cU extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ GCMDriver k;

    public C2017cU(GCMDriver gCMDriver, String str, String str2) {
        this.k = gCMDriver;
        this.i = str;
        this.j = str2;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            String str = this.i;
            C2704gW gWVar = this.k.c;
            String str2 = this.j;
            Objects.requireNonNull(gWVar);
            Bundle bundle = new Bundle();
            bundle.putString("subtype", str);
            bundle.putString("delete", "1");
            gWVar.a(str2, bundle);
            return Boolean.TRUE;
        } catch (IOException e) {
            StringBuilder i2 = AbstractC2531fV.i("GCM unsubscription failed for ");
            i2.append(this.i);
            i2.append(", ");
            i2.append(this.j);
            AbstractC1220Ua0.f("GCMDriver", i2.toString(), e);
            return Boolean.FALSE;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        GCMDriver gCMDriver = this.k;
        N.MDziew73(gCMDriver.b, gCMDriver, this.i, ((Boolean) obj).booleanValue());
    }
}
