package X;

import androidx.annotation.NonNull;

/* renamed from: X.0ri  reason: invalid class name and case insensitive filesystem */
public class C07240ri implements AnonymousClass0Ag {
    @Override // X.AnonymousClass0Ag
    @NonNull
    public <T extends AnonymousClass0Af> T A1w(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot create an instance of ");
            sb.append(cls);
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
