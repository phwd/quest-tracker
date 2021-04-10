package com.oculus.horizon.api.common;

import javax.annotation.Nullable;

public abstract class Invite {
    public String notification_id;
    public String sender_alias;
    public String sender_id;
    @Nullable
    public String sender_name;
    public String sender_photo;
}
