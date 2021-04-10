package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.content.browser.ContactsDialogHost;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: yy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5853yy {

    /* renamed from: a  reason: collision with root package name */
    public static C4548rG0 f11714a;
    public static Object b;

    public static boolean a(WindowAndroid windowAndroid, ContactsDialogHost contactsDialogHost, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str) {
        if (f11714a == null) {
            return false;
        }
        DialogC6023zy zyVar = new DialogC6023zy(windowAndroid, new C0472Hs((Context) windowAndroid.f11022J.get()), contactsDialogHost, z, z2, z3, z4, z5, z6, str);
        zyVar.getWindow().getAttributes().windowAnimations = R.style.f69080_resource_name_obfuscated_RES_2132017481;
        zyVar.show();
        b = zyVar;
        return true;
    }
}
