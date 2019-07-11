package com.hehe;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.jms.ConnectionFactory;

/**
 * <p>Description:[activemq配置]</p>
 * Created on: 2019-07-10 17:19
 *
 * @author: <a href="yelinglong@bytedance.com">叶玲珑</a>
 * version: 1.0
 * Copyright (c) 2019 北京字节跳动科技有限公司
 */
@Configuration
@EnableAsync // enable asynchronous task
@EnableJms
public class JmsConfiguration {
    private Logger logger = LoggerFactory.getLogger(JmsConfiguration.class);

    @Bean(name = "firstConnectionFactory")
    public ActiveMQConnectionFactory getFirstConnectionFactory(@Value("${activemq.broker-url}") String brokerUrl,
                                                               @Value("${activemq.username}") String userName, @Value("${activemq.password}") String password)
    {
        logger.debug(brokerUrl + " - " + userName);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "firstJmsTemplate")
    public JmsMessagingTemplate getFirstJmsTemplate(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
        return template;
    }

    @Bean(name = "firstTopicListener")
    public DefaultJmsListenerContainerFactory getFirstTopicListener(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // if topic, set true
        // factory.setSessionAcknowledgeMode(4); // change acknowledge mode
        return factory;
    }

    @Bean(name = "firstQueueListener")
    public DefaultJmsListenerContainerFactory getFirstQueueListener(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // factory.setSessionAcknowledgeMode(4); // change acknowledge mode
        return factory;
    }
}