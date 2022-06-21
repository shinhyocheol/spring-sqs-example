package com.example.app;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableSqs
@Configuration
@EnableAutoConfiguration(exclude = {ContextInstanceDataAutoConfiguration.class})
public class AwsSQSConfigure {

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync,
                                                                                       AsyncTaskExecutor asyncTaskExecutor) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync);                       // SQS API를 가지고 통신을 하는 컨테이너에 의해 사용되어지는 AmazonSQSAsync를 설정
        factory.setMaxNumberOfMessages(10);                         // 한 번 poll하는 동안 조회되어져야 하는 메세지의 최대 개수, 높을 수록 poll reuqest 요청을 줄여준다.
        factory.setWaitTimeOut(20);                                 // queue에 메세지가 없을 때, queue로 들어오는 새로운 메세지에 대해 poll 요청 시 대기하는 timeout 시간
        factory.setTaskExecutor(asyncTaskExecutor);                 // 메세지를 poll하고 처리하는 핸들러 함수 설정

        return factory;
    }

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setThreadNamePrefix("sqs-task-");
        asyncTaskExecutor.setCorePoolSize(20);
        asyncTaskExecutor.setMaxPoolSize(10);
        asyncTaskExecutor.setQueueCapacity(5);
        asyncTaskExecutor.initialize();

        return asyncTaskExecutor;
    }
}
