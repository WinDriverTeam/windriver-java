# windriver-java

A Java client for the [WinDriver Agent](https://github.com/WinDriverTeam/windriver-agent). This client allows you to automate Windows desktop applications using Java.

# Why WinDriver?
- **Stability** - WinDriver was designed to be very simple and reliable solution
- **Parallelization** - support of parallel execution (will be introduced in future releases)
- **COM Objects** - WinDriver Agent uses Microsoft's COM-based technologies
- **Java Api** - support of one of the most popular languages of programming Java
- **Free** - it is user-friendly and absolutely free

# Actions supported by WinDriver Java Client:
- **Click** - click on the element
- **Double Click** - perform double click on the element
- **Right Click** - perform right click on the element
- **Click at clickable point** - perform click on the element which is not explicit button
- **Send Keys** - send charset into the element (also perform shortcut actions)
- **Get Text** - retrieve text value from the element
- **Move Mouse** - move mouse to the element
- **Expand/Collapse** - expand/collapse the element


# Requirements
- [Java 7 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- `java` and `jar` on tha PATH

# Usage Prerequisites
To use it, add windriver-java to your build tool dependencies (e.g. maven):
```xml
<dependency>
    <groupId>com.github.windriverteam</groupId>
    <artifactId>windriver-java</artifactId>
    <version>0.1.0</version>
</dependency>
```

When dependency is added to the project, **windriver.properties** file has to be created in 'src/main/resources'.
This file should contain such properties:
```
agent.url.connection.type={type of agent url connection}
agent.url.host={host where agent is running}
agent.url.port={port where agent is running}
client.log.level={log level for client e.g. "DEBUG", "INFO" etc}
http.request.read.timeout={request read timeout}
http.request.connect.timeout={connection timeout}
```
Name and location of the file **windriver.properties** can be changed via setting system properties `windriver.config.file.name` and `windriver.config.file.path`

# Usage Examples
__Make sure agent instance is running!__
To see how to launch agent click [here](https://github.com/WinDriverTeam/windriver-agent)

### Launching application
Launching an Calculator instance:
```java
WinDriverService service = new WinDriverService();
WinDriverApplication app =  service.launchByExecutable("calc", "Calculator");
```
**Note:** WinDriver agent works very fast, so to prevent different potential problems, please use some wait solutions after launching the application and between resource-consuming operations.

### Performing actions
Performing action "click" on a button of Calculator (for Windows 10):
```java
WinDriverElement calculatorWindow = service.getWindow(new PropertyConditions(Property.NAME_PROPERTY, "Calculator"));
WinDriverElement buttonOne = calculatorWindow.findOne(new PropertyConditions(Property.AUTOMATION_ID_PROPERTY,"num1Button"));
buttonOne.click();
 ```
 
### Closing application
Closing Calculator instance:
```java
WinDriverService service = new WinDriverService();
WinDriverApplication app =  service.launchByExecutable("calc", "Calculator");
//some code
service.shutdown(app.getWinDriverElementId());
```
### Extending WinDriverElement
Custom windriver element can be used for logging purposes etc.
Let's create a custom class and add simple logging functionality:
```java
import com.fasterxml.jackson.annotation.JsonCreator;
import ua.windriver.model.automation.AutomationElementWrapper;
import ua.windriver.model.automation.WinDriverElement;
import ua.windriver.model.response.ActionControlResponse;
import ua.windriver.util.PropertyConditions;

public class CustomWinDriverElement extends WinDriverElement {
    @JsonCreator
    public CustomWinDriverElement(AutomationElementWrapper element) {
        super(element);
    }

    @Override
    public <T extends WinDriverElement> T findOne(PropertyConditions conditions) {
        System.out.println("Trying to find element using condition: " + conditions.getConditions());
        return controller.findOne(conditions);
    }
    @Override
    public ActionControlResponse click() {
        System.out.println("Clicking at element with name '" + element.getName() + "'");
        return controller.click();
    }
}
```
We need to tell WinDriverService to use CustomWinDriverElement.class instead of WinDriverElement.class:
```java
 WinDriverService service = new WinDriverService();
 service.setWinDriverElementType(CustomWinDriverElement.class);
```
Now we can use custom class with all additional functionality:
```java
WinDriverService service = new WinDriverService();
service.setWinDriverElementType(CustomWinDriverElement.class);
service.launchByExecutable("calc", "Calculator");

CustomWinDriverElement calculatorWindow = service.getWindow(new PropertyConditions(Property.NAME_PROPERTY, "Calculator"));
CustomWinDriverElement btn = calculatorWindow.findOne(new PropertyConditions(Property.AUTOMATION_ID_PROPERTY, "num1Button"));

btn.click();
```
Output:
```
Trying to find element using condition: [Condition{name=AUTOMATION_ID_PROPERTY, value='num1Button'}]
Clicking at element with name 'One'
```
