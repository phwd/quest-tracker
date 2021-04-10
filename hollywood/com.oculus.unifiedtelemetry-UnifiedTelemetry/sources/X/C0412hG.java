package X;

import android.content.pm.Signature;
import android.util.LruCache;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
/* renamed from: X.hG  reason: case insensitive filesystem */
public class C0412hG extends LruCache<Integer, Signature> {
    public C0412hG() {
        super(100);
    }
}
