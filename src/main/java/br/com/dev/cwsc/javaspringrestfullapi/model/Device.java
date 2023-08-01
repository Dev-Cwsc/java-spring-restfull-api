package br.com.dev.cwsc.javaspringrestfullapi.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "devices")
public class Device implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "device_name", unique = true)
    private String deviceName;

    @Column(nullable = false, name = "installation_name")
    private String installationName;

    @Column(name = "last_measurement_CH1")
    private double measurementCH1;

    @Column(name = "last_measurement_CH2")
    private double measurementCH2;

    @Column(name = "last_CH1_status")
    private boolean lastCH1Status;

    @Column(name = "last_CH2_status")
    private boolean lastCH2Status;

    @Column(name = "last_update")
    private Date lastUpdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getInstallationName() {
        return installationName;
    }

    public void setInstallationName(String installationName) {
        this.installationName = installationName;
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

    public boolean isLastCH1Status() {
        return lastCH1Status;
    }

    public void setLastCH1Status(boolean lastCH1Status) {
        this.lastCH1Status = lastCH1Status;
    }

    public boolean isLastCH2Status() {
        return lastCH2Status;
    }

    public void setLastCH2Status(boolean lastCH2Status) {
        this.lastCH2Status = lastCH2Status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return getId() == device.getId() && Double.compare(device.getMeasurementCH1(), getMeasurementCH1()) == 0 && Double.compare(device.getMeasurementCH2(), getMeasurementCH2()) == 0 && isLastCH1Status() == device.isLastCH1Status() && isLastCH2Status() == device.isLastCH2Status() && Objects.equals(getDeviceName(), device.getDeviceName()) && Objects.equals(getInstallationName(), device.getInstallationName()) && Objects.equals(getLastUpdate(), device.getLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDeviceName(), getInstallationName(), getMeasurementCH1(), getMeasurementCH2(), isLastCH1Status(), isLastCH2Status(), getLastUpdate());
    }
}