package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import org.chromium.chrome.browser.webapps.PwaBottomSheetController;

/* renamed from: OI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OI0 extends AbstractC5750yK0 {
    public Context I;

    /* renamed from: J  reason: collision with root package name */
    public ArrayList f8617J = new ArrayList();
    public final /* synthetic */ PwaBottomSheetController K;

    public OI0(PwaBottomSheetController pwaBottomSheetController, Context context) {
        this.K = pwaBottomSheetController;
        this.I = context;
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        ArrayList arrayList = this.f8617J;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        ((ImageView) ((NI0) xk0).G).setImageBitmap((Bitmap) this.f8617J.get(i));
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        return new NI0(this.K, new ImageView(this.I));
    }
}
