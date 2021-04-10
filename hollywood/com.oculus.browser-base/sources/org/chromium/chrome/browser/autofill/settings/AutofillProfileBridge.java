package org.chromium.chrome.browser.autofill.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.List;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillProfileBridge {

    /* renamed from: a  reason: collision with root package name */
    public String f10615a;

    public static void a(WebContents webContents, Class cls) {
        Context context = (Context) webContents.I().s0().get();
        String name = cls.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        U20.q(context, l);
    }

    public static void intArrayToList(int[] iArr, List list) {
        for (int i : iArr) {
            list.add(Integer.valueOf(i));
        }
    }

    public static void showAutofillCreditCardSettings(WebContents webContents) {
        AbstractC3535lK0.a("AutofillCreditCardsViewed");
        a(webContents, AutofillPaymentMethodsFragment.class);
    }

    public static void showAutofillProfileSettings(WebContents webContents) {
        AbstractC3535lK0.a("AutofillAddressesViewed");
        a(webContents, AutofillProfilesFragment.class);
    }

    public static void stringArrayToList(String[] strArr, List list) {
        for (String str : strArr) {
            list.add(str);
        }
    }
}
