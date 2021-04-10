package defpackage;

import android.content.Context;
import android.content.res.Resources;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.bookmarks.BookmarkActionBar;

/* renamed from: UI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class UI extends AbstractC5750yK0 {
    public final Context I;

    /* renamed from: J  reason: collision with root package name */
    public C5533x40 f9020J;
    public AbstractC4853t40 K;
    public List L;
    public RecyclerView M;
    public final int N;
    public final float O;
    public E60 P;
    public int Q;
    public C1322Vq0 R = new C1322Vq0();

    public UI(Context context) {
        this.I = context;
        Resources resources = context.getResources();
        this.N = AbstractC1331Vv.h(resources.getColor(R.color.f10940_resource_name_obfuscated_RES_2131099784), resources.getInteger(R.integer.f35890_resource_name_obfuscated_RES_2131492886));
        this.O = resources.getDimension(R.dimen.f20350_resource_name_obfuscated_RES_2131165654);
    }

    public static void s(UI ui, boolean z) {
        Iterator it = ui.R.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                BookmarkActionBar bookmarkActionBar = (BookmarkActionBar) ((QI) uq0.next());
                ((C4616ri0) bookmarkActionBar.p()).setGroupEnabled(R.id.selection_mode_menu_group, !z);
                BookmarkActionBar bookmarkActionBar2 = null;
                bookmarkActionBar.E(z ? null : bookmarkActionBar);
                if (!z) {
                    bookmarkActionBar2 = bookmarkActionBar;
                }
                bookmarkActionBar.o0 = bookmarkActionBar2;
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.L.size();
    }

    @Override // defpackage.AbstractC5750yK0
    public void i(RecyclerView recyclerView) {
        this.M = recyclerView;
    }

    @Override // defpackage.AbstractC5750yK0
    public void n(RecyclerView recyclerView) {
        this.M = null;
    }
}
