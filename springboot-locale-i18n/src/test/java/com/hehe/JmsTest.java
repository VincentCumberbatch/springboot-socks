package com.hehe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description:[TODO]</p>
 * Created on: 2019-07-10 17:23
 *
 * @author: <a href="yelinglong@bytedance.com">叶玲珑</a>
 * version: 1.0
 * Copyright (c) 2019 北京字节跳动科技有限公司
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAsync
public class JmsTest {
    @Autowired
    private JmsProducer producer;

    @Autowired
    private JmsConsumer consumer;
    private Logger logger = LoggerFactory.getLogger(JmsTest.class);

    @Test
    public void sendMsg() {
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("value", "value = " + i);
            producer.sendToTopic(map);
//          producer.sendToQueue(map);
//          producer.sendToVTopic(map);
        }
    }

    @Test
    public void sendMsg2VTopic() {
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("value", "value = " + i);
            producer.sendToVTopic(map);
        }
    }
}