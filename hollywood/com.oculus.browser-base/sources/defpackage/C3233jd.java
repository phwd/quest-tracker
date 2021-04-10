package defpackage;

import android.os.Bundle;

/* renamed from: jd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3233jd {

    /* renamed from: a  reason: collision with root package name */
    public C3404kd f10219a;

    public C3233jd(C3404kd kdVar, AbstractC3063id idVar) {
        this.f10219a = kdVar;
    }

    public C3233jd a(Bundle bundle) {
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj != null) {
                    if (str.startsWith("org.chromium.chrome.browser.autofill_assistant.special.")) {
                        if (str.equals("org.chromium.chrome.browser.autofill_assistant.special.EXPERIMENT_IDS")) {
                            C3404kd.a(this.f10219a, obj.toString());
                        }
                    } else if (!str.startsWith("org.chromium.chrome.browser.autofill_assistant.")) {
                        this.f10219a.b.put(str, obj);
                    } else if (str.equals("org.chromium.chrome.browser.autofill_assistant.EXPERIMENT_IDS")) {
                        C3404kd.a(this.f10219a, obj.toString());
                    } else {
                        this.f10219a.f10291a.put(str.substring(47), obj);
                    }
                }
            }
        }
        return this;
    }
}
