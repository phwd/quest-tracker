package defpackage;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* renamed from: VC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VC {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9069a;
    public final long b;
    public String c;

    public VC(Context context, long j) {
        String str;
        int[] iArr;
        int i;
        int i2;
        int[] iArr2;
        this.f9069a = context;
        this.b = j;
        if (DataReductionProxySettings.d().e()) {
            NU0.f8549a.f8694a.a("displayed_data_reduction_snackbar_promo_saved_bytes");
            if (AbstractC3983nz.f10523a.contains("displayed_data_reduction_snackbar_promo_saved_bytes")) {
                String MOVY9QtZ = N.MOVY9QtZ("DataCompressionProxyPromoVisibility", "x_milestone_promo_data_savings_in_megabytes");
                if (MOVY9QtZ.isEmpty()) {
                    iArr = AbstractC1575Zv.e().g("enable-data-reduction-proxy-savings-promo") ? new int[]{1} : new int[0];
                } else {
                    String[] split = MOVY9QtZ.replace(" ", "").split(";");
                    if (AbstractC1575Zv.e().g("enable-data-reduction-proxy-savings-promo")) {
                        iArr2 = new int[(split.length + 1)];
                        iArr2[split.length] = 1;
                    } else {
                        iArr2 = new int[split.length];
                    }
                    for (int i3 = 0; i3 < split.length; i3++) {
                        try {
                            iArr2[i3] = Integer.parseInt(split[i3]);
                        } catch (NumberFormatException unused) {
                            iArr2[i3] = -1;
                        }
                    }
                    iArr = iArr2;
                }
                int length = iArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = iArr[i4];
                    long j2 = (long) (1048576 * i5);
                    if (i5 <= 0 || this.b < j2 || NU0.f8549a.h("displayed_data_reduction_snackbar_promo_saved_bytes", -1) >= j2) {
                        i4++;
                    } else {
                        Context context2 = this.f9069a;
                        if (j2 < 1073741824) {
                            i2 = R.string.f50540_resource_name_obfuscated_RES_2131952371;
                            i = (int) (j2 / 1048576);
                        } else {
                            i = (int) (j2 / 1073741824);
                            i2 = R.string.f50530_resource_name_obfuscated_RES_2131952370;
                        }
                        str = context2.getResources().getString(i2, Integer.valueOf(i));
                    }
                }
            } else {
                PC.c(j);
            }
        }
        str = null;
        this.c = str;
    }
}
