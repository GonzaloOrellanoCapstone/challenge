package com.tenpo.challenge.controller;

import com.tenpo.challenge.model.LogMessage;
import com.tenpo.challenge.repository.LogMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/log-message")
public class LogMessageController {

    @Autowired
    private LogMessageRepository logMessageRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<LogMessage> getLogMessage(@RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return logMessageRepository.findAll(pageable);
    }
}
