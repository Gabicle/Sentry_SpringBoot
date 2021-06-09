package com.sentry.Sentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sentry.Sentry.entity.Room;

import com.sentry.Sentry.entity.Device;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {




	@Query(value="Select * from device WHERE FK_ROOM_ID IN ( SELECT ROOM_ID FROM room WHERE FK_USER_ID = ?1)",nativeQuery=true)
	 List<Device> findAll(int user_id);

	//Room

	@Query(value="Select count(*) from device WHERE  DEVICE_STATUS = 1 AND FK_ROOM_ID IN ( SELECT ROOM_ID FROM room WHERE FK_USER_ID = ?1)",nativeQuery=true)
	 long countactive(int user_id);

	@Query(value="Select count(*) from device WHERE  DEVICE_STATUS = 0 AND FK_ROOM_ID IN ( SELECT ROOM_ID FROM room WHERE FK_USER_ID = ?1)",nativeQuery=true)
	 long countinactive(int user_id);

	@Query(value="Select count(*) from device WHERE  DEVICE_STATUS = -1 AND FK_ROOM_ID IN ( SELECT ROOM_ID FROM room WHERE FK_USER_ID = ?1)",nativeQuery=true)
	 long counterror(int user_id);

	@Query(value="Select * from device WHERE FK_ROOM_ID= ?1",nativeQuery=true)
    List<Device> findDevicesByRoomRid(int rid);

	@Query(value="Select DEVICE_STATUS from device WHERE DEVICE_ID= ?1",nativeQuery=true)
	boolean getDeviceStatus(int did);

	@Transactional
	@Modifying
	@Query(value="update device SET DEVICE_STATUS = ?1 WHERE DEVICE_ID= ?2",nativeQuery=true)
	void updateDeviceStatus(int status, Integer did);

}