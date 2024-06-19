package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dto.EmployeeDto;
import com.dto.constantmessages.ConstantMessages;
import com.model.Employee;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Implementation of the EmployeeService interface.
 * 
 * This class provides implementation for the methods defined in the
 * EmployeeService interface. It interacts with the EmployeeRepo for data access
 * and uses ModelMapper for entity-DTO conversion.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepository employeeRepository;

	ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> createOrUpdateEmployee(EmployeeDto employeeDTO) {
		Long employeeId = employeeDTO.getId();
		if (employeeId != null && employeeRepository.existsById(employeeId)) {
			if (employeeRepository.existsByEmailAndIdNot(employeeDTO.getEmail(), employeeId)
					&& employeeRepository.existsByMobileNumberAndIdNot(employeeDTO.getMobileNumber(), employeeId)) {
				return ResponseEntity.ok("bothExist");
			} else if (employeeRepository.existsByEmailAndIdNot(employeeDTO.getEmail(), employeeId)) {
				return ResponseEntity.ok("emailExists");
			} else if (employeeRepository.existsByMobileNumberAndIdNot(employeeDTO.getMobileNumber(), employeeId)) {
				return ResponseEntity.ok("mobileExists");
			}
		} else {
			if (employeeRepository.existsByEmail(employeeDTO.getEmail())
					&& employeeRepository.existsByMobileNumber(employeeDTO.getMobileNumber())) {
				return ResponseEntity.ok("bothExist");
			} else if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
				return ResponseEntity.ok("emailExists");
			} else if (employeeRepository.existsByMobileNumber(employeeDTO.getMobileNumber())) {
				return ResponseEntity.ok("mobileExists");
			}
		}
		return ResponseEntity.ok(modelMapper.map(employeeRepository.save(modelMapper.map(employeeDTO, Employee.class)), EmployeeDto.class));
	}

	@Override
	public List<Employee> retriveAllEmployees() {
		return employeeRepository.findAll(Sort.by(Direction.DESC, "id"));
	}

	@Override
	public void deleteEmployee(Long id) {
		// Check if the id is present in database or not
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
		} else {
			// if id is not present throw error
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantMessages.USER_NOT_FOUND);
		}
	}

	@Override
	public Employee getEmployeeById(Long id) {
		// Check if the id is present in database or not
		if (employeeRepository.existsById(id)) {
			Optional<Employee> currentEmployee = employeeRepository.findById(id);
			if (currentEmployee.isPresent()) {
				return currentEmployee.get();
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantMessages.USER_NOT_FOUND);
			}
		} else {
			// if id is not present throw error
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantMessages.USER_NOT_FOUND);
		}
	}

}