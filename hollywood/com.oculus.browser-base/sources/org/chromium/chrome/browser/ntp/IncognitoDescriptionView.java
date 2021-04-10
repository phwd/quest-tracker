package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.content.res.Configuration;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChromeBulletSpan;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IncognitoDescriptionView extends LinearLayout {
    public int F;
    public int G;
    public boolean H;
    public LinearLayout I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f10709J;
    public TextView K;
    public LinearLayout L;
    public TextView M;
    public TextView[] N;
    public RelativeLayout O;
    public SwitchCompat P;
    public ImageView Q;
    public TextView R;
    public TextView S;

    public IncognitoDescriptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a() {
        if (!this.H) {
            return;
        }
        if (this.F <= 720) {
            this.O.getLayoutParams().width = -1;
            return;
        }
        this.O.getLayoutParams().width = AbstractC4656rv1.a(getContext(), 600.0f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
        // Method dump skipped, instructions count: 531
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ntp.IncognitoDescriptionView.b():void");
    }

    public final /* synthetic */ void c() {
        this.M.callOnClick();
    }

    public final void d(int i, int i2) {
        ((TextView) findViewById(i)).setText(FY0.a(getContext().getResources().getString(i2).replaceAll("<li>([^<]+)\n", "<li>$1</li>\n").replaceFirst(" *<li>([^<]*)</li>", "<li1>$1</li1>").replaceFirst(" *<li>([^<]*)</li>", "<li2>$1</li2>").replaceFirst(" *<li>([^<]*)</li>\n", "<li3>$1</li3>").replaceAll(" *</?ul>\\n?", ""), new EY0("<em>", "</em>", new ForegroundColorSpan(getContext().getResources().getColor(R.color.f12800_resource_name_obfuscated_RES_2131099970))), new EY0("<li1>", "</li1>", new ChromeBulletSpan(getContext())), new EY0("<li2>", "</li2>", new ChromeBulletSpan(getContext())), new EY0("<li3>", "</li3>", new ChromeBulletSpan(getContext()))));
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = getContext().getResources().getConfiguration().screenWidthDp;
        this.G = getContext().getResources().getConfiguration().screenHeightDp;
        d(R.id.new_tab_incognito_features, R.string.f55840_resource_name_obfuscated_RES_2131952901);
        d(R.id.new_tab_incognito_warning, R.string.f55900_resource_name_obfuscated_RES_2131952907);
        this.I = (LinearLayout) findViewById(R.id.new_tab_incognito_container);
        this.f10709J = (TextView) findViewById(R.id.new_tab_incognito_title);
        this.K = (TextView) findViewById(R.id.new_tab_incognito_subtitle);
        this.M = (TextView) findViewById(R.id.learn_more);
        this.N = new TextView[]{this.K, (TextView) findViewById(R.id.new_tab_incognito_features), (TextView) findViewById(R.id.new_tab_incognito_warning)};
        this.L = (LinearLayout) findViewById(R.id.new_tab_incognito_bulletpoints_container);
        this.O = (RelativeLayout) findViewById(R.id.cookie_controls_card);
        this.P = (SwitchCompat) findViewById(R.id.cookie_controls_card_toggle);
        this.Q = (ImageView) findViewById(R.id.cookie_controls_card_managed_icon);
        this.R = (TextView) findViewById(R.id.cookie_controls_card_title);
        this.S = (TextView) findViewById(R.id.cookie_controls_card_subtitle);
        b();
    }

    public void onMeasure(int i, int i2) {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i3 = this.F;
        int i4 = configuration.screenWidthDp;
        if (!(i3 == i4 && this.G == configuration.screenHeightDp)) {
            this.F = i4;
            this.G = configuration.screenHeightDp;
            b();
        }
        super.onMeasure(i, i2);
    }
}
