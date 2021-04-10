package com.oculus.horizon.social.request;

import com.oculus.horizon.social.model.PrivacyConcept;
import com.oculus.horizon.social.model.PrivacyState;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class SetPrivacyParams {
    public final String identityInSearch;
    public final Privacy privacy;
    public final PrivacyConcept privacyConcept;

    @Immutable
    public static class Privacy {
        public final PrivacyState baseState;
        @Nullable
        public final String[] includedUsers;
    }
}
