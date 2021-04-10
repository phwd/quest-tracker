package X;

import com.facebook.rti.push.service.FbnsService;

/* renamed from: X.0jU  reason: invalid class name and case insensitive filesystem */
public class C04760jU implements AnonymousClass0WY<Long> {
    public final /* synthetic */ FbnsService A00;

    public C04760jU(FbnsService fbnsService) {
        this.A00 = fbnsService;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0WY
    public final Long get() {
        return Long.valueOf(((long) (1 << EnumC01640Wi.SHARED_SECRET.ordinal())) | 0);
    }
}
