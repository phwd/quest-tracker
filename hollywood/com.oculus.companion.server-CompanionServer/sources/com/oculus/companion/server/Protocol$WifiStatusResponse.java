package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.oculus.companion.server.Protocol$WifiNetwork;
import java.io.IOException;

public final class Protocol$WifiStatusResponse extends GeneratedMessageLite<Protocol$WifiStatusResponse, Builder> implements Protocol$WifiStatusResponseOrBuilder {
    private static final Protocol$WifiStatusResponse DEFAULT_INSTANCE = new Protocol$WifiStatusResponse();
    private static volatile Parser<Protocol$WifiStatusResponse> PARSER;
    private int bitField0_;
    private boolean enabled_ = false;
    private Internal.ProtobufList<Protocol$WifiNetwork> knownNetworks_ = GeneratedMessageLite.emptyProtobufList();
    private byte memoizedIsInitialized = -1;
    private Protocol$WifiNetwork network_;
    private int reachability_ = 1;

    private Protocol$WifiStatusResponse() {
    }

    public boolean hasEnabled() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(boolean z) {
        this.bitField0_ |= 1;
        this.enabled_ = z;
    }

    public boolean hasNetwork() {
        return (this.bitField0_ & 2) == 2;
    }

    public Protocol$WifiNetwork getNetwork() {
        Protocol$WifiNetwork protocol$WifiNetwork = this.network_;
        return protocol$WifiNetwork == null ? Protocol$WifiNetwork.getDefaultInstance() : protocol$WifiNetwork;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetwork(Protocol$WifiNetwork protocol$WifiNetwork) {
        if (protocol$WifiNetwork != null) {
            this.network_ = protocol$WifiNetwork;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasReachability() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReachability(Protocol$ReachabilityStatus protocol$ReachabilityStatus) {
        if (protocol$ReachabilityStatus != null) {
            this.bitField0_ |= 4;
            this.reachability_ = protocol$ReachabilityStatus.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public int getKnownNetworksCount() {
        return this.knownNetworks_.size();
    }

    public Protocol$WifiNetwork getKnownNetworks(int i) {
        return this.knownNetworks_.get(i);
    }

    private void ensureKnownNetworksIsMutable() {
        if (!this.knownNetworks_.isModifiable()) {
            this.knownNetworks_ = GeneratedMessageLite.mutableCopy(this.knownNetworks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKnownNetworks(Protocol$WifiNetwork protocol$WifiNetwork) {
        if (protocol$WifiNetwork != null) {
            ensureKnownNetworksIsMutable();
            this.knownNetworks_.add(protocol$WifiNetwork);
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.enabled_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(2, getNetwork());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(3, this.reachability_);
        }
        for (int i = 0; i < this.knownNetworks_.size(); i++) {
            codedOutputStream.writeMessage(4, this.knownNetworks_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeBoolSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBoolSize(1, this.enabled_) + 0 : 0;
        if ((this.bitField0_ & 2) == 2) {
            computeBoolSize += CodedOutputStream.computeMessageSize(2, getNetwork());
        }
        if ((this.bitField0_ & 4) == 4) {
            computeBoolSize += CodedOutputStream.computeEnumSize(3, this.reachability_);
        }
        for (int i2 = 0; i2 < this.knownNetworks_.size(); i2++) {
            computeBoolSize += CodedOutputStream.computeMessageSize(4, this.knownNetworks_.get(i2));
        }
        int serializedSize = computeBoolSize + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$WifiStatusResponse, Builder> implements Protocol$WifiStatusResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$WifiStatusResponse.DEFAULT_INSTANCE);
        }

        public Builder setEnabled(boolean z) {
            copyOnWrite();
            ((Protocol$WifiStatusResponse) this.instance).setEnabled(z);
            return this;
        }

        public Builder setNetwork(Protocol$WifiNetwork protocol$WifiNetwork) {
            copyOnWrite();
            ((Protocol$WifiStatusResponse) this.instance).setNetwork(protocol$WifiNetwork);
            return this;
        }

        public Builder setReachability(Protocol$ReachabilityStatus protocol$ReachabilityStatus) {
            copyOnWrite();
            ((Protocol$WifiStatusResponse) this.instance).setReachability(protocol$ReachabilityStatus);
            return this;
        }

        public Builder addKnownNetworks(Protocol$WifiNetwork protocol$WifiNetwork) {
            copyOnWrite();
            ((Protocol$WifiStatusResponse) this.instance).addKnownNetworks(protocol$WifiNetwork);
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
                return new Protocol$WifiStatusResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasNetwork() || getNetwork().isInitialized()) {
                    for (int i = 0; i < getKnownNetworksCount(); i++) {
                        if (!getKnownNetworks(i).isInitialized()) {
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
                }
                if (booleanValue) {
                    this.memoizedIsInitialized = 0;
                }
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                this.knownNetworks_.makeImmutable();
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$WifiStatusResponse protocol$WifiStatusResponse = (Protocol$WifiStatusResponse) obj2;
                this.enabled_ = visitor.visitBoolean(hasEnabled(), this.enabled_, protocol$WifiStatusResponse.hasEnabled(), protocol$WifiStatusResponse.enabled_);
                this.network_ = (Protocol$WifiNetwork) visitor.visitMessage(this.network_, protocol$WifiStatusResponse.network_);
                this.reachability_ = visitor.visitInt(hasReachability(), this.reachability_, protocol$WifiStatusResponse.hasReachability(), protocol$WifiStatusResponse.reachability_);
                this.knownNetworks_ = visitor.visitList(this.knownNetworks_, protocol$WifiStatusResponse.knownNetworks_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$WifiStatusResponse.bitField0_;
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
                                this.bitField0_ |= 1;
                                this.enabled_ = codedInputStream.readBool();
                            } else if (readTag == 18) {
                                Protocol$WifiNetwork.Builder builder = (this.bitField0_ & 2) == 2 ? (Protocol$WifiNetwork.Builder) this.network_.toBuilder() : null;
                                this.network_ = (Protocol$WifiNetwork) codedInputStream.readMessage(Protocol$WifiNetwork.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((GeneratedMessageLite) this.network_);
                                    this.network_ = (Protocol$WifiNetwork) builder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ReachabilityStatus.forNumber(readEnum) == null) {
                                    super.mergeVarintField(3, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.reachability_ = readEnum;
                                }
                            } else if (readTag == 34) {
                                if (!this.knownNetworks_.isModifiable()) {
                                    this.knownNetworks_ = GeneratedMessageLite.mutableCopy(this.knownNetworks_);
                                }
                                this.knownNetworks_.add((Protocol$WifiNetwork) codedInputStream.readMessage(Protocol$WifiNetwork.parser(), extensionRegistryLite));
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
                    synchronized (Protocol$WifiStatusResponse.class) {
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
