package com.mifan.guessing.controller;

import com.github.pagehelper.PageInfo;
import com.mifan.guessing.controller.request.AddBannerReq;
import com.mifan.guessing.controller.request.BannerListReq;
import com.mifan.guessing.controller.response.BannerListRes;
import com.mifan.guessing.domain.BossBannerDomain;
import com.mifan.guessing.utils.FileUDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther wangbinlei
 * @create 2018/12/13
 */
@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BossBannerDomain bannerDomain;

    @RequestMapping("list")
    public String bannerList(Model model, BannerListReq bannerListReq){

        PageInfo<BannerListRes> bannerListResList = bannerDomain.bannerList(bannerListReq);
        model.addAttribute("bannerList",bannerListResList);
        return "banner_list";
    }

    @RequestMapping("add")
    public String add(Model model, AddBannerReq addBannerReq, MultipartFile bannerFile){
        //保存banner图片
        String filePath = "";
        FileUDUtils.uploadFile(filePath, bannerFile);
        addBannerReq.setPicPath(filePath);
        PageInfo<BannerListRes> bannerListResList =  bannerDomain.add(addBannerReq);
        model.addAttribute("bannerList",bannerListResList);
        return "banner_list";
    }
}
