package X;

import androidx.annotation.NonNull;

public class TX implements C8 {
    @Override // X.C8
    @NonNull
    public <T extends C7> T A1E(@NonNull Class<T> cls) {
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
