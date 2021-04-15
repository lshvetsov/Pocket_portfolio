package ru.redflag.pocketPortfolio.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.service.PortfolioService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @PostMapping
    public PortfolioDto createPortfolio(@RequestParam String name,
                                        @RequestParam LocalDate horizon,
                                        @RequestParam Broker broker) {
        return portfolioService.createPortfolio(name, horizon, broker);
    }

    @GetMapping("/{id}")
    public PortfolioDto getPortfolio(@PathVariable String id) {
        return  portfolioService.getPortfolio(id);
    }

    @GetMapping
    public List<PortfolioDto> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @DeleteMapping("/{id}")
    public void deletePortfolio (@PathVariable String id) {
        portfolioService.deletePortfolio(id);
    }

    @PutMapping("/{id}/status")
    public void inactivatePortfolio (@PathVariable String id) {
        portfolioService.changeStatus(id);
    }

    @PutMapping("/{id}")
    public PortfolioDto renamePortfolio (@PathVariable String id,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) LocalDate term) {
        PortfolioDto portfolioDto = null;
        if (name != null) portfolioDto = portfolioService.updateName(id, name);
        if (term != null) portfolioDto = portfolioService.updateHorizon(id, term);
        return portfolioDto;
    }
}
