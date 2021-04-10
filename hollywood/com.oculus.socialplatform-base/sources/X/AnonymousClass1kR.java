package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1kR  reason: invalid class name */
public final class AnonymousClass1kR extends AbstractC10031kh<AnonymousClass1lI> {
    public Executor A00;
    @Nullable
    public final CacheControl A01;
    public final Call.Factory A02;

    public AnonymousClass1kR(OkHttpClient okHttpClient) {
        ExecutorService executorService = okHttpClient.dispatcher.executorService();
        this.A02 = okHttpClient;
        this.A00 = executorService;
        CacheControl.Builder builder = new CacheControl.Builder();
        builder.noStore = true;
        this.A01 = builder.build();
    }
}
