package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$DiscoverCastingDevicesResponse extends GeneratedMessageLite<Protocol$DiscoverCastingDevicesResponse, Builder> implements Protocol$DiscoverCastingDevicesResponseOrBuilder {
    private static final Protocol$DiscoverCastingDevicesResponse DEFAULT_INSTANCE = new Protocol$DiscoverCastingDevicesResponse();
    private static volatile Parser<Protocol$DiscoverCastingDevicesResponse> PARSER;
    private Internal.ProtobufList<Protocol$CastDevice> device_ = GeneratedMessageLite.emptyProtobufList();

    private Protocol$DiscoverCastingDevicesResponse() {
    }

    private void ensureDeviceIsMutable() {
        if (!this.device_.isModifiable()) {
            this.device_ = GeneratedMessageLite.mutableCopy(this.device_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDevice(Iterable<? extends Protocol$CastDevice> iterable) {
        ensureDeviceIsMutable();
        AbstractMessageLite.addAll(iterable, this.device_);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.device_.size(); i++) {
            codedOutputStream.writeMessage(1, this.device_.get(i));
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
        for (int i3 = 0; i3 < this.device_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.device_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$DiscoverCastingDevicesResponse, Builder> implements Protocol$DiscoverCastingDevicesResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$DiscoverCastingDevicesResponse.DEFAULT_INSTANCE);
        }

        public Builder addAllDevice(Iterable<? extends Protocol$CastDevice> iterable) {
            copyOnWrite();
            ((Protocol$DiscoverCastingDevicesResponse) this.instance).addAllDevice(iterable);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$DiscoverCastingDevicesResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                this.device_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                this.device_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.device_, ((Protocol$DiscoverCastingDevicesResponse) obj2).device_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                                if (!this.device_.isModifiable()) {
                                    this.device_ = GeneratedMessageLite.mutableCopy(this.device_);
                                }
                                this.device_.add((Protocol$CastDevice) codedInputStream.readMessage(Protocol$CastDevice.parser(), extensionRegistryLite));
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
                    synchronized (Protocol$DiscoverCastingDevicesResponse.class) {
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
