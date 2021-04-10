package X;

import androidx.annotation.NonNull;
import java.util.concurrent.ThreadFactory;

/* renamed from: X.1eS  reason: invalid class name and case insensitive filesystem */
public class ThreadFactoryC08361eS implements ThreadFactory {
    public final Thread newThread(@NonNull Runnable runnable) {
        return new Thread(new RunnableC08351eR(this, runnable), "glide-active-resources");
    }
}
