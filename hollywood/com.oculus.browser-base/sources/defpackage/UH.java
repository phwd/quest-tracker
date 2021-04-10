package defpackage;

import J.N;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView;

/* renamed from: UH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class UH extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final VH f9019a;

    public UH(VH vh) {
        this.f9019a = vh;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        VH vh = this.f9019a;
        ArrayList arrayList = (ArrayList) obj;
        Objects.requireNonNull(vh);
        if (arrayList.size() == 1 && vh.K == 1) {
            LF lf = (LF) arrayList.get(0);
            if (lf.e == 0) {
                N.MQzHQbrF(lf.b);
                ((DownloadDialogBridge) vh.F).e(vh.L);
            }
        } else if (vh.G == null) {
            String str = null;
            DownloadLocationCustomView downloadLocationCustomView = (DownloadLocationCustomView) LayoutInflater.from(vh.M).inflate(R.layout.f38060_resource_name_obfuscated_RES_2131624115, (ViewGroup) null);
            vh.H = downloadLocationCustomView;
            int i = vh.K;
            File file = new File(vh.L);
            long j = vh.f9074J;
            switch (vh.K) {
                case 1:
                case 6:
                    str = vh.M.getString(R.string.f51130_resource_name_obfuscated_RES_2131952430);
                    break;
                case 2:
                    str = vh.M.getString(R.string.f51210_resource_name_obfuscated_RES_2131952438);
                    break;
                case 3:
                    str = vh.M.getString(R.string.f51190_resource_name_obfuscated_RES_2131952436);
                    break;
                case 4:
                    str = vh.M.getString(R.string.f51140_resource_name_obfuscated_RES_2131952431);
                    break;
                case 5:
                    str = vh.M.getString(R.string.f51230_resource_name_obfuscated_RES_2131952440);
                    break;
            }
            downloadLocationCustomView.N = i;
            downloadLocationCustomView.M.setChecked(DownloadDialogBridge.b() == 0);
            downloadLocationCustomView.M.setOnCheckedChangeListener(downloadLocationCustomView);
            downloadLocationCustomView.I.setText(file.getName());
            downloadLocationCustomView.G.setText(str);
            downloadLocationCustomView.O = j;
            switch (i) {
                case 1:
                    if (j <= 0) {
                        downloadLocationCustomView.c();
                        break;
                    } else {
                        downloadLocationCustomView.H.setText(OI.b(downloadLocationCustomView.getContext(), j));
                        break;
                    }
                case 2:
                    downloadLocationCustomView.H.setText(R.string.f51150_resource_name_obfuscated_RES_2131952432);
                    break;
                case 3:
                    downloadLocationCustomView.H.setText(R.string.f51150_resource_name_obfuscated_RES_2131952432);
                    break;
                case 4:
                    downloadLocationCustomView.H.setText(R.string.f51160_resource_name_obfuscated_RES_2131952433);
                    break;
                case 5:
                    downloadLocationCustomView.H.setText(R.string.f51170_resource_name_obfuscated_RES_2131952434);
                    break;
                case 6:
                    downloadLocationCustomView.L.setVisibility(0);
                    downloadLocationCustomView.f10665J.setVisibility(0);
                    downloadLocationCustomView.f10665J.setText(OI.b(downloadLocationCustomView.getContext(), j));
                    downloadLocationCustomView.c();
                    break;
            }
            downloadLocationCustomView.F.b();
            Resources resources = vh.M.getResources();
            HH0 hh0 = new HH0(AbstractC3258jl0.r);
            hh0.e(AbstractC3258jl0.f10235a, vh);
            hh0.e(AbstractC3258jl0.f, vh.H);
            hh0.d(AbstractC3258jl0.g, resources, R.string.f51820_resource_name_obfuscated_RES_2131952499);
            hh0.b(AbstractC3258jl0.q, true);
            hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
            UH0 a2 = hh0.a();
            vh.G = a2;
            vh.I.i(a2, 0, false);
        }
    }
}
