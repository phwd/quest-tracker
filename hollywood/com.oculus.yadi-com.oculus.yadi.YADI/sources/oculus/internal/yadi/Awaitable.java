package oculus.internal.yadi;

import android.os.Bundle;
import com.oculus.os.yadi.RemoteResource;
import java.util.concurrent.CompletableFuture;

public class Awaitable<K, T> {
    public final CompletableFuture<T> future = new CompletableFuture<>();
    public final K name;

    public Awaitable(K k) {
        this.name = k;
    }

    public static Yadi Yadi(RemoteResource remoteResource) {
        return new Yadi(remoteResource);
    }

    public static class Yadi extends Awaitable<RemoteResource, Bundle> {
        public Yadi(RemoteResource remoteResource) {
            super(remoteResource);
        }
    }
}
