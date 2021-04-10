package X;

import com.facebook.rti.push.service.FbnsService;

/* renamed from: X.0uv  reason: invalid class name and case insensitive filesystem */
public class C07500uv implements AbstractC09610zk<Long> {
    public final /* synthetic */ FbnsService A00;

    public C07500uv(FbnsService fbnsService) {
        this.A00 = fbnsService;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC09610zk
    public final Long get() {
        return Long.valueOf(((long) (1 << AnonymousClass0vE.SHARED_SECRET.ordinal())) | 0);
    }
}
