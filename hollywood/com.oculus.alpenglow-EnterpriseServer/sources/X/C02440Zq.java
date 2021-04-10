package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.0Zq  reason: invalid class name and case insensitive filesystem */
public final class C02440Zq implements AbstractC07080oh {
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

    public C02440Zq() {
    }

    public C02440Zq(HashMap<Class<? extends Annotation>, Annotation> hashMap) {
        this.A00 = hashMap;
    }
}
