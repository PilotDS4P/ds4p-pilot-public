ds4p-pilot-public
=================

This project is a pilot implementation of the ONC Data Segmentation for Privacy (DS4P) Implementation Guide: http://wiki.siframework.org/Data+Segmentation+for+Privacy+Homepage.

The project allows any EHR to enforce patient, organizational, and jurisdictional privacy policies in the exchange of electronic health recors between providers.

These privacy policies are expressed as XACML policies and executed at runtime by a XACML Policy Decision Point (PDP).

Before a clinical document is exchanged across organizational boundaries, the sending system sends a XACML Request to the PDP. The XACML request contains attributes such as the Purpose Of Use (e.g., treatment or emergency), subject attributes (e.g., organization and NPI of provider), and resource attributes (e.g., requested document and operations for which permission is requested). The PDP retrieves privacy policies from a policy store (or Policy Information Point) and then execute those policies.

The output of the PDP is DENY or PERMIT decision with or without OBLIGATIONS. An example of OBLIGATION could be a request by the patient to redact some sensitive information from the clinical document before exchanging it.