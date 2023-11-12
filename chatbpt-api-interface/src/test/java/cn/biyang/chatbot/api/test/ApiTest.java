package cn.biyang.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author biyang
 * @Distription 单元测试
 * @Date 2023/11/8 21:42
 * @Version 1.0
 */
public class ApiTest {
    @Test
    public void query_unanserwed_query() throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885181142581/topics?scope=all&count=20");

        get.addHeader("Cookie","zsxqsessionid=b7a9d944031928f9562cd93e923d9334; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212851582415181%22%2C%22first_id%22%3A%2218b1904604027-09bde91a9e4e3d8-18525634-1296000-18b19046041f00%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThiMTkwNDYwNDAyNy0wOWJkZTkxYTllNGUzZDgtMTg1MjU2MzQtMTI5NjAwMC0xOGIxOTA0NjA0MWYwMCIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjIxMjg1MTU4MjQxNTE4MSJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212851582415181%22%7D%2C%22%24device_id%22%3A%2218b1904604027-09bde91a9e4e3d8-18525634-1296000-18b19046041f00%22%7D; zsxq_access_token=AABE79A1-4F4F-CD8E-9D71-6E271B83B9C4_FCCAE6FC6278A7DF; abtest_env=product");
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588545852588584/comments");

        post.addHeader("Cookie","zsxqsessionid=b7a9d944031928f9562cd93e923d9334; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212851582415181%22%2C%22first_id%22%3A%2218b1904604027-09bde91a9e4e3d8-18525634-1296000-18b19046041f00%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThiMTkwNDYwNDAyNy0wOWJkZTkxYTllNGUzZDgtMTg1MjU2MzQtMTI5NjAwMC0xOGIxOTA0NjA0MWYwMCIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjIxMjg1MTU4MjQxNTE4MSJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212851582415181%22%7D%2C%22%24device_id%22%3A%2218b1904604027-09bde91a9e4e3d8-18525634-1296000-18b19046041f00%22%7D; zsxq_access_token=AABE79A1-4F4F-CD8E-9D71-6E271B83B9C4_FCCAE6FC6278A7DF; abtest_env=product");
        post.addHeader("Content-Type","application/json");
        String paramJason = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"等\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";
        StringEntity entity = new StringEntity(paramJason, ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
