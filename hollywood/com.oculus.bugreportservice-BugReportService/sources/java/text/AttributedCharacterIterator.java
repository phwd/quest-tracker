package java.text;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface AttributedCharacterIterator extends CharacterIterator {
    Set getAllAttributeKeys();

    Object getAttribute(Attribute attribute);

    Map getAttributes();

    int getRunLimit();

    int getRunLimit(Attribute attribute);

    int getRunStart();

    int getRunStart(Attribute attribute);

    public static class Attribute implements Serializable {
        public static final Attribute INPUT_METHOD_SEGMENT = new Attribute("input_method_segment");
        public static final Attribute LANGUAGE = new Attribute("language");
        public static final Attribute READING = new Attribute("reading");
        private static final Map instanceMap = new HashMap(7);
        private static final long serialVersionUID = -9142742483513960612L;
        private String name;

        protected Attribute(String str) {
            this.name = str;
            if (getClass() == Attribute.class) {
                instanceMap.put(str, this);
            }
        }

        public final boolean equals(Object obj) {
            return super.equals(obj);
        }

        public final int hashCode() {
            return super.hashCode();
        }

        public String toString() {
            return getClass().getName() + "(" + this.name + ")";
        }

        /* access modifiers changed from: protected */
        public String getName() {
            return this.name;
        }

        /* access modifiers changed from: protected */
        public Object readResolve() {
            if (getClass() == Attribute.class) {
                Attribute attribute = (Attribute) instanceMap.get(getName());
                if (attribute != null) {
                    return attribute;
                }
                throw new InvalidObjectException("unknown attribute name");
            }
            throw new InvalidObjectException("subclass didn't correctly implement readResolve");
        }
    }
}
