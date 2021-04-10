package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: ri1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4617ri1 extends AbstractC2506fJ implements B31 {

    /* renamed from: a  reason: collision with root package name */
    public List f11216a;
    public final Callback b;
    public final int c;

    public C4617ri1(Context context, Callback callback) {
        this.b = callback;
        this.c = context.getResources().getDimensionPixelSize(R.dimen.f23260_resource_name_obfuscated_RES_2131165945);
    }

    @Override // defpackage.B31
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i) {
        ArrayList arrayList;
        List list = autocompleteMatch.s;
        List list2 = this.f11216a;
        if (list2 == null || !list2.equals(list)) {
            if (list == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(list);
            }
            this.f11216a = arrayList;
            this.b.onResult(list);
        }
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void b(boolean z) {
        if (!z) {
            this.f11216a = null;
            this.b.onResult(new ArrayList());
        }
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void c() {
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 6;
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void e(UH0 uh0) {
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i) {
        return autocompleteMatch.f10861a == 28;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(AbstractC4787si1.f11293a);
    }

    @Override // defpackage.AbstractC2677gJ
    public int h() {
        return this.c;
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void i() {
    }
}
