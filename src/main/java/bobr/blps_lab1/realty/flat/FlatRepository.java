package bobr.blps_lab1.realty.flat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat, Integer> {
    @Query("SELECT f FROM Flat f " +
            "WHERE (:floor is null or f.floor = :floor) " +
            "AND (:rooms is null or f.rooms in :rooms) " +
            "AND (:haveBalcony is null or f.haveBalcony = :haveBalcony) " +
            "AND (:isApartments is null or f.isApartments = :isApartments) " +
            "AND (:minTotalArea is null or f.totalArea >= :minTotalArea) " +
            "AND (:maxTotalArea is null or f.totalArea <= :maxTotalArea) " +
            "AND (:minKitchenArea is null or f.kitchenArea >= :minKitchenArea) " +
            "AND (:maxKitchenArea is null or f.kitchenArea <= :maxKitchenArea) " +
            "AND (:minLivingArea is null or f.livingArea >= :minLivingArea) " +
            "AND (:maxLivingArea is null or f.livingArea <= :maxLivingArea)"
    )
    List<Flat> findWithFilters(
            @Param("minTotalArea") Double minTotalArea,
            @Param("maxTotalArea") Double maxTotalArea,
            @Param("minKitchenArea") Double minKitchenArea,
            @Param("maxKitchenArea") Double maxKitchenArea,
            @Param("minLivingArea") Double minLivingArea,
            @Param("maxLivingArea") Double maxLivingArea,
            @Param("rooms") Integer[] rooms,
            @Param("floor") Integer floor,
            @Param("haveBalcony") Boolean haveBalcony,
            @Param("isApartments") Boolean isApartments
            );
}
