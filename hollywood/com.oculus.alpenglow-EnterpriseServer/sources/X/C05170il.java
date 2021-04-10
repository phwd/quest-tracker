package X;

import android.content.pm.Signature;
import android.util.LruCache;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
/* renamed from: X.0il  reason: invalid class name and case insensitive filesystem */
public class C05170il extends LruCache<Integer, Signature> {
    public C05170il() {
        super(100);
    }
}
