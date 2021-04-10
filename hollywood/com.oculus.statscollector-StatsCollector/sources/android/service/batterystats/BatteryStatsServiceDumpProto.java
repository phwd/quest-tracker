package android.service.batterystats;

import android.os.BatteryStatsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class BatteryStatsServiceDumpProto extends GeneratedMessageLite<BatteryStatsServiceDumpProto, Builder> implements BatteryStatsServiceDumpProtoOrBuilder {
    public static final int BATTERYSTATS_FIELD_NUMBER = 1;
    private static final BatteryStatsServiceDumpProto DEFAULT_INSTANCE = new BatteryStatsServiceDumpProto();
    private static volatile Parser<BatteryStatsServiceDumpProto> PARSER;
    private BatteryStatsProto batterystats_;
    private int bitField0_;

    private BatteryStatsServiceDumpProto() {
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpProtoOrBuilder
    public boolean hasBatterystats() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpProtoOrBuilder
    public BatteryStatsProto getBatterystats() {
        BatteryStatsProto batteryStatsProto = this.batterystats_;
        return batteryStatsProto == null ? BatteryStatsProto.getDefaultInstance() : batteryStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatterystats(BatteryStatsProto value) {
        if (value != null) {
            this.batterystats_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatterystats(BatteryStatsProto.Builder builderForValue) {
        this.batterystats_ = (BatteryStatsProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBatterystats(BatteryStatsProto value) {
        BatteryStatsProto batteryStatsProto = this.batterystats_;
        if (batteryStatsProto == null || batteryStatsProto == BatteryStatsProto.getDefaultInstance()) {
            this.batterystats_ = value;
        } else {
            this.batterystats_ = (BatteryStatsProto) ((BatteryStatsProto.Builder) BatteryStatsProto.newBuilder(this.batterystats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatterystats() {
        this.batterystats_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getBatterystats());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getBatterystats());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BatteryStatsServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryStatsServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryStatsServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryStatsServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryStatsServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryStatsServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BatteryStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryStatsServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatteryStatsServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatteryStatsServiceDumpProto, Builder> implements BatteryStatsServiceDumpProtoOrBuilder {
        private Builder() {
            super(BatteryStatsServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpProtoOrBuilder
        public boolean hasBatterystats() {
            return ((BatteryStatsServiceDumpProto) this.instance).hasBatterystats();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpProtoOrBuilder
        public BatteryStatsProto getBatterystats() {
            return ((BatteryStatsServiceDumpProto) this.instance).getBatterystats();
        }

        public Builder setBatterystats(BatteryStatsProto value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpProto) this.instance).setBatterystats((BatteryStatsServiceDumpProto) value);
            return this;
        }

        public Builder setBatterystats(BatteryStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsServiceDumpProto) this.instance).setBatterystats((BatteryStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeBatterystats(BatteryStatsProto value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpProto) this.instance).mergeBatterystats(value);
            return this;
        }

        public Builder clearBatterystats() {
            copyOnWrite();
            ((BatteryStatsServiceDumpProto) this.instance).clearBatterystats();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BatteryStatsServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BatteryStatsServiceDumpProto other = (BatteryStatsServiceDumpProto) arg1;
                this.batterystats_ = (BatteryStatsProto) visitor.visitMessage(this.batterystats_, other.batterystats_);
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
                            BatteryStatsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (BatteryStatsProto.Builder) this.batterystats_.toBuilder();
                            }
                            this.batterystats_ = (BatteryStatsProto) input.readMessage(BatteryStatsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.batterystats_);
                                this.batterystats_ = (BatteryStatsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (BatteryStatsServiceDumpProto.class) {
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

    public static BatteryStatsServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatteryStatsServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
