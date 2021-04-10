package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    static {
        for (Class cls : new Class[]{Calendar.class, GregorianCalendar.class, Date.class, java.util.Date.class, Timestamp.class, TimeZone.class}) {
            _classNames.add(cls.getName());
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        return new StdDeserializer[]{CalendarDeserializer.instance, DateDeserializer.instance, CalendarDeserializer.gregorianInstance, SqlDateDeserializer.instance, TimestampDeserializer.instance, TimeZoneDeserializer.instance};
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (!_classNames.contains(str)) {
            return null;
        }
        if (cls == Calendar.class) {
            return CalendarDeserializer.instance;
        }
        if (cls == java.util.Date.class) {
            return DateDeserializer.instance;
        }
        if (cls == Date.class) {
            return SqlDateDeserializer.instance;
        }
        if (cls == Timestamp.class) {
            return TimestampDeserializer.instance;
        }
        if (cls == TimeZone.class) {
            return TimeZoneDeserializer.instance;
        }
        if (cls == GregorianCalendar.class) {
            return CalendarDeserializer.gregorianInstance;
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + str);
    }

    protected static abstract class DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements ContextualDeserializer {
        protected final DateFormat _customFormat;
        protected final String _formatString;

        /* access modifiers changed from: protected */
        public abstract DateBasedDeserializer<T> withDateFormat(DateFormat dateFormat, String str);

        protected DateBasedDeserializer(Class<?> cls) {
            super(cls);
            this._customFormat = null;
            this._formatString = null;
        }

        protected DateBasedDeserializer(DateBasedDeserializer<T> dateBasedDeserializer, DateFormat dateFormat, String str) {
            super(dateBasedDeserializer._valueClass);
            this._customFormat = dateFormat;
            this._formatString = str;
        }

        @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            JsonFormat.Value findFormat;
            DateFormat dateFormat;
            if (!(beanProperty == null || (findFormat = deserializationContext.getAnnotationIntrospector().findFormat((Annotated) beanProperty.getMember())) == null)) {
                TimeZone timeZone = findFormat.getTimeZone();
                String pattern = findFormat.getPattern();
                if (pattern.length() > 0) {
                    Locale locale = findFormat.getLocale();
                    if (locale == null) {
                        locale = deserializationContext.getLocale();
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
                    if (timeZone == null) {
                        timeZone = deserializationContext.getTimeZone();
                    }
                    simpleDateFormat.setTimeZone(timeZone);
                    return withDateFormat(simpleDateFormat, pattern);
                } else if (timeZone != null) {
                    DateFormat dateFormat2 = deserializationContext.getConfig().getDateFormat();
                    if (dateFormat2.getClass() == StdDateFormat.class) {
                        dateFormat = ((StdDateFormat) dateFormat2).withTimeZone(timeZone);
                    } else {
                        dateFormat = (DateFormat) dateFormat2.clone();
                        dateFormat.setTimeZone(timeZone);
                    }
                    return withDateFormat(dateFormat, pattern);
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
        public java.util.Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            java.util.Date parse;
            if (this._customFormat == null || jsonParser.getCurrentToken() != JsonToken.VALUE_STRING) {
                return super._parseDate(jsonParser, deserializationContext);
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return getEmptyValue();
            }
            synchronized (this._customFormat) {
                try {
                    parse = this._customFormat.parse(trim);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Failed to parse Date value '" + trim + "' (format: \"" + this._formatString + "\"): " + e.getMessage());
                } catch (Throwable th) {
                    throw th;
                }
            }
            return parse;
        }
    }

    @JacksonStdImpl
    public static class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
        public static final CalendarDeserializer gregorianInstance = new CalendarDeserializer(GregorianCalendar.class);
        public static final CalendarDeserializer instance = new CalendarDeserializer();
        protected final Class<? extends Calendar> _calendarClass;

        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer, com.fasterxml.jackson.databind.deser.ContextualDeserializer
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public CalendarDeserializer() {
            super(Calendar.class);
            this._calendarClass = null;
        }

        public CalendarDeserializer(Class<? extends Calendar> cls) {
            super(cls);
            this._calendarClass = cls;
        }

        public CalendarDeserializer(CalendarDeserializer calendarDeserializer, DateFormat dateFormat, String str) {
            super(calendarDeserializer, dateFormat, str);
            this._calendarClass = calendarDeserializer._calendarClass;
        }

        /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$CalendarDeserializer' to match base method */
        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer
        public DateBasedDeserializer<Calendar> withDateFormat(DateFormat dateFormat, String str) {
            return new CalendarDeserializer(this, dateFormat, str);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            Class<? extends Calendar> cls = this._calendarClass;
            if (cls == null) {
                return deserializationContext.constructCalendar(_parseDate);
            }
            try {
                Calendar calendar = (Calendar) cls.newInstance();
                calendar.setTimeInMillis(_parseDate.getTime());
                TimeZone timeZone = deserializationContext.getTimeZone();
                if (timeZone != null) {
                    calendar.setTimeZone(timeZone);
                }
                return calendar;
            } catch (Exception e) {
                throw deserializationContext.instantiationException(this._calendarClass, e);
            }
        }
    }

    public static class DateDeserializer extends DateBasedDeserializer<java.util.Date> {
        public static final DateDeserializer instance = new DateDeserializer();

        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer, com.fasterxml.jackson.databind.deser.ContextualDeserializer
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public DateDeserializer() {
            super(java.util.Date.class);
        }

        public DateDeserializer(DateDeserializer dateDeserializer, DateFormat dateFormat, String str) {
            super(dateDeserializer, dateFormat, str);
        }

        /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateDeserializer' to match base method */
        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer
        public DateBasedDeserializer<java.util.Date> withDateFormat(DateFormat dateFormat, String str) {
            return new DateDeserializer(this, dateFormat, str);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public java.util.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseDate(jsonParser, deserializationContext);
        }
    }

    public static class SqlDateDeserializer extends DateBasedDeserializer<Date> {
        public static final SqlDateDeserializer instance = new SqlDateDeserializer();

        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer, com.fasterxml.jackson.databind.deser.ContextualDeserializer
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public SqlDateDeserializer() {
            super(Date.class);
        }

        public SqlDateDeserializer(SqlDateDeserializer sqlDateDeserializer, DateFormat dateFormat, String str) {
            super(sqlDateDeserializer, dateFormat, str);
        }

        /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$SqlDateDeserializer' to match base method */
        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer
        public DateBasedDeserializer<Date> withDateFormat(DateFormat dateFormat, String str) {
            return new SqlDateDeserializer(this, dateFormat, str);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            return new Date(_parseDate.getTime());
        }
    }

    public static class TimestampDeserializer extends DateBasedDeserializer<Timestamp> {
        public static final TimestampDeserializer instance = new TimestampDeserializer();

        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer, com.fasterxml.jackson.databind.deser.ContextualDeserializer
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return super.createContextual(deserializationContext, beanProperty);
        }

        public TimestampDeserializer() {
            super(Timestamp.class);
        }

        public TimestampDeserializer(TimestampDeserializer timestampDeserializer, DateFormat dateFormat, String str) {
            super(timestampDeserializer, dateFormat, str);
        }

        /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$TimestampDeserializer' to match base method */
        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer
        public DateBasedDeserializer<Timestamp> withDateFormat(DateFormat dateFormat, String str) {
            return new TimestampDeserializer(this, dateFormat, str);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return new Timestamp(_parseDate(jsonParser, deserializationContext).getTime());
        }
    }

    protected static class TimeZoneDeserializer extends FromStringDeserializer<TimeZone> {
        public static final TimeZoneDeserializer instance = new TimeZoneDeserializer();

        public TimeZoneDeserializer() {
            super(TimeZone.class);
        }

        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public TimeZone _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return TimeZone.getTimeZone(str);
        }
    }
}
