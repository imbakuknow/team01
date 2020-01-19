package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team01.entity.Branches;
import com.sut62.team01.entity.ElectricType;
import com.sut62.team01.entity.ElectricalRegistration;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.Students;
import com.sut62.team01.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * ElectricRegisterTests
 */
@DataJpaTest
public class ElectricRegisterTests {

    private Validator validator;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private BranchesRepository branchesRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private ElectricTypeRepository electricTypeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ElectricalRegistrationRepository electricalRegistrationRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6003296_testInsertDataOk() {
        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students, rooms, branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());
        electricalRegistration = electricalRegistrationRepository.saveAndFlush(electricalRegistration);

        Optional<ElectricalRegistration> found = electricalRegistrationRepository
                .findById(electricalRegistration.getId());
        assertEquals(electricalRegistration, found.get());
    }

    @Test
    void b6003296_testStaffMustNotBeNull(){
        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students, rooms, branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(null);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("staff", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6003296_testElectricTypeMustNotBeNull(){

        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students, rooms, branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(null);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("electricType", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6003296_testRoomBookingMustNoBeNull(){

        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students, rooms, branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(null);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("roomBooking", result.iterator().next().getPropertyPath().toString());

    }


}