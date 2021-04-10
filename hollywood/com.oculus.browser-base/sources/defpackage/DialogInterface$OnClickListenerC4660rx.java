package defpackage;

import android.content.DialogInterface;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Map;
import org.chromium.chrome.browser.browsing_data.ConfirmImportantSitesDialogFragment;

/* renamed from: rx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC4660rx implements DialogInterface.OnClickListener {
    public final /* synthetic */ ConfirmImportantSitesDialogFragment F;

    public DialogInterface$OnClickListenerC4660rx(ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment) {
        this.F = confirmImportantSitesDialogFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            Intent intent = new Intent();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (Map.Entry entry : this.F.P0.entrySet()) {
                Integer num = (Integer) this.F.N0.get(entry.getKey());
                if (((Boolean) entry.getValue()).booleanValue()) {
                    arrayList3.add((String) entry.getKey());
                    arrayList4.add(num);
                } else {
                    arrayList.add((String) entry.getKey());
                    arrayList2.add(num);
                }
            }
            intent.putExtra("DeselectedDomains", (String[]) arrayList.toArray(new String[0]));
            intent.putExtra("DeselectedDomainReasons", AbstractC0417Gv.b(arrayList2));
            intent.putExtra("IgnoredDomains", (String[]) arrayList3.toArray(new String[0]));
            intent.putExtra("IgnoredDomainReasons", AbstractC0417Gv.b(arrayList4));
            this.F.R().c0(this.F.N, -1, intent);
            return;
        }
        AbstractComponentCallbacksC3550lS R = this.F.R();
        ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = this.F;
        R.c0(confirmImportantSitesDialogFragment.N, 0, confirmImportantSitesDialogFragment.u().getIntent());
    }
}
