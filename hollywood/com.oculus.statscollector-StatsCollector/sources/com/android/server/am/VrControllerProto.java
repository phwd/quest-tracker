package com.android.server.am;

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
import java.util.List;

public final class VrControllerProto extends GeneratedMessageLite<VrControllerProto, Builder> implements VrControllerProtoOrBuilder {
    private static final VrControllerProto DEFAULT_INSTANCE = new VrControllerProto();
    private static volatile Parser<VrControllerProto> PARSER = null;
    public static final int RENDER_THREAD_ID_FIELD_NUMBER = 2;
    public static final int VR_MODE_FIELD_NUMBER = 1;
    private static final Internal.ListAdapter.Converter<Integer, VrMode> vrMode_converter_ = new Internal.ListAdapter.Converter<Integer, VrMode>() {
        /* class com.android.server.am.VrControllerProto.AnonymousClass1 */

        public VrMode convert(Integer from) {
            VrMode result = VrMode.forNumber(from.intValue());
            return result == null ? VrMode.FLAG_NON_VR_MODE : result;
        }
    };
    private int bitField0_;
    private int renderThreadId_ = 0;
    private Internal.IntList vrMode_ = emptyIntList();

    private VrControllerProto() {
    }

    public enum VrMode implements Internal.EnumLite {
        FLAG_NON_VR_MODE(0),
        FLAG_VR_MODE(1),
        FLAG_PERSISTENT_VR_MODE(2);
        
