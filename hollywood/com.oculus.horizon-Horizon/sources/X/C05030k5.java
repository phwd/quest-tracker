package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* renamed from: X.0k5  reason: invalid class name and case insensitive filesystem */
public class C05030k5 extends AbstractC02390aP<Integer> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final Integer A01(SharedPreferences sharedPreferences, String str, @Nullable Integer num) {
        int intValue;
        Integer num2 = num;
        if (num2 == null) {
            intValue = 0;
        } else {
            intValue = num2.intValue();
        }
        return Integer.valueOf(sharedPreferences.getInt(str, intValue));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final Integer A02(Bundle bundle, String str, @Nullable Integer num) {
        int intValue;
        Integer num2 = num;
        if (num2 == null) {
            intValue = 0;
        } else {
            intValue = num2.intValue();
        }
        return Integer.valueOf(bundle.getInt(str, intValue));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences$Editor, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A03(SharedPreferences.Editor editor, String str, Integer num) {
        editor.putInt(str, num.intValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A04(Bundle bundle, String str, Integer num) {
        bundle.putInt(str, num.intValue());
    }

    @Override // X.AbstractC02390aP
    public final Class<?> A00() {
        return Integer.class;
    }
}
