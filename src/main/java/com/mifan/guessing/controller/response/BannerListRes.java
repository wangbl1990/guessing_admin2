package com.mifan.guessing.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerListRes {

    private String id;

    private String title;//banner标题

    private String picPath;//banner图片存储路径

    private String picLink;//banner图片链接地址

    private Date createTime;

    private Date modifyTime;

}
