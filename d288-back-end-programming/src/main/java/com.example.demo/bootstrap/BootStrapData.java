package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        long customer_Counter = customerRepository.count();

        if(customer_Counter == 1) {
            Division division1 = divisionRepository.findById(2L).orElse(null);
            Division division2 = divisionRepository.findById(72L).orElse(null);
            Division division3 = divisionRepository.findById(23L).orElse(null);
            Division division4 = divisionRepository.findById(11L).orElse(null);
            Division division5 = divisionRepository.findById(61L).orElse(null);

            Customer alice = new Customer("Alice", "Anderson", "456 Sunny Lane", "55555", "(987) 654 - 3210", division1);
            Customer bob = new Customer("Bob", "Baker", "789 Joyful Avenue", "66666", "(234) 567 - 8901", division2);
            Customer carol = new Customer("Carol", "Clark", "321 Cheerful Road", "77777", "(876) 543 - 2109", division3);
            Customer david = new Customer("David", "Davis", "654 Blissful Boulevard", "88888", "(345) 678 - 9012", division4);
            Customer emily = new Customer("Emily", "Edwards", "987 Delightful Drive", "99999", "(432) 109 - 8765", division5);

            alice.setDivision(division1);
            bob.setDivision(division2);
            carol.setDivision(division3);
            david.setDivision(division4);
            emily.setDivision(division5);

            customerRepository.save(alice);
            customerRepository.save(bob);
            customerRepository.save(carol);
            customerRepository.save(david);
            customerRepository.save(emily);

        }

    }
}
