package fleetmanagementapi.service.impl;


import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.exception.NotFoundException;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.service.TaxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TaxisServiceImpl implements TaxisService {

    private TaxisRepository taxisRepository;

    @Autowired
    public TaxisServiceImpl(TaxisRepository taxisRepository) {
        this.taxisRepository = taxisRepository;
    }
    
    @Override
    public Taxis getTaxisId(Integer taxisId) {
        Taxis taxis = taxisRepository.findById(taxisId)
                .orElseThrow(() -> new NotFoundException("Taxi does not exist with this ID: " + taxisId));
        return taxis;
    }

    @Override
    public List<Taxis> getTaxisPlate(String plate) {
        List<Taxis> taxisList = taxisRepository.findAllByPlate(plate);
        return taxisList;
    }

    @Override
    public Page<Taxis> getAllTaxis(int page, int limit) {
        PageRequest pageable = PageRequest.of(page,limit);
        Page<Taxis> taxisPage = taxisRepository.findAll(pageable);
        return taxisPage;
    }

    @Override
    public Page<Taxis> findPlateContain(String partialPlate, Pageable pageable) {
        return taxisRepository.findPlateContain(partialPlate, pageable);
    }
}