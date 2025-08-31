package br.com.fiap.on.iez.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    private final ChatClient chatClient;

    public AiChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String conversar(String userInput) {
        return chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }
}
