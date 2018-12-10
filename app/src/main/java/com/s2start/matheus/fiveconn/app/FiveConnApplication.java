package com.s2start.matheus.fiveconn.app;

import android.app.Application;

import com.s2start.matheus.fiveconn.services.RestService;
import com.s2start.matheus.fiveconn.services.ServicePost;
import com.s2start.matheus.fiveconn.services.ServiceUsuario;

public class FiveConnApplication extends Application {


    public static final String URL = "IP_DO_SEU_SERVIDOR";

    private static FiveConnApplication instance;

    private ServiceUsuario serviceUsuario;
    private ServicePost servicePost;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createServices();
    }



    private void createServices(){
        serviceUsuario = (new RestService(URL)).getService(ServiceUsuario.class);
        servicePost = (new RestService(URL)).getService(ServicePost.class);

    }

    public static FiveConnApplication getInstance() {
        return instance;
    }

    public ServiceUsuario getServiceUsuario() {
        return serviceUsuario;
    }

    public ServicePost getServicePost() {
        return servicePost;
    }

}
