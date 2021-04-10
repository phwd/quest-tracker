package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import org.chromium.chrome.browser.language.settings.AddLanguageFragment;

/* renamed from: s3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4679s3 implements AbstractC2012cR0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AddLanguageFragment f11248a;

    public C4679s3(AddLanguageFragment addLanguageFragment) {
        this.f11248a = addLanguageFragment;
    }

    @Override // defpackage.AbstractC2012cR0
    public boolean onQueryTextChange(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, this.f11248a.A0)) {
            return true;
        }
        AddLanguageFragment addLanguageFragment = this.f11248a;
        addLanguageFragment.A0 = str;
        C4849t3 t3Var = addLanguageFragment.C0;
        Objects.requireNonNull(t3Var);
        if (TextUtils.isEmpty(str)) {
            t3Var.t(t3Var.S.D0);
            return true;
        }
        Locale locale = Locale.getDefault();
        String lowerCase = str.trim().toLowerCase(locale);
        ArrayList arrayList = new ArrayList();
        for (B60 b60 : t3Var.S.D0) {
            if (b60.b.toLowerCase(locale).contains(lowerCase)) {
                arrayList.add(b60);
            }
        }
        t3Var.t(arrayList);
        return true;
    }

    @Override // defpackage.AbstractC2012cR0
    public boolean onQueryTextSubmit(String str) {
        return true;
    }
}
