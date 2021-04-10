package com.oculus.horizon.api.platformsdk;

import java.util.List;

public class HeartbeatResponse {
    public final long seconds_until_next_call_due;
    public List<UserToPing> users_to_ping;
}
