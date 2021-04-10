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
        PeopleYouMayKnow peopleYouMayKnow = this.people_you_may_know;
        if (peopleYouMayKnow == null) {
            throw new NullPointerException("PYMKResponse did not have a people_you_may_know");
        } else if (peopleYouMayKnow.nodes == null) {
            throw new NullPointerException("people_you_may_know had null for nodes");
        }
    }
}
