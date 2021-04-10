package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1fG  reason: invalid class name and case insensitive filesystem */
public final class C08771fG {
    public Class<?> A00;
    public Class<?> A01;
    public Class<?> A02;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                C08771fG r5 = (C08771fG) obj;
                if (!this.A00.equals(r5.A00) || !this.A01.equals(r5.A01) || !C08381eW.A07(this.A02, r5.A02)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        int hashCode = ((this.A00.hashCode() * 31) + this.A01.hashCode()) * 31;
        Class<?> cls = this.A02;
        if (cls != null) {
            i = cls.hashCode();
        } else {
            i = 0;
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MultiClassKey{first=");
        sb.append(this.A00);
        sb.append(", second=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public C08771fG() {
    }

    public C08771fG(@NonNull Class<?> cls, @NonNull Class<?> cls2, @Nullable Class<?> cls3) {
        this.A00 = cls;
        this.A01 = cls2;
        this.A02 = cls3;
    }
}
