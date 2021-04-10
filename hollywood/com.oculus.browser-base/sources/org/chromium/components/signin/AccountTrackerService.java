package org.chromium.components.signin;

import java.util.Iterator;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountTrackerService {

    /* renamed from: a  reason: collision with root package name */
    public final long f10889a;
    public int b;
    public boolean c;
    public W1 d;
    public final C1322Vq0 e = new C1322Vq0();

    public AccountTrackerService(long j) {
        this.f10889a = j;
        this.b = 0;
        this.c = false;
    }

    public static AccountTrackerService create(long j) {
        Object obj = ThreadUtils.f10596a;
        return new AccountTrackerService(j);
    }

    public void a(U1 u1) {
        Object obj = ThreadUtils.f10596a;
        this.e.b(u1);
    }

    public boolean b() {
        Object obj = ThreadUtils.f10596a;
        int i = this.b;
        if (i == 2 && !this.c) {
            return true;
        }
        if ((i == 0 || this.c) && i != 1) {
            e();
        }
        return false;
    }

    public void c(boolean z) {
        Object obj = ThreadUtils.f10596a;
        this.c = true;
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((U1) uq0.next()).E();
        }
        if (z) {
            b();
        }
    }

    public final void d() {
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((U1) uq0.next()).v();
            } else {
                return;
            }
        }
    }

    public final void e() {
        Object obj = ThreadUtils.f10596a;
        this.c = false;
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        if (instance.h()) {
            this.b = 1;
            if (this.d == null) {
                P1 p1 = new P1(this);
                this.d = p1;
                instance.a(p1);
            }
            instance.g(new Q1(this, instance));
            return;
        }
        this.b = 0;
    }
}
