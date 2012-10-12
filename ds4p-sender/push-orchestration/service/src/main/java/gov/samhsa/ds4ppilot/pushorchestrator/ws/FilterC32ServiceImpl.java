/*******************************************************************************
 * Open Behavioral Health Information Technology Architecture (OBHITA.org)
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package gov.samhsa.ds4ppilot.pushorchestrator.ws;

import gov.samhsa.ds4ppilot.contract.pushorchestrator.FilterC32ServicePortType;
import gov.samhsa.ds4ppilot.pushorchestrator.PushOrchestrator;
import gov.samhsa.ds4ppilot.schema.pushorchestrator.FilterC32Request;
import gov.samhsa.ds4ppilot.schema.pushorchestrator.FilterC32Response;

import javax.jws.WebService;

/**
 * The Class FilterC32ServiceImpl.
 */
@WebService(targetNamespace = "http://www.samhsa.gov/ds4ppilot/contract/pushorchestrator", portName = "FilterC32Port", serviceName = "FilterC32Service", endpointInterface = "gov.samhsa.ds4ppilot.contract.pushorchestrator.FilterC32ServicePortType")
public class FilterC32ServiceImpl implements FilterC32ServicePortType {

	/** The push orchestrator. */
	private PushOrchestrator pushOrchestrator;

	/**
	 * Instantiates a new filter c32 service implementation.
	 */
	public FilterC32ServiceImpl() {
	}

	/**
	 * Instantiates a new filter C32 service implementation.
	 * 
	 * @param pushOrchestrator
	 *            the push orchestrator
	 */
	public FilterC32ServiceImpl(PushOrchestrator pushOrchestrator) {

		this.pushOrchestrator = pushOrchestrator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.samhsa.ds4ppilot.contract.pushorchestrator.FilterC32ServicePortType
	 * #filterC32(gov.samhsa.ds4ppilot.schema.pushorchestrator.FilterC32Request)
	 */
	@Override
	public FilterC32Response filterC32(FilterC32Request parameters) {

		FilterC32Response c32Response = pushOrchestrator.handleC32Request(
				parameters.getPatientId(), parameters.isPackageAsXdm(),
				parameters.getSenderEmailAddress(),
				parameters.getRecipientEmailAddress());

		return c32Response;
	}

	/**
	 * Sets the push orchestrator.
	 * 
	 * @param pushOrchestrator
	 *            the new push orchestrator
	 */
	public void setPushOrchestrator(PushOrchestrator pushOrchestrator) {
		this.pushOrchestrator = pushOrchestrator;
	}

	
	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	public void afterPropertiesSet() throws Exception {
		if (pushOrchestrator == null) {
			throw new IllegalArgumentException(
					String.format(
							"You must set the pushOrchestrator property of any beans of type {0}.",
							this.getClass()));
		}
	}
}
