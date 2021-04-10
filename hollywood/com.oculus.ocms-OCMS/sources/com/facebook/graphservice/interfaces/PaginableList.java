package com.facebook.graphservice.interfaces;

import androidx.annotation.VisibleForTesting;
import com.facebook.graphservice.interfaces.GraphQLService;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.tigon.TigonErrorException;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PaginableList<T> {
    private static final String MISSING_PAGINATION_KEY = "MISSING_PAGINATION_KEY";
    private final boolean mFailedLastLoadNext;
    private final boolean mFailedLastLoadPrevious;
    private final boolean mHasNextPage;
    private final boolean mHasPreviousPage;
    private final boolean mIsLoadingNext;
    private final boolean mIsLoadingPrevious;
    @Nullable
    private final String mNextPageUUID;
    private final String mPaginationKey;
    @Nullable
    private final String mPreviousPageUUID;
    private final ImmutableList<T> mTrees;

    public PaginableList(@Nullable String str, ImmutableList<T> immutableList, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @Nullable String str2, @Nullable String str3) {
        this.mTrees = immutableList;
        this.mPaginationKey = str == null ? MISSING_PAGINATION_KEY : str;
        this.mHasPreviousPage = z;
        this.mHasNextPage = z2;
        this.mIsLoadingPrevious = z3;
        this.mIsLoadingNext = z4;
        this.mFailedLastLoadPrevious = z5;
        this.mFailedLastLoadNext = z6;
        this.mNextPageUUID = str2;
        this.mPreviousPageUUID = str3;
    }

    public static <E> PaginableList<E> withMetadata(ImmutableList<E> immutableList, PaginableList<?> paginableList) {
        return new PaginableList<>(paginableList.getPaginationKey(), immutableList, paginableList.hasPreviousPage(), paginableList.hasNextPage(), paginableList.isLoadingPrevious(), paginableList.isLoadingNext(), paginableList.failedLastLoadPrevious(), paginableList.failedLastLoadNext(), paginableList.nextPageUUID(), paginableList.previousPageUUID());
    }

    @VisibleForTesting
    public static <E> PaginableList<E> withoutPaging(ImmutableList<E> immutableList) {
        return new PaginableList<>(null, immutableList, false, false, false, false, false, false, null, null);
    }

    public String getPaginationKey() {
        return this.mPaginationKey;
    }

    public ImmutableList<T> getList() {
        return this.mTrees;
    }

    public boolean hasPreviousPage() {
        return this.mHasPreviousPage;
    }

    public boolean hasNextPage() {
        return this.mHasNextPage;
    }

    public boolean isLoadingPrevious() {
        return this.mIsLoadingPrevious;
    }

    public boolean isLoadingNext() {
        return this.mIsLoadingNext;
    }

    public boolean failedLastLoadPrevious() {
        return this.mFailedLastLoadPrevious;
    }

    public boolean failedLastLoadNext() {
        return this.mFailedLastLoadNext;
    }

    @Nullable
    public String nextPageUUID() {
        return this.mNextPageUUID;
    }

    @Nullable
    public String previousPageUUID() {
        return this.mPreviousPageUUID;
    }

    @Nullable
    public GraphQLService.Token loadNextPageWithService(GraphQLService graphQLService, PaginableListConfiguration paginableListConfiguration, Executor executor) {
        return loadNextPageWithService(graphQLService, paginableListConfiguration, executor, "");
    }

    @Nullable
    public GraphQLService.Token loadNextPageWithService(GraphQLService graphQLService, PaginableListConfiguration paginableListConfiguration, Executor executor, String str) {
        if (this.mPaginationKey.equals(MISSING_PAGINATION_KEY)) {
            return null;
        }
        return graphQLService.loadNextPageForKey(this.mPaginationKey, paginableListConfiguration.getTotalPageSize(), paginableListConfiguration.getInitialCount(), paginableListConfiguration.isEnableAtStream(), paginableListConfiguration.getOverriddenParameters(), paginableListConfiguration.getAnalyticTags(), paginableListConfiguration.getOperationCallbacks(), executor, str);
    }

    @Nullable
    public GraphQLService.Token loadPreviousPageWithService(GraphQLService graphQLService, PaginableListConfiguration paginableListConfiguration, Executor executor) {
        return loadPreviousPageWithService(graphQLService, paginableListConfiguration, executor, "");
    }

    @Nullable
    public GraphQLService.Token loadPreviousPageWithService(GraphQLService graphQLService, PaginableListConfiguration paginableListConfiguration, Executor executor, String str) {
        if (this.mPaginationKey.equals(MISSING_PAGINATION_KEY)) {
            return null;
        }
        return graphQLService.loadPreviousPageForKey(this.mPaginationKey, paginableListConfiguration.getTotalPageSize(), paginableListConfiguration.getOverriddenParameters(), paginableListConfiguration.getAnalyticTags(), paginableListConfiguration.getOperationCallbacks(), executor, str);
    }

    @Nullable
    public GraphQLService.Token experimentalResetWithService(GraphQLService graphQLService, boolean z, Executor executor) {
        if (this.mPaginationKey.equals(MISSING_PAGINATION_KEY)) {
            return null;
        }
        return graphQLService.experimentalResetForKey(this.mPaginationKey, z, new GraphQLService.OperationCallbacks() {
            /* class com.facebook.graphservice.interfaces.PaginableList.AnonymousClass1 */

            @Override // com.facebook.graphservice.interfaces.GraphQLService.OperationCallbacks
            public void onError(TigonErrorException tigonErrorException) {
            }

            @Override // com.facebook.graphservice.interfaces.GraphQLService.OperationCallbacks
            public void onSuccess() {
            }
        }, executor);
    }

    public void appendEdgeWithService(GraphQLService graphQLService, Tree tree) {
        if (!this.mPaginationKey.equals(MISSING_PAGINATION_KEY)) {
            graphQLService.appendEdgeForKey(this.mPaginationKey, tree);
        }
    }

    public void prependEdgeWithService(GraphQLService graphQLService, Tree tree) {
        if (!this.mPaginationKey.equals(MISSING_PAGINATION_KEY)) {
            graphQLService.prependEdgeForKey(this.mPaginationKey, tree);
        }
    }

    public void deleteEdgeWithService(GraphQLService graphQLService, String str) {
        if (!this.mPaginationKey.equals(MISSING_PAGINATION_KEY)) {
            graphQLService.deleteEdgeForKey(this.mPaginationKey, str);
        }
    }
}
