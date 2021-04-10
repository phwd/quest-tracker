package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$ControllerScanResponse extends GeneratedMessageLite<Protocol$ControllerScanResponse, Builder> implements Protocol$ControllerScanResponseOrBuilder {
    private static final Protocol$ControllerScanResponse DEFAULT_INSTANCE = new Protocol$ControllerScanResponse();
    private static volatile Parser<Protocol$ControllerScanResponse> PARSER;
    private Internal.ProtobufList<Protocol$Controller> controllers_ = GeneratedMessageLite.emptyProtobufList();
    private byte memoizedIsInitialized = -1;

    private Protocol$ControllerScanResponse() {
    }

    public int getControllersCount() {
        return this.controllers_.size();
    }

    public Protocol$Controller getControllers(int i) {
        return this.controllers_.get(i);
    }

    private void ensureControllersIsMutable() {
        if (!this.controllers_.isModifiable()) {
            this.controllers_ = GeneratedMessageLite.mutableCopy(this.controllers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addControllers(Protocol$Controller protocol$Controller) {
        if (protocol$Controller != null) {
            ensureControllersIsMutable();
            this.controllers_.add(protocol$Controller);
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.controllers_.size(); i++) {
            codedOutputStream.writeMessage(1, this.controllers_.get(i));
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
        for (int i3 = 0; i3 < this.controllers_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.controllers_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerScanResponse, Builder> implements Protocol$ControllerScanResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerScanResponse.DEFAULT_INSTANCE);
        }

        public Builder addControllers(Protocol$Controller protocol$Controller) {
            copyOnWrite();
            ((Protocol$ControllerScanResponse) this.instance).addControllers(protocol$Controller);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$ControllerScanResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                for (int i = 0; i < getControllersCount(); i++) {
                    if (!getControllers(i).isInitialized()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    }
                }
                if (booleanValue) {
                    this.memoizedIsInitialized = 1;
                }
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                this.controllers_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                this.controllers_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.controllers_, ((Protocol$ControllerScanResponse) obj2).controllers_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!this.controllers_.isModifiable()) {
                                    this.controllers_ = GeneratedMessageLite.mutableCopy(this.controllers_);
                                }
                                this.controllers_.add((Protocol$Controller) codedInputStream.readMessage(Protocol$Controller.parser(), extensionRegistryLite));
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
                    synchronized (Protocol$ControllerScanResponse.class) {
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
