package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import androidx.mediarouter.app.MediaRouteVolumeSlider;
import com.oculus.browser.R;

/* renamed from: ig0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3072ig0 extends XK0 {
    public C2392eh0 Z;
    public final ImageButton a0;
    public final MediaRouteVolumeSlider b0;
    public final /* synthetic */ DialogC5460wg0 c0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractC3072ig0(DialogC5460wg0 wg0, View view, ImageButton imageButton, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
        super(view);
        int i;
        int i2;
        this.c0 = wg0;
        this.a0 = imageButton;
        this.b0 = mediaRouteVolumeSlider;
        imageButton.setImageDrawable(AbstractC4783sh0.f(wg0.Q, R.drawable.f33890_resource_name_obfuscated_RES_2131231429));
        Context context = wg0.Q;
        if (AbstractC4783sh0.j(context)) {
            Object obj = K2.f8337a;
            i = context.getColor(R.color.f13690_resource_name_obfuscated_RES_2131100059);
            i2 = context.getColor(R.color.f13670_resource_name_obfuscated_RES_2131100057);
        } else {
            Object obj2 = K2.f8337a;
            i = context.getColor(R.color.f13680_resource_name_obfuscated_RES_2131100058);
            i2 = context.getColor(R.color.f13660_resource_name_obfuscated_RES_2131100056);
        }
        mediaRouteVolumeSlider.a(i, i2);
    }

    public void x(C2392eh0 eh0) {
        this.Z = eh0;
        int i = eh0.o;
        this.a0.setActivated(i == 0);
        this.a0.setOnClickListener(new View$OnClickListenerC2902hg0(this));
        this.b0.setTag(this.Z);
        this.b0.setMax(eh0.p);
        this.b0.setProgress(i);
        this.b0.setOnSeekBarChangeListener(this.c0.X);
    }

    public void y(boolean z) {
        if (this.a0.isActivated() != z) {
            this.a0.setActivated(z);
            if (z) {
                this.c0.a0.put(this.Z.c, Integer.valueOf(this.b0.getProgress()));
            } else {
                this.c0.a0.remove(this.Z.c);
            }
        }
    }
}
