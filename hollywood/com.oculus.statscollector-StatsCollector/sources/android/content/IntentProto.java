package android.content;

import android.content.ComponentNameProto;
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

public final class IntentProto extends GeneratedMessageLite<IntentProto, Builder> implements IntentProtoOrBuilder {
    public static final int ACTION_FIELD_NUMBER = 1;
    public static final int CATEGORIES_FIELD_NUMBER = 2;
    public static final int CLIP_DATA_FIELD_NUMBER = 9;
    public static final int COMPONENT_FIELD_NUMBER = 7;
    public static final int CONTENT_USER_HINT_FIELD_NUMBER = 11;
    public static final int DATA_FIELD_NUMBER = 3;
    private static final IntentProto DEFAULT_INSTANCE = new IntentProto();
    public static final int EXTRAS_FIELD_NUMBER = 10;
    public static final int FLAG_FIELD_NUMBER = 5;
    public static final int IDENTIFIER_FIELD_NUMBER = 13;
    public static final int PACKAGE_FIELD_NUMBER = 6;
    private static volatile Parser<IntentProto> PARSER = null;
    public static final int SELECTOR_FIELD_NUMBER = 12;
    public static final int SOURCE_BOUNDS_FIELD_NUMBER = 8;
    public static final int TYPE_FIELD_NUMBER = 4;
    private String action_ = "";
    private int bitField0_;
    private Internal.ProtobufList<String> categories_ = GeneratedMessageLite.emptyProtobufList();
    private String clipData_ = "";
    private ComponentNameProto component_;
    private int contentUserHint_ = 0;
    private String data_ = "";
    private String extras_ = "";
    private String flag_ = "";
    private String identifier_ = "";
    private String package_ = "";
    private String selector_ = "";
    private String sourceBounds_ = "";
    private String type_ = "";

    private IntentProto() {
    }

    public enum DockState implements Internal.EnumLite {
        DOCK_STATE_UNDOCKED(0),
        DOCK_STATE_DESK(1),
        DOCK_STATE_CAR(2),
        DOCK_STATE_LE_DESK(3),
        DOCK_STATE_HE_DESK(4);
        
