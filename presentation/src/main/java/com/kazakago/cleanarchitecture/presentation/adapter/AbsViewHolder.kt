package com.kazakago.cleanarchitecture.presentation.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class AbsViewHolder(protected val context: Context, parent: ViewGroup, @LayoutRes layoutResId: Int) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutResId, parent, false))