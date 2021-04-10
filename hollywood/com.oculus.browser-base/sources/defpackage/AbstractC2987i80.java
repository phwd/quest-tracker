package defpackage;

import J.N;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.task.PostTask;

/* renamed from: i80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2987i80 {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBoolean f10122a = new AtomicBoolean();

    public static void a() {
        N.MOXOasS5();
        boolean compareAndSet = f10122a.compareAndSet(false, true);
        if (!compareAndSet || !AbstractC1575Zv.e().g("log-native-library-residency")) {
            PostTask.b(C3070if1.e, new RunnableC2816h80(compareAndSet), 0);
        } else {
            new Thread(new RunnableC2645g80()).start();
        }
    }
}
