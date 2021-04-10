package defpackage;

import com.oculus.os.Version;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Zx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1579Zx extends AbstractC5079uP implements AbstractC0183Da {

    /* renamed from: a  reason: collision with root package name */
    public final Profile f9387a;
    public Runnable b;
    public C2271dy c;

    public C1579Zx(Profile profile) {
        this.f9387a = profile;
    }

    @Override // defpackage.AbstractC0183Da
    public boolean a() {
        C2271dy dyVar = this.c;
        Objects.requireNonNull(dyVar);
        Object obj = ThreadUtils.f10596a;
        return dyVar.f9821a.size() == 4;
    }

    @Override // defpackage.AbstractC0183Da
    public void b(Runnable runnable) {
        this.b = runnable;
        Profile profile = this.f9387a;
        Object obj = ThreadUtils.f10596a;
        this.c = new C2271dy(profile, 5000, this);
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        String str;
        C2271dy dyVar = this.c;
        if (dyVar == null) {
            return null;
        }
        C1759ay b2 = dyVar.b();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : b2.f9502a.entrySet()) {
            hashMap.put(C2271dy.a(((Integer) entry.getKey()).intValue()), C2271dy.c(((Integer) entry.getValue()).intValue()));
        }
        hashMap.put("Connection check elapsed (ms)", String.valueOf(b2.b));
        int i = b2.c;
        switch (i) {
            case 0:
                str = "Unknown";
                break;
            case 1:
                str = "Ethernet";
                break;
            case 2:
                str = "WiFi";
                break;
            case 3:
                str = "2G";
                break;
            case 4:
                str = "3G";
                break;
            case 5:
                str = "4G";
                break;
            case 6:
                str = "NONE";
                break;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                str = "Bluetooth";
                break;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                str = "5G";
                break;
            default:
                str = AbstractC2531fV.w("Unknown connection type ", i);
                break;
        }
        hashMap.put("Connection type", str);
        return hashMap;
    }
}
