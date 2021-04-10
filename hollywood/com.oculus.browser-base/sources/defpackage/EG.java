package defpackage;

import J.N;
import org.chromium.base.BuildInfo;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;

/* renamed from: EG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EG extends WebContentsDelegateAndroid {

    /* renamed from: a  reason: collision with root package name */
    public static EG f7949a;
    public long b = N.M69NflN_(this);

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return !BuildInfo.a();
    }
}
