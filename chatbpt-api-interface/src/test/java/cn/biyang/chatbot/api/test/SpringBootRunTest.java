package cn.biyang.chatbot.api.test;

import cn.biyang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.biyang.chatbot.api.domain.zsxq.model.vo.Talk;
import cn.biyang.chatbot.api.domain.zsxq.model.vo.Topics;
import cn.biyang.chatbot.api.domain.zsxq.service.IZsxqAPI;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author biyang
 * @Description
 * @Date 2023/11/20 15:54
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqAPI zsxqApi;

    @Test
    public void test_zsxqAPI() throws IOException {
        String answerText = "等";
        UnAnsweredQuestionAggregates unAnsweredQuestionAggregates = zsxqApi.queryUnAnsweredQuestionTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionAggregates));

        List<Topics> topics = unAnsweredQuestionAggregates.getResp_data().getTopics();
        logger.info("总共有{}个topic",topics.size());
        for(Topics topic : topics){
            String topicID = topic.getTopic_id();
            Talk talk = topic.getTalk();
            String text = talk.getText();
            logger.info("the topic id is {}, the text is {}",topicID,text);
            zsxqApi.answer(groupId,cookie,topicID,answerText);
        }

    }


}
