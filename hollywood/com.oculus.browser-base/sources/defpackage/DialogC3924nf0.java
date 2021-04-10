package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import com.oculus.browser.R;
import org.chromium.components.media_router.MediaRouteChooserDialogManager$Fragment;

/* renamed from: nf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC3924nf0 extends DialogC3240jf0 {
    public final /* synthetic */ MediaRouteChooserDialogManager$Fragment R;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DialogC3924nf0(MediaRouteChooserDialogManager$Fragment mediaRouteChooserDialogManager$Fragment, Context context, int i) {
        super(context, i);
        this.R = mediaRouteChooserDialogManager$Fragment;
    }

    @Override // defpackage.DialogC3240jf0, defpackage.AbstractDialogC3498l8
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ListView listView = (ListView) findViewById(R.id.mr_chooser_list);
        if (listView != null) {
            listView.setOnItemClickListener(new C3753mf0(this.R));
        }
    }
}
