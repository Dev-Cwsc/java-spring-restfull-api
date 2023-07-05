package br.com.dev.cwsc.javaspringrestfullapi.controller;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.DeviceVO;
import br.com.dev.cwsc.javaspringrestfullapi.services.DeviceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceServices services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceVO> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceVO findById(@PathVariable(value = "id") Long id){
        return services.findById(id);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceVO create(@RequestBody DeviceVO deviceVO){
        return services.create(deviceVO);
    }

    @PutMapping(value = "/measurement", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceVO measurementUpdate(@RequestBody DeviceVO deviceVO){
        return services.measurementUpdate(deviceVO);
    }

    @PutMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceVO deviceDataUpdate(@RequestBody DeviceVO deviceVO){
        return services.deviceDataUpdate(deviceVO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}

