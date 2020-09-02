package com.lot.controller;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Random;

@WebServlet(name = "BitCoinDataCenter", urlPatterns = "/BitCoinDataCenter", loadOnStartup = 1)
//标记为Servlet不是为了其被访问，而是为了便于伴随Tomcat一起启动
public class BitCoinDataCenter extends HttpServlet implements Runnable {

    public void init(ServletConfig config) {
        startup();
    }

    private void startup() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        int bitPrice = 10000;
        while (true) {
            int duration = 1000 + new Random().nextInt(2000);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            float random = (float) (1 + (Math.random() - 0.5));
            int newPrice = (int) (bitPrice * random);
            int total = ServerManager.getTotal();
            newPrice = newPrice * total;
            String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
            String message = String.format(messageFormat, newPrice, total);
            //广播出去
            ServerManager.broadCast(message);
        }
    }
}
