/**
 * Triple.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ilc.vikef.ontologymanager;

public class Triple  implements java.io.Serializable {
    private boolean newIsObjectLiteral;

    private java.lang.String newObject;

    private java.lang.String newPredicate;

    private java.lang.String newSubject;

    private boolean sourceIsObjectLiteral;

    private java.lang.String sourceObject;

    private java.lang.String sourcePredicate;

    private java.lang.String sourceSubject;

    public Triple() {
    }

    public Triple(
           boolean newIsObjectLiteral,
           java.lang.String newObject,
           java.lang.String newPredicate,
           java.lang.String newSubject,
           boolean sourceIsObjectLiteral,
           java.lang.String sourceObject,
           java.lang.String sourcePredicate,
           java.lang.String sourceSubject) {
           this.newIsObjectLiteral = newIsObjectLiteral;
           this.newObject = newObject;
           this.newPredicate = newPredicate;
           this.newSubject = newSubject;
           this.sourceIsObjectLiteral = sourceIsObjectLiteral;
           this.sourceObject = sourceObject;
           this.sourcePredicate = sourcePredicate;
           this.sourceSubject = sourceSubject;
    }


    /**
     * Gets the newIsObjectLiteral value for this Triple.
     * 
     * @return newIsObjectLiteral
     */
    public boolean isNewIsObjectLiteral() {
        return newIsObjectLiteral;
    }


    /**
     * Sets the newIsObjectLiteral value for this Triple.
     * 
     * @param newIsObjectLiteral
     */
    public void setNewIsObjectLiteral(boolean newIsObjectLiteral) {
        this.newIsObjectLiteral = newIsObjectLiteral;
    }


    /**
     * Gets the newObject value for this Triple.
     * 
     * @return newObject
     */
    public java.lang.String getNewObject() {
        return newObject;
    }


    /**
     * Sets the newObject value for this Triple.
     * 
     * @param newObject
     */
    public void setNewObject(java.lang.String newObject) {
        this.newObject = newObject;
    }


    /**
     * Gets the newPredicate value for this Triple.
     * 
     * @return newPredicate
     */
    public java.lang.String getNewPredicate() {
        return newPredicate;
    }


    /**
     * Sets the newPredicate value for this Triple.
     * 
     * @param newPredicate
     */
    public void setNewPredicate(java.lang.String newPredicate) {
        this.newPredicate = newPredicate;
    }


    /**
     * Gets the newSubject value for this Triple.
     * 
     * @return newSubject
     */
    public java.lang.String getNewSubject() {
        return newSubject;
    }


    /**
     * Sets the newSubject value for this Triple.
     * 
     * @param newSubject
     */
    public void setNewSubject(java.lang.String newSubject) {
        this.newSubject = newSubject;
    }


    /**
     * Gets the sourceIsObjectLiteral value for this Triple.
     * 
     * @return sourceIsObjectLiteral
     */
    public boolean isSourceIsObjectLiteral() {
        return sourceIsObjectLiteral;
    }


    /**
     * Sets the sourceIsObjectLiteral value for this Triple.
     * 
     * @param sourceIsObjectLiteral
     */
    public void setSourceIsObjectLiteral(boolean sourceIsObjectLiteral) {
        this.sourceIsObjectLiteral = sourceIsObjectLiteral;
    }


    /**
     * Gets the sourceObject value for this Triple.
     * 
     * @return sourceObject
     */
    public java.lang.String getSourceObject() {
        return sourceObject;
    }


    /**
     * Sets the sourceObject value for this Triple.
     * 
     * @param sourceObject
     */
    public void setSourceObject(java.lang.String sourceObject) {
        this.sourceObject = sourceObject;
    }


    /**
     * Gets the sourcePredicate value for this Triple.
     * 
     * @return sourcePredicate
     */
    public java.lang.String getSourcePredicate() {
        return sourcePredicate;
    }


    /**
     * Sets the sourcePredicate value for this Triple.
     * 
     * @param sourcePredicate
     */
    public void setSourcePredicate(java.lang.String sourcePredicate) {
        this.sourcePredicate = sourcePredicate;
    }


    /**
     * Gets the sourceSubject value for this Triple.
     * 
     * @return sourceSubject
     */
    public java.lang.String getSourceSubject() {
        return sourceSubject;
    }


    /**
     * Sets the sourceSubject value for this Triple.
     * 
     * @param sourceSubject
     */
    public void setSourceSubject(java.lang.String sourceSubject) {
        this.sourceSubject = sourceSubject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Triple)) return false;
        Triple other = (Triple) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.newIsObjectLiteral == other.isNewIsObjectLiteral() &&
            ((this.newObject==null && other.getNewObject()==null) || 
             (this.newObject!=null &&
              this.newObject.equals(other.getNewObject()))) &&
            ((this.newPredicate==null && other.getNewPredicate()==null) || 
             (this.newPredicate!=null &&
              this.newPredicate.equals(other.getNewPredicate()))) &&
            ((this.newSubject==null && other.getNewSubject()==null) || 
             (this.newSubject!=null &&
              this.newSubject.equals(other.getNewSubject()))) &&
            this.sourceIsObjectLiteral == other.isSourceIsObjectLiteral() &&
            ((this.sourceObject==null && other.getSourceObject()==null) || 
             (this.sourceObject!=null &&
              this.sourceObject.equals(other.getSourceObject()))) &&
            ((this.sourcePredicate==null && other.getSourcePredicate()==null) || 
             (this.sourcePredicate!=null &&
              this.sourcePredicate.equals(other.getSourcePredicate()))) &&
            ((this.sourceSubject==null && other.getSourceSubject()==null) || 
             (this.sourceSubject!=null &&
              this.sourceSubject.equals(other.getSourceSubject())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += (isNewIsObjectLiteral() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNewObject() != null) {
            _hashCode += getNewObject().hashCode();
        }
        if (getNewPredicate() != null) {
            _hashCode += getNewPredicate().hashCode();
        }
        if (getNewSubject() != null) {
            _hashCode += getNewSubject().hashCode();
        }
        _hashCode += (isSourceIsObjectLiteral() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSourceObject() != null) {
            _hashCode += getSourceObject().hashCode();
        }
        if (getSourcePredicate() != null) {
            _hashCode += getSourcePredicate().hashCode();
        }
        if (getSourceSubject() != null) {
            _hashCode += getSourceSubject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Triple.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("OntologyManagerSoapServiceProvider", "Triple"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newIsObjectLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newIsObjectLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newPredicate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newPredicate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceIsObjectLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceIsObjectLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourcePredicate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourcePredicate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
