package defpackage;

import android.content.DialogInterface;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadInfo;

/* renamed from: sq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC4810sq0 implements DialogInterface.OnClickListener {
    public final C0164Cq0 F;
    public final C5830yq0 G;
    public final DownloadInfo H;
    public final String I;

    public DialogInterface$OnClickListenerC4810sq0(C0164Cq0 cq0, C5830yq0 yq0, DownloadInfo downloadInfo, String str) {
        this.F = cq0;
        this.G = yq0;
        this.H = downloadInfo;
        this.I = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C0164Cq0 cq0 = this.F;
        C5830yq0 yq0 = this.G;
        DownloadInfo downloadInfo = this.H;
        String str = this.I;
        Objects.requireNonNull(cq0);
        if (i == -1 && !cq0.i(yq0, downloadInfo, -1, str)) {
            cq0.k(yq0);
        }
    }
}
