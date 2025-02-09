package com.example.footballanalytics.repository

import com.example.footballanalytics.model.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository : JpaRepository<Team, Long> {
    // 基本的なCRUD操作はJpaRepositoryで提供される
    
    // カスタムクエリメソッド
    fun findByCountry(country: String): List<Team>
    fun findByLeague(league: String): List<Team>
}