package com.springboot.hello.service;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import com.springboot.hello.parser.ReadLineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class HospitalService {
    private final ReadLineContext<Hospital> hospitalReadLineContext;

    private final HospitalDao hospitalDao;

    public HospitalService(ReadLineContext<Hospital> hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }

    @Transactional
    public int insertLargeVolumeHospitalData(String filename) {
        int cnt = 0;
        try {
            List<Hospital> hospitalList =
                    hospitalReadLineContext.readByLine(filename);
            System.out.println("파싱이 끝났습니다");
            for (Hospital hospital : hospitalList) {
                try {
                    this.hospitalDao.add(hospital);
                    cnt++;
                } catch (Exception e) {
                    System.out.printf("id:d% 레코드에 문제가 있습니다.", hospital.getId());
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }



}
