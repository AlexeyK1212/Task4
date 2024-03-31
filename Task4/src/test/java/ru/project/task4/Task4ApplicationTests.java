package ru.project.task4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.task4.procedures.Actions;
import ru.project.task4.procedures.FromLogsReader;
import ru.project.task4.procedures.toDataBaseWriter.WriteUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Task4ApplicationTests {


	@Autowired
	private WriteUtils writeUtils;


	@Test
	void contextLoads() throws Exception {

		assertEquals(writeUtils.GetAll().toString().replaceAll("[\n\r]",""),
"[{login=petrov_ii, fio=Петров Иван Иванов, access_date=2024-03-21 00:00:00.0, application='other:mobileq}" +
", {login=ivanov_ip, fio=Иванов Иван Петрович, access_date=2024-03-22 00:00:00.0, application='other:web}" +
", {login=petrov_ip, fio=Петров Иван Петрович, access_date=2024-03-23 00:00:00.0, application='other:mobile}" +
"]");

	}

}
