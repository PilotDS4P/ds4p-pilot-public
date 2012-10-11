ds4p-pilot-public
=================

This project is a pilot implementation of the ONC Data Segmentation for Privacy (DS4P) Implementation Guide: http://wiki.siframework.org/Data+Segmentation+for+Privacy+Homepage.

The project allows any EHR to enforce patient, organizational, and jurisdictional privacy policies in the exchange of electronic health recors between providers.

These privacy policies are expressed as XACML policies and executed at runtime by a XACML Policy Decision Point (PDP).

Before a clinical document is exchanged across organizational boundaries, the sending system sends an XACML Request to the PDP. The PDP retrieves privacy policies from a policy store (or Policy Information Point) and then execute those policies.