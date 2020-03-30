package com.zs.ots.controller;

import com.github.pagehelper.PageHelper;
import com.zs.ots.entity.Message;
import com.zs.ots.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/24 18:09
 * @description 留言信息功能
 */
@RestController
@RequestMapping
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 插入留言信息
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/setMessageInfo")
    public boolean setMessageInfo(@RequestBody Message message) {
        return messageService.setMessageInfo(message);
    }

    /**
     * 分页获取留言信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getPageMesInfo")
    public List<Message> getPageMesInfo(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return messageService.getPageMesInfo();
    }

    /**
     * 获取所有留言信息
     *
     * @return
     */
    @GetMapping(value = "/getAllMesInfo")
    public List<Message> getAllMesInfo() {
        return messageService.getAllMesInfo();
    }

    /**
     * 更改留言
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/updateMessage")
    public boolean updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/deleteMessage")
    public boolean deleteMessage(@RequestParam("id") Integer id) {
        return messageService.deleteMessage(id);
    }
}
