package X;

import android.net.Uri;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONObject;

public class ep {
    @Nullable
    public List<Uri> A00;
    @Nullable
    public JSONObject A01;

    public ep(@Nullable JSONObject jSONObject, @Nullable List<Uri> list) {
        this.A01 = jSONObject;
        this.A00 = list;
    }
}
