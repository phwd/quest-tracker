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

public final class Protocol$VerifyMultipleControllersConnectableResponse extends GeneratedMessageLite<Protocol$VerifyMultipleControllersConnectableResponse, Builder> implements Protocol$VerifyMultipleControllersConnectableResponseOrBuilder {
    private static final Protocol$VerifyMultipleControllersConnectableResponse DEFAULT_INSTANCE = new Protocol$VerifyMultipleControllersConnectableResponse();
    private static volatile Parser<Protocol$VerifyMultipleControllersConnectableResponse> PARSER;
    private byte memoizedIsInitialized = -1;
    private Internal.ProtobufList<Protocol$ControllerDetectionState> states_ = GeneratedMessageLite.emptyProtobufList();

    private Protocol$VerifyMultipleControllersConnectableResponse() {
    }

    public int getStatesCount() {
        return this.states_.size();
    }

    public Protocol$ControllerDetectionState getStates(int i) {
        return this.states_.get(i);
    }

    private void ensureStatesIsMutable() {
        if (!this.states_.isModifiable()) {
            this.states_ = GeneratedMessageLite.mutableCopy(this.states_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(Protocol$ControllerDetectionState protocol$ControllerDetectionState) {
        if (protocol$ControllerDetectionState != null) {
            ensureStatesIsMutable();
            this.states_.add(protocol$ControllerDetectionState);
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.states_.size(); i++) {
            codedOutputStream.writeMessage(1, this.states_.get(i));
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
        for (int i3 = 0; i3 < this.states_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.states_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$VerifyMultipleControllersConnectableResponse, Builder> implements Protocol$VerifyMultipleControllersConnectableResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$VerifyMultipleControllersConnectableResponse.DEFAULT_INSTANCE);
        }

        public Builder addStates(Protocol$ControllerDetectionState protocol$ControllerDetectionState) {
            copyOnWrite();
            ((Protocol$VerifyMultipleControllersConnectableResponse) this.instance).addStates(protocol$ControllerDetectionState);
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
                return new Protocol$VerifyMultipleControllersConnectableResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                for (int i = 0; i < getStatesCount(); i++) {
                    if (!getStates(i).isInitialized()) {
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
                this.states_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                this.states_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.states_, ((Protocol$VerifyMultipleControllersConnectableResponse) obj2).states_);
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
                                if (!this.states_.isModifiable()) {
                                    this.states_ = GeneratedMessageLite.mutableCopy(this.states_);
                                }
                                this.states_.add((Protocol$ControllerDetectionState) codedInputStream.readMessage(Protocol$ControllerDetectionState.parser(), extensionRegistryLite));
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
                    synchronized (Protocol$VerifyMultipleControllersConnectableResponse.class) {
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
