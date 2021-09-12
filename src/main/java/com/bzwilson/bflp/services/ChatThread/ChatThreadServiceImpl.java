package com.bzwilson.bflp.services.ChatThread;


import com.bzwilson.bflp.models.ChatThread;
import com.bzwilson.bflp.repositories.ChatThreadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "ChatThreadService")
public class ChatThreadServiceImpl {

    @Autowired
    private ChatThreadRepo chatThreadRepo;

    @Transactional
    public ChatThread save(ChatThread chatThread) {
        chatThread.setMessages(chatThread.getMessages());
        return chatThreadRepo.save(chatThread);
    }

}
