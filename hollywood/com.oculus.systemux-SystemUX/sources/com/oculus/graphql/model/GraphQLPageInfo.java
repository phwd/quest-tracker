package com.oculus.graphql.model;

import androidx.annotation.Nullable;

public class GraphQLPageInfo {
    @Nullable
    private String mEndCursor;
    private boolean mHasNextPage;
    private boolean mHasPreviousPage;
    @Nullable
    private String mStartCursor;

    public GraphQLPageInfo(String str, boolean z, String str2, boolean z2) {
        this.mEndCursor = str;
        this.mHasNextPage = z;
        this.mStartCursor = str2;
        this.mHasPreviousPage = z2;
    }

    @Nullable
    public String getEndCursor() {
        return this.mEndCursor;
    }

    public boolean hasNextPage() {
        return this.mHasNextPage;
    }

    @Nullable
    public String getStartCursor() {
        return this.mStartCursor;
    }

    public boolean hasPreviousPage() {
        return this.mHasPreviousPage;
    }

    public static class Builder {
        @Nullable
        private String mEndCursor;
        private boolean mHasNextPage;
        private boolean mHasPreviousPage;
        @Nullable
        private String mStartCursor;

        public Builder setEndCursor(@Nullable String str) {
            this.mEndCursor = str;
            return this;
        }

        public Builder setHasNextPage(boolean z) {
            this.mHasNextPage = z;
            return this;
        }

        public Builder setStartCursor(@Nullable String str) {
            this.mStartCursor = str;
            return this;
        }

        public Builder setHasPreviousPage(boolean z) {
            this.mHasPreviousPage = z;
            return this;
        }

        public GraphQLPageInfo build() {
            return new GraphQLPageInfo(this.mEndCursor, this.mHasNextPage, this.mStartCursor, this.mHasPreviousPage);
        }
    }
}
