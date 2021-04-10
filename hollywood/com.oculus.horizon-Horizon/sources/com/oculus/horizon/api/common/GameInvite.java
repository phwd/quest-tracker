package com.oculus.horizon.api.common;

import X.C08780ya;

public class GameInvite extends Invite {
    public String application_id;
    public String application_name;
    public String application_photo;
    public String room_id;

    public static GameInvite fromNotificationResponse(String str, String str2) {
        GameInvite gameInvite = (GameInvite) new C08780ya().A05(str2, GameInvite.class);
        gameInvite.notification_id = str;
        return gameInvite;
    }
}
