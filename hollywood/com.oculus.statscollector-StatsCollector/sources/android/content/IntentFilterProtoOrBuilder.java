package android.content;

import android.os.PatternMatcherProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IntentFilterProtoOrBuilder extends MessageLiteOrBuilder {
    String getActions(int i);

    ByteString getActionsBytes(int i);

    int getActionsCount();

    List<String> getActionsList();

    String getCategories(int i);

    ByteString getCategoriesBytes(int i);

    int getCategoriesCount();

    List<String> getCategoriesList();

    AuthorityEntryProto getDataAuthorities(int i);

    int getDataAuthoritiesCount();

    List<AuthorityEntryProto> getDataAuthoritiesList();

    PatternMatcherProto getDataPaths(int i);

    int getDataPathsCount();

    List<PatternMatcherProto> getDataPathsList();

    PatternMatcherProto getDataSchemeSpecs(int i);

    int getDataSchemeSpecsCount();

    List<PatternMatcherProto> getDataSchemeSpecsList();

    String getDataSchemes(int i);

    ByteString getDataSchemesBytes(int i);

    int getDataSchemesCount();

    List<String> getDataSchemesList();

    String getDataTypes(int i);

    ByteString getDataTypesBytes(int i);

    int getDataTypesCount();

    List<String> getDataTypesList();

    boolean getGetAutoVerify();

    boolean getHasPartialTypes();

    int getPriority();

    boolean hasGetAutoVerify();

    boolean hasHasPartialTypes();

    boolean hasPriority();
}
