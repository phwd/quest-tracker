package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* renamed from: X.0k6  reason: invalid class name and case insensitive filesystem */
public class C05040k6 extends AbstractC02390aP<String> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final String A01(SharedPreferences sharedPreferences, String str, @Nullable String str2) {
        return sharedPreferences.getString(str, str2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final String A02(Bundle bundle, String str, @Nullable String str2) {
        return bundle.getString(str, str2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.SharedPreferences$Editor, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A03(SharedPreferences.Editor editor, String str, String str2) {
        editor.putString(str, str2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.Bundle, java.lang.String, java.lang.Object] */
    @Override // X.AbstractC02390aP
    public final void A04(Bundle bundle, String str, String str2) {
        bundle.putString(str, str2);
    }

    @Override // X.AbstractC02390aP
    public final Class<?> A00() {
        return String.class;
    }
}
