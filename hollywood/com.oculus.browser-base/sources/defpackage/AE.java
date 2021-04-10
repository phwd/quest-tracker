package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* renamed from: AE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AE extends ArrayAdapter implements AdapterView.OnItemClickListener {
    public final LayoutInflater F;
    public final Resources G;
    public final boolean H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public int f7662J = -1;
    public Map K = new HashMap();
    public Map L = new HashMap();
    public boolean M;
    public AbstractC5732yE N;

    public AE(Context context, boolean z, int i) {
        super(context, i);
        this.F = LayoutInflater.from(context);
        this.G = context.getResources();
        this.H = z;
        this.I = i;
    }

    public void a(String str, String str2, Drawable drawable, String str3) {
        BE be = (BE) this.L.get(str);
        if (be != null) {
            boolean z = false;
            if (TextUtils.equals(be.f7724a, str) && TextUtils.equals(be.b, str2) && TextUtils.equals(be.d, str3)) {
                if (drawable != null && be.c != null) {
                    Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas();
                    canvas.setBitmap(createBitmap);
                    be.c.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    be.c.draw(canvas);
                    Bitmap createBitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas2 = new Canvas();
                    canvas2.setBitmap(createBitmap2);
                    drawable.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
                    drawable.draw(canvas2);
                    z = createBitmap.sameAs(createBitmap2);
                } else if (drawable == null && be.c == null) {
                    z = true;
                }
            }
            if (!z) {
                if (!TextUtils.equals(be.b, str2)) {
                    c(be.b);
                    be.b = str2;
                    b(str2);
                }
                if (!Objects.equals(drawable, be.c)) {
                    be.c = drawable;
                    be.d = str3;
                }
                notifyDataSetChanged();
                return;
            }
            return;
        }
        BE be2 = new BE(str, str2, drawable, str3);
        this.L.put(str, be2);
        b(be2.b);
        add(be2);
    }

    public final void b(String str) {
        this.K.put(str, Integer.valueOf((this.K.containsKey(str) ? ((Integer) this.K.get(str)).intValue() : 0) + 1));
    }

    public final void c(String str) {
        if (this.K.containsKey(str)) {
            int intValue = ((Integer) this.K.get(str)).intValue();
            if (intValue == 1) {
                this.K.remove(str);
            } else {
                this.K.put(str, Integer.valueOf(intValue - 1));
            }
        }
    }

    public void clear() {
        this.L.clear();
        this.K.clear();
        d(-1);
        super.clear();
    }

    public final void d(int i) {
        this.f7662J = i;
        AbstractC5732yE yEVar = this.N;
        if (yEVar != null) {
            ((C3316k40) yEVar).i.setEnabled(i != -1);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C5902zE zEVar;
        boolean z = false;
        if (view == null) {
            view = this.F.inflate(this.I, viewGroup, false);
            zEVar = new C5902zE(view);
            view.setTag(zEVar);
        } else {
            zEVar = (C5902zE) view.getTag();
        }
        zEVar.f11735a.setSelected(i == this.f7662J);
        zEVar.f11735a.setEnabled(this.H);
        TextView textView = zEVar.f11735a;
        BE be = (BE) getItem(i);
        String str = be.b;
        if (((Integer) this.K.get(str)).intValue() != 1) {
            str = this.G.getString(R.string.f53570_resource_name_obfuscated_RES_2131952674, str, be.f7724a);
        }
        textView.setText(str);
        ImageView imageView = zEVar.b;
        if (imageView != null) {
            if (!this.M) {
                imageView.setVisibility(8);
            } else {
                BE be2 = (BE) getItem(i);
                if (be2.c != null) {
                    zEVar.b.setContentDescription(be2.d);
                    zEVar.b.setImageDrawable(be2.c);
                    zEVar.b.setVisibility(0);
                } else {
                    zEVar.b.setVisibility(4);
                    zEVar.b.setImageDrawable(null);
                    zEVar.b.setContentDescription(null);
                }
                ImageView imageView2 = zEVar.b;
                if (i == this.f7662J) {
                    z = true;
                }
                imageView2.setSelected(z);
            }
        }
        return view;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public boolean isEnabled(int i) {
        return this.H;
    }

    public void notifyDataSetChanged() {
        this.M = false;
        for (BE be : this.L.values()) {
            if (be.c != null) {
                this.M = true;
            }
        }
        super.notifyDataSetChanged();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        d(i);
        notifyDataSetChanged();
    }
}
