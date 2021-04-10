package X;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class dV implements Serializable {
    public static final long serialVersionUID = 4939673998947122190L;
    public final Wp _annotationIntrospector;
    public final VP _classIntrospector;
    public final DateFormat _dateFormat;
    public final C0465pq _defaultBase64;
    public final dS _handlerInstantiator;
    public final Locale _locale;
    public final AbstractC0416hP _propertyNamingStrategy = null;
    public final TimeZone _timeZone;
    public final NT _typeFactory;
    public final V2<?> _typeResolverBuilder;
    public final VI<?> _visibilityChecker;

    /* JADX WARN: Incorrect args count in method signature: (LX/VP;LX/Wp;LX/VI<*>;LX/hP;LX/NT;LX/V2<*>;Ljava/text/DateFormat;LX/dS;Ljava/util/Locale;Ljava/util/TimeZone;LX/pq;)V */
    public dV(VP vp, Wp wp, VI vi, NT nt, DateFormat dateFormat, Locale locale, TimeZone timeZone, C0465pq pqVar) {
        this._classIntrospector = vp;
        this._annotationIntrospector = wp;
        this._visibilityChecker = vi;
        this._typeFactory = nt;
        this._typeResolverBuilder = null;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = null;
        this._locale = locale;
        this._timeZone = timeZone;
        this._defaultBase64 = pqVar;
    }
}
