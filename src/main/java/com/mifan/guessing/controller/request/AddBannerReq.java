package com.mifan.guessing.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBannerReq {

    private String id;

    private String title;//banner标题

    private String picPath;//banner图片存储路径

    private String picLink;//banner图片链接地址

}
