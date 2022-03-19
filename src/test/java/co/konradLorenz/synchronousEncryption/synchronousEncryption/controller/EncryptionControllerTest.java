package co.konradLorenz.synchronousEncryption.synchronousEncryption.controller;

import co.konradLorenz.synchronousEncryption.synchronousEncryption.model.Text;
import co.konradLorenz.synchronousEncryption.synchronousEncryption.service.impl.EncryptionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class EncryptionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EncryptionServiceImpl encryptionService;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new ObjectMapper();
    }

    @Test
    void testEncryptionText() throws Exception {
        var req = new Text("Text to encrypted");
        var expected = "fjsdghfsdfgreuivbdcjsbvdsgkdsajfgdñghdfñgh";
        Mockito.when(encryptionService.encryption(req.getText()))
                .thenReturn(expected);
        var response = mvc
                .perform(post("/api/encryption")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(req))
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        assertThat(response).isNotEmpty();
    }
}
