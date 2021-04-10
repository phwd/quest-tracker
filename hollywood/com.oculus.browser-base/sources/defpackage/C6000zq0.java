package defpackage;

import J.N;
import android.app.DownloadManager;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContentUriUtils;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadManagerBridge;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: zq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6000zq0 extends AbstractC2032cb {
    public final DownloadInfo i;
    public final long j;
    public long k;
    public final /* synthetic */ C0164Cq0 l;

    public C6000zq0(C0164Cq0 cq0, DownloadInfo downloadInfo, long j2) {
        this.l = cq0;
        this.i = downloadInfo;
        this.j = j2;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        ParcelFileDescriptor parcelFileDescriptor;
        DownloadManager downloadManager = (DownloadManager) this.l.b.getSystemService("download");
        boolean z = this.j == -1 && ContentUriUtils.e(this.i.g);
        C5830yq0 yq0 = null;
        if (z) {
            try {
                int openContentUriForRead = ContentUriUtils.openContentUriForRead(this.i.g);
                parcelFileDescriptor = openContentUriForRead > 0 ? ParcelFileDescriptor.fromFd(openContentUriForRead) : null;
            } catch (FileNotFoundException e) {
                AbstractC1220Ua0.f("OMADownloadHandler", "File not found.", e);
            } catch (IOException e2) {
                AbstractC1220Ua0.f("OMADownloadHandler", "Cannot read file.", e2);
            }
        } else {
            parcelFileDescriptor = downloadManager.openDownloadedFile(this.j);
        }
        if (parcelFileDescriptor != null) {
            yq0 = C0164Cq0.g(new FileInputStream(parcelFileDescriptor.getFileDescriptor()));
            parcelFileDescriptor.close();
        }
        if (z) {
            ContentUriUtils.delete(this.i.g);
        }
        this.k = Environment.getExternalStorageDirectory().getUsableSpace();
        AbstractC4724sI.a(1, this.i.c);
        return yq0;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        C5830yq0 yq0 = (C5830yq0) obj;
        if (N.M09VlOh_("UseDownloadOfflineContentProvider")) {
            AbstractC2935hr0.a().j(this.i.z);
        } else {
            DownloadManagerService p = DownloadManagerService.p();
            DownloadInfo downloadInfo = this.i;
            String str = downloadInfo.l;
            p.L.post(new RunnableC3016iI(p, str, downloadInfo.u));
            if (!N.M09VlOh_("UseDownloadOfflineContentProvider")) {
                DownloadManagerBridge.removeCompletedDownload(str, false);
            }
        }
        if (yq0 != null) {
            if (yq0.b.isEmpty() || C0164Cq0.d(yq0) <= 0 || TextUtils.isEmpty((String) yq0.f11703a.get("objectURI"))) {
                this.l.i(yq0, this.i, -1, "906 Invalid descriptor \n\r");
                return;
            }
            String str2 = (String) yq0.f11703a.get("DDVersion");
            if (str2 != null && !str2.startsWith("1.")) {
                this.l.i(yq0, this.i, -1, "951 Invalid DDVersion \n\r");
            } else if (this.k < C0164Cq0.d(yq0)) {
                this.l.j(R.string.f56590_resource_name_obfuscated_RES_2131952976, yq0, this.i, "901 insufficient memory \n\r");
            } else if (C0164Cq0.c(yq0) == null) {
                this.l.j(R.string.f56610_resource_name_obfuscated_RES_2131952978, yq0, this.i, "953 Non-Acceptable Content \n\r");
            } else {
                C0164Cq0 cq0 = this.l;
                long j2 = this.j;
                DownloadInfo downloadInfo2 = this.i;
                View inflate = ((LayoutInflater) cq0.b.getSystemService("layout_inflater")).inflate(R.layout.f37400_resource_name_obfuscated_RES_2131624049, (ViewGroup) null);
                ((TextView) inflate.findViewById(R.id.oma_download_name)).setText((String) yq0.f11703a.get("name"));
                ((TextView) inflate.findViewById(R.id.oma_download_vendor)).setText((String) yq0.f11703a.get("vendor"));
                ((TextView) inflate.findViewById(R.id.oma_download_size)).setText((String) yq0.f11703a.get("size"));
                ((TextView) inflate.findViewById(R.id.oma_download_type)).setText(C0164Cq0.c(yq0));
                ((TextView) inflate.findViewById(R.id.oma_download_description)).setText((String) yq0.f11703a.get("description"));
                DialogInterface$OnClickListenerC4640rq0 rq0 = new DialogInterface$OnClickListenerC4640rq0(cq0, j2, downloadInfo2, yq0);
                C2290e4 e4Var = new C2290e4(ApplicationStatus.e, R.style.f72700_resource_name_obfuscated_RES_2132017843);
                e4Var.g(R.string.f59550_resource_name_obfuscated_RES_2131953272);
                e4Var.e(R.string.f56550_resource_name_obfuscated_RES_2131952972, rq0);
                e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, rq0);
                C1598a4 a4Var = e4Var.f9828a;
                a4Var.r = inflate;
                a4Var.q = 0;
                a4Var.k = false;
                e4Var.i();
            }
        }
    }
}
