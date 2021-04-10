package android.service.usb;

import android.service.usb.UsbAccessoryAttachedActivities;
import android.service.usb.UsbDeviceAttachedActivities;
import android.service.usb.UsbSettingsAccessoryPermissionProto;
import android.service.usb.UsbSettingsDevicePermissionProto;
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

public final class UsbUserSettingsManagerProto extends GeneratedMessageLite<UsbUserSettingsManagerProto, Builder> implements UsbUserSettingsManagerProtoOrBuilder {
    public static final int ACCESSORY_ATTACHED_ACTIVITIES_FIELD_NUMBER = 5;
    public static final int ACCESSORY_PERMISSIONS_FIELD_NUMBER = 3;
    private static final UsbUserSettingsManagerProto DEFAULT_INSTANCE = new UsbUserSettingsManagerProto();
    public static final int DEVICE_ATTACHED_ACTIVITIES_FIELD_NUMBER = 4;
    public static final int DEVICE_PERMISSIONS_FIELD_NUMBER = 2;
    private static volatile Parser<UsbUserSettingsManagerProto> PARSER = null;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private Internal.ProtobufList<UsbAccessoryAttachedActivities> accessoryAttachedActivities_ = emptyProtobufList();
    private Internal.ProtobufList<UsbSettingsAccessoryPermissionProto> accessoryPermissions_ = emptyProtobufList();
    private int bitField0_;
    private Internal.ProtobufList<UsbDeviceAttachedActivities> deviceAttachedActivities_ = emptyProtobufList();
    private Internal.ProtobufList<UsbSettingsDevicePermissionProto> devicePermissions_ = emptyProtobufList();
    private int userId_ = 0;

