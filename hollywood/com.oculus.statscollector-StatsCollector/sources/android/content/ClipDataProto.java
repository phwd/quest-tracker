package android.content;

import android.content.ClipDescriptionProto;
import android.content.IntentProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class ClipDataProto extends GeneratedMessageLite<ClipDataProto, Builder> implements ClipDataProtoOrBuilder {
    private static final ClipDataProto DEFAULT_INSTANCE = new ClipDataProto();
    public static final int DESCRIPTION_FIELD_NUMBER = 1;
    public static final int ICON_FIELD_NUMBER = 2;
    public static final int ITEMS_FIELD_NUMBER = 3;
    private static volatile Parser<ClipDataProto> PARSER;
    private int bitField0_;
    private ClipDescriptionProto description_;
    private Icon icon_;
    private Internal.ProtobufList<Item> items_ = emptyProtobufList();

    public interface IconOrBuilder extends MessageLiteOrBuilder {
        int getHeight();

        int getWidth();

        boolean hasHeight();

        boolean hasWidth();
    }

    public interface ItemOrBuilder extends MessageLiteOrBuilder {
        Item.DataCase getDataCase();

        String getHtmlText();

        ByteString getHtmlTextBytes();

        IntentProto getIntent();

        boolean getNothing();

        String getText();

        ByteString getTextBytes();

        String getUri();

        ByteString getUriBytes();

        boolean hasHtmlText();

        boolean hasIntent();

        boolean hasNothing();

        boolean hasText();

        boolean hasUri();
    }

    private ClipDataProto() {
    }

    public static final class Icon extends GeneratedMessageLite<Icon, Builder> implements IconOrBuilder {
        private static final Icon DEFAULT_INSTANCE = new Icon();
        public static final int HEIGHT_FIELD_NUMBER = 2;
        private static volatile Parser<Icon> PARSER = null;
        public static final int WIDTH_FIELD_NUMBER = 1;
        private int bitField0_;
        private int height_ = 0;
        private int width_ = 0;

        private Icon() {
        }

        @Override // android.content.ClipDataProto.IconOrBuilder
        public boolean hasWidth() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.content.ClipDataProto.IconOrBuilder
        public int getWidth() {
            return this.width_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWidth(int value) {
            this.bitField0_ |= 1;
            this.width_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWidth() {
            this.bitField0_ &= -2;
            this.width_ = 0;
        }

        @Override // android.content.ClipDataProto.IconOrBuilder
        public boolean hasHeight() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.content.ClipDataProto.IconOrBuilder
        public int getHeight() {
            return this.height_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHeight(int value) {
            this.bitField0_ |= 2;
            this.height_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHeight() {
            this.bitField0_ &= -3;
            this.height_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.width_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.height_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.width_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.height_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Icon parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Icon parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Icon parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Icon parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Icon parseFrom(InputStream input) throws IOException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Icon parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Icon parseDelimitedFrom(InputStream input) throws IOException {
            return (Icon) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Icon parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Icon) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Icon parseFrom(CodedInputStream input) throws IOException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Icon parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Icon) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Icon prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Icon, Builder> implements IconOrBuilder {
            private Builder() {
                super(Icon.DEFAULT_INSTANCE);
            }

            @Override // android.content.ClipDataProto.IconOrBuilder
            public boolean hasWidth() {
                return ((Icon) this.instance).hasWidth();
            }

            @Override // android.content.ClipDataProto.IconOrBuilder
            public int getWidth() {
                return ((Icon) this.instance).getWidth();
            }

            public Builder setWidth(int value) {
                copyOnWrite();
                ((Icon) this.instance).setWidth(value);
                return this;
            }

            public Builder clearWidth() {
                copyOnWrite();
                ((Icon) this.instance).clearWidth();
                return this;
            }

            @Override // android.content.ClipDataProto.IconOrBuilder
            public boolean hasHeight() {
                return ((Icon) this.instance).hasHeight();
            }

            @Override // android.content.ClipDataProto.IconOrBuilder
            public int getHeight() {
                return ((Icon) this.instance).getHeight();
            }

            public Builder setHeight(int value) {
                copyOnWrite();
                ((Icon) this.instance).setHeight(value);
                return this;
            }

            public Builder clearHeight() {
                copyOnWrite();
                ((Icon) this.instance).clearHeight();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Icon();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Icon other = (Icon) arg1;
                    this.width_ = visitor.visitInt(hasWidth(), this.width_, other.hasWidth(), other.width_);
                    this.height_ = visitor.visitInt(hasHeight(), this.height_, other.hasHeight(), other.height_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.width_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.height_ = input.readInt32();
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
                        synchronized (Icon.class) {
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

        public static Icon getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Icon> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Item extends GeneratedMessageLite<Item, Builder> implements ItemOrBuilder {
        private static final Item DEFAULT_INSTANCE = new Item();
        public static final int HTML_TEXT_FIELD_NUMBER = 1;
        public static final int INTENT_FIELD_NUMBER = 4;
        public static final int NOTHING_FIELD_NUMBER = 5;
        private static volatile Parser<Item> PARSER = null;
        public static final int TEXT_FIELD_NUMBER = 2;
        public static final int URI_FIELD_NUMBER = 3;
        private int bitField0_;
        private int dataCase_ = 0;
        private Object data_;

        private Item() {
        }

        public enum DataCase implements Internal.EnumLite {
            HTML_TEXT(1),
            TEXT(2),
            URI(3),
            INTENT(4),
            NOTHING(5),
            DATA_NOT_SET(0);
            
            private final int value;

            private DataCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static DataCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static DataCase forNumber(int value2) {
                if (value2 == 0) {
                    return DATA_NOT_SET;
                }
                if (value2 == 1) {
                    return HTML_TEXT;
                }
                if (value2 == 2) {
                    return TEXT;
                }
                if (value2 == 3) {
                    return URI;
                }
                if (value2 == 4) {
                    return INTENT;
                }
                if (value2 != 5) {
                    return null;
                }
                return NOTHING;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public DataCase getDataCase() {
            return DataCase.forNumber(this.dataCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearData() {
            this.dataCase_ = 0;
            this.data_ = null;
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public boolean hasHtmlText() {
            return this.dataCase_ == 1;
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public String getHtmlText() {
            if (this.dataCase_ == 1) {
                return (String) this.data_;
            }
            return "";
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public ByteString getHtmlTextBytes() {
            String ref = "";
            if (this.dataCase_ == 1) {
                ref = (String) this.data_;
            }
            return ByteString.copyFromUtf8(ref);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHtmlText(String value) {
            if (value != null) {
                this.dataCase_ = 1;
                this.data_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHtmlText() {
            if (this.dataCase_ == 1) {
                this.dataCase_ = 0;
                this.data_ = null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHtmlTextBytes(ByteString value) {
            if (value != null) {
                this.dataCase_ = 1;
                this.data_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public boolean hasText() {
            return this.dataCase_ == 2;
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public String getText() {
            if (this.dataCase_ == 2) {
                return (String) this.data_;
            }
            return "";
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public ByteString getTextBytes() {
            String ref = "";
            if (this.dataCase_ == 2) {
                ref = (String) this.data_;
            }
            return ByteString.copyFromUtf8(ref);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setText(String value) {
            if (value != null) {
                this.dataCase_ = 2;
                this.data_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearText() {
            if (this.dataCase_ == 2) {
                this.dataCase_ = 0;
                this.data_ = null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTextBytes(ByteString value) {
            if (value != null) {
                this.dataCase_ = 2;
                this.data_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public boolean hasUri() {
            return this.dataCase_ == 3;
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public String getUri() {
            if (this.dataCase_ == 3) {
                return (String) this.data_;
            }
            return "";
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public ByteString getUriBytes() {
            String ref = "";
            if (this.dataCase_ == 3) {
                ref = (String) this.data_;
            }
            return ByteString.copyFromUtf8(ref);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUri(String value) {
            if (value != null) {
                this.dataCase_ = 3;
                this.data_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUri() {
            if (this.dataCase_ == 3) {
                this.dataCase_ = 0;
                this.data_ = null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUriBytes(ByteString value) {
            if (value != null) {
                this.dataCase_ = 3;
                this.data_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public boolean hasIntent() {
            return this.dataCase_ == 4;
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public IntentProto getIntent() {
            if (this.dataCase_ == 4) {
                return (IntentProto) this.data_;
            }
            return IntentProto.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto value) {
            if (value != null) {
                this.data_ = value;
                this.dataCase_ = 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto.Builder builderForValue) {
            this.data_ = builderForValue.build();
            this.dataCase_ = 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeIntent(IntentProto value) {
            if (this.dataCase_ != 4 || this.data_ == IntentProto.getDefaultInstance()) {
                this.data_ = value;
            } else {
                this.data_ = ((IntentProto.Builder) IntentProto.newBuilder((IntentProto) this.data_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.dataCase_ = 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntent() {
            if (this.dataCase_ == 4) {
                this.dataCase_ = 0;
                this.data_ = null;
            }
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public boolean hasNothing() {
            return this.dataCase_ == 5;
        }

        @Override // android.content.ClipDataProto.ItemOrBuilder
        public boolean getNothing() {
            if (this.dataCase_ == 5) {
                return ((Boolean) this.data_).booleanValue();
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNothing(boolean value) {
            this.dataCase_ = 5;
            this.data_ = Boolean.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNothing() {
            if (this.dataCase_ == 5) {
                this.dataCase_ = 0;
                this.data_ = null;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.dataCase_ == 1) {
                output.writeString(1, getHtmlText());
            }
            if (this.dataCase_ == 2) {
                output.writeString(2, getText());
            }
            if (this.dataCase_ == 3) {
                output.writeString(3, getUri());
            }
            if (this.dataCase_ == 4) {
                output.writeMessage(4, (IntentProto) this.data_);
            }
            if (this.dataCase_ == 5) {
                output.writeBool(5, ((Boolean) this.data_).booleanValue());
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
            if (this.dataCase_ == 1) {
                size2 = 0 + CodedOutputStream.computeStringSize(1, getHtmlText());
            }
            if (this.dataCase_ == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getText());
            }
            if (this.dataCase_ == 3) {
                size2 += CodedOutputStream.computeStringSize(3, getUri());
            }
            if (this.dataCase_ == 4) {
                size2 += CodedOutputStream.computeMessageSize(4, (IntentProto) this.data_);
            }
            if (this.dataCase_ == 5) {
                size2 += CodedOutputStream.computeBoolSize(5, ((Boolean) this.data_).booleanValue());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Item parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Item parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Item parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Item parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Item parseFrom(InputStream input) throws IOException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Item parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Item parseDelimitedFrom(InputStream input) throws IOException {
            return (Item) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Item parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Item) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Item parseFrom(CodedInputStream input) throws IOException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Item parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Item) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Item prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Item, Builder> implements ItemOrBuilder {
            private Builder() {
                super(Item.DEFAULT_INSTANCE);
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public DataCase getDataCase() {
                return ((Item) this.instance).getDataCase();
            }

            public Builder clearData() {
                copyOnWrite();
                ((Item) this.instance).clearData();
                return this;
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public boolean hasHtmlText() {
                return ((Item) this.instance).hasHtmlText();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public String getHtmlText() {
                return ((Item) this.instance).getHtmlText();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public ByteString getHtmlTextBytes() {
                return ((Item) this.instance).getHtmlTextBytes();
            }

            public Builder setHtmlText(String value) {
                copyOnWrite();
                ((Item) this.instance).setHtmlText(value);
                return this;
            }

            public Builder clearHtmlText() {
                copyOnWrite();
                ((Item) this.instance).clearHtmlText();
                return this;
            }

            public Builder setHtmlTextBytes(ByteString value) {
                copyOnWrite();
                ((Item) this.instance).setHtmlTextBytes(value);
                return this;
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public boolean hasText() {
                return ((Item) this.instance).hasText();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public String getText() {
                return ((Item) this.instance).getText();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public ByteString getTextBytes() {
                return ((Item) this.instance).getTextBytes();
            }

            public Builder setText(String value) {
                copyOnWrite();
                ((Item) this.instance).setText(value);
                return this;
            }

            public Builder clearText() {
                copyOnWrite();
                ((Item) this.instance).clearText();
                return this;
            }

            public Builder setTextBytes(ByteString value) {
                copyOnWrite();
                ((Item) this.instance).setTextBytes(value);
                return this;
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public boolean hasUri() {
                return ((Item) this.instance).hasUri();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public String getUri() {
                return ((Item) this.instance).getUri();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public ByteString getUriBytes() {
                return ((Item) this.instance).getUriBytes();
            }

            public Builder setUri(String value) {
                copyOnWrite();
                ((Item) this.instance).setUri(value);
                return this;
            }

            public Builder clearUri() {
                copyOnWrite();
                ((Item) this.instance).clearUri();
                return this;
            }

            public Builder setUriBytes(ByteString value) {
                copyOnWrite();
                ((Item) this.instance).setUriBytes(value);
                return this;
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public boolean hasIntent() {
                return ((Item) this.instance).hasIntent();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public IntentProto getIntent() {
                return ((Item) this.instance).getIntent();
            }

            public Builder setIntent(IntentProto value) {
                copyOnWrite();
                ((Item) this.instance).setIntent((Item) value);
                return this;
            }

            public Builder setIntent(IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((Item) this.instance).setIntent((Item) builderForValue);
                return this;
            }

            public Builder mergeIntent(IntentProto value) {
                copyOnWrite();
                ((Item) this.instance).mergeIntent(value);
                return this;
            }

            public Builder clearIntent() {
                copyOnWrite();
                ((Item) this.instance).clearIntent();
                return this;
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public boolean hasNothing() {
                return ((Item) this.instance).hasNothing();
            }

            @Override // android.content.ClipDataProto.ItemOrBuilder
            public boolean getNothing() {
                return ((Item) this.instance).getNothing();
            }

            public Builder setNothing(boolean value) {
                copyOnWrite();
                ((Item) this.instance).setNothing(value);
                return this;
            }

            public Builder clearNothing() {
                copyOnWrite();
                ((Item) this.instance).clearNothing();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Item();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Item other = (Item) arg1;
                    switch (other.getDataCase()) {
                        case HTML_TEXT:
                            if (this.dataCase_ != 1) {
                                z = false;
                            }
                            this.data_ = visitor.visitOneofString(z, this.data_, other.data_);
                            break;
                        case TEXT:
                            if (this.dataCase_ != 2) {
                                z = false;
                            }
                            this.data_ = visitor.visitOneofString(z, this.data_, other.data_);
                            break;
                        case URI:
                            if (this.dataCase_ != 3) {
                                z = false;
                            }
                            this.data_ = visitor.visitOneofString(z, this.data_, other.data_);
                            break;
                        case INTENT:
                            if (this.dataCase_ != 4) {
                                z = false;
                            }
                            this.data_ = visitor.visitOneofMessage(z, this.data_, other.data_);
                            break;
                        case NOTHING:
                            if (this.dataCase_ != 5) {
                                z = false;
                            }
                            this.data_ = visitor.visitOneofBoolean(z, this.data_, other.data_);
                            break;
                        case DATA_NOT_SET:
                            if (this.dataCase_ == 0) {
                                z = false;
                            }
                            visitor.visitOneofNotSet(z);
                            break;
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i = other.dataCase_;
                        if (i != 0) {
                            this.dataCase_ = i;
                        }
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
                                String s = input.readString();
                                this.dataCase_ = 1;
                                this.data_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                this.dataCase_ = 2;
                                this.data_ = s2;
                            } else if (tag == 26) {
                                String s3 = input.readString();
                                this.dataCase_ = 3;
                                this.data_ = s3;
                            } else if (tag == 34) {
                                IntentProto.Builder subBuilder = null;
                                if (this.dataCase_ == 4) {
                                    subBuilder = (IntentProto.Builder) ((IntentProto) this.data_).toBuilder();
                                }
                                this.data_ = input.readMessage(IntentProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) ((IntentProto) this.data_));
                                    this.data_ = subBuilder.buildPartial();
                                }
                                this.dataCase_ = 4;
                            } else if (tag == 40) {
                                this.dataCase_ = 5;
                                this.data_ = Boolean.valueOf(input.readBool());
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
                        synchronized (Item.class) {
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

        public static Item getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Item> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public boolean hasDescription() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public ClipDescriptionProto getDescription() {
        ClipDescriptionProto clipDescriptionProto = this.description_;
        return clipDescriptionProto == null ? ClipDescriptionProto.getDefaultInstance() : clipDescriptionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescription(ClipDescriptionProto value) {
        if (value != null) {
            this.description_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescription(ClipDescriptionProto.Builder builderForValue) {
        this.description_ = (ClipDescriptionProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDescription(ClipDescriptionProto value) {
        ClipDescriptionProto clipDescriptionProto = this.description_;
        if (clipDescriptionProto == null || clipDescriptionProto == ClipDescriptionProto.getDefaultInstance()) {
            this.description_ = value;
        } else {
            this.description_ = (ClipDescriptionProto) ((ClipDescriptionProto.Builder) ClipDescriptionProto.newBuilder(this.description_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDescription() {
        this.description_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public boolean hasIcon() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public Icon getIcon() {
        Icon icon = this.icon_;
        return icon == null ? Icon.getDefaultInstance() : icon;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIcon(Icon value) {
        if (value != null) {
            this.icon_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIcon(Icon.Builder builderForValue) {
        this.icon_ = (Icon) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIcon(Icon value) {
        Icon icon = this.icon_;
        if (icon == null || icon == Icon.getDefaultInstance()) {
            this.icon_ = value;
        } else {
            this.icon_ = (Icon) ((Icon.Builder) Icon.newBuilder(this.icon_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIcon() {
        this.icon_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public List<Item> getItemsList() {
        return this.items_;
    }

    public List<? extends ItemOrBuilder> getItemsOrBuilderList() {
        return this.items_;
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public int getItemsCount() {
        return this.items_.size();
    }

    @Override // android.content.ClipDataProtoOrBuilder
    public Item getItems(int index) {
        return this.items_.get(index);
    }

    public ItemOrBuilder getItemsOrBuilder(int index) {
        return this.items_.get(index);
    }

    private void ensureItemsIsMutable() {
        if (!this.items_.isModifiable()) {
            this.items_ = GeneratedMessageLite.mutableCopy(this.items_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setItems(int index, Item value) {
        if (value != null) {
            ensureItemsIsMutable();
            this.items_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setItems(int index, Item.Builder builderForValue) {
        ensureItemsIsMutable();
        this.items_.set(index, (Item) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addItems(Item value) {
        if (value != null) {
            ensureItemsIsMutable();
            this.items_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addItems(int index, Item value) {
        if (value != null) {
            ensureItemsIsMutable();
            this.items_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addItems(Item.Builder builderForValue) {
        ensureItemsIsMutable();
        this.items_.add((Item) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addItems(int index, Item.Builder builderForValue) {
        ensureItemsIsMutable();
        this.items_.add(index, (Item) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllItems(Iterable<? extends Item> values) {
        ensureItemsIsMutable();
        AbstractMessageLite.addAll(values, this.items_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearItems() {
        this.items_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeItems(int index) {
        ensureItemsIsMutable();
        this.items_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getDescription());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getIcon());
        }
        for (int i = 0; i < this.items_.size(); i++) {
            output.writeMessage(3, this.items_.get(i));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getDescription());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getIcon());
        }
        for (int i = 0; i < this.items_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.items_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ClipDataProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ClipDataProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ClipDataProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ClipDataProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ClipDataProto parseFrom(InputStream input) throws IOException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ClipDataProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ClipDataProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ClipDataProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ClipDataProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ClipDataProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ClipDataProto parseFrom(CodedInputStream input) throws IOException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ClipDataProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ClipDataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ClipDataProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ClipDataProto, Builder> implements ClipDataProtoOrBuilder {
        private Builder() {
            super(ClipDataProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public boolean hasDescription() {
            return ((ClipDataProto) this.instance).hasDescription();
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public ClipDescriptionProto getDescription() {
            return ((ClipDataProto) this.instance).getDescription();
        }

        public Builder setDescription(ClipDescriptionProto value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).setDescription((ClipDataProto) value);
            return this;
        }

        public Builder setDescription(ClipDescriptionProto.Builder builderForValue) {
            copyOnWrite();
            ((ClipDataProto) this.instance).setDescription((ClipDataProto) builderForValue);
            return this;
        }

        public Builder mergeDescription(ClipDescriptionProto value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).mergeDescription(value);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((ClipDataProto) this.instance).clearDescription();
            return this;
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public boolean hasIcon() {
            return ((ClipDataProto) this.instance).hasIcon();
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public Icon getIcon() {
            return ((ClipDataProto) this.instance).getIcon();
        }

        public Builder setIcon(Icon value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).setIcon((ClipDataProto) value);
            return this;
        }

        public Builder setIcon(Icon.Builder builderForValue) {
            copyOnWrite();
            ((ClipDataProto) this.instance).setIcon((ClipDataProto) builderForValue);
            return this;
        }

        public Builder mergeIcon(Icon value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).mergeIcon(value);
            return this;
        }

        public Builder clearIcon() {
            copyOnWrite();
            ((ClipDataProto) this.instance).clearIcon();
            return this;
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public List<Item> getItemsList() {
            return Collections.unmodifiableList(((ClipDataProto) this.instance).getItemsList());
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public int getItemsCount() {
            return ((ClipDataProto) this.instance).getItemsCount();
        }

        @Override // android.content.ClipDataProtoOrBuilder
        public Item getItems(int index) {
            return ((ClipDataProto) this.instance).getItems(index);
        }

        public Builder setItems(int index, Item value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).setItems((ClipDataProto) index, (int) value);
            return this;
        }

        public Builder setItems(int index, Item.Builder builderForValue) {
            copyOnWrite();
            ((ClipDataProto) this.instance).setItems((ClipDataProto) index, (int) builderForValue);
            return this;
        }

        public Builder addItems(Item value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).addItems((ClipDataProto) value);
            return this;
        }

        public Builder addItems(int index, Item value) {
            copyOnWrite();
            ((ClipDataProto) this.instance).addItems((ClipDataProto) index, (int) value);
            return this;
        }

        public Builder addItems(Item.Builder builderForValue) {
            copyOnWrite();
            ((ClipDataProto) this.instance).addItems((ClipDataProto) builderForValue);
            return this;
        }

        public Builder addItems(int index, Item.Builder builderForValue) {
            copyOnWrite();
            ((ClipDataProto) this.instance).addItems((ClipDataProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllItems(Iterable<? extends Item> values) {
            copyOnWrite();
            ((ClipDataProto) this.instance).addAllItems(values);
            return this;
        }

        public Builder clearItems() {
            copyOnWrite();
            ((ClipDataProto) this.instance).clearItems();
            return this;
        }

        public Builder removeItems(int index) {
            copyOnWrite();
            ((ClipDataProto) this.instance).removeItems(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ClipDataProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.items_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ClipDataProto other = (ClipDataProto) arg1;
                this.description_ = (ClipDescriptionProto) visitor.visitMessage(this.description_, other.description_);
                this.icon_ = (Icon) visitor.visitMessage(this.icon_, other.icon_);
                this.items_ = visitor.visitList(this.items_, other.items_);
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
                            ClipDescriptionProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ClipDescriptionProto.Builder) this.description_.toBuilder();
                            }
                            this.description_ = (ClipDescriptionProto) input.readMessage(ClipDescriptionProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.description_);
                                this.description_ = (ClipDescriptionProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            Icon.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (Icon.Builder) this.icon_.toBuilder();
                            }
                            this.icon_ = (Icon) input.readMessage(Icon.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.icon_);
                                this.icon_ = (Icon) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            if (!this.items_.isModifiable()) {
                                this.items_ = GeneratedMessageLite.mutableCopy(this.items_);
                            }
                            this.items_.add((Item) input.readMessage(Item.parser(), extensionRegistry));
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
                    synchronized (ClipDataProto.class) {
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

    public static ClipDataProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ClipDataProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
