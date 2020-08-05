//package alpha.profile.controller;
//
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//class ProfileControllerTest {
//    private MockMvc mockMvc;
//    @InjectMocks
//    private ProfileController profileController;
//
//    @Before
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
//    }
//    @Test
//    public void testAllUsers() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//
//
//}