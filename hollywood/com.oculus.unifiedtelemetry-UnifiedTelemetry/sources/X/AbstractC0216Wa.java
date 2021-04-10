package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import java.text.DateFormat;

/* renamed from: X.Wa  reason: case insensitive filesystem */
public abstract class AbstractC0216Wa extends jk {
    public static final JsonSerializer<Object> A00 = new FailingSerializer();
    public static final JsonSerializer<Object> A01 = new UnknownSerializer();
    public static final AbstractC0224Wl A02 = new AnonymousClass73(Object.class);
    public final AnonymousClass8L _config = null;
    public DateFormat _dateFormat;
    public JsonSerializer<Object> _keySerializer;
    public final Pu _knownSerializers = null;
    public JsonSerializer<Object> _nullKeySerializer = A00;
    public JsonSerializer<Object> _nullValueSerializer = NullSerializer.A00;
    public final C0100Ib _rootNames = new C0100Ib();
    public final Class<?> _serializationView = null;
    public final S2 _serializerCache = new S2();
    public final AbstractC0140Rw _serializerFactory = null;
    public JsonSerializer<Object> _unknownTypeSerializer = A01;

    @Override // X.jk
    public final WZ A04() {
        return this._config;
    }
}
