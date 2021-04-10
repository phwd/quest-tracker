package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.content_public.browser.NavigationEntry;

/* renamed from: Vm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1314Vm0 extends BaseAdapter {
    public Integer F;
    public final /* synthetic */ C1375Wm0 G;

    public C1314Vm0(C1375Wm0 wm0, View$OnLayoutChangeListenerC1192Tm0 tm0) {
        this.G = wm0;
    }

    public int getCount() {
        return this.G.f9172J.b();
    }

    public Object getItem(int i) {
        return (NavigationEntry) this.G.f9172J.f8712a.get(i);
    }

    public long getItemId(int i) {
        return (long) ((NavigationEntry) this.G.f9172J.f8712a.get(i)).f10939a;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1253Um0 um0;
        int i2 = 0;
        if (view == null) {
            view = AbstractC2531fV.r(viewGroup, R.layout.f39800_resource_name_obfuscated_RES_2131624289, viewGroup, false);
            um0 = new C1253Um0(null);
            um0.f9048a = view;
            um0.b = (ImageView) view.findViewById(R.id.favicon_img);
            um0.c = (TextView) view.findViewById(R.id.entry_title);
            view.setTag(um0);
        } else {
            um0 = (C1253Um0) view.getTag();
        }
        NavigationEntry navigationEntry = (NavigationEntry) this.G.f9172J.f8712a.get(i);
        TextView textView = um0.c;
        String str = navigationEntry.f;
        if (TextUtils.isEmpty(str)) {
            str = navigationEntry.d.h();
        }
        if (TextUtils.isEmpty(str)) {
            str = navigationEntry.b.h();
        }
        textView.setText(str);
        um0.b.setImageBitmap(navigationEntry.g);
        if (navigationEntry.f10939a == -1) {
            ImageView imageView = um0.b;
            Context context = this.G.G;
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            imageView.setImageTintList(context.getColorStateList(R.color.f11230_resource_name_obfuscated_RES_2131099813));
        } else {
            um0.b.setImageTintList(null);
        }
        if (this.G.L == 0) {
            View view2 = um0.f9048a;
            if (this.F == null) {
                this.F = Integer.valueOf(view2.getResources().getDimensionPixelSize(R.dimen.f22850_resource_name_obfuscated_RES_2131165904));
            }
            View view3 = um0.f9048a;
            int paddingLeft = view2.getPaddingLeft();
            if (i == 0) {
                i2 = this.F.intValue();
            }
            view3.setPadding(paddingLeft, i2, view2.getPaddingRight(), view2.getPaddingBottom());
        }
        return view;
    }
}
