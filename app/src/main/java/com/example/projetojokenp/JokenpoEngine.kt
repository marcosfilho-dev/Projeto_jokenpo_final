package com.example.projetojokenp

import kotlin.random.Random

enum class Result {
    Win,Draw, Loss
}
class JokenpoEngine (private val availablePlays: Array<String>){

    fun calculateResult(playerPlay : String): Result{
        val aiPlay = getAIPlay()
        return when{
            playerPlay == aiPlay -> Result.Draw
            playerPlay == "Pedra" && aiPlay == "Tesoura" -> Result.Win
            playerPlay == "Pedra" && aiPlay == "Papel" -> Result.Loss
            playerPlay == "Papel" && aiPlay == "Pedra" -> Result.Win
            playerPlay == "Papel" && aiPlay == "Tesoura" -> Result.Loss
            playerPlay == "Tesoura" && aiPlay == "Papel" -> Result.Win
            else -> Result.Loss
        }
    }
    private fun getAIPlay() : String{
        val  playIndex = Random.nextInt(0,2)
        return  availablePlays[playIndex]
    }
}