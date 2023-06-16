package br.com.dev.cwsc.javaspringrestfullapi.repositories;

import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
