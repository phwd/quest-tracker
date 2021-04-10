package defpackage;

import J.N;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* renamed from: kH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3354kH extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3696mH f10269a;

    public C3354kH(C3696mH mHVar) {
        this.f10269a = mHVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str;
        C3696mH mHVar = this.f10269a;
        Objects.requireNonNull(mHVar);
        Iterator it = ((ArrayList) obj).iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            LF lf = (LF) ((LF) it.next()).clone();
            int i3 = lf.e;
            if (i3 == 0) {
                lf.f8414a = mHVar.H.getString(R.string.f54520_resource_name_obfuscated_RES_2131952769);
                mHVar.K.add(lf);
            } else if (i3 == 1) {
                if (i2 > 0) {
                    str = mHVar.H.getString(R.string.f51800_resource_name_obfuscated_RES_2131952497, Integer.valueOf(i2 + 1));
                } else {
                    str = mHVar.H.getString(R.string.f51790_resource_name_obfuscated_RES_2131952496);
                }
                lf.f8414a = str;
                mHVar.L.add(lf);
                i2++;
            } else if (i3 == 2) {
                lf.f8414a = mHVar.H.getString(R.string.f51180_resource_name_obfuscated_RES_2131952435);
                mHVar.M.add(lf);
            }
        }
        if (mHVar.M.isEmpty()) {
            int i4 = -1;
            String M4fixBWD = N.M4fixBWD();
            while (true) {
                if (i >= mHVar.getCount()) {
                    break;
                }
                LF lf2 = (LF) mHVar.getItem(i);
                if (lf2 != null && M4fixBWD.equals(lf2.b)) {
                    i4 = i;
                    break;
                }
                i++;
            }
            mHVar.G = i4;
        }
        mHVar.notifyDataSetChanged();
        AbstractC3525lH lHVar = mHVar.f10410J;
        if (lHVar != null) {
            lHVar.b();
        }
    }
}
