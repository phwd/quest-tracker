package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.oculus.companion.server.Protocol$WifiNetwork;
import java.io.IOException;

public final class Protocol$WifiForgetRequest extends GeneratedMessageLite<Protocol$WifiForgetRequest, Builder> implements Protocol$WifiForgetRequestOrBuilder {
    private static final Protocol$WifiForgetRequest DEFAULT_INSTANCE = new Protocol$WifiForgetRequest();
    private static volatile Parser<Protocol$WifiForgetRequest> PARSER;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private Protocol$WifiNetwork network_;

    private Protocol$WifiForgetRequest() {
    }

    public boolean hasNetwork() {
        return (this.bitField0_ & 1) == 1;
    }

    public Protocol$WifiNetwork getNetwork() {
        Protocol$WifiNetwork protocol$WifiNetwork = this.network_;
        return protocol$WifiNetwork == null ? Protocol$WifiNetwork.getDefaultInstance() : protocol$WifiNetwork;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeMessage(1, getNetwork());
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
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getNetwork());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$WifiForgetRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$WifiForgetRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$WifiForgetRequest, Builder> implements Protocol$WifiForgetRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$WifiForgetRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$WifiForgetRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasNetwork()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!getNetwork().isInitialized()) {
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
                Protocol$WifiForgetRequest protocol$WifiForgetRequest = (Protocol$WifiForgetRequest) obj2;
                this.network_ = (Protocol$WifiNetwork) visitor.visitMessage(this.network_, protocol$WifiForgetRequest.network_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$WifiForgetRequest.bitField0_;
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
                                Protocol$WifiNetwork.Builder builder = (this.bitField0_ & 1) == 1 ? (Protocol$WifiNetwork.Builder) this.network_.toBuilder() : null;
                                this.network_ = (Protocol$WifiNetwork) codedInputStream.readMessage(Protocol$WifiNetwork.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((GeneratedMessageLite) this.network_);
                                    this.network_ = (Protocol$WifiNetwork) builder.buildPartial();
                                }
                                this.bitField0_ |= 1;
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
                    synchronized (Protocol$WifiForgetRequest.class) {
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
