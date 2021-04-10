package defpackage;

import android.content.Context;
import com.oculus.browser.R;

/* renamed from: sm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4799sm1 extends AbstractC4329q0 {
    public C5010u0 c;
    public final C4670s0 d;

    public C4799sm1(Context context, MK0 mk0) {
        super("Touch To Fill", AbstractC5544x8.a(context, R.drawable.f30070_resource_name_obfuscated_RES_2131231047), "", "Touch to Fill sheet is open", R.layout.f40240_resource_name_obfuscated_RES_2131624333, 4, mk0);
        C5010u0 u0Var = new C5010u0();
        this.c = u0Var;
        this.d = new C4670s0(u0Var, 4, 5, 1, null);
    }

    @Override // defpackage.AbstractC4329q0
    public C4670s0 a() {
        return this.d;
    }
}
