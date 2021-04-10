package com.oculus.horizon.social.request;

import X.AnonymousClass0MD;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GroupLaunchSetUserResponseParams {
    public static final String TAG = "GroupLaunchSetUserResponseParams";
    public String mBuildSerial = BuildSerialUtil.getSerial();
    public String mGroupLaunchID;
    public String mResponse;

    public GroupLaunchSetUserResponseParams(String str, String str2) {
        this.mResponse = str;
        this.mGroupLaunchID = str2;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("group_launch_id", this.mGroupLaunchID);
        A04.put("response", this.mResponse);
        String str = this.mBuildSerial;
        if (str == null || str.equals(BuildSerialUtil.PERMISSION_DENIED) || str.equals("unknown")) {
            AnonymousClass0MD.A0A(TAG, "Could not read build serial: %s", str);
        } else {
            A04.put("device_serial", str);
        }
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
