package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;

/* renamed from: X3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X3 extends ArrayAdapter {
    public final /* synthetic */ AlertController$RecycleListView F;
    public final /* synthetic */ C1598a4 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public X3(C1598a4 a4Var, Context context, int i, int i2, CharSequence[] charSequenceArr, AlertController$RecycleListView alertController$RecycleListView) {
        super(context, i, i2, charSequenceArr);
        this.G = a4Var;
        this.F = alertController$RecycleListView;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        boolean[] zArr = this.G.s;
        if (zArr != null && zArr[i]) {
            this.F.setItemChecked(i, true);
        }
        return view2;
    }
}
