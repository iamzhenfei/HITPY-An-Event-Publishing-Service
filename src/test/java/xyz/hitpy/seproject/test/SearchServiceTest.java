package xyz.hitpy.seproject.test;

import org.junit.Test;
import xyz.hitpy.seproject.service.SearchService;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class SearchServiceTest {

	@Test
	public void testSearchByName() {
		ResultSet res = SearchService.searchByName(" ");
		if ((res!=null)) {

		}
		else
			fail("fail in searchByName Method in SearchService.java in package xyz.hitpy.seproject.service");
		
	}

}
