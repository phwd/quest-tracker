package com.facebook.graphql.query.interfaces;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum GraphQLCachePolicy {
    FULLY_CACHED(true, true, true),
    CACHE_ONLY(true, false, false),
    NETWORK_ONLY(false, true, false),
    FETCH_AND_FILL(false, true, true);
    
    public final boolean fillDB;
    public final boolean readDB;
    public final boolean readNetwork;

    private GraphQLCachePolicy(boolean z, boolean z2, boolean z3) {
        this.readDB = z;
        this.readNetwork = z2;
        this.fillDB = z3;
    }
}
