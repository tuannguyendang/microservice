package com.dangtuan.auditor.service;

import com.dangtuan.auditor.util.ThreadCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Log4j2
public class AuditorService {

  @Autowired
  private MessageService messageService;

  @Async
  public String process() {
    final String message = messageService.message();
    log.info("Asyn message {}, userName {}", message, ThreadCache.getCache().get("userName"));
    return message + ThreadCache.getCache().get("userName");
  }

  public String processHi() {
    final String message = messageService.message();
    log.info("Synchronize message {}, userName {}", message,
        ThreadCache.getCache().get("userName"));
    return message + ThreadCache.getCache().get("userName");
  }
}
