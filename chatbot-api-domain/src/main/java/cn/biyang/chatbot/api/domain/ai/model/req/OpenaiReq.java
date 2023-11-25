package cn.biyang.chatbot.api.domain.ai.model.req;

import cn.biyang.chatbot.api.domain.ai.model.vo.Messages;

/**
 * @Author biyang
 * @Date 2023/11/24 23:31
 * @Version 1.0
 */
public class OpenaiReq {
    private String question;
    private String model;
    private Messages messages;



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }
}
