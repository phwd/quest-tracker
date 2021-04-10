package X;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface SZ extends On {
    @Deprecated
    SZ getApplicationInjector();

    @Deprecated
    Op getInjectorThreadStack();

    @Deprecated
    BX getScopeAwareInjector();

    @Deprecated
    AnonymousClass3G getScopeUnawareInjector();
}
