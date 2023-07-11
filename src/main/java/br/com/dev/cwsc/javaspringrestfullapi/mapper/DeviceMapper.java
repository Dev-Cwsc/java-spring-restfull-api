package br.com.dev.cwsc.javaspringrestfullapi.mapper;

import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.DeviceVO;
import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mapping(source = "id", target = "key")
    @Mapping(source = "deviceName", target = "device")
    @Mapping(source = "installationName", target = "installation")
    @Mapping(source = "lastCH1Status", target = "CH1Status")
    @Mapping(source = "lastCH2Status", target = "CH2Status")
    @Mapping(source = "lastUpdate", target = "updateTime")
    DeviceVO deviceEntityToDeviceVO(Device device);

    @Mapping(source = "key", target = "id")
    @Mapping(source = "device", target = "deviceName")
    @Mapping(source = "installation", target = "installationName")
    @Mapping(source = "CH1Status", target = "lastCH1Status")
    @Mapping(source = "CH2Status", target = "lastCH2Status")
    @Mapping(source = "updateTime", target = "lastUpdate")
    Device deviceVOToDeviceEntity(DeviceVO deviceVO);

    List<DeviceVO> deviceEntityListToDeviceVOList(List<Device> devices);
}