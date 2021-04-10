package com.android.server.am;

import com.android.server.am.ActiveServicesProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActiveServicesProtoOrBuilder extends MessageLiteOrBuilder {
    ActiveServicesProto.ServicesByUser getServicesByUsers(int i);

    int getServicesByUsersCount();

    List<ActiveServicesProto.ServicesByUser> getServicesByUsersList();
}
