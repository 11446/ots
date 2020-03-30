package com.zs.ots.service.impl;

import com.zs.ots.entity.Message;
import com.zs.ots.mapper.MessageMapper;
import com.zs.ots.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/3/26 21:15
 * @description
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public boolean setMessageInfo(Message message) {
        return messageMapper.setMessageInfo(message);
    }

    @Override
    public List<Message> getPageMesInfo() {
        return messageMapper.getPageMesInfo();

    }

    @Override
    public List<Message> getAllMesInfo() {
        return messageMapper.getAllMesInfo();

    }

    @Override
    public boolean updateMessage(Message message) {
        return messageMapper.updateMessage(message);

    }

    @Override
    public boolean deleteMessage(Integer id) {
        return messageMapper.deleteMessage(id);

    }
}
