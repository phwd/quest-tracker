package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadInfo;

/* renamed from: yr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5832yr implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final C0043Ar f11705a;
    public final DownloadInfo b;

    public C5832yr(C0043Ar ar, DownloadInfo downloadInfo) {
        this.f11705a = ar;
        this.b = downloadInfo;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        C0043Ar ar = this.f11705a;
        DownloadInfo downloadInfo = this.b;
        Objects.requireNonNull(ar);
        if (iArr.length > 0 && iArr[0] == 0) {
            ar.c(downloadInfo);
        }
    }
}
