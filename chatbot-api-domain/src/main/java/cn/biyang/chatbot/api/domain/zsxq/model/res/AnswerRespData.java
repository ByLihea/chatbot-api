package cn.biyang.chatbot.api.domain.zsxq.model.res;

/**
 * @Author biyang
 * @Date 2023/11/20 15:22
 * @Version 1.0
 */
public class AnswerRespData {
    private boolean succeeded;

    public AnswerRespData(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }
}
