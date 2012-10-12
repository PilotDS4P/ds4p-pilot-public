package gov.samhsa.ds4ppilot.pushorchestrator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import gov.samhsa.ds4ppilot.common.exception.DS4PException;
import gov.samhsa.ds4ppilot.pushorchestrator.PushOrchestratorImpl;
import gov.samhsa.ds4ppilot.pushorchestrator.c32getter.C32Getter;
import gov.samhsa.ds4ppilot.pushorchestrator.c32getter.C32GetterImpl;
import gov.samhsa.ds4ppilot.pushorchestrator.contexthandler.ContextHandler;
import gov.samhsa.ds4ppilot.pushorchestrator.contexthandler.ContextHandlerImpl;
import gov.samhsa.ds4ppilot.pushorchestrator.documentprocessor.DocumentProcessor;
import gov.samhsa.ds4ppilot.pushorchestrator.documentprocessor.DocumentProcessorImpl;
import gov.samhsa.ds4ppilot.schema.documentprocessor.ProcessDocumentResponse;
import gov.samhsa.ds4ppilot.schema.pushorchestrator.FilterC32Response;
import gov.va.ehtac.ds4p.ws.EnforcePolicy.Xsparesource;
import gov.va.ehtac.ds4p.ws.EnforcePolicy.Xspasubject;
import gov.va.ehtac.ds4p.ws.EnforcePolicyResponse.Return;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.activation.DataHandler;

public class PushOrchestratorImplTest {

	private static boolean packageXdm;
	private static String patientIdDeny;
	private static String patientIdPermit;
	private static String senderEmailAddress;
	private static String reciepientEmailAddress;
	
	private final static String PERMIT = "Permit";

	@Before
	public void setUp() {
		patientIdPermit = "PUI100010060001";
		patientIdDeny = "PUI100010060006";
		packageXdm = true;
		senderEmailAddress = "leo.smith@direct.obhita-stage.org";
		reciepientEmailAddress = "Duane_Decouteau@direct.healthvault.com";
	}

	@Ignore("This test should be configured to run as an integration test.")
	@Test
	public void testHandleC32Request_Deny() {

		final String contextHandlerEndpointAddress = "http://174.78.146.228:8080/DS4PACSServices/DS4PContextHandler";
		ContextHandler contextHandler = new ContextHandlerImpl(
				contextHandlerEndpointAddress);

		final String c32ServiceEndpointAddress = "http://localhost/Rem.Web/C32Service.svc";
		C32Getter c32Getter = new C32GetterImpl(
				c32ServiceEndpointAddress);

		final String documentProcessorEndpointAddress = "http://localhost:90/DocumentProcessor/services/processdocumentservice";
		DocumentProcessor documentProcessor = new DocumentProcessorImpl(
				documentProcessorEndpointAddress);
		
		DataHandlerToBytesConverter dataHandlerToBytesConverter = new DataHandlerToBytesConverterImpl();

		PushOrchestratorImpl pushOrchestrator = new PushOrchestratorImpl(
				contextHandler, c32Getter, documentProcessor, dataHandlerToBytesConverter);
		
		pushOrchestrator.setSubjectPurposeOfUse("TREAT");
		pushOrchestrator.setSubjectLocality("2.16.840.1.113883.3.467");
		pushOrchestrator.setOrganization("SAMHSA");
		pushOrchestrator.setOrganizationId("FEiSystems");;
		
		pushOrchestrator.setResourceName("NwHINDirectSend");
		pushOrchestrator.setResourceType("C32");
		pushOrchestrator.setResourceAction("Execute");
		
		FilterC32Response c32Response = pushOrchestrator.handleC32Request(
				patientIdDeny, packageXdm, senderEmailAddress,
				reciepientEmailAddress);

		assertEquals("Deny", c32Response.getPdpDecision());
	}

	@Ignore("This test should be configured to run as an integration test.")
	@Test
	public void testHandleC32Request_Permit() throws Exception {

		final String contextHandlerEndpointAddress = "http://174.78.146.228:8080/DS4PACSServices/DS4PContextHandler";
		ContextHandlerImpl contextHandler = new ContextHandlerImpl(
				contextHandlerEndpointAddress);

		final String endpointAddress = "http://localhost/Rem.Web/C32Service.svc";
		C32GetterImpl c32Getter = new C32GetterImpl(endpointAddress);

		final String documentProcessorEndpointAddress = "http://localhost:90/DocumentProcessor/services/processdocumentservice";
		DocumentProcessorImpl documentProcessor = new DocumentProcessorImpl(
				documentProcessorEndpointAddress);
		
		DataHandlerToBytesConverter dataHandlerToBytesConverter = new DataHandlerToBytesConverterImpl();

		PushOrchestratorImpl pushOrchestrator = new PushOrchestratorImpl(
				contextHandler, c32Getter, documentProcessor, dataHandlerToBytesConverter);
		
		pushOrchestrator.setSubjectPurposeOfUse("TREAT");
		pushOrchestrator.setSubjectLocality("2.16.840.1.113883.3.467");
		pushOrchestrator.setOrganization("SAMHSA");
		pushOrchestrator.setOrganizationId("FEiSystems");;
		
		pushOrchestrator.setResourceName("NwHINDirectSend");
		pushOrchestrator.setResourceType("C32");
		pushOrchestrator.setResourceAction("Execute");
		
		FilterC32Response c32Response = pushOrchestrator.handleC32Request(
				patientIdPermit, packageXdm, senderEmailAddress,
				reciepientEmailAddress);
		WritePackageToFile(c32Response);

		assertEquals(PERMIT, c32Response.getPdpDecision());
	}

