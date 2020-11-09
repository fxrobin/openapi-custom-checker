[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/openapi-custom-checker) ![Java CI with Maven](https://github.com/fxrobin/openapi-custom-checker/workflows/Java%20CI%20with%20Maven/badge.svg)

# openapi-custom-checker

## Running in Java Mode (like in Gitpod.io)

```
$ mvn package
$ java -jar ./target/openapi-custom-checker-1.0-SNAPSHOT-runner.jar --version
$ java -jar ./target/openapi-custom-checker-1.0-SNAPSHOT-runner.jar 
```

## Generating the native app (thanx to Graal and Quarkus)

```
$ mvn package -Pnative
$ ./target/openapi-custom-checker-1.0-SNAPSHOT-runner --version
```
