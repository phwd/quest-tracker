package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: dT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2187dT0 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f9784a = new ArrayList();
    public static C2187dT0 b;

    public static C2187dT0 a() {
        Object obj = ThreadUtils.f10596a;
        if (b == null) {
            b = new C2187dT0();
        }
        return b;
    }

    public void b(PersonalDataManager.CreditCard creditCard) {
        for (AbstractC2016cT0 ct0 : f9784a) {
            PostTask.b(Zo1.f9374a, new RunnableC1665aT0(this, ct0, creditCard), 0);
        }
    }
}
