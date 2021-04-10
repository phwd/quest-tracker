package defpackage;

import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* renamed from: r90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4527r90 {

    /* renamed from: a  reason: collision with root package name */
    public static C4527r90 f11185a;

    public CharSequence a(Preference preference) {
        ListPreference listPreference = (ListPreference) preference;
        if (TextUtils.isEmpty(listPreference.b0())) {
            return listPreference.F.getString(R.string.f55980_resource_name_obfuscated_RES_2131952915);
        }
        return listPreference.b0();
    }
}
