package fleetmanagementapi.service.impl;

import fleetmanagementapi.dto.TaxisDto;
import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.exception.NotFoundException;
import fleetmanagementapi.mapper.TaxisMapper;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.service.TaxisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaxisServiceImpl implements TaxisService {

    private TaxisRepository taxisRepository;


    @Override
    public TaxisDto getTaxisId(Integer taxisId) {
        Taxis taxis = taxisRepository.findById(taxisId)
                .orElseThrow(() -> new NotFoundException("Taxi does not exist with this ID: " + taxisId));
        return TaxisMapper.mapToTaxisDto(taxis);
    }

    @Override
    public List<TaxisDto> getTaxisPlate(String plate) {
        List<Taxis> taxisList = taxisRepository.findAllByPlate(plate);
        return taxisList.stream().map(TaxisMapper::mapToTaxisDto).collect(Collectors.toList());
    }
}