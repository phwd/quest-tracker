package android.service.notification;

import android.service.notification.PackageRemoteViewInfoProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class NotificationRemoteViewsProto extends GeneratedMessageLite<NotificationRemoteViewsProto, Builder> implements NotificationRemoteViewsProtoOrBuilder {
    private static final NotificationRemoteViewsProto DEFAULT_INSTANCE = new NotificationRemoteViewsProto();
    public static final int PACKAGE_REMOTE_VIEW_INFO_FIELD_NUMBER = 1;
    private static volatile Parser<NotificationRemoteViewsProto> PARSER;
    private Internal.ProtobufList<PackageRemoteViewInfoProto> packageRemoteViewInfo_ = emptyProtobufList();

    private NotificationRemoteViewsProto() {
    }

    @Override // android.service.notification.NotificationRemoteViewsProtoOrBuilder
    public List<PackageRemoteViewInfoProto> getPackageRemoteViewInfoList() {
        return this.packageRemoteViewInfo_;
    }

    public List<? extends PackageRemoteViewInfoProtoOrBuilder> getPackageRemoteViewInfoOrBuilderList() {
        return this.packageRemoteViewInfo_;
    }

    @Override // android.service.notification.NotificationRemoteViewsProtoOrBuilder
    public int getPackageRemoteViewInfoCount() {
        return this.packageRemoteViewInfo_.size();
    }

    @Override // android.service.notification.NotificationRemoteViewsProtoOrBuilder
    public PackageRemoteViewInfoProto getPackageRemoteViewInfo(int index) {
        return this.packageRemoteViewInfo_.get(index);
    }

    public PackageRemoteViewInfoProtoOrBuilder getPackageRemoteViewInfoOrBuilder(int index) {
        return this.packageRemoteViewInfo_.get(index);
    }

    private void ensurePackageRemoteViewInfoIsMutable() {
        if (!this.packageRemoteViewInfo_.isModifiable()) {
            this.packageRemoteViewInfo_ = GeneratedMessageLite.mutableCopy(this.packageRemoteViewInfo_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto value) {
        if (value != null) {
            ensurePackageRemoteViewInfoIsMutable();
            this.packageRemoteViewInfo_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto.Builder builderForValue) {
        ensurePackageRemoteViewInfoIsMutable();
        this.packageRemoteViewInfo_.set(index, (PackageRemoteViewInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageRemoteViewInfo(PackageRemoteViewInfoProto value) {
        if (value != null) {
            ensurePackageRemoteViewInfoIsMutable();
            this.packageRemoteViewInfo_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto value) {
        if (value != null) {
            ensurePackageRemoteViewInfoIsMutable();
            this.packageRemoteViewInfo_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageRemoteViewInfo(PackageRemoteViewInfoProto.Builder builderForValue) {
        ensurePackageRemoteViewInfoIsMutable();
        this.packageRemoteViewInfo_.add((PackageRemoteViewInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto.Builder builderForValue) {
        ensurePackageRemoteViewInfoIsMutable();
        this.packageRemoteViewInfo_.add(index, (PackageRemoteViewInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackageRemoteViewInfo(Iterable<? extends PackageRemoteViewInfoProto> values) {
        ensurePackageRemoteViewInfoIsMutable();
        AbstractMessageLite.addAll(values, this.packageRemoteViewInfo_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageRemoteViewInfo() {
        this.packageRemoteViewInfo_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackageRemoteViewInfo(int index) {
        ensurePackageRemoteViewInfoIsMutable();
        this.packageRemoteViewInfo_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.packageRemoteViewInfo_.size(); i++) {
            output.writeMessage(1, this.packageRemoteViewInfo_.get(i));
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
        for (int i = 0; i < this.packageRemoteViewInfo_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.packageRemoteViewInfo_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NotificationRemoteViewsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationRemoteViewsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationRemoteViewsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationRemoteViewsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationRemoteViewsProto parseFrom(InputStream input) throws IOException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationRemoteViewsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationRemoteViewsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NotificationRemoteViewsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationRemoteViewsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationRemoteViewsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationRemoteViewsProto parseFrom(CodedInputStream input) throws IOException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationRemoteViewsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationRemoteViewsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NotificationRemoteViewsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NotificationRemoteViewsProto, Builder> implements NotificationRemoteViewsProtoOrBuilder {
        private Builder() {
            super(NotificationRemoteViewsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.NotificationRemoteViewsProtoOrBuilder
        public List<PackageRemoteViewInfoProto> getPackageRemoteViewInfoList() {
            return Collections.unmodifiableList(((NotificationRemoteViewsProto) this.instance).getPackageRemoteViewInfoList());
        }

        @Override // android.service.notification.NotificationRemoteViewsProtoOrBuilder
        public int getPackageRemoteViewInfoCount() {
            return ((NotificationRemoteViewsProto) this.instance).getPackageRemoteViewInfoCount();
        }

        @Override // android.service.notification.NotificationRemoteViewsProtoOrBuilder
        public PackageRemoteViewInfoProto getPackageRemoteViewInfo(int index) {
            return ((NotificationRemoteViewsProto) this.instance).getPackageRemoteViewInfo(index);
        }

        public Builder setPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto value) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).setPackageRemoteViewInfo((NotificationRemoteViewsProto) index, (int) value);
            return this;
        }

        public Builder setPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).setPackageRemoteViewInfo((NotificationRemoteViewsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackageRemoteViewInfo(PackageRemoteViewInfoProto value) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).addPackageRemoteViewInfo((NotificationRemoteViewsProto) value);
            return this;
        }

        public Builder addPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto value) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).addPackageRemoteViewInfo((NotificationRemoteViewsProto) index, (int) value);
            return this;
        }

        public Builder addPackageRemoteViewInfo(PackageRemoteViewInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).addPackageRemoteViewInfo((NotificationRemoteViewsProto) builderForValue);
            return this;
        }

        public Builder addPackageRemoteViewInfo(int index, PackageRemoteViewInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).addPackageRemoteViewInfo((NotificationRemoteViewsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackageRemoteViewInfo(Iterable<? extends PackageRemoteViewInfoProto> values) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).addAllPackageRemoteViewInfo(values);
            return this;
        }

        public Builder clearPackageRemoteViewInfo() {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).clearPackageRemoteViewInfo();
            return this;
        }

        public Builder removePackageRemoteViewInfo(int index) {
            copyOnWrite();
            ((NotificationRemoteViewsProto) this.instance).removePackageRemoteViewInfo(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NotificationRemoteViewsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.packageRemoteViewInfo_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.packageRemoteViewInfo_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.packageRemoteViewInfo_, ((NotificationRemoteViewsProto) arg1).packageRemoteViewInfo_);
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
                            if (!this.packageRemoteViewInfo_.isModifiable()) {
                                this.packageRemoteViewInfo_ = GeneratedMessageLite.mutableCopy(this.packageRemoteViewInfo_);
                            }
                            this.packageRemoteViewInfo_.add((PackageRemoteViewInfoProto) input.readMessage(PackageRemoteViewInfoProto.parser(), extensionRegistry));
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
                    synchronized (NotificationRemoteViewsProto.class) {
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

    public static NotificationRemoteViewsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NotificationRemoteViewsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
