package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.policy.CombinedPolicyProvider;
import org.chromium.components.policy.PolicyConverter;
import org.json.JSONException;

/* renamed from: f  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2448f {

    /* renamed from: a  reason: collision with root package name */
    public CombinedPolicyProvider f9891a;
    public int b = -1;
    public final Context c;
    public final BroadcastReceiver d = new C2277e(this);

    public AbstractC2448f(Context context) {
        this.c = context;
    }

    public void a(Bundle bundle) {
        Object obj = ThreadUtils.f10596a;
        CombinedPolicyProvider combinedPolicyProvider = this.f9891a;
        combinedPolicyProvider.e.set(this.b, bundle);
        for (Bundle bundle2 : combinedPolicyProvider.e) {
            if (bundle2 == null) {
                return;
            }
        }
        if (combinedPolicyProvider.b != 0) {
            for (Bundle bundle3 : combinedPolicyProvider.e) {
                for (String str : bundle3.keySet()) {
                    PolicyConverter policyConverter = combinedPolicyProvider.c;
                    Object obj2 = bundle3.get(str);
                    Objects.requireNonNull(policyConverter);
                    if (obj2 instanceof Boolean) {
                        N.MNcVehwq(policyConverter.f10881a, policyConverter, str, ((Boolean) obj2).booleanValue());
                    } else if (obj2 instanceof String) {
                        N.Mu_Zn154(policyConverter.f10881a, policyConverter, str, (String) obj2);
                    } else if (obj2 instanceof Integer) {
                        N.MX$FOTbg(policyConverter.f10881a, policyConverter, str, ((Integer) obj2).intValue());
                    } else if (obj2 instanceof String[]) {
                        N.MLFU3fY7(policyConverter.f10881a, policyConverter, str, (String[]) obj2);
                    } else if (obj2 instanceof Bundle) {
                        Bundle bundle4 = (Bundle) obj2;
                        try {
                            N.Mu_Zn154(policyConverter.f10881a, policyConverter, str, policyConverter.b(bundle4).toString());
                        } catch (JSONException unused) {
                            StringBuilder i = AbstractC2531fV.i("Invalid bundle in app restrictions ");
                            i.append(bundle4.toString());
                            i.append(" for key ");
                            i.append(str);
                            AbstractC1220Ua0.f("PolicyConverter", i.toString(), new Object[0]);
                        }
                    } else if (obj2 instanceof Bundle[]) {
                        Bundle[] bundleArr = (Bundle[]) obj2;
                        try {
                            N.Mu_Zn154(policyConverter.f10881a, policyConverter, str, policyConverter.a(bundleArr).toString());
                        } catch (JSONException unused2) {
                            StringBuilder i2 = AbstractC2531fV.i("Invalid bundle array in app restrictions ");
                            i2.append(Arrays.toString(bundleArr));
                            i2.append(" for key ");
                            i2.append(str);
                            AbstractC1220Ua0.f("PolicyConverter", i2.toString(), new Object[0]);
                        }
                    }
                }
            }
            N.M81oD3lB(combinedPolicyProvider.b, CombinedPolicyProvider.a());
        }
    }

    public void b() {
        Bundle bundle;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        System.currentTimeMillis();
        String packageName = this.c.getPackageName();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            bundle = ((K9) this).e.getApplicationRestrictions(packageName);
        } catch (SecurityException unused) {
            bundle = new Bundle();
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        AbstractC3364kK0.k("Enterprise.AppRestrictionLoadTime2", elapsedRealtime2);
        if (bundle.isEmpty()) {
            AbstractC3364kK0.k("Enterprise.AppRestrictionLoadTime2.EmptyBundle", elapsedRealtime2);
        } else {
            AbstractC3364kK0.k("Enterprise.AppRestrictionLoadTime2.NonEmptyBundle", elapsedRealtime2);
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        a(bundle);
    }
}
