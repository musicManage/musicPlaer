package com.javaclimb.service;

public interface IMailService {

    /**
     * 发送邮件
     * @param to
     * @param title
     * @param content
     */
    public void sendSimpleMail(String to,String title,String content);
}
