package com.oculus.panelapp.people.graphql;

import android.content.Context;
import android.content.res.Resources;
import com.google.common.collect.ImmutableList;
import com.oculus.graphql.model.GraphQLPageInfo;
import com.oculus.horizon.api.rating.ReviewsRequest;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.socialplatform.R;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FBGraphQLJSONParseHelper {
    @Nullable
    public static String createLastActiveTimeHoursDays(Context context, long j, Clock clock) {
        int currentTimeMillis;
        Resources resources;
        int i;
        if (j != -1 && (currentTimeMillis = ((int) ((clock.currentTimeMillis() / 1000) - j)) / 60) >= 60) {
            int i2 = currentTimeMillis / 60;
            if (i2 < 24) {
                resources = context.getResources();
                i = R.plurals.people_tablet_last_active_in_hours;
            } else {
                i2 /= 24;
                if (i2 <= 2) {
                    resources = context.getResources();
                    i = R.plurals.people_tablet_last_active_in_days;
                }
            }
            return resources.getQuantityString(i, i2, Integer.valueOf(i2));
        }
        return null;
    }

    @Nullable
    public static String createLastActiveTimeMins(Context context, long j, Clock clock) {
        int currentTimeMillis;
        if (j == -1 || (currentTimeMillis = ((int) ((clock.currentTimeMillis() / 1000) - j)) / 60) >= 60) {
            return null;
        }
        if (currentTimeMillis == 0) {
            currentTimeMillis = 1;
        }
        return context.getResources().getQuantityString(R.plurals.people_tablet_last_active_in_mins, currentTimeMillis, Integer.valueOf(currentTimeMillis));
    }

    public static boolean parseVrFbPresenceSharing(@Nullable JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("actor")) == null || (optJSONObject2 = optJSONObject.optJSONObject("vr_data")) == null) {
            return false;
        }
        return optJSONObject2.optBoolean("fb_presence_sharing", false);
    }

    @Nullable
    public static GraphQLPageInfo parsePageInfo(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new GraphQLPageInfo(jSONObject.optString("end_cursor"), jSONObject.optBoolean("has_next_page"), jSONObject.optString("start_cursor"), jSONObject.optBoolean("has_previous_page"));
    }

    public static List<FBSocialUser> parseUserNodes(Context context, @Nullable JSONArray jSONArray, boolean z, Clock clock) {
        String str;
        SocialUserFriendshipStatus socialUserFriendshipStatus;
        String str2;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (jSONArray == null) {
            return ImmutableList.of();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (!(optJSONObject == null || optJSONObject.length() == 0)) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("profile_picture");
                String optString = optJSONObject.optString("id");
                String optString2 = optJSONObject.optString("name");
                if (optJSONObject2 != null) {
                    str = optJSONObject2.optString("uri");
                } else {
                    str = "";
                }
                FBSocialUser fBSocialUser = new FBSocialUser(optString, optString2, null, null, str, false, SocialUser.UserRowType.FRIEND);
                if (z) {
                    setMessengerStatus(fBSocialUser, context, optJSONObject.optJSONObject("last_active_messages_status"), clock);
                }
                String optString3 = optJSONObject.optString("presence_status");
                if (optString3 != null && !optString3.isEmpty()) {
                    fBSocialUser.setPresenceStatus(optString3);
                }
                String optString4 = optJSONObject.optString("friendship_status");
                if (optString4 == null || optString4.isEmpty()) {
                    socialUserFriendshipStatus = SocialUserFriendshipStatus.UNKNOWN;
                } else {
                    socialUserFriendshipStatus = SocialUserFriendshipStatus.get(optString4);
                }
                fBSocialUser.mFriendship = socialUserFriendshipStatus;
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("mutual_friends");
                if (optJSONObject3 != null) {
                    fBSocialUser.setNumMutualFriends(optJSONObject3.optInt(ReviewsRequest.KEY_COUNT));
                }
                builder.add((Object) fBSocialUser);
                JSONObject optJSONObject4 = optJSONObject.optJSONObject("vr_data");
                if (optJSONObject4 != null) {
                    boolean optBoolean = optJSONObject4.optBoolean("vr_is_vr_user", false);
                    fBSocialUser.mIsVRUser = optBoolean;
                    if (optBoolean) {
                        JSONObject optJSONObject5 = optJSONObject4.optJSONObject("vr_persona");
                        if (optJSONObject5 != null) {
                            str2 = optJSONObject5.optString("id");
                        } else {
                            str2 = null;
                        }
                        fBSocialUser.mVrPersonaID = str2;
                        if (z) {
                            setVrStatus(fBSocialUser, context, optJSONObject4, clock);
                        }
                        JSONObject optJSONObject6 = optJSONObject4.optJSONObject("vr_current_party");
                        if (optJSONObject6 != null) {
                            fBSocialUser.mCurrentPartyID = optJSONObject6.optString("id");
                            fBSocialUser.mCurrentPartyJoinPolicy = optJSONObject6.optString("vr_join_policy");
                            int optInt = optJSONObject6.optInt("vr_max_size");
                            JSONObject optJSONObject7 = optJSONObject6.optJSONObject("vr_party_users");
                            if (optJSONObject7 != null) {
                                int optInt2 = optJSONObject7.optInt(ReviewsRequest.KEY_COUNT);
                                boolean z2 = false;
                                if (optInt2 >= optInt) {
                                    z2 = true;
                                }
                                fBSocialUser.mIsPartyFull = z2;
                            } else {
                                fBSocialUser.mIsPartyFull = false;
                            }
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0042, code lost:
        if (r2 != null) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long setMessengerStatus(com.oculus.horizoncontent.social.FBSocialUser r4, android.content.Context r5, @javax.annotation.Nullable org.json.JSONObject r6, com.oculus.panelapp.people.graphql.Clock r7) {
        /*
            r0 = -1
            if (r6 == 0) goto L_0x0045
            r3 = 0
            java.lang.String r2 = "is_currently_active"
            boolean r2 = r6.optBoolean(r2, r3)
            if (r2 == 0) goto L_0x001e
            android.content.res.Resources r3 = r5.getResources()
            r2 = 2131689801(0x7f0f0149, float:1.9008628E38)
            java.lang.String r2 = r3.getString(r2)
            r4.mPresenceString = r2
            com.oculus.horizoncontent.profile.ProfilePresenceType r2 = com.oculus.horizoncontent.profile.ProfilePresenceType.MESSENGER
            r4.mPresenceIconType = r2
        L_0x001e:
            java.lang.String r2 = "time"
            long r0 = r6.optLong(r2, r0)
            java.lang.String r3 = createLastActiveTimeMins(r5, r0, r7)
            java.lang.String r2 = createLastActiveTimeHoursDays(r5, r0, r7)
            if (r3 == 0) goto L_0x0042
            r4.mLastActiveTime = r3
            android.content.res.Resources r3 = r5.getResources()
            r2 = 2131689801(0x7f0f0149, float:1.9008628E38)
            java.lang.String r2 = r3.getString(r2)
        L_0x003b:
            r4.mLastActive = r2
            com.oculus.horizoncontent.profile.ProfilePresenceType r2 = com.oculus.horizoncontent.profile.ProfilePresenceType.MESSENGER
            r4.mPresenceIconType = r2
            return r0
        L_0x0042:
            if (r2 == 0) goto L_0x0045
            goto L_0x003b
        L_0x0045:
            android.content.res.Resources r3 = r5.getResources()
            r2 = 2131689847(0x7f0f0177, float:1.900872E38)
            java.lang.String r2 = r3.getString(r2)
            r4.mLastActive = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.graphql.FBGraphQLJSONParseHelper.setMessengerStatus(com.oculus.horizoncontent.social.FBSocialUser, android.content.Context, org.json.JSONObject, com.oculus.panelapp.people.graphql.Clock):long");
    }

    public static void setVrStatus(FBSocialUser fBSocialUser, Context context, JSONObject jSONObject, Clock clock) {
        String string;
        String optString = jSONObject.optString("vr_presence_status");
        jSONObject.optString("vr_presence");
        JSONObject optJSONObject = jSONObject.optJSONObject("vr_most_recent_presence");
        if (optJSONObject == null || optJSONObject.optString("vr_presence") == null) {
            string = context.getResources().getString(R.string.people_tablet_active_status_in_oculus);
        } else {
            string = optJSONObject.optString("vr_presence");
        }
        fBSocialUser.setPresenceStatus(optString);
        if (fBSocialUser.getIsActive(context.getResources())) {
            fBSocialUser.mPresenceString = string;
            fBSocialUser.mPresenceIconType = ProfilePresenceType.VR;
        }
        if (optJSONObject != null && !fBSocialUser.getIsActive(context.getResources())) {
            long optLong = optJSONObject.optLong("vr_last_active_time", -1);
            String createLastActiveTimeMins = createLastActiveTimeMins(context, optLong, clock);
            String createLastActiveTimeHoursDays = createLastActiveTimeHoursDays(context, optLong, clock);
            if (createLastActiveTimeMins != null) {
                fBSocialUser.mLastActiveTime = createLastActiveTimeMins;
                fBSocialUser.mLastActive = string;
            } else if (fBSocialUser.mLastActiveTime == null && createLastActiveTimeHoursDays != null) {
                fBSocialUser.mLastActive = createLastActiveTimeHoursDays;
            } else {
                return;
            }
            fBSocialUser.mPresenceIconType = ProfilePresenceType.VR;
        }
    }

    public static JSONObject parseField(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject != null) {
            return optJSONObject;
        }
        throw new JSONException(String.format("Failed to extract %s from JSON: %s ", str, jSONObject.toString()));
    }
}
