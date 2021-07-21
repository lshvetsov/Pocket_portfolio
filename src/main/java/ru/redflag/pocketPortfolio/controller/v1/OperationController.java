package ru.redflag.pocketPortfolio.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.redflag.pocketPortfolio.data.dto.OperationCreateDto;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.service.OperationService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/operation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping()
    public OperationDto addOperation (@RequestParam String portfolioId,
                                      @RequestBody @Valid OperationCreateDto operationDto) {
        log.info("Operation {} under portfolio {} will be done", operationDto, portfolioId);
        return operationService.addOperation(portfolioId, operationDto);
    }

    @PostMapping("/dividend")
    public OperationDto addDividend (@RequestBody @Valid OperationCreateDto operationDto) {
        log.info("Dividend operation {} will be added", operationDto);
        return operationService.addOperation(null, operationDto);
    }

    @GetMapping
    public List<OperationDto> getAllOperations(){
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public OperationDto getOperation (@RequestParam String id) {
        return operationService.getOperation(id);
    }

    @PutMapping
    public OperationDto processOperation (@RequestParam String operationId, @RequestParam Status status) {
        log.info("Status of operation {} will be set to {}", operationId, status);
        return operationService.updateOperationStatus(operationId, status);
    }

    @GetMapping("/searchByDates")
    public List<OperationDto> searchOperationByDates (@RequestParam String portfolioId,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Nullable LocalDate from,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Nullable LocalDate to) {
        return operationService.searchByDates(portfolioId, from, to);
    }

}
