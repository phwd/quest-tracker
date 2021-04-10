package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.rK  reason: case insensitive filesystem */
public final class C1045rK implements Q0 {
    public HashMap A00;

    public final void A00(Annotation annotation) {
        HashMap hashMap = this.A00;
        if (hashMap == null || !hashMap.containsKey(annotation.annotationType())) {
            HashMap hashMap2 = this.A00;
            if (hashMap2 == null) {
                hashMap2 = new HashMap();
                this.A00 = hashMap2;
            }
            hashMap2.put(annotation.annotationType(), annotation);
        }
    }

    public final String toString() {
        HashMap hashMap = this.A00;
        if (hashMap == null) {
            return "[null]";
        }
        return hashMap.toString();
    }

    public C1045rK() {
    }

    public C1045rK(HashMap hashMap) {
        this.A00 = hashMap;
    }
}
