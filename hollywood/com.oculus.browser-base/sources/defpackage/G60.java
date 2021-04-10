package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* renamed from: G60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G60 extends XK0 {
    public TextView Z;
    public TextView a0;
    public ImageView b0;
    public ListMenuButton c0;

    public G60(View view) {
        super(view);
        this.Z = (TextView) view.findViewById(R.id.title);
        this.a0 = (TextView) view.findViewById(R.id.description);
        this.b0 = (ImageView) view.findViewById(R.id.icon_view);
        this.c0 = (ListMenuButton) view.findViewById(R.id.more);
    }
}
