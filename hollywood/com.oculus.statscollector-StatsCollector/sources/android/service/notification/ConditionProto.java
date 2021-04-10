package android.service.notification;

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

public final class ConditionProto extends GeneratedMessageLite<ConditionProto, Builder> implements ConditionProtoOrBuilder {
    private static final ConditionProto DEFAULT_INSTANCE = new ConditionProto();
    public static final int FLAGS_FIELD_NUMBER = 7;
    public static final int ICON_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int LINE_1_FIELD_NUMBER = 3;
    public static final int LINE_2_FIELD_NUMBER = 4;
    private static volatile Parser<ConditionProto> PARSER = null;
    public static final int STATE_FIELD_NUMBER = 6;
    public static final int SUMMARY_FIELD_NUMBER = 2;
    private int bitField0_;
    private int flags_ = 0;
    private int icon_ = 0;
    private String id_ = "";
    private String line1_ = "";
    private String line2_ = "";
    private int state_ = 0;
    private String summary_ = "";

    private ConditionProto() {
    }

    public enum State implements Internal.EnumLite {
        STATE_FALSE(0),
        STATE_TRUE(1),
        STATE_UNKNOWN(2),
        STATE_ERROR(3);
        
        public static final int STATE_ERROR_VALUE = 3;
        public static final int STATE_FALSE_VALUE = 0;
        public static final int STATE_TRUE_VALUE = 1;
        public static final int STATE_UNKNOWN_VALUE = 2;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
            /* class android.service.notification.ConditionProto.State.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int number) {
                return State.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static State valueOf(int value2) {
            return forNumber(value2);
        }

        public static State forNumber(int value2) {
            if (value2 == 0) {
                return STATE_FALSE;
            }
            if (value2 == 1) {
                return STATE_TRUE;
            }
            if (value2 == 2) {
                return STATE_UNKNOWN;
            }
            if (value2 != 3) {
                return null;
            }
            return STATE_ERROR;
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        private State(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
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

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasSummary() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public String getSummary() {
        return this.summary_;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public ByteString getSummaryBytes() {
        return ByteString.copyFromUtf8(this.summary_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSummary(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.summary_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSummary() {
        this.bitField0_ &= -3;
        this.summary_ = getDefaultInstance().getSummary();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSummaryBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.summary_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasLine1() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public String getLine1() {
        return this.line1_;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public ByteString getLine1Bytes() {
        return ByteString.copyFromUtf8(this.line1_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLine1(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.line1_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLine1() {
        this.bitField0_ &= -5;
        this.line1_ = getDefaultInstance().getLine1();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLine1Bytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.line1_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasLine2() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public String getLine2() {
        return this.line2_;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public ByteString getLine2Bytes() {
        return ByteString.copyFromUtf8(this.line2_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLine2(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.line2_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLine2() {
        this.bitField0_ &= -9;
        this.line2_ = getDefaultInstance().getLine2();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLine2Bytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.line2_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasIcon() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public int getIcon() {
        return this.icon_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIcon(int value) {
        this.bitField0_ |= 16;
        this.icon_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIcon() {
        this.bitField0_ &= -17;
        this.icon_ = 0;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public State getState() {
        State result = State.forNumber(this.state_);
        return result == null ? State.STATE_FALSE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(State value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -33;
        this.state_ = 0;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.notification.ConditionProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 64;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -65;
        this.flags_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getSummary());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getLine1());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getLine2());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.icon_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeEnum(6, this.state_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.flags_);
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
            size2 += CodedOutputStream.computeStringSize(2, getSummary());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getLine1());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getLine2());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.icon_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeEnumSize(6, this.state_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.flags_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ConditionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConditionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConditionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConditionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConditionProto parseFrom(InputStream input) throws IOException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConditionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConditionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ConditionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConditionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConditionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConditionProto parseFrom(CodedInputStream input) throws IOException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConditionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConditionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConditionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConditionProto, Builder> implements ConditionProtoOrBuilder {
        private Builder() {
            super(ConditionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasId() {
            return ((ConditionProto) this.instance).hasId();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public String getId() {
            return ((ConditionProto) this.instance).getId();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public ByteString getIdBytes() {
            return ((ConditionProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasSummary() {
            return ((ConditionProto) this.instance).hasSummary();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public String getSummary() {
            return ((ConditionProto) this.instance).getSummary();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public ByteString getSummaryBytes() {
            return ((ConditionProto) this.instance).getSummaryBytes();
        }

        public Builder setSummary(String value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setSummary(value);
            return this;
        }

        public Builder clearSummary() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearSummary();
            return this;
        }

        public Builder setSummaryBytes(ByteString value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setSummaryBytes(value);
            return this;
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasLine1() {
            return ((ConditionProto) this.instance).hasLine1();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public String getLine1() {
            return ((ConditionProto) this.instance).getLine1();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public ByteString getLine1Bytes() {
            return ((ConditionProto) this.instance).getLine1Bytes();
        }

        public Builder setLine1(String value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setLine1(value);
            return this;
        }

        public Builder clearLine1() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearLine1();
            return this;
        }

        public Builder setLine1Bytes(ByteString value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setLine1Bytes(value);
            return this;
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasLine2() {
            return ((ConditionProto) this.instance).hasLine2();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public String getLine2() {
            return ((ConditionProto) this.instance).getLine2();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public ByteString getLine2Bytes() {
            return ((ConditionProto) this.instance).getLine2Bytes();
        }

        public Builder setLine2(String value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setLine2(value);
            return this;
        }

        public Builder clearLine2() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearLine2();
            return this;
        }

        public Builder setLine2Bytes(ByteString value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setLine2Bytes(value);
            return this;
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasIcon() {
            return ((ConditionProto) this.instance).hasIcon();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public int getIcon() {
            return ((ConditionProto) this.instance).getIcon();
        }

        public Builder setIcon(int value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setIcon(value);
            return this;
        }

        public Builder clearIcon() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearIcon();
            return this;
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasState() {
            return ((ConditionProto) this.instance).hasState();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public State getState() {
            return ((ConditionProto) this.instance).getState();
        }

        public Builder setState(State value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearState();
            return this;
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public boolean hasFlags() {
            return ((ConditionProto) this.instance).hasFlags();
        }

        @Override // android.service.notification.ConditionProtoOrBuilder
        public int getFlags() {
            return ((ConditionProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((ConditionProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((ConditionProto) this.instance).clearFlags();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConditionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ConditionProto other = (ConditionProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.summary_ = visitor.visitString(hasSummary(), this.summary_, other.hasSummary(), other.summary_);
                this.line1_ = visitor.visitString(hasLine1(), this.line1_, other.hasLine1(), other.line1_);
                this.line2_ = visitor.visitString(hasLine2(), this.line2_, other.hasLine2(), other.line2_);
                this.icon_ = visitor.visitInt(hasIcon(), this.icon_, other.hasIcon(), other.icon_);
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
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
                            this.bitField0_ |= 1;
                            this.id_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.summary_ = s2;
                        } else if (tag == 26) {
                            String s3 = input.readString();
                            this.bitField0_ |= 4;
                            this.line1_ = s3;
                        } else if (tag == 34) {
                            String s4 = input.readString();
                            this.bitField0_ |= 8;
                            this.line2_ = s4;
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.icon_ = input.readInt32();
                        } else if (tag == 48) {
                            int rawValue = input.readEnum();
                            if (State.forNumber(rawValue) == null) {
                                super.mergeVarintField(6, rawValue);
                            } else {
                                this.bitField0_ |= 32;
                                this.state_ = rawValue;
                            }
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.flags_ = input.readInt32();
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
                    synchronized (ConditionProto.class) {
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

    public static ConditionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConditionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
