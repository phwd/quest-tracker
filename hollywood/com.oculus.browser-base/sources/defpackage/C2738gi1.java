package defpackage;

import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: gi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2738gi1 extends ZR implements AbstractC1649aL0 {
    public final C1547Zh1 G;

    public C2738gi1(C1547Zh1 zh1) {
        this.G = zh1;
        zh1.F.b(this);
    }

    @Override // defpackage.AbstractC1649aL0
    public void b(Object obj, int i, Object obj2) {
        C5637xi1 xi1 = (C5637xi1) obj;
        Void r5 = (Void) obj2;
        C1547Zh1 zh1 = this.G;
        UH0 uh0 = zh1.H;
        QueryTile queryTile = (QueryTile) zh1.G.get(i);
        ((TextView) xi1.G.findViewById(R.id.title)).setText(queryTile.b);
        xi1.G.setOnClickListener(new View$OnClickListenerC5297vi1(uh0, queryTile));
        xi1.x(null);
        ((SZ) uh0.g(AbstractC1704ai1.b)).a(queryTile, new C5467wi1(xi1));
    }

    @Override // defpackage.AbstractC1649aL0
    public void c(Object obj) {
        Objects.requireNonNull((C5637xi1) obj);
    }

    @Override // defpackage.AbstractC1649aL0
    public int getItemViewType(int i) {
        return 0;
    }

    @Override // defpackage.AbstractC1649aL0
    public int h() {
        return this.G.size();
    }
}
