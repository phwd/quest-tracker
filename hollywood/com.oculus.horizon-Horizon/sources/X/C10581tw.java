package X;

import com.facebook.quicklog.EventLevel;
import javax.annotation.Nullable;

/* renamed from: X.1tw  reason: invalid class name and case insensitive filesystem */
public class C10581tw implements AnonymousClass0Sz {
    public final /* synthetic */ AnonymousClass1tm A00;
    public final /* synthetic */ StringBuilder A01;

    public C10581tw(AnonymousClass1tm r1, StringBuilder sb) {
        this.A00 = r1;
        this.A01 = sb;
    }

    @Override // X.AnonymousClass0Sz
    public final void A9l(long j, long j2, @EventLevel int i, String str, @Nullable AnonymousClass0T7 r9, AnonymousClass0VE<?> r10) {
        StringBuilder sb = this.A01;
        sb.append("<p:");
        sb.append(str);
        if (r9 != null) {
            sb.append('=');
            sb.append(r9);
        }
        sb.append(' ');
        sb.append(j);
        sb.append("[ms]>");
    }
}
