package com.oculus.common.socialtablet.fetchers;

import com.oculus.common.fbaccountsmanager.MessengerVrAccountResult;
import java.util.Optional;
import java.util.function.Function;

/* renamed from: com.oculus.common.socialtablet.fetchers.-$$Lambda$5UYnGeIheEhIMNozIKLF9I5vmRY2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$5UYnGeIheEhIMNozIKLF9I5vmRY2 implements Function {
    public static final /* synthetic */ $$Lambda$5UYnGeIheEhIMNozIKLF9I5vmRY2 INSTANCE = new $$Lambda$5UYnGeIheEhIMNozIKLF9I5vmRY2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Optional.ofNullable(((MessengerVrAccountResult) obj).mUserProfilePictureUrl);
    }
}
