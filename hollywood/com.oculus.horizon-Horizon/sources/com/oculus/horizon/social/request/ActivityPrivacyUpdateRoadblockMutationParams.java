package com.oculus.horizon.social.request;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ActivityPrivacyUpdateRoadblockMutationParams {
    public static final String PARAM_FALSE = "false";
    public static final String PARAM_TRUE = "true";
    public static final String PARAM_VALUE = "value";
    public final boolean value;
}
