package com.oculus.horizon.social.model;

import com.google.common.collect.ImmutableList;
import javax.annotation.Nullable;

public class CurrentPrivacyState {
    public final IdentityInSearchPrivacyState identityInSearchState;
    @Nullable
    public final ImmutableList<String> includedUsers;
    public final PrivacyState state;

    public CurrentPrivacyState(PrivacyState privacyState, ImmutableList<String> immutableList, IdentityInSearchPrivacyState identityInSearchPrivacyState) {
        this.state = privacyState;
        this.includedUsers = immutableList;
        this.identityInSearchState = identityInSearchPrivacyState;
    }
}
