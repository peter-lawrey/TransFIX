THIS PROJECT HAS BEEN RETIRED 
=============================
ALL FURTHER DEVELOPMENT HAS BEEN MOVED TO CHRONICLE-FIX WHICH IS A LICENSED PRODUCT
IF YOU ARE INTERESTED IN USING CHRONICLE FIX PLEASE EMAIL "sales@chronicle.software", see also http://chronicle.software/products/chronicle-fix

TransFIX
========
A Fix 4.2 Message Parser with Sub Millisecond Latency

![TransFIX](http://chronicle.software/wp-content/uploads/2014/09/TransFix_200px.png)
### Content
* [Overview](https://github.com/OpenHFT/TransFix/wiki#overview)
* [Object Construction](https://github.com/OpenHFT/TransFix/wiki#overview)
* [Performance](https://github.com/OpenHFT/TransFix/wiki#Performance)


### Overview
TransFix is a Fix4.2 version fast fix parser for parsing inbound fix messages. It can also be used by a OMS/EMS to send out FIX messages. This is a standard Fix4.2 implementation and at this time does not allow custom fix message configurations. Since TransFix is used in the eco-system of Chronicle, all message transactions can be persisted. TransFix uses a fast, off-heap object pool for Fix message sessions. This is useful when there is a need to read/send multiple FIX messages during transactions with multiple clients.

### Object Construction
To download the JAR which contains TransFix package, we recommend you use maven, which will download it from [Maven Central](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22net.openhft%22%20AND%20a%3A%22transfix%22), once you have installed maven, all you have to do is add the following to your projects pom.xml :
```xml
<dependency>
  <groupId>net.openhft</groupId>
  <artifactId>fix-compiler</artifactId>
  <version><!--replace with the latest version--></version>
</dependency>
```
In order to create an instance of TransFix, its recommended to use the FixMessageBuilder

```java
net.openhft.fix.include.v42;
...........

FixMessagePool fmp = new FIXMessageBuilder().initFixMessagePool(true, fixMsgCount);
FixMessageContainer<FixMessage> fmc = fmp.getFixMessageContainer();
FixMessage fm = fmc.getFixMessage();
```
For `initFixMessagePool(boolean default, int fixMsgCount);` method, if `default` variable is `true`, then `FIXMessageBuilder `loads pre-defined configuration as per [FIX 4.2](https://github.com/OpenHFT/TransFIX/blob/master/fix-sandbox/schema/src/main/resources/FIX42.xml) version schema. Using default config, all FIX 4.2 protocol version fields are loaded for each `FixMessage` inside `FixMessagePool`. All FIX fields are accessibly by passing the exact field ID. For example, in order to set or get the message type (FIX 35=??), `fm.getField(35).getFieldData()` or `fm.setField(35).setFieldData(6)` for "Indication of interest". 
Each of the corresponding `Field` loaded in a `FixMessage` contains its own `ByteBufferBytes` data field of default size 1024 bytes. For multiple values, the data is separated by default delimiter "|" which is set to SOH (0x01) value while sent back on wire. 
Since the default config will load all of the described fields (1-436) for each `FixMessage` inside `FixMessagePool`, its recommended to custom load fields of necessity while constructing via `FixMessageBuilder`. This flexibility allows to control the size of `FixMessage` object.


### Performance
TransFix supports `java.lang.String` or `NativeBytes `or `ByteBufferBytes `as input data to `FixMessageBuilder`. Its recommended to use `NativeBytes` for maximum performance even though all types work ok. The average `NativeBytes` message parsing time is 0.03us while for transmitting a `FixMessage` the average latency is about 1.5us.

Some latency numbers for parsing a sample Fix message "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=FIA.MI|54=2|27=250000|44=7900.000000|25=H|10=231|";
<br>Average parse time was 0.13 us, fields per message 1.00, for 0.30m iterations
<br>Average parse time was 0.05 us, fields per message 1.00, for 1.00m iterations
<br>Average parse time was 0.02 us, fields per message 1.00, for 10.00m iterations
<br>Average parse time was 0.01 us, fields per message 1.00, for 50.00m iterations

<br>For reverse look up ( setting a single Fix message retrieved from pool and then iterating over it n times to calculate ByteBufferBytes based look up):
<br>Average reverse-look-up time was 0.75 us, for 0.30m iterations
<br>Average reverse-look-up time was 0.66 us, for 1.00m iterations
<br>Average reverse-look-up time was 0.60 us, for 10.00m iterations
<br>Average reverse-look-up time was 0.57 us, for 50.00m iterations
