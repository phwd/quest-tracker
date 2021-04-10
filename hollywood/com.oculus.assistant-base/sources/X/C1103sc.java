package X;

import android.content.Context;
import android.os.Handler;
import java.util.HashMap;

/* renamed from: X.sc  reason: case insensitive filesystem */
public final class C1103sc extends RS {
    public final Context A00;
    public final Handler A01;
    public final S3 A02;
    public final HashMap A03 = new HashMap();

    public C1103sc(Context context) {
        this.A00 = context.getApplicationContext();
        this.A01 = new SW(context.getMainLooper(), new Rv(this));
        if (S3.A02 == null) {
            synchronized (S3.A01) {
                if (S3.A02 == null) {
                    S3.A02 = new S3();
                }
            }
        }
        S3 s3 = S3.A02;
        RZ.A01(s3);
        this.A02 = s3;
    }
}
