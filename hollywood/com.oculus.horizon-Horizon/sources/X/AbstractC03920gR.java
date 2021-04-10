package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import java.text.DateFormat;

/* renamed from: X.0gR  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03920gR extends AbstractC05190kV {
    public static final JsonSerializer<Object> A00 = new FailingSerializer();
    public static final JsonSerializer<Object> A01 = new UnknownSerializer();
    public static final AbstractC04000gb A02 = new AnonymousClass071(Object.class);
    public final AnonymousClass08W _config = null;
    public DateFormat _dateFormat;
    public JsonSerializer<Object> _keySerializer;
    public final C06160mb _knownSerializers = null;
    public JsonSerializer<Object> _nullKeySerializer = A00;
    public JsonSerializer<Object> _nullValueSerializer = NullSerializer.A00;
    public final C06450nA _rootNames = new C06450nA();
    public final Class<?> _serializationView = null;
    public final C06060mR _serializerCache = new C06060mR();
    public final AbstractC06070mS _serializerFactory = null;
    public JsonSerializer<Object> _unknownTypeSerializer = A01;

    @Override // X.AbstractC05190kV
    public final AbstractC03910gQ A03() {
        return this._config;
    }
}
