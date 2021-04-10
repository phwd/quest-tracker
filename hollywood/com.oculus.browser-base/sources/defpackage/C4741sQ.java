package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import android.os.Vibrator;
import android.provider.Settings;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.components.find_in_page.FindNotificationDetails;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: sQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4741sQ extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BQ f11272a;

    public C4741sQ(BQ bq) {
        this.f11272a = bq;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        this.f11272a.d(true);
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null && this.f11272a.getVisibility() == 0) {
            this.f11272a.d(true);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void m(Tab tab, boolean z) {
        if (z) {
            this.f11272a.d(true);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.f11272a.d(true);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void y(FindMatchRectsDetails findMatchRectsDetails) {
        BQ bq = this.f11272a;
        if (bq.L != null) {
            if (bq.G.getText().length() > 0) {
                bq.L.b(findMatchRectsDetails.f10845a, findMatchRectsDetails.b, findMatchRectsDetails.c);
            } else {
                bq.L.b(-1, new RectF[0], null);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void z(FindNotificationDetails findNotificationDetails) {
        String str;
        BQ bq = this.f11272a;
        C4230pQ pQVar = bq.L;
        boolean z = false;
        if (pQVar != null) {
            pQVar.h0 = false;
        }
        int i = -1;
        if ((findNotificationDetails.c != -1 && findNotificationDetails.f10846a != 1) || findNotificationDetails.d) {
            if (findNotificationDetails.d) {
                if (findNotificationDetails.f10846a > 0) {
                    C3546lQ lQVar = bq.S;
                    if (pQVar != null) {
                        i = pQVar.V;
                    }
                    N.M4m8QCn$(lQVar.b, lQVar, i);
                } else {
                    bq.c();
                }
                bq.e(findNotificationDetails.b);
            }
            Context context = bq.getContext();
            bq.n(context.getResources().getString(R.string.f52290_resource_name_obfuscated_RES_2131952546, Integer.valueOf(Math.max(findNotificationDetails.c, 0)), Integer.valueOf(findNotificationDetails.f10846a)), findNotificationDetails.f10846a == 0);
            bq.l(findNotificationDetails.f10846a > 0);
            int max = Math.max(findNotificationDetails.c, 0);
            int i2 = findNotificationDetails.f10846a;
            Context context2 = bq.getContext();
            if (i2 > 0) {
                str = context2.getResources().getString(R.string.f46370_resource_name_obfuscated_RES_2131951954, Integer.valueOf(max), Integer.valueOf(i2));
            } else {
                str = context2.getResources().getString(R.string.f46380_resource_name_obfuscated_RES_2131951955);
            }
            bq.F.setContentDescription(str);
            if (!bq.e0) {
                Runnable runnable = bq.d0;
                if (runnable != null) {
                    bq.c0.removeCallbacks(runnable);
                }
                RunnableC4571rQ rQVar = new RunnableC4571rQ(bq, str);
                bq.d0 = rQVar;
                bq.c0.postDelayed(rQVar, 500);
            }
            if (findNotificationDetails.f10846a == 0 && findNotificationDetails.d) {
                C3546lQ lQVar2 = bq.S;
                if (!N.M3t_h9OB(lQVar2.b, lQVar2).startsWith(bq.G.getText().toString())) {
                    if (Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 1) == 1) {
                        z = true;
                    }
                    if (z) {
                        ((Vibrator) context.getSystemService("vibrator")).vibrate(50);
                    }
                }
            }
        }
    }
}
