# Inspired Commons Class

This project contains some web useful web component. To use it add the following dependency to your pom.xml

```
<dependency>
  <groupId>it.inspiredsoft</groupId>
  <artifactId>commons</artifactId>
  <version>1.0.0</version>
</dependency>
```

##COERC_TO_ZERO

To force COERC_TO_ZERO = false for your web project just add the following listenere to your web.xml
```
<listener>
  <listener-class>it.inspired.web.listener.CoerceToZeroListener</listener-class>
</listener>
```

