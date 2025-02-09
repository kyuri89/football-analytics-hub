package com.example.footballanalytics.resolver

import com.example.footballanalytics.model.Team
import com.example.footballanalytics.service.TeamService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class TeamResolver(
    private val teamService: TeamService
) {
    @QueryMapping
    fun teams(): List<Team> = teamService.getAllTeams()

    @QueryMapping
    fun team(@Argument id: Long): Team? = teamService.getTeamById(id)

    @QueryMapping
    fun teamsByCountry(@Argument country: String): List<Team> = 
        teamService.getTeamsByCountry(country)

    @QueryMapping
    fun teamsByLeague(@Argument league: String): List<Team> = 
        teamService.getTeamsByLeague(league)

    @MutationMapping
    fun createTeam(@Argument input: CreateTeamInput): Team = 
        teamService.createTeam(input.toTeam())

    @MutationMapping
    fun updateTeam(@Argument id: Long, @Argument input: UpdateTeamInput): Team? =
        teamService.updateTeam(id, input)

    @MutationMapping
    fun deleteTeam(@Argument id: Long): Boolean = 
        teamService.deleteTeam(id)
}

data class CreateTeamInput(
    val name: String,
    val country: String,
    val league: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toTeam() = Team(
        name = name,
        country = country,
        league = league,
        latitude = latitude,
        longitude = longitude
    )
}

data class UpdateTeamInput(
    val name: String?,
    val country: String?,
    val league: String?,
    val latitude: Double?,
    val longitude: Double?
)