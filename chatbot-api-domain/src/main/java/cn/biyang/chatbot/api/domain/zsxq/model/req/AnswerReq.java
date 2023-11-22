package cn.biyang.chatbot.api.domain.zsxq.model.req;

/**
 * @Author biyang
 * @discription 请求问答
 * @Date 2023/11/18 17:16
 * @Version 1.0
 */
public class AnswerReq {
   private Req_data req_data;

    public AnswerReq(Req_data req_data) {
        this.req_data = req_data;
    }

    public Req_data getReq_data() {
        return req_data;
    }

    public void setReq_data(Req_data req_data) {
        this.req_data = req_data;
    }
}
