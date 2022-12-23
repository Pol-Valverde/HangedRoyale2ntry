package com.example.hangedroyale2ntry.leaderBoard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hangedroyale2ntry.databinding.ItemLeaderboardBinding

class LeaderBoardRecycleViewAdapter : RecyclerView.Adapter<LeaderBoardRecycleViewAdapter.LeaderBoardViewHolder>(){

    private var leaderBoardList : List<LeaderBoardItem>? = null

    inner class LeaderBoardViewHolder(binding: ItemLeaderboardBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        val rankPosition = binding.rankingPositionText
        val username = binding.usernameText
        val score = binding.scoreText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLeaderboardBinding.inflate(inflater, parent, false)

        return LeaderBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int)
    {
        val item = leaderBoardList?.get(position)

        holder.rankPosition.text = item?.rankingPosition.toString()
        holder.username.text = item?.username ?: "Username is null"
        holder.score.text = item?.score.toString()
    }

    override fun getItemCount(): Int
    {
        return leaderBoardList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updatedList: List<LeaderBoardItem>)
    {
        leaderBoardList = updatedList
        notifyDataSetChanged()
    }
}