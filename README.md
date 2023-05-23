## This is a graduate work using Java EE for the DM110 discipline.

**Operational System:**

- Windows 10

---

**Technologies:**

- IntelliJ IDEA Communit Edition 2022.1.3 (optional)
- HyperSQL - HSQLDB 2.7.1 (mandatory) - http://hsqldb.org
- Java 11.0.17 (recommended) - https://www.oracle.com/java/technologies/downloads/#java11
- WildFly 24.0.0 Final (mandatory) - https://github.com/wildfly/wildfly/releases/download/27.0.1.Final/wildfly-27.0.1.Final.zip
- Postman 10.13.6 (optional) - https://www.getpostman.com
- Maven 3.8.1 (recommended) - https://maven.apache.org/download.cgi

---

**Install mandatories technologies**

1. Download and unpack/unzip the WildFly, HyperSLQ(HSQLDB), and Maven into single folders to avoid path problems.
   - Examples: 
     - C:\dm110\wildfly, C:\dm110\hsqldb, C:\dm110\maven
     - home/dm110/wildfly, home/dm110/hsqldb home/dm110/maven 
2. Be sure to have Java and an API Client like Postman. It will help with some tests.
3. Have your system environment variables configured for Java and Maven to avoid path errors. It's not mandatory but will help you a lot. You can find out how to do it on the Internet. Use a search tool and type:
   - maven system environment variables  
   - java system environment variables 

---

**How to run:**

1. Clone the repository into a single folder to not have path problems. Ex.: 'C:\dm110' or '/home/dm110'
2. Go to the folder that you used to clone, for example, C:\dm110\trabalho-dm110, open a terminal or command prompt on it, and type:
   - `mvn clean install`
3. Go to the folder that you used to unpack/unzip the HyperSLQ(HSQLDB), open a command prompt or terminal on it, go to the `/lib` folder, and type the command below:
   - `java -jar hsqldb.jar` 
   - When the HyperSQL IDE pops up, use the below configuration:
     - Setting Name: "DM110"
     - Type: HSQL Database Engine Standalone
     - Driver: org.hsqldb.jdbc.JDBCDriver
     - URL: jdbc:hsqldb:file:C:\dm110\product.db (change for the correct path that you want to use)
     - User: dm110 (You must use exactly this one unless you know what you are doing and how to change it in the next commands below)
     - Password: senhadm110 (same comment as above)
- In the available command blank area, type/copy the below script to create the mandatory tables and click on `ExecuteSQL` button:
  - Obs: The below scripts are also available inside the folder `docs` in your cloned project folder.
```
CREATE TABLE PRODUCT (
CODE INTEGER NOT NULL,
NAME VARCHAR(30) NOT NULL,
DESCRIPTION VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(30) NOT NULL,
PRICE DOUBLE NOT NULL,
PRIMARY KEY (CODE)
);
```
```
CREATE TABLE AUDITING (
IDENTIFIER INTEGER NOT NULL,
REGISTERFROM VARCHAR(30) NOT NULL,
REGISTERCODE VARCHAR(30) NOT NULL,
OPERATION VARCHAR(30) NOT NULL,
DATETIME TIMESTAMP NOT NULL,
PRIMARY KEY (IDENTIFIER)
);
```
*** After the two scripts ran successfully, you can close the HyperSQL IDE.

- Obs1.: For the next commands below, for bash terminals use `.sh` files, and for windows command prompt use `.bat` files
- Obs2.: Be careful on copy and pasting. Some prompts or terminals can behavior different. Be sure that you type things correctly.
4. Open a terminal or command prompt at the folder where you unpack/unzip the WildFly, go to the `/bin` folder, and type:  
   - `./standalone.sh -c=standalone-full.xml` or `standalone.bat -c=standalone-full.xml` 
5. Open another terminal or command prompt at the folder where you unpack/unzip the WildFly, go to the `/bin` folder, and type the below commands in the presented order after each one is completed successfully. No error should be shown before you execute the next command. Remember to use ".sh" or .".bat" according to your system. Also, remember to change and put the correct PATH where you install your technologies.
- `./jboss-cli.sh --connect --command="module add --name=br.inatel.dm110.org.hsqldb --dependencies=javax.transaction.api --export-dependencies=javax.api --resources=C:\dm110\hsqldb\lib\hsqldb.jar"` (chage to the correct path where you put your HSQLDB)
- `./jboss-cli.sh --connect --command="/subsystem=datasources/jdbc-driver=HSQLDBDriver:add(driver-name=HSQLDBDriver,driver-module-name=br.inatel.dm110.org.hsqldb,driver-class-name=org.hsqldb.jdbc.JDBCDriver)"`
- `./jboss-cli.sh --connect --command="data-source add --jndi-name=java:/ProductDS --name=ProductDS --connection-url=jdbc:hsqldb:file:C:\dm110\product.db --driver-name=HSQLDBDriver --password=senhadm110 --user-name=dm110"` (change to the correct path where you put your product.db)
- `./jboss-cli.sh --connect --command="jms-queue add --queue-address=ProductQueue --durable=true --entries=[java:/jms/queue/ProductQueue]"` 
- `./jboss-cli.sh --connect --command="deploy --force C:\dm110\trabalho-dm110\trabalho-ear\target\trabalho-ear-1.0.ear"` (change to the correct path where you clone the repository. Check if the EAR file is into the `\trabalho-ear\target` folder)

6. Use an API Client like Postman to perform the tasks and call the provided endpoints. 

---

**Payload:**

In the folder `docs` there is a file `DM110.postman_collection.json` that you can use to import to your preferred API platform or tool (I've used Postman for this project), so you will have all the endpoints ready and configured to use and test the APIs.

---

**Endpoints:**

If you decide to not import the Postman configured collection that I have exported, below are the endpoints to be used to test the API.

```
--- PRODUCT ---
(POST)   http://localhost:8080/trabalho-web/api/product             (create product)
(PUT)    http://localhost:8080/trabalho-web/api/product/code/1      (update product)
(DELETE) http://localhost:8080/trabalho-web/api/product/code/1      (delete product by id)
(GET)    http://localhost:8080/trabalho-web/api/products            (get all products)
(GET)    http://localhost:8080/trabalho-web/api/product/code/1      (get product by id)
(GET)    http://localhost:8080/trabalho-web/api/product/name/Laptop (get product by name)

--- AUDITING ---
(GET) http://localhost:8080/trabalho-web/api/auditing               (get all auditing)
(GET) http://localhost:8080/trabalho-web/api/auditing/productCode/1 (get auditing by product code)
```

---

**Diagram**

The PDF version of this diagram is available in the folder `docs` of the cloned repository.

![alt text](https://github.com/CarvalhoMarcelo/trabalho-dm110/blob/master/docs/diagrama_dm110.png)
