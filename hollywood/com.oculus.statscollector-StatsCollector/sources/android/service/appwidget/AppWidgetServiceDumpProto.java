package android.service.appwidget;

import android.service.appwidget.WidgetProto;
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

public final class AppWidgetServiceDumpProto extends GeneratedMessageLite<AppWidgetServiceDumpProto, Builder> implements AppWidgetServiceDumpProtoOrBuilder {
    private static final AppWidgetServiceDumpProto DEFAULT_INSTANCE = new AppWidgetServiceDumpProto();
    private static volatile Parser<AppWidgetServiceDumpProto> PARSER = null;
    public static final int WIDGETS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<WidgetProto> widgets_ = emptyProtobufList();

    private AppWidgetServiceDumpProto() {
    }

    @Override // android.service.appwidget.AppWidgetServiceDumpProtoOrBuilder
    public List<WidgetProto> getWidgetsList() {
        return this.widgets_;
    }

    public List<? extends WidgetProtoOrBuilder> getWidgetsOrBuilderList() {
        return this.widgets_;
    }

    @Override // android.service.appwidget.AppWidgetServiceDumpProtoOrBuilder
    public int getWidgetsCount() {
        return this.widgets_.size();
    }

    @Override // android.service.appwidget.AppWidgetServiceDumpProtoOrBuilder
    public WidgetProto getWidgets(int index) {
        return this.widgets_.get(index);
    }

    public WidgetProtoOrBuilder getWidgetsOrBuilder(int index) {
        return this.widgets_.get(index);
    }

    private void ensureWidgetsIsMutable() {
        if (!this.widgets_.isModifiable()) {
            this.widgets_ = GeneratedMessageLite.mutableCopy(this.widgets_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWidgets(int index, WidgetProto value) {
        if (value != null) {
            ensureWidgetsIsMutable();
            this.widgets_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWidgets(int index, WidgetProto.Builder builderForValue) {
        ensureWidgetsIsMutable();
        this.widgets_.set(index, (WidgetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWidgets(WidgetProto value) {
        if (value != null) {
            ensureWidgetsIsMutable();
            this.widgets_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWidgets(int index, WidgetProto value) {
        if (value != null) {
            ensureWidgetsIsMutable();
            this.widgets_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWidgets(WidgetProto.Builder builderForValue) {
        ensureWidgetsIsMutable();
        this.widgets_.add((WidgetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWidgets(int index, WidgetProto.Builder builderForValue) {
        ensureWidgetsIsMutable();
        this.widgets_.add(index, (WidgetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWidgets(Iterable<? extends WidgetProto> values) {
        ensureWidgetsIsMutable();
        AbstractMessageLite.addAll(values, this.widgets_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWidgets() {
        this.widgets_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWidgets(int index) {
        ensureWidgetsIsMutable();
        this.widgets_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.widgets_.size(); i++) {
            output.writeMessage(1, this.widgets_.get(i));
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
        for (int i = 0; i < this.widgets_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.widgets_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppWidgetServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppWidgetServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppWidgetServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppWidgetServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppWidgetServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWidgetServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppWidgetServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppWidgetServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWidgetServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWidgetServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppWidgetServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWidgetServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWidgetServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppWidgetServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppWidgetServiceDumpProto, Builder> implements AppWidgetServiceDumpProtoOrBuilder {
        private Builder() {
            super(AppWidgetServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.appwidget.AppWidgetServiceDumpProtoOrBuilder
        public List<WidgetProto> getWidgetsList() {
            return Collections.unmodifiableList(((AppWidgetServiceDumpProto) this.instance).getWidgetsList());
        }

        @Override // android.service.appwidget.AppWidgetServiceDumpProtoOrBuilder
        public int getWidgetsCount() {
            return ((AppWidgetServiceDumpProto) this.instance).getWidgetsCount();
        }

        @Override // android.service.appwidget.AppWidgetServiceDumpProtoOrBuilder
        public WidgetProto getWidgets(int index) {
            return ((AppWidgetServiceDumpProto) this.instance).getWidgets(index);
        }

        public Builder setWidgets(int index, WidgetProto value) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).setWidgets((AppWidgetServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setWidgets(int index, WidgetProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).setWidgets((AppWidgetServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWidgets(WidgetProto value) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).addWidgets((AppWidgetServiceDumpProto) value);
            return this;
        }

        public Builder addWidgets(int index, WidgetProto value) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).addWidgets((AppWidgetServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addWidgets(WidgetProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).addWidgets((AppWidgetServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addWidgets(int index, WidgetProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).addWidgets((AppWidgetServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWidgets(Iterable<? extends WidgetProto> values) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).addAllWidgets(values);
            return this;
        }

        public Builder clearWidgets() {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).clearWidgets();
            return this;
        }

        public Builder removeWidgets(int index) {
            copyOnWrite();
            ((AppWidgetServiceDumpProto) this.instance).removeWidgets(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppWidgetServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.widgets_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.widgets_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.widgets_, ((AppWidgetServiceDumpProto) arg1).widgets_);
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
                            if (!this.widgets_.isModifiable()) {
                                this.widgets_ = GeneratedMessageLite.mutableCopy(this.widgets_);
                            }
                            this.widgets_.add((WidgetProto) input.readMessage(WidgetProto.parser(), extensionRegistry));
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
                    synchronized (AppWidgetServiceDumpProto.class) {
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

    public static AppWidgetServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppWidgetServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
