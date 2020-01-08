package com.sut62.team01;

import java.util.Collections;
import java.util.stream.Stream;

import com.sut62.team01.entity.BikeType;
import com.sut62.team01.entity.Branches;
import com.sut62.team01.entity.DateType;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.Students;
import com.sut62.team01.repository.BikeTypeRepository;
import com.sut62.team01.repository.BorrowedBikeRepository;
import com.sut62.team01.repository.BranchesRepository;
import com.sut62.team01.repository.DateTypeRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RoomsRepository;
import com.sut62.team01.repository.StudentsRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class SUT62TEAM01 {

    public static void main(String[] args) {
        SpringApplication.run(SUT62TEAM01.class, args);
    }

    @Bean
    ApplicationRunner init(BorrowedBikeRepository borrowedBikeRepository, BikeTypeRepository bikeTypeRepository,
            DateTypeRepository dateTypeRepository, RoomBookingRepository roomBookingRepository,
            StudentsRepository studentsRepository, BranchesRepository branchesRepository,
            RoomsRepository roomsRepository) {
        return args -> {

            // TODO: Inject test data
            // BikeType
            BikeType bikeType1 = new BikeType("จักรยาน 2 ล้อ สำหรับคนเดียว");
            BikeType bikeType2 = new BikeType("จักรยาน 2 ล้อ สำหรับสองคน");
            BikeType bikeType3 = new BikeType("จักรยาน 3 ล้อ แบบมีหลังคา");
            BikeType bikeType4 = new BikeType("จักรยาน 3 ล้อ แบบไม่มีหลังคา");
            Stream.of(bikeType1, bikeType2, bikeType3, bikeType4).forEach(bikeType -> {
                bikeTypeRepository.save(bikeType);
                System.out.println(bikeType);
            });

            // DateType
            DateType dateType1 = new DateType("ยืม 3 วัน");
            DateType dateType2 = new DateType("ยืม 7 วัน");
            DateType dateType3 = new DateType("ยืม 1 ภาคการศึกษา");
            Stream.of(dateType1, dateType2, dateType3).forEach(dateType -> {
                dateTypeRepository.save(dateType);
                System.out.println(dateType);
            });

            // Students
            Students student1 = new Students("นายพรเทพ ทวีทรัพย์", "b6000783", "pontep", "1234");
            Students student2 = new Students("นายเจตุพนน์ ศรีภูธร", "b6000123", "jayza", "1234");
            Students student3 = new Students("นายภูมิรินทร์ เพียวสูงเนิน", "b6003296", "leo2541", "1234");
            Stream.of(student1, student2, student3).forEach(student -> {
                studentsRepository.save(student);
                System.out.println(student);
            });

            // Branchs
            Branches branche1 = new Branches("branch คืออะไร");
            branchesRepository.save(branche1);

            // Room
            for (int i = 1; i <= 40; i++) {
                Rooms room = new Rooms("71" + (i < 10 ? "0" + i : i));
                roomsRepository.save(room);
            }
            Rooms room7133 = roomsRepository.findByRoomId("7133");

            // RoomBooking
            RoomBooking roomBooking1 = new RoomBooking(student1, room7133, branche1);
            roomBookingRepository.save(roomBooking1);

        };
    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}