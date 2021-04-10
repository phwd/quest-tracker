package android.service.usb;

import android.service.usb.UsbProfileGroupSettingsManagerProto;
import android.service.usb.UsbUserSettingsManagerProto;
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

public final class UsbSettingsManagerProto extends GeneratedMessageLite<UsbSettingsManagerProto, Builder> implements UsbSettingsManagerProtoOrBuilder {
    private static final UsbSettingsManagerProto DEFAULT_INSTANCE = new UsbSettingsManagerProto();
    private static volatile Parser<UsbSettingsManagerProto> PARSER = null;
    public static final int PROFILE_GROUP_SETTINGS_FIELD_NUMBER = 2;
    public static final int USER_SETTINGS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<UsbProfileGroupSettingsManagerProto> profileGroupSettings_ = emptyProtobufList();
    private Internal.ProtobufList<UsbUserSettingsManagerProto> userSettings_ = emptyProtobufList();

    private UsbSettingsManagerProto() {
    }

    @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
    public List<UsbUserSettingsManagerProto> getUserSettingsList() {
        return this.userSettings_;
    }

    public List<? extends UsbUserSettingsManagerProtoOrBuilder> getUserSettingsOrBuilderList() {
        return this.userSettings_;
    }

    @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
    public int getUserSettingsCount() {
        return this.userSettings_.size();
    }

    @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
    public UsbUserSettingsManagerProto getUserSettings(int index) {
        return this.userSettings_.get(index);
    }

    public UsbUserSettingsManagerProtoOrBuilder getUserSettingsOrBuilder(int index) {
        return this.userSettings_.get(index);
    }

