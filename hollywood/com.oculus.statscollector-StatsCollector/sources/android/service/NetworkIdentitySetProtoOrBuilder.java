package android.service;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NetworkIdentitySetProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkIdentityProto getIdentities(int i);

    int getIdentitiesCount();

    List<NetworkIdentityProto> getIdentitiesList();
}
