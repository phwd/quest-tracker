package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.C1042rH;
import X.EnumC1023qs;
import X.NX;
import X.O5;
import X.Ok;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase implements AbstractC0264Od {
    public static final long serialVersionUID = -2003828398549708958L;
    public final AbstractC1024qt _collectionType;
    public final JsonDeserializer _delegateDeserializer;
    public final JsonDeserializer _valueDeserializer;
    public final Ok _valueInstantiator;
    public final PR _valueTypeDeserializer;

    public CollectionDeserializer(AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer, PR pr, Ok ok, JsonDeserializer jsonDeserializer2) {
        super(qtVar._class);
        this._collectionType = qtVar;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = pr;
        this._valueInstantiator = ok;
        this._delegateDeserializer = jsonDeserializer2;
    }

    private final void A0P(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Collection collection) {
        Object A09;
        if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer jsonDeserializer = this._valueDeserializer;
            PR pr = this._valueTypeDeserializer;
            if (qiVar.A0U() == NX.VALUE_NULL) {
                A09 = null;
            } else if (pr == null) {
                A09 = jsonDeserializer.A0C(qiVar, qrVar);
            } else {
                A09 = jsonDeserializer.A09(qiVar, qrVar, pr);
            }
            collection.add(A09);
            return;
        }
        qrVar.A0J();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    /* renamed from: A0N */
    public Collection A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A03;
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A03 = this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
        } else {
            if (qiVar.A0U() == NX.VALUE_STRING) {
                String A0p = qiVar.A0p();
                if (A0p.length() == 0) {
                    A03 = this._valueInstantiator.A03(qrVar, A0p);
                }
            }
            return A0O(qiVar, qrVar, (Collection) this._valueInstantiator.A01(qrVar));
        }
        return (Collection) A03;
    }

    public final Collection A0O(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Collection collection) {
        Object A09;
        Object A092;
        if (!(this instanceof ArrayBlockingQueueDeserializer)) {
            if (qiVar.A0i()) {
                JsonDeserializer jsonDeserializer = this._valueDeserializer;
                PR pr = this._valueTypeDeserializer;
                while (true) {
                    NX A0o = qiVar.A0o();
                    if (A0o == NX.END_ARRAY) {
                        break;
                    }
                    if (A0o == NX.VALUE_NULL) {
                        A092 = null;
                    } else if (pr == null) {
                        A092 = jsonDeserializer.A0C(qiVar, qrVar);
                    } else {
                        A092 = jsonDeserializer.A09(qiVar, qrVar, pr);
                    }
                    collection.add(A092);
                }
            } else {
                A0P(qiVar, qrVar, collection);
            }
            return collection;
        } else if (!qiVar.A0i()) {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
            A0P(qiVar, qrVar, arrayBlockingQueue);
            return arrayBlockingQueue;
        } else {
            ArrayList arrayList = new ArrayList();
            JsonDeserializer jsonDeserializer2 = this._valueDeserializer;
            PR pr2 = this._valueTypeDeserializer;
            while (true) {
                NX A0o2 = qiVar.A0o();
                if (A0o2 == NX.END_ARRAY) {
                    break;
                }
                if (A0o2 == NX.VALUE_NULL) {
                    A09 = null;
                } else if (pr2 == null) {
                    A09 = jsonDeserializer2.A0C(qiVar, qrVar);
                } else {
                    A09 = jsonDeserializer2.A09(qiVar, qrVar, pr2);
                }
                arrayList.add(A09);
            }
            if (collection == null) {
                return new ArrayBlockingQueue(arrayList.size(), false, arrayList);
            }
            collection.addAll(arrayList);
            return collection;
        }
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        JsonDeserializer jsonDeserializer;
        AbstractC1024qt qtVar;
        Ok ok = this._valueInstantiator;
        if (ok == null || !ok.A08()) {
            jsonDeserializer = null;
        } else if (!(ok instanceof C1042rH) || (qtVar = ((C1042rH) ok)._delegateType) == null) {
            StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
            sb.append(this._collectionType);
            sb.append(": value instantiator (");
            sb.append(this._valueInstantiator.getClass().getName());
            sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            throw new IllegalArgumentException(sb.toString());
        } else {
            jsonDeserializer = qrVar.A08(qtVar, o5);
        }
        JsonDeserializer A05 = StdDeserializer.A05(qrVar, o5, this._valueDeserializer);
        if (A05 == null) {
            A05 = qrVar.A08(this._collectionType.A04(), o5);
        } else if (A05 instanceof AbstractC0264Od) {
            A05 = ((AbstractC0264Od) A05).A1X(qrVar, o5);
        }
        PR pr = this._valueTypeDeserializer;
        if (pr != null) {
            pr = pr.A03(o5);
        }
        if (!(this instanceof ArrayBlockingQueueDeserializer)) {
            if (jsonDeserializer == this._delegateDeserializer && A05 == this._valueDeserializer && pr == this._valueTypeDeserializer) {
                return this;
            }
            return new CollectionDeserializer(this._collectionType, A05, pr, this._valueInstantiator, jsonDeserializer);
        } else if (jsonDeserializer == this._delegateDeserializer && A05 == this._valueDeserializer && pr == this._valueTypeDeserializer) {
            return this;
        } else {
            return new ArrayBlockingQueueDeserializer(this._collectionType, A05, pr, this._valueInstantiator, jsonDeserializer);
        }
    }
}
