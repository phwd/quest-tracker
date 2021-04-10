package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.oculus.companion.server.Protocol$Controller;
import java.io.IOException;

public final class Protocol$ControllerPairRequest extends GeneratedMessageLite<Protocol$ControllerPairRequest, Builder> implements Protocol$ControllerPairRequestOrBuilder {
    private static final Protocol$ControllerPairRequest DEFAULT_INSTANCE = new Protocol$ControllerPairRequest();
    private static volatile Parser<Protocol$ControllerPairRequest> PARSER;
    private int bitField0_;
    private Protocol$Controller controller_;
    private byte memoizedIsInitialized = -1;
    private boolean reuseLastRequest_ = false;

    private Protocol$ControllerPairRequest() {
    }

    public boolean hasController() {
        return (this.bitField0_ & 1) == 1;
    }

    public Protocol$Controller getController() {
        Protocol$Controller protocol$Controller = this.controller_;
        return protocol$Controller == null ? Protocol$Controller.getDefaultInstance() : protocol$Controller;
    }

    public boolean hasReuseLastRequest() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getReuseLastRequest() {
        return this.reuseLastRequest_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeMessage(1, getController());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.reuseLastRequest_);
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
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getController());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, this.reuseLastRequest_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$ControllerPairRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$ControllerPairRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerPairRequest, Builder> implements Protocol$ControllerPairRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerPairRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$ControllerPairRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasController()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!getController().isInitialized()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                }
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$ControllerPairRequest protocol$ControllerPairRequest = (Protocol$ControllerPairRequest) obj2;
                this.controller_ = (Protocol$Controller) visitor.visitMessage(this.controller_, protocol$ControllerPairRequest.controller_);
                this.reuseLastRequest_ = visitor.visitBoolean(hasReuseLastRequest(), this.reuseLastRequest_, protocol$ControllerPairRequest.hasReuseLastRequest(), protocol$ControllerPairRequest.reuseLastRequest_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$ControllerPairRequest.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                Protocol$Controller.Builder builder = (this.bitField0_ & 1) == 1 ? (Protocol$Controller.Builder) this.controller_.toBuilder() : null;
                                this.controller_ = (Protocol$Controller) codedInputStream.readMessage(Protocol$Controller.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((GeneratedMessageLite) this.controller_);
                                    this.controller_ = (Protocol$Controller) builder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.reuseLastRequest_ = codedInputStream.readBool();
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
                    synchronized (Protocol$ControllerPairRequest.class) {
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
