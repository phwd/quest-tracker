package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView;

/* renamed from: SH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SH implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ DownloadLocationCustomView F;

    public SH(DownloadLocationCustomView downloadLocationCustomView) {
        this.F = downloadLocationCustomView;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        DownloadLocationCustomView downloadLocationCustomView = this.F;
        long j2 = ((LF) this.F.F.getItem(i)).c;
        if (downloadLocationCustomView.N == 6) {
            String a2 = R21.a(downloadLocationCustomView.getContext(), j2);
            Context context = downloadLocationCustomView.getContext();
            Object obj = K2.f8337a;
            int color = context.getColor(R.color.f11450_resource_name_obfuscated_RES_2131099835);
            int color2 = downloadLocationCustomView.getContext().getColor(R.color.f12230_resource_name_obfuscated_RES_2131099913);
            if (j2 < downloadLocationCustomView.O) {
                a2 = downloadLocationCustomView.getContext().getResources().getString(R.string.f51290_resource_name_obfuscated_RES_2131952446, a2, downloadLocationCustomView.getContext().getText(R.string.f51210_resource_name_obfuscated_RES_2131952438));
                int color3 = downloadLocationCustomView.getContext().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976);
                color2 = downloadLocationCustomView.getContext().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976);
                AbstractC3364kK0.g("MobileDownload.Location.Dialog.Suggestion.Events", 1, 2);
                color = color3;
            }
            downloadLocationCustomView.L.setText(a2);
            downloadLocationCustomView.L.setTextColor(color);
            downloadLocationCustomView.K.getBackground().mutate().setTint(color2);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
