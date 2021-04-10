package X;

import android.util.LruCache;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
/* renamed from: X.hF  reason: case insensitive filesystem */
public class C0411hF extends LruCache<Integer, String[]> {
    public C0411hF() {
        super(100);
    }
}