    private void ensureUserSettingsIsMutable() {
        if (!this.userSettings_.isModifiable()) {
            this.userSettings_ = GeneratedMessageLite.mutableCopy(this.userSettings_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserSettings(int index, UsbUserSettingsManagerProto value) {
        if (value != null) {
            ensureUserSettingsIsMutable();
            this.userSettings_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserSettings(int index, UsbUserSettingsManagerProto.Builder builderForValue) {
        ensureUserSettingsIsMutable();
        this.userSettings_.set(index, (UsbUserSettingsManagerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(UsbUserSettingsManagerProto value) {
        if (value != null) {
            ensureUserSettingsIsMutable();
            this.userSettings_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(int index, UsbUserSettingsManagerProto value) {
        if (value != null) {
            ensureUserSettingsIsMutable();
            this.userSettings_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(UsbUserSettingsManagerProto.Builder builderForValue) {
        ensureUserSettingsIsMutable();
        this.userSettings_.add((UsbUserSettingsManagerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(int index, UsbUserSettingsManagerProto.Builder builderForValue) {
        ensureUserSettingsIsMutable();
        this.userSettings_.add(index, (UsbUserSettingsManagerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserSettings(Iterable<? extends UsbUserSettingsManagerProto> values) {
        ensureUserSettingsIsMutable();
        AbstractMessageLite.addAll(values, this.userSettings_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserSettings() {
        this.userSettings_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUserSettings(int index) {
        ensureUserSettingsIsMutable();
        this.userSettings_.remove(index);
    }

    @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
    public List<UsbProfileGroupSettingsManagerProto> getProfileGroupSettingsList() {
        return this.profileGroupSettings_;
    }

    public List<? extends UsbProfileGroupSettingsManagerProtoOrBuilder> getProfileGroupSettingsOrBuilderList() {
        return this.profileGroupSettings_;
    }

    @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
    public int getProfileGroupSettingsCount() {
        return this.profileGroupSettings_.size();
    }

    @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
    public UsbProfileGroupSettingsManagerProto getProfileGroupSettings(int index) {
        return this.profileGroupSettings_.get(index);
    }

    public UsbProfileGroupSettingsManagerProtoOrBuilder getProfileGroupSettingsOrBuilder(int index) {
        return this.profileGroupSettings_.get(index);
    }

    private void ensureProfileGroupSettingsIsMutable() {
        if (!this.profileGroupSettings_.isModifiable()) {
            this.profileGroupSettings_ = GeneratedMessageLite.mutableCopy(this.profileGroupSettings_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto value) {
        if (value != null) {
            ensureProfileGroupSettingsIsMutable();
            this.profileGroupSettings_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto.Builder builderForValue) {
        ensureProfileGroupSettingsIsMutable();
        this.profileGroupSettings_.set(index, (UsbProfileGroupSettingsManagerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProfileGroupSettings(UsbProfileGroupSettingsManagerProto value) {
        if (value != null) {
            ensureProfileGroupSettingsIsMutable();
            this.profileGroupSettings_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto value) {
        if (value != null) {
            ensureProfileGroupSettingsIsMutable();
            this.profileGroupSettings_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProfileGroupSettings(UsbProfileGroupSettingsManagerProto.Builder builderForValue) {
        ensureProfileGroupSettingsIsMutable();
        this.profileGroupSettings_.add((UsbProfileGroupSettingsManagerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto.Builder builderForValue) {
        ensureProfileGroupSettingsIsMutable();
        this.profileGroupSettings_.add(index, (UsbProfileGroupSettingsManagerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllProfileGroupSettings(Iterable<? extends UsbProfileGroupSettingsManagerProto> values) {
        ensureProfileGroupSettingsIsMutable();
        AbstractMessageLite.addAll(values, this.profileGroupSettings_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProfileGroupSettings() {
        this.profileGroupSettings_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeProfileGroupSettings(int index) {
        ensureProfileGroupSettingsIsMutable();
        this.profileGroupSettings_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.userSettings_.size(); i++) {
            output.writeMessage(1, this.userSettings_.get(i));
        }
        for (int i2 = 0; i2 < this.profileGroupSettings_.size(); i2++) {
            output.writeMessage(2, this.profileGroupSettings_.get(i2));
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
        for (int i = 0; i < this.userSettings_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.userSettings_.get(i));
        }
        for (int i2 = 0; i2 < this.profileGroupSettings_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.profileGroupSettings_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbSettingsManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbSettingsManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbSettingsManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbSettingsManagerProto, Builder> implements UsbSettingsManagerProtoOrBuilder {
        private Builder() {
            super(UsbSettingsManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
        public List<UsbUserSettingsManagerProto> getUserSettingsList() {
            return Collections.unmodifiableList(((UsbSettingsManagerProto) this.instance).getUserSettingsList());
        }

        @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
        public int getUserSettingsCount() {
            return ((UsbSettingsManagerProto) this.instance).getUserSettingsCount();
        }

        @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
        public UsbUserSettingsManagerProto getUserSettings(int index) {
            return ((UsbSettingsManagerProto) this.instance).getUserSettings(index);
        }

        public Builder setUserSettings(int index, UsbUserSettingsManagerProto value) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).setUserSettings((UsbSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setUserSettings(int index, UsbUserSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).setUserSettings((UsbSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUserSettings(UsbUserSettingsManagerProto value) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addUserSettings((UsbSettingsManagerProto) value);
            return this;
        }

        public Builder addUserSettings(int index, UsbUserSettingsManagerProto value) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addUserSettings((UsbSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addUserSettings(UsbUserSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addUserSettings((UsbSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addUserSettings(int index, UsbUserSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addUserSettings((UsbSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUserSettings(Iterable<? extends UsbUserSettingsManagerProto> values) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addAllUserSettings(values);
            return this;
        }

        public Builder clearUserSettings() {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).clearUserSettings();
            return this;
        }

        public Builder removeUserSettings(int index) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).removeUserSettings(index);
            return this;
        }

        @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
        public List<UsbProfileGroupSettingsManagerProto> getProfileGroupSettingsList() {
            return Collections.unmodifiableList(((UsbSettingsManagerProto) this.instance).getProfileGroupSettingsList());
        }

        @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
        public int getProfileGroupSettingsCount() {
            return ((UsbSettingsManagerProto) this.instance).getProfileGroupSettingsCount();
        }

        @Override // android.service.usb.UsbSettingsManagerProtoOrBuilder
        public UsbProfileGroupSettingsManagerProto getProfileGroupSettings(int index) {
            return ((UsbSettingsManagerProto) this.instance).getProfileGroupSettings(index);
        }

        public Builder setProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto value) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).setProfileGroupSettings((UsbSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder setProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).setProfileGroupSettings((UsbSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProfileGroupSettings(UsbProfileGroupSettingsManagerProto value) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addProfileGroupSettings((UsbSettingsManagerProto) value);
            return this;
        }

        public Builder addProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto value) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addProfileGroupSettings((UsbSettingsManagerProto) index, (int) value);
            return this;
        }

        public Builder addProfileGroupSettings(UsbProfileGroupSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addProfileGroupSettings((UsbSettingsManagerProto) builderForValue);
            return this;
        }

        public Builder addProfileGroupSettings(int index, UsbProfileGroupSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addProfileGroupSettings((UsbSettingsManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProfileGroupSettings(Iterable<? extends UsbProfileGroupSettingsManagerProto> values) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).addAllProfileGroupSettings(values);
            return this;
        }

        public Builder clearProfileGroupSettings() {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).clearProfileGroupSettings();
            return this;
        }

        public Builder removeProfileGroupSettings(int index) {
            copyOnWrite();
            ((UsbSettingsManagerProto) this.instance).removeProfileGroupSettings(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbSettingsManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.userSettings_.makeImmutable();
                this.profileGroupSettings_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbSettingsManagerProto other = (UsbSettingsManagerProto) arg1;
                this.userSettings_ = visitor.visitList(this.userSettings_, other.userSettings_);
                this.profileGroupSettings_ = visitor.visitList(this.profileGroupSettings_, other.profileGroupSettings_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.userSettings_.isModifiable()) {
                                this.userSettings_ = GeneratedMessageLite.mutableCopy(this.userSettings_);
                            }
                            this.userSettings_.add((UsbUserSettingsManagerProto) input.readMessage(UsbUserSettingsManagerProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            if (!this.profileGroupSettings_.isModifiable()) {
                                this.profileGroupSettings_ = GeneratedMessageLite.mutableCopy(this.profileGroupSettings_);
                            }
                            this.profileGroupSettings_.add((UsbProfileGroupSettingsManagerProto) input.readMessage(UsbProfileGroupSettingsManagerProto.parser(), extensionRegistry));
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
                    synchronized (UsbSettingsManagerProto.class) {
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

    public static UsbSettingsManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbSettingsManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
