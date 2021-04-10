package defpackage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.format.Formatter;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;

/* renamed from: Fy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Fy1 extends ChromeImageViewPreference {
    public final C2427et A0;
    public final C3469ky1 B0;
    public final QX0 C0;
    public boolean D0;

    public Fy1(Context context, C2427et etVar, C3469ky1 ky1, QX0 qx0) {
        super(context);
        String str;
        this.A0 = etVar;
        this.B0 = ky1;
        this.C0 = qx0;
        this.l0 = R.layout.f42370_resource_name_obfuscated_RES_2131624546;
        ColorDrawable colorDrawable = new ColorDrawable(0);
        if (this.P != colorDrawable) {
            this.P = colorDrawable;
            this.O = 0;
            s();
        }
        V(ky1.i());
        if (ky1.G == null) {
            OB0 h = ky1.h(qx0.i());
            if (h != null && h.F) {
                T(this.F.getString(R.string.f47640_resource_name_obfuscated_RES_2131952081));
                return;
            }
            return;
        }
        if (ky1.k()) {
            str = this.F.getString(R.string.f65610_resource_name_obfuscated_RES_2131953878);
        } else {
            str = String.format(this.F.getString(R.string.f65440_resource_name_obfuscated_RES_2131953861), ky1.G.f());
        }
        T(str);
    }

    @Override // androidx.preference.Preference
    public int g(Preference preference) {
        if (!(preference instanceof Fy1)) {
            return super.compareTo(preference);
        }
        Fy1 fy1 = (Fy1) preference;
        if (!this.C0.r(22)) {
            return this.B0.b(fy1.B0);
        }
        C3469ky1 ky1 = this.B0;
        C3469ky1 ky12 = fy1.B0;
        Objects.requireNonNull(ky1);
        if (ky1 == ky12) {
            return 0;
        }
        int i = (ky12.j() > ky1.j() ? 1 : (ky12.j() == ky1.j() ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        if (i == 0) {
            return 0;
        }
        return 1;
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeImageViewPreference, androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        TextView textView = (TextView) tf0.x(R.id.usage_text);
        textView.setVisibility(8);
        if (this.C0.r(22)) {
            long j = this.B0.j();
            if (j > 0) {
                textView.setText(Formatter.formatShortFileSize(this.F, j));
                textView.setTextSize(13.0f);
                textView.setVisibility(0);
            }
        }
        if (!this.D0) {
            C2427et etVar = this.A0;
            Uri parse = Uri.parse(this.B0.F.d());
            if (parse.getPort() != -1) {
                parse = parse.buildUpon().authority(parse.getHost()).build();
            }
            String uri = parse.toString();
            Ey1 ey1 = new Ey1(this);
            Objects.requireNonNull(etVar);
            new C2256dt(etVar, uri, ey1, null);
            this.D0 = true;
        }
        int round = Math.round(this.F.getResources().getDisplayMetrics().density * 4.0f);
        tf0.x(16908294).setPadding(round, round, round, round);
    }
}
