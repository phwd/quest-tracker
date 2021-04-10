package com.android.server.am;

import com.android.server.am.UserStateProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class UserControllerProto extends GeneratedMessageLite<UserControllerProto, Builder> implements UserControllerProtoOrBuilder {
    private static final UserControllerProto DEFAULT_INSTANCE = new UserControllerProto();
    private static volatile Parser<UserControllerProto> PARSER = null;
    public static final int STARTED_USERS_FIELD_NUMBER = 1;
    public static final int STARTED_USER_ARRAY_FIELD_NUMBER = 2;
    public static final int USER_LRU_FIELD_NUMBER = 3;
    public static final int USER_PROFILE_GROUP_IDS_FIELD_NUMBER = 4;
    private Internal.IntList startedUserArray_ = emptyIntList();
    private Internal.ProtobufList<User> startedUsers_ = emptyProtobufList();
    private Internal.IntList userLru_ = emptyIntList();
    private Internal.ProtobufList<UserProfile> userProfileGroupIds_ = emptyProtobufList();

    public interface UserOrBuilder extends MessageLiteOrBuilder {
        int getId();

        UserStateProto getState();

        boolean hasId();

        boolean hasState();
    }

    public interface UserProfileOrBuilder extends MessageLiteOrBuilder {
        int getProfile();

        int getUser();

        boolean hasProfile();

        boolean hasUser();
    }

    private UserControllerProto() {
    }

    public static final class User extends GeneratedMessageLite<User, Builder> implements UserOrBuilder {
        private static final User DEFAULT_INSTANCE = new User();
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile Parser<User> PARSER = null;
        public static final int STATE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int id_ = 0;
        private UserStateProto state_;

        private User() {
        }

        @Override // com.android.server.am.UserControllerProto.UserOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.UserControllerProto.UserOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.server.am.UserControllerProto.UserOrBuilder
        public boolean hasState() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.UserControllerProto.UserOrBuilder
        public UserStateProto getState() {
            UserStateProto userStateProto = this.state_;
            return userStateProto == null ? UserStateProto.getDefaultInstance() : userStateProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setState(UserStateProto value) {
            if (value != null) {
                this.state_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setState(UserStateProto.Builder builderForValue) {
            this.state_ = (UserStateProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeState(UserStateProto value) {
            UserStateProto userStateProto = this.state_;
            if (userStateProto == null || userStateProto == UserStateProto.getDefaultInstance()) {
                this.state_ = value;
            } else {
                this.state_ = (UserStateProto) ((UserStateProto.Builder) UserStateProto.newBuilder(this.state_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearState() {
            this.state_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getState());
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getState());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static User parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static User parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static User parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static User parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static User parseFrom(InputStream input) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static User parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static User parseDelimitedFrom(InputStream input) throws IOException {
            return (User) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static User parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (User) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static User parseFrom(CodedInputStream input) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static User parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(User prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<User, Builder> implements UserOrBuilder {
            private Builder() {
                super(User.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.UserControllerProto.UserOrBuilder
            public boolean hasId() {
                return ((User) this.instance).hasId();
            }

            @Override // com.android.server.am.UserControllerProto.UserOrBuilder
            public int getId() {
                return ((User) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((User) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((User) this.instance).clearId();
                return this;
            }

            @Override // com.android.server.am.UserControllerProto.UserOrBuilder
            public boolean hasState() {
                return ((User) this.instance).hasState();
            }

            @Override // com.android.server.am.UserControllerProto.UserOrBuilder
            public UserStateProto getState() {
                return ((User) this.instance).getState();
            }

            public Builder setState(UserStateProto value) {
                copyOnWrite();
                ((User) this.instance).setState((User) value);
                return this;
            }

            public Builder setState(UserStateProto.Builder builderForValue) {
                copyOnWrite();
                ((User) this.instance).setState((User) builderForValue);
                return this;
            }

            public Builder mergeState(UserStateProto value) {
                copyOnWrite();
                ((User) this.instance).mergeState(value);
                return this;
            }

            public Builder clearState() {
                copyOnWrite();
                ((User) this.instance).clearState();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new User();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    User other = (User) arg1;
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.state_ = (UserStateProto) visitor.visitMessage(this.state_, other.state_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt32();
                            } else if (tag == 18) {
                                UserStateProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (UserStateProto.Builder) this.state_.toBuilder();
                                }
                                this.state_ = (UserStateProto) input.readMessage(UserStateProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.state_);
                                    this.state_ = (UserStateProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (User.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static User getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<User> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UserProfile extends GeneratedMessageLite<UserProfile, Builder> implements UserProfileOrBuilder {
        private static final UserProfile DEFAULT_INSTANCE = new UserProfile();
        private static volatile Parser<UserProfile> PARSER = null;
        public static final int PROFILE_FIELD_NUMBER = 2;
        public static final int USER_FIELD_NUMBER = 1;
        private int bitField0_;
        private int profile_ = 0;
        private int user_ = 0;

        private UserProfile() {
        }

        @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
        public boolean hasUser() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
        public int getUser() {
            return this.user_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUser(int value) {
            this.bitField0_ |= 1;
            this.user_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUser() {
            this.bitField0_ &= -2;
            this.user_ = 0;
        }

        @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
        public boolean hasProfile() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
        public int getProfile() {
            return this.profile_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProfile(int value) {
            this.bitField0_ |= 2;
            this.profile_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProfile() {
            this.bitField0_ &= -3;
            this.profile_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.user_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.profile_);
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.user_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.profile_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static UserProfile parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserProfile parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserProfile parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserProfile parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserProfile parseFrom(InputStream input) throws IOException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserProfile parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserProfile parseDelimitedFrom(InputStream input) throws IOException {
            return (UserProfile) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UserProfile parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserProfile) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserProfile parseFrom(CodedInputStream input) throws IOException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserProfile parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserProfile prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserProfile, Builder> implements UserProfileOrBuilder {
            private Builder() {
                super(UserProfile.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
            public boolean hasUser() {
                return ((UserProfile) this.instance).hasUser();
            }

            @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
            public int getUser() {
                return ((UserProfile) this.instance).getUser();
            }

            public Builder setUser(int value) {
                copyOnWrite();
                ((UserProfile) this.instance).setUser(value);
                return this;
            }

            public Builder clearUser() {
                copyOnWrite();
                ((UserProfile) this.instance).clearUser();
                return this;
            }

            @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
            public boolean hasProfile() {
                return ((UserProfile) this.instance).hasProfile();
            }

            @Override // com.android.server.am.UserControllerProto.UserProfileOrBuilder
            public int getProfile() {
                return ((UserProfile) this.instance).getProfile();
            }

            public Builder setProfile(int value) {
                copyOnWrite();
                ((UserProfile) this.instance).setProfile(value);
                return this;
            }

            public Builder clearProfile() {
                copyOnWrite();
                ((UserProfile) this.instance).clearProfile();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserProfile();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UserProfile other = (UserProfile) arg1;
                    this.user_ = visitor.visitInt(hasUser(), this.user_, other.hasUser(), other.user_);
                    this.profile_ = visitor.visitInt(hasProfile(), this.profile_, other.hasProfile(), other.profile_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.user_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.profile_ = input.readInt32();
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (UserProfile.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static UserProfile getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UserProfile> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public List<User> getStartedUsersList() {
        return this.startedUsers_;
    }

    public List<? extends UserOrBuilder> getStartedUsersOrBuilderList() {
        return this.startedUsers_;
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public int getStartedUsersCount() {
        return this.startedUsers_.size();
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public User getStartedUsers(int index) {
        return this.startedUsers_.get(index);
    }

    public UserOrBuilder getStartedUsersOrBuilder(int index) {
        return this.startedUsers_.get(index);
    }

    private void ensureStartedUsersIsMutable() {
        if (!this.startedUsers_.isModifiable()) {
            this.startedUsers_ = GeneratedMessageLite.mutableCopy(this.startedUsers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedUsers(int index, User value) {
        if (value != null) {
            ensureStartedUsersIsMutable();
            this.startedUsers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedUsers(int index, User.Builder builderForValue) {
        ensureStartedUsersIsMutable();
        this.startedUsers_.set(index, (User) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStartedUsers(User value) {
        if (value != null) {
            ensureStartedUsersIsMutable();
            this.startedUsers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStartedUsers(int index, User value) {
        if (value != null) {
            ensureStartedUsersIsMutable();
            this.startedUsers_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStartedUsers(User.Builder builderForValue) {
        ensureStartedUsersIsMutable();
        this.startedUsers_.add((User) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStartedUsers(int index, User.Builder builderForValue) {
        ensureStartedUsersIsMutable();
        this.startedUsers_.add(index, (User) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStartedUsers(Iterable<? extends User> values) {
        ensureStartedUsersIsMutable();
        AbstractMessageLite.addAll(values, this.startedUsers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartedUsers() {
        this.startedUsers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStartedUsers(int index) {
        ensureStartedUsersIsMutable();
        this.startedUsers_.remove(index);
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public List<Integer> getStartedUserArrayList() {
        return this.startedUserArray_;
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public int getStartedUserArrayCount() {
        return this.startedUserArray_.size();
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public int getStartedUserArray(int index) {
        return this.startedUserArray_.getInt(index);
    }

    private void ensureStartedUserArrayIsMutable() {
        if (!this.startedUserArray_.isModifiable()) {
            this.startedUserArray_ = GeneratedMessageLite.mutableCopy(this.startedUserArray_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedUserArray(int index, int value) {
        ensureStartedUserArrayIsMutable();
        this.startedUserArray_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStartedUserArray(int value) {
        ensureStartedUserArrayIsMutable();
        this.startedUserArray_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStartedUserArray(Iterable<? extends Integer> values) {
        ensureStartedUserArrayIsMutable();
        AbstractMessageLite.addAll(values, this.startedUserArray_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartedUserArray() {
        this.startedUserArray_ = emptyIntList();
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public List<Integer> getUserLruList() {
        return this.userLru_;
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public int getUserLruCount() {
        return this.userLru_.size();
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public int getUserLru(int index) {
        return this.userLru_.getInt(index);
    }

    private void ensureUserLruIsMutable() {
        if (!this.userLru_.isModifiable()) {
            this.userLru_ = GeneratedMessageLite.mutableCopy(this.userLru_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserLru(int index, int value) {
        ensureUserLruIsMutable();
        this.userLru_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserLru(int value) {
        ensureUserLruIsMutable();
        this.userLru_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserLru(Iterable<? extends Integer> values) {
        ensureUserLruIsMutable();
        AbstractMessageLite.addAll(values, this.userLru_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserLru() {
        this.userLru_ = emptyIntList();
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public List<UserProfile> getUserProfileGroupIdsList() {
        return this.userProfileGroupIds_;
    }

    public List<? extends UserProfileOrBuilder> getUserProfileGroupIdsOrBuilderList() {
        return this.userProfileGroupIds_;
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public int getUserProfileGroupIdsCount() {
        return this.userProfileGroupIds_.size();
    }

    @Override // com.android.server.am.UserControllerProtoOrBuilder
    public UserProfile getUserProfileGroupIds(int index) {
        return this.userProfileGroupIds_.get(index);
    }

    public UserProfileOrBuilder getUserProfileGroupIdsOrBuilder(int index) {
        return this.userProfileGroupIds_.get(index);
    }

    private void ensureUserProfileGroupIdsIsMutable() {
        if (!this.userProfileGroupIds_.isModifiable()) {
            this.userProfileGroupIds_ = GeneratedMessageLite.mutableCopy(this.userProfileGroupIds_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserProfileGroupIds(int index, UserProfile value) {
        if (value != null) {
            ensureUserProfileGroupIdsIsMutable();
            this.userProfileGroupIds_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserProfileGroupIds(int index, UserProfile.Builder builderForValue) {
        ensureUserProfileGroupIdsIsMutable();
        this.userProfileGroupIds_.set(index, (UserProfile) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserProfileGroupIds(UserProfile value) {
        if (value != null) {
            ensureUserProfileGroupIdsIsMutable();
            this.userProfileGroupIds_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserProfileGroupIds(int index, UserProfile value) {
        if (value != null) {
            ensureUserProfileGroupIdsIsMutable();
            this.userProfileGroupIds_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserProfileGroupIds(UserProfile.Builder builderForValue) {
        ensureUserProfileGroupIdsIsMutable();
        this.userProfileGroupIds_.add((UserProfile) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserProfileGroupIds(int index, UserProfile.Builder builderForValue) {
        ensureUserProfileGroupIdsIsMutable();
        this.userProfileGroupIds_.add(index, (UserProfile) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserProfileGroupIds(Iterable<? extends UserProfile> values) {
        ensureUserProfileGroupIdsIsMutable();
        AbstractMessageLite.addAll(values, this.userProfileGroupIds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserProfileGroupIds() {
        this.userProfileGroupIds_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUserProfileGroupIds(int index) {
        ensureUserProfileGroupIdsIsMutable();
        this.userProfileGroupIds_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.startedUsers_.size(); i++) {
            output.writeMessage(1, this.startedUsers_.get(i));
        }
        for (int i2 = 0; i2 < this.startedUserArray_.size(); i2++) {
            output.writeInt32(2, this.startedUserArray_.getInt(i2));
        }
        for (int i3 = 0; i3 < this.userLru_.size(); i3++) {
            output.writeInt32(3, this.userLru_.getInt(i3));
        }
        for (int i4 = 0; i4 < this.userProfileGroupIds_.size(); i4++) {
            output.writeMessage(4, this.userProfileGroupIds_.get(i4));
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        for (int i = 0; i < this.startedUsers_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.startedUsers_.get(i));
        }
        int dataSize = 0;
        for (int i2 = 0; i2 < this.startedUserArray_.size(); i2++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.startedUserArray_.getInt(i2));
        }
        int size3 = size2 + dataSize + (getStartedUserArrayList().size() * 1);
        int dataSize2 = 0;
        for (int i3 = 0; i3 < this.userLru_.size(); i3++) {
            dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.userLru_.getInt(i3));
        }
        int size4 = size3 + dataSize2 + (getUserLruList().size() * 1);
        for (int i4 = 0; i4 < this.userProfileGroupIds_.size(); i4++) {
            size4 += CodedOutputStream.computeMessageSize(4, this.userProfileGroupIds_.get(i4));
        }
        int size5 = size4 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size5;
        return size5;
    }

    public static UserControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserControllerProto parseFrom(InputStream input) throws IOException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UserControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UserControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UserControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UserControllerProto, Builder> implements UserControllerProtoOrBuilder {
        private Builder() {
            super(UserControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public List<User> getStartedUsersList() {
            return Collections.unmodifiableList(((UserControllerProto) this.instance).getStartedUsersList());
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public int getStartedUsersCount() {
            return ((UserControllerProto) this.instance).getStartedUsersCount();
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public User getStartedUsers(int index) {
            return ((UserControllerProto) this.instance).getStartedUsers(index);
        }

        public Builder setStartedUsers(int index, User value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).setStartedUsers((UserControllerProto) index, (int) value);
            return this;
        }

        public Builder setStartedUsers(int index, User.Builder builderForValue) {
            copyOnWrite();
            ((UserControllerProto) this.instance).setStartedUsers((UserControllerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStartedUsers(User value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addStartedUsers((UserControllerProto) value);
            return this;
        }

        public Builder addStartedUsers(int index, User value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addStartedUsers((UserControllerProto) index, (int) value);
            return this;
        }

        public Builder addStartedUsers(User.Builder builderForValue) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addStartedUsers((UserControllerProto) builderForValue);
            return this;
        }

        public Builder addStartedUsers(int index, User.Builder builderForValue) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addStartedUsers((UserControllerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStartedUsers(Iterable<? extends User> values) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addAllStartedUsers(values);
            return this;
        }

        public Builder clearStartedUsers() {
            copyOnWrite();
            ((UserControllerProto) this.instance).clearStartedUsers();
            return this;
        }

        public Builder removeStartedUsers(int index) {
            copyOnWrite();
            ((UserControllerProto) this.instance).removeStartedUsers(index);
            return this;
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public List<Integer> getStartedUserArrayList() {
            return Collections.unmodifiableList(((UserControllerProto) this.instance).getStartedUserArrayList());
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public int getStartedUserArrayCount() {
            return ((UserControllerProto) this.instance).getStartedUserArrayCount();
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public int getStartedUserArray(int index) {
            return ((UserControllerProto) this.instance).getStartedUserArray(index);
        }

        public Builder setStartedUserArray(int index, int value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).setStartedUserArray(index, value);
            return this;
        }

        public Builder addStartedUserArray(int value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addStartedUserArray(value);
            return this;
        }

        public Builder addAllStartedUserArray(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addAllStartedUserArray(values);
            return this;
        }

        public Builder clearStartedUserArray() {
            copyOnWrite();
            ((UserControllerProto) this.instance).clearStartedUserArray();
            return this;
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public List<Integer> getUserLruList() {
            return Collections.unmodifiableList(((UserControllerProto) this.instance).getUserLruList());
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public int getUserLruCount() {
            return ((UserControllerProto) this.instance).getUserLruCount();
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public int getUserLru(int index) {
            return ((UserControllerProto) this.instance).getUserLru(index);
        }

        public Builder setUserLru(int index, int value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).setUserLru(index, value);
            return this;
        }

        public Builder addUserLru(int value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addUserLru(value);
            return this;
        }

        public Builder addAllUserLru(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addAllUserLru(values);
            return this;
        }

        public Builder clearUserLru() {
            copyOnWrite();
            ((UserControllerProto) this.instance).clearUserLru();
            return this;
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public List<UserProfile> getUserProfileGroupIdsList() {
            return Collections.unmodifiableList(((UserControllerProto) this.instance).getUserProfileGroupIdsList());
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public int getUserProfileGroupIdsCount() {
            return ((UserControllerProto) this.instance).getUserProfileGroupIdsCount();
        }

        @Override // com.android.server.am.UserControllerProtoOrBuilder
        public UserProfile getUserProfileGroupIds(int index) {
            return ((UserControllerProto) this.instance).getUserProfileGroupIds(index);
        }

        public Builder setUserProfileGroupIds(int index, UserProfile value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).setUserProfileGroupIds((UserControllerProto) index, (int) value);
            return this;
        }

        public Builder setUserProfileGroupIds(int index, UserProfile.Builder builderForValue) {
            copyOnWrite();
            ((UserControllerProto) this.instance).setUserProfileGroupIds((UserControllerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUserProfileGroupIds(UserProfile value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addUserProfileGroupIds((UserControllerProto) value);
            return this;
        }

        public Builder addUserProfileGroupIds(int index, UserProfile value) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addUserProfileGroupIds((UserControllerProto) index, (int) value);
            return this;
        }

        public Builder addUserProfileGroupIds(UserProfile.Builder builderForValue) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addUserProfileGroupIds((UserControllerProto) builderForValue);
            return this;
        }

        public Builder addUserProfileGroupIds(int index, UserProfile.Builder builderForValue) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addUserProfileGroupIds((UserControllerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUserProfileGroupIds(Iterable<? extends UserProfile> values) {
            copyOnWrite();
            ((UserControllerProto) this.instance).addAllUserProfileGroupIds(values);
            return this;
        }

        public Builder clearUserProfileGroupIds() {
            copyOnWrite();
            ((UserControllerProto) this.instance).clearUserProfileGroupIds();
            return this;
        }

        public Builder removeUserProfileGroupIds(int index) {
            copyOnWrite();
            ((UserControllerProto) this.instance).removeUserProfileGroupIds(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UserControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.startedUsers_.makeImmutable();
                this.startedUserArray_.makeImmutable();
                this.userLru_.makeImmutable();
                this.userProfileGroupIds_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UserControllerProto other = (UserControllerProto) arg1;
                this.startedUsers_ = visitor.visitList(this.startedUsers_, other.startedUsers_);
                this.startedUserArray_ = visitor.visitIntList(this.startedUserArray_, other.startedUserArray_);
                this.userLru_ = visitor.visitIntList(this.userLru_, other.userLru_);
                this.userProfileGroupIds_ = visitor.visitList(this.userProfileGroupIds_, other.userProfileGroupIds_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            if (!this.startedUsers_.isModifiable()) {
                                this.startedUsers_ = GeneratedMessageLite.mutableCopy(this.startedUsers_);
                            }
                            this.startedUsers_.add((User) input.readMessage(User.parser(), extensionRegistry));
                        } else if (tag == 16) {
                            if (!this.startedUserArray_.isModifiable()) {
                                this.startedUserArray_ = GeneratedMessageLite.mutableCopy(this.startedUserArray_);
                            }
                            this.startedUserArray_.addInt(input.readInt32());
                        } else if (tag == 18) {
                            int limit = input.pushLimit(input.readRawVarint32());
                            if (!this.startedUserArray_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                this.startedUserArray_ = GeneratedMessageLite.mutableCopy(this.startedUserArray_);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.startedUserArray_.addInt(input.readInt32());
                            }
                            input.popLimit(limit);
                        } else if (tag == 24) {
                            if (!this.userLru_.isModifiable()) {
                                this.userLru_ = GeneratedMessageLite.mutableCopy(this.userLru_);
                            }
                            this.userLru_.addInt(input.readInt32());
                        } else if (tag == 26) {
                            int limit2 = input.pushLimit(input.readRawVarint32());
                            if (!this.userLru_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                this.userLru_ = GeneratedMessageLite.mutableCopy(this.userLru_);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.userLru_.addInt(input.readInt32());
                            }
                            input.popLimit(limit2);
                        } else if (tag == 34) {
                            if (!this.userProfileGroupIds_.isModifiable()) {
                                this.userProfileGroupIds_ = GeneratedMessageLite.mutableCopy(this.userProfileGroupIds_);
                            }
                            this.userProfileGroupIds_.add((UserProfile) input.readMessage(UserProfile.parser(), extensionRegistry));
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (UserControllerProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static UserControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UserControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
