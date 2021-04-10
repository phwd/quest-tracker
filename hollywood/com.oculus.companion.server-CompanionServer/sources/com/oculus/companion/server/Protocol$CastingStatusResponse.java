package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.oculus.companion.server.Protocol$CastDevice;
import java.io.IOException;

public final class Protocol$CastingStatusResponse extends GeneratedMessageLite<Protocol$CastingStatusResponse, Builder> implements Protocol$CastingStatusResponseOrBuilder {
    private static final Protocol$CastingStatusResponse DEFAULT_INSTANCE = new Protocol$CastingStatusResponse();
    private static volatile Parser<Protocol$CastingStatusResponse> PARSER;
    private int bitField0_;
    private Protocol$CastDevice device_;

    private Protocol$CastingStatusResponse() {
    }

    public Protocol$CastDevice getDevice() {
        Protocol$CastDevice protocol$CastDevice = this.device_;
        return protocol$CastDevice == null ? Protocol$CastDevice.getDefaultInstance() : protocol$CastDevice;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevice(Protocol$CastDevice protocol$CastDevice) {
        if (protocol$CastDevice != null) {
            this.device_ = protocol$CastDevice;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeMessage(1, getDevice());
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
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getDevice());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$CastingStatusResponse, Builder> implements Protocol$CastingStatusResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$CastingStatusResponse.DEFAULT_INSTANCE);
        }

        public Builder setDevice(Protocol$CastDevice protocol$CastDevice) {
            copyOnWrite();
            ((Protocol$CastingStatusResponse) this.instance).setDevice(protocol$CastDevice);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$CastingStatusResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$CastingStatusResponse protocol$CastingStatusResponse = (Protocol$CastingStatusResponse) obj2;
                this.device_ = (Protocol$CastDevice) visitor.visitMessage(this.device_, protocol$CastingStatusResponse.device_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$CastingStatusResponse.bitField0_;
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
                                Protocol$CastDevice.Builder builder = (this.bitField0_ & 1) == 1 ? (Protocol$CastDevice.Builder) this.device_.toBuilder() : null;
                                this.device_ = (Protocol$CastDevice) codedInputStream.readMessage(Protocol$CastDevice.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((GeneratedMessageLite) this.device_);
                                    this.device_ = (Protocol$CastDevice) builder.buildPartial();
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
                    synchronized (Protocol$CastingStatusResponse.class) {
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
