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
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.models.heroes.Hero
import com.github.tehras.overwatchstats.models.heroes.Heroes
import com.github.tehras.overwatchstats.views.DataWithHint
import java.util.*

/**
 * Created by tehras on 8/21/16.
 *
 * Recycler Adapter for Champion Display
 */
class ChampionAdapter(var heroes: Heroes?) : RecyclerView.Adapter<ChampionAdapter.ChampHolder>() {

    fun updateHeroes(heroes: Heroes?) {
        this.heroes = heroes
        notifyDataSetChanged()
    }

    private var maxPlayed: Double? = 0.toDouble()

    init {
        //lets get max
        maxPlayedSet()
    }

    private fun maxPlayedSet() {
        var maxPlayed: Double = 0.toDouble()
        for (n in ((heroes?.heroes?.size?.minus(1)) ?: 0) downTo 0) {
            val it = heroes?.heroes?.get(n)
            val played = it?.played
            if (played?.isZero() ?: true) {
                getRealmInstance()?.singleTransaction { heroes?.heroes?.remove(it) }
            }
            if (played ?: 0.toDouble() > maxPlayed) {
                maxPlayed = played ?: 0.toDouble()
            }
        }

        this.maxPlayed = maxPlayed.toDouble()
    }

    private val LOADING: CharSequence? = "Loading"

    override fun onBindViewHolder(holder: ChampHolder, position: Int) {
        val hero = heroes?.heroes?.get(position)

        //lets draw the bar
        drawTheBar(hero, holder.bar, holder.overallLayout)
        holder.champName.text = hero?.name
        holder.icon.setImageDrawable(holder.itemView.context.resources.getDrawable(getDynamicImage(hero, holder.itemView!!.context), null))

        if (hero != null) {
            holder.champWinLoss.value.text = hero.winLosses()
            holder.champWinRate.value.setWinPercentage(hero.winPercentage())

            holder.champGames.text = "${hero.played?.format(2)} hours"
            historyText(hero, holder.historyText, holder.historyIcon)
        } else {
            holder.champWinLoss.value.text = LOADING
            holder.champWinRate.value.text = LOADING

            holder.champGames.text = ""
            holder.historyIcon.setImageResource(R.drawable.ic_timer_red)
            holder.historyText.setText(R.string.history_loading_text, holder.itemView.context)
        }
    }

    private fun historyText(hero: Hero, historyText: TextView, historyIcon: ImageView) {
        val diff = getDiff(hero)
        historyText.text = getHistoryText(diff)

        if (diff < ONE_MINUTE)
            historyIcon.setImageResource(R.drawable.ic_timer_green)
        else
            historyIcon.setImageResource(R.drawable.ic_timer_orange)
    }

    private fun getDiff(hero: Hero): Long {
        val c = Calendar.getInstance()
        val time = Calendar.getInstance()
        time.timeInMillis = hero.lastUpdated ?: 0.toLong()

        //today
        val diff = c.timeInMillis - time.timeInMillis

        return diff
    }

    private fun Long.isMultiple(): String {
        return if (this == 1.toLong()) "" else "s"
    }


    private fun getHistoryText(diff: Long): CharSequence {
        if (diff < ONE_MINUTE) {
            return "Up to date"
        } else if (diff < ONE_HOUR) {
            val minutes = diff.div(ONE_MINUTE)

            return "Updated $minutes minute${minutes.isMultiple()} ago"
        } else if (diff < ONE_HOUR.times(24)) {
            val hours = diff.div(ONE_HOUR)

            return "Updated $hours hour${hours.isMultiple()} ago"
        } else if (diff < ONE_DAY * 7) {
            val days = diff.div(ONE_DAY)

            return "Updated $days days ago"
        } else {
            val weeks = diff.div(ONE_DAY.times(7))

            return "Updated $weeks week${weeks.isMultiple()} ago"
        }

    }

    private fun getDynamicImage(hero: Hero?, context: Context): Int {
        val id = context.resources?.getIdentifier(getDynamicName(hero), "drawable", context.packageName)?.toInt() ?: 0

        if (id.toInt() == 0)
            return R.drawable.ic_soldier76
        else
            return id
    }

    private fun getDynamicName(hero: Hero?): String? {
        return "ic_${hero?.name?.toLowerCase() ?: "bastion"}"
    }

    private fun drawTheBar(hero: Hero?, bar: LinearLayout?, itemView: View?) {

        itemView?.post {
            val width = (hero?.played?.div(maxPlayed ?: 1.toDouble()) ?: 0.toDouble()).times(itemView.measuredWidth.toDouble())
            val params = bar?.layoutParams

            bar?.setBackgroundColor(bar.context.resources.getColor(hero?.getConfirmedType()?.color ?: 0, null))

            params?.width = width.toInt()
            bar?.requestLayout()
        }
    }

    override fun getItemCount(): Int {
        return heroes?.heroes?.size?.toInt() ?: 0
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

        lateinit var historyIcon: ImageView
        lateinit var historyText: TextView

        init {
            champName = itemView?.findViewById(R.id.champ_hero_name) as TextView
            champGames = itemView?.findViewById(R.id.champ_hero_games_played) as TextView
            champWinRate = itemView?.findViewById(R.id.champ_hero_win_rate) as DataWithHint
            champWinLoss = itemView?.findViewById(R.id.champ_hero_win_loss) as DataWithHint

            icon = itemView?.findViewById(R.id.champ_hero_icon) as ImageView
            bar = itemView?.findViewById(R.id.champ_hero_top_bar_size) as LinearLayout

            overallLayout = itemView?.findViewById(R.id.champ_hero_overall_layout)!!

            historyIcon = itemView?.findViewById(R.id.champ_hero_last_updated_icon) as ImageView
            historyText = itemView?.findViewById(R.id.champ_hero_last_updated) as TextView
        }
    }

    val TAG = "ChampionAdapter"

    fun updateHero(hero: Hero) {
        val i = heroes?.heroes?.getIndex(hero) { this.name.equals(it.name) } ?: -1

        if (i != -1) {
            heroes?.heroes?.replace(i = i, s = hero) {}

            maxPlayedSet()
            notifyItemChanged(i)
        }
    }

}