package android.content;

import android.content.AuthorityEntryProto;
import android.os.PatternMatcherProto;
import android.os.PatternMatcherProtoOrBuilder;
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

public final class IntentFilterProto extends GeneratedMessageLite<IntentFilterProto, Builder> implements IntentFilterProtoOrBuilder {
    public static final int ACTIONS_FIELD_NUMBER = 1;
    public static final int CATEGORIES_FIELD_NUMBER = 2;
    public static final int DATA_AUTHORITIES_FIELD_NUMBER = 5;
    public static final int DATA_PATHS_FIELD_NUMBER = 6;
    public static final int DATA_SCHEMES_FIELD_NUMBER = 3;
    public static final int DATA_SCHEME_SPECS_FIELD_NUMBER = 4;
    public static final int DATA_TYPES_FIELD_NUMBER = 7;
    private static final IntentFilterProto DEFAULT_INSTANCE = new IntentFilterProto();
    public static final int GET_AUTO_VERIFY_FIELD_NUMBER = 10;
    public static final int HAS_PARTIAL_TYPES_FIELD_NUMBER = 9;
    private static volatile Parser<IntentFilterProto> PARSER = null;
    public static final int PRIORITY_FIELD_NUMBER = 8;
    private Internal.ProtobufList<String> actions_ = GeneratedMessageLite.emptyProtobufList();
    private int bitField0_;
    private Internal.ProtobufList<String> categories_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<AuthorityEntryProto> dataAuthorities_ = emptyProtobufList();
    private Internal.ProtobufList<PatternMatcherProto> dataPaths_ = emptyProtobufList();
    private Internal.ProtobufList<PatternMatcherProto> dataSchemeSpecs_ = emptyProtobufList();
    private Internal.ProtobufList<String> dataSchemes_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> dataTypes_ = GeneratedMessageLite.emptyProtobufList();
    private boolean getAutoVerify_ = false;
    private boolean hasPartialTypes_ = false;
    private int priority_ = 0;

