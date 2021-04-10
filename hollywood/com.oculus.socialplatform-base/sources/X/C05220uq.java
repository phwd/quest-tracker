package X;

import androidx.annotation.NonNull;

/* renamed from: X.0uq  reason: invalid class name and case insensitive filesystem */
public class C05220uq implements AnonymousClass0Ah {
    @Override // X.AnonymousClass0Ah
    @NonNull
    public <T extends AnonymousClass0Ag> T A2L(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot create an instance of ");
            sb.append(cls);
            throw new RuntimeException(sb.toString(), e);
        } catch (IllegalAccessException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot create an instance of ");
            sb2.append(cls);
            throw new RuntimeException(sb2.toString(), e2);
        }
    }
}