    private UsbUserSettingsManagerProto() {
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 1;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -2;
        this.userId_ = 0;
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public List<UsbSettingsDevicePermissionProto> getDevicePermissionsList() {
        return this.devicePermissions_;
    }

    public List<? extends UsbSettingsDevicePermissionProtoOrBuilder> getDevicePermissionsOrBuilderList() {
        return this.devicePermissions_;
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public int getDevicePermissionsCount() {
        return this.devicePermissions_.size();
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public UsbSettingsDevicePermissionProto getDevicePermissions(int index) {
        return this.devicePermissions_.get(index);
    }

    public UsbSettingsDevicePermissionProtoOrBuilder getDevicePermissionsOrBuilder(int index) {
        return this.devicePermissions_.get(index);
    }

    private void ensureDevicePermissionsIsMutable() {
        if (!this.devicePermissions_.isModifiable()) {
            this.devicePermissions_ = GeneratedMessageLite.mutableCopy(this.devicePermissions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevicePermissions(int index, UsbSettingsDevicePermissionProto value) {
        if (value != null) {
            ensureDevicePermissionsIsMutable();
            this.devicePermissions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevicePermissions(int index, UsbSettingsDevicePermissionProto.Builder builderForValue) {
        ensureDevicePermissionsIsMutable();
        this.devicePermissions_.set(index, (UsbSettingsDevicePermissionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePermissions(UsbSettingsDevicePermissionProto value) {
        if (value != null) {
            ensureDevicePermissionsIsMutable();
            this.devicePermissions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePermissions(int index, UsbSettingsDevicePermissionProto value) {
        if (value != null) {
            ensureDevicePermissionsIsMutable();
            this.devicePermissions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePermissions(UsbSettingsDevicePermissionProto.Builder builderForValue) {
        ensureDevicePermissionsIsMutable();
        this.devicePermissions_.add((UsbSettingsDevicePermissionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePermissions(int index, UsbSettingsDevicePermissionProto.Builder builderForValue) {
        ensureDevicePermissionsIsMutable();
        this.devicePermissions_.add(index, (UsbSettingsDevicePermissionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDevicePermissions(Iterable<? extends UsbSettingsDevicePermissionProto> values) {
        ensureDevicePermissionsIsMutable();
        AbstractMessageLite.addAll(values, this.devicePermissions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDevicePermissions() {
        this.devicePermissions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDevicePermissions(int index) {
        ensureDevicePermissionsIsMutable();
        this.devicePermissions_.remove(index);
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public List<UsbSettingsAccessoryPermissionProto> getAccessoryPermissionsList() {
        return this.accessoryPermissions_;
    }

    public List<? extends UsbSettingsAccessoryPermissionProtoOrBuilder> getAccessoryPermissionsOrBuilderList() {
        return this.accessoryPermissions_;
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public int getAccessoryPermissionsCount() {
        return this.accessoryPermissions_.size();
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public UsbSettingsAccessoryPermissionProto getAccessoryPermissions(int index) {
        return this.accessoryPermissions_.get(index);
    }

    public UsbSettingsAccessoryPermissionProtoOrBuilder getAccessoryPermissionsOrBuilder(int index) {
        return this.accessoryPermissions_.get(index);
    }

    private void ensureAccessoryPermissionsIsMutable() {
        if (!this.accessoryPermissions_.isModifiable()) {
            this.accessoryPermissions_ = GeneratedMessageLite.mutableCopy(this.accessoryPermissions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto value) {
        if (value != null) {
            ensureAccessoryPermissionsIsMutable();
            this.accessoryPermissions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto.Builder builderForValue) {
        ensureAccessoryPermissionsIsMutable();
        this.accessoryPermissions_.set(index, (UsbSettingsAccessoryPermissionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPermissions(UsbSettingsAccessoryPermissionProto value) {
        if (value != null) {
            ensureAccessoryPermissionsIsMutable();
            this.accessoryPermissions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto value) {
        if (value != null) {
            ensureAccessoryPermissionsIsMutable();
            this.accessoryPermissions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPermissions(UsbSettingsAccessoryPermissionProto.Builder builderForValue) {
        ensureAccessoryPermissionsIsMutable();
        this.accessoryPermissions_.add((UsbSettingsAccessoryPermissionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto.Builder builderForValue) {
        ensureAccessoryPermissionsIsMutable();
        this.accessoryPermissions_.add(index, (UsbSettingsAccessoryPermissionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAccessoryPermissions(Iterable<? extends UsbSettingsAccessoryPermissionProto> values) {
        ensureAccessoryPermissionsIsMutable();
        AbstractMessageLite.addAll(values, this.accessoryPermissions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAccessoryPermissions() {
        this.accessoryPermissions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAccessoryPermissions(int index) {
        ensureAccessoryPermissionsIsMutable();
        this.accessoryPermissions_.remove(index);
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public List<UsbDeviceAttachedActivities> getDeviceAttachedActivitiesList() {
        return this.deviceAttachedActivities_;
    }

    public List<? extends UsbDeviceAttachedActivitiesOrBuilder> getDeviceAttachedActivitiesOrBuilderList() {
        return this.deviceAttachedActivities_;
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public int getDeviceAttachedActivitiesCount() {
        return this.deviceAttachedActivities_.size();
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public UsbDeviceAttachedActivities getDeviceAttachedActivities(int index) {
        return this.deviceAttachedActivities_.get(index);
    }

    public UsbDeviceAttachedActivitiesOrBuilder getDeviceAttachedActivitiesOrBuilder(int index) {
        return this.deviceAttachedActivities_.get(index);
    }

    private void ensureDeviceAttachedActivitiesIsMutable() {
        if (!this.deviceAttachedActivities_.isModifiable()) {
            this.deviceAttachedActivities_ = GeneratedMessageLite.mutableCopy(this.deviceAttachedActivities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceAttachedActivities(int index, UsbDeviceAttachedActivities value) {
        if (value != null) {
            ensureDeviceAttachedActivitiesIsMutable();
            this.deviceAttachedActivities_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceAttachedActivities(int index, UsbDeviceAttachedActivities.Builder builderForValue) {
        ensureDeviceAttachedActivitiesIsMutable();
        this.deviceAttachedActivities_.set(index, (UsbDeviceAttachedActivities) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceAttachedActivities(UsbDeviceAttachedActivities value) {
        if (value != null) {
            ensureDeviceAttachedActivitiesIsMutable();
            this.deviceAttachedActivities_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceAttachedActivities(int index, UsbDeviceAttachedActivities value) {
        if (value != null) {
            ensureDeviceAttachedActivitiesIsMutable();
            this.deviceAttachedActivities_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceAttachedActivities(UsbDeviceAttachedActivities.Builder builderForValue) {
        ensureDeviceAttachedActivitiesIsMutable();
        this.deviceAttachedActivities_.add((UsbDeviceAttachedActivities) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceAttachedActivities(int index, UsbDeviceAttachedActivities.Builder builderForValue) {
        ensureDeviceAttachedActivitiesIsMutable();
        this.deviceAttachedActivities_.add(index, (UsbDeviceAttachedActivities) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeviceAttachedActivities(Iterable<? extends UsbDeviceAttachedActivities> values) {
        ensureDeviceAttachedActivitiesIsMutable();
        AbstractMessageLite.addAll(values, this.deviceAttachedActivities_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceAttachedActivities() {
        this.deviceAttachedActivities_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDeviceAttachedActivities(int index) {
        ensureDeviceAttachedActivitiesIsMutable();
        this.deviceAttachedActivities_.remove(index);
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public List<UsbAccessoryAttachedActivities> getAccessoryAttachedActivitiesList() {
        return this.accessoryAttachedActivities_;
    }

    public List<? extends UsbAccessoryAttachedActivitiesOrBuilder> getAccessoryAttachedActivitiesOrBuilderList() {
        return this.accessoryAttachedActivities_;
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public int getAccessoryAttachedActivitiesCount() {
        return this.accessoryAttachedActivities_.size();
    }

    @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
    public UsbAccessoryAttachedActivities getAccessoryAttachedActivities(int index) {
        return this.accessoryAttachedActivities_.get(index);
    }

    public UsbAccessoryAttachedActivitiesOrBuilder getAccessoryAttachedActivitiesOrBuilder(int index) {
        return this.accessoryAttachedActivities_.get(index);
    }

    private void ensureAccessoryAttachedActivitiesIsMutable() {
        if (!this.accessoryAttachedActivities_.isModifiable()) {
            this.accessoryAttachedActivities_ = GeneratedMessageLite.mutableCopy(this.accessoryAttachedActivities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities value) {
        if (value != null) {
            ensureAccessoryAttachedActivitiesIsMutable();
            this.accessoryAttachedActivities_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities.Builder builderForValue) {
        ensureAccessoryAttachedActivitiesIsMutable();
        this.accessoryAttachedActivities_.set(index, (UsbAccessoryAttachedActivities) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryAttachedActivities(UsbAccessoryAttachedActivities value) {
        if (value != null) {
            ensureAccessoryAttachedActivitiesIsMutable();
            this.accessoryAttachedActivities_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities value) {
        if (value != null) {
            ensureAccessoryAttachedActivitiesIsMutable();
            this.accessoryAttachedActivities_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryAttachedActivities(UsbAccessoryAttachedActivities.Builder builderForValue) {
        ensureAccessoryAttachedActivitiesIsMutable();
        this.accessoryAttachedActivities_.add((UsbAccessoryAttachedActivities) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities.Builder builderForValue) {
        ensureAccessoryAttachedActivitiesIsMutable();
        this.accessoryAttachedActivities_.add(index, (UsbAccessoryAttachedActivities) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAccessoryAttachedActivities(Iterable<? extends UsbAccessoryAttachedActivities> values) {
        ensureAccessoryAttachedActivitiesIsMutable();
        AbstractMessageLite.addAll(values, this.accessoryAttachedActivities_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAccessoryAttachedActivities() {
        this.accessoryAttachedActivities_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAccessoryAttachedActivities(int index) {
        ensureAccessoryAttachedActivitiesIsMutable();
        this.accessoryAttachedActivities_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        for (int i = 0; i < this.devicePermissions_.size(); i++) {
            output.writeMessage(2, this.devicePermissions_.get(i));
        }
        for (int i2 = 0; i2 < this.accessoryPermissions_.size(); i2++) {
            output.writeMessage(3, this.accessoryPermissions_.get(i2));
        }
        for (int i3 = 0; i3 < this.deviceAttachedActivities_.size(); i3++) {
            output.writeMessage(4, this.deviceAttachedActivities_.get(i3));
        }
        for (int i4 = 0; i4 < this.accessoryAttachedActivities_.size(); i4++) {
            output.writeMessage(5, this.accessoryAttachedActivities_.get(i4));
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
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
        }
        for (int i = 0; i < this.devicePermissions_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.devicePermissions_.get(i));
        }
        for (int i2 = 0; i2 < this.accessoryPermissions_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.accessoryPermissions_.get(i2));
        }
        for (int i3 = 0; i3 < this.deviceAttachedActivities_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.deviceAttachedActivities_.get(i3));
        }
        for (int i4 = 0; i4 < this.accessoryAttachedActivities_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.accessoryAttachedActivities_.get(i4));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbUserSettingsManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbUserSettingsManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbUserSettingsManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbUserSettingsManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbUserSettingsManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbUserSettingsManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbUserSettingsManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbUserSettingsManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbUserSettingsManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbUserSettingsManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbUserSettingsManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbUserSettingsManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbUserSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbUserSettingsManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbUserSettingsManagerProto, Builder> implements UsbUserSettingsManagerProtoOrBuilder {
        private Builder() {
            super(UsbUserSettingsManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public boolean hasUserId() {
            return ((UsbUserSettingsManagerProto) this.instance).hasUserId();
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public int getUserId() {
            return ((UsbUserSettingsManagerProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).clearUserId();
            return this;
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public List<UsbSettingsDevicePermissionProto> getDevicePermissionsList() {
            return Collections.unmodifiableList(((UsbUserSettingsManagerProto) this.instance).getDevicePermissionsList());
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public int getDevicePermissionsCount() {
            return ((UsbUserSettingsManagerProto) this.instance).getDevicePermissionsCount();
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public UsbSettingsDevicePermissionProto getDevicePermissions(int index) {
            return ((UsbUserSettingsManagerProto) this.instance).getDevicePermissions(index);
        }

        public Builder setDevicePermissions(int index, UsbSettingsDevicePermissionProto value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setDevicePermissions((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setDevicePermissions(int index, UsbSettingsDevicePermissionProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setDevicePermissions((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDevicePermissions(UsbSettingsDevicePermissionProto value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDevicePermissions((UsbUserSettingsManagerProto) value);
            return this;
        }

        public Builder addDevicePermissions(int index, UsbSettingsDevicePermissionProto value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDevicePermissions((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addDevicePermissions(UsbSettingsDevicePermissionProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDevicePermissions((UsbUserSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addDevicePermissions(int index, UsbSettingsDevicePermissionProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDevicePermissions((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDevicePermissions(Iterable<? extends UsbSettingsDevicePermissionProto> values) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAllDevicePermissions(values);
            return this;
        }

        public Builder clearDevicePermissions() {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).clearDevicePermissions();
            return this;
        }

        public Builder removeDevicePermissions(int index) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).removeDevicePermissions(index);
            return this;
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public List<UsbSettingsAccessoryPermissionProto> getAccessoryPermissionsList() {
            return Collections.unmodifiableList(((UsbUserSettingsManagerProto) this.instance).getAccessoryPermissionsList());
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public int getAccessoryPermissionsCount() {
            return ((UsbUserSettingsManagerProto) this.instance).getAccessoryPermissionsCount();
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public UsbSettingsAccessoryPermissionProto getAccessoryPermissions(int index) {
            return ((UsbUserSettingsManagerProto) this.instance).getAccessoryPermissions(index);
        }

        public Builder setAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setAccessoryPermissions((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setAccessoryPermissions((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAccessoryPermissions(UsbSettingsAccessoryPermissionProto value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryPermissions((UsbUserSettingsManagerProto) value);
            return this;
        }

        public Builder addAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryPermissions((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addAccessoryPermissions(UsbSettingsAccessoryPermissionProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryPermissions((UsbUserSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addAccessoryPermissions(int index, UsbSettingsAccessoryPermissionProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryPermissions((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAccessoryPermissions(Iterable<? extends UsbSettingsAccessoryPermissionProto> values) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAllAccessoryPermissions(values);
            return this;
        }

        public Builder clearAccessoryPermissions() {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).clearAccessoryPermissions();
            return this;
        }

        public Builder removeAccessoryPermissions(int index) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).removeAccessoryPermissions(index);
            return this;
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public List<UsbDeviceAttachedActivities> getDeviceAttachedActivitiesList() {
            return Collections.unmodifiableList(((UsbUserSettingsManagerProto) this.instance).getDeviceAttachedActivitiesList());
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public int getDeviceAttachedActivitiesCount() {
            return ((UsbUserSettingsManagerProto) this.instance).getDeviceAttachedActivitiesCount();
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public UsbDeviceAttachedActivities getDeviceAttachedActivities(int index) {
            return ((UsbUserSettingsManagerProto) this.instance).getDeviceAttachedActivities(index);
        }

        public Builder setDeviceAttachedActivities(int index, UsbDeviceAttachedActivities value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setDeviceAttachedActivities((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setDeviceAttachedActivities(int index, UsbDeviceAttachedActivities.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setDeviceAttachedActivities((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDeviceAttachedActivities(UsbDeviceAttachedActivities value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDeviceAttachedActivities((UsbUserSettingsManagerProto) value);
            return this;
        }

        public Builder addDeviceAttachedActivities(int index, UsbDeviceAttachedActivities value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDeviceAttachedActivities((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addDeviceAttachedActivities(UsbDeviceAttachedActivities.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDeviceAttachedActivities((UsbUserSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addDeviceAttachedActivities(int index, UsbDeviceAttachedActivities.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addDeviceAttachedActivities((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDeviceAttachedActivities(Iterable<? extends UsbDeviceAttachedActivities> values) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAllDeviceAttachedActivities(values);
            return this;
        }

        public Builder clearDeviceAttachedActivities() {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).clearDeviceAttachedActivities();
            return this;
        }

        public Builder removeDeviceAttachedActivities(int index) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).removeDeviceAttachedActivities(index);
            return this;
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public List<UsbAccessoryAttachedActivities> getAccessoryAttachedActivitiesList() {
            return Collections.unmodifiableList(((UsbUserSettingsManagerProto) this.instance).getAccessoryAttachedActivitiesList());
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public int getAccessoryAttachedActivitiesCount() {
            return ((UsbUserSettingsManagerProto) this.instance).getAccessoryAttachedActivitiesCount();
        }

        @Override // android.service.usb.UsbUserSettingsManagerProtoOrBuilder
        public UsbAccessoryAttachedActivities getAccessoryAttachedActivities(int index) {
            return ((UsbUserSettingsManagerProto) this.instance).getAccessoryAttachedActivities(index);
        }

        public Builder setAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setAccessoryAttachedActivities((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).setAccessoryAttachedActivities((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAccessoryAttachedActivities(UsbAccessoryAttachedActivities value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryAttachedActivities((UsbUserSettingsManagerProto) value);
            return this;
        }

        public Builder addAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities value) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryAttachedActivities((UsbUserSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addAccessoryAttachedActivities(UsbAccessoryAttachedActivities.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryAttachedActivities((UsbUserSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addAccessoryAttachedActivities(int index, UsbAccessoryAttachedActivities.Builder builderForValue) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAccessoryAttachedActivities((UsbUserSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAccessoryAttachedActivities(Iterable<? extends UsbAccessoryAttachedActivities> values) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).addAllAccessoryAttachedActivities(values);
            return this;
        }

        public Builder clearAccessoryAttachedActivities() {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).clearAccessoryAttachedActivities();
            return this;
        }

        public Builder removeAccessoryAttachedActivities(int index) {
            copyOnWrite();
            ((UsbUserSettingsManagerProto) this.instance).removeAccessoryAttachedActivities(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbUserSettingsManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.devicePermissions_.makeImmutable();
                this.accessoryPermissions_.makeImmutable();
                this.deviceAttachedActivities_.makeImmutable();
                this.accessoryAttachedActivities_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbUserSettingsManagerProto other = (UsbUserSettingsManagerProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.devicePermissions_ = visitor.visitList(this.devicePermissions_, other.devicePermissions_);
                this.accessoryPermissions_ = visitor.visitList(this.accessoryPermissions_, other.accessoryPermissions_);
                this.deviceAttachedActivities_ = visitor.visitList(this.deviceAttachedActivities_, other.deviceAttachedActivities_);
                this.accessoryAttachedActivities_ = visitor.visitList(this.accessoryAttachedActivities_, other.accessoryAttachedActivities_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.userId_ = input.readInt32();
                        } else if (tag == 18) {
                            if (!this.devicePermissions_.isModifiable()) {
                                this.devicePermissions_ = GeneratedMessageLite.mutableCopy(this.devicePermissions_);
                            }
                            this.devicePermissions_.add((UsbSettingsDevicePermissionProto) input.readMessage(UsbSettingsDevicePermissionProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.accessoryPermissions_.isModifiable()) {
                                this.accessoryPermissions_ = GeneratedMessageLite.mutableCopy(this.accessoryPermissions_);
                            }
                            this.accessoryPermissions_.add((UsbSettingsAccessoryPermissionProto) input.readMessage(UsbSettingsAccessoryPermissionProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.deviceAttachedActivities_.isModifiable()) {
                                this.deviceAttachedActivities_ = GeneratedMessageLite.mutableCopy(this.deviceAttachedActivities_);
                            }
                            this.deviceAttachedActivities_.add((UsbDeviceAttachedActivities) input.readMessage(UsbDeviceAttachedActivities.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            if (!this.accessoryAttachedActivities_.isModifiable()) {
                                this.accessoryAttachedActivities_ = GeneratedMessageLite.mutableCopy(this.accessoryAttachedActivities_);
                            }
                            this.accessoryAttachedActivities_.add((UsbAccessoryAttachedActivities) input.readMessage(UsbAccessoryAttachedActivities.parser(), extensionRegistry));
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
                    synchronized (UsbUserSettingsManagerProto.class) {
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

    public static UsbUserSettingsManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbUserSettingsManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