        public static final int FLAG_NON_VR_MODE_VALUE = 0;
        public static final int FLAG_PERSISTENT_VR_MODE_VALUE = 2;
        public static final int FLAG_VR_MODE_VALUE = 1;
        private static final Internal.EnumLiteMap<VrMode> internalValueMap = new Internal.EnumLiteMap<VrMode>() {
            /* class com.android.server.am.VrControllerProto.VrMode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VrMode findValueByNumber(int number) {
                return VrMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static VrMode valueOf(int value2) {
            return forNumber(value2);
        }

        public static VrMode forNumber(int value2) {
            if (value2 == 0) {
                return FLAG_NON_VR_MODE;
            }
            if (value2 == 1) {
                return FLAG_VR_MODE;
            }
            if (value2 != 2) {
                return null;
            }
            return FLAG_PERSISTENT_VR_MODE;
        }

        public static Internal.EnumLiteMap<VrMode> internalGetValueMap() {
            return internalValueMap;
        }

        private VrMode(int value2) {
            this.value = value2;
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // com.android.server.am.VrControllerProtoOrBuilder
    public List<VrMode> getVrModeList() {
        return new Internal.ListAdapter(this.vrMode_, vrMode_converter_);
    }

    @Override // com.android.server.am.VrControllerProtoOrBuilder
    public int getVrModeCount() {
        return this.vrMode_.size();
    }

    @Override // com.android.server.am.VrControllerProtoOrBuilder
    public VrMode getVrMode(int index) {
        return vrMode_converter_.convert(Integer.valueOf(this.vrMode_.getInt(index)));
    }

    private void ensureVrModeIsMutable() {
        if (!this.vrMode_.isModifiable()) {
            this.vrMode_ = GeneratedMessageLite.mutableCopy(this.vrMode_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVrMode(int index, VrMode value) {
        if (value != null) {
            ensureVrModeIsMutable();
            this.vrMode_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addVrMode(VrMode value) {
        if (value != null) {
            ensureVrModeIsMutable();
            this.vrMode_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllVrMode(Iterable<? extends VrMode> values) {
        ensureVrModeIsMutable();
        for (VrMode value : values) {
            this.vrMode_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVrMode() {
        this.vrMode_ = emptyIntList();
    }

    @Override // com.android.server.am.VrControllerProtoOrBuilder
    public boolean hasRenderThreadId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.VrControllerProtoOrBuilder
    public int getRenderThreadId() {
        return this.renderThreadId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRenderThreadId(int value) {
        this.bitField0_ |= 1;
        this.renderThreadId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRenderThreadId() {
        this.bitField0_ &= -2;
        this.renderThreadId_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.vrMode_.size(); i++) {
            output.writeEnum(1, this.vrMode_.getInt(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(2, this.renderThreadId_);
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
        for (int i = 0; i < this.vrMode_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.vrMode_.getInt(i));
        }
        int size2 = 0 + dataSize + (this.vrMode_.size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeInt32Size(2, this.renderThreadId_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static VrControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static VrControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static VrControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static VrControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static VrControllerProto parseFrom(InputStream input) throws IOException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static VrControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static VrControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (VrControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static VrControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (VrControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static VrControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static VrControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (VrControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(VrControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<VrControllerProto, Builder> implements VrControllerProtoOrBuilder {
        private Builder() {
            super(VrControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.VrControllerProtoOrBuilder
        public List<VrMode> getVrModeList() {
            return ((VrControllerProto) this.instance).getVrModeList();
        }

        @Override // com.android.server.am.VrControllerProtoOrBuilder
        public int getVrModeCount() {
            return ((VrControllerProto) this.instance).getVrModeCount();
        }

        @Override // com.android.server.am.VrControllerProtoOrBuilder
        public VrMode getVrMode(int index) {
            return ((VrControllerProto) this.instance).getVrMode(index);
        }

        public Builder setVrMode(int index, VrMode value) {
            copyOnWrite();
            ((VrControllerProto) this.instance).setVrMode(index, value);
            return this;
        }

        public Builder addVrMode(VrMode value) {
            copyOnWrite();
            ((VrControllerProto) this.instance).addVrMode(value);
            return this;
        }

        public Builder addAllVrMode(Iterable<? extends VrMode> values) {
            copyOnWrite();
            ((VrControllerProto) this.instance).addAllVrMode(values);
            return this;
        }

        public Builder clearVrMode() {
            copyOnWrite();
            ((VrControllerProto) this.instance).clearVrMode();
            return this;
        }

        @Override // com.android.server.am.VrControllerProtoOrBuilder
        public boolean hasRenderThreadId() {
            return ((VrControllerProto) this.instance).hasRenderThreadId();
        }

        @Override // com.android.server.am.VrControllerProtoOrBuilder
        public int getRenderThreadId() {
            return ((VrControllerProto) this.instance).getRenderThreadId();
        }

        public Builder setRenderThreadId(int value) {
            copyOnWrite();
            ((VrControllerProto) this.instance).setRenderThreadId(value);
            return this;
        }

        public Builder clearRenderThreadId() {
            copyOnWrite();
            ((VrControllerProto) this.instance).clearRenderThreadId();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new VrControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.vrMode_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                VrControllerProto other = (VrControllerProto) arg1;
                this.vrMode_ = visitor.visitIntList(this.vrMode_, other.vrMode_);
                this.renderThreadId_ = visitor.visitInt(hasRenderThreadId(), this.renderThreadId_, other.hasRenderThreadId(), other.renderThreadId_);
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
                            if (!this.vrMode_.isModifiable()) {
                                this.vrMode_ = GeneratedMessageLite.mutableCopy(this.vrMode_);
                            }
                            int rawValue = input.readEnum();
                            if (VrMode.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.vrMode_.addInt(rawValue);
                            }
                        } else if (tag == 10) {
                            if (!this.vrMode_.isModifiable()) {
                                this.vrMode_ = GeneratedMessageLite.mutableCopy(this.vrMode_);
                            }
                            int oldLimit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue2 = input.readEnum();
                                if (VrMode.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(1, rawValue2);
                                } else {
                                    this.vrMode_.addInt(rawValue2);
                                }
                            }
                            input.popLimit(oldLimit);
                        } else if (tag == 16) {
                            this.bitField0_ |= 1;
                            this.renderThreadId_ = input.readInt32();
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
                    synchronized (VrControllerProto.class) {
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

    public static VrControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<VrControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
