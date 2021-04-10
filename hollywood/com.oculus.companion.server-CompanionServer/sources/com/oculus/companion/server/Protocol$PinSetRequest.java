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

public final class Protocol$PinSetRequest extends GeneratedMessageLite<Protocol$PinSetRequest, Builder> implements Protocol$PinSetRequestOrBuilder {
    private static final Protocol$PinSetRequest DEFAULT_INSTANCE = new Protocol$PinSetRequest();
    private static volatile Parser<Protocol$PinSetRequest> PARSER;
    private int bitField0_;
    private int method_ = 0;
    private String newPin_ = "";
    private String oldPin_ = "";

    private Protocol$PinSetRequest() {
    }

    public boolean hasNewPin() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getNewPin() {
        return this.newPin_;
    }

    public boolean hasOldPin() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getOldPin() {
        return this.oldPin_;
    }

    public boolean hasMethod() {
        return (this.bitField0_ & 4) == 4;
    }

    public Protocol$CredentialLockMethod getMethod() {
        Protocol$CredentialLockMethod forNumber = Protocol$CredentialLockMethod.forNumber(this.method_);
        return forNumber == null ? Protocol$CredentialLockMethod.PATTERN : forNumber;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getNewPin());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getOldPin());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.method_);
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getNewPin());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getOldPin());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(3, this.method_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$PinSetRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$PinSetRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$PinSetRequest, Builder> implements Protocol$PinSetRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$PinSetRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$PinSetRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$PinSetRequest protocol$PinSetRequest = (Protocol$PinSetRequest) obj2;
                this.newPin_ = visitor.visitString(hasNewPin(), this.newPin_, protocol$PinSetRequest.hasNewPin(), protocol$PinSetRequest.newPin_);
                this.oldPin_ = visitor.visitString(hasOldPin(), this.oldPin_, protocol$PinSetRequest.hasOldPin(), protocol$PinSetRequest.oldPin_);
                this.method_ = visitor.visitInt(hasMethod(), this.method_, protocol$PinSetRequest.hasMethod(), protocol$PinSetRequest.method_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$PinSetRequest.bitField0_;
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
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.newPin_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.oldPin_ = readString2;
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$CredentialLockMethod.forNumber(readEnum) == null) {
                                    super.mergeVarintField(3, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.method_ = readEnum;
                                }
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
                    synchronized (Protocol$PinSetRequest.class) {
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