        public static final int DOCK_STATE_CAR_VALUE = 2;
        public static final int DOCK_STATE_DESK_VALUE = 1;
        public static final int DOCK_STATE_HE_DESK_VALUE = 4;
        public static final int DOCK_STATE_LE_DESK_VALUE = 3;
        public static final int DOCK_STATE_UNDOCKED_VALUE = 0;
        private static final Internal.EnumLiteMap<DockState> internalValueMap = new Internal.EnumLiteMap<DockState>() {
            /* class android.content.IntentProto.DockState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DockState findValueByNumber(int number) {
                return DockState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DockState valueOf(int value2) {
            return forNumber(value2);
        }

        public static DockState forNumber(int value2) {
            if (value2 == 0) {
                return DOCK_STATE_UNDOCKED;
            }
            if (value2 == 1) {
                return DOCK_STATE_DESK;
            }
            if (value2 == 2) {
                return DOCK_STATE_CAR;
            }
            if (value2 == 3) {
                return DOCK_STATE_LE_DESK;
            }
            if (value2 != 4) {
                return null;
            }
            return DOCK_STATE_HE_DESK;
        }

        public static Internal.EnumLiteMap<DockState> internalGetValueMap() {
            return internalValueMap;
        }

        private DockState(int value2) {
            this.value = value2;
        }
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasAction() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getAction() {
        return this.action_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getActionBytes() {
        return ByteString.copyFromUtf8(this.action_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAction(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.action_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAction() {
        this.bitField0_ &= -2;
        this.action_ = getDefaultInstance().getAction();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.action_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public List<String> getCategoriesList() {
        return this.categories_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public int getCategoriesCount() {
        return this.categories_.size();
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getCategories(int index) {
        return this.categories_.get(index);
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getCategoriesBytes(int index) {
        return ByteString.copyFromUtf8(this.categories_.get(index));
    }

    private void ensureCategoriesIsMutable() {
        if (!this.categories_.isModifiable()) {
            this.categories_ = GeneratedMessageLite.mutableCopy(this.categories_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCategories(int index, String value) {
        if (value != null) {
            ensureCategoriesIsMutable();
            this.categories_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCategories(String value) {
        if (value != null) {
            ensureCategoriesIsMutable();
            this.categories_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCategories(Iterable<String> values) {
        ensureCategoriesIsMutable();
        AbstractMessageLite.addAll(values, this.categories_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCategories() {
        this.categories_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCategoriesBytes(ByteString value) {
        if (value != null) {
            ensureCategoriesIsMutable();
            this.categories_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasData() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getData() {
        return this.data_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getDataBytes() {
        return ByteString.copyFromUtf8(this.data_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setData(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.data_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearData() {
        this.bitField0_ &= -3;
        this.data_ = getDefaultInstance().getData();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.data_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getType() {
        return this.type_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.type_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -5;
        this.type_ = getDefaultInstance().getType();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTypeBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.type_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasFlag() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getFlag() {
        return this.flag_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getFlagBytes() {
        return ByteString.copyFromUtf8(this.flag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlag(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.flag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlag() {
        this.bitField0_ &= -9;
        this.flag_ = getDefaultInstance().getFlag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.flag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasPackage() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getPackage() {
        return this.package_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getPackageBytes() {
        return ByteString.copyFromUtf8(this.package_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.package_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackage() {
        this.bitField0_ &= -17;
        this.package_ = getDefaultInstance().getPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.package_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasComponent() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ComponentNameProto getComponent() {
        ComponentNameProto componentNameProto = this.component_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponent(ComponentNameProto value) {
        if (value != null) {
            this.component_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponent(ComponentNameProto.Builder builderForValue) {
        this.component_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeComponent(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.component_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.component_ = value;
        } else {
            this.component_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.component_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearComponent() {
        this.component_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasSourceBounds() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getSourceBounds() {
        return this.sourceBounds_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getSourceBoundsBytes() {
        return ByteString.copyFromUtf8(this.sourceBounds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceBounds(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.sourceBounds_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceBounds() {
        this.bitField0_ &= -65;
        this.sourceBounds_ = getDefaultInstance().getSourceBounds();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceBoundsBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.sourceBounds_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasClipData() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getClipData() {
        return this.clipData_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getClipDataBytes() {
        return ByteString.copyFromUtf8(this.clipData_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClipData(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.clipData_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClipData() {
        this.bitField0_ &= -129;
        this.clipData_ = getDefaultInstance().getClipData();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClipDataBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.clipData_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasExtras() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getExtras() {
        return this.extras_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getExtrasBytes() {
        return ByteString.copyFromUtf8(this.extras_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExtras(String value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.extras_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearExtras() {
        this.bitField0_ &= -257;
        this.extras_ = getDefaultInstance().getExtras();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExtrasBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.extras_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasContentUserHint() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.content.IntentProtoOrBuilder
    public int getContentUserHint() {
        return this.contentUserHint_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentUserHint(int value) {
        this.bitField0_ |= 512;
        this.contentUserHint_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentUserHint() {
        this.bitField0_ &= -513;
        this.contentUserHint_ = 0;
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasSelector() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSelector(String value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.selector_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSelector() {
        this.bitField0_ &= -1025;
        this.selector_ = getDefaultInstance().getSelector();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSelectorBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.selector_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentProtoOrBuilder
    public boolean hasIdentifier() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.content.IntentProtoOrBuilder
    public String getIdentifier() {
        return this.identifier_;
    }

    @Override // android.content.IntentProtoOrBuilder
    public ByteString getIdentifierBytes() {
        return ByteString.copyFromUtf8(this.identifier_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentifier(String value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.identifier_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdentifier() {
        this.bitField0_ &= -2049;
        this.identifier_ = getDefaultInstance().getIdentifier();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdentifierBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.identifier_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getAction());
        }
        for (int i = 0; i < this.categories_.size(); i++) {
            output.writeString(2, this.categories_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(3, getData());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(4, getType());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(5, getFlag());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(6, getPackage());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(7, getComponent());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(8, getSourceBounds());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(9, getClipData());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeString(10, getExtras());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt32(11, this.contentUserHint_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeString(12, getSelector());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeString(13, getIdentifier());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getAction());
        }
        int dataSize = 0;
        for (int i = 0; i < this.categories_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.categories_.get(i));
        }
        int size3 = size2 + dataSize + (getCategoriesList().size() * 1);
        if ((this.bitField0_ & 2) == 2) {
            size3 += CodedOutputStream.computeStringSize(3, getData());
        }
        if ((this.bitField0_ & 4) == 4) {
            size3 += CodedOutputStream.computeStringSize(4, getType());
        }
        if ((this.bitField0_ & 8) == 8) {
            size3 += CodedOutputStream.computeStringSize(5, getFlag());
        }
        if ((this.bitField0_ & 16) == 16) {
            size3 += CodedOutputStream.computeStringSize(6, getPackage());
        }
        if ((this.bitField0_ & 32) == 32) {
            size3 += CodedOutputStream.computeMessageSize(7, getComponent());
        }
        if ((this.bitField0_ & 64) == 64) {
            size3 += CodedOutputStream.computeStringSize(8, getSourceBounds());
        }
        if ((this.bitField0_ & 128) == 128) {
            size3 += CodedOutputStream.computeStringSize(9, getClipData());
        }
        if ((this.bitField0_ & 256) == 256) {
            size3 += CodedOutputStream.computeStringSize(10, getExtras());
        }
        if ((this.bitField0_ & 512) == 512) {
            size3 += CodedOutputStream.computeInt32Size(11, this.contentUserHint_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size3 += CodedOutputStream.computeStringSize(12, getSelector());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size3 += CodedOutputStream.computeStringSize(13, getIdentifier());
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static IntentProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentProto parseFrom(InputStream input) throws IOException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IntentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentProto parseFrom(CodedInputStream input) throws IOException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IntentProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IntentProto, Builder> implements IntentProtoOrBuilder {
        private Builder() {
            super(IntentProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasAction() {
            return ((IntentProto) this.instance).hasAction();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getAction() {
            return ((IntentProto) this.instance).getAction();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getActionBytes() {
            return ((IntentProto) this.instance).getActionBytes();
        }

        public Builder setAction(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setAction(value);
            return this;
        }

        public Builder clearAction() {
            copyOnWrite();
            ((IntentProto) this.instance).clearAction();
            return this;
        }

        public Builder setActionBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setActionBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public List<String> getCategoriesList() {
            return Collections.unmodifiableList(((IntentProto) this.instance).getCategoriesList());
        }

        @Override // android.content.IntentProtoOrBuilder
        public int getCategoriesCount() {
            return ((IntentProto) this.instance).getCategoriesCount();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getCategories(int index) {
            return ((IntentProto) this.instance).getCategories(index);
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getCategoriesBytes(int index) {
            return ((IntentProto) this.instance).getCategoriesBytes(index);
        }

        public Builder setCategories(int index, String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setCategories(index, value);
            return this;
        }

        public Builder addCategories(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).addCategories(value);
            return this;
        }

        public Builder addAllCategories(Iterable<String> values) {
            copyOnWrite();
            ((IntentProto) this.instance).addAllCategories(values);
            return this;
        }

        public Builder clearCategories() {
            copyOnWrite();
            ((IntentProto) this.instance).clearCategories();
            return this;
        }

        public Builder addCategoriesBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).addCategoriesBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasData() {
            return ((IntentProto) this.instance).hasData();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getData() {
            return ((IntentProto) this.instance).getData();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getDataBytes() {
            return ((IntentProto) this.instance).getDataBytes();
        }

        public Builder setData(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setData(value);
            return this;
        }

        public Builder clearData() {
            copyOnWrite();
            ((IntentProto) this.instance).clearData();
            return this;
        }

        public Builder setDataBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setDataBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasType() {
            return ((IntentProto) this.instance).hasType();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getType() {
            return ((IntentProto) this.instance).getType();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getTypeBytes() {
            return ((IntentProto) this.instance).getTypeBytes();
        }

        public Builder setType(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((IntentProto) this.instance).clearType();
            return this;
        }

        public Builder setTypeBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setTypeBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasFlag() {
            return ((IntentProto) this.instance).hasFlag();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getFlag() {
            return ((IntentProto) this.instance).getFlag();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getFlagBytes() {
            return ((IntentProto) this.instance).getFlagBytes();
        }

        public Builder setFlag(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setFlag(value);
            return this;
        }

        public Builder clearFlag() {
            copyOnWrite();
            ((IntentProto) this.instance).clearFlag();
            return this;
        }

        public Builder setFlagBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setFlagBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasPackage() {
            return ((IntentProto) this.instance).hasPackage();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getPackage() {
            return ((IntentProto) this.instance).getPackage();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getPackageBytes() {
            return ((IntentProto) this.instance).getPackageBytes();
        }

        public Builder setPackage(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setPackage(value);
            return this;
        }

        public Builder clearPackage() {
            copyOnWrite();
            ((IntentProto) this.instance).clearPackage();
            return this;
        }

        public Builder setPackageBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setPackageBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasComponent() {
            return ((IntentProto) this.instance).hasComponent();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ComponentNameProto getComponent() {
            return ((IntentProto) this.instance).getComponent();
        }

        public Builder setComponent(ComponentNameProto value) {
            copyOnWrite();
            ((IntentProto) this.instance).setComponent((IntentProto) value);
            return this;
        }

        public Builder setComponent(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentProto) this.instance).setComponent((IntentProto) builderForValue);
            return this;
        }

        public Builder mergeComponent(ComponentNameProto value) {
            copyOnWrite();
            ((IntentProto) this.instance).mergeComponent(value);
            return this;
        }

        public Builder clearComponent() {
            copyOnWrite();
            ((IntentProto) this.instance).clearComponent();
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasSourceBounds() {
            return ((IntentProto) this.instance).hasSourceBounds();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getSourceBounds() {
            return ((IntentProto) this.instance).getSourceBounds();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getSourceBoundsBytes() {
            return ((IntentProto) this.instance).getSourceBoundsBytes();
        }

        public Builder setSourceBounds(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setSourceBounds(value);
            return this;
        }

        public Builder clearSourceBounds() {
            copyOnWrite();
            ((IntentProto) this.instance).clearSourceBounds();
            return this;
        }

        public Builder setSourceBoundsBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setSourceBoundsBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasClipData() {
            return ((IntentProto) this.instance).hasClipData();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getClipData() {
            return ((IntentProto) this.instance).getClipData();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getClipDataBytes() {
            return ((IntentProto) this.instance).getClipDataBytes();
        }

        public Builder setClipData(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setClipData(value);
            return this;
        }

        public Builder clearClipData() {
            copyOnWrite();
            ((IntentProto) this.instance).clearClipData();
            return this;
        }

        public Builder setClipDataBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setClipDataBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasExtras() {
            return ((IntentProto) this.instance).hasExtras();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getExtras() {
            return ((IntentProto) this.instance).getExtras();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getExtrasBytes() {
            return ((IntentProto) this.instance).getExtrasBytes();
        }

        public Builder setExtras(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setExtras(value);
            return this;
        }

        public Builder clearExtras() {
            copyOnWrite();
            ((IntentProto) this.instance).clearExtras();
            return this;
        }

        public Builder setExtrasBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setExtrasBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasContentUserHint() {
            return ((IntentProto) this.instance).hasContentUserHint();
        }

        @Override // android.content.IntentProtoOrBuilder
        public int getContentUserHint() {
            return ((IntentProto) this.instance).getContentUserHint();
        }

        public Builder setContentUserHint(int value) {
            copyOnWrite();
            ((IntentProto) this.instance).setContentUserHint(value);
            return this;
        }

        public Builder clearContentUserHint() {
            copyOnWrite();
            ((IntentProto) this.instance).clearContentUserHint();
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasSelector() {
            return ((IntentProto) this.instance).hasSelector();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getSelector() {
            return ((IntentProto) this.instance).getSelector();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getSelectorBytes() {
            return ((IntentProto) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((IntentProto) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setSelectorBytes(value);
            return this;
        }

        @Override // android.content.IntentProtoOrBuilder
        public boolean hasIdentifier() {
            return ((IntentProto) this.instance).hasIdentifier();
        }

        @Override // android.content.IntentProtoOrBuilder
        public String getIdentifier() {
            return ((IntentProto) this.instance).getIdentifier();
        }

        @Override // android.content.IntentProtoOrBuilder
        public ByteString getIdentifierBytes() {
            return ((IntentProto) this.instance).getIdentifierBytes();
        }

        public Builder setIdentifier(String value) {
            copyOnWrite();
            ((IntentProto) this.instance).setIdentifier(value);
            return this;
        }

        public Builder clearIdentifier() {
            copyOnWrite();
            ((IntentProto) this.instance).clearIdentifier();
            return this;
        }

        public Builder setIdentifierBytes(ByteString value) {
            copyOnWrite();
            ((IntentProto) this.instance).setIdentifierBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IntentProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.categories_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IntentProto other = (IntentProto) arg1;
                this.action_ = visitor.visitString(hasAction(), this.action_, other.hasAction(), other.action_);
                this.categories_ = visitor.visitList(this.categories_, other.categories_);
                this.data_ = visitor.visitString(hasData(), this.data_, other.hasData(), other.data_);
                this.type_ = visitor.visitString(hasType(), this.type_, other.hasType(), other.type_);
                this.flag_ = visitor.visitString(hasFlag(), this.flag_, other.hasFlag(), other.flag_);
                this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                this.component_ = (ComponentNameProto) visitor.visitMessage(this.component_, other.component_);
                this.sourceBounds_ = visitor.visitString(hasSourceBounds(), this.sourceBounds_, other.hasSourceBounds(), other.sourceBounds_);
                this.clipData_ = visitor.visitString(hasClipData(), this.clipData_, other.hasClipData(), other.clipData_);
                this.extras_ = visitor.visitString(hasExtras(), this.extras_, other.hasExtras(), other.extras_);
                this.contentUserHint_ = visitor.visitInt(hasContentUserHint(), this.contentUserHint_, other.hasContentUserHint(), other.contentUserHint_);
                this.selector_ = visitor.visitString(hasSelector(), this.selector_, other.hasSelector(), other.selector_);
                this.identifier_ = visitor.visitString(hasIdentifier(), this.identifier_, other.hasIdentifier(), other.identifier_);
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
                                this.action_ = s;
                                break;
                            case 18:
                                String s2 = input.readString();
                                if (!this.categories_.isModifiable()) {
                                    this.categories_ = GeneratedMessageLite.mutableCopy(this.categories_);
                                }
                                this.categories_.add(s2);
                                break;
                            case 26:
                                String s3 = input.readString();
                                this.bitField0_ |= 2;
                                this.data_ = s3;
                                break;
                            case 34:
                                String s4 = input.readString();
                                this.bitField0_ |= 4;
                                this.type_ = s4;
                                break;
                            case 42:
                                String s5 = input.readString();
                                this.bitField0_ |= 8;
                                this.flag_ = s5;
                                break;
                            case 50:
                                String s6 = input.readString();
                                this.bitField0_ |= 16;
                                this.package_ = s6;
                                break;
                            case 58:
                                ComponentNameProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder = (ComponentNameProto.Builder) this.component_.toBuilder();
                                }
                                this.component_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.component_);
                                    this.component_ = (ComponentNameProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 66:
                                String s7 = input.readString();
                                this.bitField0_ |= 64;
                                this.sourceBounds_ = s7;
                                break;
                            case 74:
                                String s8 = input.readString();
                                this.bitField0_ |= 128;
                                this.clipData_ = s8;
                                break;
                            case 82:
                                String s9 = input.readString();
                                this.bitField0_ |= 256;
                                this.extras_ = s9;
                                break;
                            case 88:
                                this.bitField0_ |= 512;
                                this.contentUserHint_ = input.readInt32();
                                break;
                            case 98:
                                String s10 = input.readString();
                                this.bitField0_ |= 1024;
                                this.selector_ = s10;
                                break;
                            case 106:
                                String s11 = input.readString();
                                this.bitField0_ |= 2048;
                                this.identifier_ = s11;
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
                    synchronized (IntentProto.class) {
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

    public static IntentProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IntentProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
