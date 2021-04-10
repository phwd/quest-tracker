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

public final class Protocol$Request extends GeneratedMessageLite<Protocol$Request, Builder> implements Protocol$RequestOrBuilder {
    private static final Protocol$Request DEFAULT_INSTANCE = new Protocol$Request();
    private static volatile Parser<Protocol$Request> PARSER;
    private int bitField0_;
    private ByteString body_ = ByteString.EMPTY;
    private byte memoizedIsInitialized = -1;
    private int method_ = 99999;
    private int seq_ = 0;
    private int version_ = 0;

    private Protocol$Request() {
    }

    public boolean hasVersion() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean hasMethod() {
        return (this.bitField0_ & 2) == 2;
    }

    public Protocol$Method getMethod() {
        Protocol$Method forNumber = Protocol$Method.forNumber(this.method_);
        return forNumber == null ? Protocol$Method.UNKNOWN : forNumber;
    }

    public boolean hasSeq() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getSeq() {
        return this.seq_;
    }

    public boolean hasBody() {
        return (this.bitField0_ & 8) == 8;
    }

    public ByteString getBody() {
        return this.body_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeEnum(2, this.method_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeUInt32(3, this.seq_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBytes(4, this.body_);
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
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, this.version_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeEnumSize(2, this.method_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeUInt32Size(3, this.seq_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBytesSize(4, this.body_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$Request parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Protocol$Request parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Protocol$Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$Request, Builder> implements Protocol$RequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$Request.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$Request();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasVersion()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                }
                if (booleanValue) {
                    this.memoizedIsInitialized = 1;
                }
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$Request protocol$Request = (Protocol$Request) obj2;
                this.version_ = visitor.visitInt(hasVersion(), this.version_, protocol$Request.hasVersion(), protocol$Request.version_);
                this.method_ = visitor.visitInt(hasMethod(), this.method_, protocol$Request.hasMethod(), protocol$Request.method_);
                this.seq_ = visitor.visitInt(hasSeq(), this.seq_, protocol$Request.hasSeq(), protocol$Request.seq_);
                this.body_ = visitor.visitByteString(hasBody(), this.body_, protocol$Request.hasBody(), protocol$Request.body_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$Request.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.version_ = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$Method.forNumber(readEnum) == null) {
                                    super.mergeVarintField(2, readEnum);
                                } else {
                                    this.bitField0_ |= 2;
                                    this.method_ = readEnum;
                                }
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.seq_ = codedInputStream.readUInt32();
                            } else if (readTag == 34) {
                                this.bitField0_ |= 8;
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
                    synchronized (Protocol$Request.class) {
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
