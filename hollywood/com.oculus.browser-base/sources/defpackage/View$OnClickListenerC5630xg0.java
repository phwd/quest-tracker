package defpackage;

import android.view.View;
import androidx.mediarouter.app.MediaRouteExpandCollapseButton;

/* renamed from: xg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5630xg0 implements View.OnClickListener {
    public final /* synthetic */ MediaRouteExpandCollapseButton F;

    public View$OnClickListenerC5630xg0(MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton) {
        this.F = mediaRouteExpandCollapseButton;
    }

    public void onClick(View view) {
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = this.F;
        boolean z = !mediaRouteExpandCollapseButton.L;
        mediaRouteExpandCollapseButton.L = z;
        if (z) {
            mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.H);
            this.F.H.start();
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton2 = this.F;
            mediaRouteExpandCollapseButton2.setContentDescription(mediaRouteExpandCollapseButton2.K);
        } else {
            mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.I);
            this.F.I.start();
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton3 = this.F;
            mediaRouteExpandCollapseButton3.setContentDescription(mediaRouteExpandCollapseButton3.f9478J);
        }
        View.OnClickListener onClickListener = this.F.M;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
