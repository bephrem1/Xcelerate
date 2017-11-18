package com.benyamephrem.tasking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //So that spring picks up this class as a component
public class DatabaseSeeder implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //Run task on applications startup, in this case will be used for test DB seeding

        //Remember to use this for dates:
        //new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(createdDate) ----> pass this into date queries
    }

}
