package android.service.notification;

import android.content.ComponentNameProto;
import android.content.ComponentNameProtoOrBuilder;
import android.service.notification.ManagedServiceInfoProto;
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

public final class ManagedServicesProto extends GeneratedMessageLite<ManagedServicesProto, Builder> implements ManagedServicesProtoOrBuilder {
    public static final int APPROVED_FIELD_NUMBER = 2;
    public static final int CAPTION_FIELD_NUMBER = 1;
    private static final ManagedServicesProto DEFAULT_INSTANCE = new ManagedServicesProto();
    public static final int ENABLED_FIELD_NUMBER = 3;
    public static final int LIVE_SERVICES_FIELD_NUMBER = 4;
    private static volatile Parser<ManagedServicesProto> PARSER = null;
    public static final int SNOOZED_FIELD_NUMBER = 5;
    private Internal.ProtobufList<ServiceProto> approved_ = emptyProtobufList();
    private int bitField0_;
    private String caption_ = "";
    private Internal.ProtobufList<ComponentNameProto> enabled_ = emptyProtobufList();
    private Internal.ProtobufList<ManagedServiceInfoProto> liveServices_ = emptyProtobufList();
    private Internal.ProtobufList<ComponentNameProto> snoozed_ = emptyProtobufList();

    public interface ServiceProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsPrimary();

        String getName(int i);

        ByteString getNameBytes(int i);

        int getNameCount();

        List<String> getNameList();

        int getUserId();

        boolean hasIsPrimary();

