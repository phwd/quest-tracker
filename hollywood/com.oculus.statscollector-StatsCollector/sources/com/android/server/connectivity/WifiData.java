package com.android.server.connectivity;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WifiData extends GeneratedMessageLite<WifiData, Builder> implements WifiDataOrBuilder {
    private static final WifiData DEFAULT_INSTANCE = new WifiData();
    private static volatile Parser<WifiData> PARSER = null;
    public static final int SIGNAL_STRENGTH_FIELD_NUMBER = 1;
    public static final int WIFI_BAND_FIELD_NUMBER = 2;
    private int bitField0_;
    private int signalStrength_ = 0;
    private int wifiBand_ = 0;

    private WifiData() {
    }

    @Override // com.android.server.connectivity.WifiDataOrBuilder
    public boolean hasSignalStrength() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.connectivity.WifiDataOrBuilder
    public int getSignalStrength() {
        return this.signalStrength_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignalStrength(int value) {
        this.bitField0_ |= 1;
        this.signalStrength_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSignalStrength() {
        this.bitField0_ &= -2;
        this.signalStrength_ = 0;
    }

    @Override // com.android.server.connectivity.WifiDataOrBuilder
    public boolean hasWifiBand() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.connectivity.WifiDataOrBuilder
    public ApBand getWifiBand() {
        ApBand result = ApBand.forNumber(this.wifiBand_);
        return result == null ? ApBand.AP_BAND_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWifiBand(ApBand value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.wifiBand_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWifiBand() {
        this.bitField0_ &= -3;
        this.wifiBand_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.signalStrength_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.wifiBand_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.signalStrength_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.wifiBand_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WifiData parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WifiData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WifiData parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WifiData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WifiData parseFrom(InputStream input) throws IOException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WifiData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WifiData parseDelimitedFrom(InputStream input) throws IOException {
        return (WifiData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WifiData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WifiData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WifiData parseFrom(CodedInputStream input) throws IOException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WifiData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WifiData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WifiData prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WifiData, Builder> implements WifiDataOrBuilder {
        private Builder() {
            super(WifiData.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.connectivity.WifiDataOrBuilder
        public boolean hasSignalStrength() {
            return ((WifiData) this.instance).hasSignalStrength();
        }

        @Override // com.android.server.connectivity.WifiDataOrBuilder
        public int getSignalStrength() {
            return ((WifiData) this.instance).getSignalStrength();
        }

        public Builder setSignalStrength(int value) {
            copyOnWrite();
            ((WifiData) this.instance).setSignalStrength(value);
            return this;
        }

        public Builder clearSignalStrength() {
            copyOnWrite();
            ((WifiData) this.instance).clearSignalStrength();
            return this;
        }

        @Override // com.android.server.connectivity.WifiDataOrBuilder
        public boolean hasWifiBand() {
            return ((WifiData) this.instance).hasWifiBand();
        }

        @Override // com.android.server.connectivity.WifiDataOrBuilder
        public ApBand getWifiBand() {
            return ((WifiData) this.instance).getWifiBand();
        }

        public Builder setWifiBand(ApBand value) {
            copyOnWrite();
            ((WifiData) this.instance).setWifiBand(value);
            return this;
        }

        public Builder clearWifiBand() {
            copyOnWrite();
            ((WifiData) this.instance).clearWifiBand();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WifiData();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WifiData other = (WifiData) arg1;
                this.signalStrength_ = visitor.visitInt(hasSignalStrength(), this.signalStrength_, other.hasSignalStrength(), other.signalStrength_);
                this.wifiBand_ = visitor.visitInt(hasWifiBand(), this.wifiBand_, other.hasWifiBand(), other.wifiBand_);
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
                            this.signalStrength_ = input.readInt32();
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            if (ApBand.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.wifiBand_ = rawValue;
                            }
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
                    synchronized (WifiData.class) {
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

    public static WifiData getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WifiData> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
