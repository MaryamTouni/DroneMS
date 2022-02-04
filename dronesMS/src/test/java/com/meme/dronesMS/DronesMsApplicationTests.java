package com.meme.dronesMS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.meme.dronesMS.controller.DroneController;
import com.meme.dronesMS.repository.DroneRepository;

@RunWith(SpringRunner.class) 
@WebMvcTest
@AutoConfigureMockMvc
class DronesMsApplicationTests {

	@MockBean
    private DroneRepository droneRepository;
    
    @Autowired
    DroneController droneController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void xx(){
    	
    }

}
