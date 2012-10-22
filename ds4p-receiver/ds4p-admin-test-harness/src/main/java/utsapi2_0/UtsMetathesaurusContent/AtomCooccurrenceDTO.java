/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */

package UtsMetathesaurusContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for atomCooccurrenceDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="atomCooccurrenceDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.uts.umls.nlm.nih.gov/}dataDTO">
 *       &lt;sequence>
 *         &lt;element name="frequency" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="relatedAtom" type="{http://webservice.uts.umls.nlm.nih.gov/}atomDTO" minOccurs="0"/>
 *         &lt;element name="subheadingCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "atomCooccurrenceDTO", propOrder = {
    "frequency",
    "relatedAtom",
    "subheadingCount",
    "type"
})
public class AtomCooccurrenceDTO
    extends DataDTO
{

    protected long frequency;
    protected AtomDTO relatedAtom;
    protected int subheadingCount;
    protected String type;

    /**
     * Gets the value of the frequency property.
     * 
     */
    public long getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     */
    public void setFrequency(long value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the relatedAtom property.
     * 
     * @return
     *     possible object is
     *     {@link AtomDTO }
     *     
     */
    public AtomDTO getRelatedAtom() {
        return relatedAtom;
    }

    /**
     * Sets the value of the relatedAtom property.
     * 
     * @param value
     *     allowed object is
     *     {@link AtomDTO }
     *     
     */
    public void setRelatedAtom(AtomDTO value) {
        this.relatedAtom = value;
    }

    /**
     * Gets the value of the subheadingCount property.
     * 
     */
    public int getSubheadingCount() {
        return subheadingCount;
    }

    /**
     * Sets the value of the subheadingCount property.
     * 
     */
    public void setSubheadingCount(int value) {
        this.subheadingCount = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
