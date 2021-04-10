package com.oculus.horizon.sso;

import java.util.ArrayList;
import javax.annotation.Nullable;

public class GetSessionForAppResponse {
    public String access_token;
    public final String error_msg;
    public final String machine_id;
    @Nullable
    public final ArrayList<SessionCookie> session_cookies;
    public String session_key;
    public final String uid;
}
