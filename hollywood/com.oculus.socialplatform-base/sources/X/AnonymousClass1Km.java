package X;

import android.text.TextUtils;
import com.bumptech.glide.load.model.LazyHeaderFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.1Km  reason: invalid class name */
public final class AnonymousClass1Km {
    public static final String A01;
    public static final Map<String, List<LazyHeaderFactory>> A02;
    public Map<String, List<LazyHeaderFactory>> A00 = A02;

    static {
        String property = System.getProperty("http.agent");
        if (!TextUtils.isEmpty(property)) {
            int length = property.length();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                char charAt = property.charAt(i);
                if (charAt <= 31) {
                    if (charAt == '\t') {
                    }
                    charAt = '?';
                } else {
                    if (charAt < 127) {
                    }
                    charAt = '?';
                }
                sb.append(charAt);
            }
            property = sb.toString();
        }
        A01 = property;
        HashMap hashMap = new HashMap(2);
        if (!TextUtils.isEmpty(property)) {
            hashMap.put("User-Agent", Collections.singletonList(new AnonymousClass1Jc(A01)));
        }
        A02 = Collections.unmodifiableMap(hashMap);
    }
}
