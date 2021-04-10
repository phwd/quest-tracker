package defpackage;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: mH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3696mH extends ArrayAdapter {
    public static final /* synthetic */ int F = 0;
    public int G = -2;
    public Context H;
    public LayoutInflater I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC3525lH f10410J;
    public List K = new ArrayList();
    public List L = new ArrayList();
    public List M = new ArrayList();

    public C3696mH(Context context, AbstractC3525lH lHVar) {
        super(context, 17367048);
        this.H = context;
        this.f10410J = lHVar;
        this.I = LayoutInflater.from(context);
    }

    public final void a() {
        if (this.L.size() + this.K.size() > 0) {
            this.M.clear();
        } else {
            this.M.add(new LF(this.H.getString(R.string.f51180_resource_name_obfuscated_RES_2131952435), null, 0, 0, 2));
        }
    }

    public void b() {
        this.K.clear();
        this.L.clear();
        this.M.clear();
        AbstractC4550rH.f11194a.a(new C3354kH(this));
    }

    public int c() {
        for (int i = 0; i < getCount(); i++) {
            LF lf = (LF) getItem(i);
            if (lf != null && lf.c > 0) {
                N.MQzHQbrF(lf.b);
                this.G = i;
                return i;
            }
        }
        a();
        return 0;
    }

    public int getCount() {
        return this.M.size() + this.L.size() + this.K.size();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.I.inflate(R.layout.f38090_resource_name_obfuscated_RES_2131624118, (ViewGroup) null);
        }
        view.setTag(Integer.valueOf(i));
        LF lf = (LF) getItem(i);
        if (lf == null) {
            return view;
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        TextView textView2 = (TextView) view.findViewById(R.id.description);
        boolean isEnabled = isEnabled(i);
        textView.setText(lf.f8414a);
        textView.setEnabled(isEnabled);
        textView2.setEnabled(isEnabled);
        if (isEnabled) {
            textView2.setText(R21.a(this.H, lf.c));
        } else if (this.M.isEmpty()) {
            textView2.setText(this.H.getText(R.string.f51210_resource_name_obfuscated_RES_2131952438));
        } else {
            textView2.setVisibility(8);
        }
        ((ImageView) view.findViewById(R.id.start_icon)).setVisibility(8);
        return view;
    }

    @Override // android.widget.ArrayAdapter
    public Object getItem(int i) {
        if (!this.M.isEmpty()) {
            return this.M.get(i);
        }
        if (i < this.K.size()) {
            return this.K.get(i);
        }
        return this.L.get(i - this.K.size());
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.I.inflate(R.layout.f38100_resource_name_obfuscated_RES_2131624119, (ViewGroup) null);
        }
        view.setTag(Integer.valueOf(i));
        LF lf = (LF) getItem(i);
        if (lf == null) {
            return view;
        }
        ((TextView) view.findViewById(R.id.title)).setText(lf.f8414a);
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        }
        return view;
    }

    public boolean isEnabled(int i) {
        LF lf = (LF) getItem(i);
        return (lf == null || lf.c == 0) ? false : true;
    }
}