    private IntentFilterProto() {
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<String> getActionsList() {
        return this.actions_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getActionsCount() {
        return this.actions_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public String getActions(int index) {
        return this.actions_.get(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public ByteString getActionsBytes(int index) {
        return ByteString.copyFromUtf8(this.actions_.get(index));
    }

    private void ensureActionsIsMutable() {
        if (!this.actions_.isModifiable()) {
            this.actions_ = GeneratedMessageLite.mutableCopy(this.actions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActions(int index, String value) {
        if (value != null) {
            ensureActionsIsMutable();
            this.actions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActions(String value) {
        if (value != null) {
            ensureActionsIsMutable();
            this.actions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActions(Iterable<String> values) {
        ensureActionsIsMutable();
        AbstractMessageLite.addAll(values, this.actions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActions() {
        this.actions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActionsBytes(ByteString value) {
        if (value != null) {
            ensureActionsIsMutable();
            this.actions_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<String> getCategoriesList() {
        return this.categories_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getCategoriesCount() {
        return this.categories_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public String getCategories(int index) {
        return this.categories_.get(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public ByteString getCategoriesBytes(int index) {
        return ByteString.copyFromUtf8(this.categories_.get(index));
    }

    private void ensureCategoriesIsMutable() {
        if (!this.categories_.isModifiable()) {
            this.categories_ = GeneratedMessageLite.mutableCopy(this.categories_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCategories(int index, String value) {
        if (value != null) {
            ensureCategoriesIsMutable();
            this.categories_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCategories(String value) {
        if (value != null) {
            ensureCategoriesIsMutable();
            this.categories_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCategories(Iterable<String> values) {
        ensureCategoriesIsMutable();
        AbstractMessageLite.addAll(values, this.categories_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCategories() {
        this.categories_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCategoriesBytes(ByteString value) {
        if (value != null) {
            ensureCategoriesIsMutable();
            this.categories_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<String> getDataSchemesList() {
        return this.dataSchemes_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getDataSchemesCount() {
        return this.dataSchemes_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public String getDataSchemes(int index) {
        return this.dataSchemes_.get(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public ByteString getDataSchemesBytes(int index) {
        return ByteString.copyFromUtf8(this.dataSchemes_.get(index));
    }

    private void ensureDataSchemesIsMutable() {
        if (!this.dataSchemes_.isModifiable()) {
            this.dataSchemes_ = GeneratedMessageLite.mutableCopy(this.dataSchemes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataSchemes(int index, String value) {
        if (value != null) {
            ensureDataSchemesIsMutable();
            this.dataSchemes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataSchemes(String value) {
        if (value != null) {
            ensureDataSchemesIsMutable();
            this.dataSchemes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDataSchemes(Iterable<String> values) {
        ensureDataSchemesIsMutable();
        AbstractMessageLite.addAll(values, this.dataSchemes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataSchemes() {
        this.dataSchemes_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataSchemesBytes(ByteString value) {
        if (value != null) {
            ensureDataSchemesIsMutable();
            this.dataSchemes_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<PatternMatcherProto> getDataSchemeSpecsList() {
        return this.dataSchemeSpecs_;
    }

    public List<? extends PatternMatcherProtoOrBuilder> getDataSchemeSpecsOrBuilderList() {
        return this.dataSchemeSpecs_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getDataSchemeSpecsCount() {
        return this.dataSchemeSpecs_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public PatternMatcherProto getDataSchemeSpecs(int index) {
        return this.dataSchemeSpecs_.get(index);
    }

    public PatternMatcherProtoOrBuilder getDataSchemeSpecsOrBuilder(int index) {
        return this.dataSchemeSpecs_.get(index);
    }

    private void ensureDataSchemeSpecsIsMutable() {
        if (!this.dataSchemeSpecs_.isModifiable()) {
            this.dataSchemeSpecs_ = GeneratedMessageLite.mutableCopy(this.dataSchemeSpecs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataSchemeSpecs(int index, PatternMatcherProto value) {
        if (value != null) {
            ensureDataSchemeSpecsIsMutable();
            this.dataSchemeSpecs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataSchemeSpecs(int index, PatternMatcherProto.Builder builderForValue) {
        ensureDataSchemeSpecsIsMutable();
        this.dataSchemeSpecs_.set(index, (PatternMatcherProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataSchemeSpecs(PatternMatcherProto value) {
        if (value != null) {
            ensureDataSchemeSpecsIsMutable();
            this.dataSchemeSpecs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataSchemeSpecs(int index, PatternMatcherProto value) {
        if (value != null) {
            ensureDataSchemeSpecsIsMutable();
            this.dataSchemeSpecs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataSchemeSpecs(PatternMatcherProto.Builder builderForValue) {
        ensureDataSchemeSpecsIsMutable();
        this.dataSchemeSpecs_.add((PatternMatcherProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataSchemeSpecs(int index, PatternMatcherProto.Builder builderForValue) {
        ensureDataSchemeSpecsIsMutable();
        this.dataSchemeSpecs_.add(index, (PatternMatcherProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDataSchemeSpecs(Iterable<? extends PatternMatcherProto> values) {
        ensureDataSchemeSpecsIsMutable();
        AbstractMessageLite.addAll(values, this.dataSchemeSpecs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataSchemeSpecs() {
        this.dataSchemeSpecs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDataSchemeSpecs(int index) {
        ensureDataSchemeSpecsIsMutable();
        this.dataSchemeSpecs_.remove(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<AuthorityEntryProto> getDataAuthoritiesList() {
        return this.dataAuthorities_;
    }

    public List<? extends AuthorityEntryProtoOrBuilder> getDataAuthoritiesOrBuilderList() {
        return this.dataAuthorities_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getDataAuthoritiesCount() {
        return this.dataAuthorities_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public AuthorityEntryProto getDataAuthorities(int index) {
        return this.dataAuthorities_.get(index);
    }

    public AuthorityEntryProtoOrBuilder getDataAuthoritiesOrBuilder(int index) {
        return this.dataAuthorities_.get(index);
    }

    private void ensureDataAuthoritiesIsMutable() {
        if (!this.dataAuthorities_.isModifiable()) {
            this.dataAuthorities_ = GeneratedMessageLite.mutableCopy(this.dataAuthorities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataAuthorities(int index, AuthorityEntryProto value) {
        if (value != null) {
            ensureDataAuthoritiesIsMutable();
            this.dataAuthorities_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataAuthorities(int index, AuthorityEntryProto.Builder builderForValue) {
        ensureDataAuthoritiesIsMutable();
        this.dataAuthorities_.set(index, (AuthorityEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataAuthorities(AuthorityEntryProto value) {
        if (value != null) {
            ensureDataAuthoritiesIsMutable();
            this.dataAuthorities_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataAuthorities(int index, AuthorityEntryProto value) {
        if (value != null) {
            ensureDataAuthoritiesIsMutable();
            this.dataAuthorities_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataAuthorities(AuthorityEntryProto.Builder builderForValue) {
        ensureDataAuthoritiesIsMutable();
        this.dataAuthorities_.add((AuthorityEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataAuthorities(int index, AuthorityEntryProto.Builder builderForValue) {
        ensureDataAuthoritiesIsMutable();
        this.dataAuthorities_.add(index, (AuthorityEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDataAuthorities(Iterable<? extends AuthorityEntryProto> values) {
        ensureDataAuthoritiesIsMutable();
        AbstractMessageLite.addAll(values, this.dataAuthorities_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataAuthorities() {
        this.dataAuthorities_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDataAuthorities(int index) {
        ensureDataAuthoritiesIsMutable();
        this.dataAuthorities_.remove(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<PatternMatcherProto> getDataPathsList() {
        return this.dataPaths_;
    }

    public List<? extends PatternMatcherProtoOrBuilder> getDataPathsOrBuilderList() {
        return this.dataPaths_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getDataPathsCount() {
        return this.dataPaths_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public PatternMatcherProto getDataPaths(int index) {
        return this.dataPaths_.get(index);
    }

    public PatternMatcherProtoOrBuilder getDataPathsOrBuilder(int index) {
        return this.dataPaths_.get(index);
    }

    private void ensureDataPathsIsMutable() {
        if (!this.dataPaths_.isModifiable()) {
            this.dataPaths_ = GeneratedMessageLite.mutableCopy(this.dataPaths_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataPaths(int index, PatternMatcherProto value) {
        if (value != null) {
            ensureDataPathsIsMutable();
            this.dataPaths_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataPaths(int index, PatternMatcherProto.Builder builderForValue) {
        ensureDataPathsIsMutable();
        this.dataPaths_.set(index, (PatternMatcherProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataPaths(PatternMatcherProto value) {
        if (value != null) {
            ensureDataPathsIsMutable();
            this.dataPaths_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataPaths(int index, PatternMatcherProto value) {
        if (value != null) {
            ensureDataPathsIsMutable();
            this.dataPaths_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataPaths(PatternMatcherProto.Builder builderForValue) {
        ensureDataPathsIsMutable();
        this.dataPaths_.add((PatternMatcherProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataPaths(int index, PatternMatcherProto.Builder builderForValue) {
        ensureDataPathsIsMutable();
        this.dataPaths_.add(index, (PatternMatcherProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDataPaths(Iterable<? extends PatternMatcherProto> values) {
        ensureDataPathsIsMutable();
        AbstractMessageLite.addAll(values, this.dataPaths_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataPaths() {
        this.dataPaths_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDataPaths(int index) {
        ensureDataPathsIsMutable();
        this.dataPaths_.remove(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public List<String> getDataTypesList() {
        return this.dataTypes_;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getDataTypesCount() {
        return this.dataTypes_.size();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public String getDataTypes(int index) {
        return this.dataTypes_.get(index);
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public ByteString getDataTypesBytes(int index) {
        return ByteString.copyFromUtf8(this.dataTypes_.get(index));
    }

    private void ensureDataTypesIsMutable() {
        if (!this.dataTypes_.isModifiable()) {
            this.dataTypes_ = GeneratedMessageLite.mutableCopy(this.dataTypes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataTypes(int index, String value) {
        if (value != null) {
            ensureDataTypesIsMutable();
            this.dataTypes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataTypes(String value) {
        if (value != null) {
            ensureDataTypesIsMutable();
            this.dataTypes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDataTypes(Iterable<String> values) {
        ensureDataTypesIsMutable();
        AbstractMessageLite.addAll(values, this.dataTypes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataTypes() {
        this.dataTypes_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDataTypesBytes(ByteString value) {
        if (value != null) {
            ensureDataTypesIsMutable();
            this.dataTypes_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public boolean hasPriority() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public int getPriority() {
        return this.priority_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriority(int value) {
        this.bitField0_ |= 1;
        this.priority_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriority() {
        this.bitField0_ &= -2;
        this.priority_ = 0;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public boolean hasHasPartialTypes() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public boolean getHasPartialTypes() {
        return this.hasPartialTypes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasPartialTypes(boolean value) {
        this.bitField0_ |= 2;
        this.hasPartialTypes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasPartialTypes() {
        this.bitField0_ &= -3;
        this.hasPartialTypes_ = false;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public boolean hasGetAutoVerify() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.IntentFilterProtoOrBuilder
    public boolean getGetAutoVerify() {
        return this.getAutoVerify_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGetAutoVerify(boolean value) {
        this.bitField0_ |= 4;
        this.getAutoVerify_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGetAutoVerify() {
        this.bitField0_ &= -5;
        this.getAutoVerify_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.actions_.size(); i++) {
            output.writeString(1, this.actions_.get(i));
        }
        for (int i2 = 0; i2 < this.categories_.size(); i2++) {
            output.writeString(2, this.categories_.get(i2));
        }
        for (int i3 = 0; i3 < this.dataSchemes_.size(); i3++) {
            output.writeString(3, this.dataSchemes_.get(i3));
        }
        for (int i4 = 0; i4 < this.dataSchemeSpecs_.size(); i4++) {
            output.writeMessage(4, this.dataSchemeSpecs_.get(i4));
        }
        for (int i5 = 0; i5 < this.dataAuthorities_.size(); i5++) {
            output.writeMessage(5, this.dataAuthorities_.get(i5));
        }
        for (int i6 = 0; i6 < this.dataPaths_.size(); i6++) {
            output.writeMessage(6, this.dataPaths_.get(i6));
        }
        for (int i7 = 0; i7 < this.dataTypes_.size(); i7++) {
            output.writeString(7, this.dataTypes_.get(i7));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(8, this.priority_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(9, this.hasPartialTypes_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(10, this.getAutoVerify_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.actions_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.actions_.get(i));
        }
        int size2 = 0 + dataSize + (getActionsList().size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.categories_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeStringSizeNoTag(this.categories_.get(i2));
        }
        int size3 = size2 + dataSize2 + (getCategoriesList().size() * 1);
        int dataSize3 = 0;
        for (int i3 = 0; i3 < this.dataSchemes_.size(); i3++) {
            dataSize3 += CodedOutputStream.computeStringSizeNoTag(this.dataSchemes_.get(i3));
        }
        int size4 = size3 + dataSize3 + (getDataSchemesList().size() * 1);
        for (int i4 = 0; i4 < this.dataSchemeSpecs_.size(); i4++) {
            size4 += CodedOutputStream.computeMessageSize(4, this.dataSchemeSpecs_.get(i4));
        }
        for (int i5 = 0; i5 < this.dataAuthorities_.size(); i5++) {
            size4 += CodedOutputStream.computeMessageSize(5, this.dataAuthorities_.get(i5));
        }
        for (int i6 = 0; i6 < this.dataPaths_.size(); i6++) {
            size4 += CodedOutputStream.computeMessageSize(6, this.dataPaths_.get(i6));
        }
        int dataSize4 = 0;
        for (int i7 = 0; i7 < this.dataTypes_.size(); i7++) {
            dataSize4 += CodedOutputStream.computeStringSizeNoTag(this.dataTypes_.get(i7));
        }
        int size5 = size4 + dataSize4 + (getDataTypesList().size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            size5 += CodedOutputStream.computeInt32Size(8, this.priority_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size5 += CodedOutputStream.computeBoolSize(9, this.hasPartialTypes_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size5 += CodedOutputStream.computeBoolSize(10, this.getAutoVerify_);
        }
        int size6 = size5 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size6;
        return size6;
    }

    public static IntentFilterProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentFilterProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentFilterProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentFilterProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentFilterProto parseFrom(InputStream input) throws IOException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentFilterProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentFilterProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IntentFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentFilterProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentFilterProto parseFrom(CodedInputStream input) throws IOException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentFilterProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IntentFilterProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IntentFilterProto, Builder> implements IntentFilterProtoOrBuilder {
        private Builder() {
            super(IntentFilterProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<String> getActionsList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getActionsList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getActionsCount() {
            return ((IntentFilterProto) this.instance).getActionsCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public String getActions(int index) {
            return ((IntentFilterProto) this.instance).getActions(index);
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public ByteString getActionsBytes(int index) {
            return ((IntentFilterProto) this.instance).getActionsBytes(index);
        }

        public Builder setActions(int index, String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setActions(index, value);
            return this;
        }

        public Builder addActions(String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addActions(value);
            return this;
        }

        public Builder addAllActions(Iterable<String> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllActions(values);
            return this;
        }

        public Builder clearActions() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearActions();
            return this;
        }

        public Builder addActionsBytes(ByteString value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addActionsBytes(value);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<String> getCategoriesList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getCategoriesList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getCategoriesCount() {
            return ((IntentFilterProto) this.instance).getCategoriesCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public String getCategories(int index) {
            return ((IntentFilterProto) this.instance).getCategories(index);
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public ByteString getCategoriesBytes(int index) {
            return ((IntentFilterProto) this.instance).getCategoriesBytes(index);
        }

        public Builder setCategories(int index, String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setCategories(index, value);
            return this;
        }

        public Builder addCategories(String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addCategories(value);
            return this;
        }

        public Builder addAllCategories(Iterable<String> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllCategories(values);
            return this;
        }

        public Builder clearCategories() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearCategories();
            return this;
        }

        public Builder addCategoriesBytes(ByteString value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addCategoriesBytes(value);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<String> getDataSchemesList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getDataSchemesList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getDataSchemesCount() {
            return ((IntentFilterProto) this.instance).getDataSchemesCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public String getDataSchemes(int index) {
            return ((IntentFilterProto) this.instance).getDataSchemes(index);
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public ByteString getDataSchemesBytes(int index) {
            return ((IntentFilterProto) this.instance).getDataSchemesBytes(index);
        }

        public Builder setDataSchemes(int index, String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataSchemes(index, value);
            return this;
        }

        public Builder addDataSchemes(String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataSchemes(value);
            return this;
        }

        public Builder addAllDataSchemes(Iterable<String> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllDataSchemes(values);
            return this;
        }

        public Builder clearDataSchemes() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearDataSchemes();
            return this;
        }

        public Builder addDataSchemesBytes(ByteString value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataSchemesBytes(value);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<PatternMatcherProto> getDataSchemeSpecsList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getDataSchemeSpecsList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getDataSchemeSpecsCount() {
            return ((IntentFilterProto) this.instance).getDataSchemeSpecsCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public PatternMatcherProto getDataSchemeSpecs(int index) {
            return ((IntentFilterProto) this.instance).getDataSchemeSpecs(index);
        }

        public Builder setDataSchemeSpecs(int index, PatternMatcherProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataSchemeSpecs((IntentFilterProto) index, (int) value);
            return this;
        }

        public Builder setDataSchemeSpecs(int index, PatternMatcherProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataSchemeSpecs((IntentFilterProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDataSchemeSpecs(PatternMatcherProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataSchemeSpecs((IntentFilterProto) value);
            return this;
        }

        public Builder addDataSchemeSpecs(int index, PatternMatcherProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataSchemeSpecs((IntentFilterProto) index, (int) value);
            return this;
        }

        public Builder addDataSchemeSpecs(PatternMatcherProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataSchemeSpecs((IntentFilterProto) builderForValue);
            return this;
        }

        public Builder addDataSchemeSpecs(int index, PatternMatcherProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataSchemeSpecs((IntentFilterProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDataSchemeSpecs(Iterable<? extends PatternMatcherProto> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllDataSchemeSpecs(values);
            return this;
        }

        public Builder clearDataSchemeSpecs() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearDataSchemeSpecs();
            return this;
        }

        public Builder removeDataSchemeSpecs(int index) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).removeDataSchemeSpecs(index);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<AuthorityEntryProto> getDataAuthoritiesList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getDataAuthoritiesList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getDataAuthoritiesCount() {
            return ((IntentFilterProto) this.instance).getDataAuthoritiesCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public AuthorityEntryProto getDataAuthorities(int index) {
            return ((IntentFilterProto) this.instance).getDataAuthorities(index);
        }

        public Builder setDataAuthorities(int index, AuthorityEntryProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataAuthorities((IntentFilterProto) index, (int) value);
            return this;
        }

        public Builder setDataAuthorities(int index, AuthorityEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataAuthorities((IntentFilterProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDataAuthorities(AuthorityEntryProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataAuthorities((IntentFilterProto) value);
            return this;
        }

        public Builder addDataAuthorities(int index, AuthorityEntryProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataAuthorities((IntentFilterProto) index, (int) value);
            return this;
        }

        public Builder addDataAuthorities(AuthorityEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataAuthorities((IntentFilterProto) builderForValue);
            return this;
        }

        public Builder addDataAuthorities(int index, AuthorityEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataAuthorities((IntentFilterProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDataAuthorities(Iterable<? extends AuthorityEntryProto> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllDataAuthorities(values);
            return this;
        }

        public Builder clearDataAuthorities() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearDataAuthorities();
            return this;
        }

        public Builder removeDataAuthorities(int index) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).removeDataAuthorities(index);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<PatternMatcherProto> getDataPathsList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getDataPathsList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getDataPathsCount() {
            return ((IntentFilterProto) this.instance).getDataPathsCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public PatternMatcherProto getDataPaths(int index) {
            return ((IntentFilterProto) this.instance).getDataPaths(index);
        }

        public Builder setDataPaths(int index, PatternMatcherProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataPaths((IntentFilterProto) index, (int) value);
            return this;
        }

        public Builder setDataPaths(int index, PatternMatcherProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataPaths((IntentFilterProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDataPaths(PatternMatcherProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataPaths((IntentFilterProto) value);
            return this;
        }

        public Builder addDataPaths(int index, PatternMatcherProto value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataPaths((IntentFilterProto) index, (int) value);
            return this;
        }

        public Builder addDataPaths(PatternMatcherProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataPaths((IntentFilterProto) builderForValue);
            return this;
        }

        public Builder addDataPaths(int index, PatternMatcherProto.Builder builderForValue) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataPaths((IntentFilterProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDataPaths(Iterable<? extends PatternMatcherProto> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllDataPaths(values);
            return this;
        }

        public Builder clearDataPaths() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearDataPaths();
            return this;
        }

        public Builder removeDataPaths(int index) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).removeDataPaths(index);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public List<String> getDataTypesList() {
            return Collections.unmodifiableList(((IntentFilterProto) this.instance).getDataTypesList());
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getDataTypesCount() {
            return ((IntentFilterProto) this.instance).getDataTypesCount();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public String getDataTypes(int index) {
            return ((IntentFilterProto) this.instance).getDataTypes(index);
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public ByteString getDataTypesBytes(int index) {
            return ((IntentFilterProto) this.instance).getDataTypesBytes(index);
        }

        public Builder setDataTypes(int index, String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setDataTypes(index, value);
            return this;
        }

        public Builder addDataTypes(String value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataTypes(value);
            return this;
        }

        public Builder addAllDataTypes(Iterable<String> values) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addAllDataTypes(values);
            return this;
        }

        public Builder clearDataTypes() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearDataTypes();
            return this;
        }

        public Builder addDataTypesBytes(ByteString value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).addDataTypesBytes(value);
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public boolean hasPriority() {
            return ((IntentFilterProto) this.instance).hasPriority();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public int getPriority() {
            return ((IntentFilterProto) this.instance).getPriority();
        }

        public Builder setPriority(int value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setPriority(value);
            return this;
        }

        public Builder clearPriority() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearPriority();
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public boolean hasHasPartialTypes() {
            return ((IntentFilterProto) this.instance).hasHasPartialTypes();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public boolean getHasPartialTypes() {
            return ((IntentFilterProto) this.instance).getHasPartialTypes();
        }

        public Builder setHasPartialTypes(boolean value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setHasPartialTypes(value);
            return this;
        }

        public Builder clearHasPartialTypes() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearHasPartialTypes();
            return this;
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public boolean hasGetAutoVerify() {
            return ((IntentFilterProto) this.instance).hasGetAutoVerify();
        }

        @Override // android.content.IntentFilterProtoOrBuilder
        public boolean getGetAutoVerify() {
            return ((IntentFilterProto) this.instance).getGetAutoVerify();
        }

        public Builder setGetAutoVerify(boolean value) {
            copyOnWrite();
            ((IntentFilterProto) this.instance).setGetAutoVerify(value);
            return this;
        }

        public Builder clearGetAutoVerify() {
            copyOnWrite();
            ((IntentFilterProto) this.instance).clearGetAutoVerify();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IntentFilterProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.actions_.makeImmutable();
                this.categories_.makeImmutable();
                this.dataSchemes_.makeImmutable();
                this.dataSchemeSpecs_.makeImmutable();
                this.dataAuthorities_.makeImmutable();
                this.dataPaths_.makeImmutable();
                this.dataTypes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IntentFilterProto other = (IntentFilterProto) arg1;
                this.actions_ = visitor.visitList(this.actions_, other.actions_);
                this.categories_ = visitor.visitList(this.categories_, other.categories_);
                this.dataSchemes_ = visitor.visitList(this.dataSchemes_, other.dataSchemes_);
                this.dataSchemeSpecs_ = visitor.visitList(this.dataSchemeSpecs_, other.dataSchemeSpecs_);
                this.dataAuthorities_ = visitor.visitList(this.dataAuthorities_, other.dataAuthorities_);
                this.dataPaths_ = visitor.visitList(this.dataPaths_, other.dataPaths_);
                this.dataTypes_ = visitor.visitList(this.dataTypes_, other.dataTypes_);
                this.priority_ = visitor.visitInt(hasPriority(), this.priority_, other.hasPriority(), other.priority_);
                this.hasPartialTypes_ = visitor.visitBoolean(hasHasPartialTypes(), this.hasPartialTypes_, other.hasHasPartialTypes(), other.hasPartialTypes_);
                this.getAutoVerify_ = visitor.visitBoolean(hasGetAutoVerify(), this.getAutoVerify_, other.hasGetAutoVerify(), other.getAutoVerify_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                String s = input.readString();
                                if (!this.actions_.isModifiable()) {
                                    this.actions_ = GeneratedMessageLite.mutableCopy(this.actions_);
                                }
                                this.actions_.add(s);
                                break;
                            case 18:
                                String s2 = input.readString();
                                if (!this.categories_.isModifiable()) {
                                    this.categories_ = GeneratedMessageLite.mutableCopy(this.categories_);
                                }
                                this.categories_.add(s2);
                                break;
                            case 26:
                                String s3 = input.readString();
                                if (!this.dataSchemes_.isModifiable()) {
                                    this.dataSchemes_ = GeneratedMessageLite.mutableCopy(this.dataSchemes_);
                                }
                                this.dataSchemes_.add(s3);
                                break;
                            case 34:
                                if (!this.dataSchemeSpecs_.isModifiable()) {
                                    this.dataSchemeSpecs_ = GeneratedMessageLite.mutableCopy(this.dataSchemeSpecs_);
                                }
                                this.dataSchemeSpecs_.add((PatternMatcherProto) input.readMessage(PatternMatcherProto.parser(), extensionRegistry));
                                break;
                            case 42:
                                if (!this.dataAuthorities_.isModifiable()) {
                                    this.dataAuthorities_ = GeneratedMessageLite.mutableCopy(this.dataAuthorities_);
                                }
                                this.dataAuthorities_.add((AuthorityEntryProto) input.readMessage(AuthorityEntryProto.parser(), extensionRegistry));
                                break;
                            case 50:
                                if (!this.dataPaths_.isModifiable()) {
                                    this.dataPaths_ = GeneratedMessageLite.mutableCopy(this.dataPaths_);
                                }
                                this.dataPaths_.add((PatternMatcherProto) input.readMessage(PatternMatcherProto.parser(), extensionRegistry));
                                break;
                            case 58:
                                String s4 = input.readString();
                                if (!this.dataTypes_.isModifiable()) {
                                    this.dataTypes_ = GeneratedMessageLite.mutableCopy(this.dataTypes_);
                                }
                                this.dataTypes_.add(s4);
                                break;
                            case 64:
                                this.bitField0_ |= 1;
                                this.priority_ = input.readInt32();
                                break;
                            case 72:
                                this.bitField0_ |= 2;
                                this.hasPartialTypes_ = input.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= 4;
                                this.getAutoVerify_ = input.readBool();
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (IntentFilterProto.class) {
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

    public static IntentFilterProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IntentFilterProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
