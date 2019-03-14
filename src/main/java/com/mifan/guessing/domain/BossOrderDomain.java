package com.mifan.guessing.domain;

import com.github.pagehelper.PageInfo;
import com.mifan.guessing.controller.request.OrderListReq;
import com.mifan.guessing.controller.request.SettleOrderListReq;
import com.mifan.guessing.controller.response.OrderListRes;
import com.mifan.guessing.controller.response.SettleOrderListRes;
import com.mifan.guessing.service.OrderService;
import com.mifan.guessing.utils.BeanMapper;
import com.mifan.guessingapi.request.order.BossOrderListRequest;
import com.mifan.guessingapi.request.order.BossSettleOrderListRequest;
import com.mifan.guessingapi.response.BaseResponse;
import com.mifan.guessingapi.response.order.BossOrderListResponse;
import com.mifan.guessingapi.response.order.BossSettleOrderListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BossOrderDomain {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @return
     */
    public PageInfo<OrderListRes> orderList(OrderListReq orderListReq) {
        BossOrderListRequest request = BeanMapper.map(orderListReq,BossOrderListRequest.class);
        BaseResponse<PageInfo<BossOrderListResponse>> pageInfoBaseResponse = orderService.orderList(request);
        PageInfo pageInfo = pageInfoBaseResponse.getResult();
        pageInfo.setList(BeanMapper.mapList(pageInfo.getList(),OrderListRes.class));
        return pageInfo;
    }

    /**
     * 注单列表
     */
    public PageInfo<SettleOrderListRes> settleOrderList(SettleOrderListReq settleOrderListReq) {

        BossSettleOrderListRequest request = BeanMapper.map(settleOrderListReq,BossSettleOrderListRequest.class);
        BaseResponse<PageInfo<BossSettleOrderListResponse>> pageInfoBaseResponse = orderService.settleList(request);
        PageInfo pageInfo = pageInfoBaseResponse.getResult();
        pageInfo.setList(BeanMapper.mapList(pageInfo.getList(),SettleOrderListRes.class));
        return pageInfo;
    }
}
