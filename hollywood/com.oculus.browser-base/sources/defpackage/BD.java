package defpackage;

import J.N;
import android.content.Intent;
import android.os.IBinder;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: BD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BD extends AbstractC1677aZ0 {
    public final AZ b = new AZ();

    @Override // defpackage.AbstractC1677aZ0
    public IBinder a(Intent intent) {
        AbstractC1220Ua0.d("DecoderService", "Decoder process binding", new Object[0]);
        return this.b;
    }

    @Override // defpackage.AbstractC1677aZ0
    public void b() {
        AbstractC1220Ua0.d("DecoderService", "Decoder service process started", new Object[0]);
        if (!AbstractC1575Zv.i()) {
            AbstractC1575Zv.h(null);
        }
        PostTask.e(Zo1.f9374a, new AD());
        C2474f80.f9900a.b();
        AZ az = this.b;
        Objects.requireNonNull(az);
        N.Mw4AD5hY();
        az.b = true;
        AbstractC1220Ua0.d("DecoderService", "Decoder service process initialized", new Object[0]);
    }
}
