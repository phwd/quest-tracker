package oculus.internal.license.parsing;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.oculus.license.FilterClass;
import java.io.IOException;

public final class FilterClassAdapter extends TypeAdapter<FilterClass> {
    public void write(JsonWriter out, FilterClass filterClass) throws IOException {
        out.value(filterClass.toString());
    }

    @Override // com.google.gson.TypeAdapter
    public FilterClass read(JsonReader in) throws IOException {
        return FilterClass.fromString(in.nextString());
    }
}
