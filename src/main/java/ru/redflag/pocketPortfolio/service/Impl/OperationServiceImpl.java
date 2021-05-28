package ru.redflag.pocketPortfolio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.repositories.OperationRepository;
import ru.redflag.pocketPortfolio.repositories.PortfolioRepository;
import ru.redflag.pocketPortfolio.service.OperationService;
import ru.redflag.pocketPortfolio.utils.OperationMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    OperationMapper operationMapper;

    @Override
    public OperationDto addOperation(String portfolioId, OperationDto operationDto) {
        Operation operation = operationMapper.toOperation(operationDto);
        operation.setPortfolio(portfolioRepository.findById(portfolioId).orElseThrow(ObjectNotFoundException::new));
        return operationMapper.toOperationDto(operation);
    }

    @Override
    public OperationDto updateOperationStatus(String id, Status status) {
        Operation operation = operationRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        operation.setOperationStatus(status);
        return operationMapper.toOperationDto(operationRepository.save(operation));
    }

    @Override
    public List<OperationDto> getAllOperations() {
        List<OperationDto> operations = new ArrayList<>();
        operationRepository.findAll().forEach(x -> operations.add(operationMapper.toOperationDto(x)));
        return operations;
    }

    @Override
    public OperationDto getOperation(String id) {
        Operation operation = operationRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        return operationMapper.toOperationDto(operation);
    }

    @Override
    public List<OperationDto> searchByParameters() {
        return null;
    }

    @Override
    public List<OperationDto> searchByDates(LocalDate from, LocalDate to) {
        return null;
    }
}
