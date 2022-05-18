Logging Library for Spring Boot
-------
This library provides utilities that make it easy to add logging into spring boot project

<b>Feature List</b>:
* [Application Logging](#Application Logging)
* [Kpi Logging](#Kpi Logging)

<b>The built-in configuration</b>:
* Message Pattern:
    * Application Log Pattern: `%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5p %c{1}:%L - %m%n`
    * KPI Log Pattern: `%d{yyyy-MM-dd@HH:mm:ss}[%c{1}:%L]:%m%n`
* File Rolling Policy:
    * Max History: `3`
    * Max File Size: `10MB`

Quick start
-------
* Just add the dependency to an existing Spring Boot project
```xml
<dependency>
    <groupId>com.atviettelsolutions</groupId>
    <artifactId>vts-kit-ms-logs-handler</artifactId>
    <version>1.0.0</version>
</dependency>
```

* Add `<include resource="vtskit-default-logback.xml"/>` line to your `logback.xml` file. Example:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="vtskit-default-logback.xml"/>
</configuration>
```

* Then, add the following properties to your `application-*.yml` file.
```yaml
vtskit:
  logs:
    level: INFO # Optional. Default is INFO
    kpi-logs:
      application-code: DEMO-APPLICATION # Optional. Default ApplicationCode value
      service-code: ${spring.application.name} # Optional. Default ServiceCode value
```

Usage
-------
##### Application Logging
By default, application log will be logged to the console log.

Example code to write application log:
```java
private static final Logger LOGGER = LoggerFactory.getLogger(<Your-Class>);

// Info level
AppLogService.info(LOGGER, "message");

// Debug level
AppLogService.debug(LOGGER, "message");

// Warning level
AppLogService.warn(LOGGER, "message");

// Error level
AppLogService.error(LOGGER, "error");
```

To configure logging to the file, add below configuration to `application-*.yml` file:
```yaml
vtskit:
  logs:
    log-folder: logs # Folder to save log file
    app-logs:
      error-log-file-name: ${spring.application.name}.error.log # File to save error log
      console-log-file-name: ${spring.application.name}.log # File to save all log
```

##### Kpi Logging
Define `KpiLogService` instance:
```java
private KpiLogService kpiLogService;

@Autowired
public void setKpiLogService(KpiLogService kpiLogService) {
    this.kpiLogService = kpiLogService;
}
```

Example code to write KPI Log:
```java
KpiLog.Builder builder = new KpiLog.Builder();
builder.sessionId("SessionId");
builder.ipPortParentNode("127.0.0.1");
builder.ipPortCurrentNode("127.0.0.1");
builder.requestContent("requestContent");
builder.responseContent("responseContent");
builder.startTime(new Date(Calendar.getInstance().getTimeInMillis()));
builder.errorCode("00");
builder.errorDescription("");
builder.transactionStatus(TransactionStatus.SUCCESS);
builder.actionName("actionName");
builder.username("username");
builder.account("account");
builder.endTime(new Date(Calendar.getInstance().getTimeInMillis()));

kpiLogService.writeLog(LOGGER, builder.build());
```

By default, KPI log will be logged to the console log. In addition, it can be configured to write to file or database.

To configure logging to the file, add below configuration to `application-*.yml` file:
```yaml
vtskit:
  logs:
    log-folder: logs # Folder to save log file
    kpi-logs:
      kpi-log-file-name: ${spring.application.name}.kpi.log # File to save kpi log
```

Similarly, to save the kpi log to the database, add below configuration to `application-*.yml` file:
```yaml
vtskit:
  logs:
    kpi-logs:
      datasource: # Configuration mariadb for store kpi log
        url: jdbc:mariadb://localhost:3307/test-database
        username: root
        password: root
```

Build
-------
* Build with Unittest
```shell script
mvn clean install
```

* Build without Unittest
```shell script
mvn clean install -DskipTests
```

Contribute
-------
Please refer [Contributors Guide](CONTRIBUTING.md)

License
-------
This code is under the [MIT License](https://opensource.org/licenses/MIT).

See the [LICENSE](LICENSE) file for required notices and attributions.
