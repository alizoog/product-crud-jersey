package com.example.productcrud.resource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class MainApp extends Application {
//
//    private Set<Object> singletons = new HashSet<>();
//
//    public MainApp() {
//        singletons.add(new CORSFilter());
//        singletons.add(new ProductResource());
//    }
//
////    @Override
////    public Set<Class<?>> getClasses() {
////        Set<Class<?>> classes = new HashSet<>();
////        classes.add(CORSFilter.class);
////        return classes;
////    }
//
//    @Override
//    public Set<Object> getSingletons() {
//        return singletons;
//    }
}
