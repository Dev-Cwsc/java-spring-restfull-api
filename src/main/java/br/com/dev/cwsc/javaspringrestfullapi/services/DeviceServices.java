package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.controller.DeviceController;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.RequiredObjectIsNullException;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.DeviceMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.DeviceVO;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DeviceServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private DeviceRepository repository;

    @Autowired
    private DeviceMapper mapper;

    public List<DeviceVO> findAll() {
        logger.info("Finding all devices...");

        List<DeviceVO> deviceVOs = mapper.deviceEntityListToDeviceVOList(repository.findAll());

        deviceVOs.forEach(
                this::addLinks
        );
        return deviceVOs;
    }

    public DeviceVO findById(Long id) {
        logger.info("Finding one device...");

        Device entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        DeviceVO vo = mapper.deviceEntityToDeviceVO(entity);
        addLinks(vo);
        return vo;
    }

    public DeviceVO create(DeviceVO deviceVO) {
        if (deviceVO == null) throw new RequiredObjectIsNullException();

        logger.info("Creating new device...");

        DeviceVO vo = mapper.deviceEntityToDeviceVO(repository.save(mapper.deviceVOToDeviceEntity(deviceVO)));
        addLinks(vo);
        return vo;
    }

    public DeviceVO measurementUpdate(DeviceVO deviceVO) {
        if (deviceVO == null) throw new RequiredObjectIsNullException();

        Device entity = mapper.deviceVOToDeviceEntity(this.findById(deviceVO.getKey()));
        logger.info("Updating device measurement...");

        entity.setMeasurementCH1(deviceVO.getMeasurementCH1());
        entity.setMeasurementCH2(deviceVO.getMeasurementCH2());
        entity.setLastCH1Status(deviceVO.isCH1Status());
        entity.setLastCH2Status(deviceVO.isCH2Status());
        entity.setLastUpdate(new Date());

        DeviceVO vo = mapper.deviceEntityToDeviceVO(repository.save(entity));
        addLinks(vo);
        return vo;
    }

    public DeviceVO deviceDataUpdate(DeviceVO deviceVO) {
        if (deviceVO == null) throw new RequiredObjectIsNullException();

        Device entity = mapper.deviceVOToDeviceEntity(this.findById(deviceVO.getKey()));
        logger.info("Updating device data...");

        entity.setDeviceName(deviceVO.getDevice());
        entity.setInstallationName(deviceVO.getInstallation());

        DeviceVO vo = mapper.deviceEntityToDeviceVO(repository.save(entity));
        addLinks(vo);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting device...");

        Device entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    private void addLinks(DeviceVO vo) {
        vo.add(linkTo(methodOn(DeviceController.class).findById(vo.getKey())).withSelfRel());
        vo.add(linkTo(methodOn(DeviceController.class).measurementUpdate(vo)).withRel("measurement-update"));
        vo.add(linkTo(methodOn(DeviceController.class).deviceDataUpdate(vo)).withRel("data-update"));
    }
}