package com.example.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/16 17:04
 * @Version: 1.0
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("****result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) throws InterruptedException {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("****result: " + result);
        return result;
    }

    //有效果
    //原生代码
    @GetMapping("/payment/ok")
    public String getHello() {

        try(Entry entry =SphU.entry("getHello")) {
           return "OK";
        } catch (Exception e ){
            log.info("被限流了");
            return "系统繁忙，稍后再试";
        }
    }


    //注解
    //好用
    @GetMapping("/singOut")
    @SentinelResource(value = "singOut", blockHandler = "Ex", fallback = "Ex")
    public String singOut() {
            return "singOut!!";
    }


    public String Ex(){
        return "系统繁忙，稍后再试";
    }

    @GetMapping("/mazda")
    public String mazda() {
        return paymentService.mazda();
    }



    /**
     * 定义隔离规则
     *
     * @PostConstruct 当前类的构造函数执行之后执行该方法
     */
    @PostConstruct
    public void initFlowRule() {
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 设置资源名称
        rule.setResource("getHello");
        // 指定限流模式为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS限流阈值
        rule.setCount(2);
        ruleList.add(rule);

        // 设置资源名称
        rule.setResource("singOut");
        // 指定限流模式为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS限流阈值
        rule.setCount(2);
        ruleList.add(rule);

        // 设置资源名称
        rule.setResource("mazda");
        // 指定限流模式为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS限流阈值
        rule.setCount(2);
        ruleList.add(rule);

        // 设置资源名称
        rule.setResource("paymentInfo_TimeOut");
        // 指定限流模式为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS限流阈值
        rule.setCount(2);
        ruleList.add(rule);

        // 设置资源名称
        rule.setResource("paymentInfo_OK");
        // 指定限流模式为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS限流阈值
        rule.setCount(2);
        ruleList.add(rule);


        // 加载该规则
        FlowRuleManager.loadRules(ruleList);
    }

}