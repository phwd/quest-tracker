package X;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: X.0mr  reason: invalid class name and case insensitive filesystem */
public final class C06420mr implements Serializable {
    public static final long serialVersionUID = 4939673998947122190L;
    public final AbstractC02590aM _annotationIntrospector;
    public final AbstractC06700nh _classIntrospector;
    public final DateFormat _dateFormat;
    public final C05830lU _defaultBase64;
    public final AbstractC06450mu _handlerInstantiator;
    public final Locale _locale;
    public final AbstractC06360md _propertyNamingStrategy = null;
    public final TimeZone _timeZone;
    public final C07040od _typeFactory;
    public final AnonymousClass0o5<?> _typeResolverBuilder;
    public final AbstractC06760no<?> _visibilityChecker;

    /* JADX WARN: Incorrect args count in method signature: (LX/0nh;LX/0aM;LX/0no<*>;LX/0md;LX/0od;LX/0o5<*>;Ljava/text/DateFormat;LX/0mu;Ljava/util/Locale;Ljava/util/TimeZone;LX/0lU;)V */
    public C06420mr(AbstractC06700nh r2, AbstractC02590aM r3, AbstractC06760no r4, C07040od r5, DateFormat dateFormat, Locale locale, TimeZone timeZone, C05830lU r9) {
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
