/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.meaningfuluse.displayobjects;

/**
 *
 * @author Duane DeCouteau
 */
public class UMLSDisplayObject {
    private String code;
    private String displayName;
    
    public UMLSDisplayObject() {
        
    }
    
    public UMLSDisplayObject(String code, String displayname) {
        this.code = code;
        this.displayName = displayname;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
}
