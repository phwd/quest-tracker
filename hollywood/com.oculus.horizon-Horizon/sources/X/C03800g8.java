package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.0g8  reason: invalid class name and case insensitive filesystem */
public final class C03800g8 implements AbstractC06280mp {
    public HashMap<Class<? extends Annotation>, Annotation> A00;

    public final void A00(Annotation annotation) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A00;
        if (hashMap == null || !hashMap.containsKey(annotation.annotationType())) {
            HashMap<Class<? extends Annotation>, Annotation> hashMap2 = this.A00;
            if (hashMap2 == null) {
                hashMap2 = new HashMap<>();
                this.A00 = hashMap2;
            }
            hashMap2.put(annotation.annotationType(), annotation);
        }
    }

    public final String toString() {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A00;
        if (hashMap == null) {
            return "[null]";
        }
        return hashMap.toString();
    }

    public C03800g8() {
    }

    public C03800g8(HashMap<Class<? extends Annotation>, Annotation> hashMap) {
        this.A00 = hashMap;
    }
}
