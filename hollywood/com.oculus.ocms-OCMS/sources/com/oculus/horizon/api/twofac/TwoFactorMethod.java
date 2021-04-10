package com.oculus.horizon.api.twofac;

import androidx.annotation.Nullable;

public class TwoFactorMethod {
    public Boolean enter_code_option;
    public String id;
    @Nullable
    public String instructions;
    public String label;
    @Nullable
    public String resend_text;
    public Boolean send_option;
}
