package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: dy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2272dy0 extends BaseAdapter {
    public final Context F;
    public final String G;

    public C2272dy0(Context context, String str) {
        this.F = context;
        this.G = str;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public int getCount() {
        return 1;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return view;
        }
        View inflate = LayoutInflater.from(this.F).inflate(R.layout.f40380_resource_name_obfuscated_RES_2131624347, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.password_generation_explanation)).setText(this.G);
        return inflate;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEnabled(int i) {
        return false;
    }
}
