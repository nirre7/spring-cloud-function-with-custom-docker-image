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


