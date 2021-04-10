package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInviteInfoResponse {
    @Nullable
    public final Party deeplink_target_node;
    @Nullable
    public final Party node;
    public final Viewer viewer;

    public static class Application {
        public final String display_name;
        public final Image icon_image;
        public final String id;
    }

    public static class GroupLaunch {
        public final GroupLaunchDestination destination;
        public String id;
    }

    public static class GroupLaunchDestination {
        public final Application application;
        public final String display_name;
        public final String id;
        public final String image;
    }

    public static class Image {
        public final String uri;
    }

    public static class Party {
        public final String id;
        public final SocialUser invited_by;
        public final SocialUsers invited_users;
        public final SocialUsers party_blocked_invited_users;
        public final SocialUsers party_blocked_users;
        public final GroupLaunch party_group_launch;
        public final SocialUser party_leader;
        public final String party_type;
        public final SocialUsers party_users;

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("Party{");
            stringBuffer.append("id='");
            stringBuffer.append(this.id);
            stringBuffer.append('\'');
            stringBuffer.append(", party_type='");
            stringBuffer.append(this.party_type);
            stringBuffer.append('\'');
            stringBuffer.append(", party_leader=");
            stringBuffer.append(this.party_leader);
            stringBuffer.append(", invited_by=");
            stringBuffer.append(this.invited_by);
            stringBuffer.append(", invited_users=");
            stringBuffer.append(this.invited_users);
            stringBuffer.append(", party_users=");
            stringBuffer.append(this.party_users);
            stringBuffer.append(", party_group_launch=");
            stringBuffer.append(this.party_group_launch);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    public static class SocialUsers {
        public final ArrayList<SocialUser> nodes;
    }

    public static class User {
        public final ViewerParty current_party;
    }

    public static class Viewer {
        public final User user;
    }

    public static class ViewerParty {
        public final String id;
    }

    @Nullable
    public Party getNode() {
        Party party = this.node;
        if (party == null) {
            return this.deeplink_target_node;
        }
        return party;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PartyInviteInfoResponse{");
        stringBuffer.append("node=");
        stringBuffer.append(this.node);
        stringBuffer.append(", deeplink_target_node=");
        stringBuffer.append(this.deeplink_target_node);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
