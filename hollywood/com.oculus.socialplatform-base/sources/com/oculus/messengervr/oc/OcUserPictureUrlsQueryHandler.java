package com.oculus.messengervr.oc;

import android.annotation.TargetApi;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface OcUserPictureUrlsQueryHandler {
    public static final String FETCH_USERS_NAMES_AND_PROFILES_QUERY = "query FetchUsersNamesAndProfilesQuery($user_ids: [ID!]!) {  nodes(node_id: $user_ids) {    id,    ... on User {      display_name      profile_photo {        uri      }    }  }}";
    public static final String FETCH_USERS_PROFILES_QUERY = "query FetchUsersProfilesQuery($user_ids: [ID!]!) {  nodes(node_id: $user_ids) {    id,    ... on User {      profile_photo {        uri      }    }  }}";

    void queryOCUsersNamesAndProfilePics(List<String> list, Consumer<Map<String, Pair<String, String>>> consumer, Consumer<Throwable> consumer2);

    void queryOCUsersProfilePics(List<String> list, Consumer<Map<String, String>> consumer, Consumer<Throwable> consumer2);
}
