package com.oculus.horizon.api.common;

public class Entitlement {
    public String id;
    public boolean is_active;
    public Item item;
    public final long last_used;
    public final String signed_token;
}
