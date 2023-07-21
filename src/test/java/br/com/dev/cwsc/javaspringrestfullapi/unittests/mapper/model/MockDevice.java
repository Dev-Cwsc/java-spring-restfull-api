package br.com.dev.cwsc.javaspringrestfullapi.unittests.mapper.model;

import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.DeviceVO;

import java.util.ArrayList;
import java.util.List;

public class MockDevice {
    public Device mockEntity() {
        return mockEntity(0);
    }

    public DeviceVO mockVO() {
        return mockVO(0);
    }

    public List<Device> mockEntityList() {
        List<Device> devices = new ArrayList<Device>();
        for (int i = 0; i < 15; i++) {
            devices.add(mockEntity(i));
        }
        return devices;
    }

    public List<DeviceVO> mockVOList() {
        List<DeviceVO> deviceVOs = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            deviceVOs.add(mockVO(i));
        }
        return deviceVOs;
    }

    public Device mockEntity(Integer number) {
        Device device = new Device();
        device.setDeviceName("teste-restfull-api-" + number.longValue());
        device.setInstallationName("biblioteca");
        device.setId(number.longValue());
        return device;
    }

    public DeviceVO mockVO(Integer number) {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDevice("teste-restfull-api-" + number.longValue());
        deviceVO.setInstallation("biblioteca");
        deviceVO.setKey(number.longValue());
        return deviceVO;
    }
}