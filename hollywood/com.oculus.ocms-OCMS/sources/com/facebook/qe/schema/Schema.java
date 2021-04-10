package com.facebook.qe.schema;

public interface Schema {
    String getHash();

    byte[] getIndexBin();

    int getNumExperiments();

    int getNumSlots();
}
