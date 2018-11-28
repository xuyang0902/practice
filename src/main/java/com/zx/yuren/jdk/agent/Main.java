package com.zx.yuren.jdk.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;

import java.io.IOException;

/**
 * java -javaagent:/Users/yuren/.m2/repository/com/practice/agent/1.0/agent-1.0.jar
 *
 * @author xu.qiang
 * @date 18/11/7
 */
public class Main {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {

        Main main = new Main();
        main.test();


//        List<VirtualMachineDescriptor> list = VirtualMachine.list();
//        for (VirtualMachineDescriptor vmd : list) {
//            if (vmd.displayName().endsWith("Main")) {
//                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
//                virtualMachine.loadAgent("/Users/yuren/.m2/repository/com/practice/agent/1.0/agent-1.0.jar", "agentArgs");
//                System.out.println("ok");
//                virtualMachine.detach();
//            }
//        }

    }


    public void test() {

        System.out.println("client test");

    }

}