        boolean hasUserId();
    }

    private ManagedServicesProto() {
    }

    public static final class ServiceProto extends GeneratedMessageLite<ServiceProto, Builder> implements ServiceProtoOrBuilder {
        private static final ServiceProto DEFAULT_INSTANCE = new ServiceProto();
        public static final int IS_PRIMARY_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ServiceProto> PARSER = null;
        public static final int USER_ID_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean isPrimary_ = false;
        private Internal.ProtobufList<String> name_ = GeneratedMessageLite.emptyProtobufList();
        private int userId_ = 0;

        private ServiceProto() {
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public List<String> getNameList() {
            return this.name_;
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public int getNameCount() {
            return this.name_.size();
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public String getName(int index) {
            return this.name_.get(index);
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public ByteString getNameBytes(int index) {
            return ByteString.copyFromUtf8(this.name_.get(index));
        }

        private void ensureNameIsMutable() {
            if (!this.name_.isModifiable()) {
                this.name_ = GeneratedMessageLite.mutableCopy(this.name_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(int index, String value) {
            if (value != null) {
                ensureNameIsMutable();
                this.name_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addName(String value) {
            if (value != null) {
                ensureNameIsMutable();
                this.name_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllName(Iterable<String> values) {
            ensureNameIsMutable();
            AbstractMessageLite.addAll(values, this.name_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.name_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addNameBytes(ByteString value) {
            if (value != null) {
                ensureNameIsMutable();
                this.name_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public boolean hasUserId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
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

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public boolean hasIsPrimary() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
        public boolean getIsPrimary() {
            return this.isPrimary_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsPrimary(boolean value) {
            this.bitField0_ |= 2;
            this.isPrimary_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsPrimary() {
            this.bitField0_ &= -3;
            this.isPrimary_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.name_.size(); i++) {
                output.writeString(1, this.name_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(2, this.userId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(3, this.isPrimary_);
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int dataSize = 0;
            for (int i = 0; i < this.name_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.name_.get(i));
            }
            int size2 = 0 + dataSize + (getNameList().size() * 1);
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeInt32Size(2, this.userId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isPrimary_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ServiceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ServiceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ServiceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ServiceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ServiceProto parseFrom(InputStream input) throws IOException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ServiceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ServiceProto parseDelimitedFrom(InputStream input) throws IOException {
            return (ServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ServiceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ServiceProto parseFrom(CodedInputStream input) throws IOException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ServiceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ServiceProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ServiceProto, Builder> implements ServiceProtoOrBuilder {
            private Builder() {
                super(ServiceProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public List<String> getNameList() {
                return Collections.unmodifiableList(((ServiceProto) this.instance).getNameList());
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public int getNameCount() {
                return ((ServiceProto) this.instance).getNameCount();
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public String getName(int index) {
                return ((ServiceProto) this.instance).getName(index);
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public ByteString getNameBytes(int index) {
                return ((ServiceProto) this.instance).getNameBytes(index);
            }

            public Builder setName(int index, String value) {
                copyOnWrite();
                ((ServiceProto) this.instance).setName(index, value);
                return this;
            }

            public Builder addName(String value) {
                copyOnWrite();
                ((ServiceProto) this.instance).addName(value);
                return this;
            }

            public Builder addAllName(Iterable<String> values) {
                copyOnWrite();
                ((ServiceProto) this.instance).addAllName(values);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((ServiceProto) this.instance).clearName();
                return this;
            }

            public Builder addNameBytes(ByteString value) {
                copyOnWrite();
                ((ServiceProto) this.instance).addNameBytes(value);
                return this;
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public boolean hasUserId() {
                return ((ServiceProto) this.instance).hasUserId();
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public int getUserId() {
                return ((ServiceProto) this.instance).getUserId();
            }

            public Builder setUserId(int value) {
                copyOnWrite();
                ((ServiceProto) this.instance).setUserId(value);
                return this;
            }

            public Builder clearUserId() {
                copyOnWrite();
                ((ServiceProto) this.instance).clearUserId();
                return this;
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public boolean hasIsPrimary() {
                return ((ServiceProto) this.instance).hasIsPrimary();
            }

            @Override // android.service.notification.ManagedServicesProto.ServiceProtoOrBuilder
            public boolean getIsPrimary() {
                return ((ServiceProto) this.instance).getIsPrimary();
            }

            public Builder setIsPrimary(boolean value) {
                copyOnWrite();
                ((ServiceProto) this.instance).setIsPrimary(value);
                return this;
            }

            public Builder clearIsPrimary() {
                copyOnWrite();
                ((ServiceProto) this.instance).clearIsPrimary();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ServiceProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.name_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ServiceProto other = (ServiceProto) arg1;
                    this.name_ = visitor.visitList(this.name_, other.name_);
                    this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                    this.isPrimary_ = visitor.visitBoolean(hasIsPrimary(), this.isPrimary_, other.hasIsPrimary(), other.isPrimary_);
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
                            } else if (tag == 10) {
                                String s = input.readString();
                                if (!this.name_.isModifiable()) {
                                    this.name_ = GeneratedMessageLite.mutableCopy(this.name_);
                                }
                                this.name_.add(s);
                            } else if (tag == 16) {
                                this.bitField0_ |= 1;
                                this.userId_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 2;
                                this.isPrimary_ = input.readBool();
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
                        synchronized (ServiceProto.class) {
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

        public static ServiceProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ServiceProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public boolean hasCaption() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public String getCaption() {
        return this.caption_;
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public ByteString getCaptionBytes() {
        return ByteString.copyFromUtf8(this.caption_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCaption(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.caption_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCaption() {
        this.bitField0_ &= -2;
        this.caption_ = getDefaultInstance().getCaption();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCaptionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.caption_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public List<ServiceProto> getApprovedList() {
        return this.approved_;
    }

    public List<? extends ServiceProtoOrBuilder> getApprovedOrBuilderList() {
        return this.approved_;
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public int getApprovedCount() {
        return this.approved_.size();
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public ServiceProto getApproved(int index) {
        return this.approved_.get(index);
    }

    public ServiceProtoOrBuilder getApprovedOrBuilder(int index) {
        return this.approved_.get(index);
    }

    private void ensureApprovedIsMutable() {
        if (!this.approved_.isModifiable()) {
            this.approved_ = GeneratedMessageLite.mutableCopy(this.approved_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApproved(int index, ServiceProto value) {
        if (value != null) {
            ensureApprovedIsMutable();
            this.approved_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApproved(int index, ServiceProto.Builder builderForValue) {
        ensureApprovedIsMutable();
        this.approved_.set(index, (ServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApproved(ServiceProto value) {
        if (value != null) {
            ensureApprovedIsMutable();
            this.approved_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApproved(int index, ServiceProto value) {
        if (value != null) {
            ensureApprovedIsMutable();
            this.approved_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApproved(ServiceProto.Builder builderForValue) {
        ensureApprovedIsMutable();
        this.approved_.add((ServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApproved(int index, ServiceProto.Builder builderForValue) {
        ensureApprovedIsMutable();
        this.approved_.add(index, (ServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllApproved(Iterable<? extends ServiceProto> values) {
        ensureApprovedIsMutable();
        AbstractMessageLite.addAll(values, this.approved_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearApproved() {
        this.approved_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeApproved(int index) {
        ensureApprovedIsMutable();
        this.approved_.remove(index);
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public List<ComponentNameProto> getEnabledList() {
        return this.enabled_;
    }

    public List<? extends ComponentNameProtoOrBuilder> getEnabledOrBuilderList() {
        return this.enabled_;
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public int getEnabledCount() {
        return this.enabled_.size();
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public ComponentNameProto getEnabled(int index) {
        return this.enabled_.get(index);
    }

    public ComponentNameProtoOrBuilder getEnabledOrBuilder(int index) {
        return this.enabled_.get(index);
    }

    private void ensureEnabledIsMutable() {
        if (!this.enabled_.isModifiable()) {
            this.enabled_ = GeneratedMessageLite.mutableCopy(this.enabled_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(int index, ComponentNameProto value) {
        if (value != null) {
            ensureEnabledIsMutable();
            this.enabled_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(int index, ComponentNameProto.Builder builderForValue) {
        ensureEnabledIsMutable();
        this.enabled_.set(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabled(ComponentNameProto value) {
        if (value != null) {
            ensureEnabledIsMutable();
            this.enabled_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabled(int index, ComponentNameProto value) {
        if (value != null) {
            ensureEnabledIsMutable();
            this.enabled_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabled(ComponentNameProto.Builder builderForValue) {
        ensureEnabledIsMutable();
        this.enabled_.add((ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEnabled(int index, ComponentNameProto.Builder builderForValue) {
        ensureEnabledIsMutable();
        this.enabled_.add(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEnabled(Iterable<? extends ComponentNameProto> values) {
        ensureEnabledIsMutable();
        AbstractMessageLite.addAll(values, this.enabled_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnabled() {
        this.enabled_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEnabled(int index) {
        ensureEnabledIsMutable();
        this.enabled_.remove(index);
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public List<ManagedServiceInfoProto> getLiveServicesList() {
        return this.liveServices_;
    }

    public List<? extends ManagedServiceInfoProtoOrBuilder> getLiveServicesOrBuilderList() {
        return this.liveServices_;
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public int getLiveServicesCount() {
        return this.liveServices_.size();
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public ManagedServiceInfoProto getLiveServices(int index) {
        return this.liveServices_.get(index);
    }

    public ManagedServiceInfoProtoOrBuilder getLiveServicesOrBuilder(int index) {
        return this.liveServices_.get(index);
    }

    private void ensureLiveServicesIsMutable() {
        if (!this.liveServices_.isModifiable()) {
            this.liveServices_ = GeneratedMessageLite.mutableCopy(this.liveServices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLiveServices(int index, ManagedServiceInfoProto value) {
        if (value != null) {
            ensureLiveServicesIsMutable();
            this.liveServices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLiveServices(int index, ManagedServiceInfoProto.Builder builderForValue) {
        ensureLiveServicesIsMutable();
        this.liveServices_.set(index, (ManagedServiceInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLiveServices(ManagedServiceInfoProto value) {
        if (value != null) {
            ensureLiveServicesIsMutable();
            this.liveServices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLiveServices(int index, ManagedServiceInfoProto value) {
        if (value != null) {
            ensureLiveServicesIsMutable();
            this.liveServices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLiveServices(ManagedServiceInfoProto.Builder builderForValue) {
        ensureLiveServicesIsMutable();
        this.liveServices_.add((ManagedServiceInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLiveServices(int index, ManagedServiceInfoProto.Builder builderForValue) {
        ensureLiveServicesIsMutable();
        this.liveServices_.add(index, (ManagedServiceInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllLiveServices(Iterable<? extends ManagedServiceInfoProto> values) {
        ensureLiveServicesIsMutable();
        AbstractMessageLite.addAll(values, this.liveServices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLiveServices() {
        this.liveServices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeLiveServices(int index) {
        ensureLiveServicesIsMutable();
        this.liveServices_.remove(index);
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public List<ComponentNameProto> getSnoozedList() {
        return this.snoozed_;
    }

    public List<? extends ComponentNameProtoOrBuilder> getSnoozedOrBuilderList() {
        return this.snoozed_;
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public int getSnoozedCount() {
        return this.snoozed_.size();
    }

    @Override // android.service.notification.ManagedServicesProtoOrBuilder
    public ComponentNameProto getSnoozed(int index) {
        return this.snoozed_.get(index);
    }

    public ComponentNameProtoOrBuilder getSnoozedOrBuilder(int index) {
        return this.snoozed_.get(index);
    }

    private void ensureSnoozedIsMutable() {
        if (!this.snoozed_.isModifiable()) {
            this.snoozed_ = GeneratedMessageLite.mutableCopy(this.snoozed_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSnoozed(int index, ComponentNameProto value) {
        if (value != null) {
            ensureSnoozedIsMutable();
            this.snoozed_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSnoozed(int index, ComponentNameProto.Builder builderForValue) {
        ensureSnoozedIsMutable();
        this.snoozed_.set(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSnoozed(ComponentNameProto value) {
        if (value != null) {
            ensureSnoozedIsMutable();
            this.snoozed_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSnoozed(int index, ComponentNameProto value) {
        if (value != null) {
            ensureSnoozedIsMutable();
            this.snoozed_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSnoozed(ComponentNameProto.Builder builderForValue) {
        ensureSnoozedIsMutable();
        this.snoozed_.add((ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSnoozed(int index, ComponentNameProto.Builder builderForValue) {
        ensureSnoozedIsMutable();
        this.snoozed_.add(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSnoozed(Iterable<? extends ComponentNameProto> values) {
        ensureSnoozedIsMutable();
        AbstractMessageLite.addAll(values, this.snoozed_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSnoozed() {
        this.snoozed_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSnoozed(int index) {
        ensureSnoozedIsMutable();
        this.snoozed_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getCaption());
        }
        for (int i = 0; i < this.approved_.size(); i++) {
            output.writeMessage(2, this.approved_.get(i));
        }
        for (int i2 = 0; i2 < this.enabled_.size(); i2++) {
            output.writeMessage(3, this.enabled_.get(i2));
        }
        for (int i3 = 0; i3 < this.liveServices_.size(); i3++) {
            output.writeMessage(4, this.liveServices_.get(i3));
        }
        for (int i4 = 0; i4 < this.snoozed_.size(); i4++) {
            output.writeMessage(5, this.snoozed_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getCaption());
        }
        for (int i = 0; i < this.approved_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.approved_.get(i));
        }
        for (int i2 = 0; i2 < this.enabled_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.enabled_.get(i2));
        }
        for (int i3 = 0; i3 < this.liveServices_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.liveServices_.get(i3));
        }
        for (int i4 = 0; i4 < this.snoozed_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.snoozed_.get(i4));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ManagedServicesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ManagedServicesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ManagedServicesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ManagedServicesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ManagedServicesProto parseFrom(InputStream input) throws IOException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ManagedServicesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ManagedServicesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ManagedServicesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ManagedServicesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ManagedServicesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ManagedServicesProto parseFrom(CodedInputStream input) throws IOException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ManagedServicesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ManagedServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ManagedServicesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ManagedServicesProto, Builder> implements ManagedServicesProtoOrBuilder {
        private Builder() {
            super(ManagedServicesProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public boolean hasCaption() {
            return ((ManagedServicesProto) this.instance).hasCaption();
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public String getCaption() {
            return ((ManagedServicesProto) this.instance).getCaption();
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public ByteString getCaptionBytes() {
            return ((ManagedServicesProto) this.instance).getCaptionBytes();
        }

        public Builder setCaption(String value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setCaption(value);
            return this;
        }

        public Builder clearCaption() {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).clearCaption();
            return this;
        }

        public Builder setCaptionBytes(ByteString value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setCaptionBytes(value);
            return this;
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public List<ServiceProto> getApprovedList() {
            return Collections.unmodifiableList(((ManagedServicesProto) this.instance).getApprovedList());
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public int getApprovedCount() {
            return ((ManagedServicesProto) this.instance).getApprovedCount();
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public ServiceProto getApproved(int index) {
            return ((ManagedServicesProto) this.instance).getApproved(index);
        }

        public Builder setApproved(int index, ServiceProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setApproved((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder setApproved(int index, ServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setApproved((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addApproved(ServiceProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addApproved((ManagedServicesProto) value);
            return this;
        }

        public Builder addApproved(int index, ServiceProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addApproved((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder addApproved(ServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addApproved((ManagedServicesProto) builderForValue);
            return this;
        }

        public Builder addApproved(int index, ServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addApproved((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllApproved(Iterable<? extends ServiceProto> values) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addAllApproved(values);
            return this;
        }

        public Builder clearApproved() {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).clearApproved();
            return this;
        }

        public Builder removeApproved(int index) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).removeApproved(index);
            return this;
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public List<ComponentNameProto> getEnabledList() {
            return Collections.unmodifiableList(((ManagedServicesProto) this.instance).getEnabledList());
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public int getEnabledCount() {
            return ((ManagedServicesProto) this.instance).getEnabledCount();
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public ComponentNameProto getEnabled(int index) {
            return ((ManagedServicesProto) this.instance).getEnabled(index);
        }

        public Builder setEnabled(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setEnabled((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder setEnabled(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setEnabled((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEnabled(ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addEnabled((ManagedServicesProto) value);
            return this;
        }

        public Builder addEnabled(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addEnabled((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder addEnabled(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addEnabled((ManagedServicesProto) builderForValue);
            return this;
        }

        public Builder addEnabled(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addEnabled((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEnabled(Iterable<? extends ComponentNameProto> values) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addAllEnabled(values);
            return this;
        }

        public Builder clearEnabled() {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).clearEnabled();
            return this;
        }

        public Builder removeEnabled(int index) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).removeEnabled(index);
            return this;
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public List<ManagedServiceInfoProto> getLiveServicesList() {
            return Collections.unmodifiableList(((ManagedServicesProto) this.instance).getLiveServicesList());
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public int getLiveServicesCount() {
            return ((ManagedServicesProto) this.instance).getLiveServicesCount();
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public ManagedServiceInfoProto getLiveServices(int index) {
            return ((ManagedServicesProto) this.instance).getLiveServices(index);
        }

        public Builder setLiveServices(int index, ManagedServiceInfoProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setLiveServices((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder setLiveServices(int index, ManagedServiceInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setLiveServices((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addLiveServices(ManagedServiceInfoProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addLiveServices((ManagedServicesProto) value);
            return this;
        }

        public Builder addLiveServices(int index, ManagedServiceInfoProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addLiveServices((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder addLiveServices(ManagedServiceInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addLiveServices((ManagedServicesProto) builderForValue);
            return this;
        }

        public Builder addLiveServices(int index, ManagedServiceInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addLiveServices((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllLiveServices(Iterable<? extends ManagedServiceInfoProto> values) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addAllLiveServices(values);
            return this;
        }

        public Builder clearLiveServices() {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).clearLiveServices();
            return this;
        }

        public Builder removeLiveServices(int index) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).removeLiveServices(index);
            return this;
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public List<ComponentNameProto> getSnoozedList() {
            return Collections.unmodifiableList(((ManagedServicesProto) this.instance).getSnoozedList());
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public int getSnoozedCount() {
            return ((ManagedServicesProto) this.instance).getSnoozedCount();
        }

        @Override // android.service.notification.ManagedServicesProtoOrBuilder
        public ComponentNameProto getSnoozed(int index) {
            return ((ManagedServicesProto) this.instance).getSnoozed(index);
        }

        public Builder setSnoozed(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setSnoozed((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder setSnoozed(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).setSnoozed((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSnoozed(ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addSnoozed((ManagedServicesProto) value);
            return this;
        }

        public Builder addSnoozed(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addSnoozed((ManagedServicesProto) index, (int) value);
            return this;
        }

        public Builder addSnoozed(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addSnoozed((ManagedServicesProto) builderForValue);
            return this;
        }

        public Builder addSnoozed(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addSnoozed((ManagedServicesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSnoozed(Iterable<? extends ComponentNameProto> values) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).addAllSnoozed(values);
            return this;
        }

        public Builder clearSnoozed() {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).clearSnoozed();
            return this;
        }

        public Builder removeSnoozed(int index) {
            copyOnWrite();
            ((ManagedServicesProto) this.instance).removeSnoozed(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ManagedServicesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.approved_.makeImmutable();
                this.enabled_.makeImmutable();
                this.liveServices_.makeImmutable();
                this.snoozed_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ManagedServicesProto other = (ManagedServicesProto) arg1;
                this.caption_ = visitor.visitString(hasCaption(), this.caption_, other.hasCaption(), other.caption_);
                this.approved_ = visitor.visitList(this.approved_, other.approved_);
                this.enabled_ = visitor.visitList(this.enabled_, other.enabled_);
                this.liveServices_ = visitor.visitList(this.liveServices_, other.liveServices_);
                this.snoozed_ = visitor.visitList(this.snoozed_, other.snoozed_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.caption_ = s;
                        } else if (tag == 18) {
                            if (!this.approved_.isModifiable()) {
                                this.approved_ = GeneratedMessageLite.mutableCopy(this.approved_);
                            }
                            this.approved_.add((ServiceProto) input.readMessage(ServiceProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.enabled_.isModifiable()) {
                                this.enabled_ = GeneratedMessageLite.mutableCopy(this.enabled_);
                            }
                            this.enabled_.add((ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.liveServices_.isModifiable()) {
                                this.liveServices_ = GeneratedMessageLite.mutableCopy(this.liveServices_);
                            }
                            this.liveServices_.add((ManagedServiceInfoProto) input.readMessage(ManagedServiceInfoProto.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            if (!this.snoozed_.isModifiable()) {
                                this.snoozed_ = GeneratedMessageLite.mutableCopy(this.snoozed_);
                            }
                            this.snoozed_.add((ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry));
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
                    synchronized (ManagedServicesProto.class) {
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

    public static ManagedServicesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ManagedServicesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
