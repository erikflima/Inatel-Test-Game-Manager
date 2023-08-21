package com.gamemanager.app.springdoc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
    	
        return new OpenAPI().info( getInfo() );
        			  
    }
  
    
	//Info about the documentation.
	public Info getInfo() {  
				
		return new Info().title("🕹️ Game Manager API documentation") 
		          		 .version("v1")
		          		 .description("📋 Here you can check all information about the endpoints, parameters, responses, validations, etc. \n\n 👨🏽‍💻 If you need help, please contact our development team.")		          		 
		          		 .license( getLicense() );
	}


	public License getLicense() {  
		
		return new License().name("Made by Erik Lima (Click here to hire him 👍)")
        			 	    .url("http://eriklima.com");
	}


}