package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AP {
    @NonNull
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP})
    public AtomicReference<Object> A00 = new AtomicReference<>();

    @NonNull
    @MainThread
    public abstract AO A05();

    @MainThread
    public abstract void A06(@NonNull AQ aq);

    @MainThread
    public abstract void A07(@NonNull AQ aq);
}
