package X;

import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import android.os.PowerManager;
import javax.annotation.Nullable;

/* renamed from: X.0Hv  reason: invalid class name and case insensitive filesystem */
public class C00970Hv {
    public PowerManager.WakeLock A00;
    @Nullable
    public AnonymousClass0HZ A01;
    public final int A02;
    public final Context A03;
    @Nullable
    public final Bundle A04;
    @Nullable
    public final Messenger A05;
    public final AnonymousClass0HX A06;
    @Nullable
    public final AnonymousClass0Hr A07;
    @Nullable
    public final String A08;

    public static C00970Hv A00(Bundle bundle, Context context) throws C00860Gv {
        AnonymousClass0Hr r10;
        Messenger messenger = (Messenger) bundle.getParcelable("_messenger");
        Bundle bundle2 = bundle.getBundle("_extras");
        String string = bundle.getString("_hack_action");
        int i = bundle.getInt("_job_id", -1);
        if (i != -1) {
            Bundle bundle3 = bundle.getBundle("_fallback_config");
            AnonymousClass0HX r8 = new AnonymousClass0HX(bundle.getBundle("_upload_job_config"));
            if (bundle3 != null) {
                r10 = new AnonymousClass0Hr(bundle3.getLong("min_delay_ms", -1), bundle3.getLong("max_delay_ms", -1), bundle3.getString("action"));
            } else {
                r10 = null;
            }
            return new C00970Hv(messenger, bundle2, string, r8, i, r10, context);
        }
        StringBuilder sb = new StringBuilder("_job_id is ");
        sb.append(bundle.get("_job_id"));
        throw new C00860Gv(sb.toString());
    }

    public C00970Hv(@Nullable Messenger messenger, @Nullable Bundle bundle, @Nullable String str, AnonymousClass0HX r4, int i, @Nullable AnonymousClass0Hr r6, Context context) {
        this.A05 = messenger;
        this.A04 = bundle;
        this.A08 = str;
        this.A06 = r4;
        this.A02 = i;
        this.A03 = context;
        this.A07 = r6;
    }
}
