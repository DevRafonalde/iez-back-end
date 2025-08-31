package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.services.AiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiChatController {

    @Autowired
    private AiChatService chatService;

    @GetMapping
    public String responder(@RequestParam String pergunta) {
        return chatService.conversar(pergunta);
    }

}
