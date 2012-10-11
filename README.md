ds4p-pilot-public
=================

Architecture
=================

This project is a pilot implementation of the ONC Data Segmentation for Privacy (DS4P) Implementation Guide: http://wiki.siframework.org/Data+Segmentation+for+Privacy+Homepage.

The project allows any EHR to enforce patient, organizational, and jurisdictional privacy policies in the exchange of electronic health records between providers. In addition to enforcing privacy policies, the system also provides a clinical document segmentation engine. Segmentation refers to the tagging, redaction, masking, and encrypting of clinical documents. The system is based on SOA architecture. Major components communication through a set of WSDL interfaces.

Privacy policies are expressed as XACML policies and executed at runtime by a XACML Policy Decision Point (PDP). Before a clinical document is exchanged across organizational boundaries, the sending system sends a XACML Request to the PDP. The XACML request contains attributes such as the Purpose Of Use (e.g., treatment or emergency), subject attributes (e.g., organization and NPI of provider), and resource attributes (e.g., requested document and operations for which permission is requested). The PDP retrieves privacy policies from a policy store (or Policy Information Point) and then execute those policies.

The output of the PDP is DENY or PERMIT decision with or without OBLIGATIONS. An example of OBLIGATION could be a request by the patient to redact some sensitive information from the clinical document before exchanging it.

When the PDP decision is a PERMIT with OBLIGATIONS, (1) the clincical document is retrieved from the EHR, (2) clinical facts (such as problem and medication entries) are extracted from the clinical document, (3) the clinical facts, the Purpose of Use, and the Obligations are sent to the Drools rules engine, (4) the Drools rules engine returns a set of directives for segmenting the clinical document, (5) the segmentation directives are applied to the clinical document, and (6) the sgemented document is sent to the recipient.


Running the SENDERCode
=================
 
The project is organized as two Maven projects: one for the clinical document sender (SENDER), the other for the clinical document recipient (RECIPIENT). Each project contains its own Maven POM file. The SENDER project is a modular Maven project. There is a parent POM file and submodules with their own child POM files. To run the SENDER's Maven project file, do the following:

1. Download and install the JDK
2. Download and install Maven
3. Download and install the JBoss AS7 application server
4. Download and unzip the SENDER Maven project from GitHub
5. Go to the context-handler-client sub-folder and run (1) mvn clean install, (2) mvn assembly:assembly, and (3) mvn install:install-file -DgroupId=context-handler-client-jar-with-dependencies -DartifactId=context-handler-client-jar-with-dependencies -Dversion=1.0 -Dfile=context-handler-client-jar-with-dependencies.jar -Dpackaging=jar -DgeneratePom=true
6. Go to the clinically-adaptive-rules-client sub-folder and run (1) mvn clean install, (2) mvn assembly:assembly, and (3) mvn install:install-file -DgroupId=clinically-adaptive-rules-client-jar-with-dependencies -DartifactId=clinically-adaptive-rules-client-jar-with-dependencies -Dversion=1.0 -Dfile=clinically-adaptive-rules-client-jar-with-dependencies.jar -Dpackaging=jar -DgeneratePom=true
7. Go to the c32-service-client sub-folder and run (1) mvn clean install, (2) mvn assembly:assembly, and (3) mvn install:install-file -DgroupId=c32-client-jar-with-dependencies -DartifactId=c32-client-jar-with-dependencies -Dversion=1.0 -Dfile=c32-client-jar-with-dependencies.jar -Dpackaging=jar -DgeneratePom=true
8. Go to the document-processor sub-folder and run (1) mvn clean install, (2) mvn assembly:assembly, and (3) mvn install:install-file -DgroupId=document-processor-client-jar-with-dependencies -DartifactId=document-processor-client-jar-with-dependencies -Dversion=1.0 -Dfile=document-processor-client-jar-with-dependencies.jar -Dpackaging=jar -DgeneratePom=true
9. Go to the common-library sub-folder and run (1) mvn clean install, (2) mvn assembly:assembly, and (3) mvn install:install-file -DgroupId=common-library-jar-with-dependencies -DartifactId=common-library-jar-with-dependencies -Dversion=1.0 -Dfile=common-library-jar-with-dependencies.jar -Dpackaging=jar -DgeneratePom=true
10. Go to the healthcare-classification-service sub-folder and run (1) mvn clean install, (2) mvn assembly:assembly, and (3) mvn install:install-file -DgroupId=healthcare-classification-client-jar-with-dependencies -DartifactId=healthcare-classification-client-jar-with-dependencies -Dversion=1.0 -Dfile=healthcare-classification-client-jar-with-dependencies.jar -Dpackaging=jar -DgeneratePom=true
5. Open the command prompt and go to the root folder (the parent POM file is located in the root folder) of the SENDER
6. Execute the following on the command line to build the entire SENDER project: mvn clean install.