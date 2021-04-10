package X;

import androidx.annotation.NonNull;

public abstract class Zs extends Ah implements Ag {
    @NonNull
    public abstract <T extends Af> T A01(@NonNull String str, @NonNull Class<T> cls);

    @Override // X.Ag
    @NonNull
    public <T extends Af> T A1d(@NonNull Class<T> cls) {
        throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
    }
}
