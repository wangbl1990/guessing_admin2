package com.mifan.guessing.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettleOrderListReq {

    private int pageNum;

    private int pageSize =10;

    private String userName;

    private String orderId;
}
