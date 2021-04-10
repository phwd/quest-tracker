package android.app;

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

public final class NotificationProto extends GeneratedMessageLite<NotificationProto, Builder> implements NotificationProtoOrBuilder {
    public static final int ACTION_LENGTH_FIELD_NUMBER = 8;
    public static final int CATEGORY_FIELD_NUMBER = 5;
    public static final int CHANNEL_ID_FIELD_NUMBER = 1;
    public static final int COLOR_FIELD_NUMBER = 4;
    private static final NotificationProto DEFAULT_INSTANCE = new NotificationProto();
    public static final int FLAGS_FIELD_NUMBER = 3;
    public static final int GROUP_KEY_FIELD_NUMBER = 6;
    public static final int HAS_TICKER_TEXT_FIELD_NUMBER = 2;
    private static volatile Parser<NotificationProto> PARSER = null;
    public static final int PUBLIC_VERSION_FIELD_NUMBER = 10;
    public static final int SORT_KEY_FIELD_NUMBER = 7;
    public static final int VISIBILITY_FIELD_NUMBER = 9;
    private int actionLength_ = 0;
    private int bitField0_;
    private String category_ = "";
    private String channelId_ = "";
    private int color_ = 0;
    private int flags_ = 0;
    private String groupKey_ = "";
    private boolean hasTickerText_ = false;
    private NotificationProto publicVersion_;
    private String sortKey_ = "";
    private int visibility_ = -1;

    private NotificationProto() {
    }

    public enum Visibility implements Internal.EnumLite {
        VISIBILITY_SECRET(-1),
        VISIBILITY_PRIVATE(0),
        VISIBILITY_PUBLIC(1);
        
