package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.concurrent.Immutable;

@Immutable
public class SocialActivityParams {
    public String mActivityID;
    public Role mRole;

    public enum Role {
        SENDER("SENDER"),
        RECEIVER("RECEIVER");
        
        public String value;

        public static Role fromString(String str) {
            if (str.equals("SENDER")) {
                return SENDER;
            }
            return RECEIVER;
        }

        /* access modifiers changed from: public */
        Role(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    public SocialActivityParams(String str, Role role) {
        this.mActivityID = str;
        this.mRole = role;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("id", this.mActivityID);
        A01.put(OculusContent.FriendList.ROLE_PARAM, this.mRole.toString());
        return GraphQLParamsHelper.encodeAsNestedInputParams(A01.build());
    }
}
