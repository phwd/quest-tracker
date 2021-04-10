package android.service.usb;

import android.service.usb.UsbSettingsAccessoryPreferenceProto;
import android.service.usb.UsbSettingsDevicePreferenceProto;
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

public final class UsbProfileGroupSettingsManagerProto extends GeneratedMessageLite<UsbProfileGroupSettingsManagerProto, Builder> implements UsbProfileGroupSettingsManagerProtoOrBuilder {
    public static final int ACCESSORY_PREFERENCES_FIELD_NUMBER = 3;
    private static final UsbProfileGroupSettingsManagerProto DEFAULT_INSTANCE = new UsbProfileGroupSettingsManagerProto();
    public static final int DEVICE_PREFERENCES_FIELD_NUMBER = 2;
    public static final int PARENT_USER_ID_FIELD_NUMBER = 1;
    private static volatile Parser<UsbProfileGroupSettingsManagerProto> PARSER;
    private Internal.ProtobufList<UsbSettingsAccessoryPreferenceProto> accessoryPreferences_ = emptyProtobufList();
    private int bitField0_;
    private Internal.ProtobufList<UsbSettingsDevicePreferenceProto> devicePreferences_ = emptyProtobufList();
    private int parentUserId_ = 0;

    private UsbProfileGroupSettingsManagerProto() {
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public boolean hasParentUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public int getParentUserId() {
        return this.parentUserId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParentUserId(int value) {
        this.bitField0_ |= 1;
        this.parentUserId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParentUserId() {
        this.bitField0_ &= -2;
        this.parentUserId_ = 0;
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public List<UsbSettingsDevicePreferenceProto> getDevicePreferencesList() {
        return this.devicePreferences_;
    }

    public List<? extends UsbSettingsDevicePreferenceProtoOrBuilder> getDevicePreferencesOrBuilderList() {
        return this.devicePreferences_;
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public int getDevicePreferencesCount() {
        return this.devicePreferences_.size();
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public UsbSettingsDevicePreferenceProto getDevicePreferences(int index) {
        return this.devicePreferences_.get(index);
    }

    public UsbSettingsDevicePreferenceProtoOrBuilder getDevicePreferencesOrBuilder(int index) {
        return this.devicePreferences_.get(index);
    }

    private void ensureDevicePreferencesIsMutable() {
        if (!this.devicePreferences_.isModifiable()) {
            this.devicePreferences_ = GeneratedMessageLite.mutableCopy(this.devicePreferences_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevicePreferences(int index, UsbSettingsDevicePreferenceProto value) {
        if (value != null) {
            ensureDevicePreferencesIsMutable();
            this.devicePreferences_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevicePreferences(int index, UsbSettingsDevicePreferenceProto.Builder builderForValue) {
        ensureDevicePreferencesIsMutable();
        this.devicePreferences_.set(index, (UsbSettingsDevicePreferenceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePreferences(UsbSettingsDevicePreferenceProto value) {
        if (value != null) {
            ensureDevicePreferencesIsMutable();
            this.devicePreferences_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePreferences(int index, UsbSettingsDevicePreferenceProto value) {
        if (value != null) {
            ensureDevicePreferencesIsMutable();
            this.devicePreferences_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePreferences(UsbSettingsDevicePreferenceProto.Builder builderForValue) {
        ensureDevicePreferencesIsMutable();
        this.devicePreferences_.add((UsbSettingsDevicePreferenceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevicePreferences(int index, UsbSettingsDevicePreferenceProto.Builder builderForValue) {
        ensureDevicePreferencesIsMutable();
        this.devicePreferences_.add(index, (UsbSettingsDevicePreferenceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDevicePreferences(Iterable<? extends UsbSettingsDevicePreferenceProto> values) {
        ensureDevicePreferencesIsMutable();
        AbstractMessageLite.addAll(values, this.devicePreferences_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDevicePreferences() {
        this.devicePreferences_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDevicePreferences(int index) {
        ensureDevicePreferencesIsMutable();
        this.devicePreferences_.remove(index);
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public List<UsbSettingsAccessoryPreferenceProto> getAccessoryPreferencesList() {
        return this.accessoryPreferences_;
    }

    public List<? extends UsbSettingsAccessoryPreferenceProtoOrBuilder> getAccessoryPreferencesOrBuilderList() {
        return this.accessoryPreferences_;
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public int getAccessoryPreferencesCount() {
        return this.accessoryPreferences_.size();
    }

    @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
    public UsbSettingsAccessoryPreferenceProto getAccessoryPreferences(int index) {
        return this.accessoryPreferences_.get(index);
    }

    public UsbSettingsAccessoryPreferenceProtoOrBuilder getAccessoryPreferencesOrBuilder(int index) {
        return this.accessoryPreferences_.get(index);
    }

    private void ensureAccessoryPreferencesIsMutable() {
        if (!this.accessoryPreferences_.isModifiable()) {
            this.accessoryPreferences_ = GeneratedMessageLite.mutableCopy(this.accessoryPreferences_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto value) {
        if (value != null) {
            ensureAccessoryPreferencesIsMutable();
            this.accessoryPreferences_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto.Builder builderForValue) {
        ensureAccessoryPreferencesIsMutable();
        this.accessoryPreferences_.set(index, (UsbSettingsAccessoryPreferenceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPreferences(UsbSettingsAccessoryPreferenceProto value) {
        if (value != null) {
            ensureAccessoryPreferencesIsMutable();
            this.accessoryPreferences_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto value) {
        if (value != null) {
            ensureAccessoryPreferencesIsMutable();
            this.accessoryPreferences_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPreferences(UsbSettingsAccessoryPreferenceProto.Builder builderForValue) {
        ensureAccessoryPreferencesIsMutable();
        this.accessoryPreferences_.add((UsbSettingsAccessoryPreferenceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto.Builder builderForValue) {
        ensureAccessoryPreferencesIsMutable();
        this.accessoryPreferences_.add(index, (UsbSettingsAccessoryPreferenceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAccessoryPreferences(Iterable<? extends UsbSettingsAccessoryPreferenceProto> values) {
        ensureAccessoryPreferencesIsMutable();
        AbstractMessageLite.addAll(values, this.accessoryPreferences_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAccessoryPreferences() {
        this.accessoryPreferences_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAccessoryPreferences(int index) {
        ensureAccessoryPreferencesIsMutable();
        this.accessoryPreferences_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.parentUserId_);
        }
        for (int i = 0; i < this.devicePreferences_.size(); i++) {
            output.writeMessage(2, this.devicePreferences_.get(i));
        }
        for (int i2 = 0; i2 < this.accessoryPreferences_.size(); i2++) {
            output.writeMessage(3, this.accessoryPreferences_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.parentUserId_);
        }
        for (int i = 0; i < this.devicePreferences_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.devicePreferences_.get(i));
        }
        for (int i2 = 0; i2 < this.accessoryPreferences_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.accessoryPreferences_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbProfileGroupSettingsManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbProfileGroupSettingsManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbProfileGroupSettingsManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbProfileGroupSettingsManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbProfileGroupSettingsManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbProfileGroupSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbProfileGroupSettingsManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbProfileGroupSettingsManagerProto, Builder> implements UsbProfileGroupSettingsManagerProtoOrBuilder {
        private Builder() {
            super(UsbProfileGroupSettingsManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public boolean hasParentUserId() {
            return ((UsbProfileGroupSettingsManagerProto) this.instance).hasParentUserId();
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public int getParentUserId() {
            return ((UsbProfileGroupSettingsManagerProto) this.instance).getParentUserId();
        }

        public Builder setParentUserId(int value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).setParentUserId(value);
            return this;
        }

        public Builder clearParentUserId() {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).clearParentUserId();
            return this;
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public List<UsbSettingsDevicePreferenceProto> getDevicePreferencesList() {
            return Collections.unmodifiableList(((UsbProfileGroupSettingsManagerProto) this.instance).getDevicePreferencesList());
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public int getDevicePreferencesCount() {
            return ((UsbProfileGroupSettingsManagerProto) this.instance).getDevicePreferencesCount();
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public UsbSettingsDevicePreferenceProto getDevicePreferences(int index) {
            return ((UsbProfileGroupSettingsManagerProto) this.instance).getDevicePreferences(index);
        }

        public Builder setDevicePreferences(int index, UsbSettingsDevicePreferenceProto value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).setDevicePreferences((UsbProfileGroupSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setDevicePreferences(int index, UsbSettingsDevicePreferenceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).setDevicePreferences((UsbProfileGroupSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDevicePreferences(UsbSettingsDevicePreferenceProto value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addDevicePreferences((UsbProfileGroupSettingsManagerProto) value);
            return this;
        }

        public Builder addDevicePreferences(int index, UsbSettingsDevicePreferenceProto value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addDevicePreferences((UsbProfileGroupSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addDevicePreferences(UsbSettingsDevicePreferenceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addDevicePreferences((UsbProfileGroupSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addDevicePreferences(int index, UsbSettingsDevicePreferenceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addDevicePreferences((UsbProfileGroupSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDevicePreferences(Iterable<? extends UsbSettingsDevicePreferenceProto> values) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addAllDevicePreferences(values);
            return this;
        }

        public Builder clearDevicePreferences() {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).clearDevicePreferences();
            return this;
        }

        public Builder removeDevicePreferences(int index) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).removeDevicePreferences(index);
            return this;
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public List<UsbSettingsAccessoryPreferenceProto> getAccessoryPreferencesList() {
            return Collections.unmodifiableList(((UsbProfileGroupSettingsManagerProto) this.instance).getAccessoryPreferencesList());
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public int getAccessoryPreferencesCount() {
            return ((UsbProfileGroupSettingsManagerProto) this.instance).getAccessoryPreferencesCount();
        }

        @Override // android.service.usb.UsbProfileGroupSettingsManagerProtoOrBuilder
        public UsbSettingsAccessoryPreferenceProto getAccessoryPreferences(int index) {
            return ((UsbProfileGroupSettingsManagerProto) this.instance).getAccessoryPreferences(index);
        }

        public Builder setAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).setAccessoryPreferences((UsbProfileGroupSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).setAccessoryPreferences((UsbProfileGroupSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAccessoryPreferences(UsbSettingsAccessoryPreferenceProto value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addAccessoryPreferences((UsbProfileGroupSettingsManagerProto) value);
            return this;
        }

        public Builder addAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto value) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addAccessoryPreferences((UsbProfileGroupSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addAccessoryPreferences(UsbSettingsAccessoryPreferenceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addAccessoryPreferences((UsbProfileGroupSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addAccessoryPreferences(int index, UsbSettingsAccessoryPreferenceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addAccessoryPreferences((UsbProfileGroupSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAccessoryPreferences(Iterable<? extends UsbSettingsAccessoryPreferenceProto> values) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).addAllAccessoryPreferences(values);
            return this;
        }

        public Builder clearAccessoryPreferences() {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).clearAccessoryPreferences();
            return this;
        }

        public Builder removeAccessoryPreferences(int index) {
            copyOnWrite();
            ((UsbProfileGroupSettingsManagerProto) this.instance).removeAccessoryPreferences(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbProfileGroupSettingsManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.devicePreferences_.makeImmutable();
                this.accessoryPreferences_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbProfileGroupSettingsManagerProto other = (UsbProfileGroupSettingsManagerProto) arg1;
                this.parentUserId_ = visitor.visitInt(hasParentUserId(), this.parentUserId_, other.hasParentUserId(), other.parentUserId_);
                this.devicePreferences_ = visitor.visitList(this.devicePreferences_, other.devicePreferences_);
                this.accessoryPreferences_ = visitor.visitList(this.accessoryPreferences_, other.accessoryPreferences_);
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
                            this.parentUserId_ = input.readInt32();
                        } else if (tag == 18) {
                            if (!this.devicePreferences_.isModifiable()) {
                                this.devicePreferences_ = GeneratedMessageLite.mutableCopy(this.devicePreferences_);
                            }
                            this.devicePreferences_.add((UsbSettingsDevicePreferenceProto) input.readMessage(UsbSettingsDevicePreferenceProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.accessoryPreferences_.isModifiable()) {
                                this.accessoryPreferences_ = GeneratedMessageLite.mutableCopy(this.accessoryPreferences_);
                            }
                            this.accessoryPreferences_.add((UsbSettingsAccessoryPreferenceProto) input.readMessage(UsbSettingsAccessoryPreferenceProto.parser(), extensionRegistry));
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
                    synchronized (UsbProfileGroupSettingsManagerProto.class) {
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

    public static UsbProfileGroupSettingsManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbProfileGroupSettingsManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
