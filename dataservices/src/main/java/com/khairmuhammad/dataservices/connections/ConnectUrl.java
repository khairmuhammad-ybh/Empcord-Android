package com.khairmuhammad.dataservices.connections;

public class ConnectUrl {

    private static String HOST = "localhost";   //Change host to your server ip address

    public static String URL_REGISTER = "http://"+ HOST +":3000/users/register";
    public static String URL_REGISTER_WORKER = "http://"+ HOST +":3000/users/register/worker";
    public static String URL_LOGIN = "http://"+ HOST +":3000/users/login";
    public static String URL_GET_COMPANIES = "http://"+ HOST +":3000/companies";
}
