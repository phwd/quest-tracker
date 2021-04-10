package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.oculus.browser.R;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: sv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4824sv extends AbstractC0749Mg {
    public final Q31 g;

    public C4824sv(Context context, AbstractC5531x31 x31, Q31 q31) {
        super(context, x31);
        this.g = q31;
    }

    @Override // defpackage.B31, defpackage.AbstractC0749Mg
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i) {
        byte[] bArr;
        Bitmap decodeByteArray;
        super.a(autocompleteMatch, uh0, i);
        boolean z = autocompleteMatch.f10861a == 19;
        uh0.j(D31.b, !z);
        uh0.m(D31.c, new C31(autocompleteMatch.f));
        uh0.m(D31.d, new C31(autocompleteMatch.d));
        if (autocompleteMatch.f10861a != 27 || (bArr = autocompleteMatch.t) == null || bArr.length <= 0 || (decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length)) == null) {
            C5191v31 c = C5191v31.c(this.f8494a, z ? R.drawable.f30310_resource_name_obfuscated_RES_2131231071 : R.drawable.f32740_resource_name_obfuscated_RES_2131231314);
            c.b = true;
            uh0.m(AbstractC0871Og.f8641a, c.a());
            if (z) {
                k(uh0, autocompleteMatch.j, (X60) this.g.get(), null);
                return;
            }
            return;
        }
        if (decodeByteArray.getWidth() > 0 && decodeByteArray.getHeight() > 0 && (decodeByteArray.getWidth() > this.d || decodeByteArray.getHeight() > this.d)) {
            float max = ((float) this.d) / ((float) Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight()));
            decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray, Math.round(((float) decodeByteArray.getWidth()) * max), Math.round(max * ((float) decodeByteArray.getHeight())), true);
        }
        C5191v31 b = C5191v31.b(this.f8494a, decodeByteArray);
        b.c = true;
        b.d = true;
        uh0.m(AbstractC0871Og.f8641a, b.a());
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 5;
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i) {
        int i2 = autocompleteMatch.f10861a;
        return i2 == 19 || i2 == 26 || i2 == 27;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(D31.g);
    }
}
