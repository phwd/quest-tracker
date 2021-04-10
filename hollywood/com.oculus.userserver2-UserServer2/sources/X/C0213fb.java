package X;

import android.content.pm.Signature;
import android.util.LruCache;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
/* renamed from: X.fb  reason: case insensitive filesystem */
public class C0213fb extends LruCache<Integer, Signature> {
    public C0213fb() {
        super(100);
    }
}
