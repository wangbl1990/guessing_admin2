package com.mifan.guessing.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListRes {

    private String orderId;

    private String eventId;

    private String marketId;

    private String selectionId;

    private BigDecimal requestAmount;

    private BigDecimal requestPrice;

    private String status;

    private Date submittedTime;

    private String userCode;

    private String userName;

    private String eventType;

    private String eventName;

    private BigDecimal fillAmount;

    private BigDecimal fillPrice;

    private String placedResult;

}
