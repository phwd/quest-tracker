package X;

import androidx.annotation.NonNull;

public class Zr implements Ag {
    @Override // X.Ag
    @NonNull
    public <T extends Af> T A1d(@NonNull Class<T> cls) {
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
