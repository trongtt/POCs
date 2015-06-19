Java Persistence API (JPA)
====

Setup environment
======
MySQL Server - in the case you want to test with MySQL database system.

How to run
======
The project includes a set of bare JUnit Testcases, and difference configuration to work with different databases (H2, HSQLDB, MySQL).

Simply you can just execute the following maven command:

{code}
mvn clean test
{code}

By default, it uses HyberSQL Database (HSQLDB) in standalone mode. You can even explicitly specify to use a database system via a system property name "database", for instance -Ddatabase=[ h2 | hsql | mysql ]