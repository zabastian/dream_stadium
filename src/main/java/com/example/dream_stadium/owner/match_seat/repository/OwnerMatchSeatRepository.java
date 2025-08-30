package com.example.dream_stadium.owner.match_seat.repository;

import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OwnerMatchSeatRepository extends JpaRepository<MatchSeat, Long> {

    @Modifying //Modifying는 select이외의 다른 쿼이 적용가능하게 한다.
    @Transactional
    @Query("delete from MatchSeat ms where ms.match.id = :matchId")
    void deleteByMatchId(@Param("matchId") Long matchId);

    @Modifying
    @Transactional
    @Query("delete from MatchSeat ms where ms.seat.id = :seatId")
    void deleteBySeatId(@Param("seatId") Long seatId);
}


