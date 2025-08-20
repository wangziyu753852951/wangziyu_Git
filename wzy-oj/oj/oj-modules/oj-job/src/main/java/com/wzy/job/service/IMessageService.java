package com.wzy.job.service;

import com.wzy.job.domain.message.Message;

import java.util.List;

public interface IMessageService {

    boolean batchInsert(List<Message> messageTextList);
}
