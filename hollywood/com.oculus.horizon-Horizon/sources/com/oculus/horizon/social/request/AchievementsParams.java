package com.oculus.horizon.social.request;

import javax.annotation.concurrent.Immutable;

@Immutable
public class AchievementsParams {
    public static final String PARAM_APPLICATION_ID = "application_id";
    public static final String PARAM_USER_ID = "user_id";
    public final String mApplicationId;
    public final String mUserId;
}
