package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: Z80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z80 extends BaseAdapter {
    public int F = -1;
    public final /* synthetic */ C1614a90 G;

    public Z80(C1614a90 a90) {
        this.G = a90;
        a();
    }

    public void a() {
        C4616ri0 ri0 = this.G.H;
        C0817Ni0 ni0 = ri0.w;
        if (ni0 != null) {
            ri0.i();
            ArrayList arrayList = ri0.k;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((C0817Ni0) arrayList.get(i)) == ni0) {
                    this.F = i;
                    return;
                }
            }
        }
        this.F = -1;
    }

    /* renamed from: b */
    public C0817Ni0 getItem(int i) {
        C4616ri0 ri0 = this.G.H;
        ri0.i();
        ArrayList arrayList = ri0.k;
        Objects.requireNonNull(this.G);
        int i2 = i + 0;
        int i3 = this.F;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return (C0817Ni0) arrayList.get(i2);
    }

    public int getCount() {
        C4616ri0 ri0 = this.G.H;
        ri0.i();
        int size = ri0.k.size();
        Objects.requireNonNull(this.G);
        int i = size + 0;
        return this.F < 0 ? i : i - 1;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.G.G.inflate(R.layout.f36430_resource_name_obfuscated_RES_2131623952, viewGroup, false);
        }
        ((AbstractC2228dj0) view).e(getItem(i), 0);
        return view;
    }

    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
