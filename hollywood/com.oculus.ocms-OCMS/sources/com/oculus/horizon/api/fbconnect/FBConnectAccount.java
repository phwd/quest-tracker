package com.oculus.horizon.api.fbconnect;

import javax.annotation.Nullable;

public class FBConnectAccount {
    @Nullable
    public String facebook_email;
    public String facebook_id;
    public String friend_policy;
    public boolean needs_password_to_unlink;
}
