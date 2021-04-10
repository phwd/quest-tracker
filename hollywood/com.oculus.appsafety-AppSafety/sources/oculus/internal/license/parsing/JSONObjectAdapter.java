package oculus.internal.license.parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.json.JSONObject;

public final class JSONObjectAdapter implements JsonDeserializer<JSONObject>, JsonSerializer<JSONObject> {
    @Override // com.google.gson.JsonDeserializer
    public JSONObject deserialize(JsonElement json, Type t, JsonDeserializationContext context) throws JsonParseException {
        try {
            return new JSONObject(json.toString());
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    public JsonElement serialize(JSONObject object, Type t, JsonSerializationContext context) {
        return new JsonParser().parse(object.toString());
    }
}
