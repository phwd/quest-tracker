package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.PageInfo;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.List;

@SingleEntryMapResponse
public class UserSearchResponse implements ValidatableApiResponse {
    public final SearchResults results;

    public static class SearchResults {
        public final List<SearchResultUser> nodes;
        public final PageInfo page_info;
        public final String result_mode;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        SearchResults searchResults = this.results;
        if (searchResults == null) {
            throw new NullPointerException("Received an invalid UserSearchResponse. Response didn't have a results object");
        } else if (searchResults.nodes == null) {
            throw new NullPointerException("Received an invalid UserSearchResponse. SearchResults had no nodes");
        } else if (searchResults.page_info == null) {
            throw new NullPointerException("Received an invalid UserSearchResponse. SearchResults didn't have a page_info");
        } else if (searchResults.result_mode == null) {
            throw new NullPointerException("Received an invalid UserSearchResponse. SearchResults didn't have a result_mode");
        }
    }
}
