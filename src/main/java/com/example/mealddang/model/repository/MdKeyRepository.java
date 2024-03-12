package com.example.mealddang.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdKey;

public interface MdKeyRepository extends JpaRepository<MdKey,Long>{

    @Query(value = "SELECT COUNT(DISTINCT restaurant_id) FROM md_restaurant_keyword WHERE keyword = :searchkey", nativeQuery = true)
    int countByKeyword(@Param("searchkey") String searchkey);
}
// 분위기
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '분위기';
// 맛있는
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '맛있는'
// OR keyword = '맛';
// 깔끔한 = 깨끗
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '깨끗';
// 청결 = 청결
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '청결';
// 예약가능 = 예약
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '예약';
// 대형매장 = 대형
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '대형';
// 회식
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '회식';
// 혼밥
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '혼밥';
// 데이트
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '데이트';
// 가족모임
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '가족모임';
// 단체석
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '단체석';
// 룸
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '룸';
// 프라이빗
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '프라이빗';
// 주차
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '주차';
// 콜키지프리
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '콜키지프리';
// 전통
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '전통';
// 배달
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '배달';
// 포장
// SELECT count(distinct(restaurant_id))
// FROM md_restaurant_keyword
// WHERE keyword = '포장';
// 24시
// SELECT count(distinct(store_id_o))
// FROM md_store_master_tmp
// WHERE 1=1
// AND store_businessHours LIKE '%00:00~24:00%';
// 연중무휴
// SELECT count(distinct(store_id_o))
// FROM md_store_master_tmp
// WHERE 1=1
// AND store_businessHours LIKE '%연중무휴%';
// 반려동물
// SELECT count(distinct(store_id))
// FROM md_review
// WHERE 1=1
// AND review LIKE '%반려동물%';
// 새로 오픈 = %새로 오픈%
// SELECT count(distinct(store_id))
// FROM md_review
// WHERE 1=1
// AND review LIKE '%새로 오픈%'
// AND visited LIKE '%23.12%';