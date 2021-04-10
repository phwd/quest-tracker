package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$Response extends GeneratedMessageLite<Protocol$Response, Builder> implements Protocol$ResponseOrBuilder {
    private static final Protocol$Response DEFAULT_INSTANCE = new Protocol$Response();
    private static volatile Parser<Protocol$Response> PARSER;
    private int bitField0_;
    private ByteString body_ = ByteString.EMPTY;
    private int code_ = 0;
    private int seq_ = 0;

    private Protocol$Response() {
    }

    public boolean hasSeq() {
        return (this.bitField0_ & 1) == 1;
    }

    public int getSeq() {
        return this.seq_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSeq(int i) {
        this.bitField0_ |= 1;
        this.seq_ = i;
    }

    public boolean hasCode() {
        return (this.bitField0_ & 2) == 2;
    }

    public Protocol$ResponseCode getCode() {
        Protocol$ResponseCode forNumber = Protocol$ResponseCode.forNumber(this.code_);
        return forNumber == null ? Protocol$ResponseCode.SUCCESS : forNumber;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCode(Protocol$ResponseCode protocol$ResponseCode) {
        if (protocol$ResponseCode != null) {
            this.bitField0_ |= 2;
            this.code_ = protocol$ResponseCode.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasBody() {
        return (this.bitField0_ & 4) == 4;
    }

    public ByteString getBody() {
        return this.body_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBody(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 4;
            this.body_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeUInt32(1, this.seq_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeEnum(2, this.code_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBytes(3, this.body_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, this.seq_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeEnumSize(2, this.code_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBytesSize(3, this.body_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$Response, Builder> implements Protocol$ResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$Response.DEFAULT_INSTANCE);
        }

        public Builder setSeq(int i) {
            copyOnWrite();
            ((Protocol$Response) this.instance).setSeq(i);
            return this;
        }

        public Builder setCode(Protocol$ResponseCode protocol$ResponseCode) {
            copyOnWrite();
            ((Protocol$Response) this.instance).setCode(protocol$ResponseCode);
            return this;
        }

        public Builder setBody(ByteString byteString) {
            copyOnWrite();
            ((Protocol$Response) this.instance).setBody(byteString);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$Response();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$Response protocol$Response = (Protocol$Response) obj2;
                this.seq_ = visitor.visitInt(hasSeq(), this.seq_, protocol$Response.hasSeq(), protocol$Response.seq_);
                this.code_ = visitor.visitInt(hasCode(), this.code_, protocol$Response.hasCode(), protocol$Response.code_);
                this.body_ = visitor.visitByteString(hasBody(), this.body_, protocol$Response.hasBody(), protocol$Response.body_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$Response.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.seq_ = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ResponseCode.forNumber(readEnum) == null) {
                                    super.mergeVarintField(2, readEnum);
                                } else {
                                    this.bitField0_ |= 2;
                                    this.code_ = readEnum;
                                }
                            } else if (readTag == 26) {
                                this.bitField0_ |= 4;
                                this.body_ = codedInputStream.readBytes();
                            } else if (!parseUnknownField(readTag, codedInputStream)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (PARSER == null) {
                    synchronized (Protocol$Response.class) {
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
}
