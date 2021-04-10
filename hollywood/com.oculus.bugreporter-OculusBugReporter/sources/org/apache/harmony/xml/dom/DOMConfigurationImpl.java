package org.apache.harmony.xml.dom;

import android.icu.text.PluralRules;
import java.util.Map;
import java.util.TreeMap;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;

public final class DOMConfigurationImpl implements DOMConfiguration {
    private static final Map<String, Parameter> PARAMETERS = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    private boolean cdataSections = true;
    private boolean comments = true;
    private boolean datatypeNormalization = false;
    private boolean entities = true;
    private DOMErrorHandler errorHandler;
    private boolean namespaces = true;
    private String schemaLocation;
    private String schemaType;
    private boolean splitCdataSections = true;
    private boolean validate = false;
    private boolean wellFormed = true;

    /* access modifiers changed from: package-private */
    public interface Parameter {
        boolean canSet(DOMConfigurationImpl dOMConfigurationImpl, Object obj);

        Object get(DOMConfigurationImpl dOMConfigurationImpl);

        void set(DOMConfigurationImpl dOMConfigurationImpl, Object obj);
    }

    static {
        PARAMETERS.put("canonical-form", new FixedParameter(false));
        PARAMETERS.put("cdata-sections", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass1 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.cdataSections);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.cdataSections = ((Boolean) value).booleanValue();
            }
        });
        PARAMETERS.put("check-character-normalization", new FixedParameter(false));
        PARAMETERS.put("comments", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass2 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.comments);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.comments = ((Boolean) value).booleanValue();
            }
        });
        PARAMETERS.put("datatype-normalization", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass3 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.datatypeNormalization);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                if (((Boolean) value).booleanValue()) {
                    config.datatypeNormalization = true;
                    config.validate = true;
                    return;
                }
                config.datatypeNormalization = false;
            }
        });
        PARAMETERS.put("element-content-whitespace", new FixedParameter(true));
        PARAMETERS.put("entities", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass4 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.entities);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.entities = ((Boolean) value).booleanValue();
            }
        });
        PARAMETERS.put("error-handler", new Parameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass5 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return config.errorHandler;
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.errorHandler = (DOMErrorHandler) value;
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public boolean canSet(DOMConfigurationImpl config, Object value) {
                return value == null || (value instanceof DOMErrorHandler);
            }
        });
        PARAMETERS.put("infoset", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass6 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(!config.entities && !config.datatypeNormalization && !config.cdataSections && config.wellFormed && config.comments && config.namespaces);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                if (((Boolean) value).booleanValue()) {
                    config.entities = false;
                    config.datatypeNormalization = false;
                    config.cdataSections = false;
                    config.wellFormed = true;
                    config.comments = true;
                    config.namespaces = true;
                }
            }
        });
        PARAMETERS.put("namespaces", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass7 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.namespaces);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.namespaces = ((Boolean) value).booleanValue();
            }
        });
        PARAMETERS.put("namespace-declarations", new FixedParameter(true));
        PARAMETERS.put("normalize-characters", new FixedParameter(false));
        PARAMETERS.put("schema-location", new Parameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass8 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return config.schemaLocation;
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.schemaLocation = (String) value;
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public boolean canSet(DOMConfigurationImpl config, Object value) {
                return value == null || (value instanceof String);
            }
        });
        PARAMETERS.put("schema-type", new Parameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass9 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return config.schemaType;
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.schemaType = (String) value;
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public boolean canSet(DOMConfigurationImpl config, Object value) {
                return value == null || (value instanceof String);
            }
        });
        PARAMETERS.put("split-cdata-sections", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass10 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.splitCdataSections);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.splitCdataSections = ((Boolean) value).booleanValue();
            }
        });
        PARAMETERS.put("validate", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass11 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.validate);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.validate = ((Boolean) value).booleanValue();
            }
        });
        PARAMETERS.put("validate-if-schema", new FixedParameter(false));
        PARAMETERS.put("well-formed", new BooleanParameter() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass12 */

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public Object get(DOMConfigurationImpl config) {
                return Boolean.valueOf(config.wellFormed);
            }

            @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
            public void set(DOMConfigurationImpl config, Object value) {
                config.wellFormed = ((Boolean) value).booleanValue();
            }
        });
    }

    static class FixedParameter implements Parameter {
        final Object onlyValue;

        FixedParameter(Object onlyValue2) {
            this.onlyValue = onlyValue2;
        }

        @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
        public Object get(DOMConfigurationImpl config) {
            return this.onlyValue;
        }

        @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
        public void set(DOMConfigurationImpl config, Object value) {
            if (!this.onlyValue.equals(value)) {
                throw new DOMException(9, "Unsupported value: " + value);
            }
        }

        @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
        public boolean canSet(DOMConfigurationImpl config, Object value) {
            return this.onlyValue.equals(value);
        }
    }

    static abstract class BooleanParameter implements Parameter {
        BooleanParameter() {
        }

        @Override // org.apache.harmony.xml.dom.DOMConfigurationImpl.Parameter
        public boolean canSet(DOMConfigurationImpl config, Object value) {
            return value instanceof Boolean;
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String name, Object value) {
        Parameter parameter = PARAMETERS.get(name);
        return parameter != null && parameter.canSet(this, value);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String name, Object value) throws DOMException {
        Parameter parameter = PARAMETERS.get(name);
        if (parameter != null) {
            try {
                parameter.set(this, value);
            } catch (NullPointerException e) {
                throw new DOMException(17, "Null not allowed for " + name);
            } catch (ClassCastException e2) {
                throw new DOMException(17, "Invalid type for " + name + PluralRules.KEYWORD_RULE_SEPARATOR + ((Object) value.getClass()));
            }
        } else {
            throw new DOMException(8, "No such parameter: " + name);
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String name) throws DOMException {
        Parameter parameter = PARAMETERS.get(name);
        if (parameter != null) {
            return parameter.get(this);
        }
        throw new DOMException(8, "No such parameter: " + name);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        return internalGetParameterNames();
    }

    private static DOMStringList internalGetParameterNames() {
        final String[] result = (String[]) PARAMETERS.keySet().toArray(new String[PARAMETERS.size()]);
        return new DOMStringList() {
            /* class org.apache.harmony.xml.dom.DOMConfigurationImpl.AnonymousClass13 */

            @Override // org.w3c.dom.DOMStringList
            public String item(int index) {
                String[] strArr = result;
                if (index < strArr.length) {
                    return strArr[index];
                }
                return null;
            }

            @Override // org.w3c.dom.DOMStringList
            public int getLength() {
                return result.length;
            }

            @Override // org.w3c.dom.DOMStringList
            public boolean contains(String str) {
                return DOMConfigurationImpl.PARAMETERS.containsKey(str);
            }
        };
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5 A[LOOP:1: B:31:0x00b3->B:32:0x00b5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void normalize(org.w3c.dom.Node r5) {
        /*
        // Method dump skipped, instructions count: 220
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.harmony.xml.dom.DOMConfigurationImpl.normalize(org.w3c.dom.Node):void");
    }

    private void checkTextValidity(CharSequence s) {
        if (this.wellFormed && !isValid(s)) {
            report(2, "wf-invalid-character");
        }
    }

    private boolean isValid(CharSequence text) {
        int i = 0;
        while (true) {
            boolean valid = true;
            if (i >= text.length()) {
                return true;
            }
            char c = text.charAt(i);
            if (!(c == '\t' || c == '\n' || c == '\r' || ((c >= ' ' && c <= 55295) || (c >= 57344 && c <= 65533)))) {
                valid = false;
            }
            if (!valid) {
                return false;
            }
            i++;
        }
    }

    private void report(short severity, String type) {
        DOMErrorHandler dOMErrorHandler = this.errorHandler;
        if (dOMErrorHandler != null) {
            dOMErrorHandler.handleError(new DOMErrorImpl(severity, type));
        }
    }
}
