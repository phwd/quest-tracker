package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: ng0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3927ng0 extends XK0 {
    public final View Z;
    public final ImageView a0;
    public final ProgressBar b0;
    public final TextView c0;
    public final float d0;
    public C2392eh0 e0;
    public final /* synthetic */ C4950tg0 f0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3927ng0(C4950tg0 tg0, View view) {
        super(view);
        this.f0 = tg0;
        this.Z = view;
        this.a0 = (ImageView) view.findViewById(R.id.mr_cast_group_icon);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_cast_group_progress_bar);
        this.b0 = progressBar;
        this.c0 = (TextView) view.findViewById(R.id.mr_cast_group_name);
        this.d0 = AbstractC4783sh0.d(tg0.R.Q);
        AbstractC4783sh0.l(tg0.R.Q, progressBar);
    }
}
