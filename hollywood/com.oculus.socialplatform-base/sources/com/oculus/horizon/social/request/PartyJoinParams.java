package com.oculus.horizon.social.request;

import X.AnonymousClass0MD;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyJoinParams {
    public static final String TAG = "PartyJoinParams";
    public String mBuildSerial;
    public String mInstalledGroupLaunchApplicationId;
    public String mNonce;
    public String mPartyID;

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("party_id", this.mPartyID);
        String str = this.mInstalledGroupLaunchApplicationId;
        if (str != null) {
            A04.put("installed_group_launch_app_id", str);
        }
        String str2 = this.mNonce;
        if (str2 != null) {
            A04.put("nonce", str2);
        }
        String str3 = this.mBuildSerial;
        if (str3 == null || str3.equals(BuildSerialUtil.PERMISSION_DENIED) || str3.equals("unknown")) {
            AnonymousClass0MD.A0A(TAG, "Could not read build serial: %s", str3);
        } else {
            A04.put("device_serial", str3);
        }
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }

    public PartyJoinParams(String str) {
        this(str, null, null);
    }

    public PartyJoinParams(String str, String str2, String str3) {
        this.mInstalledGroupLaunchApplicationId = null;
        this.mNonce = null;
        this.mPartyID = str;
        this.mInstalledGroupLaunchApplicationId = str3;
        this.mNonce = str2;
        this.mBuildSerial = BuildSerialUtil.getSerial();
    }
}
