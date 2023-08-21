package com.gamemanager.app.services;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.gamemanager.app.model.Notification;
import com.gamemanager.app.model.Publisher;

@Service
public class PublisherService {

	private static final Logger log = LoggerFactory.getLogger( PublisherService.class);
	
    @Autowired
    private Environment environment;
    
	@Value("${baseUrlPublisher}")
	private String baseUrlPublisher;
	
	@Value("${pathPublisher}")
	private String pathPublisher;
  
	@Value("${pathNotification}")
	private String pathNotification;

   
	private RestTemplate restTemplate = new RestTemplate();


	public PublisherService() {
	}


	@Cacheable("cachePublisherList")
	public List<Publisher> findAll() {


		log.info("Finding all publishers.");
		
        UriComponents uri = UriComponentsBuilder.fromHttpUrl( baseUrlPublisher )
											        		 .path( pathPublisher )
											        		 .build();
		
		log.info("Request to uri: " +uri.toString() );
		
	    ResponseEntity< Publisher[] > responseEntity = restTemplate.exchange( uri.toString(),
																	          HttpMethod.GET,
																	          new HttpEntity<>( getHeaders() ),
																	          Publisher[].class );

	    return Arrays.asList( responseEntity.getBody() );
	}


    @CacheEvict(value = "cachePublisherList", allEntries = true)
    public void clearCache() {
    	
		log.info("Cache was deleted!");
	
    }	


	public boolean PublisherIsNotValid( String publisherId ) {

		List<Publisher> publisherList = findAll();	


		//Check if the publihser is in the list.
        Optional<Publisher> foundPublisher = publisherList.stream()
                .filter(publisher -> publisher.getId().equals( publisherId ))
                .findFirst();


        if ( foundPublisher.isPresent() ) {
	    	
	    	return false;
	    }

		return true;
	}		


	public void NotifyPublisherManager() {

		log.info("Notifying the publisher manager app.");   

        UriComponents uri = UriComponentsBuilder.fromHttpUrl( baseUrlPublisher )
												       		 .path( pathNotification )
												       		 .build();

		log.info("Request to uri: " +uri.toString() );


        Notification notification = new Notification( environment.getProperty("standardHost"), 
        											  environment.getProperty("server.port") );


        //Preparing the structure of the 'header' and 'body'.
        HttpEntity<Notification> entityDoBody = new HttpEntity<Notification>( notification, getHeaders() );	
      
		
        ResponseEntity<Notification[]> response = restTemplate.exchange( uri.toString(),
																	       HttpMethod.POST,
																	       entityDoBody,
																	       Notification[].class );
        
        if( response.getStatusCode() == HttpStatus.OK ){

        	log.info("Notification completed!");
        	
        }else {

        	log.error("An error occurred when trying to perform the notification.");
        }
        
	}	


	private HttpHeaders getHeaders(){

		//Preparing the request header.
	    HttpHeaders headers = new HttpHeaders();
	    
	    headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON) );
	    
	    headers.setContentType( MediaType.APPLICATION_JSON  );

		return headers;	
	}

}