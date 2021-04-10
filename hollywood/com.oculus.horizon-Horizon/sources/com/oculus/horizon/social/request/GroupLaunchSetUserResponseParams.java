package com.oculus.horizon.social.request;

import X.AnonymousClass0NO;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.remotelaunchlogger.RemoteLaunchLogger;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GroupLaunchSetUserResponseParams {
    public static final String TAG = "GroupLaunchSetUserResponseParams";
    public String mBuildSerial = BuildSerialUtil.A00();
    public String mGroupLaunchID;
    public String mResponse;

    public GroupLaunchSetUserResponseParams(String str, String str2) {
        this.mResponse = str;
        this.mGroupLaunchID = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("group_launch_id", this.mGroupLaunchID);
        A01.put("response", this.mResponse);
        String str = this.mBuildSerial;
        if (str == null || str.equals(BuildSerialUtil.PERMISSION_DENIED) || str.equals("unknown")) {
            AnonymousClass0NO.A0F(TAG, "Could not read build serial: %s", str);
        } else {
            A01.put(RemoteLaunchLogger.KEY_DEVICE_SERIAL, str);
        }
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
