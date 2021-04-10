package android.service;

import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkStatsCollectionKeyProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkIdentitySetProto getIdentity();

    int getSet();

    int getTag();

    int getUid();

    boolean hasIdentity();

    boolean hasSet();

    boolean hasTag();

    boolean hasUid();
}
