ds4p-pilot-public
=================

This project is a pilot implementation of the ONC Data Segmentation for Privacy (DS4P) Implementation Guide: http://wiki.siframework.org/Data+Segmentation+for+Privacy+Homepage.

The project allows any EHR to enforce patient, organizational, and jurisdictional privacy policies in the exchange of electronic health records between providers. In addition to enforcing privacy policies, the system also provides a clinical document segmentation engine. Segmentation refers to the tagging, redaction, masking, and encrypting of clinical documents. The system is based on SOA architecture. Major components communication through a set of WSDL interfaces.

Privacy policies are expressed as XACML policies and executed at runtime by a XACML Policy Decision Point (PDP). Before a clinical document is exchanged across organizational boundaries, the sending system sends a XACML Request to the PDP. The XACML request contains attributes such as the Purpose Of Use (e.g., treatment or emergency), subject attributes (e.g., organization and NPI of provider), and resource attributes (e.g., requested document and operations for which permission is requested). The PDP retrieves privacy policies from a policy store (or Policy Information Point) and then execute those policies.

The output of the PDP is DENY or PERMIT decision with or without OBLIGATIONS. An example of OBLIGATION could be a request by the patient to redact some sensitive information from the clinical document before exchanging it.

When the PDP decision is a PERMIT with OBLIGATIONS, (1) the clincical document is retrieved from the EHR, (2) clinical facts (such as problem and medication entries) are extracted from the clinical document, (3) the clinical facts, the Purpose of Use, and the Obligations are sent to the Drools rules engine, and (4) the Drools rules engine returns a set of directives for segmenting the clinical document.
 
