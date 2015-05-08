/*
 * It is not open source project!
 */
package com.we.communityservices.member;

import com.we.communityservices.member.model.TestModel;
import com.we.communityservices.member.model.Type;
import com.we.communityservices.member.repository.TestRepository;
import com.we.communityservices.member.service.AddressService;
import com.we.communityservices.member.service.MemberService;
import com.we.communityservices.member.service.StatusService;
import com.we.communityservices.member.service.TypeService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Zaid Wadud
 */
@SpringBootApplication
public class MemberApp implements CommandLineRunner{
    
    @Autowired
    private TestRepository repository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private StatusService statusService;
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MemberApp.class, args);

        /*
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        System.out.println(beanName);
        }
        */
    }

    @Override
    public void run(String... strings) throws Exception {
    
                
                typeService.saveType(new Type(101,"Office Address"));
                typeService.saveType(new Type(102,"Home Address"));
                
                System.out.println("Types found with findAll():");
                for (Type typeList : typeService.findAll()) {
			System.out.println(typeList);
		}
                
                /*//repository.deleteAll();
                
                // save a couple of customers
                //repository.save(new TestModel("Alice", "Smith"));
                //repository.save(new TestModel("Bob", "Smith"));
                
                // fetch all customers
                System.out.println("Customers found with findAll():");
                System.out.println("-------------------------------");
                repository.findAll().stream().forEach((customer) -> {
                System.out.println(customer);
                });*/
		//System.out.println();

		// fetch an individual customer
                /*System.out.println("Customer found with findByFirstName('Alice'):");
                System.out.println("--------------------------------");
                System.out.println(repository.findByFirstName("Alice"));
                
                System.out.println("Customers found with findByLastName('Smith'):");
                System.out.println("--------------------------------");
                for (TestModel customer : repository.findByLastName("Smith")) {
                System.out.println(customer);
                }*/
    
    }

}
