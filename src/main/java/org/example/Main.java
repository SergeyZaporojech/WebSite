package org.example;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.example.services.SeedServise;
import org.example.storage.StorageProperties;
import org.example.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@SecurityScheme(name="my-api", scheme="bearer",type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class Main {
    public static void main(String[] args) {
        //System.out.println("Привіт! Команда.");
        SpringApplication.run(Main.class, args);}

    @Bean
    CommandLineRunner init(StorageService storageService, SeedServise seedServise){
        return (args->{
            try{
                storageService.init();
                seedServise.seedRoleData();
                seedServise.seedUserData();
            }catch (Exception ex){
                System.out.println("Щось пішло не так" + ex.getMessage());
            }
        });
    }
}
