package fleetmanagementapi;

import fleetmanagementapi.service.TaxisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
class FleetManagementApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaxisService taxisService;

	//@BeforeEach


	//@Test

}
