package com.android.server.am;

import com.android.server.am.ServiceRecordProto;
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

public final class ActiveServicesProto extends GeneratedMessageLite<ActiveServicesProto, Builder> implements ActiveServicesProtoOrBuilder {
    private static final ActiveServicesProto DEFAULT_INSTANCE = new ActiveServicesProto();
    private static volatile Parser<ActiveServicesProto> PARSER = null;
    public static final int SERVICES_BY_USERS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<ServicesByUser> servicesByUsers_ = emptyProtobufList();

    public interface ServicesByUserOrBuilder extends MessageLiteOrBuilder {
        ServiceRecordProto getServiceRecords(int i);

        int getServiceRecordsCount();

        List<ServiceRecordProto> getServiceRecordsList();

        int getUserId();

        boolean hasUserId();
    }

    private ActiveServicesProto() {
    }

    public static final class ServicesByUser extends GeneratedMessageLite<ServicesByUser, Builder> implements ServicesByUserOrBuilder {
        private static final ServicesByUser DEFAULT_INSTANCE = new ServicesByUser();
        private static volatile Parser<ServicesByUser> PARSER = null;
        public static final int SERVICE_RECORDS_FIELD_NUMBER = 2;
        public static final int USER_ID_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<ServiceRecordProto> serviceRecords_ = emptyProtobufList();
        private int userId_ = 0;

        private ServicesByUser() {
        }

