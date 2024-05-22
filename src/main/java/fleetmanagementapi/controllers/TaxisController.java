package fleetmanagementapi.controllers;

import fleetmanagementapi.dto.TaxisDto;
import fleetmanagementapi.service.TaxisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TaxisController {

    @Autowired
    private TaxisService taxisService;

    @GetMapping("{id}")
    public ResponseEntity<TaxisDto> getTaxisId(@PathVariable("id") Integer taxisId) {
        TaxisDto taxisDto = taxisService.getTaxisId(taxisId);
        return ResponseEntity.ok(taxisDto);
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<List<TaxisDto>> getTaxisPlate(
            @PathVariable("plate") String taxisPlate) {
        List<TaxisDto> taxisDto = taxisService.getTaxisPlate(taxisPlate);
        return ResponseEntity.ok(taxisDto);
    }

}
