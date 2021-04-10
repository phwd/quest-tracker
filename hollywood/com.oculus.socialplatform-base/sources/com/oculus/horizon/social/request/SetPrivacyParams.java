package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.social.model.IdentityInSearchPrivacyState;
import com.oculus.horizon.social.model.PrivacyConcept;
import com.oculus.horizon.social.model.PrivacyState;
import com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL;
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

        public Privacy(PrivacyState privacyState, String[] strArr) {
            this.baseState = privacyState;
            this.includedUsers = strArr;
        }

        public String toString() {
            ImmutableMap.Builder A04 = ImmutableMap.A04();
            A04.put(SocialSettingsGraphQL.BASE_STATE, this.baseState.graphQLName);
            String[] strArr = this.includedUsers;
            if (strArr != null) {
                A04.put("included_users", strArr);
            }
            return GraphQLParamsHelper.GSON_CONVERTER.A06(A04.build());
        }
    }

    public SetPrivacyParams(PrivacyConcept privacyConcept2, PrivacyState privacyState, String[] strArr, IdentityInSearchPrivacyState identityInSearchPrivacyState) {
        String str;
        this.privacyConcept = privacyConcept2;
        this.privacy = new Privacy(privacyState, strArr);
        if (privacyConcept2.shouldAlwaysSendIdentityInSearch()) {
            str = String.valueOf(identityInSearchPrivacyState == IdentityInSearchPrivacyState.ENABLED);
        } else {
            str = null;
        }
        this.identityInSearch = str;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put(SocialSettingsGraphQL.PRIVACY_CONCEPT, this.privacyConcept.graphQLName);
        A04.put(SocialSettingsGraphQL.PRIVACY, this.privacy.toString());
        String str = this.identityInSearch;
        if (str != null) {
            A04.put("identity_in_search", str);
        }
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
