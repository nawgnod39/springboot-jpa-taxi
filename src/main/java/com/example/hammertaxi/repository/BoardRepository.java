package com.example.hammertaxi.repository;



import com.example.hammertaxi.model.Board;



import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Integer>{

	/*
	 * @Async
	 * 
	 * @Query(
	 * value="INSERT INTO LOCATION VALUES(:#FROM_LOCATION, :#TO_LOCATION, :#UserKey, :#ReqDTTM)"
	 * , nativeQuery = true) boolean saveLocation(@RequestBody Location loc);
	 * 
	 * Optional<Location> findByUsername(String saveLocation);
	 */
}