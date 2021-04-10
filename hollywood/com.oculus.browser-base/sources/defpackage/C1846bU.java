package defpackage;

import J.N;
import android.os.Bundle;
import java.io.IOException;
import java.util.Objects;
import org.chromium.components.gcm_driver.GCMDriver;

/* renamed from: bU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1846bU extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ GCMDriver k;

    public C1846bU(GCMDriver gCMDriver, String str, String str2) {
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
            return gWVar.a(str2, bundle).getString("registration_id");
        } catch (IOException e) {
            StringBuilder i2 = AbstractC2531fV.i("GCM subscription failed for ");
            i2.append(this.i);
            i2.append(", ");
            i2.append(this.j);
            AbstractC1220Ua0.f("GCMDriver", i2.toString(), e);
            return "";
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        String str = (String) obj;
        GCMDriver gCMDriver = this.k;
        N.MOEO6cdX(gCMDriver.b, gCMDriver, this.i, str, !str.isEmpty());
    }
}