        public static final int VISIBILITY_PRIVATE_VALUE = 0;
        public static final int VISIBILITY_PUBLIC_VALUE = 1;
        public static final int VISIBILITY_SECRET_VALUE = -1;
        private static final Internal.EnumLiteMap<Visibility> internalValueMap = new Internal.EnumLiteMap<Visibility>() {
            /* class android.app.NotificationProto.Visibility.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Visibility findValueByNumber(int number) {
                return Visibility.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Visibility valueOf(int value2) {
            return forNumber(value2);
        }

        public static Visibility forNumber(int value2) {
            if (value2 == -1) {
                return VISIBILITY_SECRET;
            }
            if (value2 == 0) {
                return VISIBILITY_PRIVATE;
            }
            if (value2 != 1) {
                return null;
            }
            return VISIBILITY_PUBLIC;
        }

        public static Internal.EnumLiteMap<Visibility> internalGetValueMap() {
            return internalValueMap;
        }

        private Visibility(int value2) {
            this.value = value2;
        }
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasChannelId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public String getChannelId() {
        return this.channelId_;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public ByteString getChannelIdBytes() {
        return ByteString.copyFromUtf8(this.channelId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChannelId(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.channelId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChannelId() {
        this.bitField0_ &= -2;
        this.channelId_ = getDefaultInstance().getChannelId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChannelIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.channelId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasHasTickerText() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean getHasTickerText() {
        return this.hasTickerText_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasTickerText(boolean value) {
        this.bitField0_ |= 2;
        this.hasTickerText_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasTickerText() {
        this.bitField0_ &= -3;
        this.hasTickerText_ = false;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 4;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -5;
        this.flags_ = 0;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasColor() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public int getColor() {
        return this.color_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setColor(int value) {
        this.bitField0_ |= 8;
        this.color_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearColor() {
        this.bitField0_ &= -9;
        this.color_ = 0;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasCategory() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public String getCategory() {
        return this.category_;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public ByteString getCategoryBytes() {
        return ByteString.copyFromUtf8(this.category_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCategory(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.category_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCategory() {
        this.bitField0_ &= -17;
        this.category_ = getDefaultInstance().getCategory();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCategoryBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.category_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasGroupKey() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public String getGroupKey() {
        return this.groupKey_;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public ByteString getGroupKeyBytes() {
        return ByteString.copyFromUtf8(this.groupKey_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGroupKey(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.groupKey_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGroupKey() {
        this.bitField0_ &= -33;
        this.groupKey_ = getDefaultInstance().getGroupKey();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGroupKeyBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.groupKey_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasSortKey() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public String getSortKey() {
        return this.sortKey_;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public ByteString getSortKeyBytes() {
        return ByteString.copyFromUtf8(this.sortKey_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSortKey(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.sortKey_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSortKey() {
        this.bitField0_ &= -65;
        this.sortKey_ = getDefaultInstance().getSortKey();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSortKeyBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.sortKey_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasActionLength() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public int getActionLength() {
        return this.actionLength_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActionLength(int value) {
        this.bitField0_ |= 128;
        this.actionLength_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActionLength() {
        this.bitField0_ &= -129;
        this.actionLength_ = 0;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasVisibility() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public Visibility getVisibility() {
        Visibility result = Visibility.forNumber(this.visibility_);
        return result == null ? Visibility.VISIBILITY_SECRET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisibility(Visibility value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.visibility_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisibility() {
        this.bitField0_ &= -257;
        this.visibility_ = -1;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public boolean hasPublicVersion() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.app.NotificationProtoOrBuilder
    public NotificationProto getPublicVersion() {
        NotificationProto notificationProto = this.publicVersion_;
        return notificationProto == null ? getDefaultInstance() : notificationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPublicVersion(NotificationProto value) {
        if (value != null) {
            this.publicVersion_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPublicVersion(Builder builderForValue) {
        this.publicVersion_ = (NotificationProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePublicVersion(NotificationProto value) {
        NotificationProto notificationProto = this.publicVersion_;
        if (notificationProto == null || notificationProto == getDefaultInstance()) {
            this.publicVersion_ = value;
        } else {
            this.publicVersion_ = (NotificationProto) ((Builder) newBuilder(this.publicVersion_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPublicVersion() {
        this.publicVersion_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getChannelId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.hasTickerText_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.flags_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.color_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getCategory());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getGroupKey());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getSortKey());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.actionLength_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeEnum(9, this.visibility_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getPublicVersion());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getChannelId());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.hasTickerText_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.flags_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.color_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getCategory());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getGroupKey());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getSortKey());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.actionLength_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeEnumSize(9, this.visibility_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getPublicVersion());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NotificationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationProto parseFrom(InputStream input) throws IOException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NotificationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationProto parseFrom(CodedInputStream input) throws IOException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NotificationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NotificationProto, Builder> implements NotificationProtoOrBuilder {
        private Builder() {
            super(NotificationProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasChannelId() {
            return ((NotificationProto) this.instance).hasChannelId();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public String getChannelId() {
            return ((NotificationProto) this.instance).getChannelId();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public ByteString getChannelIdBytes() {
            return ((NotificationProto) this.instance).getChannelIdBytes();
        }

        public Builder setChannelId(String value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setChannelId(value);
            return this;
        }

        public Builder clearChannelId() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearChannelId();
            return this;
        }

        public Builder setChannelIdBytes(ByteString value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setChannelIdBytes(value);
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasHasTickerText() {
            return ((NotificationProto) this.instance).hasHasTickerText();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean getHasTickerText() {
            return ((NotificationProto) this.instance).getHasTickerText();
        }

        public Builder setHasTickerText(boolean value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setHasTickerText(value);
            return this;
        }

        public Builder clearHasTickerText() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearHasTickerText();
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasFlags() {
            return ((NotificationProto) this.instance).hasFlags();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public int getFlags() {
            return ((NotificationProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearFlags();
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasColor() {
            return ((NotificationProto) this.instance).hasColor();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public int getColor() {
            return ((NotificationProto) this.instance).getColor();
        }

        public Builder setColor(int value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setColor(value);
            return this;
        }

        public Builder clearColor() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearColor();
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasCategory() {
            return ((NotificationProto) this.instance).hasCategory();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public String getCategory() {
            return ((NotificationProto) this.instance).getCategory();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public ByteString getCategoryBytes() {
            return ((NotificationProto) this.instance).getCategoryBytes();
        }

        public Builder setCategory(String value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setCategory(value);
            return this;
        }

        public Builder clearCategory() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearCategory();
            return this;
        }

        public Builder setCategoryBytes(ByteString value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setCategoryBytes(value);
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasGroupKey() {
            return ((NotificationProto) this.instance).hasGroupKey();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public String getGroupKey() {
            return ((NotificationProto) this.instance).getGroupKey();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public ByteString getGroupKeyBytes() {
            return ((NotificationProto) this.instance).getGroupKeyBytes();
        }

        public Builder setGroupKey(String value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setGroupKey(value);
            return this;
        }

        public Builder clearGroupKey() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearGroupKey();
            return this;
        }

        public Builder setGroupKeyBytes(ByteString value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setGroupKeyBytes(value);
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasSortKey() {
            return ((NotificationProto) this.instance).hasSortKey();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public String getSortKey() {
            return ((NotificationProto) this.instance).getSortKey();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public ByteString getSortKeyBytes() {
            return ((NotificationProto) this.instance).getSortKeyBytes();
        }

        public Builder setSortKey(String value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setSortKey(value);
            return this;
        }

        public Builder clearSortKey() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearSortKey();
            return this;
        }

        public Builder setSortKeyBytes(ByteString value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setSortKeyBytes(value);
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasActionLength() {
            return ((NotificationProto) this.instance).hasActionLength();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public int getActionLength() {
            return ((NotificationProto) this.instance).getActionLength();
        }

        public Builder setActionLength(int value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setActionLength(value);
            return this;
        }

        public Builder clearActionLength() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearActionLength();
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasVisibility() {
            return ((NotificationProto) this.instance).hasVisibility();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public Visibility getVisibility() {
            return ((NotificationProto) this.instance).getVisibility();
        }

        public Builder setVisibility(Visibility value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setVisibility(value);
            return this;
        }

        public Builder clearVisibility() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearVisibility();
            return this;
        }

        @Override // android.app.NotificationProtoOrBuilder
        public boolean hasPublicVersion() {
            return ((NotificationProto) this.instance).hasPublicVersion();
        }

        @Override // android.app.NotificationProtoOrBuilder
        public NotificationProto getPublicVersion() {
            return ((NotificationProto) this.instance).getPublicVersion();
        }

        public Builder setPublicVersion(NotificationProto value) {
            copyOnWrite();
            ((NotificationProto) this.instance).setPublicVersion(value);
            return this;
        }

        public Builder setPublicVersion(Builder builderForValue) {
            copyOnWrite();
            ((NotificationProto) this.instance).setPublicVersion((NotificationProto) builderForValue);
            return this;
        }

        public Builder mergePublicVersion(NotificationProto value) {
            copyOnWrite();
            ((NotificationProto) this.instance).mergePublicVersion(value);
            return this;
        }

        public Builder clearPublicVersion() {
            copyOnWrite();
            ((NotificationProto) this.instance).clearPublicVersion();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NotificationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NotificationProto other = (NotificationProto) arg1;
                this.channelId_ = visitor.visitString(hasChannelId(), this.channelId_, other.hasChannelId(), other.channelId_);
                this.hasTickerText_ = visitor.visitBoolean(hasHasTickerText(), this.hasTickerText_, other.hasHasTickerText(), other.hasTickerText_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.color_ = visitor.visitInt(hasColor(), this.color_, other.hasColor(), other.color_);
                this.category_ = visitor.visitString(hasCategory(), this.category_, other.hasCategory(), other.category_);
                this.groupKey_ = visitor.visitString(hasGroupKey(), this.groupKey_, other.hasGroupKey(), other.groupKey_);
                this.sortKey_ = visitor.visitString(hasSortKey(), this.sortKey_, other.hasSortKey(), other.sortKey_);
                this.actionLength_ = visitor.visitInt(hasActionLength(), this.actionLength_, other.hasActionLength(), other.actionLength_);
                this.visibility_ = visitor.visitInt(hasVisibility(), this.visibility_, other.hasVisibility(), other.visibility_);
                this.publicVersion_ = (NotificationProto) visitor.visitMessage(this.publicVersion_, other.publicVersion_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.channelId_ = s;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.hasTickerText_ = input.readBool();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.flags_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.color_ = input.readInt32();
                                break;
                            case 42:
                                String s2 = input.readString();
                                this.bitField0_ |= 16;
                                this.category_ = s2;
                                break;
                            case 50:
                                String s3 = input.readString();
                                this.bitField0_ |= 32;
                                this.groupKey_ = s3;
                                break;
                            case 58:
                                String s4 = input.readString();
                                this.bitField0_ |= 64;
                                this.sortKey_ = s4;
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.actionLength_ = input.readInt32();
                                break;
                            case 72:
                                int rawValue = input.readEnum();
                                if (Visibility.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 256;
                                    this.visibility_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(9, rawValue);
                                    break;
                                }
                            case 82:
                                Builder subBuilder = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder = (Builder) this.publicVersion_.toBuilder();
                                }
                                this.publicVersion_ = (NotificationProto) input.readMessage(parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.publicVersion_);
                                    this.publicVersion_ = (NotificationProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (NotificationProto.class) {
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

    public static NotificationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NotificationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
