package X;

import android.content.ContentResolver;
import android.graphics.Rect;
import java.util.concurrent.Executor;

/* renamed from: X.1iz  reason: invalid class name */
public final class AnonymousClass1iz extends AnonymousClass1j9 implements AnonymousClass1j5<AnonymousClass0PZ> {
    public static final Rect A01 = new Rect(0, 0, 96, 96);
    public static final Rect A02 = new Rect(0, 0, 512, 384);
    public static final String[] A03 = {"_id", "_data"};
    public static final String[] A04 = {"_data"};
    public final ContentResolver A00;

    public AnonymousClass1iz(Executor executor, AnonymousClass0JW r2, ContentResolver contentResolver) {
        super(executor, r2);
        this.A00 = contentResolver;
    }
}
