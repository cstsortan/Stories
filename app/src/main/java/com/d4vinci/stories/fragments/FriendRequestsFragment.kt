package com.d4vinci.stories.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.d4vinci.stories.R


/**
 * A simple [Fragment] subclass.
 */
class FriendRequestsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_friend_requests, container, false)



        return view
    }

}// Required empty public constructor
