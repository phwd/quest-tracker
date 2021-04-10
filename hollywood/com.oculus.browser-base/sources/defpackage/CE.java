package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.send_tab_to_self.TargetDeviceInfo;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: CE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CE extends BaseAdapter {
    public final List F;

    public CE(Profile profile) {
        ArrayList arrayList = new ArrayList();
        N.MVujpkId(profile, arrayList);
        this.F = arrayList;
    }

    public int getCount() {
        return this.F.size();
    }

    public Object getItem(int i) {
        return (TargetDeviceInfo) this.F.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Drawable drawable;
        String str;
        if (view != null) {
            return view;
        }
        Context context = viewGroup.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.f41390_resource_name_obfuscated_RES_2131624448, viewGroup, false);
        TargetDeviceInfo targetDeviceInfo = (TargetDeviceInfo) this.F.get(i);
        ChromeImageView chromeImageView = (ChromeImageView) inflate.findViewById(R.id.device_icon);
        int i2 = targetDeviceInfo.b;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            drawable = AbstractC5544x8.a(context, R.drawable.f28900_resource_name_obfuscated_RES_2131230930);
        } else if (i2 != 6) {
            drawable = AbstractC5544x8.a(context, R.drawable.f29110_resource_name_obfuscated_RES_2131230951);
        } else {
            drawable = AbstractC5544x8.a(context, R.drawable.f35010_resource_name_obfuscated_RES_2131231541);
        }
        chromeImageView.setImageDrawable(drawable);
        chromeImageView.setVisibility(0);
        ((TextView) inflate.findViewById(R.id.device_name)).setText(targetDeviceInfo.c);
        TextView textView = (TextView) inflate.findViewById(R.id.last_active);
        long days = TimeUnit.MILLISECONDS.toDays(Calendar.getInstance().getTimeInMillis() - targetDeviceInfo.d);
        Resources resources = context.getResources();
        int i3 = (days > 1 ? 1 : (days == 1 ? 0 : -1));
        if (i3 < 0) {
            str = resources.getString(R.string.f61210_resource_name_obfuscated_RES_2131953438);
        } else if (i3 == 0) {
            str = resources.getString(R.string.f61200_resource_name_obfuscated_RES_2131953437);
        } else {
            str = resources.getString(R.string.f61190_resource_name_obfuscated_RES_2131953436, Long.valueOf(days));
        }
        textView.setText(str);
        return inflate;
    }
}
