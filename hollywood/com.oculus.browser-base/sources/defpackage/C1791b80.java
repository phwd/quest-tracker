package defpackage;

import J.N;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.state.LevelDBPersistedDataStorage;

/* renamed from: b80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1791b80 implements AbstractC3511lC0 {

    /* renamed from: a  reason: collision with root package name */
    public LevelDBPersistedDataStorage f9515a;

    public C1791b80(Profile profile) {
        this.f9515a = new LevelDBPersistedDataStorage(profile, "");
    }

    public static final String e(int i, String str) {
        return String.format(Locale.US, "%d-%s", Integer.valueOf(i), str);
    }

    @Override // defpackage.AbstractC3511lC0
    public void a(int i, String str, Callback callback) {
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.f9515a;
        N.MqUV_juQ(levelDBPersistedDataStorage.f10776a, levelDBPersistedDataStorage.a(e(i, str)), callback);
    }

    @Override // defpackage.AbstractC3511lC0
    public void b(int i, String str) {
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.f9515a;
        N.Mf_BEvgG(levelDBPersistedDataStorage.f10776a, levelDBPersistedDataStorage.a(e(i, str)), null);
    }

    @Override // defpackage.AbstractC3511lC0
    @Deprecated
    public byte[] c(int i, String str) {
        return null;
    }

    @Override // defpackage.AbstractC3511lC0
    public void d(int i, String str, byte[] bArr) {
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.f9515a;
        N.MDiWNRLP(levelDBPersistedDataStorage.f10776a, levelDBPersistedDataStorage.a(e(i, str)), bArr, null);
    }
}
