package com.oculus.horizon.social.api;

import com.oculus.horizon.api.profile.PrivacyAudience;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class SetPrivacyResponse {
    public PrivacyAudience privacy_audience;
}
