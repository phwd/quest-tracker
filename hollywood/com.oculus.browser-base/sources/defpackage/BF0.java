package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.tasks.tab_management.PriceTrackingDialogView;

/* renamed from: BF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BF0 implements CompoundButton.OnCheckedChangeListener {
    public final UH0 F;
    public final C2746gl0 G;
    public final PriceTrackingDialogView H;

    public BF0(Context context, C2746gl0 gl0, AbstractC3748md1 md1, AbstractC0124Ca1 ca1) {
        PriceTrackingDialogView priceTrackingDialogView = (PriceTrackingDialogView) LayoutInflater.from(context).inflate(R.layout.f40900_resource_name_obfuscated_RES_2131624399, (ViewGroup) null, false);
        this.H = priceTrackingDialogView;
        priceTrackingDialogView.F.setOnCheckedChangeListener(this);
        priceTrackingDialogView.G.setOnCheckedChangeListener(this);
        this.G = gl0;
        AF0 af0 = new AF0(this, md1, ca1);
        Map c = UH0.c(AbstractC3258jl0.r);
        OH0 oh0 = AbstractC3258jl0.f10235a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = af0;
        HashMap hashMap = (HashMap) c;
        hashMap.put(oh0, lh0);
        QH0 qh0 = AbstractC3258jl0.m;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        TH0 th0 = AbstractC3258jl0.f;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = priceTrackingDialogView;
        hashMap.put(th0, lh02);
        this.F = new UH0(c, null);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.track_prices_switch && z != CF0.e()) {
            PU0 pu0 = CF0.f7797a;
            pu0.m("Chrome.PriceTracking.TrackPricesOnTabs", !pu0.d("Chrome.PriceTracking.TrackPricesOnTabs", AbstractC4772sd1.e()));
        } else if (compoundButton.getId() == R.id.price_alerts_switch && z != CF0.b()) {
            PU0 pu02 = CF0.f7797a;
            pu02.m("Chrome.PriceTracking.PriceDropAlerts", !pu02.d("Chrome.PriceTracking.PriceDropAlerts", false));
        }
    }
}
