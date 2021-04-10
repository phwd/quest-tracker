package defpackage;

import android.content.Context;
import android.widget.LinearLayout;
import java.util.Iterator;
import java.util.List;

/* renamed from: Fd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0314Fd extends LinearLayout {
    public C0314Fd(Context context, List list, AbstractC0253Ed ed) {
        super(context);
        setOrientation(1);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            addView(new View$OnClickListenerC0192Dd(this, context, (AbstractC2335eJ) it.next(), ed));
        }
    }
}
