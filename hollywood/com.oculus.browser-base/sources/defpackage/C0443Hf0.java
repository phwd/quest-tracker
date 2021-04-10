package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.mediarouter.app.MediaRouteVolumeSlider;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* renamed from: Hf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0443Hf0 extends ArrayAdapter {
    public final float F;
    public final /* synthetic */ DialogC0504If0 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0443Hf0(DialogC0504If0 if0, Context context, List list) {
        super(context, 0, list);
        this.G = if0;
        this.F = AbstractC4783sh0.d(context);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        int i3 = 0;
        if (view == null) {
            view = AbstractC2531fV.r(viewGroup, R.layout.f39430_resource_name_obfuscated_RES_2131624252, viewGroup, false);
        } else {
            DialogC0504If0 if0 = this.G;
            Objects.requireNonNull(if0);
            DialogC0504If0.q((LinearLayout) view.findViewById(R.id.volume_item_container), if0.s0);
            View findViewById = view.findViewById(R.id.mr_volume_item_icon);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            int i4 = if0.r0;
            layoutParams.width = i4;
            layoutParams.height = i4;
            findViewById.setLayoutParams(layoutParams);
        }
        C2392eh0 eh0 = (C2392eh0) getItem(i);
        if (eh0 != null) {
            boolean z = eh0.g;
            TextView textView = (TextView) view.findViewById(R.id.mr_name);
            textView.setEnabled(z);
            textView.setText(eh0.d);
            MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(R.id.mr_volume_slider);
            AbstractC4783sh0.m(viewGroup.getContext(), mediaRouteVolumeSlider, this.G.i0);
            mediaRouteVolumeSlider.setTag(eh0);
            this.G.v0.put(eh0, mediaRouteVolumeSlider);
            mediaRouteVolumeSlider.b(!z);
            mediaRouteVolumeSlider.setEnabled(z);
            if (z) {
                if (this.G.d0 && eh0.n == 1) {
                    mediaRouteVolumeSlider.setMax(eh0.p);
                    mediaRouteVolumeSlider.setProgress(eh0.o);
                    mediaRouteVolumeSlider.setOnSeekBarChangeListener(this.G.p0);
                } else {
                    mediaRouteVolumeSlider.setMax(100);
                    mediaRouteVolumeSlider.setProgress(100);
                    mediaRouteVolumeSlider.setEnabled(false);
                }
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.mr_volume_item_icon);
            if (z) {
                i2 = 255;
            } else {
                i2 = (int) (this.F * 255.0f);
            }
            imageView.setAlpha(i2);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.volume_item_container);
            if (this.G.n0.contains(eh0)) {
                i3 = 4;
            }
            linearLayout.setVisibility(i3);
            Set set = this.G.l0;
            if (set != null && set.contains(eh0)) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                alphaAnimation.setDuration(0);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                view.clearAnimation();
                view.startAnimation(alphaAnimation);
            }
        }
        return view;
    }

    public boolean isEnabled(int i) {
        return false;
    }
}
