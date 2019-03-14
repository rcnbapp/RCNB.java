# RCNB.java 

The world is based on RC. Thus, everything can be encoded into RCNB.
[![](https://www.jitpack.io/v/IsSkyfalls/RCNB.java.svg)](https://www.jitpack.io/#IsSkyfalls/RCNB.java)

See also: [librcnb](https://github.com/rcnbapp/librcnb) [RCNB.php](https://github.com/rcnbapp/RCNB.php) [RCNB.js](https://github.com/rcnbapp/RCNB.js)

## Why RCNB?

### RCNB vs Base64

|           | Base64       | RCNB                                                          |
|-----------|--------------|---------------------------------------------------------------|
| Speed     | ❌ Fast       | ✔️ Slow, motivate Intel to improve their CPU                   |
| Printable | ❌ On all OS  | ✔️ Jvm supports almost all os! |
| Niubility | ❌ Not at all | ✔️ RCNB!                                                     |
| Example   | QmFzZTY0Lg== | ȐĉņþƦȻƝƃŔć                                                    |

<a name="maven"></a>
Maven import:
--------

repository:

```xml
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
```

dependency:

```xml
	<dependency>
	    <groupId>com.github.IsSkyfalls</groupId>
	    <artifactId>RCNB.java</artifactId>
	    <version>v1.0</version>
	</dependency>
```

<br>

<a name="gradle"></a>
Gradle import:
--------

repository:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

dependency:

```gradle
dependencies {
    implementation 'com.github.IsSkyfalls:RCNB.java:v1.0'
}
```

## Usage
```java
//encode
RCNBEncoder.encode("Who's NB?");
//decode
RCNBDecoder.decode("ȐȼŃƅȓčÑƄɌCňƀƦȻƝƃŖć");
```
