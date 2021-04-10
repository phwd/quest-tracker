package X;

import java.io.Serializable;

/* renamed from: X.rH  reason: case insensitive filesystem */
public final class C1042rH extends Ok implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _cfgEmptyStringsAsObjects;
    public C0372Ui[] _constructorArguments;
    public SV _defaultCreator;
    public C0372Ui[] _delegateArguments;
    public SV _delegateCreator;
    public AbstractC1024qt _delegateType;
    public SV _fromBooleanCreator;
    public SV _fromDoubleCreator;
    public SV _fromIntCreator;
    public SV _fromLongCreator;
    public SV _fromStringCreator;
    public Ss _incompleteParameter;
    public final String _valueTypeDesc;
    public SV _withArgsCreator;

    public C1042rH(AnonymousClass2I r3, AbstractC1024qt qtVar) {
        boolean z;
        String obj;
        if (r3 == null) {
            z = false;
        } else {
            EnumC1023qs qsVar = EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
            z = false;
            if ((qsVar.getMask() & r3._deserFeatures) != 0) {
                z = true;
            }
        }
        this._cfgEmptyStringsAsObjects = z;
        if (qtVar == null) {
            obj = "UNKNOWN TYPE";
        } else {
            obj = qtVar.toString();
        }
        this._valueTypeDesc = obj;
    }

    public static final C1025qv A00(C1042rH rHVar, Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof C1025qv) {
            return (C1025qv) th;
        }
        return new C1025qv(AnonymousClass08.A06("Instantiation of ", rHVar.A05(), " value failed: ", th.getMessage()), th);
    }
}
