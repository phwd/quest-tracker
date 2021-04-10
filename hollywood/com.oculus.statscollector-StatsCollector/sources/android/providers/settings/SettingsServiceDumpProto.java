package android.providers.settings;

import android.providers.settings.GlobalSettingsProto;
import android.providers.settings.UserSettingsProto;
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

public final class SettingsServiceDumpProto extends GeneratedMessageLite<SettingsServiceDumpProto, Builder> implements SettingsServiceDumpProtoOrBuilder {
    private static final SettingsServiceDumpProto DEFAULT_INSTANCE = new SettingsServiceDumpProto();
    public static final int GLOBAL_SETTINGS_FIELD_NUMBER = 2;
    private static volatile Parser<SettingsServiceDumpProto> PARSER = null;
    public static final int USER_SETTINGS_FIELD_NUMBER = 1;
    private int bitField0_;
    private GlobalSettingsProto globalSettings_;
    private Internal.ProtobufList<UserSettingsProto> userSettings_ = emptyProtobufList();

    private SettingsServiceDumpProto() {
    }

    @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
    public List<UserSettingsProto> getUserSettingsList() {
        return this.userSettings_;
    }

    public List<? extends UserSettingsProtoOrBuilder> getUserSettingsOrBuilderList() {
        return this.userSettings_;
    }

    @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
    public int getUserSettingsCount() {
        return this.userSettings_.size();
    }

    @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
    public UserSettingsProto getUserSettings(int index) {
        return this.userSettings_.get(index);
    }

    public UserSettingsProtoOrBuilder getUserSettingsOrBuilder(int index) {
        return this.userSettings_.get(index);
    }

    private void ensureUserSettingsIsMutable() {
        if (!this.userSettings_.isModifiable()) {
            this.userSettings_ = GeneratedMessageLite.mutableCopy(this.userSettings_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserSettings(int index, UserSettingsProto value) {
        if (value != null) {
            ensureUserSettingsIsMutable();
            this.userSettings_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserSettings(int index, UserSettingsProto.Builder builderForValue) {
        ensureUserSettingsIsMutable();
        this.userSettings_.set(index, (UserSettingsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(UserSettingsProto value) {
        if (value != null) {
            ensureUserSettingsIsMutable();
            this.userSettings_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(int index, UserSettingsProto value) {
        if (value != null) {
            ensureUserSettingsIsMutable();
            this.userSettings_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(UserSettingsProto.Builder builderForValue) {
        ensureUserSettingsIsMutable();
        this.userSettings_.add((UserSettingsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserSettings(int index, UserSettingsProto.Builder builderForValue) {
        ensureUserSettingsIsMutable();
        this.userSettings_.add(index, (UserSettingsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserSettings(Iterable<? extends UserSettingsProto> values) {
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

    @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
    public boolean hasGlobalSettings() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
    public GlobalSettingsProto getGlobalSettings() {
        GlobalSettingsProto globalSettingsProto = this.globalSettings_;
        return globalSettingsProto == null ? GlobalSettingsProto.getDefaultInstance() : globalSettingsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalSettings(GlobalSettingsProto value) {
        if (value != null) {
            this.globalSettings_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlobalSettings(GlobalSettingsProto.Builder builderForValue) {
        this.globalSettings_ = (GlobalSettingsProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGlobalSettings(GlobalSettingsProto value) {
        GlobalSettingsProto globalSettingsProto = this.globalSettings_;
        if (globalSettingsProto == null || globalSettingsProto == GlobalSettingsProto.getDefaultInstance()) {
            this.globalSettings_ = value;
        } else {
            this.globalSettings_ = (GlobalSettingsProto) ((GlobalSettingsProto.Builder) GlobalSettingsProto.newBuilder(this.globalSettings_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlobalSettings() {
        this.globalSettings_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.userSettings_.size(); i++) {
            output.writeMessage(1, this.userSettings_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getGlobalSettings());
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
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getGlobalSettings());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SettingsServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingsServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingsServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingsServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingsServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingsServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SettingsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingsServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SettingsServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SettingsServiceDumpProto, Builder> implements SettingsServiceDumpProtoOrBuilder {
        private Builder() {
            super(SettingsServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
        public List<UserSettingsProto> getUserSettingsList() {
            return Collections.unmodifiableList(((SettingsServiceDumpProto) this.instance).getUserSettingsList());
        }

        @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
        public int getUserSettingsCount() {
            return ((SettingsServiceDumpProto) this.instance).getUserSettingsCount();
        }

        @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
        public UserSettingsProto getUserSettings(int index) {
            return ((SettingsServiceDumpProto) this.instance).getUserSettings(index);
        }

        public Builder setUserSettings(int index, UserSettingsProto value) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).setUserSettings((SettingsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setUserSettings(int index, UserSettingsProto.Builder builderForValue) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).setUserSettings((SettingsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUserSettings(UserSettingsProto value) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).addUserSettings((SettingsServiceDumpProto) value);
            return this;
        }

        public Builder addUserSettings(int index, UserSettingsProto value) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).addUserSettings((SettingsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addUserSettings(UserSettingsProto.Builder builderForValue) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).addUserSettings((SettingsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addUserSettings(int index, UserSettingsProto.Builder builderForValue) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).addUserSettings((SettingsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUserSettings(Iterable<? extends UserSettingsProto> values) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).addAllUserSettings(values);
            return this;
        }

        public Builder clearUserSettings() {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).clearUserSettings();
            return this;
        }

        public Builder removeUserSettings(int index) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).removeUserSettings(index);
            return this;
        }

        @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
        public boolean hasGlobalSettings() {
            return ((SettingsServiceDumpProto) this.instance).hasGlobalSettings();
        }

        @Override // android.providers.settings.SettingsServiceDumpProtoOrBuilder
        public GlobalSettingsProto getGlobalSettings() {
            return ((SettingsServiceDumpProto) this.instance).getGlobalSettings();
        }

        public Builder setGlobalSettings(GlobalSettingsProto value) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).setGlobalSettings((SettingsServiceDumpProto) value);
            return this;
        }

        public Builder setGlobalSettings(GlobalSettingsProto.Builder builderForValue) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).setGlobalSettings((SettingsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeGlobalSettings(GlobalSettingsProto value) {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).mergeGlobalSettings(value);
            return this;
        }

        public Builder clearGlobalSettings() {
            copyOnWrite();
            ((SettingsServiceDumpProto) this.instance).clearGlobalSettings();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SettingsServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.userSettings_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SettingsServiceDumpProto other = (SettingsServiceDumpProto) arg1;
                this.userSettings_ = visitor.visitList(this.userSettings_, other.userSettings_);
                this.globalSettings_ = (GlobalSettingsProto) visitor.visitMessage(this.globalSettings_, other.globalSettings_);
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
                            if (!this.userSettings_.isModifiable()) {
                                this.userSettings_ = GeneratedMessageLite.mutableCopy(this.userSettings_);
                            }
                            this.userSettings_.add((UserSettingsProto) input.readMessage(UserSettingsProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            GlobalSettingsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (GlobalSettingsProto.Builder) this.globalSettings_.toBuilder();
                            }
                            this.globalSettings_ = (GlobalSettingsProto) input.readMessage(GlobalSettingsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.globalSettings_);
                                this.globalSettings_ = (GlobalSettingsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (SettingsServiceDumpProto.class) {
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

    public static SettingsServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SettingsServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
