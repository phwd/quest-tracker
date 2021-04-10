package defpackage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.chromium.components.gcm_driver.instance_id.InstanceIDBridge;

/* renamed from: K20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class K20 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ InstanceIDBridge f8338a;

    public K20(InstanceIDBridge instanceIDBridge, E20 e20) {
        this.f8338a = instanceIDBridge;
    }

    public abstract Object a();

    public void b() {
        J20 j20 = new J20(this);
        Executor executor = AbstractC2032cb.f9616a;
        j20.f();
        ((ExecutorC1463Ya) executor).execute(j20.e);
        if (InstanceIDBridge.f10848a) {
            try {
                c(j20.g());
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public abstract void c(Object obj);
}
