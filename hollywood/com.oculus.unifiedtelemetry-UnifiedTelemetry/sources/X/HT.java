package X;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.os.PowerManager;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class HT {
    @GuardedBy("StartServiceParams.this")
    public static List<Handler> A09;
    public PowerManager.WakeLock A00;
    @Nullable
    public HA A01;
    public final int A02;
    public final Context A03;
    @Nullable
    public final Bundle A04;
    @Nullable
    public final Messenger A05;
    public final H8 A06;
    @Nullable
    public final HQ A07;
    @Nullable
    public final String A08;

    public static HT A00(Bundle bundle, Context context) throws Gc {
        HQ hq;
        Messenger messenger = (Messenger) bundle.getParcelable("_messenger");
        Bundle bundle2 = bundle.getBundle("_extras");
        String string = bundle.getString("_hack_action");
        int i = bundle.getInt("_job_id", -1);
        if (i != -1) {
            Bundle bundle3 = bundle.getBundle("_fallback_config");
            H8 h8 = new H8(bundle.getBundle("_upload_job_config"));
            if (bundle3 != null) {
                hq = new HQ(bundle3.getLong("min_delay_ms", -1), bundle3.getLong("max_delay_ms", -1), bundle3.getString(OculusAuthorizationInterceptor.EXTRA_ACTION));
            } else {
                hq = null;
            }
            return new HT(messenger, bundle2, string, h8, i, hq, context);
        }
        StringBuilder sb = new StringBuilder("_job_id is ");
        sb.append(bundle.get("_job_id"));
        throw new Gc(sb.toString());
    }

    public static List<Handler> A01() {
        List<Handler> list;
        synchronized (HT.class) {
            list = A09;
            if (list == null) {
                list = Collections.synchronizedList(new ArrayList(1));
                A09 = list;
            }
        }
        return list;
    }

    public final Bundle A02() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("_messenger", this.A05);
        bundle.putBundle("_extras", this.A04);
        bundle.putString("_hack_action", this.A08);
        bundle.putBundle("_upload_job_config", new Bundle((Bundle) this.A06.A00(new C0253Yb(new Bundle()))));
        bundle.putInt("_job_id", this.A02);
        HQ hq = this.A07;
        if (hq != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("min_delay_ms", hq.A01);
            bundle2.putLong("max_delay_ms", hq.A00);
            bundle2.putString(OculusAuthorizationInterceptor.EXTRA_ACTION, hq.A02);
            bundle2.putInt("__VERSION_CODE", IK.A01());
            bundle.putBundle("_fallback_config", bundle2);
        }
        return bundle;
    }

    public HT(@Nullable Messenger messenger, @Nullable Bundle bundle, @Nullable String str, H8 h8, int i, @Nullable HQ hq, Context context) {
        this.A05 = messenger;
        this.A04 = bundle;
        this.A08 = str;
        this.A06 = h8;
        this.A02 = i;
        this.A03 = context;
        this.A07 = hq;
    }
}
