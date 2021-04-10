package X;

import android.os.Process;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1uE  reason: invalid class name and case insensitive filesystem */
public final class C10631uE implements AnonymousClass0VG {
    public static final C10631uE A00 = new C10631uE();

    @Override // X.AnonymousClass0VG
    public final int A5Y() {
        return Process.myTid();
    }
}
