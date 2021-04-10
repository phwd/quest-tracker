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

public final class Protocol$WifiScanResponse extends GeneratedMessageLite<Protocol$WifiScanResponse, Builder> implements Protocol$WifiScanResponseOrBuilder {
    private static final Protocol$WifiScanResponse DEFAULT_INSTANCE = new Protocol$WifiScanResponse();
    private static volatile Parser<Protocol$WifiScanResponse> PARSER;
    private int bitField0_;
    private String deviceMacAddress_ = "";
    private byte memoizedIsInitialized = -1;
    private Internal.ProtobufList<Protocol$WifiNetwork> networks_ = GeneratedMessageLite.emptyProtobufList();

    private Protocol$WifiScanResponse() {
    }

    public int getNetworksCount() {
        return this.networks_.size();
    }

    public Protocol$WifiNetwork getNetworks(int i) {
        return this.networks_.get(i);
    }

    private void ensureNetworksIsMutable() {
        if (!this.networks_.isModifiable()) {
            this.networks_ = GeneratedMessageLite.mutableCopy(this.networks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNetworks(Protocol$WifiNetwork protocol$WifiNetwork) {
        if (protocol$WifiNetwork != null) {
            ensureNetworksIsMutable();
            this.networks_.add(protocol$WifiNetwork);
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDeviceMacAddress() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getDeviceMacAddress() {
        return this.deviceMacAddress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceMacAddress(String str) {
        if (str != null) {
            this.bitField0_ |= 1;
            this.deviceMacAddress_ = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.networks_.size(); i++) {
            codedOutputStream.writeMessage(1, this.networks_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(2, getDeviceMacAddress());
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
        for (int i3 = 0; i3 < this.networks_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.networks_.get(i3));
        }
        if ((this.bitField0_ & 1) == 1) {
            i2 += CodedOutputStream.computeStringSize(2, getDeviceMacAddress());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$WifiScanResponse, Builder> implements Protocol$WifiScanResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$WifiScanResponse.DEFAULT_INSTANCE);
        }

        public Builder addNetworks(Protocol$WifiNetwork protocol$WifiNetwork) {
            copyOnWrite();
            ((Protocol$WifiScanResponse) this.instance).addNetworks(protocol$WifiNetwork);
            return this;
        }

        public Builder setDeviceMacAddress(String str) {
            copyOnWrite();
            ((Protocol$WifiScanResponse) this.instance).setDeviceMacAddress(str);
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
                return new Protocol$WifiScanResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                for (int i = 0; i < getNetworksCount(); i++) {
                    if (!getNetworks(i).isInitialized()) {
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
                this.networks_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$WifiScanResponse protocol$WifiScanResponse = (Protocol$WifiScanResponse) obj2;
                this.networks_ = visitor.visitList(this.networks_, protocol$WifiScanResponse.networks_);
                this.deviceMacAddress_ = visitor.visitString(hasDeviceMacAddress(), this.deviceMacAddress_, protocol$WifiScanResponse.hasDeviceMacAddress(), protocol$WifiScanResponse.deviceMacAddress_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$WifiScanResponse.bitField0_;
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
                                if (!this.networks_.isModifiable()) {
                                    this.networks_ = GeneratedMessageLite.mutableCopy(this.networks_);
                                }
                                this.networks_.add((Protocol$WifiNetwork) codedInputStream.readMessage(Protocol$WifiNetwork.parser(), extensionRegistryLite));
                            } else if (readTag == 18) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.deviceMacAddress_ = readString;
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
                    synchronized (Protocol$WifiScanResponse.class) {
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
