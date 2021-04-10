package defpackage;

import android.view.View;
import android.widget.AdapterView;
import org.chromium.components.media_router.MediaRouteChooserDialogManager$Fragment;

/* renamed from: mf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3753mf0 implements AdapterView.OnItemClickListener {
    public final MediaRouteChooserDialogManager$Fragment F;

    public C3753mf0(MediaRouteChooserDialogManager$Fragment mediaRouteChooserDialogManager$Fragment) {
        this.F = mediaRouteChooserDialogManager$Fragment;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        MediaRouteChooserDialogManager$Fragment mediaRouteChooserDialogManager$Fragment = this.F;
        int i2 = MediaRouteChooserDialogManager$Fragment.P0;
        mediaRouteChooserDialogManager$Fragment.o1(adapterView, i);
    }
}
