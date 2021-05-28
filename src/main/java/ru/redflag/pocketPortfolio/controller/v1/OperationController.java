package ru.redflag.pocketPortfolio.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.service.OperationService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping
    public OperationDto addOperation (@RequestParam String portfolioId, @RequestBody OperationDto operationDto) {
        return operationService.addOperation(portfolioId, operationDto);
    }

    @PostMapping("/dividend")
    public OperationDto addDividend (@RequestParam String portfolioId, @RequestBody OperationDto operationDto) {
        return null;
    }

    @GetMapping
    public List<OperationDto> getAllOperations(){
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public OperationDto getOperation (@RequestParam String id) {
        return operationService.getOperation(id);
    }

    @PutMapping("/{id}/{status}")
    public OperationDto processOperation (@RequestParam String operationId, @RequestParam Status status) {
        return operationService.updateOperationStatus(operationId, status);
    }

    @GetMapping("/searchByParameters")
    public List<OperationDto> searchOperationByParameters () {
        return null;
    }

    @GetMapping("/searchByDates")
    public List<OperationDto> searchOperationByDates (@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return null;
    }

}
