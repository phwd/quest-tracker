package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.util.List;

/* renamed from: uK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5069uK extends BaseAdapter {
    public final Context F;
    public final List G;
    public final List H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f11405J;
    public final int K;

    public C5069uK(Context context, List list, List list2) {
        this.F = context;
        this.G = list;
        this.H = list2;
        this.I = context.getResources().getDimensionPixelSize(R.dimen.f18970_resource_name_obfuscated_RES_2131165516);
        this.f11405J = context.getResources().getDimensionPixelSize(R.dimen.f18950_resource_name_obfuscated_RES_2131165514);
        this.K = context.getResources().getDimensionPixelSize(R.dimen.f18960_resource_name_obfuscated_RES_2131165515);
    }

    public int getCount() {
        return this.G.size();
    }

    public Object getItem(int i) {
        return this.G.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = (ImageView) view;
        if (imageView == null) {
            imageView = new ImageView(this.F);
        }
        imageView.setImageDrawable(AbstractC5544x8.a(this.F, ((Integer) this.G.get(i)).intValue()));
        imageView.setContentDescription(this.F.getString(((Integer) this.H.get(i)).intValue()));
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(this.I);
        imageView.setMaxHeight(this.I);
        int i2 = this.f11405J;
        int i3 = this.K;
        imageView.setPadding(i2, i3, i2, i3);
        return imageView;
    }
}