        @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
        public boolean hasUserId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
        public int getUserId() {
            return this.userId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserId(int value) {
            this.bitField0_ |= 1;
            this.userId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUserId() {
            this.bitField0_ &= -2;
            this.userId_ = 0;
        }

        @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
        public List<ServiceRecordProto> getServiceRecordsList() {
            return this.serviceRecords_;
        }

        public List<? extends ServiceRecordProtoOrBuilder> getServiceRecordsOrBuilderList() {
            return this.serviceRecords_;
        }

        @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
        public int getServiceRecordsCount() {
            return this.serviceRecords_.size();
        }

        @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
        public ServiceRecordProto getServiceRecords(int index) {
            return this.serviceRecords_.get(index);
        }

        public ServiceRecordProtoOrBuilder getServiceRecordsOrBuilder(int index) {
            return this.serviceRecords_.get(index);
        }

        private void ensureServiceRecordsIsMutable() {
            if (!this.serviceRecords_.isModifiable()) {
                this.serviceRecords_ = GeneratedMessageLite.mutableCopy(this.serviceRecords_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setServiceRecords(int index, ServiceRecordProto value) {
            if (value != null) {
                ensureServiceRecordsIsMutable();
                this.serviceRecords_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setServiceRecords(int index, ServiceRecordProto.Builder builderForValue) {
            ensureServiceRecordsIsMutable();
            this.serviceRecords_.set(index, (ServiceRecordProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServiceRecords(ServiceRecordProto value) {
            if (value != null) {
                ensureServiceRecordsIsMutable();
                this.serviceRecords_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServiceRecords(int index, ServiceRecordProto value) {
            if (value != null) {
                ensureServiceRecordsIsMutable();
                this.serviceRecords_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServiceRecords(ServiceRecordProto.Builder builderForValue) {
            ensureServiceRecordsIsMutable();
            this.serviceRecords_.add((ServiceRecordProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addServiceRecords(int index, ServiceRecordProto.Builder builderForValue) {
            ensureServiceRecordsIsMutable();
            this.serviceRecords_.add(index, (ServiceRecordProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllServiceRecords(Iterable<? extends ServiceRecordProto> values) {
            ensureServiceRecordsIsMutable();
            AbstractMessageLite.addAll(values, this.serviceRecords_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearServiceRecords() {
            this.serviceRecords_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeServiceRecords(int index) {
            ensureServiceRecordsIsMutable();
            this.serviceRecords_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.userId_);
            }
            for (int i = 0; i < this.serviceRecords_.size(); i++) {
                output.writeMessage(2, this.serviceRecords_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
            }
            for (int i = 0; i < this.serviceRecords_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.serviceRecords_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ServicesByUser parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ServicesByUser parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ServicesByUser parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ServicesByUser parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ServicesByUser parseFrom(InputStream input) throws IOException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ServicesByUser parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ServicesByUser parseDelimitedFrom(InputStream input) throws IOException {
            return (ServicesByUser) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ServicesByUser parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ServicesByUser) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ServicesByUser parseFrom(CodedInputStream input) throws IOException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ServicesByUser parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ServicesByUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ServicesByUser prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ServicesByUser, Builder> implements ServicesByUserOrBuilder {
            private Builder() {
                super(ServicesByUser.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
            public boolean hasUserId() {
                return ((ServicesByUser) this.instance).hasUserId();
            }

            @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
            public int getUserId() {
                return ((ServicesByUser) this.instance).getUserId();
            }

            public Builder setUserId(int value) {
                copyOnWrite();
                ((ServicesByUser) this.instance).setUserId(value);
                return this;
            }

            public Builder clearUserId() {
                copyOnWrite();
                ((ServicesByUser) this.instance).clearUserId();
                return this;
            }

            @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
            public List<ServiceRecordProto> getServiceRecordsList() {
                return Collections.unmodifiableList(((ServicesByUser) this.instance).getServiceRecordsList());
            }

            @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
            public int getServiceRecordsCount() {
                return ((ServicesByUser) this.instance).getServiceRecordsCount();
            }

            @Override // com.android.server.am.ActiveServicesProto.ServicesByUserOrBuilder
            public ServiceRecordProto getServiceRecords(int index) {
                return ((ServicesByUser) this.instance).getServiceRecords(index);
            }

            public Builder setServiceRecords(int index, ServiceRecordProto value) {
                copyOnWrite();
                ((ServicesByUser) this.instance).setServiceRecords((ServicesByUser) index, (int) value);
                return this;
            }

            public Builder setServiceRecords(int index, ServiceRecordProto.Builder builderForValue) {
                copyOnWrite();
                ((ServicesByUser) this.instance).setServiceRecords((ServicesByUser) index, (int) builderForValue);
                return this;
            }

            public Builder addServiceRecords(ServiceRecordProto value) {
                copyOnWrite();
                ((ServicesByUser) this.instance).addServiceRecords((ServicesByUser) value);
                return this;
            }

            public Builder addServiceRecords(int index, ServiceRecordProto value) {
                copyOnWrite();
                ((ServicesByUser) this.instance).addServiceRecords((ServicesByUser) index, (int) value);
                return this;
            }

            public Builder addServiceRecords(ServiceRecordProto.Builder builderForValue) {
                copyOnWrite();
                ((ServicesByUser) this.instance).addServiceRecords((ServicesByUser) builderForValue);
                return this;
            }

            public Builder addServiceRecords(int index, ServiceRecordProto.Builder builderForValue) {
                copyOnWrite();
                ((ServicesByUser) this.instance).addServiceRecords((ServicesByUser) index, (int) builderForValue);
                return this;
            }

            public Builder addAllServiceRecords(Iterable<? extends ServiceRecordProto> values) {
                copyOnWrite();
                ((ServicesByUser) this.instance).addAllServiceRecords(values);
                return this;
            }

            public Builder clearServiceRecords() {
                copyOnWrite();
                ((ServicesByUser) this.instance).clearServiceRecords();
                return this;
            }

            public Builder removeServiceRecords(int index) {
                copyOnWrite();
                ((ServicesByUser) this.instance).removeServiceRecords(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ServicesByUser();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.serviceRecords_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ServicesByUser other = (ServicesByUser) arg1;
                    this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                    this.serviceRecords_ = visitor.visitList(this.serviceRecords_, other.serviceRecords_);
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
                                this.userId_ = input.readInt32();
                            } else if (tag == 18) {
                                if (!this.serviceRecords_.isModifiable()) {
                                    this.serviceRecords_ = GeneratedMessageLite.mutableCopy(this.serviceRecords_);
                                }
                                this.serviceRecords_.add((ServiceRecordProto) input.readMessage(ServiceRecordProto.parser(), extensionRegistry));
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
                        synchronized (ServicesByUser.class) {
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

        public static ServicesByUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ServicesByUser> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.ActiveServicesProtoOrBuilder
    public List<ServicesByUser> getServicesByUsersList() {
        return this.servicesByUsers_;
    }

    public List<? extends ServicesByUserOrBuilder> getServicesByUsersOrBuilderList() {
        return this.servicesByUsers_;
    }

    @Override // com.android.server.am.ActiveServicesProtoOrBuilder
    public int getServicesByUsersCount() {
        return this.servicesByUsers_.size();
    }

    @Override // com.android.server.am.ActiveServicesProtoOrBuilder
    public ServicesByUser getServicesByUsers(int index) {
        return this.servicesByUsers_.get(index);
    }

    public ServicesByUserOrBuilder getServicesByUsersOrBuilder(int index) {
        return this.servicesByUsers_.get(index);
    }

    private void ensureServicesByUsersIsMutable() {
        if (!this.servicesByUsers_.isModifiable()) {
            this.servicesByUsers_ = GeneratedMessageLite.mutableCopy(this.servicesByUsers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServicesByUsers(int index, ServicesByUser value) {
        if (value != null) {
            ensureServicesByUsersIsMutable();
            this.servicesByUsers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServicesByUsers(int index, ServicesByUser.Builder builderForValue) {
        ensureServicesByUsersIsMutable();
        this.servicesByUsers_.set(index, (ServicesByUser) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServicesByUsers(ServicesByUser value) {
        if (value != null) {
            ensureServicesByUsersIsMutable();
            this.servicesByUsers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServicesByUsers(int index, ServicesByUser value) {
        if (value != null) {
            ensureServicesByUsersIsMutable();
            this.servicesByUsers_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServicesByUsers(ServicesByUser.Builder builderForValue) {
        ensureServicesByUsersIsMutable();
        this.servicesByUsers_.add((ServicesByUser) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServicesByUsers(int index, ServicesByUser.Builder builderForValue) {
        ensureServicesByUsersIsMutable();
        this.servicesByUsers_.add(index, (ServicesByUser) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllServicesByUsers(Iterable<? extends ServicesByUser> values) {
        ensureServicesByUsersIsMutable();
        AbstractMessageLite.addAll(values, this.servicesByUsers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServicesByUsers() {
        this.servicesByUsers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeServicesByUsers(int index) {
        ensureServicesByUsersIsMutable();
        this.servicesByUsers_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.servicesByUsers_.size(); i++) {
            output.writeMessage(1, this.servicesByUsers_.get(i));
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
        for (int i = 0; i < this.servicesByUsers_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.servicesByUsers_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActiveServicesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActiveServicesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActiveServicesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActiveServicesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActiveServicesProto parseFrom(InputStream input) throws IOException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActiveServicesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActiveServicesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActiveServicesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActiveServicesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActiveServicesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActiveServicesProto parseFrom(CodedInputStream input) throws IOException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActiveServicesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActiveServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActiveServicesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActiveServicesProto, Builder> implements ActiveServicesProtoOrBuilder {
        private Builder() {
            super(ActiveServicesProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActiveServicesProtoOrBuilder
        public List<ServicesByUser> getServicesByUsersList() {
            return Collections.unmodifiableList(((ActiveServicesProto) this.instance).getServicesByUsersList());
        }

        @Override // com.android.server.am.ActiveServicesProtoOrBuilder
        public int getServicesByUsersCount() {
            return ((ActiveServicesProto) this.instance).getServicesByUsersCount();
        }

        @Override // com.android.server.am.ActiveServicesProtoOrBuilder
        public ServicesByUser getServicesByUsers(int index) {
            return ((ActiveServicesProto) this.instance).getServicesByUsers(index);
        }

        public Builder setServicesByUsers(int index, ServicesByUser value) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).setServicesByUsers((ActiveServicesProto) index, (int) value);
            return this;
        }

        public Builder setServicesByUsers(int index, ServicesByUser.Builder builderForValue) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).setServicesByUsers((ActiveServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addServicesByUsers(ServicesByUser value) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).addServicesByUsers((ActiveServicesProto) value);
            return this;
        }

        public Builder addServicesByUsers(int index, ServicesByUser value) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).addServicesByUsers((ActiveServicesProto) index, (int) value);
            return this;
        }

        public Builder addServicesByUsers(ServicesByUser.Builder builderForValue) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).addServicesByUsers((ActiveServicesProto) builderForValue);
            return this;
        }

        public Builder addServicesByUsers(int index, ServicesByUser.Builder builderForValue) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).addServicesByUsers((ActiveServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllServicesByUsers(Iterable<? extends ServicesByUser> values) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).addAllServicesByUsers(values);
            return this;
        }

        public Builder clearServicesByUsers() {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).clearServicesByUsers();
            return this;
        }

        public Builder removeServicesByUsers(int index) {
            copyOnWrite();
            ((ActiveServicesProto) this.instance).removeServicesByUsers(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActiveServicesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.servicesByUsers_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.servicesByUsers_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.servicesByUsers_, ((ActiveServicesProto) arg1).servicesByUsers_);
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
                            if (!this.servicesByUsers_.isModifiable()) {
                                this.servicesByUsers_ = GeneratedMessageLite.mutableCopy(this.servicesByUsers_);
                            }
                            this.servicesByUsers_.add((ServicesByUser) input.readMessage(ServicesByUser.parser(), extensionRegistry));
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
                    synchronized (ActiveServicesProto.class) {
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

    public static ActiveServicesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActiveServicesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
