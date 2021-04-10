package X;

import android.text.TextUtils;
import com.bumptech.glide.load.model.LazyHeaderFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.1Kj  reason: invalid class name */
public final class AnonymousClass1Kj implements AnonymousClass1Kl {
    public final Map<String, List<LazyHeaderFactory>> A00;
    public volatile Map<String, String> A01;

    @Override // X.AnonymousClass1Kl
    public final Map<String, String> A43() {
        if (this.A01 == null) {
            synchronized (this) {
                if (this.A01 == null) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.A00.entrySet()) {
                        List<LazyHeaderFactory> value = entry.getValue();
                        StringBuilder sb = new StringBuilder();
                        int size = value.size();
                        for (int i = 0; i < size; i++) {
                            String str = value.get(i).A00;
                            if (!TextUtils.isEmpty(str)) {
                                sb.append(str);
                                if (i != value.size() - 1) {
                                    sb.append(',');
                                }
                            }
                        }
                        String obj = sb.toString();
                        if (!TextUtils.isEmpty(obj)) {
                            hashMap.put(entry.getKey(), obj);
                        }
                    }
                    this.A01 = Collections.unmodifiableMap(hashMap);
                }
            }
        }
        return this.A01;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AnonymousClass1Kj) {
            return this.A00.equals(((AnonymousClass1Kj) obj).A00);
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LazyHeaders{headers=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1Kj(Map<String, List<LazyHeaderFactory>> map) {
        this.A00 = Collections.unmodifiableMap(map);
    }
}
