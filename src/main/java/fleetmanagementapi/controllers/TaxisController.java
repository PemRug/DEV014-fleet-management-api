package fleetmanagementapi.controllers;


import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.service.TaxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;



@RestController
@RequestMapping("/")
public class TaxisController {

    private TaxisService taxisService;

    @Autowired
    public TaxisController(TaxisService taxisService) {
        this.taxisService = taxisService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taxis> getTaxisId(@PathVariable("id") Integer taxisId) {
        Taxis taxisDto = taxisService.getTaxisId(taxisId);
        return ResponseEntity.ok(taxisDto);
    }

    @GetMapping("/taxis")
    public Page<Taxis> getAllTaxis (
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int limit) {
        return taxisService.getAllTaxis(page, limit);
    }

    @GetMapping("/search")
    public Page<Taxis> searchTaxis(@RequestParam String partialPlate,
                                   @RequestParam (defaultValue = "0") int page,
                                   @RequestParam (defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return taxisService.findPlateContain(partialPlate, pageable);
    }

}
