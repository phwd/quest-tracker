package X;

import androidx.annotation.NonNull;

/* renamed from: X.0rj  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07250rj extends C00500Ah implements AnonymousClass0Ag {
    @NonNull
    public abstract <T extends AnonymousClass0Af> T A01(@NonNull String str, @NonNull Class<T> cls);

    @Override // X.AnonymousClass0Ag
    @NonNull
    public <T extends AnonymousClass0Af> T A1w(@NonNull Class<T> cls) {
        throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
    }
}
