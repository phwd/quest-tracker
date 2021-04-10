package oculus.internal.license.parsing;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.oculus.license.Category;
import java.io.IOException;

public final class LicenseCategoryAdapter extends TypeAdapter<Category> {
    public void write(JsonWriter out, Category category) throws IOException {
        out.value(category.toString());
    }

    @Override // com.google.gson.TypeAdapter
    public Category read(JsonReader in) throws IOException {
        return Category.fromString(in.nextString());
    }
}
