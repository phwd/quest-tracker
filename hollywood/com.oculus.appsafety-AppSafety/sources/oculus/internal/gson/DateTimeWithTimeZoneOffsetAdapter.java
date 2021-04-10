package oculus.internal.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeWithTimeZoneOffsetAdapter extends TypeAdapter<Long> {
    private static final SimpleDateFormat sdf;

    static {
        SimpleDateFormat tempFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZZ");
        tempFormat.setLenient(false);
        sdf = tempFormat;
    }

    public void write(JsonWriter out, Long date) throws IOException {
        out.value(sdf.format(new Date(date.longValue() * 1000)));
    }

    @Override // com.google.gson.TypeAdapter
    public Long read(JsonReader in) throws IOException {
        try {
            return Long.valueOf(sdf.parse(in.nextString()).getTime() / 1000);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
}
