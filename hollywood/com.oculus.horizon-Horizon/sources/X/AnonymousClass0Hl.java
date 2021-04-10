package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Hl  reason: invalid class name */
public abstract class AnonymousClass0Hl {
    @GuardedBy("UploadScheduler.class")
    public static AnonymousClass0Hl A00;

    public abstract void A00(int i, @Nullable String str, AnonymousClass0HX v, long j, long j2);
}
