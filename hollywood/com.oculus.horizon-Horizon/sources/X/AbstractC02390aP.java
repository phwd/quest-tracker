package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* renamed from: X.0aP  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02390aP<ValueType> {
    public static final AbstractC02390aP<Boolean> A00 = new C05000k1();
    public static final AbstractC02390aP<Integer> A01 = new C05030k5();
    public static final AbstractC02390aP<Long> A02 = new C04990k0();
    public static final AbstractC02390aP<String> A03 = new C05040k6();

    public abstract Class<?> A00();

    public abstract ValueType A01(SharedPreferences sharedPreferences, String str, @Nullable ValueType valuetype);

    public abstract ValueType A02(Bundle bundle, String str, @Nullable ValueType valuetype);

    public abstract void A03(SharedPreferences.Editor editor, String str, ValueType valuetype);

    public abstract void A04(Bundle bundle, String str, ValueType valuetype);
}
