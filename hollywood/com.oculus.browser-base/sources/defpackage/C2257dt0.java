package defpackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: dt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2257dt0 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9816a = new HashMap();
    public final List b;
    public AbstractC1818bH0 c;

    public C2257dt0(List list) {
        this.b = list;
        if (this.c == null) {
            C2087ct0 ct0 = new C2087ct0(this);
            this.c = ct0;
            ProfileManager.f10754a.b(ct0);
        }
    }
}
