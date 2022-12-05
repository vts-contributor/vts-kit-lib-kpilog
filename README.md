Logging Library for Spring Boot
-------
This library provides utilities that make it easy to add logging into spring boot project

<b>Feature List</b>
* [Kpi Logging](#Kpi-Logging)
* [Application Logging](#Application-Logging)

<b>Output type supported</b>
* File
* MariaDB, Postgresql, MySql,... (Use for KPI log)

<b>The built-in configuration</b>
* Pattern:
    * KPI Log Message Pattern: `ApplicationCode|ServiceCode|SessionId|IpPortParentNode|IpPortCurrentNode|RequestContent|ResponseContent|StartTime|EndTime|Duration|ErrorCode|ErrorDescription|TransactionStatus|ActionName|Username|Account`
    * Archived File Name Pattern: `archived-*.zip`
* File Rolling Policy:
    * Max History: `3`
    * Max File Size: `10MB`

Quick start
-------
* Just add the dependency to an existing Spring Boot project
```xml
<dependency>
    <groupId>com.atviettelsolutions</groupId>
    <artifactId>vts-kit-lib-kpilog</artifactId>
    <version>1.0.0</version>
</dependency>
```

* Then, add the following properties to your `application.properties` file.
```properties
logs.level= INFO
logs.log-folder= D:\\log_kpi
logs.app-logs.console-log-file-name=info_log.log
logs.app-logs.error-log-file-name:error_log.log
logs.kpi-logs.enabled: true
logs.kpi-logs.kpi-log-file-name: kpi_log.log
logs.kpi-logs.datasource.enabled: true
logs.kpi-logs.datasource.driver-class-name: org.postgresql.Driver
logs.kpi-logs.datasource.url: jdbc:postgresql://10.60.109.19:8082/postgres?currentSchema=gis_portal
logs.kpi-logs.datasource.username: postgres
logs.kpi-logs.datasource.password: postgres
logs.kpi-logs.datasource.table-name: KPI_Log
```
The system will automatically create a table named `KPI_LOG` and save the kpi log data there.

Usage
-------

### Kpi Logging

#### Save KPI Log to database
Follow the steps below:

<b>Step 1</b>: Define `KpiLogService` instance:
```java
@Autowired
private KpiLogService kpiLogService;
```

<b>Step 2</b>: Set KPI Log information using `KpiLog.Builder`:
```java
 KpiLog kpiLog = new KpiLog.Builder()
         .sessionId("SessionId")
         .applicationCode("APPLICATTION_CODE")
         .serviceCode("SERVICE_CODE")
         .ipPortParentNode("127.0.0.1")
         .ipPortCurrentNode("127.0.0.1")
         .requestContent("requestContent")
         .responseContent("responseContent")
         .startTime(new Date())
         .errorCode("00")
         .errorDescription("errorDescription")
         .transactionStatus(TransactionStatus.SUCCESS)
         .actionName("actionName")
         .username("username")
         .account("account")
         .endTime(new Date())
         .build();
    
```

<b>Step 3</b>: Execute save KPI Log
```java
kpiLogService.writeLog(kpiLog);
```

#### Application Logging
To write log error, info, debug just add the codes like below:

```java    
    try {
     //log info
      AppLog.info("Test log info");
    }catch (Exception e) {
     //log error 
     AppLogService.error(LOGGER, e);
    }

```

Contribute
-------
#### Setting up the development environment
* <b>IDE:</b> Eclipse, Intellij IDEA
* <b>JDK:</b> >= JDK 8
* <b>Maven:</b> >= 3.6.0
* <b>Build:</b>
```shell script
mvn clean install
# Skip Unittest
mvn clean install -DskipTests
```
#### Contribute Guidelines
If you have any ideas, just open an issue and tell us what you think.

If you'd like to contribute, please refer [Contributors Guide](CONTRIBUTING.md)

License
-------
This code is under the [MIT License](https://opensource.org/licenses/MIT).

See the [LICENSE](LICENSE) file for required notices and attributions.
