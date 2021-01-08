# quarkus-jobrunr-example

Project containing two sides, enqueue and executor.

enqueue is a rest-api where if you call POST http://localhost:8081/api/testjob a testjob will be enqueued.

executor is the application with the dashboard and backgroundjob-server.

The test-job-executor is the implementation of the job.

Between all of these things is job-api containing definition of job and parameters.

Uses a postgres-datasource.


## execution

mvn compile quarkus:dev -f quarkus\quarkus-job-enqueue

mvn compile quarkus:dev -f quarkus\quarkus-job-executor


Note: database setup will be performed by the first starting application.
