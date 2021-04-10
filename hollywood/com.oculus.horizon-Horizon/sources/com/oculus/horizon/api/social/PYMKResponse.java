package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class PYMKResponse implements ValidatableApiResponse {
    public final PeopleYouMayKnow people_you_may_know;

    public static class PeopleYouMayKnow {
        public final ArrayList<SocialUser> nodes;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        PeopleYouMayKnow peopleYouMayKnow = this.people_you_may_know;
        if (peopleYouMayKnow == null) {
            str = "PYMKResponse did not have a people_you_may_know";
        } else if (peopleYouMayKnow.nodes == null) {
            str = "people_you_may_know had null for nodes";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
