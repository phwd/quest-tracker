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

public final class CellularData extends GeneratedMessageLite<CellularData, Builder> implements CellularDataOrBuilder {
    private static final CellularData DEFAULT_INSTANCE = new CellularData();
    public static final int IS_ROAMING_FIELD_NUMBER = 2;
    public static final int NETWORK_MCCMNC_FIELD_NUMBER = 3;
    private static volatile Parser<CellularData> PARSER = null;
    public static final int RAT_TYPE_FIELD_NUMBER = 1;
    public static final int SIGNAL_STRENGTH_FIELD_NUMBER = 5;
    public static final int SIM_MCCMNC_FIELD_NUMBER = 4;
    private int bitField0_;
    private boolean isRoaming_ = false;
    private String networkMccmnc_ = "";
    private int ratType_ = 0;
    private int signalStrength_ = 0;
    private String simMccmnc_ = "";

    private CellularData() {
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public boolean hasRatType() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public RadioTech getRatType() {
        RadioTech result = RadioTech.forNumber(this.ratType_);
        return result == null ? RadioTech.RADIO_TECHNOLOGY_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRatType(RadioTech value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.ratType_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRatType() {
        this.bitField0_ &= -2;
        this.ratType_ = 0;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public boolean hasIsRoaming() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public boolean getIsRoaming() {
        return this.isRoaming_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsRoaming(boolean value) {
        this.bitField0_ |= 2;
        this.isRoaming_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsRoaming() {
        this.bitField0_ &= -3;
        this.isRoaming_ = false;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public boolean hasNetworkMccmnc() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public String getNetworkMccmnc() {
        return this.networkMccmnc_;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public ByteString getNetworkMccmncBytes() {
        return ByteString.copyFromUtf8(this.networkMccmnc_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkMccmnc(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.networkMccmnc_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetworkMccmnc() {
        this.bitField0_ &= -5;
        this.networkMccmnc_ = getDefaultInstance().getNetworkMccmnc();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkMccmncBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.networkMccmnc_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public boolean hasSimMccmnc() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public String getSimMccmnc() {
        return this.simMccmnc_;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public ByteString getSimMccmncBytes() {
        return ByteString.copyFromUtf8(this.simMccmnc_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSimMccmnc(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.simMccmnc_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSimMccmnc() {
        this.bitField0_ &= -9;
        this.simMccmnc_ = getDefaultInstance().getSimMccmnc();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSimMccmncBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.simMccmnc_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public boolean hasSignalStrength() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.connectivity.CellularDataOrBuilder
    public int getSignalStrength() {
        return this.signalStrength_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignalStrength(int value) {
        this.bitField0_ |= 16;
        this.signalStrength_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSignalStrength() {
        this.bitField0_ &= -17;
        this.signalStrength_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.ratType_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isRoaming_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getNetworkMccmnc());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getSimMccmnc());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.signalStrength_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.ratType_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isRoaming_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getNetworkMccmnc());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getSimMccmnc());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.signalStrength_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static CellularData parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CellularData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CellularData parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CellularData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CellularData parseFrom(InputStream input) throws IOException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CellularData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CellularData parseDelimitedFrom(InputStream input) throws IOException {
        return (CellularData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static CellularData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CellularData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CellularData parseFrom(CodedInputStream input) throws IOException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CellularData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CellularData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CellularData prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CellularData, Builder> implements CellularDataOrBuilder {
        private Builder() {
            super(CellularData.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public boolean hasRatType() {
            return ((CellularData) this.instance).hasRatType();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public RadioTech getRatType() {
            return ((CellularData) this.instance).getRatType();
        }

        public Builder setRatType(RadioTech value) {
            copyOnWrite();
            ((CellularData) this.instance).setRatType(value);
            return this;
        }

        public Builder clearRatType() {
            copyOnWrite();
            ((CellularData) this.instance).clearRatType();
            return this;
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public boolean hasIsRoaming() {
            return ((CellularData) this.instance).hasIsRoaming();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public boolean getIsRoaming() {
            return ((CellularData) this.instance).getIsRoaming();
        }

        public Builder setIsRoaming(boolean value) {
            copyOnWrite();
            ((CellularData) this.instance).setIsRoaming(value);
            return this;
        }

        public Builder clearIsRoaming() {
            copyOnWrite();
            ((CellularData) this.instance).clearIsRoaming();
            return this;
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public boolean hasNetworkMccmnc() {
            return ((CellularData) this.instance).hasNetworkMccmnc();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public String getNetworkMccmnc() {
            return ((CellularData) this.instance).getNetworkMccmnc();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public ByteString getNetworkMccmncBytes() {
            return ((CellularData) this.instance).getNetworkMccmncBytes();
        }

        public Builder setNetworkMccmnc(String value) {
            copyOnWrite();
            ((CellularData) this.instance).setNetworkMccmnc(value);
            return this;
        }

        public Builder clearNetworkMccmnc() {
            copyOnWrite();
            ((CellularData) this.instance).clearNetworkMccmnc();
            return this;
        }

        public Builder setNetworkMccmncBytes(ByteString value) {
            copyOnWrite();
            ((CellularData) this.instance).setNetworkMccmncBytes(value);
            return this;
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public boolean hasSimMccmnc() {
            return ((CellularData) this.instance).hasSimMccmnc();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public String getSimMccmnc() {
            return ((CellularData) this.instance).getSimMccmnc();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public ByteString getSimMccmncBytes() {
            return ((CellularData) this.instance).getSimMccmncBytes();
        }

        public Builder setSimMccmnc(String value) {
            copyOnWrite();
            ((CellularData) this.instance).setSimMccmnc(value);
            return this;
        }

        public Builder clearSimMccmnc() {
            copyOnWrite();
            ((CellularData) this.instance).clearSimMccmnc();
            return this;
        }

        public Builder setSimMccmncBytes(ByteString value) {
            copyOnWrite();
            ((CellularData) this.instance).setSimMccmncBytes(value);
            return this;
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public boolean hasSignalStrength() {
            return ((CellularData) this.instance).hasSignalStrength();
        }

        @Override // com.android.server.connectivity.CellularDataOrBuilder
        public int getSignalStrength() {
            return ((CellularData) this.instance).getSignalStrength();
        }

        public Builder setSignalStrength(int value) {
            copyOnWrite();
            ((CellularData) this.instance).setSignalStrength(value);
            return this;
        }

        public Builder clearSignalStrength() {
            copyOnWrite();
            ((CellularData) this.instance).clearSignalStrength();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new CellularData();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                CellularData other = (CellularData) arg1;
                this.ratType_ = visitor.visitInt(hasRatType(), this.ratType_, other.hasRatType(), other.ratType_);
                this.isRoaming_ = visitor.visitBoolean(hasIsRoaming(), this.isRoaming_, other.hasIsRoaming(), other.isRoaming_);
                this.networkMccmnc_ = visitor.visitString(hasNetworkMccmnc(), this.networkMccmnc_, other.hasNetworkMccmnc(), other.networkMccmnc_);
                this.simMccmnc_ = visitor.visitString(hasSimMccmnc(), this.simMccmnc_, other.hasSimMccmnc(), other.simMccmnc_);
                this.signalStrength_ = visitor.visitInt(hasSignalStrength(), this.signalStrength_, other.hasSignalStrength(), other.signalStrength_);
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
                            int rawValue = input.readEnum();
                            if (RadioTech.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.ratType_ = rawValue;
                            }
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isRoaming_ = input.readBool();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.networkMccmnc_ = s;
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.simMccmnc_ = s2;
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.signalStrength_ = input.readInt32();
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
                    synchronized (CellularData.class) {
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

    public static CellularData getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CellularData> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
