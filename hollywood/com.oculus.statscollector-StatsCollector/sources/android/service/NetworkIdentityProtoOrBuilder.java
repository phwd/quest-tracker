package android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkIdentityProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getDefaultNetwork();

    boolean getMetered();

    String getNetworkId();

    ByteString getNetworkIdBytes();

    boolean getRoaming();

    String getSubscriberId();

    ByteString getSubscriberIdBytes();

    int getType();

    boolean hasDefaultNetwork();

    boolean hasMetered();

    boolean hasNetworkId();

    boolean hasRoaming();

    boolean hasSubscriberId();

    boolean hasType();
}
