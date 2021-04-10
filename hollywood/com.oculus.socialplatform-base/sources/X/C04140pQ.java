package X;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: X.0pQ  reason: invalid class name and case insensitive filesystem */
public final class C04140pQ implements Serializable {
    public static final long serialVersionUID = 4939673998947122190L;
    public final AbstractC02230iJ _annotationIntrospector;
    public final AnonymousClass0qG _classIntrospector;
    public final DateFormat _dateFormat;
    public final AnonymousClass0o2 _defaultBase64;
    public final AbstractC04170pT _handlerInstantiator = null;
    public final Locale _locale;
    public final AbstractC04080pC _propertyNamingStrategy;
    public final TimeZone _timeZone;
    public final AnonymousClass0r9 _typeFactory;
    public final AbstractC04540qc<?> _typeResolverBuilder;
    public final AnonymousClass0qO<?> _visibilityChecker;

    /* JADX WARN: Incorrect args count in method signature: (LX/0qG;LX/0iJ;LX/0qO<*>;LX/0pC;LX/0r9;LX/0qc<*>;Ljava/text/DateFormat;LX/0pT;Ljava/util/Locale;Ljava/util/TimeZone;LX/0o2;)V */
    public C04140pQ(AnonymousClass0qG r2, AbstractC02230iJ r3, AnonymousClass0qO r4, AbstractC04080pC r5, AnonymousClass0r9 r6, AbstractC04540qc r7, DateFormat dateFormat, Locale locale, TimeZone timeZone, AnonymousClass0o2 r11) {
        this._classIntrospector = r2;
        this._annotationIntrospector = r3;
        this._visibilityChecker = r4;
        this._propertyNamingStrategy = r5;
        this._typeFactory = r6;
        this._typeResolverBuilder = r7;
        this._dateFormat = dateFormat;
        this._locale = locale;
        this._timeZone = timeZone;
        this._defaultBase64 = r11;
    }

    public final C04140pQ A00(AnonymousClass0o1 r12, AnonymousClass0nR r13) {
        return new C04140pQ(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker.AB9(r12, r13), this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._locale, this._timeZone, this._defaultBase64);
    }

    public final C04140pQ A01(AnonymousClass0r9 r12) {
        if (this._typeFactory == r12) {
            return this;
        }
        return new C04140pQ(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, r12, this._typeResolverBuilder, this._dateFormat, this._locale, this._timeZone, this._defaultBase64);
    }
}
