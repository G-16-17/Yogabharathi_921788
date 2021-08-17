package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
	@Query("select s from Stock s where s.stockCode=?1 and month(s.stockDate)=?2 and year(s.stockDate)=?3")
	public List<Stock> findStockByCodeAndMonthAndYear(String code, int month, int year);

	@Query("select s from Stock s where s.stockCode = ?1 and s.closingStock>?2")
	public List<Stock> findGoogleStockByPrice(String name, int price);

	@Query("select s from Stock s")
	public List<Stock> findHighestThreeVolume(Pageable page);

	@Query("select s from Stock s where s.stockCode=?1")
	public List<Stock> findStockByCodeAndVolume(String name, Sort sort);
}