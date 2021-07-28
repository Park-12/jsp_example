package com.sbs.exam.exam1.http.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sbs.exam.exam1.app.App;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        App.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("On shutdown web app");
    }

}
