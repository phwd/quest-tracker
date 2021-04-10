package X;

import androidx.annotation.NonNull;

/* renamed from: X.0cY  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03500cY extends C01130Dq implements AbstractC01120Dp {
    @NonNull
    public abstract <T extends AnonymousClass0Do> T A01(@NonNull String str, @NonNull Class<T> cls);

    @Override // X.AbstractC01120Dp
    @NonNull
    public <T extends AnonymousClass0Do> T A1t(@NonNull Class<T> cls) {
        throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
    }
}
