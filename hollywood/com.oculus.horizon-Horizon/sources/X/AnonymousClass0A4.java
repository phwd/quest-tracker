package X;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0A4  reason: invalid class name */
public class AnonymousClass0A4 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransitionImpl$2";
    public final /* synthetic */ AnonymousClass0A6 A00;
    public final /* synthetic */ ArrayList A01;
    public final /* synthetic */ Map A02;

    public AnonymousClass0A4(AnonymousClass0A6 r1, ArrayList arrayList, Map map) {
        this.A00 = r1;
        this.A01 = arrayList;
        this.A02 = map;
    }

    public final void run() {
        String str;
        ArrayList arrayList = this.A01;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayList.get(i);
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                Iterator it = this.A02.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (transitionName.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                view.setTransitionName(str);
            }
        }
    }
}
