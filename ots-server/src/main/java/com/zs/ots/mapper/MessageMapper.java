package com.zs.ots.mapper;

import com.zs.ots.entity.Message;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/24 18:17
 * @description 留言信息mapper文件
 */
public interface MessageMapper {

    boolean setMessageInfo(Message message);

    List<Message> getPageMesInfo();

    List<Message> getAllMesInfo();

    boolean updateMessage(Message message);

    boolean deleteMessage(Integer id);
}