	@Test(expected = DS4PException.class)
	public void testHandleC32Request_ThrowsExceptionWhenContextHandlerEnforcePolicyThrowsException() {
		// Arrange
		ContextHandler contextHandlerMock = mock(ContextHandler.class);
		C32Getter c32GetterMock = mock(C32Getter.class);
		DocumentProcessor documentProcessorMock = mock(DocumentProcessor.class);
		DataHandlerToBytesConverter dataHandlerToBytesConverterMock = mock(DataHandlerToBytesConverter.class);
		PushOrchestratorImpl sut = new PushOrchestratorImpl(contextHandlerMock,
				c32GetterMock, documentProcessorMock, dataHandlerToBytesConverterMock);

		when(
				contextHandlerMock.enforcePolicy(isA(Xspasubject.class),
						isA(Xsparesource.class))).thenThrow(
				new RuntimeException());

		// Act
		sut.handleC32Request(null, false, null, null);

		// Assert
		verify(contextHandlerMock).enforcePolicy(isA(Xspasubject.class),
						isA(Xsparesource.class));
	}
	
	@Test
	public void testHandleC32Request_WorksWhenHavingNotPermitDecision() {
		// Arrange
		ContextHandler contextHandlerMock = mock(ContextHandler.class);
		C32Getter c32GetterMock = mock(C32Getter.class);
		DocumentProcessor documentProcessorMock = mock(DocumentProcessor.class);
		DataHandlerToBytesConverter dataHandlerToBytesConverterMock = mock(DataHandlerToBytesConverter.class);
		PushOrchestratorImpl sut = new PushOrchestratorImpl(contextHandlerMock,
				c32GetterMock, documentProcessorMock, dataHandlerToBytesConverterMock);
		
		Return returnMock = mock(Return.class);
		when(returnMock.getPdpDecision()).thenReturn("Deny");
		
		when(
				contextHandlerMock.enforcePolicy(isA(Xspasubject.class),
						isA(Xsparesource.class))).thenReturn(returnMock);
		
		final String patientId = "patientId";

		// Act
		FilterC32Response c32Response = sut.handleC32Request(patientId, true, "senderEmailAddress", "recipientEmailAddress");

		// Assert
		assertTrue(!(PERMIT.equals(c32Response.getPdpDecision())));
		assertNull(c32Response.getFilteredStreamBody());
		assertNull(c32Response.getMaskedDocument());
		assertEquals(patientId, c32Response.getPatientId());
	}
	
	@Test
	public void testHandleC32Request_WorksWhenHavingPermitDecision() throws IOException {
		// Arrange
		ContextHandler contextHandlerMock = mock(ContextHandler.class);
		C32Getter c32GetterMock = mock(C32Getter.class);
		DocumentProcessor documentProcessorMock = mock(DocumentProcessor.class);
		DataHandlerToBytesConverter dataHandlerToBytesConverterMock = mock(DataHandlerToBytesConverter.class);
		PushOrchestratorImpl sut = new PushOrchestratorImpl(contextHandlerMock,
				c32GetterMock, documentProcessorMock, dataHandlerToBytesConverterMock);
		
		Return returnMock = mock(Return.class);
		when(returnMock.getPdpDecision()).thenReturn(PERMIT);

		when(
				contextHandlerMock.enforcePolicy(isA(Xspasubject.class),
						isA(Xsparesource.class))).thenReturn(returnMock);
		
		final String patientId = "patientId";
		final String c32 = "c32";
		final String recipientEmailAddress = "recipientEmailAddress";
		final String senderEmailAddress = "senderEmailAddress";
		final boolean packageAsXdm = true;
		final String maskedDocument = "maskedDocument";
		final byte[] filteredStreamBody = new byte[1];
		
		when(c32GetterMock.getC32(patientId)).thenReturn(c32);
		
		ProcessDocumentResponse processDocumentResponseMock = mock(ProcessDocumentResponse.class);
		when(processDocumentResponseMock.getMaskedDocument()).thenReturn(maskedDocument);
		
		DataHandler dataHandlerMock = mock(DataHandler.class);
		when(processDocumentResponseMock.getProcessedDocument()).thenReturn(dataHandlerMock);
		
		when(documentProcessorMock.processDocument(eq(c32), anyString(), eq(packageAsXdm), eq(senderEmailAddress), eq(recipientEmailAddress))).thenReturn(processDocumentResponseMock);
		
		when(dataHandlerToBytesConverterMock.toByteArray(isA(DataHandler.class))).thenReturn(filteredStreamBody);

		// Act
		FilterC32Response c32Response = sut.handleC32Request(patientId, packageAsXdm, senderEmailAddress, recipientEmailAddress);

		// Assert
		assertEquals(PERMIT, c32Response.getPdpDecision());
		assertEquals(filteredStreamBody, c32Response.getFilteredStreamBody());
		assertEquals(maskedDocument, c32Response.getMaskedDocument());
		assertEquals(patientId, c32Response.getPatientId());
	}

	@SuppressWarnings("unused")
	private static void DisplayC32(String xml) throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "4");

		Document xmlDocument = loadXMLFrom(xml);

		transformer.transform(new DOMSource(xmlDocument), new StreamResult(
				new OutputStreamWriter(System.out, "UTF-8")));
		System.out.println("\n\n\r");
	}

	private static Document loadXMLFrom(String xml) throws Exception {
		InputSource is = new InputSource(new StringReader(xml));
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is);
		return doc;
	}

	/**
	 * @param c32Response
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void WritePackageToFile(FilterC32Response c32Response)
			throws IOException, FileNotFoundException {

		byte[] bytes = c32Response.getFilteredStreamBody();

		FileOutputStream fos = new FileOutputStream("xdm.zip");
		try {
			fos.write(bytes);
		} finally {
			fos.close();
		}
	}
}
