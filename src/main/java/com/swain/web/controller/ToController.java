package com.swain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 这个类用来控制网页之间的跳转
 */

@Controller
public class ToController {

    @GetMapping("/to/login")
    public String toLogin() {
        return "login";
    }

    //管理员首页
    @RequestMapping("/to/index")
    public String toIndex() {
        return "index";
    }

    //员工首页
    @RequestMapping("/to/index2")
    public String toIndex2() {
        return "index2";
    }

    @RequestMapping("/to/welcome")
    public String toWelcome() {
        return "welcome";
    }

//    /**
//     *     客户 - 订单管理
//     */
//
//    @RequestMapping("/to/customer-sorder")
//    public String toCustomerSorder() {
//        return "customer-sorder";
//    }
//
//    @RequestMapping("/to/customer-sorder-add")
//    public String toCustomerAddSorder() {
//        return "customer-sorder-add";
//    }
//
//    /**
//     *     快递员 - 订单管理
//     */
//
//
//    @RequestMapping("/to/trans-received")
//    public String toTransReceived() {
//        return "trans-send";
//    }
//
//    @RequestMapping("/to/trans-send")
//    public String toTransSend() {
//        return "trans-received";
//    }


    /**
     *     管理员 - 人员管理
     */
    @RequestMapping("/to/admin-user")
    public String toAdminUser() {
        return "admin-user";
    }
    @RequestMapping("/to/admin-user-add")
    public String toAdminAddUser() {
        return "admin-user-add";
    }
    @RequestMapping("/to/admin-user-update")
    public ModelAndView toAdminUpdateUser(@RequestParam Long userId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-user-update");
        mv.addObject("userId", userId);
        return mv;
    }


    /**
     *     管理员 - 机器管理
     */
    @RequestMapping("/to/admin-machine")
    public String toAdminMachine() {
        return "admin-machine";
    }
    @RequestMapping("/to/admin-machine-add")
    public String toAdminAddMachine() {
        return "admin-machine-add";
    }
    @RequestMapping("/to/admin-machine-update")
    public ModelAndView toAdminUpdateMachine(@RequestParam Long machineId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-machine-update");
        mv.addObject("machineId", machineId);
        return mv;
    }

    /**
     *  管理员 物料管理
     */
    @RequestMapping("/to/admin-material")
    public String toAdminMaterial() {return "admin-material";}

    @RequestMapping("/to/admin-material-add")
    public String toAdminAddMaterial() {return "admin-material-add";}

    @RequestMapping("/to/admin-material-update")
    public ModelAndView toAdminUpdateMaterial(@RequestParam Long materialId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-material-update");
        mv.addObject("materialId", materialId);
        return mv;
    }


    /**
     * 管理员 生产数据管理
     */
    @RequestMapping("/to/admin-product")
    public String toAdminProduct(){
        return "admin-product";
    }
    @RequestMapping("/to/admin-product-update")
    public ModelAndView toAdminUpdateProduct(@RequestParam Long productId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-product-update");
        mv.addObject("productId", productId);
        return mv;
    }

    /**
     * 管理员 维修管理
     */
    @RequestMapping("/to/admin-repair")
    public String toAdminRepair(){
        return "admin-repair";
    }
    @RequestMapping("/to/admin-repair-update")
    public ModelAndView toAdminUpdateRepair(@RequestParam Long repairId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-repair-update");
        mv.addObject("repairId", repairId);
        return mv;
    }

//
//    /**
//     *     管理员 - 车辆管理
//     */
//    @RequestMapping("/to/admin-car")
//    public String toAdminCar() {
//        return "admin-car";
//    }
//
//    @RequestMapping("/to/admin-car-add")
//    public String toAdminAddCar() {
//        return "admin-car-add";
//    }
//
//    /**
//     *     管理员 - 配送管理
//     */
//    @RequestMapping("/to/admin-arrange")
//    public String toAdminArrange() {
//        return "admin-arrange";
//    }
//    @RequestMapping("/to/admin-arrange-update")
//    public ModelAndView toAdminUpdateArrange(@RequestParam Long orderId, @RequestParam Long orderRegionId) {
//        System.out.println(orderId);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("admin-arrange-update");
//        mv.addObject("orderId", orderId);
//        mv.addObject("orderRegionId", orderRegionId);
//        return mv;
//    }
//
//    /**
//     *     管理员 - 报表管理
//     */
//    @RequestMapping("/to/admin-report")
//    public String toAdminReport() {
//        return "admin-report";
//    }


    /**
     *  员工 物料管理
     */
    @RequestMapping("/to/staff-material")
    public String toStaffMaterial() {return "staff-material";}

    @RequestMapping("/to/staff-material-add")
    public String toStaffAddMaterial() {return "staff-material-add";}

    @RequestMapping("/to/staff-material-update")
    public ModelAndView toStaffUpdateMaterial(@RequestParam Long materialId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("staff-material-update");
        mv.addObject("materialId", materialId);
        return mv;
    }
    /**
     * 员工 生产数据管理
     */
    @RequestMapping("/to/staff-product")
    public String toStaffProduct() {return "staff-product";}
    @RequestMapping("/to/staff-product-add")
    public String toStaffAddProduct(){
        return "staff-product-add";
    }
    @RequestMapping("/to/staff-product-update")
    public String toStaffUpdateProduct(){
        return "staff-product-update";
    }
    /**
     * 员工 故障上报管理
     */
    @RequestMapping("/to/staff-repair")
    public String toStaffRepair() {return "staff-repair";}

    @RequestMapping("/to/staff-repair-add")
    public String toStaffAddRepair() {return "staff-repair-add";}

    @RequestMapping("/to/admin-chart")
    public String toAdminChart() {return "admin-chart";}


}
