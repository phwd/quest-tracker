package com.oculus.horizon.api.twofac;

import androidx.annotation.Nullable;

public class TwoFactorMethod {
    public final Boolean enter_code_option;
    public final String id;
    @Nullable
    public final String instructions;
    public final String label;
    @Nullable
    public final String resend_text;
    public final Boolean send_option;
}
