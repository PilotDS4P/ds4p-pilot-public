/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.meaningfuluse.displayobjects;

/**
 *
 * @author Duane DeCouteau
 */
public class PatientAllowedObligations {
    private Integer obligationId;
    private String obligation;
    
    public PatientAllowedObligations() {
        
    }

    /**
     * @return the obligationId
     */
    public Integer getObligationId() {
        return obligationId;
    }

    /**
     * @param obligationId the obligationId to set
     */
    public void setObligationId(Integer obligationId) {
        this.obligationId = obligationId;
    }

    /**
     * @return the obligation
     */
    public String getObligation() {
        return obligation;
    }

    /**
     * @param obligation the obligation to set
     */
    public void setObligation(String obligation) {
        this.obligation = obligation;
    }
}
