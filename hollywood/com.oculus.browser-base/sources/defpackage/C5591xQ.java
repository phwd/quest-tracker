package defpackage;

import J.N;
import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: xQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5591xQ implements TextView.OnEditorActionListener {
    public final /* synthetic */ BQ F;

    public C5591xQ(BQ bq) {
        this.F = bq;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getAction() == 1) {
            return false;
        }
        BQ bq = this.F;
        if (bq.S == null) {
            return false;
        }
        if (bq.W) {
            bq.W = false;
            BQ.a(bq, true);
        } else {
            bq.R.u0().d(this.F.G);
            C3546lQ lQVar = this.F.S;
            N.MNC06_Rq(lQVar.b, lQVar);
            this.F.e0 = true;
        }
        return true;
    }
}
