package android.app;

import android.app.NotificationChannelProto;
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

public final class NotificationChannelGroupProto extends GeneratedMessageLite<NotificationChannelGroupProto, Builder> implements NotificationChannelGroupProtoOrBuilder {
    public static final int ALLOW_APP_OVERLAY_FIELD_NUMBER = 6;
    public static final int CHANNELS_FIELD_NUMBER = 5;
    private static final NotificationChannelGroupProto DEFAULT_INSTANCE = new NotificationChannelGroupProto();
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int IS_BLOCKED_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<NotificationChannelGroupProto> PARSER;
    private boolean allowAppOverlay_ = false;
    private int bitField0_;
    private Internal.ProtobufList<NotificationChannelProto> channels_ = emptyProtobufList();
    private String description_ = "";
    private String id_ = "";
    private boolean isBlocked_ = false;
    private String name_ = "";

    private NotificationChannelGroupProto() {
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -2;
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -3;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean hasDescription() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescription(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.description_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDescription() {
        this.bitField0_ &= -5;
        this.description_ = getDefaultInstance().getDescription();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescriptionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.description_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean hasIsBlocked() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean getIsBlocked() {
        return this.isBlocked_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBlocked(boolean value) {
        this.bitField0_ |= 8;
        this.isBlocked_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBlocked() {
        this.bitField0_ &= -9;
        this.isBlocked_ = false;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public List<NotificationChannelProto> getChannelsList() {
        return this.channels_;
    }

    public List<? extends NotificationChannelProtoOrBuilder> getChannelsOrBuilderList() {
        return this.channels_;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public int getChannelsCount() {
        return this.channels_.size();
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public NotificationChannelProto getChannels(int index) {
        return this.channels_.get(index);
    }

    public NotificationChannelProtoOrBuilder getChannelsOrBuilder(int index) {
        return this.channels_.get(index);
    }

    private void ensureChannelsIsMutable() {
        if (!this.channels_.isModifiable()) {
            this.channels_ = GeneratedMessageLite.mutableCopy(this.channels_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChannels(int index, NotificationChannelProto value) {
        if (value != null) {
            ensureChannelsIsMutable();
            this.channels_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChannels(int index, NotificationChannelProto.Builder builderForValue) {
        ensureChannelsIsMutable();
        this.channels_.set(index, (NotificationChannelProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChannels(NotificationChannelProto value) {
        if (value != null) {
            ensureChannelsIsMutable();
            this.channels_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChannels(int index, NotificationChannelProto value) {
        if (value != null) {
            ensureChannelsIsMutable();
            this.channels_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChannels(NotificationChannelProto.Builder builderForValue) {
        ensureChannelsIsMutable();
        this.channels_.add((NotificationChannelProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChannels(int index, NotificationChannelProto.Builder builderForValue) {
        ensureChannelsIsMutable();
        this.channels_.add(index, (NotificationChannelProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllChannels(Iterable<? extends NotificationChannelProto> values) {
        ensureChannelsIsMutable();
        AbstractMessageLite.addAll(values, this.channels_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChannels() {
        this.channels_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeChannels(int index) {
        ensureChannelsIsMutable();
        this.channels_.remove(index);
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean hasAllowAppOverlay() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.app.NotificationChannelGroupProtoOrBuilder
    public boolean getAllowAppOverlay() {
        return this.allowAppOverlay_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowAppOverlay(boolean value) {
        this.bitField0_ |= 16;
        this.allowAppOverlay_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowAppOverlay() {
        this.bitField0_ &= -17;
        this.allowAppOverlay_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isBlocked_);
        }
        for (int i = 0; i < this.channels_.size(); i++) {
            output.writeMessage(5, this.channels_.get(i));
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(6, this.allowAppOverlay_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isBlocked_);
        }
        for (int i = 0; i < this.channels_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.channels_.get(i));
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(6, this.allowAppOverlay_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NotificationChannelGroupProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationChannelGroupProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationChannelGroupProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationChannelGroupProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationChannelGroupProto parseFrom(InputStream input) throws IOException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationChannelGroupProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationChannelGroupProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NotificationChannelGroupProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationChannelGroupProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationChannelGroupProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationChannelGroupProto parseFrom(CodedInputStream input) throws IOException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationChannelGroupProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationChannelGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NotificationChannelGroupProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NotificationChannelGroupProto, Builder> implements NotificationChannelGroupProtoOrBuilder {
        private Builder() {
            super(NotificationChannelGroupProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean hasId() {
            return ((NotificationChannelGroupProto) this.instance).hasId();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public String getId() {
            return ((NotificationChannelGroupProto) this.instance).getId();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public ByteString getIdBytes() {
            return ((NotificationChannelGroupProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean hasName() {
            return ((NotificationChannelGroupProto) this.instance).hasName();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public String getName() {
            return ((NotificationChannelGroupProto) this.instance).getName();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public ByteString getNameBytes() {
            return ((NotificationChannelGroupProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean hasDescription() {
            return ((NotificationChannelGroupProto) this.instance).hasDescription();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public String getDescription() {
            return ((NotificationChannelGroupProto) this.instance).getDescription();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public ByteString getDescriptionBytes() {
            return ((NotificationChannelGroupProto) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setDescription(value);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setDescriptionBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean hasIsBlocked() {
            return ((NotificationChannelGroupProto) this.instance).hasIsBlocked();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean getIsBlocked() {
            return ((NotificationChannelGroupProto) this.instance).getIsBlocked();
        }

        public Builder setIsBlocked(boolean value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setIsBlocked(value);
            return this;
        }

        public Builder clearIsBlocked() {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).clearIsBlocked();
            return this;
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public List<NotificationChannelProto> getChannelsList() {
            return Collections.unmodifiableList(((NotificationChannelGroupProto) this.instance).getChannelsList());
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public int getChannelsCount() {
            return ((NotificationChannelGroupProto) this.instance).getChannelsCount();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public NotificationChannelProto getChannels(int index) {
            return ((NotificationChannelGroupProto) this.instance).getChannels(index);
        }

        public Builder setChannels(int index, NotificationChannelProto value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setChannels((NotificationChannelGroupProto) index, (int) value);
            return this;
        }

        public Builder setChannels(int index, NotificationChannelProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setChannels((NotificationChannelGroupProto) index, (int) builderForValue);
            return this;
        }

        public Builder addChannels(NotificationChannelProto value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).addChannels((NotificationChannelGroupProto) value);
            return this;
        }

        public Builder addChannels(int index, NotificationChannelProto value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).addChannels((NotificationChannelGroupProto) index, (int) value);
            return this;
        }

        public Builder addChannels(NotificationChannelProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).addChannels((NotificationChannelGroupProto) builderForValue);
            return this;
        }

        public Builder addChannels(int index, NotificationChannelProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).addChannels((NotificationChannelGroupProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllChannels(Iterable<? extends NotificationChannelProto> values) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).addAllChannels(values);
            return this;
        }

        public Builder clearChannels() {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).clearChannels();
            return this;
        }

        public Builder removeChannels(int index) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).removeChannels(index);
            return this;
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean hasAllowAppOverlay() {
            return ((NotificationChannelGroupProto) this.instance).hasAllowAppOverlay();
        }

        @Override // android.app.NotificationChannelGroupProtoOrBuilder
        public boolean getAllowAppOverlay() {
            return ((NotificationChannelGroupProto) this.instance).getAllowAppOverlay();
        }

        public Builder setAllowAppOverlay(boolean value) {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).setAllowAppOverlay(value);
            return this;
        }

        public Builder clearAllowAppOverlay() {
            copyOnWrite();
            ((NotificationChannelGroupProto) this.instance).clearAllowAppOverlay();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NotificationChannelGroupProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.channels_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NotificationChannelGroupProto other = (NotificationChannelGroupProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.description_ = visitor.visitString(hasDescription(), this.description_, other.hasDescription(), other.description_);
                this.isBlocked_ = visitor.visitBoolean(hasIsBlocked(), this.isBlocked_, other.hasIsBlocked(), other.isBlocked_);
                this.channels_ = visitor.visitList(this.channels_, other.channels_);
                this.allowAppOverlay_ = visitor.visitBoolean(hasAllowAppOverlay(), this.allowAppOverlay_, other.hasAllowAppOverlay(), other.allowAppOverlay_);
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
                            this.id_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.name_ = s2;
                        } else if (tag == 26) {
                            String s3 = input.readString();
                            this.bitField0_ |= 4;
                            this.description_ = s3;
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.isBlocked_ = input.readBool();
                        } else if (tag == 42) {
                            if (!this.channels_.isModifiable()) {
                                this.channels_ = GeneratedMessageLite.mutableCopy(this.channels_);
                            }
                            this.channels_.add((NotificationChannelProto) input.readMessage(NotificationChannelProto.parser(), extensionRegistry));
                        } else if (tag == 48) {
                            this.bitField0_ |= 16;
                            this.allowAppOverlay_ = input.readBool();
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
                    synchronized (NotificationChannelGroupProto.class) {
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

    public static NotificationChannelGroupProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NotificationChannelGroupProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
