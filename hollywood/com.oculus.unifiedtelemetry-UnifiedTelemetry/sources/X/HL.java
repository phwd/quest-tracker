package X;

import android.content.ComponentName;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class HL {
    @GuardedBy("UploadScheduler.class")
    public static HL A00;

    public abstract long A01(int i);

    public abstract ComponentName A02();

    public abstract void A03(int i);

    public abstract void A04(int i, @Nullable String str, H8 h8, long j, long j2);

    public static synchronized HL A00(Context context) {
        HL hl;
        synchronized (HL.class) {
            hl = A00;
            if (hl == null) {
                hl = new C0267Yr(context);
                A00 = hl;
            }
        }
        return hl;
    }
}
