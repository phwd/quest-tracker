package defpackage;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: qM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4393qM extends Preference {
    public long t0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4393qM(Context context, List list, long j) {
        super(context, null);
        String str = null;
        this.k0 = R.layout.f38390_resource_name_obfuscated_RES_2131624148;
        M(R.drawable.f29560_resource_name_obfuscated_RES_2131230996);
        U(R.string.f52030_resource_name_obfuscated_RES_2131952520);
        P(999);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Preference preference = (Preference) it.next();
            CharSequence charSequence = preference.M;
            boolean z = preference instanceof AbstractC2837hF0;
            if (z && !TextUtils.isEmpty(charSequence)) {
                arrayList.add((AbstractC2837hF0) preference);
            }
            if (arrayList.contains(preference.o0)) {
                if (z) {
                    arrayList.add((AbstractC2837hF0) preference);
                }
            } else if (!TextUtils.isEmpty(charSequence)) {
                if (str == null) {
                    str = charSequence;
                } else {
                    str = this.F.getString(R.string.f62560_resource_name_obfuscated_RES_2131953573, str, charSequence);
                }
            }
        }
        T(str);
        this.t0 = j + 1000000;
    }

    @Override // androidx.preference.Preference
    public long k() {
        return this.t0;
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        tf0.a0 = false;
    }
}
