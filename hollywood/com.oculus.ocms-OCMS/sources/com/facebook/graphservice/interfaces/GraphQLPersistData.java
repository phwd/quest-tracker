package com.facebook.graphservice.interfaces;

public interface GraphQLPersistData {
    long getLegacyPersistIdForQueryNameHash(long j);

    long getOssPersistIdForQueryNameHash(long j);

    String getQueryTextForQueryNameHash(long j);

    String[] getTransientParametersForQueryNameHash(long j);
}
