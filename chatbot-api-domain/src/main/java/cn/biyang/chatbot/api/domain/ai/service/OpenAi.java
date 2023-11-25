package cn.biyang.chatbot.api.domain.ai.service;

import cn.biyang.chatbot.api.domain.ai.IOpenAi;
import cn.biyang.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import cn.biyang.chatbot.api.domain.ai.model.vo.Choices;
import cn.biyang.chatbot.api.domain.ai.model.vo.Message;
import cn.biyang.chatbot.api.domain.zsxq.model.req.AnswerReq;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author biyang
 * @Date 2023/11/24 18:17
 * @Version 1.0
 */
@Service
public class OpenAi implements IOpenAi {
    private Logger logger = LoggerFactory.getLogger(IOpenAi.class);

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Value("${chatbot-api.model}")
    private String model;

    @Override
    public String chatWithOpenai(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.openai-proxy.com/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer "+openAiKey);
        String paramJson = "{\n" +
                "     \"model\": \""+model+"\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \""+question+"\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";
        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(s, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for(Choices choice :choices){
                Message message = choice.getMessage();
                answers.append(message.getContent());
            }
            logger.info("调用Api接口回复为： {}",answers.toString());
            return answers.toString();
        }else {
            throw new RuntimeException("openai api erro code is "+response.getStatusLine().getStatusCode());
        }

    }
}
