package ru.redflag.pocketPortfolio.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.DividendDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Dividend;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.repositories.DividendRepository;
import ru.redflag.pocketPortfolio.service.DividendService;
import ru.redflag.pocketPortfolio.utils.mappers.DividendMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DividendServiceImpl implements DividendService {

    @Autowired
    DividendMapper dividendMapper;
    @Autowired
    DividendRepository dividendRepository;

    @Override
    public Dividend addDividend(Position position, Operation operation) {
        Dividend dividend = Dividend.builder()
                .position(position)
                .dividendStatus(operation.getOperationStatus())
                .currency(operation.getCurrency())
                .date(operation.getDate())
                .operationId(operation.getId())
                .amountPerUnit(operation.getPricePerUnit())
                .totalAmount(operation.getTotalPrice())
                .totalFee(operation.getTotalFee())
                .percentage(operation.getTotalPrice()/position.getCurrentCost())
                .build();
        log.info("Dividend {} has added", dividend);
        return dividendRepository.save(dividend);
    }

    @Override
    public Dividend updateDividendStatus(String dividendId, Status status) {
        Dividend dividend = dividendRepository.findById(dividendId).orElseThrow(ObjectNotFoundException::new);
        dividend.setDividendStatus(status);
        log.info("Status of dividend {} has updated to {}", dividendId, status);
        return dividendRepository.save(dividend);
    }

    @Override
    public DividendDto getDividend(String id) {
        return dividendMapper.dividendDto(dividendRepository.findById(id).orElseThrow(ObjectNotFoundException::new));
    }

    @Override
    public List<Dividend> getDividendsBydPosition (String positionId, LocalDate from, LocalDate to) {
        List<Dividend> dividends = dividendRepository.findByPositionId(positionId);
        if (from == null && to == null) return dividends;
        else if (from == null) return dividends.stream().filter(x -> x.getDate().isBefore(to)).collect(Collectors.toList());
        else if (to == null) return dividends.stream().filter(x -> x.getDate().isAfter(from)).collect(Collectors.toList());
        else return dividends.stream().filter(x -> x.getDate().isAfter(from))
                    .filter(x -> x.getDate().isBefore(to)).collect(Collectors.toList());
    }

    @Override
    public Dividend findByOperationId(String operationId) {
        return dividendRepository.findByOperationId(operationId);
    }
}
