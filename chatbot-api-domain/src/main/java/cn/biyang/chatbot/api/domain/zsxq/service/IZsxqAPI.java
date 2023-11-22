package cn.biyang.chatbot.api.domain.zsxq.service;

import cn.biyang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author biyang
 * @Date 2023/11/21 17:52
 * @Version 1.0
 */
public interface IZsxqAPI {
    UnAnsweredQuestionAggregates queryUnAnsweredQuestionTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text) throws IOException;
}
