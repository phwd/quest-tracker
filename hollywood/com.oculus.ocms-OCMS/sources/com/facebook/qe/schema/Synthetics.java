package com.facebook.qe.schema;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Synthetics {
    public static final String ASSIGNED_GROUP_NAME = "$G";
    public static final int GROUP_SYNTHETIC_SLOT_OFFSET = 2;
    public static final String HASH_NAME = "$H";
    public static final int HASH_SYNTHETIC_SLOT_OFFSET = 3;
    public static final String IN_DEPLOY_GROUP_NAME = "$D";
    public static final String IN_EXPERIMENT_NAME = "$E";
    public static final int IS_IN_DEPLOY_GROUP_SYNTHETIC_SLOT_OFFSET = 0;
    public static final int IS_IN_EXPERIMENT_SYNTHETIC_SLOT_OFFSET = 1;
    public static final int LAST_SYNTHETIC_PARAMETER_INDEX = 3;
}
