# FuseDemo

This repository contains a demonstration created for exploring how Enterprise Integration Patterns (EIPs)
can be implemented using the JBoss Fuse Integration Platform.  It uses a fictitious retail scenario in 
which customers can place orders to an order management system via a REST API or dropping a CSV file into
a directory.  

EIPs leveraged in Fuse are based on Apache Camel and documentation for the various EIPs can be found at the
following URL:  http://camel.apache.org/enterprise-integration-patterns.html

The demo uses a combination of the Spring and Java DSLs available to create integrations.  The requirements 
for the order management system are the following:

Project Description:
Create a project with Fuse that will update an order management system for an organization that handles orders
from two divisions: Gadgets and Widgets.  The system needs to be able to:

* Handle two channels of input
1. CSV files uploaded to an FTP server (legacy orders processed this way)
2. Web interface which submits orders via a REST endpoint

* Orders consist of a customers information, widget items and gadget items
* Orders can consist of both types of products from each division
* When an order is processed Widgets are processed by the Widget division, Gadgets by the Gadget division
* Once inventory has been validated within each division, the total order amount must be validated.
* New orders go directly to the Accounting division
* If an existing customer, any order total under 50k can be processed automatically by sending it to the Fulfillment 
division.  Over 50k gets sent to Accounting division.

To run the demonstration you will need the following tools and technologies:
1.  JBoss Developer Studio with Integration Stack plugins
2.  JBoss AMQ Broker 7.0.0
3.  MySQL or MariaDB (DDL provided in repo)
4.  Apache Maven 3+
5.  JDK 1.8u144+
5.  jolokia agent for monitoring via console (more on this below)

Once the project is imported in JBoss Developer Studio you will need to leverage Maven to run the demo.  This 
demonstration can be run with other containers such as JBoss EAP 6.4 or other but only utilizes a JVM for simplicity.

1.  Use the following Maven run configuration to run the demo: clean package org.apache.camel:camel-maven-plugin:run

** Ensure that the following is added to the VM arguments of the JRE you are using to run the demo:
-javaagent:"/(path to jolokia jar included in this repo)/jolokia-jvm-1.3.7-agent.jar"

Make sure in the console you can see that the jolokia agent started:
Jolokia: Agent started with URL http://127.0.0.1:8778/jolokia/

2.  Once started you can monitor messages as they flow through your integration project. Use the hawtio console to connect 
to the jolokia agent deployed with your JVM (port 8778).

1.  Go to http://localhost:8161/hawtio/login
2.  "Connect" -> use localhost and port 8778
3.  Once connected you should see a "Camel" tab appear with the Camel routes created in the project.

3.  Also included is a SOAP UI project to test simple use cases requried for the demonstration.  Simply download SOAPUI and
import the project or create one similar and point the invocations to the URL endpoint: /order
