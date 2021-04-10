package android.service;

import android.service.NetworkInterfaceProto;
import android.service.NetworkStatsRecorderProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class NetworkStatsServiceDumpProto extends GeneratedMessageLite<NetworkStatsServiceDumpProto, Builder> implements NetworkStatsServiceDumpProtoOrBuilder {
    public static final int ACTIVE_INTERFACES_FIELD_NUMBER = 1;
    public static final int ACTIVE_UID_INTERFACES_FIELD_NUMBER = 2;
    private static final NetworkStatsServiceDumpProto DEFAULT_INSTANCE = new NetworkStatsServiceDumpProto();
    public static final int DEV_STATS_FIELD_NUMBER = 3;
    private static volatile Parser<NetworkStatsServiceDumpProto> PARSER = null;
    public static final int UID_STATS_FIELD_NUMBER = 5;
    public static final int UID_TAG_STATS_FIELD_NUMBER = 6;
    public static final int XT_STATS_FIELD_NUMBER = 4;
    private Internal.ProtobufList<NetworkInterfaceProto> activeInterfaces_ = emptyProtobufList();
    private Internal.ProtobufList<NetworkInterfaceProto> activeUidInterfaces_ = emptyProtobufList();
    private int bitField0_;
    private NetworkStatsRecorderProto devStats_;
    private NetworkStatsRecorderProto uidStats_;
    private NetworkStatsRecorderProto uidTagStats_;
    private NetworkStatsRecorderProto xtStats_;

    private NetworkStatsServiceDumpProto() {
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public List<NetworkInterfaceProto> getActiveInterfacesList() {
        return this.activeInterfaces_;
    }

    public List<? extends NetworkInterfaceProtoOrBuilder> getActiveInterfacesOrBuilderList() {
        return this.activeInterfaces_;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public int getActiveInterfacesCount() {
        return this.activeInterfaces_.size();
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public NetworkInterfaceProto getActiveInterfaces(int index) {
        return this.activeInterfaces_.get(index);
    }

    public NetworkInterfaceProtoOrBuilder getActiveInterfacesOrBuilder(int index) {
        return this.activeInterfaces_.get(index);
    }

    private void ensureActiveInterfacesIsMutable() {
        if (!this.activeInterfaces_.isModifiable()) {
            this.activeInterfaces_ = GeneratedMessageLite.mutableCopy(this.activeInterfaces_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveInterfaces(int index, NetworkInterfaceProto value) {
        if (value != null) {
            ensureActiveInterfacesIsMutable();
            this.activeInterfaces_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
        ensureActiveInterfacesIsMutable();
        this.activeInterfaces_.set(index, (NetworkInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInterfaces(NetworkInterfaceProto value) {
        if (value != null) {
            ensureActiveInterfacesIsMutable();
            this.activeInterfaces_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInterfaces(int index, NetworkInterfaceProto value) {
        if (value != null) {
            ensureActiveInterfacesIsMutable();
            this.activeInterfaces_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInterfaces(NetworkInterfaceProto.Builder builderForValue) {
        ensureActiveInterfacesIsMutable();
        this.activeInterfaces_.add((NetworkInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
        ensureActiveInterfacesIsMutable();
        this.activeInterfaces_.add(index, (NetworkInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveInterfaces(Iterable<? extends NetworkInterfaceProto> values) {
        ensureActiveInterfacesIsMutable();
        AbstractMessageLite.addAll(values, this.activeInterfaces_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveInterfaces() {
        this.activeInterfaces_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveInterfaces(int index) {
        ensureActiveInterfacesIsMutable();
        this.activeInterfaces_.remove(index);
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public List<NetworkInterfaceProto> getActiveUidInterfacesList() {
        return this.activeUidInterfaces_;
    }

    public List<? extends NetworkInterfaceProtoOrBuilder> getActiveUidInterfacesOrBuilderList() {
        return this.activeUidInterfaces_;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public int getActiveUidInterfacesCount() {
        return this.activeUidInterfaces_.size();
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public NetworkInterfaceProto getActiveUidInterfaces(int index) {
        return this.activeUidInterfaces_.get(index);
    }

    public NetworkInterfaceProtoOrBuilder getActiveUidInterfacesOrBuilder(int index) {
        return this.activeUidInterfaces_.get(index);
    }

    private void ensureActiveUidInterfacesIsMutable() {
        if (!this.activeUidInterfaces_.isModifiable()) {
            this.activeUidInterfaces_ = GeneratedMessageLite.mutableCopy(this.activeUidInterfaces_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveUidInterfaces(int index, NetworkInterfaceProto value) {
        if (value != null) {
            ensureActiveUidInterfacesIsMutable();
            this.activeUidInterfaces_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveUidInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
        ensureActiveUidInterfacesIsMutable();
        this.activeUidInterfaces_.set(index, (NetworkInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUidInterfaces(NetworkInterfaceProto value) {
        if (value != null) {
            ensureActiveUidInterfacesIsMutable();
            this.activeUidInterfaces_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUidInterfaces(int index, NetworkInterfaceProto value) {
        if (value != null) {
            ensureActiveUidInterfacesIsMutable();
            this.activeUidInterfaces_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUidInterfaces(NetworkInterfaceProto.Builder builderForValue) {
        ensureActiveUidInterfacesIsMutable();
        this.activeUidInterfaces_.add((NetworkInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUidInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
        ensureActiveUidInterfacesIsMutable();
        this.activeUidInterfaces_.add(index, (NetworkInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveUidInterfaces(Iterable<? extends NetworkInterfaceProto> values) {
        ensureActiveUidInterfacesIsMutable();
        AbstractMessageLite.addAll(values, this.activeUidInterfaces_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveUidInterfaces() {
        this.activeUidInterfaces_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveUidInterfaces(int index) {
        ensureActiveUidInterfacesIsMutable();
        this.activeUidInterfaces_.remove(index);
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public boolean hasDevStats() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public NetworkStatsRecorderProto getDevStats() {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.devStats_;
        return networkStatsRecorderProto == null ? NetworkStatsRecorderProto.getDefaultInstance() : networkStatsRecorderProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevStats(NetworkStatsRecorderProto value) {
        if (value != null) {
            this.devStats_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevStats(NetworkStatsRecorderProto.Builder builderForValue) {
        this.devStats_ = (NetworkStatsRecorderProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDevStats(NetworkStatsRecorderProto value) {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.devStats_;
        if (networkStatsRecorderProto == null || networkStatsRecorderProto == NetworkStatsRecorderProto.getDefaultInstance()) {
            this.devStats_ = value;
        } else {
            this.devStats_ = (NetworkStatsRecorderProto) ((NetworkStatsRecorderProto.Builder) NetworkStatsRecorderProto.newBuilder(this.devStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDevStats() {
        this.devStats_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public boolean hasXtStats() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public NetworkStatsRecorderProto getXtStats() {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.xtStats_;
        return networkStatsRecorderProto == null ? NetworkStatsRecorderProto.getDefaultInstance() : networkStatsRecorderProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setXtStats(NetworkStatsRecorderProto value) {
        if (value != null) {
            this.xtStats_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setXtStats(NetworkStatsRecorderProto.Builder builderForValue) {
        this.xtStats_ = (NetworkStatsRecorderProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeXtStats(NetworkStatsRecorderProto value) {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.xtStats_;
        if (networkStatsRecorderProto == null || networkStatsRecorderProto == NetworkStatsRecorderProto.getDefaultInstance()) {
            this.xtStats_ = value;
        } else {
            this.xtStats_ = (NetworkStatsRecorderProto) ((NetworkStatsRecorderProto.Builder) NetworkStatsRecorderProto.newBuilder(this.xtStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearXtStats() {
        this.xtStats_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public boolean hasUidStats() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public NetworkStatsRecorderProto getUidStats() {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.uidStats_;
        return networkStatsRecorderProto == null ? NetworkStatsRecorderProto.getDefaultInstance() : networkStatsRecorderProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidStats(NetworkStatsRecorderProto value) {
        if (value != null) {
            this.uidStats_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidStats(NetworkStatsRecorderProto.Builder builderForValue) {
        this.uidStats_ = (NetworkStatsRecorderProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUidStats(NetworkStatsRecorderProto value) {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.uidStats_;
        if (networkStatsRecorderProto == null || networkStatsRecorderProto == NetworkStatsRecorderProto.getDefaultInstance()) {
            this.uidStats_ = value;
        } else {
            this.uidStats_ = (NetworkStatsRecorderProto) ((NetworkStatsRecorderProto.Builder) NetworkStatsRecorderProto.newBuilder(this.uidStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUidStats() {
        this.uidStats_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public boolean hasUidTagStats() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
    public NetworkStatsRecorderProto getUidTagStats() {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.uidTagStats_;
        return networkStatsRecorderProto == null ? NetworkStatsRecorderProto.getDefaultInstance() : networkStatsRecorderProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidTagStats(NetworkStatsRecorderProto value) {
        if (value != null) {
            this.uidTagStats_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidTagStats(NetworkStatsRecorderProto.Builder builderForValue) {
        this.uidTagStats_ = (NetworkStatsRecorderProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUidTagStats(NetworkStatsRecorderProto value) {
        NetworkStatsRecorderProto networkStatsRecorderProto = this.uidTagStats_;
        if (networkStatsRecorderProto == null || networkStatsRecorderProto == NetworkStatsRecorderProto.getDefaultInstance()) {
            this.uidTagStats_ = value;
        } else {
            this.uidTagStats_ = (NetworkStatsRecorderProto) ((NetworkStatsRecorderProto.Builder) NetworkStatsRecorderProto.newBuilder(this.uidTagStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUidTagStats() {
        this.uidTagStats_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.activeInterfaces_.size(); i++) {
            output.writeMessage(1, this.activeInterfaces_.get(i));
        }
        for (int i2 = 0; i2 < this.activeUidInterfaces_.size(); i2++) {
            output.writeMessage(2, this.activeUidInterfaces_.get(i2));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(3, getDevStats());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(4, getXtStats());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(5, getUidStats());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(6, getUidTagStats());
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        for (int i = 0; i < this.activeInterfaces_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.activeInterfaces_.get(i));
        }
        for (int i2 = 0; i2 < this.activeUidInterfaces_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.activeUidInterfaces_.get(i2));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(3, getDevStats());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(4, getXtStats());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(5, getUidStats());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(6, getUidTagStats());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkStatsServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkStatsServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkStatsServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkStatsServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkStatsServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkStatsServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkStatsServiceDumpProto, Builder> implements NetworkStatsServiceDumpProtoOrBuilder {
        private Builder() {
            super(NetworkStatsServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public List<NetworkInterfaceProto> getActiveInterfacesList() {
            return Collections.unmodifiableList(((NetworkStatsServiceDumpProto) this.instance).getActiveInterfacesList());
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public int getActiveInterfacesCount() {
            return ((NetworkStatsServiceDumpProto) this.instance).getActiveInterfacesCount();
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public NetworkInterfaceProto getActiveInterfaces(int index) {
            return ((NetworkStatsServiceDumpProto) this.instance).getActiveInterfaces(index);
        }

        public Builder setActiveInterfaces(int index, NetworkInterfaceProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setActiveInterfaces((NetworkStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setActiveInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setActiveInterfaces((NetworkStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveInterfaces(NetworkInterfaceProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveInterfaces((NetworkStatsServiceDumpProto) value);
            return this;
        }

        public Builder addActiveInterfaces(int index, NetworkInterfaceProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveInterfaces((NetworkStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addActiveInterfaces(NetworkInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveInterfaces((NetworkStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addActiveInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveInterfaces((NetworkStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveInterfaces(Iterable<? extends NetworkInterfaceProto> values) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addAllActiveInterfaces(values);
            return this;
        }

        public Builder clearActiveInterfaces() {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).clearActiveInterfaces();
            return this;
        }

        public Builder removeActiveInterfaces(int index) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).removeActiveInterfaces(index);
            return this;
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public List<NetworkInterfaceProto> getActiveUidInterfacesList() {
            return Collections.unmodifiableList(((NetworkStatsServiceDumpProto) this.instance).getActiveUidInterfacesList());
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public int getActiveUidInterfacesCount() {
            return ((NetworkStatsServiceDumpProto) this.instance).getActiveUidInterfacesCount();
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public NetworkInterfaceProto getActiveUidInterfaces(int index) {
            return ((NetworkStatsServiceDumpProto) this.instance).getActiveUidInterfaces(index);
        }

        public Builder setActiveUidInterfaces(int index, NetworkInterfaceProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setActiveUidInterfaces((NetworkStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setActiveUidInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setActiveUidInterfaces((NetworkStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveUidInterfaces(NetworkInterfaceProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveUidInterfaces((NetworkStatsServiceDumpProto) value);
            return this;
        }

        public Builder addActiveUidInterfaces(int index, NetworkInterfaceProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveUidInterfaces((NetworkStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addActiveUidInterfaces(NetworkInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveUidInterfaces((NetworkStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addActiveUidInterfaces(int index, NetworkInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addActiveUidInterfaces((NetworkStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveUidInterfaces(Iterable<? extends NetworkInterfaceProto> values) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).addAllActiveUidInterfaces(values);
            return this;
        }

        public Builder clearActiveUidInterfaces() {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).clearActiveUidInterfaces();
            return this;
        }

        public Builder removeActiveUidInterfaces(int index) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).removeActiveUidInterfaces(index);
            return this;
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public boolean hasDevStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).hasDevStats();
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public NetworkStatsRecorderProto getDevStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).getDevStats();
        }

        public Builder setDevStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setDevStats((NetworkStatsServiceDumpProto) value);
            return this;
        }

        public Builder setDevStats(NetworkStatsRecorderProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setDevStats((NetworkStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeDevStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).mergeDevStats(value);
            return this;
        }

        public Builder clearDevStats() {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).clearDevStats();
            return this;
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public boolean hasXtStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).hasXtStats();
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public NetworkStatsRecorderProto getXtStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).getXtStats();
        }

        public Builder setXtStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setXtStats((NetworkStatsServiceDumpProto) value);
            return this;
        }

        public Builder setXtStats(NetworkStatsRecorderProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setXtStats((NetworkStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeXtStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).mergeXtStats(value);
            return this;
        }

        public Builder clearXtStats() {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).clearXtStats();
            return this;
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public boolean hasUidStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).hasUidStats();
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public NetworkStatsRecorderProto getUidStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).getUidStats();
        }

        public Builder setUidStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setUidStats((NetworkStatsServiceDumpProto) value);
            return this;
        }

        public Builder setUidStats(NetworkStatsRecorderProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setUidStats((NetworkStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeUidStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).mergeUidStats(value);
            return this;
        }

        public Builder clearUidStats() {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).clearUidStats();
            return this;
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public boolean hasUidTagStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).hasUidTagStats();
        }

        @Override // android.service.NetworkStatsServiceDumpProtoOrBuilder
        public NetworkStatsRecorderProto getUidTagStats() {
            return ((NetworkStatsServiceDumpProto) this.instance).getUidTagStats();
        }

        public Builder setUidTagStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setUidTagStats((NetworkStatsServiceDumpProto) value);
            return this;
        }

        public Builder setUidTagStats(NetworkStatsRecorderProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).setUidTagStats((NetworkStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeUidTagStats(NetworkStatsRecorderProto value) {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).mergeUidTagStats(value);
            return this;
        }

        public Builder clearUidTagStats() {
            copyOnWrite();
            ((NetworkStatsServiceDumpProto) this.instance).clearUidTagStats();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkStatsServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.activeInterfaces_.makeImmutable();
                this.activeUidInterfaces_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkStatsServiceDumpProto other = (NetworkStatsServiceDumpProto) arg1;
                this.activeInterfaces_ = visitor.visitList(this.activeInterfaces_, other.activeInterfaces_);
                this.activeUidInterfaces_ = visitor.visitList(this.activeUidInterfaces_, other.activeUidInterfaces_);
                this.devStats_ = (NetworkStatsRecorderProto) visitor.visitMessage(this.devStats_, other.devStats_);
                this.xtStats_ = (NetworkStatsRecorderProto) visitor.visitMessage(this.xtStats_, other.xtStats_);
                this.uidStats_ = (NetworkStatsRecorderProto) visitor.visitMessage(this.uidStats_, other.uidStats_);
                this.uidTagStats_ = (NetworkStatsRecorderProto) visitor.visitMessage(this.uidTagStats_, other.uidTagStats_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            if (!this.activeInterfaces_.isModifiable()) {
                                this.activeInterfaces_ = GeneratedMessageLite.mutableCopy(this.activeInterfaces_);
                            }
                            this.activeInterfaces_.add((NetworkInterfaceProto) input.readMessage(NetworkInterfaceProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            if (!this.activeUidInterfaces_.isModifiable()) {
                                this.activeUidInterfaces_ = GeneratedMessageLite.mutableCopy(this.activeUidInterfaces_);
                            }
                            this.activeUidInterfaces_.add((NetworkInterfaceProto) input.readMessage(NetworkInterfaceProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            NetworkStatsRecorderProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (NetworkStatsRecorderProto.Builder) this.devStats_.toBuilder();
                            }
                            this.devStats_ = (NetworkStatsRecorderProto) input.readMessage(NetworkStatsRecorderProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.devStats_);
                                this.devStats_ = (NetworkStatsRecorderProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 34) {
                            NetworkStatsRecorderProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (NetworkStatsRecorderProto.Builder) this.xtStats_.toBuilder();
                            }
                            this.xtStats_ = (NetworkStatsRecorderProto) input.readMessage(NetworkStatsRecorderProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.xtStats_);
                                this.xtStats_ = (NetworkStatsRecorderProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 42) {
                            NetworkStatsRecorderProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (NetworkStatsRecorderProto.Builder) this.uidStats_.toBuilder();
                            }
                            this.uidStats_ = (NetworkStatsRecorderProto) input.readMessage(NetworkStatsRecorderProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.uidStats_);
                                this.uidStats_ = (NetworkStatsRecorderProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 50) {
                            NetworkStatsRecorderProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder4 = (NetworkStatsRecorderProto.Builder) this.uidTagStats_.toBuilder();
                            }
                            this.uidTagStats_ = (NetworkStatsRecorderProto) input.readMessage(NetworkStatsRecorderProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.uidTagStats_);
                                this.uidTagStats_ = (NetworkStatsRecorderProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (NetworkStatsServiceDumpProto.class) {
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

    public static NetworkStatsServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkStatsServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
