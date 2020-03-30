package com.zs.ots.service;

import com.zs.ots.entity.Message;
import com.zs.ots.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/24 18:16
 * @description
 */
public interface MessageService {

    /**
     * 插入文件信息
     * @param message
     * @return
     */
    public boolean setMessageInfo(Message message);

    /**
     * 分页获取留言信息
     * @return
     */
    public List<Message> getPageMesInfo() ;

    /**
     * 获取所有留言信息
     * @return
     */
    public List<Message> getAllMesInfo() ;

    /**
     * 更改留言信息
     * @param message
     * @return
     */
    public boolean updateMessage(Message message) ;

    /**
     * 删除留言信息
     * @param id
     * @return
     */
    public boolean deleteMessage(Integer id) ;
}
