package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
@Service // Annotation do Spring. Sinaliza que o objeto pode ser injetado em tempo de execução (não é necessário instanciar)
public class DeviceServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    DeviceRepository repository;

    public List<Device> findAll(){
        logger.info("Finding all devices...");
        return repository.findAll();
    }

    public Device findById(Long id){
        logger.info("Finding one device...");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Device create(Device device){
        logger.info("Creating new device...");
        return repository.save(device);
    }

    public Device measurementUpdate(Device device){
        Device entity = this.findById(device.getId());
        logger.info("Updating device measurement...");
        entity.setMeasurementCH1(device.getMeasurementCH1());
        entity.setMeasurementCH2(device.getMeasurementCH2());
        entity.setLastCH1Status(device.isLastCH1Status());
        entity.setLastCH2Status(device.isLastCH2Status());
        entity.setLastUpdate(new Date());
        return repository.save(entity);
    }

    public Device deviceDataUpdate(Device device){
        Device entity = this.findById(device.getId());
        logger.info("Updating device data...");
        entity.setDeviceName(device.getDeviceName());
        entity.setInstallationName(device.getInstallationName());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting device...");
        Device entity = this.findById(id);
        repository.delete(entity);
    }
}