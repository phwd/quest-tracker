package com.oculus.horizon.social.request;

import X.AnonymousClass0NO;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.remotelaunchlogger.RemoteLaunchLogger;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyJoinParams {
    public static final String TAG = "PartyJoinParams";
    public String mBuildSerial;
    public String mInstalledGroupLaunchApplicationId = null;
    public String mNonce = null;
    public String mPartyID;

    public PartyJoinParams(String str, String str2, String str3) {
        this.mPartyID = str;
        this.mInstalledGroupLaunchApplicationId = str3;
        this.mNonce = str2;
        this.mBuildSerial = BuildSerialUtil.A00();
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("party_id", this.mPartyID);
        String str = this.mInstalledGroupLaunchApplicationId;
        if (str != null) {
            A01.put("installed_group_launch_app_id", str);
        }
        String str2 = this.mNonce;
        if (str2 != null) {
            A01.put("nonce", str2);
        }
        String str3 = this.mBuildSerial;
        if (str3 == null || str3.equals(BuildSerialUtil.PERMISSION_DENIED) || str3.equals("unknown")) {
            AnonymousClass0NO.A0F(TAG, "Could not read build serial: %s", str3);
        } else {
            A01.put(RemoteLaunchLogger.KEY_DEVICE_SERIAL, str3);
        }
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
