package X;

import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.21x  reason: invalid class name and case insensitive filesystem */
public interface AbstractC141321x {
    AnonymousClass228 A4V();

    void A5c(C142422m v);

    int A8i(String str, byte[] bArr, Integer num, @Nullable AnonymousClass22C v, @Nullable VrMsysMqttClientCallbacks.AnonymousClass1.AnonymousClass1 v2);

    void destroy();

    void start();

    void stop();
}
