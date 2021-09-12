package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.ChatThread;
import com.bzwilson.bflp.models.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ChatThreadRepo extends CrudRepository<ChatThread, Long> {
}
