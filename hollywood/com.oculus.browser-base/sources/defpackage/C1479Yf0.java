package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Yf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1479Yf0 extends XK0 {
    public final View Z;
    public final ImageView a0;
    public final ProgressBar b0;
    public final TextView c0;
    public final /* synthetic */ C1540Zf0 d0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1479Yf0(C1540Zf0 zf0, View view) {
        super(view);
        this.d0 = zf0;
        this.Z = view;
        this.a0 = (ImageView) view.findViewById(R.id.mr_picker_route_icon);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_picker_route_progress_bar);
        this.b0 = progressBar;
        this.c0 = (TextView) view.findViewById(R.id.mr_picker_route_name);
        AbstractC4783sh0.l(zf0.O.f9555J, progressBar);
    }
}
