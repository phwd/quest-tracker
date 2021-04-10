package defpackage;

import android.net.Uri;
import org.chromium.chrome.browser.provider.ChromeBrowserProviderImpl;

/* renamed from: Xq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1443Xq implements Runnable {
    public final /* synthetic */ Uri F;
    public final /* synthetic */ ChromeBrowserProviderImpl G;

    public RunnableC1443Xq(ChromeBrowserProviderImpl chromeBrowserProviderImpl, Uri uri) {
        this.G = chromeBrowserProviderImpl;
        this.F = uri;
    }

    public void run() {
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = this.G;
        String[] strArr = ChromeBrowserProviderImpl.b;
        chromeBrowserProviderImpl.b().getContentResolver().notifyChange(this.F, null);
    }
}
