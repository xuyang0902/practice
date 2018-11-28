//package com.zx.yuren.sentinel;
//
//import com.alibaba.csp.sentinel.Entry;
//import com.alibaba.csp.sentinel.SphU;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.RuleConstant;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author xu.qiang
// * @date 18/11/2
// */
//public class MainTest {
//
//    public static void main(String[] args) {
//        initFlowRules();
//            Entry entry = null;
//            try {
//                entry = SphU.entry("HelloWorld");
//                System.out.println("hello world");
//            } catch (BlockException e1) {
//                System.out.println("block!----》》》》》》》");
//            } finally {
//                if (entry != null) {
//                    entry.exit();
//                }
//            }
//    }
//
//    private static void initFlowRules(){
//        List<FlowRule> rules = new ArrayList<FlowRule>();
//        FlowRule rule = new FlowRule();
//        rule.setResource("HelloWorld");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // Set limit QPS to 20.
//        rule.setCount(20);
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }
//
//}
