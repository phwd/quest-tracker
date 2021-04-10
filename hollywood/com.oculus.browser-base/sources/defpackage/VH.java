package defpackage;

import J.N;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.Spinner;
import java.io.File;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* renamed from: VH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VH implements AbstractC3087il0 {
    public TH F;
    public UH0 G;
    public DownloadLocationCustomView H;
    public C2746gl0 I;

    /* renamed from: J  reason: collision with root package name */
    public long f9074J;
    public int K;
    public String L;
    public Context M;

    public void a(Context context, C2746gl0 gl0, long j, int i, String str) {
        if (context == null || gl0 == null) {
            f(null, 8);
            return;
        }
        this.M = context;
        this.I = gl0;
        this.f9074J = j;
        this.K = i;
        this.L = str;
        AbstractC4550rH.f11194a.a(new UH(this));
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.I.b(uh0, 1);
        } else if (i == 1) {
            this.I.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        LF lf;
        String str;
        if (i != 1) {
            DownloadDialogBridge downloadDialogBridge = (DownloadDialogBridge) this.F;
            if (!downloadDialogBridge.j) {
                downloadDialogBridge.c();
            } else {
                downloadDialogBridge.j = false;
                downloadDialogBridge.g();
            }
        } else {
            DownloadLocationCustomView downloadLocationCustomView = this.H;
            AlertDialogEditText alertDialogEditText = downloadLocationCustomView.I;
            String obj = (alertDialogEditText == null || alertDialogEditText.getText() == null) ? null : downloadLocationCustomView.I.getText().toString();
            Spinner spinner = this.H.K;
            if (spinner == null) {
                lf = null;
            } else {
                lf = (LF) spinner.getSelectedItem();
            }
            CheckBox checkBox = this.H.M;
            boolean z = checkBox != null && checkBox.isChecked();
            if (lf == null || (str = lf.b) == null || obj == null) {
                DownloadDialogBridge downloadDialogBridge2 = (DownloadDialogBridge) this.F;
                if (!downloadDialogBridge2.j) {
                    downloadDialogBridge2.c();
                } else {
                    downloadDialogBridge2.j = false;
                    downloadDialogBridge2.g();
                }
            } else {
                N.MQzHQbrF(str);
                AbstractC3364kK0.g("MobileDownload.Location.Dialog.DirectoryType", lf.e, 3);
                ((DownloadDialogBridge) this.F).e(new File(lf.b, obj).getAbsolutePath());
                if (z) {
                    DownloadDialogBridge.f(2);
                } else {
                    DownloadDialogBridge.f(1);
                }
            }
        }
        this.G = null;
        this.H = null;
    }
}
