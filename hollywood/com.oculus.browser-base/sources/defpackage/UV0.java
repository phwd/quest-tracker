package defpackage;

import J.N;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: UV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UV0 extends AbstractC2032cb {
    public final /* synthetic */ TextView i;
    public final /* synthetic */ SigninFragmentBase j;

    public UV0(SigninFragmentBase signinFragmentBase, TextView textView) {
        this.j = signinFragmentBase;
        this.i = textView;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return AccountManagerFacadeProvider.getInstance().f(this.j.F0);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        String str = (String) obj;
        SigninFragmentBase signinFragmentBase = this.j;
        C3296jy jyVar = signinFragmentBase.C0;
        TextView textView = this.i;
        View[] viewArr = {signinFragmentBase.B0};
        int i2 = ((C3125iy) jyVar.b.get(textView)).b;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < 1; i3++) {
            jyVar.a(viewArr[i3], arrayList2);
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            if (view instanceof TextView) {
                int i4 = ((C3125iy) jyVar.b.get((TextView) view)).b;
                if (i4 != 0) {
                    arrayList.add(Integer.valueOf(i4));
                }
            }
        }
        Object obj2 = ThreadUtils.f10596a;
        if (C2955hy.f10114a == null) {
            C2955hy.f10114a = new C2955hy();
        }
        C2955hy hyVar = C2955hy.f10114a;
        Objects.requireNonNull(hyVar);
        int[] iArr = new int[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            iArr[i5] = ((Integer) arrayList.get(i5)).intValue();
        }
        N.MswahTi8(hyVar, Profile.b(), str, 0, iArr, i2);
    }
}
