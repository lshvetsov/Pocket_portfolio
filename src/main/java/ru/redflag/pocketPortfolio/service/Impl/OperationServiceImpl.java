package ru.redflag.pocketPortfolio.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.OperationCreateDto;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.OperationType;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Dividend;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.errors.WrongActionException;
import ru.redflag.pocketPortfolio.repositories.OperationRepository;
import ru.redflag.pocketPortfolio.service.DividendService;
import ru.redflag.pocketPortfolio.service.OperationService;
import ru.redflag.pocketPortfolio.service.PositionService;
import ru.redflag.pocketPortfolio.utils.mappers.OperationMapper;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;
    @Autowired
    OperationMapper operationMapper;
    @Autowired
    PositionService positionService;
    @Autowired
    DividendService dividendService;

    @Override
    public OperationDto addOperation(String portfolioId, OperationCreateDto operationDto) {

        Operation operation = operationMapper.toOperation(operationDto);

        Position position = operationDto.getPosition().getId() != null ?
                positionService.findById(operationDto.getPosition().getId()) :
                positionService.findByPortfolioIdAndTicker(portfolioId, operationDto.getPosition().getEquity().getTicker());

        if (Status.INACTIVE.equals(operation.getOperationStatus())) throw new WrongActionException();

        if (position == null) {
            if (OperationType.DIVIDEND.equals(operation.getOperationType())) throw new WrongActionException();
            position = positionService.addPosition(portfolioId, operationDto.getPosition());
        }

        operation.setPosition(position);
        operation.setPortfolio(position.getPortfolio());
        operation = operationRepository.save(operation);

        if (OperationType.DIVIDEND.equals(operationDto.getOperationType())) {
            processDividend(position, operation);
        } else {
            processOperation(position, operation);
        }

        return operationMapper.toOperationDto(operation);

    }
    @Override
    public OperationDto updateOperationStatus(String id, Status status) {
        Operation operation = operationRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        operation.setOperationStatus(status);
        if (OperationType.DIVIDEND.equals(operation.getOperationType())) {
            Dividend dividend = dividendService.findByOperationId(operation.getId());
            dividendService.updateDividendStatus(dividend.getId(), status);
        }
        log.info("Status of operation {} has been set to {}", operation.getId(), status);
        return operationMapper.toOperationDto(operationRepository.save(operation));
    }
    @Override
    public OperationDto updateOperation(String operationId, OperationDto operationDto) {
        Operation basicOperation = operationRepository.findById(operationId).orElseThrow(ObjectNotFoundException::new);
        Operation newOperation = operationMapper.toOperation(operationDto);
        newOperation.setId(basicOperation.getId());
        log.info("Operation {} has been updated: old state {}, new state {}", operationId, basicOperation, newOperation);
        return operationMapper.toOperationDto(operationRepository.save(newOperation));
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
    public List<OperationDto> getOperationByPortfolio(String portfolioId) {
        return operationRepository.findByPortfolioId(portfolioId).stream()
                .map(operationMapper::toOperationDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<OperationDto> searchByDates(String portfolioId, LocalDate from, LocalDate to) {
        List<Operation> operations = operationRepository.findByPortfolioId(portfolioId);
        if (from == null && to == null) return operations.stream().map(operationMapper::toOperationDto)
                .collect(Collectors.toList());
        if (from == null) return operations.stream().filter(x -> x.getDate().isBefore(to))
                .map(operationMapper::toOperationDto).collect(Collectors.toList());
        if (to == null) return operations.stream().filter(x -> x.getDate().isAfter(from))
                .map(operationMapper::toOperationDto).collect(Collectors.toList());
        return operations.stream().filter(x -> x.getDate().isBefore(to)).filter(x -> x.getDate().isAfter(from))
                .map(operationMapper::toOperationDto).collect(Collectors.toList());
    }

    @NotNull
    private void processOperation (Position position, Operation operation) {
        log.info("Operation {} under position {} has processed", operation, position.getId());
        positionService.upatePosition(position, operation);
    }
    @NotNull
    private void processDividend (Position position, Operation operation) {
        log.info("Operation {} under position {} has processed", operation, position.getId());
        dividendService.addDividend(position, operation);
    }

}
