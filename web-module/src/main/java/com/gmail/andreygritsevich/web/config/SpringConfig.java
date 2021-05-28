package com.gmail.andreygritsevich.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.gmail.andreygritsevich.web",
        "com.gmail.andreygritsevich.service",
        "com.gmail.andreygritsevich.repository"})
public class SpringConfig {
}
