package defpackage;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: oD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4026oD extends BaseAdapter {
    public static final int F = (Build.VERSION.SDK_INT >= 26 ? 4 : 1);
    public final Calendar G;
    public final int H;
    public final int I;

    public C4026oD() {
        Calendar e = AbstractC2255ds1.e();
        this.G = e;
        this.H = e.getMaximum(7);
        this.I = e.getFirstDayOfWeek();
    }

    public int getCount() {
        return this.H;
    }

    public Object getItem(int i) {
        int i2 = this.H;
        if (i >= i2) {
            return null;
        }
        int i3 = i + this.I;
        if (i3 > i2) {
            i3 -= i2;
        }
        return Integer.valueOf(i3);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) AbstractC2531fV.r(viewGroup, R.layout.f39560_resource_name_obfuscated_RES_2131624265, viewGroup, false);
        }
        Calendar calendar = this.G;
        int i2 = i + this.I;
        int i3 = this.H;
        if (i2 > i3) {
            i2 -= i3;
        }
        calendar.set(7, i2);
        textView.setText(this.G.getDisplayName(7, F, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R.string.f55510_resource_name_obfuscated_RES_2131952868), this.G.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
