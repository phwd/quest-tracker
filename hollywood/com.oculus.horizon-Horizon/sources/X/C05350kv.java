package X;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: X.0kv  reason: invalid class name and case insensitive filesystem */
public final class C05350kv implements Serializable {
    public static final long serialVersionUID = 4939673998947122190L;
    public final AbstractC04040gi _annotationIntrospector;
    public final AbstractC05740lm _classIntrospector;
    public final DateFormat _dateFormat;
    public final C04780jW _defaultBase64;
    public final AbstractC05380ky _handlerInstantiator;
    public final Locale _locale;
    public final AbstractC05290kh _propertyNamingStrategy = null;
    public final TimeZone _timeZone;
    public final C06240ml _typeFactory;
    public final AbstractC05950mB<?> _typeResolverBuilder;
    public final AbstractC05820lu<?> _visibilityChecker;

    /* JADX WARN: Incorrect args count in method signature: (LX/0lm;LX/0gi;LX/0lu<*>;LX/0kh;LX/0ml;LX/0mB<*>;Ljava/text/DateFormat;LX/0ky;Ljava/util/Locale;Ljava/util/TimeZone;LX/0jW;)V */
    public C05350kv(AbstractC05740lm r2, AbstractC04040gi r3, AbstractC05820lu r4, C06240ml r5, DateFormat dateFormat, Locale locale, TimeZone timeZone, C04780jW r9) {
        this._classIntrospector = r2;
        this._annotationIntrospector = r3;
        this._visibilityChecker = r4;
        this._typeFactory = r5;
        this._typeResolverBuilder = null;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = null;
        this._locale = locale;
        this._timeZone = timeZone;
        this._defaultBase64 = r9;
    }
}
