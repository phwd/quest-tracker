package android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkInterfaceProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkIdentitySetProto getIdentities();

    String getInterface();

    ByteString getInterfaceBytes();

    boolean hasIdentities();

    boolean hasInterface();
}
