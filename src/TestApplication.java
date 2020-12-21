import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.training.models.Edible;
import com.training.models.Electronic;
import com.training.models.Garment;
import com.training.services.EdibleService;
import com.training.services.ElectronicService;
import com.training.services.GarmentService;
import com.training.utils.ConnectionUtils;

class TestApplication {
	
	Connection connection = ConnectionUtils.getMySqlConnection();
	EdibleService edibleService = new EdibleService(connection);
	GarmentService garmentService = new GarmentService(connection);
	ElectronicService electronicService = new ElectronicService(connection);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddEdible() {
		Edible edi = new Edible(LocalDate.of(2014, Month.JANUARY, 1), 101, "Eggs", 10.5, 4, LocalDate.of(2013, Month.DECEMBER, 29), LocalDate.of(2014, Month.JANUARY, 4), "Non-Veg");
		assertTrue(edibleService.add(edi));
	}
	
	@Test
	void testAddGarment() {
		Garment gar = new Garment(LocalDate.of(2014, Month.MARCH, 4), 103, "Shirts", 7250, 40, "M", "gents");
		assertTrue(garmentService.add(gar));
	}
	
	@Test
	void testAddElectronic() {
		Electronic elec = new Electronic(LocalDate.of(2014, Month.JANUARY, 1), 103, "Mobile", 1600, 1, "5.5 inch", 1, 100);
		assertTrue(electronicService.add(elec));
	}
	
	@Test
	void testNotNull1() {
		assertNotNull(edibleService.getReportByDate(LocalDate.of(2014, 1, 1)));
	}
	
	@Test
	void testNotNull2() {
		assertNotNull(edibleService.getTopThreeProductsSold(LocalDate.of(2014, 1, 1)));
	}

}
