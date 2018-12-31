package com.squirrelAi.test;

import com.zhangyibin.aifunction.BaiduFanyi;
import com.zhangyibin.aifunction.SquirrelAiRobot;

public class RobotTest {

    public static void main(String[] args) throws Exception {
        String strGreetings = "明天的天气好吗?";

        String strRobotResponse = SquirrelAiRobot.SquirrelRobot(strGreetings);

        System.out.println("问候语：" + strGreetings);
        System.out.println(strRobotResponse);
        System.out.println(BaiduFanyi.getBaiduFanyi(strRobotResponse));
    }

}
