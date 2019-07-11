package com.hehe.locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * <p>Description:[TODO]</p>
 * Created on: 2019-07-04 10:18
 *
 * @author: <a href="yelinglong@bytedance.com">叶玲珑</a>
 * version: 1.0
 * Copyright (c) 2019 北京字节跳动科技有限公司
 */
@Component
public class MessageUtils {

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }
}