package com.oculus.panelapp.people.graphql;

import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.panelapp.people.model.FBViewerSocialParty;
import java.util.List;
import javax.annotation.Nonnull;

public class FBFriendsListResult {
    public FBViewerSocialParty mFBViewerSocialParty;
    public int mFriendsCount;
    public List<FBSocialUser> mFriendsList;

    public static class Builder {
        public FBViewerSocialParty mFBViewerSocialParty;
        public int mFriendsCount;
        public List<FBSocialUser> mFriendsList;

        public FBFriendsListResult build() {
            return new FBFriendsListResult(this.mFBViewerSocialParty, this.mFriendsList, this.mFriendsCount);
        }

        public Builder setFBViewerSocialParty(FBViewerSocialParty fBViewerSocialParty) {
            this.mFBViewerSocialParty = fBViewerSocialParty;
            return this;
        }

        public Builder setFriendsCount(int i) {
            this.mFriendsCount = i;
            return this;
        }

        public Builder setFriendsList(@Nonnull List<FBSocialUser> list) {
            this.mFriendsList = list;
            return this;
        }
    }

    public FBViewerSocialParty getFBViewerSocialParty() {
        return this.mFBViewerSocialParty;
    }

    public int getFriendsCount() {
        return this.mFriendsCount;
    }

    public List<FBSocialUser> getFriendsList() {
        return this.mFriendsList;
    }

    public FBFriendsListResult(FBViewerSocialParty fBViewerSocialParty, List<FBSocialUser> list, int i) {
        this.mFBViewerSocialParty = fBViewerSocialParty;
        this.mFriendsList = list;
        this.mFriendsCount = i;
    }
}
