package br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "device", "installation", "measurementCH1", "measurementCH2", "CH1Status", "CH2Status", "updateTime"})
public class DeviceVO extends RepresentationModel<DeviceVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private long key;
    private String device;
    private String installation;
    private double measurementCH1;
    private double measurementCH2;
    private boolean CH1Status;
    private boolean CH2Status;
    private Date updateTime;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getInstallation() {
        return installation;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public double getMeasurementCH1() {
        return measurementCH1;
    }

    public void setMeasurementCH1(double measurementCH1) {
        this.measurementCH1 = measurementCH1;
    }

    public double getMeasurementCH2() {
        return measurementCH2;
    }

    public void setMeasurementCH2(double measurementCH2) {
        this.measurementCH2 = measurementCH2;
    }

    public boolean isCH1Status() {
        return CH1Status;
    }

    public void setCH1Status(boolean CH1Status) {
        this.CH1Status = CH1Status;
    }

    public boolean isCH2Status() {
        return CH2Status;
    }

    public void setCH2Status(boolean CH2Status) {
        this.CH2Status = CH2Status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceVO deviceVO = (DeviceVO) o;
        return getKey() == deviceVO.getKey() && Double.compare(deviceVO.getMeasurementCH1(),
                getMeasurementCH1()) == 0 && Double.compare(deviceVO.getMeasurementCH2(),
                getMeasurementCH2()) == 0 && isCH1Status() == deviceVO.isCH1Status() && isCH2Status() == deviceVO.isCH2Status() && Objects.equals(getDevice(),
                deviceVO.getDevice()) && Objects.equals(getInstallation(),
                deviceVO.getInstallation()) && Objects.equals(getUpdateTime(), deviceVO.getUpdateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getDevice(), getInstallation(), getMeasurementCH1(), getMeasurementCH2(), isCH1Status(), isCH2Status(), getUpdateTime());
    }
}