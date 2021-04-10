package com.oculus.horizon.api.fbconnect;

import javax.annotation.Nullable;

public class FBConnectAccount {
    @Nullable
    public final String facebook_email;
    public final String facebook_id;
    public final String friend_policy;
    public boolean needs_password_to_unlink;
}
