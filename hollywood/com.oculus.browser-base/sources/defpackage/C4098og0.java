package defpackage;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.mediarouter.app.MediaRouteVolumeSlider;
import com.oculus.browser.R;

/* renamed from: og0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4098og0 extends AbstractC3072ig0 {
    public final TextView d0;
    public final int e0;
    public final /* synthetic */ C4950tg0 f0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4098og0(C4950tg0 tg0, View view) {
        super(tg0.R, view, (ImageButton) view.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R.id.mr_cast_volume_slider));
        this.f0 = tg0;
        this.d0 = (TextView) view.findViewById(R.id.mr_group_volume_route_name);
        Resources resources = tg0.R.Q.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.f21080_resource_name_obfuscated_RES_2131165727, typedValue, true);
        this.e0 = (int) typedValue.getDimension(displayMetrics);
    }
}
