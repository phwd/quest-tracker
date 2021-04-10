package defpackage;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* renamed from: Tl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1190Tl0 extends AbstractC2506fJ implements B31 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8980a;
    public final Context b;
    public final AbstractC5531x31 c;
    public final Q31 d;
    public final int e;
    public final int f;
    public final KN0 g;

    public C1190Tl0(Context context, AbstractC5531x31 x31, Q31 q31) {
        this.f8980a = context.getResources().getDimensionPixelSize(R.dimen.f23320_resource_name_obfuscated_RES_2131165951);
        this.b = context;
        this.c = x31;
        this.d = q31;
        this.e = context.getResources().getDimensionPixelSize(R.dimen.f26190_resource_name_obfuscated_RES_2131166238);
        this.f = context.getResources().getDimensionPixelSize(R.dimen.f23310_resource_name_obfuscated_RES_2131165950);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f26160_resource_name_obfuscated_RES_2131166235);
        this.g = new KN0(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, context.getResources().getColor(R.color.f11180_resource_name_obfuscated_RES_2131099808), (float) context.getResources().getDimensionPixelSize(R.dimen.f26180_resource_name_obfuscated_RES_2131166237));
    }

    @Override // defpackage.B31
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i) {
        List list = autocompleteMatch.v;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        X60 x60 = (X60) this.d.get();
        for (int i2 = 0; i2 < size; i2++) {
            UH0 uh02 = new UH0(AbstractC5977zi1.k);
            String str = ((C1408Xc) list.get(i2)).f9218a;
            GURL gurl = ((C1408Xc) list.get(i2)).b;
            uh02.m(AbstractC5977zi1.f11761a, str);
            uh02.l(AbstractC5977zi1.b, 1);
            uh02.m(AbstractC5977zi1.g, new RunnableC0885Ol0(this, gurl));
            uh02.m(AbstractC5977zi1.h, new View$OnClickListenerC0946Pl0(this, autocompleteMatch, i, gurl));
            uh02.m(AbstractC5977zi1.f, this.b.getString(R.string.f45650_resource_name_obfuscated_RES_2131951882, str, gurl.d()));
            uh02.m(AbstractC5977zi1.c, new BitmapDrawable(this.g.b(gurl.h())));
            if (x60 != null) {
                x60.b(((C1408Xc) list.get(i2)).b, this.f, new C1007Ql0(uh02));
            }
            arrayList.add(new C4765sb0(0, uh02));
        }
        uh0.m(AbstractC1539Zf.f9356a, arrayList);
        uh0.m(AbstractC1539Zf.b, this.b.getResources().getString(R.string.f55130_resource_name_obfuscated_RES_2131952830));
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 8;
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i) {
        return autocompleteMatch.f10861a == 29;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(AbstractC1539Zf.d);
    }

    @Override // defpackage.AbstractC2677gJ
    public final int h() {
        return this.f8980a + this.e;
    }
}
