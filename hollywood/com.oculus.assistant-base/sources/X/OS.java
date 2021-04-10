package X;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class OS implements Serializable {
    public static final long serialVersionUID = 4939673998947122190L;
    public final AbstractC1020qp _annotationIntrospector;
    public final PG _classIntrospector;
    public final DateFormat _dateFormat;
    public final NP _defaultBase64;
    public final OV _handlerInstantiator;
    public final Locale _locale;
    public final OF _propertyNamingStrategy = null;
    public final TimeZone _timeZone;
    public final C0300Pw _typeFactory;
    public final PT _typeResolverBuilder;
    public final PN _visibilityChecker;

    public OS(PG pg, AbstractC1020qp qpVar, PN pn, C0300Pw pw, DateFormat dateFormat, Locale locale, TimeZone timeZone, NP np) {
        this._classIntrospector = pg;
        this._annotationIntrospector = qpVar;
        this._visibilityChecker = pn;
        this._typeFactory = pw;
        this._typeResolverBuilder = null;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = null;
        this._locale = locale;
        this._timeZone = timeZone;
        this._defaultBase64 = np;
    }
}
