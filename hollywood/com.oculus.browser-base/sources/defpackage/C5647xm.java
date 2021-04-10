package defpackage;

import J.N;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.content_public.browser.WebContents;

/* renamed from: xm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5647xm extends AbstractC2168dK implements AbstractC4532rB {
    public boolean A;
    public final WebContents c;
    public final List d = new ArrayList();
    public final Map e = new HashMap();
    public final S3 f;
    public final Map g;
    public final Set h;
    public final Set i;
    public final List j;
    public final Handler k;
    public final AbstractC4559rK l;
    public final C4286pm m;
    public final AbstractC2032cb n;
    public C4729sK o;
    public C4729sK p;
    public C4729sK q;
    public C4729sK r;
    public C4729sK s;
    public C4729sK t;
    public C4729sK u;
    public C4703sB v;
    public AbstractC4559rK w;
    public boolean x;
    public int y;
    public int z;

    public C5647xm(WebContents webContents, S3 s3, boolean z2) {
        this.c = webContents;
        this.f = s3;
        PersonalDataManager c2 = PersonalDataManager.c();
        Objects.requireNonNull(c2);
        Object obj = ThreadUtils.f10596a;
        ArrayList f2 = c2.f(N.MfY8Rzvb(c2.b, c2, true, z2, false), N.M2$wnjuR(c2.b, c2));
        boolean z3 = false;
        for (int i2 = 0; i2 < f2.size(); i2++) {
            PersonalDataManager.AutofillProfile autofillProfile = (PersonalDataManager.AutofillProfile) f2.get(i2);
            if (autofillProfile.c && !TextUtils.isEmpty(autofillProfile.getStreetAddress())) {
                this.d.add(autofillProfile);
                Pair j2 = C2892hd.j(C2892hd.g(autofillProfile, 1));
                if (((Integer) j2.first).intValue() != 0) {
                    this.e.put(autofillProfile.getGUID(), (Integer) j2.first);
                }
            }
        }
        Collections.sort(this.d, new C4115om());
        HashMap hashMap = new HashMap();
        this.g = hashMap;
        hashMap.put("amex", new C5477wm(R.drawable.f28140_resource_name_obfuscated_RES_2131230854, R.string.f47180_resource_name_obfuscated_RES_2131952035));
        hashMap.put("diners", new C5477wm(R.drawable.f29120_resource_name_obfuscated_RES_2131230952, R.string.f47190_resource_name_obfuscated_RES_2131952036));
        hashMap.put("discover", new C5477wm(R.drawable.f29130_resource_name_obfuscated_RES_2131230953, R.string.f47200_resource_name_obfuscated_RES_2131952037));
        hashMap.put("jcb", new C5477wm(R.drawable.f33480_resource_name_obfuscated_RES_2131231388, R.string.f47220_resource_name_obfuscated_RES_2131952039));
        hashMap.put("mastercard", new C5477wm(R.drawable.f33660_resource_name_obfuscated_RES_2131231406, R.string.f47230_resource_name_obfuscated_RES_2131952040));
        hashMap.put("mir", new C5477wm(R.drawable.f33740_resource_name_obfuscated_RES_2131231414, R.string.f47240_resource_name_obfuscated_RES_2131952041));
        hashMap.put("unionpay", new C5477wm(R.drawable.f35500_resource_name_obfuscated_RES_2131231590, R.string.f47250_resource_name_obfuscated_RES_2131952042));
        hashMap.put("visa", new C5477wm(R.drawable.f35530_resource_name_obfuscated_RES_2131231593, R.string.f47260_resource_name_obfuscated_RES_2131952043));
        this.h = new HashSet();
        this.i = new HashSet();
        this.j = new ArrayList();
        this.k = new Handler();
        this.l = new C4627rm(this);
        this.m = new C4286pm(this);
        C4797sm smVar = new C4797sm(this);
        this.n = smVar;
        Executor executor = AbstractC2032cb.f9616a;
        smVar.f();
        ((ExecutorC1463Ya) executor).execute(smVar.e);
        ChromeActivity J0 = ChromeActivity.J0(this.c);
        if (J0 != null && J0.Q0().a()) {
            z3 = true;
        }
        this.A = z3;
    }

    public static PersonalDataManager.AutofillProfile c(List list, String str) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (((PersonalDataManager.AutofillProfile) list.get(i2)).getGUID().equals(str)) {
                return (PersonalDataManager.AutofillProfile) list.get(i2);
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC4532rB
    public void a() {
        this.x = false;
    }

    public void d(C2892hd hdVar) {
        if (hdVar.f9599a) {
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                if (TextUtils.equals(((PersonalDataManager.AutofillProfile) this.d.get(i2)).getGUID(), hdVar.g)) {
                    this.d.set(i2, hdVar.m);
                    this.e.remove(hdVar.g);
                    return;
                }
            }
            hdVar.l();
            this.d.add(0, new PersonalDataManager.AutofillProfile(hdVar.m));
        }
    }
}
