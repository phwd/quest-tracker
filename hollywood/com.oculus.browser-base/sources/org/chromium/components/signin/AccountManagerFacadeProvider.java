package org.chromium.components.signin;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountManagerFacadeProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicReference f10888a = new AtomicReference();
    public static AccountManagerFacade b;
    public static AccountManagerFacade c;

    public static AccountManagerFacade getInstance() {
        AccountManagerFacade accountManagerFacade = (AccountManagerFacade) f10888a.get();
        if (accountManagerFacade != null) {
            return accountManagerFacade;
        }
        throw new IllegalStateException("AccountManagerFacade is not yet initialized!");
    }
}
