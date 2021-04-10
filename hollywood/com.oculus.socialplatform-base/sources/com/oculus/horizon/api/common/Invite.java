package com.oculus.horizon.api.common;

import javax.annotation.Nullable;

public abstract class Invite {
    public String notification_id;
    public final String sender_alias;
    public final String sender_id;
    @Nullable
    public final String sender_name;
    public String sender_photo;
}
