package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* renamed from: X.0k1  reason: invalid class name and case insensitive filesystem */
public class C05000k1 extends AbstractC02390aP<Boolean> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final Boolean A01(SharedPreferences sharedPreferences, String str, @Nullable Boolean bool) {
        boolean booleanValue;
        Boolean bool2 = bool;
        if (bool2 == null) {
            booleanValue = false;
        } else {
            booleanValue = bool2.booleanValue();
        }
        return Boolean.valueOf(sharedPreferences.getBoolean(str, booleanValue));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final Boolean A02(Bundle bundle, String str, @Nullable Boolean bool) {
        boolean booleanValue;
        Boolean bool2 = bool;
        if (bool2 == null) {
            booleanValue = false;
        } else {
            booleanValue = bool2.booleanValue();
        }
        return Boolean.valueOf(bundle.getBoolean(str, booleanValue));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences$Editor, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A03(SharedPreferences.Editor editor, String str, Boolean bool) {
        editor.putBoolean(str, bool.booleanValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A04(Bundle bundle, String str, Boolean bool) {
        bundle.putBoolean(str, bool.booleanValue());
    }

    @Override // X.AbstractC02390aP
    public final Class<?> A00() {
        return Boolean.class;
    }
}
