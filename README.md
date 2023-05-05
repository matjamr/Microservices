# Microservices using hexagonal and clean architecture
Microservices example with usage of kafka, spring boot, swagger, spring boot admin, docker, kubernetes and many more....

### Run dependency graph
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.matjamr*:*"