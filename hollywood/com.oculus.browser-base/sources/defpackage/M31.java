package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: M31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M31 extends BaseAdapter {
    public LayoutInflater F;
    public final /* synthetic */ N31 G;

    public M31(N31 n31, L31 l31) {
        this.G = n31;
        this.F = (LayoutInflater) n31.F.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.G.O;
    }

    public Object getItem(int i) {
        return this.G.b(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (textView == null) {
            textView = (TextView) this.F.inflate(R.layout.f41910_resource_name_obfuscated_RES_2131624500, viewGroup, false);
        }
        textView.setText(this.G.c(i));
        return textView;
    }
}
