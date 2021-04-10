package X;

import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0bP  reason: invalid class name */
public final class AnonymousClass0bP {
    public static C02790bO A00(Context context, String str) {
        int i = AnonymousClass0bU.A01(context, str).applicationInfo.uid;
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList(str));
        return new C02790bO(i, unmodifiableList, AnonymousClass0bU.A04(context, (String[]) unmodifiableList.toArray(new String[0])), null, null);
    }
}
