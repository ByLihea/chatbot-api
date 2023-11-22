package cn.biyang.chatbot.api.domain.zsxq.model.aggregates;

import cn.biyang.chatbot.api.domain.zsxq.model.res.Resp_data;

/**
 * @Author biyang
 * @Date 2023/11/14 17:13
 * @Version 1.0
 */
public class UnAnsweredQuestionAggregates {
    private boolean succeeded;
    private Resp_data resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Resp_data getResp_data() {
        return resp_data;
    }

    public void setResp_data(Resp_data resp_data) {
        this.resp_data = resp_data;
    }
}
