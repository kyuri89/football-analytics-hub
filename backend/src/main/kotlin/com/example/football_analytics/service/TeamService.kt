package com.example.footballanalytics.service

import com.example.footballanalytics.model.Team
import com.example.footballanalytics.repository.TeamRepository
import com.example.footballanalytics.resolver.UpdateTeamInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {
    fun getAllTeams(): List<Team> = teamRepository.findAll()

    fun getTeamById(id: Long): Team? = teamRepository.findById(id).orElse(null)

    fun getTeamsByCountry(country: String): List<Team> = 
        teamRepository.findByCountry(country)

    fun getTeamsByLeague(league: String): List<Team> = 
        teamRepository.findByLeague(league)

    @Transactional
    fun createTeam(team: Team): Team = teamRepository.save(team)

    @Transactional
    fun updateTeam(id: Long, input: UpdateTeamInput): Team? {
        val existingTeam = getTeamById(id) ?: return null
        
        val updatedTeam = existingTeam.copy(
            name = input.name ?: existingTeam.name,
            country = input.country ?: existingTeam.country,
            league = input.league ?: existingTeam.league,
            latitude = input.latitude ?: existingTeam.latitude,
            longitude = input.longitude ?: existingTeam.longitude
        )
        
        return teamRepository.save(updatedTeam)
    }

    @Transactional
    fun deleteTeam(id: Long): Boolean {
        return if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}