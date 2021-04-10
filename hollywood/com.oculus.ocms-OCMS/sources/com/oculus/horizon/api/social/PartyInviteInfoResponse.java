package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInviteInfoResponse {
    @Nullable
    public Party deeplink_target_node;
    @Nullable
    public Party node;
    public Viewer viewer;

    public static class Application {
        public String display_name;
        public Image icon_image;
        public String id;
    }

    public static class GroupLaunch {
        public GroupLaunchDestination destination;
        public String id;
    }

    public static class GroupLaunchDestination {
        public Application application;
        public String display_name;
        public String id;
        public String image;
    }

    public static class Image {
        public String uri;
    }

    public static class SocialUsers {
        public ArrayList<SocialUser> nodes;
    }

    public static class User {
        public ViewerParty current_party;
    }

    public static class Viewer {
        public User user;
    }

    public static class ViewerParty {
        public String id;
    }

    public static class Party {
        public String id;
        public SocialUser invited_by;
        public SocialUsers invited_users;
        public SocialUsers party_blocked_invited_users;
        public SocialUsers party_blocked_users;
        public GroupLaunch party_group_launch;
        public SocialUser party_leader;
        public String party_type;
        public SocialUsers party_users;

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

    @Nullable
    public Party getNode() {
        Party party = this.node;
        return party != null ? party : this.deeplink_target_node;
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
