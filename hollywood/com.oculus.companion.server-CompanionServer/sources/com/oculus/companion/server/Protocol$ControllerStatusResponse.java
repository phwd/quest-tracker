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

public final class Protocol$ControllerStatusResponse extends GeneratedMessageLite<Protocol$ControllerStatusResponse, Builder> implements Protocol$ControllerStatusResponseOrBuilder {
    private static final Protocol$ControllerStatusResponse DEFAULT_INSTANCE = new Protocol$ControllerStatusResponse();
    private static volatile Parser<Protocol$ControllerStatusResponse> PARSER;
    private int bitField0_;
    private int handedness_ = 0;
    private byte memoizedIsInitialized = -1;
    private Internal.ProtobufList<Protocol$Controller> pairedControllers_ = GeneratedMessageLite.emptyProtobufList();

    private Protocol$ControllerStatusResponse() {
    }

    public int getPairedControllersCount() {
        return this.pairedControllers_.size();
    }

    public Protocol$Controller getPairedControllers(int i) {
        return this.pairedControllers_.get(i);
    }

    private void ensurePairedControllersIsMutable() {
        if (!this.pairedControllers_.isModifiable()) {
            this.pairedControllers_ = GeneratedMessageLite.mutableCopy(this.pairedControllers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPairedControllers(Protocol$Controller protocol$Controller) {
        if (protocol$Controller != null) {
            ensurePairedControllersIsMutable();
            this.pairedControllers_.add(protocol$Controller);
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasHandedness() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHandedness(Protocol$ControllerHandedness protocol$ControllerHandedness) {
        if (protocol$ControllerHandedness != null) {
            this.bitField0_ |= 1;
            this.handedness_ = protocol$ControllerHandedness.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.pairedControllers_.size(); i++) {
            codedOutputStream.writeMessage(1, this.pairedControllers_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeEnum(2, this.handedness_);
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
        for (int i3 = 0; i3 < this.pairedControllers_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.pairedControllers_.get(i3));
        }
        if ((this.bitField0_ & 1) == 1) {
            i2 += CodedOutputStream.computeEnumSize(2, this.handedness_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ControllerStatusResponse, Builder> implements Protocol$ControllerStatusResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ControllerStatusResponse.DEFAULT_INSTANCE);
        }

        public Builder addPairedControllers(Protocol$Controller protocol$Controller) {
            copyOnWrite();
            ((Protocol$ControllerStatusResponse) this.instance).addPairedControllers(protocol$Controller);
            return this;
        }

        public Builder setHandedness(Protocol$ControllerHandedness protocol$ControllerHandedness) {
            copyOnWrite();
            ((Protocol$ControllerStatusResponse) this.instance).setHandedness(protocol$ControllerHandedness);
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
                return new Protocol$ControllerStatusResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                for (int i = 0; i < getPairedControllersCount(); i++) {
                    if (!getPairedControllers(i).isInitialized()) {
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
                this.pairedControllers_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$ControllerStatusResponse protocol$ControllerStatusResponse = (Protocol$ControllerStatusResponse) obj2;
                this.pairedControllers_ = visitor.visitList(this.pairedControllers_, protocol$ControllerStatusResponse.pairedControllers_);
                this.handedness_ = visitor.visitInt(hasHandedness(), this.handedness_, protocol$ControllerStatusResponse.hasHandedness(), protocol$ControllerStatusResponse.handedness_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$ControllerStatusResponse.bitField0_;
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
                                if (!this.pairedControllers_.isModifiable()) {
                                    this.pairedControllers_ = GeneratedMessageLite.mutableCopy(this.pairedControllers_);
                                }
                                this.pairedControllers_.add((Protocol$Controller) codedInputStream.readMessage(Protocol$Controller.parser(), extensionRegistryLite));
                            } else if (readTag == 16) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ControllerHandedness.forNumber(readEnum) == null) {
                                    super.mergeVarintField(2, readEnum);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.handedness_ = readEnum;
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
                    synchronized (Protocol$ControllerStatusResponse.class) {
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
