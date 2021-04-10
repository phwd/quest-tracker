package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.download.DownloadManagerBridge;

/* renamed from: cI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1991cI extends AbstractC2032cb {
    public final long i;
    public final Callback j;

    public C1991cI(long j2, Callback callback) {
        this.i = j2;
        this.j = callback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return DownloadManagerBridge.d(this.i);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.onResult((C1820bI) obj);
    }
}
