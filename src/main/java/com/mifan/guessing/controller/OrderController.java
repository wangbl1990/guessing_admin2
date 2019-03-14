package com.mifan.guessing.controller;

import com.github.pagehelper.PageInfo;
import com.mifan.guessing.controller.request.OrderListReq;
import com.mifan.guessing.controller.request.SettleOrderListReq;
import com.mifan.guessing.controller.response.OrderListRes;
import com.mifan.guessing.controller.response.SettleOrderListRes;
import com.mifan.guessing.domain.BossOrderDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther wangbinlei
 * @create 2018/12/13
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private BossOrderDomain orderDomain;

    @RequestMapping("list")
    public String orderList(Model model, OrderListReq orderListReq){

        System.out.println("订单列表查询");
        if(0 == orderListReq.getPageNum()){
            orderListReq.setPageNum(1);
        }
        PageInfo<OrderListRes> orderList = orderDomain.orderList(orderListReq);
        model.addAttribute("orderList",orderList);
        model.addAttribute("pageNum",orderListReq.getPageNum());
        model.addAttribute("userName",orderListReq.getUserName());
        model.addAttribute("orderId",orderListReq.getOrderId());
        return "order_list";
    }

    @RequestMapping("settleList")
    public String settleList(Model model, SettleOrderListReq settleOrderListReq){
        if(0 == settleOrderListReq.getPageNum()){
            settleOrderListReq.setPageNum(1);
        }
        PageInfo<SettleOrderListRes> settleOrderListRes = orderDomain.settleOrderList(settleOrderListReq);
        model.addAttribute("settleOrderList",settleOrderListRes);
        model.addAttribute("pageNum",settleOrderListReq.getPageNum());
        model.addAttribute("orderId",settleOrderListReq.getOrderId());
        return "settle_order_list";
    }
}
