package com.oculus.horizon.social.api;

public class SocialSearchData {
    public SearchResultMode mSearchResultMode;
    public String mSearchText;
    public SearchResultUser mUser;

    public static class Builder {
        public SearchResultMode searchResultMode;
        public String searchText;
        public SearchResultUser user;

        public SocialSearchData build() {
            return new SocialSearchData(this);
        }

        public Builder setSearchResultMode(SearchResultMode searchResultMode2) {
            this.searchResultMode = searchResultMode2;
            return this;
        }

        public Builder setSearchText(String str) {
            this.searchText = str;
            return this;
        }

        public Builder setUser(SearchResultUser searchResultUser) {
            this.user = searchResultUser;
            return this;
        }
    }

    public SocialSearchData(Builder builder) {
        this.mUser = builder.user;
        this.mSearchText = builder.searchText;
        this.mSearchResultMode = builder.searchResultMode;
    }

    public SearchResultMode getSearchResultMode() {
        return this.mSearchResultMode;
    }

    public String getSearchText() {
        return this.mSearchText;
    }

    public SearchResultUser getUser() {
        return this.mUser;
    }
}
