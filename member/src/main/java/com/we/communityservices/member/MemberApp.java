/*
 * It is not open source project!
 */
package com.we.communityservices.member;

import com.we.communityservices.persistance.model.ParentType;
import com.we.communityservices.persistance.service.ParentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Zaid Wadud
 */
@SpringBootApplication
@ComponentScan("com.we.communityservices.persistance.service")
public class MemberApp implements CommandLineRunner{
    
    /*@Autowired
    private TestRepository repository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private StatusService statusService;*/
    
    @Autowired
    private ParentTypeService parentTypeService;
    
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
        
                /* Type model Start 
                typeService.deleteAll();                
                typeService.saveType(new Type("Office Address-15"));
                typeService.saveType(new Type("Home Address-15"));
                
                System.out.println("Types found with findAll():");
                for (Type typeList : typeService.findAll()) {
		System.out.println(typeList);
		}
                Type model End */
                
                /* ParentType model Start */
        
                //parentTypeService.deleteAll();
                parentTypeService.saveParentType(new ParentType("MemberType",true));
                parentTypeService.saveParentType(new ParentType("AddressType",true));
                parentTypeService.saveParentType(new ParentType("StatusType",true));
                parentTypeService.saveParentType(new ParentType("NotificationType",true));
                
                System.out.println("ParentType found with findAll():");
                for (ParentType typeList : parentTypeService.findAll()) {
		System.out.println(typeList);
		}
                /* Status model End */
                
                
                               
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
