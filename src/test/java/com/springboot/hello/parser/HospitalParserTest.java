package com.springboot.hello.parser;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import com.springboot.hello.service.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class HospitalParserTest {
    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired //factory도 없는데 왜 될까?
    HospitalDao hospitalDao;


    @Autowired
    HospitalService hospitalService;

    @Test
    @DisplayName("Hospital이 insert, select가 잘 되는지")
    void addAndGet() {
        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
        assertEquals(1, hospitalDao.getCount());

        Hospital selectedHospital = hospitalDao.findById(hospital.getId());
        assertEquals(selectedHospital.getId(), hospital.getId());
        assertEquals(selectedHospital.getOpenServiceName(), hospital.getOpenServiceName());
        assertEquals(selectedHospital.getOpenLocalGovernmentCode(), hospital.getOpenLocalGovernmentCode());
        assertEquals(selectedHospital.getManagementNumber(), hospital.getManagementNumber());

        assertEquals(selectedHospital.getBusinessStatus(), hospital.getBusinessStatus());
        assertEquals(selectedHospital.getBusinessStatusCode(), hospital.getBusinessStatusCode());
        assertEquals(selectedHospital.getPhone(), hospital.getPhone());
        assertEquals(selectedHospital.getFullAddress(), hospital.getFullAddress());
        assertEquals(selectedHospital.getRoadNameAddress(), hospital.getRoadNameAddress());
        assertEquals(selectedHospital.getHospitalName(), hospital.getHospitalName());
        assertEquals(selectedHospital.getBusinessTypeName(), hospital.getBusinessTypeName());
        assertEquals(selectedHospital.getHealthcareProviderCount(), hospital.getHealthcareProviderCount());
        assertEquals(selectedHospital.getPatientRoomCount(), hospital.getPatientRoomCount());
        assertEquals(selectedHospital.getTotalNumberOfBeds(), hospital.getTotalNumberOfBeds());

        //날짜, float
        assertTrue(selectedHospital.getLicenseDate().isEqual(hospital.getLicenseDate()));
        assertEquals(selectedHospital.getTotalAreaSize(), hospital.getTotalAreaSize());
    }

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        this.hospitalReadLineContext = context.getBean(
                "hospitalReadLineContext", ReadLineContext.class);
    }

    @Test
    @DisplayName("10만 건 이상 데이터가 파싱되는지") //코드 수정
    void oneHundreadThousandRows() throws IOException {
        //서버 환경에서 build 시 문제가 생길 수 있음.
        //어디에서든지 실행할 수 있게 짜는 것이 목표
        hospitalDao.deleteAll();
        String filename = "C:\\Users\\dohyu\\git\\hello-kdt\\src\\main\\java";
        int cnt = this.hospitalService.insertLargeVolumeHospitalData(filename);
        assertTrue(cnt > 1000);
        assertTrue(cnt > 10000);
        System.out.printf("파싱된 데이터 개수:%d", cnt);
    }

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 test")
    void convertToHospital() {
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // col:0
        assertEquals("의원", hospital.getOpenServiceName());//col:1
        assertEquals(3620000, hospital.getOpenLocalGovernmentCode()); // col: 3
        assertEquals("PHMA119993620020041100004", hospital.getManagementNumber()); // col:4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
        assertEquals(1, hospital.getBusinessStatus()); //col:7
        assertEquals(13, hospital.getBusinessStatusCode());//col:9
        assertEquals("062-515-2875", hospital.getPhone());//col:15
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());//col:19
        assertEquals("효치과의원", hospital.getHospitalName());//col:21
        assertEquals("치과의원", hospital.getBusinessTypeName());//col:25
        assertEquals(1, hospital.getHealthcareProviderCount()); //col:29
        assertEquals(0, hospital.getPatientRoomCount()); //col:30
        assertEquals(0, hospital.getTotalNumberOfBeds()); //col:31
        assertEquals(52.29f, hospital.getTotalAreaSize()); //col:32

    }
}

