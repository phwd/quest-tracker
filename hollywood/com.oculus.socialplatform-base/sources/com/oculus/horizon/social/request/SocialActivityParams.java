package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
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

        public String toString() {
            return this.value;
        }

        /* access modifiers changed from: public */
        Role(String str) {
            this.value = str;
        }
    }

    public SocialActivityParams(String str, Role role) {
        this.mActivityID = str;
        this.mRole = role;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("id", this.mActivityID);
        A04.put("role", this.mRole.toString());
        return GraphQLParamsHelper.encodeAsNestedInputParams(A04.build());
    }
}
