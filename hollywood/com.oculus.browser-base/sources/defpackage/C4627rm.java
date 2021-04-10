package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: rm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4627rm implements AbstractC4559rK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5647xm f11219a;

    public C4627rm(C5647xm xmVar) {
        this.f11219a = xmVar;
    }

    @Override // defpackage.AbstractC4559rK
    public boolean a(CharSequence charSequence) {
        if (charSequence == null || !this.f11219a.h.contains(PersonalDataManager.c().a(charSequence.toString(), true))) {
            return false;
        }
        return true;
    }

    @Override // defpackage.AbstractC4559rK
    public boolean b(CharSequence charSequence) {
        Objects.requireNonNull(this.f11219a);
        if (!TextUtils.isEmpty(charSequence)) {
            String a2 = PersonalDataManager.c().a(charSequence.toString(), false);
            if (!TextUtils.isEmpty(a2)) {
                String replace = charSequence.toString().replace(" ", "").replace("-", "");
                a2.hashCode();
                char c = 65535;
                switch (a2.hashCode()) {
                    case -1331704771:
                        if (a2.equals("diners")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -296504455:
                        if (a2.equals("unionpay")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 2997727:
                        if (a2.equals("amex")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        if (replace.length() == 14) {
                            return true;
                        }
                        break;
                    case 1:
                        if (replace.length() == 19) {
                            return true;
                        }
                        break;
                    case 2:
                        if (replace.length() == 15) {
                            return true;
                        }
                        break;
                    default:
                        if (replace.length() == 16) {
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }
}
