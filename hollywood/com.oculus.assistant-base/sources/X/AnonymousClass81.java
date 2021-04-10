package X;

import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import android.os.PowerManager;

/* renamed from: X.81  reason: invalid class name */
public final class AnonymousClass81 {
    public PowerManager.WakeLock A00;
    public AnonymousClass7l A01;
    public final int A02;
    public final Context A03;
    public final Bundle A04;
    public final Messenger A05;
    public final AnonymousClass7j A06;
    public final AnonymousClass7y A07;
    public final String A08;

    public static AnonymousClass81 A00(Bundle bundle, Context context) {
        AnonymousClass7y r10;
        Messenger messenger = (Messenger) bundle.getParcelable("_messenger");
        Bundle bundle2 = bundle.getBundle("_extras");
        String string = bundle.getString("_hack_action");
        int i = bundle.getInt("_job_id", -1);
        if (i != -1) {
            Bundle bundle3 = bundle.getBundle("_fallback_config");
            AnonymousClass7j r8 = new AnonymousClass7j(new C0691fS(bundle.getBundle("_upload_job_config")));
            if (bundle3 != null) {
                r10 = new AnonymousClass7y(bundle3.getLong("min_delay_ms", -1), bundle3.getLong("max_delay_ms", -1), bundle3.getString("action"));
            } else {
                r10 = null;
            }
            return new AnonymousClass81(messenger, bundle2, string, r8, i, r10, context);
        }
        StringBuilder sb = new StringBuilder("_job_id is ");
        sb.append(bundle.get("_job_id"));
        throw new AnonymousClass7D(sb.toString());
    }

    public AnonymousClass81(Messenger messenger, Bundle bundle, String str, AnonymousClass7j r4, int i, AnonymousClass7y r6, Context context) {
        this.A05 = messenger;
        this.A04 = bundle;
        this.A08 = str;
        this.A06 = r4;
        this.A02 = i;
        this.A03 = context;
        this.A07 = r6;
    }
}
