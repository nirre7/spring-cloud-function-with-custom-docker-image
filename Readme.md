# spring-cloud-function-with-custom-docker-image

## Workflow is as follows:

- Build the native image via maven
- Build the Docker image with the GraalVM native image
- Push the Docker image to ECR
- Deploy the Lambda function with the ECR image

## How to build

Build with the following command:

```mvn clean package -Pnative```

## Source resources/services in AWS

Source with the following command:

```aws cloudformation deploy --stack-name demo --template-file template.yaml --capabilities CAPABILITY_NAMED_IAM```

## Error when running the build on AWS Lambda

Seems like the error comes from [this line](https://github.com/spring-cloud/spring-cloud-function/blob/1234a9473703a5e70dc8de162a23f65b7f0b7729/spring-cloud-function-adapters/spring-cloud-function-adapter-aws/src/main/java/org/springframework/cloud/function/adapter/aws/CustomRuntimeInitializer.java#L46)

:: Spring Boot ::                (v3.4.3)
2025-03-10T16:18:21.216Z DEBUG 8 --- [demo] [           main] o.s.c.f.a.aws.CustomRuntimeInitializer   : AWS Environment: {DEFAULT_HANDLER=ping, _AWS_XRAY_DAEMON_PORT=2000, SHLVL=1, _HANDLER=/bin/sh, SPRING_CLOUD_FUNCTION_WEB_EXPORT_ENABLED=false, AWS_LAMBDA_FUNCTION_NAME=demo-lambda, AWS_LAMBDA_LOG_FORMAT=Text, AWS_REGION=eu-west-1, AWS_LAMBDA_FUNCTION_MEMORY_SIZE=2024, AWS_XRAY_DAEMON_ADDRESS=anAdreess:2000, AWS_DEFAULT_REGION=eu-west-1, AWS_LAMBDA_LOG_GROUP_NAME=/aws/demo-lambda, PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin, AWS_LAMBDA_LOG_STREAM_NAME=2025/03/10/demo-lambda, AWS_SESSION_TOKEN=aToken, LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_CLOUD=DEBUG, AWS_LAMBDA_INITIALIZATION_TYPE=on-demand, AWS_ACCESS_KEY_ID=AccessKeyId, LAMBDA_RUNTIME_DIR=/var/runtime, AWS_LAMBDA_FUNCTION_VERSION=$LATEST, AWS_LAMBDA_RUNTIME_API=127.0.0.1:9001, AWS_SECRET_ACCESS_KEY=aSecret, AWS_XRAY_CONTEXT_MISSING=LOG_ERROR, _AWS_XRAY_DAEMON_ADDRESS=169.254.79.129, LAMBDA_TASK_ROOT=/var/task, PWD=/app, AWS_EXECUTION_ENV=AWS_Lambda_Image}
2025-03-10T16:18:21.216Z  INFO 8 --- [demo] [           main] o.s.c.f.a.aws.CustomRuntimeInitializer   : AWS Handler: /bin/sh
2025-03-10T16:18:21.216Z DEBUG 8 --- [demo] [           main] o.s.c.f.a.aws.CustomRuntimeInitializer   : Will execute Lambda in Custom Runtime
2025-03-10T16:18:21.216Z  INFO 8 --- [demo] [           main] com.example.demo.DemoApplication         : Starting AOT-processed DemoApplication using Java 21.0.2 with PID 8 (/app/demo started by sbx_user1051 in /app)
2025-03-10T16:18:21.216Z  INFO 8 --- [demo] [           main] com.example.demo.DemoApplication         : No active profile set, falling back to 1 default profile: "default"

2025-03-10T16:18:21.238Z  WARN 8 --- [demo] [           main] o.s.c.support.GenericApplicationContext  : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'customRuntimeEventLoop': Instantiation of supplied bean failed
Application run failed
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'customRuntimeEventLoop': Instantiation of supplied bean failed
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainFromSupplier(AbstractAutowireCapableBeanFactory.java:1249)
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1186)
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:563)
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:523)
at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339)
at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:346)
at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337)
at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1155)
at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1121)
at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1056)
at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987)
at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627)
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752)
at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439)
at org.springframework.boot.SpringApplication.run(SpringApplication.java:318)
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361)
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350)
at com.example.demo.DemoApplication.main(DemoApplication.java:10)
at java.base@21.0.2/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)
Caused by: java.lang.NoClassDefFoundError: Could not initialize class org.springframework.cloud.function.adapter.aws.CustomRuntimeEventLoop
at org.springframework.cloud.function.adapter.aws.CustomRuntimeInitializer.lambda$initialize$0(CustomRuntimeInitializer.java:46)
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainInstanceFromSupplier(AbstractAutowireCapableBeanFactory.java:1283)
at org.springframework.beans.factory.support.DefaultListableBeanFactory.obtainInstanceFromSupplier(DefaultListableBeanFactory.java:1007)
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainFromSupplier(AbstractAutowireCapableBeanFactory.java:1243)
... 19 more

