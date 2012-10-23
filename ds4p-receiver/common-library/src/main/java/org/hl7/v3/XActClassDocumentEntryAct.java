/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.17 at 12:15:19 PM MDT 
//


package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for x_ActClassDocumentEntryAct.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="x_ActClassDocumentEntryAct">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="ACT"/>
 *     &lt;enumeration value="ACCM"/>
 *     &lt;enumeration value="CONS"/>
 *     &lt;enumeration value="CTTEVENT"/>
 *     &lt;enumeration value="INC"/>
 *     &lt;enumeration value="INFRM"/>
 *     &lt;enumeration value="PCPR"/>
 *     &lt;enumeration value="REG"/>
 *     &lt;enumeration value="SPCTRT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "x_ActClassDocumentEntryAct")
@XmlEnum
public enum XActClassDocumentEntryAct {

    ACT,
    ACCM,
    CONS,
    CTTEVENT,
    INC,
    INFRM,
    PCPR,
    REG,
    SPCTRT;

    public String value() {
        return name();
    }

    public static XActClassDocumentEntryAct fromValue(String v) {
        return valueOf(v);
    }

}
