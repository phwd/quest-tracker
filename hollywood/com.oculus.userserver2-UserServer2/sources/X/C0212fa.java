package X;

import android.util.LruCache;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
/* renamed from: X.fa  reason: case insensitive filesystem */
public class C0212fa extends LruCache<Integer, String[]> {
    public C0212fa() {
        super(100);
    }
}
