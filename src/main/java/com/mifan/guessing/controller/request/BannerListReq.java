package com.mifan.guessing.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerListReq {

    private int pageNum =1;

    private int pageSize = 10;
}
