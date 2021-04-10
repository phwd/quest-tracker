package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* renamed from: oi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4104oi0 extends BaseAdapter {
    public C4616ri0 F;
    public int G = -1;
    public boolean H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final LayoutInflater f10569J;
    public final int K;

    public C4104oi0(C4616ri0 ri0, LayoutInflater layoutInflater, boolean z, int i) {
        this.I = z;
        this.f10569J = layoutInflater;
        this.F = ri0;
        this.K = i;
        a();
    }

    public void a() {
        C4616ri0 ri0 = this.F;
        C0817Ni0 ni0 = ri0.w;
        if (ni0 != null) {
            ri0.i();
            ArrayList arrayList = ri0.k;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((C0817Ni0) arrayList.get(i)) == ni0) {
                    this.G = i;
                    return;
                }
            }
        }
        this.G = -1;
    }

    /* renamed from: b */
    public C0817Ni0 getItem(int i) {
        ArrayList arrayList;
        if (this.I) {
            C4616ri0 ri0 = this.F;
            ri0.i();
            arrayList = ri0.k;
        } else {
            arrayList = this.F.l();
        }
        int i2 = this.G;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (C0817Ni0) arrayList.get(i);
    }

    public int getCount() {
        ArrayList arrayList;
        if (this.I) {
            C4616ri0 ri0 = this.F;
            ri0.i();
            arrayList = ri0.k;
        } else {
            arrayList = this.F.l();
        }
        if (this.G < 0) {
            return arrayList.size();
        }
        return arrayList.size() - 1;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f10569J.inflate(this.K, viewGroup, false);
        }
        int i2 = getItem(i).b;
        int i3 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        boolean z = this.F.m() && i2 != (i3 >= 0 ? getItem(i3).b : i2);
        ImageView imageView = listMenuItemView.M;
        if (imageView != null) {
            imageView.setVisibility((listMenuItemView.T || !z) ? 8 : 0);
        }
        AbstractC2228dj0 dj0 = (AbstractC2228dj0) view;
        if (this.H) {
            listMenuItemView.V = true;
            listMenuItemView.R = true;
        }
        dj0.e(getItem(i), 0);
        return view;
    }

    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
