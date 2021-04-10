package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1kI  reason: invalid class name */
public final class AnonymousClass1kI {
    public long A00 = -1;
    public ArrayList<AnonymousClass1kL> A01 = new ArrayList<>();

    public static void A00(AnonymousClass1kI r2, long j, long j2) {
        ArrayList<AnonymousClass1kL> arrayList = r2.A01;
        if (!arrayList.isEmpty()) {
            AnonymousClass1kL r22 = arrayList.get(arrayList.size() - 1);
            r22.A00 += j;
            r22.A01 += j2;
        }
    }
}
