package com.github.tehras.overwatchstats.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.exts.setWinPercentage
import com.github.tehras.overwatchstats.models.heroes.Hero
import com.github.tehras.overwatchstats.models.heroes.HeroGeneralStats
import com.github.tehras.overwatchstats.models.heroes.Heroes
import com.github.tehras.overwatchstats.views.DataWithHint

/**
 * Created by tehras on 8/21/16.
 *
 * Recycler Adapter for Champion Display
 */
class ChampionAdapter(val heroes: Heroes) : RecyclerView.Adapter<ChampionAdapter.ChampHolder>() {

    private val TAG = "championAdapter"
    private val LOADING: CharSequence? = "Loading"

    override fun onBindViewHolder(holder: ChampHolder, position: Int) {
        val hero = heroes.heroes!![position]

        //lets draw the bar
        drawTheBar(hero, holder.bar, holder.overallLayout)
        holder.champName.text = hero.name
        holder.icon.setImageDrawable(holder.itemView.context.resources.getDrawable(getDynamicImage(hero, holder.itemView!!.context), null))

        if (hero.generalStats != null) {
            holder.champWinLoss.value.text = winLosses(hero.generalStats)
            holder.champWinRate.value.setWinPercentage(winPercentage(hero.generalStats))

            holder.champGames.text = hero.generalStats!!.timePlayed
        } else {
            holder.champWinLoss.value.text = LOADING
            holder.champWinRate.value.text = LOADING

            holder.champGames.text = ""
        }
    }

    private fun winLosses(generalStats: HeroGeneralStats?): String {
        val won = generalStats?.gamesWon?.toInt() ?: 0
        val losses = (generalStats?.gamesPlayed?.toInt() ?: 0).minus(generalStats?.gamesWon?.toInt() ?: 0)

        return "${won}W/${losses}L"
    }

    private fun winPercentage(generalStats: HeroGeneralStats?): Double {
        val won = generalStats?.gamesWon?.toDouble()
        var total = generalStats?.gamesPlayed?.toDouble()

        if (total == 0.toDouble())
            total = 1.toDouble()

        return (won?.toDouble() ?: 0.toDouble()).div(total?.toDouble() ?: 1.toDouble())
    }

    private fun getDynamicImage(hero: Hero, context: Context): Int {
        val id = context.resources?.getIdentifier(getDynamicName(hero), "drawable", context.packageName)?.toInt() ?: 0

        if (id.toInt() == 0)
            return R.drawable.ic_soldier76
        else
            return id
    }

    private fun getDynamicName(hero: Hero): String? {
        return "ic_${hero.name}"
    }

    private fun drawTheBar(hero: Hero, bar: LinearLayout?, itemView: View?) {

        itemView?.post {
            val width = hero.played?.times(itemView.measuredWidth.toDouble()) ?: 0.toDouble()
            val params = bar?.layoutParams

            bar?.setBackgroundColor(bar.context.resources.getColor(hero.getConfirmedType().color, null))

            params?.width = width.toInt()
            bar?.requestLayout()
        }
    }

    override fun getItemCount(): Int {
        return heroes.heroes?.size?.toInt() ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChampHolder {
        //ViewHolder
        return ChampHolder(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_champion_layout, parent, false))
    }

    class ChampHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        lateinit var champName: TextView
        lateinit var champGames: TextView
        lateinit var champWinLoss: DataWithHint
        lateinit var champWinRate: DataWithHint

        lateinit var icon: ImageView
        lateinit var bar: LinearLayout
        lateinit var overallLayout: View

        init {
            champName = itemView?.findViewById(R.id.champ_hero_name) as TextView
            champGames = itemView?.findViewById(R.id.champ_hero_games_played) as TextView
            champWinRate = itemView?.findViewById(R.id.champ_hero_win_rate) as DataWithHint
            champWinLoss = itemView?.findViewById(R.id.champ_hero_win_loss) as DataWithHint

            icon = itemView?.findViewById(R.id.champ_hero_icon) as ImageView
            bar = itemView?.findViewById(R.id.champ_hero_top_bar_size) as LinearLayout

            overallLayout = itemView?.findViewById(R.id.champ_hero_overall_layout)!!
        }
    }

    fun updateHero(hero: Hero) {
        val i = heroes.heroes?.indexOf(hero) ?: -1
        if (i != -1) {
            notifyItemChanged(i)
        }
    }

}