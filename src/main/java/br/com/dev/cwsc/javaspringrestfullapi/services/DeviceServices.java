package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.controller.DeviceController;
import br.com.dev.cwsc.javaspringrestfullapi.controller.UserController;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.DeviceVO;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.DeviceMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service // Annotation do Spring. Sinaliza que o objeto pode ser injetado em tempo de execução (não é necessário instanciar)
public class DeviceServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private DeviceRepository repository;

    @Autowired
    private DeviceMapper mapper;

    public List<DeviceVO> findAll(){
        logger.info("Finding all devices...");
        List<DeviceVO> deviceVOs = mapper.deviceEntityListToDeviceVOList(repository.findAll());
        deviceVOs.forEach(
                d -> {
                    d.add(linkTo(methodOn(DeviceController.class).findById(d.getKey())).withSelfRel());
                    d.add(linkTo(methodOn(DeviceController.class).measurementUpdate(d)).withRel("measurement-update"));
                    d.add(linkTo(methodOn(DeviceController.class).deviceDataUpdate(d)).withRel("data-update"));
                }
        );
        return deviceVOs;
    }

    public DeviceVO findById(Long id){
        logger.info("Finding one device...");
        return getDeviceVO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")));
    }

    public DeviceVO create(DeviceVO deviceVO){
        logger.info("Creating new device...");
        Device entity = mapper.deviceVOToDeviceEntity(deviceVO);
        return getDeviceVO(entity);
    }

    public DeviceVO measurementUpdate(DeviceVO deviceVO){
        Device entity = mapper.deviceVOToDeviceEntity(this.findById(deviceVO.getKey()));
        logger.info("Updating device measurement...");
        entity.setMeasurementCH1(deviceVO.getMeasurementCH1());
        entity.setMeasurementCH2(deviceVO.getMeasurementCH2());
        entity.setLastCH1Status(deviceVO.isCH1Status());
        entity.setLastCH2Status(deviceVO.isCH2Status());
        entity.setLastUpdate(new Date());
        return getDeviceVO(entity);
    }

    public DeviceVO deviceDataUpdate(DeviceVO deviceVO){
        Device entity = mapper.deviceVOToDeviceEntity(deviceVO);
        logger.info("Updating device data...");
        entity.setDeviceName(deviceVO.getDevice());
        entity.setInstallationName(deviceVO.getInstallation());
        return getDeviceVO(entity);
    }

    public void delete(Long id){
        logger.info("Deleting device...");
        Device entity = mapper.deviceVOToDeviceEntity(this.findById(id));
        repository.delete(entity);
    }

    private DeviceVO getDeviceVO(Device entity) {
        DeviceVO vo = mapper.deviceEntityToDeviceVO(repository.save(entity));
        vo.add(linkTo(methodOn(DeviceController.class).findById(vo.getKey())).withSelfRel());
        vo.add(linkTo(methodOn(DeviceController.class).measurementUpdate(vo)).withRel("measurement-update"));
        vo.add(linkTo(methodOn(DeviceController.class).deviceDataUpdate(vo)).withRel("data-update"));
        return vo;
    }
}