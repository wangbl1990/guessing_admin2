package com.mifan.guessing.domain;

import com.github.pagehelper.PageInfo;
import com.mifan.guessing.controller.request.AddBannerReq;
import com.mifan.guessing.controller.request.BannerListReq;
import com.mifan.guessing.controller.response.BannerListRes;
import com.mifan.guessing.service.BannerService;
import com.mifan.guessing.utils.BeanMapper;
import com.mifan.guessingapi.request.banner.BossBannerListRequest;
import com.mifan.guessingapi.response.BaseResponse;
import com.mifan.guessingapi.response.banner.BossBannerListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BossBannerDomain {

    @Autowired
    private BannerService bannerService;


    public PageInfo<BannerListRes> bannerList(BannerListReq bannerListReq) {
        BossBannerListRequest request = new BossBannerListRequest();
        request.setPageNum(bannerListReq.getPageNum());
        request.setPageSize(bannerListReq.getPageSize());
        BaseResponse<PageInfo<BossBannerListResponse>> list = bannerService.list(request);
        PageInfo pageInfo = list.getResult();
        List<BannerListRes> resList = BeanMapper.mapList(pageInfo.getList(),BannerListRes.class);
        pageInfo.setList(resList);
        return pageInfo;
    }

    public PageInfo<BannerListRes> add(AddBannerReq addBannerReq) {
        List<BannerListRes> resList = new ArrayList<BannerListRes>();
        BannerListRes res = new BannerListRes();
        res.setPicLink("www.baidu.com");
        res.setPicPath("picPath");
        res.setTitle("图片");
        res.setId("1");
        resList.add(res);
        PageInfo<BannerListRes> pageInfo = new PageInfo<BannerListRes>(resList);
        pageInfo.setList(resList);
        return pageInfo;
    }
}
