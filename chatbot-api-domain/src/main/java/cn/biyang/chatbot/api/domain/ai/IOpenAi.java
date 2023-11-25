package cn.biyang.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @Author biyang
 * @Date 2023/11/24 18:10
 * @Version 1.0
 */
public interface IOpenAi {
    String chatWithOpenai(String question) throws IOException;
}
