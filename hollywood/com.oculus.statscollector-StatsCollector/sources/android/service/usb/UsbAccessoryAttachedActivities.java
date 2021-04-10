package android.service.usb;

import android.content.ComponentNameProto;
import android.service.usb.UsbAccessoryFilterProto;
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

public final class UsbAccessoryAttachedActivities extends GeneratedMessageLite<UsbAccessoryAttachedActivities, Builder> implements UsbAccessoryAttachedActivitiesOrBuilder {
    public static final int ACTIVITY_FIELD_NUMBER = 1;
    private static final UsbAccessoryAttachedActivities DEFAULT_INSTANCE = new UsbAccessoryAttachedActivities();
    public static final int FILTERS_FIELD_NUMBER = 2;
    private static volatile Parser<UsbAccessoryAttachedActivities> PARSER;
    private ComponentNameProto activity_;
    private int bitField0_;
    private Internal.ProtobufList<UsbAccessoryFilterProto> filters_ = emptyProtobufList();

    private UsbAccessoryAttachedActivities() {
    }

    @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
    public boolean hasActivity() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
    public ComponentNameProto getActivity() {
        ComponentNameProto componentNameProto = this.activity_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivity(ComponentNameProto value) {
        if (value != null) {
            this.activity_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivity(ComponentNameProto.Builder builderForValue) {
        this.activity_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeActivity(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.activity_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.activity_ = value;
        } else {
            this.activity_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.activity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivity() {
        this.activity_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
    public List<UsbAccessoryFilterProto> getFiltersList() {
        return this.filters_;
    }

    public List<? extends UsbAccessoryFilterProtoOrBuilder> getFiltersOrBuilderList() {
        return this.filters_;
    }

    @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
    public int getFiltersCount() {
        return this.filters_.size();
    }

    @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
    public UsbAccessoryFilterProto getFilters(int index) {
        return this.filters_.get(index);
    }

    public UsbAccessoryFilterProtoOrBuilder getFiltersOrBuilder(int index) {
        return this.filters_.get(index);
    }

    private void ensureFiltersIsMutable() {
        if (!this.filters_.isModifiable()) {
            this.filters_ = GeneratedMessageLite.mutableCopy(this.filters_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilters(int index, UsbAccessoryFilterProto value) {
        if (value != null) {
            ensureFiltersIsMutable();
            this.filters_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilters(int index, UsbAccessoryFilterProto.Builder builderForValue) {
        ensureFiltersIsMutable();
        this.filters_.set(index, (UsbAccessoryFilterProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(UsbAccessoryFilterProto value) {
        if (value != null) {
            ensureFiltersIsMutable();
            this.filters_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(int index, UsbAccessoryFilterProto value) {
        if (value != null) {
            ensureFiltersIsMutable();
            this.filters_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(UsbAccessoryFilterProto.Builder builderForValue) {
        ensureFiltersIsMutable();
        this.filters_.add((UsbAccessoryFilterProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFilters(int index, UsbAccessoryFilterProto.Builder builderForValue) {
        ensureFiltersIsMutable();
        this.filters_.add(index, (UsbAccessoryFilterProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFilters(Iterable<? extends UsbAccessoryFilterProto> values) {
        ensureFiltersIsMutable();
        AbstractMessageLite.addAll(values, this.filters_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFilters() {
        this.filters_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFilters(int index) {
        ensureFiltersIsMutable();
        this.filters_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getActivity());
        }
        for (int i = 0; i < this.filters_.size(); i++) {
            output.writeMessage(2, this.filters_.get(i));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getActivity());
        }
        for (int i = 0; i < this.filters_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.filters_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbAccessoryAttachedActivities parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAccessoryAttachedActivities parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAccessoryAttachedActivities parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAccessoryAttachedActivities parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAccessoryAttachedActivities parseFrom(InputStream input) throws IOException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryAttachedActivities parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAccessoryAttachedActivities parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbAccessoryAttachedActivities) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryAttachedActivities parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryAttachedActivities) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAccessoryAttachedActivities parseFrom(CodedInputStream input) throws IOException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryAttachedActivities parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryAttachedActivities) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbAccessoryAttachedActivities prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbAccessoryAttachedActivities, Builder> implements UsbAccessoryAttachedActivitiesOrBuilder {
        private Builder() {
            super(UsbAccessoryAttachedActivities.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
        public boolean hasActivity() {
            return ((UsbAccessoryAttachedActivities) this.instance).hasActivity();
        }

        @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
        public ComponentNameProto getActivity() {
            return ((UsbAccessoryAttachedActivities) this.instance).getActivity();
        }

        public Builder setActivity(ComponentNameProto value) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).setActivity((UsbAccessoryAttachedActivities) value);
            return this;
        }

        public Builder setActivity(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).setActivity((UsbAccessoryAttachedActivities) builderForValue);
            return this;
        }

        public Builder mergeActivity(ComponentNameProto value) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).mergeActivity(value);
            return this;
        }

        public Builder clearActivity() {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).clearActivity();
            return this;
        }

        @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
        public List<UsbAccessoryFilterProto> getFiltersList() {
            return Collections.unmodifiableList(((UsbAccessoryAttachedActivities) this.instance).getFiltersList());
        }

        @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
        public int getFiltersCount() {
            return ((UsbAccessoryAttachedActivities) this.instance).getFiltersCount();
        }

        @Override // android.service.usb.UsbAccessoryAttachedActivitiesOrBuilder
        public UsbAccessoryFilterProto getFilters(int index) {
            return ((UsbAccessoryAttachedActivities) this.instance).getFilters(index);
        }

        public Builder setFilters(int index, UsbAccessoryFilterProto value) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).setFilters((UsbAccessoryAttachedActivities) index, (int) value);
            return this;
        }

        public Builder setFilters(int index, UsbAccessoryFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).setFilters((UsbAccessoryAttachedActivities) index, (int) builderForValue);
            return this;
        }

        public Builder addFilters(UsbAccessoryFilterProto value) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).addFilters((UsbAccessoryAttachedActivities) value);
            return this;
        }

        public Builder addFilters(int index, UsbAccessoryFilterProto value) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).addFilters((UsbAccessoryAttachedActivities) index, (int) value);
            return this;
        }

        public Builder addFilters(UsbAccessoryFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).addFilters((UsbAccessoryAttachedActivities) builderForValue);
            return this;
        }

        public Builder addFilters(int index, UsbAccessoryFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).addFilters((UsbAccessoryAttachedActivities) index, (int) builderForValue);
            return this;
        }

        public Builder addAllFilters(Iterable<? extends UsbAccessoryFilterProto> values) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).addAllFilters(values);
            return this;
        }

        public Builder clearFilters() {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).clearFilters();
            return this;
        }

        public Builder removeFilters(int index) {
            copyOnWrite();
            ((UsbAccessoryAttachedActivities) this.instance).removeFilters(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbAccessoryAttachedActivities();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.filters_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbAccessoryAttachedActivities other = (UsbAccessoryAttachedActivities) arg1;
                this.activity_ = (ComponentNameProto) visitor.visitMessage(this.activity_, other.activity_);
                this.filters_ = visitor.visitList(this.filters_, other.filters_);
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
                            ComponentNameProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ComponentNameProto.Builder) this.activity_.toBuilder();
                            }
                            this.activity_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.activity_);
                                this.activity_ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            if (!this.filters_.isModifiable()) {
                                this.filters_ = GeneratedMessageLite.mutableCopy(this.filters_);
                            }
                            this.filters_.add((UsbAccessoryFilterProto) input.readMessage(UsbAccessoryFilterProto.parser(), extensionRegistry));
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
                    synchronized (UsbAccessoryAttachedActivities.class) {
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

    public static UsbAccessoryAttachedActivities getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbAccessoryAttachedActivities> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
