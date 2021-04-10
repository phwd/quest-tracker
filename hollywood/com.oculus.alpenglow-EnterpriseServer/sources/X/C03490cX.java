package X;

import androidx.annotation.NonNull;

/* renamed from: X.0cX  reason: invalid class name and case insensitive filesystem */
public class C03490cX implements AbstractC01120Dp {
    @Override // X.AbstractC01120Dp
    @NonNull
    public <T extends AnonymousClass0Do> T A1t(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + cls, e);
        }
    }
}
