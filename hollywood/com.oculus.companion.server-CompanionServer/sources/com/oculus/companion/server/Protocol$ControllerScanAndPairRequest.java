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

public final class Protocol$ControllerScanAndPairRequest extends GeneratedMessageLite<Protocol$ControllerScanAndPairRequest, Builder> implements Protocol$ControllerScanAndPairRequestOrBuilder {
    private static final Protocol$ControllerScanAndPairRequest DEFAULT_INSTANCE = new Protocol$ControllerScanAndPairRequest();
    private static volatile Parser<Protocol$ControllerScanAndPairRequest> PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int timeoutMs_ = 0;
    private int type_ = 0;

    private Protocol$ControllerScanAndPairRequest() {
    }

    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    public Protocol$ControllerType getType() {
        Protocol$ControllerType forNumber = Protocol$ControllerType.forNumber(this.type_);
        return forNumber == null ? Protocol$ControllerType.PRIMARY : forNumber;
    }

    public boolean hasTimeoutMs() {
        return (this.bitField0_ & 2) == 2;
    }

    public int getTimeoutMs() {
        return this.timeoutMs_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeEnum(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.timeoutMs_);
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
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt32Size(2, this.timeoutMs_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$ControllerScanAndPairRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$ControllerScanAndPairRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerScanAndPairRequest, Builder> implements Protocol$ControllerScanAndPairRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerScanAndPairRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$ControllerScanAndPairRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasType()) {
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
                Protocol$ControllerScanAndPairRequest protocol$ControllerScanAndPairRequest = (Protocol$ControllerScanAndPairRequest) obj2;
                this.type_ = visitor.visitInt(hasType(), this.type_, protocol$ControllerScanAndPairRequest.hasType(), protocol$ControllerScanAndPairRequest.type_);
                this.timeoutMs_ = visitor.visitInt(hasTimeoutMs(), this.timeoutMs_, protocol$ControllerScanAndPairRequest.hasTimeoutMs(), protocol$ControllerScanAndPairRequest.timeoutMs_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$ControllerScanAndPairRequest.bitField0_;
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
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ControllerType.forNumber(readEnum) == null) {
                                    super.mergeVarintField(1, readEnum);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.type_ = readEnum;
                                }
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.timeoutMs_ = codedInputStream.readInt32();
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
                    synchronized (Protocol$ControllerScanAndPairRequest.class) {
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
