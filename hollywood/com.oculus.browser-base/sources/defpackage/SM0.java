package defpackage;

import J.N;
import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ListAdapter;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.contextmenu.RevampedContextMenuListView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: SM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SM0 implements AbstractC3299jz {

    /* renamed from: a  reason: collision with root package name */
    public WebContents f8892a;
    public AbstractC6022zx1 b;
    public GM0 c;
    public TM0 d;
    public RevampedContextMenuListView e;
    public float f;
    public DialogC2104cz g;
    public Runnable h;
    public ContextMenuNativeDelegateImpl i;

    public SM0(float f2, ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl) {
        this.f = f2;
        this.i = contextMenuNativeDelegateImpl;
    }

    public final void a(int i2, Activity activity, Callback callback) {
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            callback.onResult(Integer.valueOf(i2));
            b();
        }
    }

    public final void b() {
        AbstractC6022zx1 zx1 = this.b;
        if (zx1 != null) {
            zx1.destroy();
        }
        GM0 gm0 = this.c;
        if (gm0 != null) {
            gm0.a();
        }
        this.g.dismiss();
    }

    public void c(WindowAndroid windowAndroid, WebContents webContents, ContextMenuParams contextMenuParams, List list, Callback callback, Runnable runnable, Runnable runnable2, W70 w70) {
        int i2;
        int i3;
        View view;
        this.h = runnable2;
        boolean z = contextMenuParams.o == 1;
        Activity activity = (Activity) windowAndroid.s0().get();
        float f2 = activity.getResources().getDisplayMetrics().density;
        float f3 = ((float) contextMenuParams.m) * f2;
        float f4 = ((float) contextMenuParams.n) * f2;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.f37480_resource_name_obfuscated_RES_2131624057, (ViewGroup) null);
        if (!contextMenuParams.j || w70 == null || z) {
            i3 = -1;
            i2 = -1;
        } else {
            this.c = new GM0(activity, inflate.findViewById(R.id.context_menu_chip_anchor_point));
            HM0 hm0 = new HM0(this, w70);
            if (w70.f9130a == null) {
                hm0.onResult(null);
            } else {
                w70.c.a(2, new V70(w70, hm0));
            }
            GM0 gm0 = this.c;
            int dimensionPixelSize = gm0.I.getResources().getDimensionPixelSize(R.dimen.f17180_resource_name_obfuscated_RES_2131165337) + (gm0.I.getResources().getDimensionPixelSize(R.dimen.f17730_resource_name_obfuscated_RES_2131165392) * 2);
            i2 = dimensionPixelSize;
            i3 = dimensionPixelSize / 2;
        }
        if (z) {
            view = LayoutInflater.from(activity).inflate(R.layout.f37470_resource_name_obfuscated_RES_2131624056, (ViewGroup) null);
        } else {
            view = ((ViewStub) inflate.findViewById(R.id.context_menu_stub)).inflate();
        }
        DialogC2104cz czVar = new DialogC2104cz(activity, R.style.f72700_resource_name_obfuscated_RES_2132017843, f3, f4, this.f, i3, i2, inflate, view, z);
        czVar.setContentView(inflate);
        this.g = czVar;
        czVar.setOnShowListener(new IM0(runnable));
        this.g.setOnDismissListener(new JM0(this));
        this.f8892a = webContents;
        this.d = new TM0(activity, contextMenuParams.i ? N.MO0TyD6h(webContents, contextMenuParams.c) : 0, contextMenuParams, Profile.a(this.f8892a), this.i);
        C4935tb0 tb0 = new C4935tb0();
        tb0.q(new C4765sb0(1, this.d.f8954a));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            tb0.q(new C4765sb0(0, new UH0(new KH0[0])));
            int size = tb0.G.size();
            AbstractC1965c90 c90 = (AbstractC1965c90) ((FW0) ((Pair) it.next()).second);
            Iterator it2 = c90.iterator();
            int i4 = size;
            while (it2.hasNext()) {
                tb0.G.add(i4, it2.next());
                i4++;
            }
            tb0.o(size, c90.size());
        }
        Iterator it3 = tb0.iterator();
        while (it3.hasNext()) {
            C4765sb0 sb0 = (C4765sb0) it3.next();
            if (sb0.f11283a == 3) {
                sb0.b.m(ZM0.g, new PM0(this, sb0, activity, callback));
            }
        }
        QM0 qm0 = new QM0(this, tb0);
        RevampedContextMenuListView revampedContextMenuListView = (RevampedContextMenuListView) view.findViewById(R.id.context_menu_list_view);
        this.e = revampedContextMenuListView;
        revampedContextMenuListView.setAdapter((ListAdapter) qm0);
        qm0.a(1, new L70(R.layout.f41190_resource_name_obfuscated_RES_2131624428), new KM0());
        qm0.a(0, new L70(R.layout.f36850_resource_name_obfuscated_RES_2131623994), new LM0());
        qm0.a(2, new L70(R.layout.f41200_resource_name_obfuscated_RES_2131624429), new MM0());
        qm0.a(3, new L70(R.layout.f41210_resource_name_obfuscated_RES_2131624430), new NM0());
        this.e.setOnItemClickListener(new OM0(this, activity, callback));
        this.b = new RM0(this, this.f8892a);
        this.g.show();
    }
}
