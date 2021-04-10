package com.android.server.am;

import com.android.server.am.UserControllerProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UserControllerProtoOrBuilder extends MessageLiteOrBuilder {
    int getStartedUserArray(int i);

    int getStartedUserArrayCount();

    List<Integer> getStartedUserArrayList();

    UserControllerProto.User getStartedUsers(int i);

    int getStartedUsersCount();

    List<UserControllerProto.User> getStartedUsersList();

    int getUserLru(int i);

    int getUserLruCount();

    List<Integer> getUserLruList();

    UserControllerProto.UserProfile getUserProfileGroupIds(int i);

    int getUserProfileGroupIdsCount();

    List<UserControllerProto.UserProfile> getUserProfileGroupIdsList();
}
