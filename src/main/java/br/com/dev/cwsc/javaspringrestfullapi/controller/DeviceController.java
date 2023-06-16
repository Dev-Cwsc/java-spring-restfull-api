package br.com.dev.cwsc.javaspringrestfullapi.controller;
import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
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
    public List<Device> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Device findById(@PathVariable(value = "id") Long id){
        return services.findById(id);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Device create(@RequestBody Device user){
        return services.create(user);
    }

    @PutMapping(value = "/measurement", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Device measurementUpdate(@RequestBody Device device){
        return services.measurementUpdate(device);
    }

    @PutMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Device deviceDataUpdate(@RequestBody Device device){
        return services.deviceDataUpdate(device);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}

