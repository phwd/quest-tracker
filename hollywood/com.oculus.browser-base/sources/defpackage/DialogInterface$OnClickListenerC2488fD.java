package defpackage;

import J.N;
import android.content.DialogInterface;
import java.util.Objects;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* renamed from: fD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC2488fD implements DialogInterface.OnClickListener {
    public final /* synthetic */ View$OnClickListenerC2659gD F;

    public DialogInterface$OnClickListenerC2488fD(View$OnClickListenerC2659gD gDVar) {
        this.F = gDVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            long currentTimeMillis = System.currentTimeMillis();
            PU0 pu0 = NU0.f8549a;
            if (pu0.h("data_reduction_site_breakdown_allowed_date", Long.MAX_VALUE) > currentTimeMillis) {
                pu0.o("data_reduction_site_breakdown_allowed_date", currentTimeMillis);
            }
            DataReductionProxySettings d = DataReductionProxySettings.d();
            Objects.requireNonNull(d);
            PC.c(0);
            pu0.o("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", System.currentTimeMillis());
            N.MqCEYHgN(d.c, d, 3);
            this.F.F.d0(currentTimeMillis);
            this.F.F.c0();
            this.F.F.s();
            UC.a(20);
        }
    }
}
