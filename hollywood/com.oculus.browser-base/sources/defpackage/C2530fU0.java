package defpackage;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.task.PostTask;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: fU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2530fU0 implements AbstractC2359eU0 {
    public static final Set F = Collections.unmodifiableSet(AbstractC0417Gv.d("bmp", "css", "csv", "ehtml", "flac", "gif", "htm", "html", "ico", "jfif", "jpeg", "jpg", "m4a", "m4v", "mp3", "mp4", "mpeg", "mpg", "oga", "ogg", "ogm", "ogv", "opus", "pjp", "pjpeg", "png", "shtm", "shtml", "svg", "svgz", "text", "tif", "tiff", "txt", "wav", "weba", "webm", "webp", "xbm"));
    public static final Set G = Collections.unmodifiableSet(AbstractC0417Gv.d("audio/flac", "audio/mp3", "audio/ogg", "audio/wav", "audio/webm", "audio/x-m4a", "image/bmp", "image/gif", "image/jpeg", "image/png", "image/svg+xml", "image/tiff", "image/webp", "image/x-icon", "image/x-ms-bmp", "image/x-xbitmap", "text/comma-separated-values", "text/css", "text/csv", "text/html", "text/plain", "video/mp4", "video/mpeg", "video/ogg", "video/webm"));
    public static final AbstractC2387ef1 H = PostTask.a(C3070if1.e);
    public final WindowAndroid I;

    /* renamed from: J  reason: collision with root package name */
    public final C2701gU0 f9923J;

    public C2530fU0(WebContents webContents, C2701gU0 gu0) {
        this.I = webContents.I();
        this.f9923J = gu0;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.AbstractC2359eU0
    public void k(String str, String str2, Cq1 cq1, KU0[] ku0Arr, C3897nU0 nu0) {
        AbstractC3364kK0.g("WebShare.ApiCount", 0, 2);
        Objects.requireNonNull(this.f9923J);
        AbstractC3364kK0.g("WebShare.ShareOutcome", 1, 3);
        nu0.a(1);
    }
}
