package com.github.tehras.overwatchstats.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.heroes.Hero

/**
 * Created by tehras on 8/29/16.
 *
 * Hero Layout View
 */
class HeroLayoutView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    private lateinit var champIcon: ImageView
    private lateinit var champName: TextView
    private lateinit var champGames: TextView

    private lateinit var champGold: TextView
    private lateinit var champSilver: TextView
    private lateinit var champBronze: TextView

    private lateinit var champWinLoss: DataWithHint
    private lateinit var champWinRate: DataWithHint

    private lateinit var champEliminations: DataWithBarView
    private lateinit var champKillDeath: DataWithBarView
    private lateinit var champMostElims: DataWithBarView

    private lateinit var champDmgAverage: DataWithBarView
    private lateinit var champDmgTotal: DataWithBarView
    private lateinit var champDmgMost: DataWithBarView

    private lateinit var champObjTime: DataWithBarView
    private lateinit var champObjKills: DataWithBarView
    private lateinit var champDeaths: DataWithBarView

    private lateinit var fullChampView: View

    init {
        View.inflate(context, R.layout.view_hero_layout, this)

        champIcon = findViewById(R.id.champ_icon) as ImageView

        champName = findViewById(R.id.champ_name) as TextView
        champGames = findViewById(R.id.champ_games) as TextView

        champGold = findViewById(R.id.champ_gold) as TextView
        champSilver = findViewById(R.id.champ_silver) as TextView
        champBronze = findViewById(R.id.champ_bronze) as TextView

        champWinLoss = findViewById(R.id.champ_win_loss) as DataWithHint
        champWinRate = findViewById(R.id.champ_win_rate) as DataWithHint

        champEliminations = findViewById(R.id.champ_eliminations) as DataWithBarView
        champKillDeath = findViewById(R.id.champ_kill_death) as DataWithBarView
        champMostElims = findViewById(R.id.champ_most_elims) as DataWithBarView

        champDmgAverage = findViewById(R.id.champ_dmg_average) as DataWithBarView
        champDmgTotal = findViewById(R.id.champ_dmg_total) as DataWithBarView
        champDmgMost = findViewById(R.id.champ_dmg_most) as DataWithBarView

        champObjTime = findViewById(R.id.champ_obj_time) as DataWithBarView
        champObjKills = findViewById(R.id.champ_obj_kills) as DataWithBarView
        champDeaths = findViewById(R.id.champ_deaths) as DataWithBarView

        fullChampView = findViewById(R.id.champ_view)
    }

    fun populate(hero: Hero, user: OWAPIUser): HeroLayoutView {
        hero.loadImage(context, champIcon)

        champName.text = hero.name
        champGames.text = "${hero.generalStats?.gamesPlayed?.format(0)} Games"

        champGold.text = (hero.generalStats?.medalsGold?.format(0)?.toInt() ?: 0).toString()
        champSilver.text = (hero.generalStats?.medalsSilver?.format(0)?.toInt() ?: 0).toString()
        champBronze.text = (hero.generalStats?.medalsBronze?.format(0)?.toInt() ?: 0).toString()

        val color = context.resources.getColor(hero.getConfirmedType().color, null)

        champWinLoss.value.text = hero.winLosses()
        champWinRate.value.setWinPercentage(hero.winPercentage())

        champEliminations.setValueText("${(hero.generalStats?.eliminations ?: 0.toDouble())}")
                .setBarWidth(hero.generalStats?.eliminations?.times(100)?.div(user.mostElims().toDouble())?.toInt() ?: 0).setBarColor(color)

        champKillDeath.setValueText("${(hero.generalStats?.eliminationsPerLife ?: 0.toDouble()).format(1)}")
                .setBarWidth(hero.generalStats?.eliminationsPerLife?.times(100)?.div(user.highestElimsPerLife())?.toInt() ?: 0).setBarColor(color)

        champMostElims.setValueText("${hero.generalStats?.eliminationsMostInLife?.format(0) ?: 0}")
                .setBarWidth(hero.generalStats?.eliminationsMostInGame?.times(100)?.div(user.highestElimsInGame())?.toInt() ?: 0).setBarColor(color)

        champDmgAverage.setValueText("${hero.highestAverageDmg()}")
                .setBarWidth(hero.averageDmgJustNum().times(100.toDouble()).div(user.highestAverageDmg()).toInt()).setBarColor(color)

        champDmgTotal.setValueText("${hero.generalStats?.damageDone?.formatThousands() ?: 0}")
                .setBarWidth((hero.generalStats?.damageDone?.times(100)?.div(user.highestAverageTotalDmg()) ?: 0.toDouble()).toInt()).setBarColor(color)

        champDmgMost.setValueText("${hero.generalStats?.damageDoneMostInGame?.formatThousands()}")
                .setBarWidth((hero.generalStats?.damageDoneMostInGame?.times(100)?.div(user.highestMostDmg()) ?: 0.toDouble()).toInt()).setBarColor(color)

        champObjTime.setValueText("${hero.averageObjTime()}")
                .setBarColor(color).setBarWidth(hero.averageObjTimeD().times(100).div(user.highestObjTime()).toInt())

        champObjKills.setValueText("${hero.objectiveKills()}")
                .setBarColor(color).setBarWidth(hero.objectiveKills().times(100).div(user.highestObjectiveKills()).toInt())

        champDeaths.setValueText("${hero.averageDeaths().format(2)}")
                .setBarColor(color).setBarWidth(hero.averageDeaths().times(100).div(user.mostDeaths()).toInt())

        return this
    }
}