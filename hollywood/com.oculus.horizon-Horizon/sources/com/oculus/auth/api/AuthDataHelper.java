package com.oculus.auth.api;

import com.google.common.collect.ImmutableList;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.horizon.api.twofac.TwoFactorMethod;
import java.util.List;
import javax.annotation.Nullable;

public class AuthDataHelper {
    @Nullable
    public static ImmutableList<AuthTwoFactorMethod> convertTwoFactorMethods(@Nullable List<TwoFactorMethod> list) {
        if (list == null) {
            return null;
        }
        ImmutableList.Builder A02 = ImmutableList.A02();
        for (TwoFactorMethod twoFactorMethod : list) {
            A02.add((Object) new AuthTwoFactorMethod(twoFactorMethod.id, twoFactorMethod.label, twoFactorMethod.instructions, twoFactorMethod.send_option.booleanValue(), twoFactorMethod.resend_text, twoFactorMethod.enter_code_option.booleanValue()));
        }
        return A02.build();
    }
}
