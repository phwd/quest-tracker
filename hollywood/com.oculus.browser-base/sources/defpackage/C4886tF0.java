package defpackage;

import android.util.SparseArray;
import android.view.View;
import com.oculus.browser.R;

/* renamed from: tF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4886tF0 extends XK0 {
    public final SparseArray Z;
    public boolean a0;
    public boolean b0;

    public C4886tF0(View view) {
        super(view);
        SparseArray sparseArray = new SparseArray(4);
        this.Z = sparseArray;
        sparseArray.put(16908310, view.findViewById(16908310));
        sparseArray.put(16908304, view.findViewById(16908304));
        sparseArray.put(16908294, view.findViewById(16908294));
        sparseArray.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        sparseArray.put(16908350, view.findViewById(16908350));
    }

    public View x(int i) {
        View view = (View) this.Z.get(i);
        if (view != null) {
            return view;
        }
        View findViewById = this.G.findViewById(i);
        if (findViewById != null) {
            this.Z.put(i, findViewById);
        }
        return findViewById;
    }
}
