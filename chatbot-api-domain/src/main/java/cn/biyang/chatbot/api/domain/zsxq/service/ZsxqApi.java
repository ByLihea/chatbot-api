package cn.biyang.chatbot.api.domain.zsxq.service;

import cn.biyang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.biyang.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.biyang.chatbot.api.domain.zsxq.model.req.Req_data;
import cn.biyang.chatbot.api.domain.zsxq.model.res.AnswerRespData;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;


/**
 * @Author biyang
 * @Date 2023/11/14 17:22
 * @Version 1.0
 */
@Service
public class ZsxqApi implements IZsxqAPI {
    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);
    @Override
    public UnAnsweredQuestionAggregates queryUnAnsweredQuestionTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=all&count=20");
        get.addHeader("Cookie",cookie);
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答星球拉取问题数据. groupId: {}  jsonStr: {}",groupId,jsonStr);
            return JSON.parseObject(jsonStr,UnAnsweredQuestionAggregates.class);
        }else{
            throw new  RuntimeException("queryUnAnsweredQuestionTopicId ERR is"+response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/comments");
        post.addHeader("Cookie", cookie);
        post.addHeader("Content-Type","application/json");
        //为了保证安全，确保是从浏览器发起的请求，比如风控的时候会有一些要求
       // post.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");
//        String paramJason = "{\n" +
//                "  \"req_data\": {\n" +
//                "    \"text\": \""+text+"\\n\",\n" +
//                "    \"image_ids\": [],\n" +
//                "    \"mentioned_user_ids\": []\n" +
//                "  }\n" +
//                "}";

        AnswerReq answerReq = new AnswerReq(new Req_data(text));
        String s = JSONObject.fromObject(answerReq).toString();
        logger.info("传送数据为{}",s);
        StringEntity entity = new StringEntity(s, ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答星球问题的结果数据. groupId: {} TopicId: {} jsonStr: {}",groupId,topicId,jsonStr);
            AnswerRespData answerRespData = JSON.parseObject(jsonStr, AnswerRespData.class);
            return answerRespData.isSucceeded();
        }else{
            throw new RuntimeException("answer err is :" +response.getStatusLine().getStatusCode() );
        }
    }
}
