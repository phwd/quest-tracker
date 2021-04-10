package X;

import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ContextScoped;

/* renamed from: X.tF  reason: case insensitive filesystem */
public final class C0520tF extends AbstractC0099Hz implements AbstractC0242Xp {
    @Override // X.AbstractC0242Xp
    public final void A1a(Pq pq) {
        this.mBinder = pq;
        AbstractC0096Hu hu = (AbstractC0096Hu) pq.A2V();
        bindScope(ContextScoped.class, new C0250Xx(hu));
        bindScope(ApplicationScoped.class, new Y2(hu));
    }
}
