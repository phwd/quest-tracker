package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* renamed from: X.0k0  reason: invalid class name and case insensitive filesystem */
public class C04990k0 extends AbstractC02390aP<Long> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final Long A01(SharedPreferences sharedPreferences, String str, @Nullable Long l) {
        long longValue;
        Long l2 = l;
        if (l2 == null) {
            longValue = Long.MAX_VALUE;
        } else {
            longValue = l2.longValue();
        }
        return Long.valueOf(sharedPreferences.getLong(str, longValue));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final Long A02(Bundle bundle, String str, @Nullable Long l) {
        long longValue;
        Long l2 = l;
        if (l2 == null) {
            longValue = Long.MAX_VALUE;
        } else {
            longValue = l2.longValue();
        }
        return Long.valueOf(bundle.getLong(str, longValue));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences$Editor, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A03(SharedPreferences.Editor editor, String str, Long l) {
        editor.putLong(str, l.longValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A04(Bundle bundle, String str, Long l) {
        bundle.putLong(str, l.longValue());
    }

    @Override // X.AbstractC02390aP
    public final Class<?> A00() {
        return Long.class;
    }
}
